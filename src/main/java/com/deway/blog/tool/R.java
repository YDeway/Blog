package com.deway.blog.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    public static <T> R<T> response(HttpStatus httpStatus, T data) {
        return new R<>(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }

    public String toResponseBody() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
