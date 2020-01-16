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
 * Used to annotate a Provider implementation class.
 *
 * @since 1.6, JAX-WS 2.0
 * @see jakarta.xml.ws.Provider
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServiceProvider {
    /**
     * Location of the WSDL description for the service.
     *
     * @return location of the WSDL description
     */
    String wsdlLocation() default "";

    /**
     * Service name.
     *
     * @return service name
     */
    String serviceName() default "";

    /**
     * Target namespace for the service
     *
     * @return target namespace
     */
    String targetNamespace() default "";

    /**
     * Port name.
     *
     * @return port name
     */
    String portName() default "";
}
