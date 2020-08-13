package com.deway.blog.tool;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    public static <T> R<T> response(HttpStatus httpStatus, T data) {
        return new R<>(httpStatus.getCode(), httpStatus.getMessage(), data);
    }

}
