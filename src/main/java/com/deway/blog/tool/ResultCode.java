package com.deway.blog.tool;

import lombok.Getter;

/**
 * Http响应状态
 *
 * @author Deway
 */

@Getter
public enum ResultCode {
    /**
     * SUCCESS
     */
    SUCCESS(200, "SUCCESS"),
    /**
     *  BAD_REQUEST
     */
    FAILURE(400, "BAD_REQUEST");

    private final String message;
    private final int code;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
