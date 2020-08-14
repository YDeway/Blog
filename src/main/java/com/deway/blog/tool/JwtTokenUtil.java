package com.deway.blog.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

/**
 * JWT的生成，解析，验证
 *
 * @author Deway
 */
public class JwtTokenUtil {

    /**
     * jwt生成器
     *
     * @param kv 需要保存到jwt的数据
     * @param salt 加密随机salt
     * @return jwt字符串
     */
    public static String encrypt(Map<String, String> kv, String salt) {
        var token = JWT.create();
        kv.forEach(token::withClaim);
        return token.sign(Algorithm.HMAC256(salt));
    }

    public static <T> T decrypt(String token, Class<T> clazz) throws Exception {
        var decode = JWT.decode(token);
        var payload = Base64.getDecoder().decode(decode.getPayload());
        var mapper = new ObjectMapper();
        return mapper.readValue(payload, clazz);
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
