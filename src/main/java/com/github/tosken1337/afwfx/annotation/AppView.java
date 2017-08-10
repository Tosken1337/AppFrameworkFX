package com.github.tosken1337.afwfx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Sebastian Greif on 07.08.2017.
 * Copyright di support 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AppView {
    String viewStateName();
    boolean defaultView() default false;
}
