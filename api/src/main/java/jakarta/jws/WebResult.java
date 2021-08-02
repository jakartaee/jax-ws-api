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
 * Customizes the mapping of the return value to a WSDL part and XML element.
 *
 * @since 1.6
 */
@Retention(value = RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD})
    public @interface WebResult
{

    /**
     * Name of return value.
     * <p>
     * If the operation is rpc style and @WebResult.partName has not been specified, this is the name of the wsdl:part
     * representing the return value.
     * <br>
     * If the operation is document style or the return value maps to a header, this is the local name of the XML
     * element representing the return value.
     *
     * @return the name of return value
     * @specdefault
     *   If the operation is document style and the parameter style is BARE, {@code @WebParam.operationName}+"Response".<br>
     *   Otherwise, "return."
     */
    String name() default "";

    /**
     * The name of the wsdl:part representing this return value.
     * <p>
     * This is only used if the operation is rpc style, or if the operation is document style and the parameter style
     * is BARE.
     *
     * @return the name of the {@code wsdl:part}
     * @specdefault {@code @WebResult.name}
     *
     * @since 2.0
     */
    String partName() default "";

    /**
     * The XML namespace for the return value.
     * <p>
     * Only used if the operation is document style or the return value maps to a header.
     * If the target namespace is set to ��, this represents the empty namespace.
     *
     * @return the XML namespace
     * @specdefault
     *   If the operation is document style, the parameter style is WRAPPED, and the return value does not map to a
     *   header, the empty namespace.<br>
     *   Otherwise, the targetNamespace for the Web Service.
     */
    String targetNamespace() default "";

    /**
     * If true, the result is pulled from a message header rather then the message body.
     *
     * @return value of {@code true} to pull the parameter from a message header
     *         rather then the message body or {@code false} otherwise
     * @since 2.0
     */
    boolean header() default false;
}
