package com.deway.blog.entiry.auth;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    private Long userId;

    private String token;

    private Integer ttl;

    private LocalDateTime createTime;

    private String scope;

}