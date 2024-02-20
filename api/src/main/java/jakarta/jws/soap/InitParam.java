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

@Deprecated public @interface InitParam {

    /**
     * Name of the initialization parameter.
     *
     * @return the name of the initialization parameter
     */
    String name();

    /**
     * Value of the initialization parameter.
     *
     * @return the value of the initialization parameter
     */
    String value();
}

