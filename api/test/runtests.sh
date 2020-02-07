#!/bin/sh
#
# Copyright (c) 2015, 2020 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Distribution License v. 1.0, which is available at
# http://www.eclipse.org/org/documents/edl-v10.php.
#
# SPDX-License-Identifier: BSD-3-Clause
#


#
# The latest SPEC's version javadoc:
# http://docs.oracle.com/javase/8/docs/api/javax/xml/ws/spi/Provider.html
#
# in short:
#
# 1) ServiceLoader: /META-INF/services/jakarta.xml.ws.spi.Provider
# 2) $java.home/lib/jaxws.properties - keyd by jakarta.xml.ws.spi.Provider
# 3) SystemProperty: jakarta.xml.ws.spi.Provider
#  * OSGi (non-SPEC)
# 4) default provider


#    testcases:
#
#        1) property file: ok
#        2) property file: missing property
#        3) property file: non-existing class property
#        4) property file: invalid class property
#
#        # system property: jakarta.xml.bind.context.factory
#        5) system property: ok
#        6) system property: ClassNotFound
#        7) system property: incorrect class

#        # ServiceLoader:
#        8) ok
#        9) ok - no new line
#        10) ClassNotFound
#        11) incorrect class
#
#        # default
#        12) no setup
#
#        # priorities:
#        13) prop.file sys.property ServiceLoader > service loader
#        14) prop.file sys.property  > prop.file
#        15) - sys. property - > sys.property
#

export JDK_CONF_DIR=jre/lib
#export JDK_CONF_DIR=conf

export ENDORSED_DIR="`pwd`/endorsed"
export ENDORSED="-Djava.endorsed.dirs=$ENDORSED_DIR"
#export ENDORSED=

echo "JAVA_HOME: " $JAVA_HOME
echo "endorsed dirs: " $ENDORSED
ls -al $ENDORSED_DIR
javac -version

export D=
if [ "$1" = "debug" ]; then
    export D=$DEBUG
fi;

scenario() {
    echo ""
    echo "================================================"
    echo " Scenario " $1
    echo "================================================"
}

compile() {
#    javac -cp . -Djava.endorsed.dirs=../endorsed -XDignore.symbol.file  $1
    javac -cp . -XDignore.symbol.file  $1
}

#
# Each test call tests 5 different cases:
#  1) JAXBContext.newInstance( String path )
#  2) JAXBContext.newInstance( Class ... classes )
#  3) JAXBContext.newInstance( Class[] classes, Map<String,Object> properties )
#  4) JAXBContext.newInstance( String path ) + setting TCCL
#  5) JAXBContext.newInstance( Class[] classes, Map<String,Object> properties ) + setting TCCL
#

## test [expected-impl] [expected-exception-type] [JVM_OPTS]
## jaxb.test.JAXBTestContextPath [expected-impl] [expected-exception-type] [useTCCL]
test() {
    JVM_OPTS=$3

#    echo - Test ---
    echo java $JVM_OPTS $D $ENDORSED jaxws.test.Test $1 $2
    java $JVM_OPTS $D $ENDORSED jaxws.test.Test $1 $2

#    prepareCtxClassloader
#
#    echo - JAXBTestContextPath+ClassLoader ---
#    java $JVM_OPTS $D $ENDORSED -cp ../ctx-classloader-test jaxb.test.JAXBTestContextPath $1 $2 WithClassLoader
#
#    # parametrized classloader not applicable for method with classes:
#    echo - JAXBTestClasses2+ClassLoader -
#    java $JVM_OPTS $D $ENDORSED -cp ../ctx-classloader-test jaxb.test.JAXBTestClasses2 $1 $2 WithClassLoader
}

clean() {
    rm -rf META-INF
}

#
# Sets up:
#  1) ${java.home}/conf/jaxws.properties file
#  2) META-INF/services/jakarta.xml.ws.spi.Provider file
prepare() {
    PROPS=$1
    SVC=$2

    echo ""
    echo "- prepare/clean -"
    clean

    if [ "$SVC" != "-" ]; then
        mkdir -p META-INF/services
        echo "$SVC" > META-INF/services/jakarta.xml.ws.spi.Provider
    else
        rm -rf META-INF
    fi

    echo META-INF: $SVC
    if [ -f META-INF/services/jakarta.xml.ws.spi.Provider ]; then
      echo "   "`ls -al META-INF/services/jakarta.xml.ws.spi.Provider`
      echo "   "`cat META-INF/services/jakarta.xml.ws.spi.Provider`
      echo ""
    fi

    if [ "$PROPS" != "-" ]; then
        echo $PROPS > $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties
        else rm -rf $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties
    fi

    echo properties: $PROPS
    if [ -f $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties ]; then
      echo "   "`ls -al $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties`
      echo "   "`cat $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties`
      echo ""
    fi

    #listDirectory
}

function listDirectory() {
    echo == prepared done ================================
    echo `pwd`:
    find ..
    echo =================================================
}

function compileAll() {
    echo "- compilation -"
    find . -name '*.class' -delete

    # current version of API
    compile 'jaxws/factory/*.java'
    compile 'jaxws/test/*.java'
}

#TCCL_DIR=../ctx-classloader-test

function cleanAll() {
    scenario cleanup
    prepare - -
    rm -rf ../classes
    find . -name '*.class' -delete
#    rm -rf $TCCL_DIR
}

#function prepareCtxClassloader() {
#
#    # preparation for testing TCCL method
#    # copying compiled user classes + property files
#    rm -rf $TCCL_DIR
#    mkdir -p $TCCL_DIR/jaxb/test/usr
#
#    cp jaxb/test/*.class $TCCL_DIR/jaxb/test > /dev/null 2>&1
#
#    cp jaxb/test/usr/*.class $TCCL_DIR/jaxb/test/usr > /dev/null 2>&1
#    cp jaxb/test/usr/*.properties ../ctx-classloader-test/jaxb/test/usr > /dev/null 2>&1
#
#    # debug: list firectory content
#    #find ../
#}


export DEFAULT=com.sun.xml.ws.spi.ProviderImpl

cd src

### old version of API
#FACTORY_ID=jakarta.xml.ws.spi.Provider
#SVC_FACTORY_ID=jakarta.xml.ws.spi.Provider
#FACTORY_IMPL_PREFIX=jaxws.factory.

compileAll
source ../scenarios.sh
cleanAll

## new version of API
#FACTORY_IMPL_PREFIX=jaxb.factory.jaxbctxfactory.New
#FACTORY_ID=jakarta.xml.bind.JAXBContextFactory
#SVC_FACTORY_ID=jakarta.xml.bind.JAXBContextFactory
#
#compileAll
#source ../scenarios.sh
#cleanAll

rm -rf endorsed
