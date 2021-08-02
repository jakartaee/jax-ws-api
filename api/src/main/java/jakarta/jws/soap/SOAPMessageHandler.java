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

@Deprecated public @interface SOAPMessageHandler {

    /**
     * Name of the handler.Defaults to the name of the handler class.
     *
     * @return the name of the handler
     */
    String name() default "";

    /**
     * Name of the handler class.
     *
     * @return the name of the handler class
     */
    String className();

    /**
     * Array of name/value pairs that should be passed to the handler during initialization.
     *
     * @return the array of name/value pairs that should be passed to the handler during initialization
     */
    InitParam[] initParams() default {};

    /**
     * List of SOAP roles/actors implemented by the handler.
     *
     * @return the list of SOAP roles/actors
     */
    String[] roles() default {};

    /**
     * List of SOAP headers processed by the handler.
     * Each element in this array contains a QName which defines the header element processed by the handler.
     * The QNames are specified using the string notation described in the documentation
     * for javax.xml.namespace.QName.valueOf(String qNameAsString).
     *
     * @return the list of SOAP headers processed by the handler
     */
    String[] headers() default {};
};


