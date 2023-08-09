package org.utilityclient.api;

import org.utilityclient.api.abstraction.StandaloneCompatible;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Register all implementation of Types, which implement Registrable with this.
 * @author Sam302
 * @since 3.0
 * @see Registrable
 */
@StandaloneCompatible
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Register {
}