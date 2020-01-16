/*
 * Copyright (c) 2015, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jaxws.test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.ws.spi.Provider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Tests creation of JAXBContext - base class for different tests
 */
public class Test {

    protected Provider provider() throws Throwable {
        try {
            Provider provider = Provider.provider();
            System.out.println("     TEST: context class = [" + provider.getClass().getName() + "]\n");
            return provider;
        } catch (Throwable t) {
            System.out.println("     TEST: Throwable [" + t.getClass().getName() + "] thrown.\n");
            throw t;
        }
    }

    protected void test(String[] args) {

//        // 3rd parameter means that TCCL should be used
//        if (args.length > 2) {
//            setContextClassLoader();
//        }

        try {
            Provider provider = provider();
            assertTrue(provider != null, "No Provider found.");
            String className = provider.getClass().getName();
            assertTrue(args[0].equals(className), "Incorrect provider: [" + className +
                    "], Expected: [" + args[0] + "]");

        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            String expectedExceptionClass = args[1];
            String throwableClass = throwable.getClass().getName();
            boolean correctException = throwableClass.equals(expectedExceptionClass);
            if (!correctException) {
                throwable.printStackTrace();
            }
            assertTrue(correctException, "Got unexpected exception: [" +
                    throwableClass + "], expected: [" + expectedExceptionClass + "]");
        }
    }

    /**
     * Alloas to test loading classes using TCCL.
     */
    private void setContextClassLoader() {
        try {
            String path = new File(".").getAbsoluteFile().getParentFile().getParent() + "/src/";
//            System.out.println("    Creating URLClassLoader to load classes from: " + path);
            ClassLoader cl = new URLClassLoader(
                    new URL[]{
                            new URL("file://" + path + "/")
                    }, null
            );
            Thread.currentThread().setContextClassLoader(cl);
//            System.out.println("    ...contextClassLoader set.");
        } catch (Throwable t) {
            System.out.println("    FAILED: Problem while creating URLClassLoader: ");
            t.printStackTrace();
        }
    }

    private static void assertTrue(boolean condition, String msg) {
        if (!condition) {
            log(" FAILED -  ERROR: " + msg);
            throw new RuntimeException(msg);
        } else {
            log(" PASSED");
        }
    }

    private static void log(String msg) {
        System.out.println(msg);
    }


    public static void main(String[] args) {
        new Test().test(args);
    }
}
