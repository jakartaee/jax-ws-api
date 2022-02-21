/*
 * Copyright (c) 2018, 2022 Oracle and/or its affiliates. All rights reserved.
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
 * Customizes the mapping of an individual parameter to a Web Service message part and XML element.
 *
 * @since 1.6
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER})
public @interface WebParam {

    /**
     * The direction in which the parameter flows
     */
    enum Mode {
        IN,
        OUT,
        INOUT
    };

    /**
     * Name of the parameter.
     * <p>
     * If the operation is rpc style and @WebParam.partName has not been specified, this is name of the wsdl:part
     * representing the parameter.
     * <br>
     * If the operation is document style or the parameter maps to a header, this is the local name of the XML element
     * representing the parameter.
     * <p>
     * A name MUST be specified if the operation is document style, the parameter style is BARE, and the mode is OUT
     * or INOUT.
     *
     * @return the name of the parameter
     * @specdefault
     *   If the operation is document style and the parameter style is BARE, {@code @WebMethod.operationName}.<br>
     *   Otherwise, argN, where N represents the index of the parameter in the method signature (starting at arg0).
     */
    String name() default "";

    /**
     * The name of the {@code wsdl:part} representing this parameter.
     * <p>
     * This is only used if the operation is rpc style or if the operation is document style and the parameter style
     * is BARE.
     *
     * @return the name of the {@code wsdl:part}
     * @specdefault {@code @WebParam.name}
     *
     * @since 2.0
     */
    String partName() default "";

    /**
     * The XML namespace for the parameter.
     * <p>
     * Only used if the operation is document style or the paramater maps to a header.
     * If the target namespace is set to "", this represents the empty namespace.
     *
     * @return the XML namespace for the parameter
     * @specdefault
     *   If the operation is document style, the parameter style is WRAPPED, and the parameter does not map to a
     *   header, the empty namespace.<br>
     *   Otherwise, the targetNamespace for the Web Service.
     */
    String targetNamespace() default "";

    /**
     * The direction in which the parameter is flowing (One of IN, OUT, or INOUT).
     * <p>
     * The OUT and INOUT modes may only be specified for parameter types that conform to the definition of Holder types
     * (Jakarta XML Web Services Specification [5], section 2.3.3).  Parameters that are Holder Types MUST be OUT or INOUT.
     *
     * @return the parameter flowing direction
     * @specdefault
     *   INOUT if a Holder type.<br>
     *   IN if not a Holder type.
     */
    Mode mode() default Mode.IN;

    /**
     * If true, the parameter is pulled from a message header rather then the message body.
     *
     * @return value of {@code true} to pull the parameter from a message header
     *         rather then the message body or {@code false} otherwise
     */
    boolean header() default false;
};
