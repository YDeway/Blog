package com.deway.blog.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long authorId;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    private String category;

    private int favour;

}
