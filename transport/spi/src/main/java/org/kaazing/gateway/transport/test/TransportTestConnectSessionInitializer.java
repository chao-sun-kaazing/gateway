/**
 * Copyright 2007-2015, Kaazing Corporation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaazing.gateway.transport.test;

import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionInitializer;
import org.kaazing.test.util.ThrowableContainer;

public abstract class TransportTestConnectSessionInitializer implements IoSessionInitializer<ConnectFuture> {
    private final CountDownLatch latch;
    private final ThrowableContainer failures;

    public static TransportTestConnectSessionInitializer EMPTY_INITIALIZER = new TransportTestConnectSessionInitializer() {
        @Override
        public String getCheckpointFailureMessage() {
            return "A Failure occurred on a NOOP connect session initializer.";
        }

        @Override
        public void initializeSession(IoSession session, ConnectFuture future) {
            return; // no op
        }
    };

    public TransportTestConnectSessionInitializer() {
        this(0);
    }

    public TransportTestConnectSessionInitializer(int desiredCount) {
        latch = new CountDownLatch(desiredCount);
        failures = new ThrowableContainer();
    }

    public void checkpoint() {
        latch.countDown();
    }

    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        boolean result = latch.await(timeout, unit);
        if ( !result ) {
            fail(getCheckpointFailureMessage());
        }
        if ( !failures.isEmpty() ) {
            fail(failures.toString());
        }
        return result;
    }

    public void assertEquals(Object s1, Object s2) {
        try {
            Assert.assertEquals(s1, s2);
        } catch (AssertionError error) {
            failures.add(error);
        }
    }

    public void assertEquals(int s1, int s2) {
        try {
            Assert.assertEquals(s1, s2);
        } catch (AssertionError error) {
            failures.add(error);
        }
    }

    public void assertEquals(double s1, double s2) {
        try {
            Assert.assertEquals(s1, s2);
        } catch (AssertionError error) {
            failures.add(error);
        }
    }

    public void assertEquals(boolean s1, boolean s2) {
        try {
            Assert.assertEquals(s1, s2);
        } catch (AssertionError error) {
            failures.add(error);
        }
    }

    public abstract String getCheckpointFailureMessage();
}
