/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.soap;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.xml.ws.spi.WebServiceFeatureAnnotation;
import jakarta.xml.ws.WebServiceRef;
import jakarta.xml.ws.WebServiceProvider;

/**
 * This feature represents the use of MTOM with a
 * web service.
 * <p>
 * This annotation MUST only be used in conjunction the
 * {@code jakarta.jws.WebService}, {@link WebServiceProvider},
 * {@link WebServiceRef} annotations.
 * When used with the {@code jakarta.jws.WebService} annotation this
 * annotation MUST only be used on the service endpoint implementation
 * class.
 * When used with a {@code WebServiceRef} annotation, this annotation
 * MUST only be used when a proxy instance is created. The injected SEI
 * proxy, and endpoint MUST honor the values of the {@code MTOM}
 * annotation.
 * <p>
 *
 * This annotation's behaviour is defined by the corresponding feature
 * {@link MTOMFeature}.
 *
 * @since 1.6, JAX-WS 2.1
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WebServiceFeatureAnnotation(id=MTOMFeature.ID,bean=MTOMFeature.class)
public @interface MTOM {
    /**
     * Specifies if this feature is enabled or disabled.
     *
     * @return {@code true} if MTOM is supported, {@code false} otherwise
     */
    boolean enabled() default true;

    /**
     * Property for MTOM threshold value. When MTOM is enabled, binary data above this
     * size in bytes will be XOP encoded or sent as attachment. The value of this property
     * MUST always be {@literal >=} 0. Default value is 0.
     *
     * @return MTOM threshold in bytes
     */
    int threshold() default 0;
}
