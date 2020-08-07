package com.deway.blog.entiry.auth;

import lombok.Data;

@Data
public class User {
    private Long id ;
    private String nickname;
    private String userId;
    private String password;
    private String email;
    private String salt;

}
