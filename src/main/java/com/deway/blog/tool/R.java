package com.deway.blog.tool;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import com.deway.blog.tool.HttpCode;

@Data
@AllArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    public static <T> R<T> response(HttpCode httpCode,T data) {
        return new R<>(httpCode.getCode(), httpCode.getMessage(), data);
    }

}
