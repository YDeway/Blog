package com.deway.blog.entiry.auth;

import java.io.Serializable;
import com.deway.blog.tool.Reserved;
import lombok.Data;

/**
 * access-token
 *
 * @author Deway
 */
@Data
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long uid;

    private Long exp;

    private Long iat;

    private String jti;

    @Reserved
    private String scope;

    public static class Constant {
        public static final String TOKEN = "token";
        public static final String SALT = "salt";
        public static final String UID = "uid";
    }

}
