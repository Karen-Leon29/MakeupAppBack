package com.dorysoft.mackeupApp.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // Aplica tanto a métodos como a clases
@Retention(RetentionPolicy.RUNTIME) // Se mantiene en tiempo de ejecución
public @interface Public {
}
