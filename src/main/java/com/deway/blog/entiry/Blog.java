package com.deway.blog.entiry;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private String category;
    private int favour;

}
