package com.deway.blog.entiry;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * back_ground_image
 *
 * @author Deway
 */
@Data
public class BackGroundImage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long filePath;

    private Date createTime;

}