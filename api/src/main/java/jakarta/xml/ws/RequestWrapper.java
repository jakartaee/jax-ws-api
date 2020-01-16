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
 * Used to annotate methods in the Service Endpoint Interface with the request
 * wrapper bean to be used at runtime. The default value of the {@code localName} is
 * the {@code operationName}, as defined in {@code WebMethod} annotation and the
 * {@code targetNamespace} is the target namespace of the SEI.
 * <p> When starting from Java this annotation is used resolve
 * overloading conflicts in document literal mode. Only the {@code className}
 * is required in this case.
 *
 *  @since 1.6, JAX-WS 2.0
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestWrapper {
    /**
     * Element's local name.
     * @return local name
     */
    public String localName() default "";

    /**
     * Element's namespace name.
     * @return target namespace name
     */
    public String targetNamespace() default "";

    /**
     * Request wrapper bean name.
     * @return bean name
     */
    public String className() default "";

    /**
     * wsdl:part name for the wrapper part
     *
     * @return wsdl:part name
     * @since 1.7, JAX-WS 2.2
     */
    public String partName() default "";

}

