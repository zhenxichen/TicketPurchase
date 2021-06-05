package com.ruoyi.common.utils.http;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;

/**
 * 对Token进行操作的工具类
 */
public class TokenUtils {
    /**
     * 从Authorization字段当中获取token
     * @param auth Authorization字段
     * @return 获取到的token
     */
    public static String getTokenFromAuthorization(String auth) {
        String token;
        if (StringUtils.isNotEmpty(auth) && auth.startsWith(Constants.TOKEN_PREFIX)) {
            token = auth.replace(Constants.TOKEN_PREFIX, "");
            return token;
        }
        return null;
    }
}
