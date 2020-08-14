package com.deway.blog.tool;

/**
 * @author Deway
 */
public interface JwtConstant {
    /**
     * 令牌
     */
    String TOKEN = "token";

    /**
     * 盐
     */
    String SALT = "salt";

    /**
     * 用户标识符
     */
    String UID = "uid";

    /**
     * HTTP 授权 header字段
     */
    String AUTHORIZATION = "Authorization";

    /**
     * jwt标识符
     */
    String JTI = "jti";

    /**
     * jwt颁发时间
     */
    String IAT = "iat";

    /**
     * jwt过期时间
     */
    String EXP = "exp";

}
