package org.zezutom.javadp.creational;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StaticFactory {

    private static final ConcurrentMap<String, ServiceProvider> serviceProviders = new ConcurrentHashMap<>();

    private static String DEFAULT_KEY = "default";

    public static ServiceProvider getInstance(String key) {
        return serviceProviders.computeIfAbsent(key == null ? DEFAULT_KEY : key, ServiceProvider::newInstance);
    }
}

interface ServiceProvider {
    static ServiceProvider newInstance(String key) {
        ServiceProvider serviceProvider;
        switch (key) {
            case "a":
                serviceProvider = new ServiceProviderA();
                break;
            case "b":
                serviceProvider = new ServiceProviderB();
                break;
            default:
                serviceProvider = new ServiceProviderC();
        }
        return serviceProvider;
    }
}

class ServiceProviderA implements ServiceProvider {}

class ServiceProviderB implements ServiceProvider {}

class ServiceProviderC implements ServiceProvider {}
