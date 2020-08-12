package com.deway.blog.tool;

import org.springframework.core.annotation.AliasFor;
import java.lang.annotation.*;

/**
 *  标识保留代码,将来可能会用到
 *
 * @author Deway
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Reserved {
    /**
     *  保留使用的原因
     *
     * @return reserved reason
     */
    @AliasFor("value")
    String reason() default "";

    @AliasFor("reason")
    String value() default "";

}
