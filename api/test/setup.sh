#!/bin/sh
#
# Copyright (c) 2014, 2018 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Distribution License v. 1.0, which is available at
# http://www.eclipse.org/org/documents/edl-v10.php.
#
# SPDX-License-Identifier: BSD-3-Clause
#

rm -rf endorsed
mkdir endorsed
cd ..
mvn clean package
cp target/javax.xml.ws-api*.jar test/endorsed

echo "endorsed dir: "`pwd`/test/endorsed
ls -al test/endorsed
