package com.deway.blog.tool;

import lombok.Getter;

/**
 * Http响应状态
 *
 * @author Deway
 */

@Getter
public enum HttpCode {

    /**
     * SUCCESS
     */
    SUCCESS(200, "SUCCESS"),
    /**
     *  BAD_REQUEST
     */
    FAILURE(400, "BAD REQUEST"),

    /**
     * Unauthorized
     */
    UNAUTHORIZED(401, "UNAUTHORIZED"),

    /**
     * RESOURCE_CONFLICT
     */
    CONFLICT(409, "RESOURCE CONFLICT"),

    /**
     * INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR")
    ;


    private final String message;
    private final int code;

    HttpCode(int code, String message) {
        this.code = code;
        this.message = message;
    }



}
