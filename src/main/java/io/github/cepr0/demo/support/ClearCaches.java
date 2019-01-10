package io.github.cepr0.demo.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClearCaches {
	/**
	 * String pattern array of cache names that will be evicted.
	 */
	String[] value() default {};
}
