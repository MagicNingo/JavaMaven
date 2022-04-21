package com.bus.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface AnimalColor {
    /**
     * 颜色枚举
     */
    enum Color{黑色, 白色, 灰色, 杂色}
    Color animalColor() default Color.灰色;
}
