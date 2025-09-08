package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Used to disable jacoco coverage since jacoco ignores everything which
 * annotation contains sequence "Generated"
 */
@Retention(RetentionPolicy.CLASS)
public @interface Generated {
}
