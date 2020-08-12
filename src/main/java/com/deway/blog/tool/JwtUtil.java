package com.deway.blog.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.deway.blog.entiry.auth.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;
import java.util.*;

public class JwtUtil {

    /**
     * jwt生成器
     *
     * @param kv 需要保存到jwt的数据
     * @param secretKey 加密密钥
     * @param expire 过期时间，单位毫秒
     * @return jwt字符串
     */
    public static String encrypt(@NonNull Map<String, String> kv, String secretKey, int expire) {

        long now = System.currentTimeMillis();

        var token = JWT.create()
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + expire));

        kv.forEach(token::withClaim);

        return token.sign(Algorithm.HMAC256(secretKey));

    }

    public static AccessToken decrypt(String secretKey, String token) throws Exception {

        var jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            var ex = new Exception(e);
            ex.initCause(e);
            throw ex;
        }

        var decode = JWT.decode(token);
        var payload = Base64.getDecoder().decode(decode.getPayload());

        var mapper = new ObjectMapper();

        return mapper.readValue(payload, AccessToken.class);
    }

}
