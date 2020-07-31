package com.deway.blog.tool;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int statusCode;
    private String message;
    private T data;

    public static <T> R<T> response(int statusCode, String message, T data) {
        return new R(statusCode, message, data);
    }

}
