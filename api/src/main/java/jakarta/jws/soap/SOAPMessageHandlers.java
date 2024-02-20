/*
 * Copyright (c) 2018, 2024 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.jws.soap;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Specifies a list of SOAP protocol handlers that run before and after business methods on the Web Service.  These
 * handlers are called in response to SOAP messages targeting the service.
 * <p>
 * The @SOAPMessageHandlers annotation is an array of SOAPMessageHandler types.  The handlers are run in the order in
 * which they appear in the annotation, starting with the first handler in the array.
 *
 * @deprecated  As of JSR-181 2.0 with no replacement.
 *
 * @since 1.6
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Deprecated public @interface SOAPMessageHandlers {

    SOAPMessageHandler[] value();
}
