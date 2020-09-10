/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import jakarta.xml.ws.WebServiceFeature;
import jakarta.xml.ws.WebServiceRef;
import jakarta.xml.ws.RespectBinding;
import jakarta.xml.ws.soap.Addressing;
import jakarta.xml.ws.soap.MTOM;

/**
 * Annotation used to identify other annotations
 * as a {@code WebServiceFeature}.
 * <p>
 * Each {@code WebServiceFeature} annotation annotated with
 * this annotation MUST contain an
 * {@code enabled} property of type
 * {@code boolean} with a default value of {@code true}.
 * <p>
 * Jakarta XML Web Services defines the following
 * {@code WebServiceFeature} annotations ({@code Addressing},
 * {@code MTOM}, {@code RespectBinding}), however, an implementation
 * may define vendors specific annotations for other features.
 * <p>
 * Annotations annotated with {@code WebServiceFeatureAnnotation} MUST
 * have the same @Target of {@link WebServiceRef} annotation, so that the resulting
 * feature annotation can be used in conjunction with the {@link WebServiceRef}
 * annotation if necessary.
 * <p>
 * If a Jakarta XML Web Services implementation encounters an annotation annotated
 * with the {@code WebServiceFeatureAnnotation} that it does not
 * recognize/support an error MUST be given.
 *
 * @see Addressing
 * @see MTOM
 * @see RespectBinding
 *
 * @since 1.6, JAX-WS 2.1
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServiceFeatureAnnotation {
    /**
     * Unique identifier for the WebServiceFeature.  This
     * identifier MUST be unique across all implementations
     * of Jakarta XML Web Services.
     * @return unique identifier for the WebServiceFeature
     */
    String id();

    /**
     * The {@code WebServiceFeature} bean that is associated
     * with the {@code WebServiceFeature} annotation
     * @return the {@code WebServiceFeature} bean
     */
    Class<? extends WebServiceFeature> bean();
}
