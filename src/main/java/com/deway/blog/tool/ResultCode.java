package com.deway.blog.tool;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(200, "SUCCESS"),
    FAILURE(400, "BAD_REQUEST");

    private final String message;
    private final int code;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
