package com.dx.avserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    /**
     * 过期时间100天
     */
    private static final long EXPIRE_TIME = 100 * 24 * 60 * 60 * 1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "9a1006a8-bb79-48b9-bae7-9cc4c87c5815";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWT.require(algorithm).build().verify(token);
            //这里只要不异常那么就是正确的
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取登陆用户ID
     *
     * @param token 加密的token
     * @return 提取的userId信息
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,100天后过期
     *
     * @param userId   用户id
     * @param username 用户昵称
     * @return 加密的token
     */
    public static String sign(String userId, String username) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 附带username，userId信息，生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

}
