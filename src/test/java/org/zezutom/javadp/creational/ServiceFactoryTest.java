package org.zezutom.javadp.creational;

import org.junit.Test;
import org.zezutom.javadp.TestUtil;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ServiceFactoryTest {

    @Test
    public void neverReturnsNull() {
        assertNotNull(StaticFactory.getInstance("a"));
        assertNotNull(StaticFactory.getInstance("b"));
        assertNotNull(StaticFactory.getInstance("x"));
        assertNotNull(StaticFactory.getInstance(null));
    }

    @Test
    public void objectsAreReused() {
        ServiceProvider instance1 = StaticFactory.getInstance("a");
        ServiceProvider instance2 = StaticFactory.getInstance("a");
        assertTrue(instance1 == instance2);
    }

    @Test
    public void threadSafe() {
        TestUtil.testThreadSafety(() -> StaticFactory.getInstance("a"));
    }

    @Test
    public void polymorphism() {
        ServiceProvider providerA = StaticFactory.getInstance("a");
        ServiceProvider providerB = StaticFactory.getInstance("b");
        TestUtil.isInstanceOf(providerA, ServiceProviderA.class);
        TestUtil.isInstanceOf(providerB, ServiceProviderB.class);
    }
}
