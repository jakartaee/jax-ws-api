/*
 * Copyright (c) 2018, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.jws.soap;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Specifies the mapping of the Web Service onto the SOAP message protocol.
 *
 * @since 1.6, Jakarta XML Web Services 2.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface SOAPBinding {

    /**
     * The SOAP binding style
     */
    public enum Style {
      DOCUMENT,
      RPC
    };

    /**
     * The SOAP binding use
     */
    public enum Use {
      LITERAL,
      ENCODED
    };

    /**
     * The style of mapping parameters onto SOAP messages
     */
    public enum ParameterStyle {
      BARE,
      WRAPPED
    }

    /**
     * Defines the encoding style for messages send to and from the Web Service.
     *
     * @return the encoding style for messages
     */
    Style style() default Style.DOCUMENT;

    /**
     * Defines the formatting style for messages sent to and from the Web Service.
     *
     * @return the formatting style for messages
     */
    Use use() default Use.LITERAL;

    /**
     * Determines whether method parameters represent the entire message body, or whether the parameters are elements
     * wrapped inside a top-level element named after the operation.
     *
     * @return parameter style as {@code BARE} or {@code WRAPPED}
     */
    ParameterStyle parameterStyle() default ParameterStyle.WRAPPED;
}
