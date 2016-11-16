package org.zezutom.javadp.creational.staticfactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServiceProviderFactory {

    private static final ConcurrentMap<String, ServiceProvider> serviceProviders = new ConcurrentHashMap<>();

    private static final String DEFAULT_KEY = "default";

    public static void registerDefaultProvider(ServiceProvider serviceProvider) {
        registerProvider(DEFAULT_KEY, serviceProvider);
    }

    public static void registerProvider(String key, ServiceProvider serviceProvider) {
        serviceProviders.put(key, serviceProvider);
    }

    public static ServiceProvider getInstance(String key) {
        return serviceProviders.computeIfAbsent(key == null ? DEFAULT_KEY : key, ServiceProviderFactory::newInstance);
    }

    public static ServiceProvider newInstance(String key) {
        return serviceProviders.getOrDefault(key, serviceProviders.get(DEFAULT_KEY));
    }
}

