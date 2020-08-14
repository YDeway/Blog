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

    private String uid;

    private Long exp;

    private Long iat;

    private String jti;

    @Reserved
    private String scope;

}
