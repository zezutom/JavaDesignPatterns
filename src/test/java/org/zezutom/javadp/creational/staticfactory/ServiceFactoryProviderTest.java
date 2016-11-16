package org.zezutom.javadp.creational.staticfactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.zezutom.javadp.TestUtil;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ServiceFactoryProviderTest {

    private static class ServiceProviderA implements ServiceProvider {}

    private static class ServiceProviderB implements ServiceProvider {}

    private static class ServiceProviderC implements ServiceProvider {}

    @BeforeClass
    public static void setUp() {
        ServiceProviderFactory.registerProvider("a", new ServiceProviderA());
        ServiceProviderFactory.registerProvider("b", new ServiceProviderB());
        ServiceProviderFactory.registerDefaultProvider(new ServiceProviderC());
    }

    @Test
    public void neverReturnsNull() {
        assertNotNull(ServiceProviderFactory.getInstance("a"));
        assertNotNull(ServiceProviderFactory.getInstance("b"));
        assertNotNull(ServiceProviderFactory.getInstance("x"));
        assertNotNull(ServiceProviderFactory.getInstance(null));
    }

    @Test
    public void objectsAreReused() {
        ServiceProvider instance1 = ServiceProviderFactory.getInstance("a");
        ServiceProvider instance2 = ServiceProviderFactory.getInstance("a");
        assertTrue(instance1 == instance2);
    }

    @Test
    public void threadSafe() {
        TestUtil.testThreadSafety(() -> ServiceProviderFactory.getInstance("a"));
    }

    @Test
    public void polymorphism() {
        ServiceProvider providerA = ServiceProviderFactory.getInstance("a");
        ServiceProvider providerB = ServiceProviderFactory.getInstance("b");
        TestUtil.isInstanceOf(providerA, ServiceProviderA.class);
        TestUtil.isInstanceOf(providerB, ServiceProviderB.class);
    }
}
