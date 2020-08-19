package com.deway.blog.entiry;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * file
 * @author Deway
 */
@Data
public class FileRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String path;

    private Date createTime;

}