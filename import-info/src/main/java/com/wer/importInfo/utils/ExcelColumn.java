package com.wer.importInfo.utils;

import java.lang.annotation.*;

/**
 * 实体类需要的bean
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {

    String value() default "";

    int col() default 0;
}
