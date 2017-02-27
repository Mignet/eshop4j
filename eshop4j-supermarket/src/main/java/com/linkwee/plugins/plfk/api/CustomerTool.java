package com.linkwee.plugins.plfk.api;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bill99.asap.exception.CryptoException;
import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;
import com.bill99.schema.commons.Version;
import com.bill99.schema.fo.commons.RequestHeader;
import com.bill99.schema.fo.commons.SealDataType;
import com.bill99.schema.fo.settlement.BatchSettlementApplyResponse;
import com.bill99.schema.fo.settlement.BatchidQueryResponse;
import com.bill99.schema.fo.settlement.ComplexQueryResponse;
import com.bill99.schema.fo.settlement.SettlementPkiApiRequest;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;
import com.bill99.schema.fo.settlement.SettlementPkiRequestType;
import com.bill99.schema.fo.settlement.SettlementPkiResponseType;
import com.bill99.seashell.common.util.Base64Util;
import com.bill99.seashell.common.util.DateUtil;
import com.linkwee.plugins.plfk.entity.DealInfoEntity;
import com.linkwee.plugins.plfk.util.CustomerUtil;
import com.linkwee.plugins.plfk.util.GzipUtil;
import com.linkwee.plugins.plfk.util.SftpUtil;


/**
 * 批量付款数据处理理
 * */
public class CustomerTool {

	private final String ENCODING = "utf-8";
	private final String ACTION_APPLY = "fo.batch.settlement.pay";
	private final String ACTION_COMPLEXQUERY = "fo.batch.settlement.complexquery";
	private final String ACTION_BATCHIDQUERY = "fo.batch.settlement.batchidquery";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTool.class);
	
	/**
	 * 提交请求，并且拿到对应的应答
	 * @param dealInfo 请求数据内容
	 * @return SettlementPkiApiResponse 对应的密文应答信息
	 */
	public SettlementPkiApiResponse apply_ws(DealInfoEntity dealInfo) {
			//返回应答信息
			return FoApiPkiWSClient.doit(this.getSettlementPkiApiRequest(dealInfo));
	}
	
	/**
	 * FTP上传文件
	 * @param dealInfo 交易明细
	 * @return boolean 上传是否成功
	 * */
	public boolean apply_ftp(DealInfoEntity dealInfo) {
			String filename=CustomerUtil.fileRequest(this.getSettlementPkiApiRequest(dealInfo), dealInfo);
			SftpUtil sftp=new SftpUtil();
			if ("".equals(filename) || filename==null) {
				System.out.println("========文件不存在=======");
			}else{
				try {
					//打开连接
					sftp.connect();
					//开始上传文件
					return sftp.upload(filename,"uploadPath");
				} catch (Exception e) {
					System.out.println("=====上传失败=====");
						e.printStackTrace();
					}finally{
	//					//关闭连接
					sftp.disconnect();
				}
			}
		return false;
	}
	
	/**
	 * FTP下载文件
	 * @param filename 要下载的文件
	 * @param filePath 要下载的路径
	 * @return BatchSettlementApplyResponse 应答实体类
	 * */
	public BatchSettlementApplyResponse downFile_ftp(String downloadFile,
			String filePath, DealInfoEntity dealInfo) {
		InputStream input = null;
		SftpUtil sftp=new SftpUtil();
		try {
			//打开连接
			sftp.connect();
			input = sftp.download(downloadFile, filePath);
		} catch (Exception e) {
			System.out.println("=====下载失败=====");
			e.printStackTrace();
		}
		if(input!=null){
			byte[] unsealedResultbyte = this.unsealData(CustomerUtil.xmlToSettlementPkiApiResponse(input), dealInfo);
			if(unsealedResultbyte!=null){
				BatchSettlementApplyResponse responseObject = null;
				try {
					responseObject = CustomerUtil.xmlToBatchSettlementApplyResponse(new String(unsealedResultbyte, ENCODING));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}finally {
					// 关闭连接
					sftp.disconnect();
				}
				return responseObject;
			}
		}
		return null;
	}
	
	/**
	 * 对返回的应答信息进行解密，得到请求响应结果：成功or失败
	 * @param dealInfo 请求的数据内容
	 * @return String 响应结果
	 * */
	public Object unseal(SettlementPkiApiResponse response,DealInfoEntity dealInfo) {

		if (response == null) {
			LOGGER.info("BILL99_unseal[response == null]应答信息为空");
			return null;
		} else {
			try {
				byte[] unsealedResultbyte = this.unsealData(response, dealInfo);
				if(unsealedResultbyte!=null){//fo.batch.settlement.batchidquery
					if (ACTION_BATCHIDQUERY.equalsIgnoreCase(dealInfo
							.getServiceType())) {
						BatchidQueryResponse responseObject = CustomerUtil
								.xmlToBatchidQueryResponse(new String(
										unsealedResultbyte, ENCODING));
						return responseObject;
					} else if (ACTION_APPLY.equalsIgnoreCase(dealInfo//fo.batch.settlement.pay
							.getServiceType())) {
						BatchSettlementApplyResponse responseObject = CustomerUtil
								.xmlToBatchSettlementApplyResponse(new String(
										unsealedResultbyte, ENCODING));
						return responseObject;
					} else if (ACTION_COMPLEXQUERY.equalsIgnoreCase(dealInfo//fo.batch.settlement.complexquery
							.getServiceType())) {
						ComplexQueryResponse responseObject = CustomerUtil
								.xmlToComplexQueryResponse(new String(
										unsealedResultbyte, ENCODING));
						return responseObject;
					}
				}

			} catch (UnsupportedEncodingException e) {
				LOGGER.info("BILL99_unseal[UnsupportedEncodingException]异常");
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 将提交数据进行加密处理，并且返回一个加密后的数据类sealedData
	 * @param dealInfo 请求的数据内容
	 * @return SealedData 加密后的数据
	 */
	private SealedData seal(DealInfoEntity dealInfo) {
		String originalData = "";
		if (ACTION_APPLY.equalsIgnoreCase(dealInfo.getServiceType())) {
			originalData = CustomerUtil.batchSettlementApplyRequestToXml(CustomerUtil.getBatchSettlementApplyRequest(dealInfo));
		} else if (ACTION_BATCHIDQUERY.equalsIgnoreCase(dealInfo.getServiceType())) {
			originalData = CustomerUtil.batchidQueryRequestToXml(CustomerUtil.getBatchidQueryRequest(dealInfo));
		} else if (ACTION_COMPLEXQUERY.equalsIgnoreCase(dealInfo.getServiceType())) {
			originalData = CustomerUtil.complexQueryRequestToXml(CustomerUtil.getComplexQueryRequest(dealInfo));
		}
		Validate.notNull(originalData);
		System.out.println("提交的原始报文为:\n"+originalData);
		SealedData sealedData = null;
		try {
			//先压缩
			byte[] orc = GzipUtil.gzip(originalData.getBytes(ENCODING));
			Mpf mpf = new Mpf();
			mpf.setMemberCode(dealInfo.getMemberCode());
			mpf.setFeatureCode(dealInfo.getFeatureCode());
			ICryptoService service = CryptoServiceFactory.createCryptoService();
			//再加密
			sealedData = service.seal(mpf, orc);
			byte[] nullbyte = {};
			byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte
					: sealedData.getOriginalData();
			byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte
					: sealedData.getEncryptedData();
			byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte
					: sealedData.getDigitalEnvelope();
			byte[] byteSig = sealedData.getSignedData() == null ? nullbyte
					: sealedData.getSignedData();
			
			//encode? base64encode
			byte[] byteOri2 = Base64Util.encode(byteOri);
			byte[] byteEnc2 = Base64Util.encode(byteEnc);
			byte[] byteEnv2 = Base64Util.encode(byteEnv);
			byte[] byteSig2 = Base64Util.encode(byteSig);
			
			sealedData.setOriginalData(byteOri2);
			sealedData.setSignedData(byteSig2);
			sealedData.setEncryptedData(byteEnc2);
			sealedData.setDigitalEnvelope(byteEnv2);
		} catch (CryptoException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sealedData;
	}
	
//	/**
//	 * 对商户号进行加密处理
//	 * @param memberCode 商户号
//	 * @return featureCode 加密类型
//	 */
//	public SealedData sealMembercode(String memberCode, String featureCode) {
//		SealedData sealedData = null;
//		Mpf mpf = new Mpf();
//		mpf.setMemberCode(memberCode);
//		mpf.setFeatureCode(featureCode);
//		try {
//			ICryptoService service = CryptoServiceFactory.createCryptoService();
//			// 再加密
//			sealedData = service.seal(mpf, memberCode.getBytes());
//			byte[] nullbyte = {};
//			byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte
//					: sealedData.getOriginalData();
//			byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte
//					: sealedData.getEncryptedData();
//			byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte
//					: sealedData.getDigitalEnvelope();
//			byte[] byteSig = sealedData.getSignedData() == null ? nullbyte
//					: sealedData.getSignedData();
//
//			// encode? base64encode
//			byte[] byteOri2 = Base64Util.encode(byteOri);
//			byte[] byteEnc2 = Base64Util.encode(byteEnc);
//			byte[] byteEnv2 = Base64Util.encode(byteEnv);
//			byte[] byteSig2 = Base64Util.encode(byteSig);
//
//			sealedData.setOriginalData(byteOri2);
//			sealedData.setSignedData(byteSig2);
//			sealedData.setEncryptedData(byteEnc2);
//			sealedData.setDigitalEnvelope(byteEnv2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		return sealedData;
//	}
	
	/**
	 * 对应返回的应答信息做解密处理
	 * @param response 返回的应答信息类
	 * @param dealInfo 交易数据
	 * @return byte[] 解密数据
	 * */
	public byte[] unsealData(SettlementPkiApiResponse response1,DealInfoEntity dealInfo){
		if(response1==null){
			System.out.println("得到的应答报文为空");
			return null;
		}else{
			try {
				String errorCode="";
				SealDataType responseSealedData=null;
				SettlementPkiResponseType responsebody = response1.getResponseBody();
				errorCode=responsebody.getErrorCode();
				responseSealedData = responsebody.getData();
				System.out.println("付款商户号"+responsebody.getMemberCode());
				System.out.println("应答状态"+responsebody.getStatus());
				if(!"0000".equals(errorCode) && !"1313".equals(errorCode)){
					System.out.println("申请失败,失败代码"+errorCode);
					return null;
				}
				if("0000".equals(errorCode)){
					System.out.println("申请成功");
				}else{
					System.out.println("申请失败,失败代码:"+errorCode);
				}				
				byte[] resOriData = responseSealedData.getOriginalData()
						.getBytes(ENCODING);
				byte[] resSigData = responseSealedData.getSignedData()
						.getBytes(ENCODING);
				byte[] resEnvData = responseSealedData.getDigitalEnvelope()
						.getBytes(ENCODING);
				byte[] resEncData = responseSealedData.getEncryptedData()
						.getBytes(ENCODING);
		
				// decode
				//decode? base64decode
				byte[] resDecodeOriData = Base64Util.decode(resOriData);
				byte[] resDecodeSigData = Base64Util.decode(resSigData);
				byte[] resDecodeEnvData = Base64Util.decode(resEnvData);
				byte[] resDecodeEncData = Base64Util.decode(resEncData);
				
				SealedData sealedData = new SealedData();
				sealedData.setSignedData(resDecodeSigData);
				sealedData.setOriginalData(resDecodeOriData);
				sealedData.setEncryptedData(resDecodeEncData);
				sealedData.setDigitalEnvelope(resDecodeEnvData);

				// 解密
				Mpf mpf = new Mpf();
				mpf.setMemberCode(dealInfo.getMemberCode());
				mpf.setFeatureCode(dealInfo.getFeatureCode());
				ICryptoService service = CryptoServiceFactory.createCryptoService();
				UnsealedData unsealedData=service.unseal(mpf, sealedData);
				if (unsealedData.getVerifySignResult()) {
					byte[] DecryptedData = unsealedData.getDecryptedData();
					if (DecryptedData == null){
						// 解压缩
						byte[] unsealedResultbyte =GzipUtil.unBGzip(resDecodeOriData);
						System.out.println("解密后的应答报文：\n"+new String(unsealedResultbyte, ENCODING));
						return unsealedResultbyte;
					}else {
						byte[] unsealedResultbyte = GzipUtil
								.unBGzip(DecryptedData);
						System.out.println("解密后的应答报文：\n"+new String(unsealedResultbyte, ENCODING));
						return unsealedResultbyte;
					}
				} else {
					System.out.println("验签失败");
					return null;
				}
			} catch (CryptoException e) {
				e.printStackTrace();
				return null;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
//	public  Object unsealRequest(SettlementPkiApiRequest response,DealInfoEntity dealInfo){
//		if (response == null) {
//			System.out.println("====应答信息为空=====");
//			return null;
//		} else {
//			try {
//				byte[] unsealedResultbyte = this.unsealData(response, dealInfo);
//				if(unsealedResultbyte!=null){
//					if (ACTION_BATCHIDQUERY.equalsIgnoreCase(dealInfo
//							.getServiceType())) {
//						BatchidQueryResponse responseObject = CustomerUtil
//								.xmlToBatchidQueryResponse(new String(
//										unsealedResultbyte, ENCODING));
//						return responseObject;
//					} else if (ACTION_APPLY.equalsIgnoreCase(dealInfo
//							.getServiceType())) {
//						BatchSettlementApplyResponse responseObject = CustomerUtil
//								.xmlToBatchSettlementApplyResponse(new String(
//										unsealedResultbyte, ENCODING));
//						return responseObject;
//					} else if (ACTION_COMPLEXQUERY.equalsIgnoreCase(dealInfo
//							.getServiceType())) {
//						ComplexQueryResponse responseObject = CustomerUtil
//								.xmlToComplexQueryResponse(new String(
//										unsealedResultbyte, ENCODING));
//						return responseObject;
//					}
//				}
//
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
//		return null;
//	}
	
	/**
	 * 将提交信息设置到SettlementPkiApiRequest类
	 * @param dealInfo 请求的数据内容
	 * @return SettlementPkiApiRequest 请求数据类
	 */
	private SettlementPkiApiRequest getSettlementPkiApiRequest(DealInfoEntity dealInfo){
		SettlementPkiApiRequest request = new SettlementPkiApiRequest();
		RequestHeader head = new RequestHeader();
		Version ve = new Version();
		head.setTime(DateUtil.formatDateTime("yyyyMMddHHmmss", new Date()));
		ve.setService(dealInfo.getServiceType());
		ve.setVersion(dealInfo.getVersion());
		head.setVersion(ve);
		request.setRequestHeader(head);
		SettlementPkiRequestType body = new SettlementPkiRequestType();
		SealedData sealedData=null;
		try {
			sealedData=this.seal(dealInfo);
			body.setMemberCode(dealInfo.getMemberCode());
			SealDataType sealdata = new SealDataType();
			byte[] byteOri = sealedData.getOriginalData();
			byte[] byteEnc = sealedData.getEncryptedData();
			byte[] byteEnv = sealedData.getDigitalEnvelope();
			byte[] byteSig = sealedData.getSignedData();
			sealdata.setOriginalData(new String(byteOri, ENCODING));
			sealdata.setEncryptedData(new String(byteEnc, ENCODING));
			sealdata.setDigitalEnvelope(new String(byteEnv, ENCODING));
			sealdata.setSignedData(new String(byteSig, ENCODING));
			body.setData(sealdata);
			request.setRequestBody(body);
			return request;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public void SendMessage(SealedData sealedData ,String file ,String batchNo,String vesion, String status , String memberCode) { 
//		try { 
//			HttpClient client = new HttpClient(); 
//			PostMethod authpost = new PostMethod("/servlet/applyPaymentServlet"); 
//			NameValuePair s = new NameValuePair("status",status); 
//			NameValuePair v = new NameValuePair("vesion",vesion); 
//			NameValuePair batchid = new NameValuePair("batchNo",batchNo); 
//			NameValuePair filename = new NameValuePair("filename ", file); 
//			NameValuePair mc= new NameValuePair("memberCode", memberCode); 
//			NameValuePair signedMemberCode = new NameValuePair("signedMemberCode ", new String(Base64Util.decode(sealedData.getSignedData()),"utf-8")); 
//			NameValuePair encryptedMemberCode = new NameValuePair("encryptedMemberCode ", new String(Base64Util.decode(sealedData.getEncryptedData()),"utf-8")); 
//			NameValuePair digitalEnvelope = new NameValuePair("digitalEnvelope", new String(Base64Util.decode(sealedData .getDigitalEnvelope()),"utf-8")); 
//			authpost.setRequestBody(newNameValuePair[] {s,v,batchid,mc ,filename,signedMemberCode, encryptedMemberCode, digitalEnvelope}); 
//			client.executeMethod(authpost); 
//		} catch (HttpException e) { 
//			e.printStackTrace(); 
//		} catch (IOException e) { 
//			e.printStackTrace(); 
//		} 
//	} 
}

