package com.deway.blog.tool;

import lombok.Getter;

/**
 * Http响应状态
 *
 * @author Deway
 */

@Getter
@Deprecated(since = "no version.  Spring 自带的有")
public enum HttpStatus {

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
     * 404 Not Found
     */
    NOT_FOUND(404, "NOT_FOUND"),

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

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String toString() {
        return code + "  " + message;
    }
}
