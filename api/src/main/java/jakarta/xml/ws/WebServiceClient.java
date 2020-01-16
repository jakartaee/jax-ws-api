/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 *  Used to annotate a generated service interface.
 *
 *  <p>The information specified in this annotation is sufficient
 *  to uniquely identify a {@code wsdl:service}
 *  element inside a WSDL document. This {@code wsdl:service}
 *  element represents the Web service for which the generated
 *  service interface provides a client view.
 *
 *  @since 1.6, JAX-WS 2.0
**/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServiceClient {
    /**
     * The local name of the Web service.
     *
     * @return local name
     */
    String name() default "";

    /**
     * The namespace for the Web service.
     *
     * @return target namespace name
     */
    String targetNamespace() default "";

    /**
     * The location of the WSDL document for the service (a URL).
     *
     * @return location of the WSDL document (a URL)
     */
    String wsdlLocation() default "";
}
