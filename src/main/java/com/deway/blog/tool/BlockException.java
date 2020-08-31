package com.deway.blog.tool;

/**
 *  压制受检查异常
 *
 * @author Deway
 */

public final class BlockException {

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void  throwAs(Throwable e) throws T {
        throw (T)e;
    }

    private BlockException() {
    }

}

