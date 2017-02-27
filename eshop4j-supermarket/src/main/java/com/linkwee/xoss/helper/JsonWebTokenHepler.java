package com.linkwee.xoss.helper;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisCluster;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.xoss.constant.InnerResponseConstant;
import com.linkwee.xoss.constant.TimeSetConstants;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class JsonWebTokenHepler {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonWebTokenHepler.class);

	@Resource
	private JedisCluster jedisCluster;
	
	private static Map<String, Long> tokenMap = new HashMap<String, Long>();
	
	private static byte[] sharedSecret = null;
	
	/**
	 * 认证标示
	 */
	private static String issuerClaim = "https://www.linkwee.com";
	
	static {
		sharedSecret = StringUtils.getBytesUtf8(issuerClaim);
	}
	
	/**
	 * token校验
	 * @param token
	 * @return
	 * @throws ParseException
	 */
	public BaseResponse checkToken(String token){
		try {
			//判断token是否为空
			if(org.apache.commons.lang.StringUtils.isBlank(token) || "null".equalsIgnoreCase(token.trim())){
				LOGGER.info("token校验失败,token为NULL");
				return InnerResponseConstant.TOKEN_NOTNULL;
			}
			
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(sharedSecret);
			//验证成功
			if(signedJWT.verify(verifier)){
				String myClaim = signedJWT.getJWTClaimsSet().getIssuerClaim();
				//检验认证标示是否相等
				if(!myClaim.equals(issuerClaim)){
					return InnerResponseConstant.TOKEN_INVALID_PARAMERRO;
				}
				//判断是否过了有效期
				if(jedisCluster != null){
					if(!jedisCluster.exists("investor#"+token) && !jedisCluster.exists("channel#"+token)){
						return InnerResponseConstant.TOKEN_INVALID_TIMEOUT;
					}
				} else {
					if((tokenMap.get("investor#"+token) != null && System.currentTimeMillis()>tokenMap.get("investor#"+token)) 
					|| (tokenMap.get("channel#"+token) != null && System.currentTimeMillis()>tokenMap.get("channel#"+token))){
						return InnerResponseConstant.TOKEN_INVALID_TIMEOUT;
					}
				}
				//返回获取的userid
				String userId = signedJWT.getJWTClaimsSet().getSubjectClaim();
				return new BaseResponse("token_valid_success", userId);
			} else {
				//验证失败
				return InnerResponseConstant.TOKEN_INVALID_FAIL;
			}
		} catch (Exception e) {
			LOGGER.info("token校验异常",e);
			return InnerResponseConstant.TOKEN_INVALID_EXCEPTION;
		}
	}
	
	/**
	 * 创建token
	 * @param userId 根据userId创建令牌
	 * @return
	 */
	public String creatToken(String appKind,String userId){
		String saveKey = "";
		String token = "";
		try {
			JWTClaimsSet claimsSet = new JWTClaimsSet();
			claimsSet.setSubjectClaim(userId);//被认证目标
			claimsSet.setIssuerClaim(issuerClaim);//认证目标
			long tokenValidityTime = System.currentTimeMillis()+TimeSetConstants.TOKEN_VALID_DATE;//有效期
			claimsSet.setExpirationTimeClaim(tokenValidityTime);
			
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);//加密
			JWSSigner signer = new MACSigner(sharedSecret);
			signedJWT.sign(signer);//签名
			token = signedJWT.serialize();//生成token
			saveKey = appKind+"#"+token;//拼接待保存的key
			//判断是否过了有效期
			if(jedisCluster != null){//若redis存在
				jedisCluster.setex(saveKey, (int)TimeSetConstants.TOKEN_VALID_DATE/1000,token);
			} else {
				//将token保存到tokenMap
				LOGGER.info("将token保存到tokenMap:{}",token);
				if(tokenMap.size() >= 10000){
					//将过期的token清除
					for(Map.Entry<String, Long> entry: tokenMap.entrySet()){
						if(System.currentTimeMillis() > entry.getValue()){
							tokenMap.remove(entry.getKey());
						}
					}
				}
				tokenMap.put(saveKey, tokenValidityTime);
			}
		} catch (JOSEException e) {
			LOGGER.info("token生成异常,被认证的目标是:{}",userId,e);
		}
		return token;
	}
	
	/**
	 * 根据token获取userId
	 * @param token
	 * @return
	 */
	public static String getUserIdByToken(String token){
		String userId = "undefined";
		try {
			if(null == token || "".equals(token) || token.length() < 12){
				LOGGER.info("根据token获取userId失败,token为空或者token不符合规范");
			} else {	
				SignedJWT signedJWT = SignedJWT.parse(token);
				JWSVerifier verifier = new MACVerifier(sharedSecret);
				//验证成功
				if(signedJWT.verify(verifier)){
					userId = signedJWT.getJWTClaimsSet().getSubjectClaim();
					LOGGER.info("根据token获取userId成功,userId={},token={}",userId,token);
				} else {
					//验证失败
					LOGGER.info("根据token获取userId验证失败,token={},verifier={}",token,verifier);
				}
			}
		} catch (Exception e) {
			LOGGER.info("根据token获取userId异常,token={}",token,e);
		}
		return userId;
	}
	
	/**
	 * 重新设置token有效期
	 * @param token
	 * @return
	 */
	public boolean resetTokenValidDate(String appKind,String token){
		String saveKey = appKind+"#"+token;
		if(jedisCluster != null){//若redis存在
			jedisCluster.setex(saveKey, (int)TimeSetConstants.TOKEN_VALID_DATE/1000, token);
			return true;
		} else {
			if(tokenMap.containsKey(saveKey)){
				long tokenValidityTime = System.currentTimeMillis()+TimeSetConstants.TOKEN_VALID_DATE;//有效期
				tokenMap.put(saveKey, tokenValidityTime);
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * 删除token
	 * @param token
	 * @return 删除成功or失败
	 */
	public boolean removeToken(String appKind,String token){
		String saveKey = appKind+"#"+token;
		if(jedisCluster != null){//若redis存在
			return jedisCluster.del(saveKey)>0 ? true:false;
		} else {
			return isTokenExist(saveKey) ? tokenMap.remove(saveKey)>0:false;
		}
	}
	
	/**
	 * 判断token是否存在
	 * @param token
	 * @return
	 */
	private boolean isTokenExist(String saveKey){
		if(jedisCluster != null){//若redis存在
			return jedisCluster.exists(saveKey);
		} else {
			if(tokenMap.containsKey(saveKey)){
				return true;
			} else {
				return false;
			}
		}
	}
}
