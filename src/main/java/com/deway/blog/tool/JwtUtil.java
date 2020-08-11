package com.deway.blog.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.deway.blog.entiry.auth.AccessToken;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

//    @Value("")
    private int EXPIRE_INTERVAL = 0;

    public static String encrypt(String secretKey, AccessToken accessToken) {

        long now = System.currentTimeMillis();

        var token = JWT.create()
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + 30 * 60 * 1000))
                .withJWTId(accessToken.getUserId().toString())
                .withClaim("k", "v")
                .withClaim("ss", new HashMap<String, AccessToken>() {
                    {
                        this.put("sda", accessToken);
                    }
                })
                .sign(Algorithm.HMAC256(secretKey));

        return token;
    }

    public static void decrypt(String secretKey, String token) {
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
//        jwtVerifier.verify(token);
        var decode = JWT.decode(token);

        byte[] payload = Base64.getDecoder().decode(decode.getPayload());
        byte[] header = Base64.getDecoder().decode(decode.getHeader());


        System.out.println(decode.getSignature());
        System.out.println(new String(payload));
        System.out.println(new String(header));


    }
}
