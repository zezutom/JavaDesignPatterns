package org.zezutom.javadp.creational;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Initialization-on-demand holder idiom and is thread-safe, resistant to reflection
 * and serializable.
 */
public class Singleton implements Serializable {

    private static final AtomicBoolean instanceExists = new AtomicBoolean(false);

    private static final String INSTANCE_EXISTS_ERR_MSG = "Please use the current instance!";

    private Singleton() {
        if (instanceExists.get()) {
            throw new IllegalStateException(INSTANCE_EXISTS_ERR_MSG);
        }
        synchronized (Singleton.class) {
            if (instanceExists.get()) {
                throw new IllegalStateException(INSTANCE_EXISTS_ERR_MSG);
            }
            instanceExists.set(true);
        }
    }

    // Prevents spinning up a new instance upon deserialization
    Object readResolve() {
        return getInstance();
    }

    private static class InstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
