package com.deway.blog.entiry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("blog")
public class Blog {

    private Long id;
    private Long author;
    private String content;
    private Date createTime;
    private Date updateTime;
    private String cateGory;
    private int favour;

}
