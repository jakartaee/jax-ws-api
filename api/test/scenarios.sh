#
# Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Distribution License v. 1.0, which is available at
# http://www.eclipse.org/org/documents/edl-v10.php.
#
# SPDX-License-Identifier: BSD-3-Clause
#

#prepare props svc

# arg1 ... ${java.home}/conf/jaxws.properties content (jakarta.xml.ws.spi.Provider=fq class name)
# META-INF/services/jakarta.xml.ws.spi.Provider content (fq class name)
# prepare arg1 arg2

# arg1 ... expected provider class / -
# arg2 ... expected exception / -
# arg3 ... system property /
# test arg1 arg2 arg3

export DEFAULT=com.sun.xml.internal.ws.spi.ProviderImpl

scenario 1
prepare jakarta.xml.ws.spi.Provider=jaxws.factory.Valid -
test jaxws.factory.Valid -

# JAXB fails here
scenario 2
prepare something=AnotherThing -
test $DEFAULT -

# JAXB fails here
scenario 3
prepare jakarta.xml.ws.spi.Provider=non.existing.FactoryClass -
test $DEFAULT -

scenario 4
prepare jakarta.xml.ws.spi.Provider=jaxws.factory.Invalid -
test - jakarta.xml.ws.WebServiceException

scenario 5
prepare - -
test jaxws.factory.Valid - -Djakarta.xml.ws.spi.Provider=jaxws.factory.Valid

scenario 6
prepare - -
test - jakarta.xml.ws.WebServiceException -Djakarta.xml.ws.spi.Provider=jaxws.factory.NonExisting

scenario 7
prepare - -
test - jakarta.xml.ws.WebServiceException -Djakarta.xml.ws.spi.Provider=jaxws.factory.Invalid

scenario 8
prepare - 'jaxws.factory.Valid\n'
test jaxws.factory.Valid -

scenario 9
prepare - jaxws.factory.Valid
test jaxws.factory.Valid -

# JAXB throws JAXBException here ...  java.util.ServiceConfigurationError
scenario 10
prepare - jaxws.factory.NonExisting
test - jakarta.xml.ws.WebServiceException

# JAXB throws JAXBException here ...  java.util.ServiceConfigurationError
scenario 11
prepare - jaxws.factory.Invalid
test - jakarta.xml.ws.WebServiceException

scenario 12
prepare - -
test $DEFAULT -

scenario 13
prepare jakarta.xml.ws.spi.Provider=jaxws.factory.Valid jaxws.factory.Valid2
test jaxws.factory.Valid2 - -Djakarta.xml.ws.spi.Provider=jaxws.factory.Valid3

scenario 14
prepare jakarta.xml.ws.spi.Provider=jaxws.factory.Valid -
test jaxws.factory.Valid - -Djakarta.xml.ws.spi.Provider=jaxws.factory.Valid

scenario 15
prepare - -
test jaxws.factory.Valid - -Djakarta.xml.ws.spi.Provider=jaxws.factory.Valid
