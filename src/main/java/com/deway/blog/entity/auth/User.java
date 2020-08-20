package com.deway.blog.entity.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id ;

    private String nickname;

    private String userId;

    private String password;

    private String email;

    private String salt;

}
