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
 * Used to annotate service specific exception classes to customize
 * to the local and namespace name of the fault element and the name
 * of the fault bean.
 *
 *  @since 1.6, JAX-WS 2.0
**/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebFault {
    /**
     * Element's local name.
     *
     * @return loxL name
     */
    public String name() default "";

    /**
     * Element's namespace name.
     *
     * @return target namespace name
     */
    public String targetNamespace() default "";

    /**
     * Fault bean name.
     *
     * @return bean name
     */
    public String faultBean() default "";

    /**
     * wsdl:Message's name. Default name is the exception's class name.
     *
     * @return wsdl:Message's name
     * @since 1.7, JAX-WS 2.2
     */
    public String messageName() default "";

}
