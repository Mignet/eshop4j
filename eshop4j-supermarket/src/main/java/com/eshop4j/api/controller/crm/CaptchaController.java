package com.eshop4j.api.controller.crm;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.eshop4j.core.constant.TokenConstant;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.api.BaseController;
import com.eshop4j.xoss.helper.CommonHelper;

import redis.clients.jedis.JedisCluster;


@Controller
@RequestMapping(value = "/image")
public class CaptchaController extends BaseController{
	
	private static Map<String, String> verifyCodeMap = new HashMap<String, String>();
	
	@Resource
	private Producer captchaProducer;
	
	@Resource
	private CommonHelper commonHelper;
	
	@Resource
	private JedisCluster jedisCluster;

	@RequestMapping("captcha")
	public ModelAndView getKaptchaImage(String mobile,AppRequestHead head,HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = captchaProducer.createText();
		String key = TokenConstant.TOKEN_TYPE_PIC_VCODE+mobile;
		if(jedisCluster != null){
			jedisCluster.setex(key, TokenConstant.PIC_VCODE_TIMEOUT, capText);
		} else {
			verifyCodeMap.put(key, capText);
		}
		
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpeg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
	
	public boolean checkVCode(String mobile, String verifyCode){
		if(StringUtils.isBlank(mobile) || StringUtils.isBlank(verifyCode)) {
			return false;
		}
		String key = TokenConstant.TOKEN_TYPE_PIC_VCODE + mobile;
		String capText = null;
		if(jedisCluster != null){
			capText = jedisCluster.get(key);
		} else {
			capText = verifyCodeMap.get(key);
		}
		
		if(capText != null && capText.toLowerCase().equals(verifyCode.toLowerCase())){
			return true;
		} else {
			return false;
		}
	}

	public void clearCache(String mobile) {
		String key = TokenConstant.TOKEN_TYPE_PIC_VCODE + mobile;
		if(jedisCluster != null){
			jedisCluster.del(key);
		}
		verifyCodeMap.remove(key);
	}
	
	
}
