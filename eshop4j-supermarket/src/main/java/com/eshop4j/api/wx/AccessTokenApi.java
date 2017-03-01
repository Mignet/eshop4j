
package com.eshop4j.api.wx;

import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

import com.eshop4j.api.wx.cache.IAccessTokenCache;
import com.eshop4j.api.wx.common.AccessToken;
import com.eshop4j.api.wx.common.ApiConfig;
import com.eshop4j.api.wx.kit.ApiConfigKit;
import com.eshop4j.api.wx.kit.ParaMap;
import com.eshop4j.xoss.util.HttpUtils;
import com.eshop4j.xoss.util.RetryUtils;


/**
 * 认证并获取 access_token API
 * http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96access_token
 *
 * AccessToken默认存储于内存中，可实现IAccessTokenCache到存储到数据库或者redis中实现分布式可用
 *
 * 具体配置：
 * <pre>
 * ApiConfigKit.setAccessTokenCache(new LocalAccessTokenCache());
 * </pre>
 */
public class AccessTokenApi {

    // "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    // 利用 appId 与 accessToken 建立关联，支持多账户
    static IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();

    /**
     * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
     * @return AccessToken accessToken
     */
    public static AccessToken getAccessToken() {
        ApiConfig ac = ApiConfigKit.getApiConfig();
        AccessToken result = getAvailableAccessToken(ac);
        if (result != null) {
            return result;
        }

        return refreshAccessTokenIfNecessary(ac);
    }

    private static AccessToken getAvailableAccessToken(ApiConfig ac) {
        String accessTokenJson = accessTokenCache.get(ac.getAppId());
        if (StringUtils.isNotBlank(accessTokenJson)) {
            AccessToken result = new AccessToken(accessTokenJson);
            if (result != null && result.isAvailable()) {
                return result;
            }
        }
        return null;
    }

    /**
     * 直接获取 accessToken 字符串，方便使用
     * @return String accessToken
     */
    public static String getAccessTokenStr() {
        return getAccessToken().getAccessToken();
    }

    /**
     * synchronized 配合再次获取 token 并检测可用性，防止多线程重复刷新 token 值
     */
    private static synchronized AccessToken refreshAccessTokenIfNecessary(ApiConfig ac) {
        AccessToken result = getAvailableAccessToken(ac);
        if (result != null) {
            return result;
        }
        return refreshAccessToken(ac);
    }

    /**
     * 无条件强制更新 access token 值，不再对 cache 中的 token 进行判断
     */
    public static AccessToken refreshAccessToken() {
        return refreshAccessToken(ApiConfigKit.getApiConfig());
    }

    /**
     * 无条件强制更新 access token 值，不再对 cache 中的 token 进行判断
     */
    public static AccessToken refreshAccessToken(ApiConfig ac) {
        String appId = ac.getAppId();
        String appSecret = ac.getAppSecret();
        final Map<String, String> queryParas = ParaMap.create("appid", appId).put("secret", appSecret).getData();

        // 最多三次请求
        AccessToken result = RetryUtils.retryOnException(3, new Callable<AccessToken>() {

            @Override
            public AccessToken call() throws Exception {
                String json = HttpUtils.get(url, queryParas);
                return new AccessToken(json);
            }
        });

        // 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
        if (null != result) {
            accessTokenCache.set(ac.getAppId(), result.getJson());
        }
        return result;
    }

}
