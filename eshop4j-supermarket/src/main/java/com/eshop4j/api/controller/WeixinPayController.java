package com.eshop4j.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.wx.PaymentApi;
import com.eshop4j.api.wx.PaymentApi.TradeType;
import com.eshop4j.api.wx.kit.IpKit;
import com.eshop4j.api.wx.kit.PaymentKit;
import com.eshop4j.xoss.util.RequestUtil;
import com.google.common.base.Charsets;

@RequestMapping("api/pay")
public class WeixinPayController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinPayController.class);
	
    //商户相关资料
    private static String appid = "";
    private static String partner = "";
    private static String paternerKey = "";
    private static String notify_url = "http://www.xxx.com/pay/pay_notify";

    /**
     * 统一下单接口
     */
    @RequestMapping("unified_order")
    @ResponseBody
    public Object unifiedorder(HttpServletRequest request) {
        // openId，采用 网页授权获取 access_token API：SnsAccessTokenApi获取
        String openId = "";

        // 统一下单文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1

        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);
        params.put("mch_id", partner);
        params.put("body", "统一下单接口");
        params.put("out_trade_no", "977773682111");
        params.put("total_fee", "1");

        String ip = IpKit.getRealIp(request);
        if (StringUtils.isEmpty(ip)) {
            ip = "127.0.0.1";
        }

        params.put("spbill_create_ip", ip);
        params.put("trade_type", TradeType.JSAPI.name());
        params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
        params.put("notify_url", notify_url);
        params.put("openid", openId);

        String sign = PaymentKit.createSign(params, paternerKey);
        
        params.put("sign", sign);
        
        String xmlResult = PaymentApi.pushOrder(params);

        Map<String, String> result = PaymentKit.xmlToMap(xmlResult);

        String return_code = result.get("return_code");
        String return_msg = result.get("return_msg");
        if (StringUtils.isEmpty(return_code) || !"SUCCESS".equals(return_code)) {
        
            return return_msg;
        }
     
        // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
        String prepay_id = result.get("prepay_id");
        Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("appId", appid);
        packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        packageParams.put("nonceStr", System.currentTimeMillis() + "");
        packageParams.put("package", "prepay_id=" + prepay_id);
        packageParams.put("signType", "MD5");
        String packageSign = PaymentKit.createSign(packageParams, paternerKey);
        packageParams.put("paySign", packageSign);
        
        return packageParams;
    }

    /**
     * 支付成功通知
     */
    @RequestMapping("pay_notify")
    public void pay_notify(HttpServletRequest request,HttpServletResponse response) {
        // 支付结果通用通知文档: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
        String xmlMsg = RequestUtil.readData(request);
        System.out.println("支付通知="+xmlMsg);
        Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);

        String result_code  = params.get("result_code");
        // 总金额
        String totalFee     = params.get("total_fee");
        // 商户订单号
        String orderId      = params.get("out_trade_no");
        // 微信支付订单号
        String transId      = params.get("transaction_id");
        // 支付完成时间，格式为yyyyMMddHHmmss
        String timeEnd      = params.get("time_end");

        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 避免已经成功、关闭、退款的订单被再次更新
        String result = "";
        if(PaymentKit.verifyNotify(params, paternerKey)){
            if (("SUCCESS").equals(result_code)) {
                //更新订单信息
                System.out.println("更新订单信息");

                Map<String, String> xml = new HashMap<String, String>();
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                result = PaymentKit.toXml(xml);
            }
        }
        PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setContentType("text/plain");
			response.setCharacterEncoding(Charsets.UTF_8.name());	
			writer = response.getWriter();
			writer.write(result);
			writer.flush();
		} catch (IOException e) {
			LOGGER.error("pay_notify Exception", e);
		}
		finally {
			IOUtils.closeQuietly(writer);
		}
    }
}
