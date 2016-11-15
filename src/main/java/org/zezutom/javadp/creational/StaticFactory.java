package org.zezutom.javadp.creational;

import org.zezutom.javadp.util.shim.patternmatching.PatternMatching;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.zezutom.javadp.util.shim.patternmatching.Pattern.inCaseOf;
import static org.zezutom.javadp.util.shim.patternmatching.Pattern.otherwise;

public class StaticFactory {

    private static String DEFAULT_KEY = "default";

    private static final ConcurrentMap<String, ServiceProvider> serviceProviders = new ConcurrentHashMap<>();

    private static final PatternMatching patternMatching = new PatternMatching(
        inCaseOf("a", p -> new ServiceProviderA()),
        inCaseOf("b", p -> new ServiceProviderB()),
        otherwise(p -> new ServiceProviderC())
    );

    public static ServiceProvider getInstance(String key) {
        return serviceProviders.computeIfAbsent(key == null ? DEFAULT_KEY : key, patternMatching::matchFor);
    }
}

interface ServiceProvider {}

class ServiceProviderA implements ServiceProvider {}

class ServiceProviderB implements ServiceProvider {}

class ServiceProviderC implements ServiceProvider {}
