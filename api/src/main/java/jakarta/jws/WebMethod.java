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

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Customizes a method that is exposed as a Web Service operation.
 * The associated method must be public and its parameters return value,
 * and exceptions must follow the rules defined in Jakarta XML Web Services Specification, section 5.
 *
 *  The method is not required to throw java.rmi.RemoteException.
 *
 * @since 1.6
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface WebMethod {

    /**
     * Name of the {@code wsdl:operation} matching this method.
     *
     * @return the name of the {@code wsdl:operation}
     * @specdefault Name of the Java method.
     */
    String operationName() default "";

    /**
     * The action for this operation.
     * <p>
     * For SOAP bindings, this determines the value of the soap action.
     *
     * @return the action
     */
    String action() default "";

    /**
     * Marks a method to NOT be exposed as a web method.
     * <p>
     * Used to stop an inherited method from being exposed as part of this web service.
     * If this element is specified, other elements MUST NOT be specified for the @WebMethod.
     * <p>
     * <i>This member-value is not allowed on endpoint interfaces.</i>
     *
     * @return value of {@code true} to mark the method to not be exposed or {@code false} otherwise
     * @since 2.0
     */
    boolean exclude() default false;
};
