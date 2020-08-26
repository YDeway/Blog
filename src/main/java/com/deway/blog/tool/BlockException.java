package com.deway.blog.tool;

/**
 *  压制受常异常
 *
 * @author Deway
 */

public abstract class BlockException {
Object
    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void  throwAsRuntimeException(Throwable e) throws T {
        throw (T)e;
    }

}
