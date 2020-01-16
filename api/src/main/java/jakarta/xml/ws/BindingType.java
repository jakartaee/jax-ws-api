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
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import jakarta.xml.ws.http.HTTPBinding;
import jakarta.xml.ws.soap.SOAPBinding;

/**
 *  The {@code BindingType} annotation is used to
 *  specify the binding to use for a web service
 *  endpoint implementation class.
 *  <p>
 *  This annotation may be overridden programmatically or via
 *  deployment descriptors, depending on the platform in use.
 *
 *  @since 1.6, JAX-WS 2.0
 *
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindingType {
     /**
      * A binding identifier (a URI). If not specified, the default is the SOAP 1.1 / HTTP binding.<br>
      * See the {@link SOAPBinding} and {@link HTTPBinding}
      * for the definition of the standard binding identifiers.
      *
      * @return A binding identifier (a URI)
      * @see jakarta.xml.ws.Binding
      * @see jakarta.xml.ws.soap.SOAPBinding#SOAP11HTTP_BINDING
      * @see jakarta.xml.ws.soap.SOAPBinding#SOAP12HTTP_BINDING
      * @see jakarta.xml.ws.http.HTTPBinding#HTTP_BINDING
      */
     String value() default "" ;
}
