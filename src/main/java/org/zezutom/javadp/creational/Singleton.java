package org.zezutom.javadp.creational;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Initialization-on-demand holder idiom, thread-safe and resistant to reflection.
 */
public class Singleton {

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

    private static class InstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
