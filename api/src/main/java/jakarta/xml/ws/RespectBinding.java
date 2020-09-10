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
import jakarta.xml.ws.spi.WebServiceFeatureAnnotation;


/**
 * This feature clarifies the use of the {@code wsdl:binding}
 * in a Jakarta XML Web Services runtime.
 * <p>
 * This annotation MUST only be used in conjunction the
 * {@code jakarta.jws.WebService}, {@link WebServiceProvider},
 * {@link WebServiceRef} annotations.
 * When used with the {@code jakarta.jws.WebService} annotation this
 * annotation MUST only be used on the service endpoint implementation
 * class.
 * When used with a {@code WebServiceRef} annotation, this annotation
 * MUST only be used when a proxy instance is created. The injected SEI
 * proxy, and endpoint MUST honor the values of the {@code RespectBinding}
 * annotation.
 * <p>
 *
 * This annotation's behaviour is defined by the corresponding feature
 * {@link RespectBindingFeature}.
 *
 * @see RespectBindingFeature
 *
 * @since 1.6, JAX-WS 2.1
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WebServiceFeatureAnnotation(id=RespectBindingFeature.ID,bean=RespectBindingFeature.class)
public @interface RespectBinding {
    /**
     * Specifies if this feature is enabled or disabled.
     *
     * @return {@code true} if this feature is enabled, {@code false} otherwise
     */
    boolean enabled() default true;
}
