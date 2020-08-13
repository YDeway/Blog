package com.deway.blog.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.deway.blog.entiry.auth.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;
import java.util.*;

/**
 * JWT的生成，解析，验证
 *
 * @author Deway
 */
public class JwtUtil {

    /**
     * jwt生成器
     *
     * @param kv 需要保存到jwt的数据
     * @param salt 加密密钥
     * @param expire 过期时间，单位秒
     * @return jwt字符串
     */
    public static String encrypt(@NonNull Map<String, String> kv, String salt, long expire) {

        long now = System.currentTimeMillis();

        var token = JWT.create()
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + expire * 1000));

        kv.forEach(token::withClaim);

        return token.sign(Algorithm.HMAC256(salt));

    }

    public static AccessToken decrypt(String token) throws Exception {
        var decode = JWT.decode(token);
        var payload = Base64.getDecoder().decode(decode.getPayload());

        var mapper = new ObjectMapper();

        return mapper.readValue(payload, AccessToken.class);
    }

    /**
     *  只验证签名摘要是否通过
     *
     * @param token 待验证的token
     * @param salt 生成token的随机salt
     * @return 验证是否通过
     */
    public static boolean validate(String token, String salt) {
        var jwtVerifier = JWT.require(Algorithm.HMAC256(salt)).build();
        try {
            jwtVerifier.verify(token);
        } catch (RuntimeException e) {
            if(e instanceof TokenExpiredException) {
                return true;
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
