/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.spi.test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import jakarta.xml.ws.spi.Provider;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProviderLookupTest {

    private static final Logger LOGGER = Logger.getLogger("jakarta.xml.ws");

    private final List<LogRecord> records = new CopyOnWriteArrayList<>();

    private final Handler handler = new Handler() {
        @Override
        public void publish(LogRecord logRecord) {
            records.add(logRecord);
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() {
        }
    };

    @Before
    public void setUp() {
        LOGGER.addHandler(handler);
    }

    @After
    public void tearDown() {
        LOGGER.removeHandler(handler);
    }

    @Test
    public void defaultProviderOutsideOsgiLogsNoWarnings() {
        Provider provider = Provider.provider();

        Assert.assertEquals("com.sun.xml.ws.spi.ProviderImpl", provider.getClass().getName());
        for (LogRecord logRecord : records) {
            Assert.assertTrue("Unexpected log record: [" + logRecord.getLevel() + "] " + logRecord.getMessage(),
                    logRecord.getLevel().intValue() < Level.WARNING.intValue());
        }
    }
}
