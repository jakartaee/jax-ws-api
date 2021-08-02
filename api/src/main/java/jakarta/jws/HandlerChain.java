/*
 * Copyright (c) 2018, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.jws;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Associates the Web Service with an externally defined handler chain.  This annotation is typically used in scenarios
 * where embedding the handler configuration directly in the Java source is not appropriate; for example, where the
 * handler configuration needs to be shared across multiple Web Services, or where the handler chain consists of
 * handlers for multiple transports.
 *
 * It is an error to combine this annotation with the @SOAPMessageHandlers annotation.
 *
 * @since 1.6
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface HandlerChain {

    /**
     * Location of the handler chain file.
     * <p>
     * The location supports 2 formats:
     * <ol>
     * <li>An absolute java.net.URL in externalForm (ex: http://myhandlers.foo.com/handlerfile1.xml).
     * <li>A relative path from the source file or class file (ex: bar/handlerfile1.xml).
     * </ol>
     *
     * @return the handler chain
     */
    String file();

    /**
     * Name of the handler chain in the configuration file
     *
     * @return the handler chain
     * @deprecated As of JSR-181 2.0 with no replacement.
     */
    @Deprecated String name() default "";
};
