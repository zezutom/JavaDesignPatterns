package org.zezutom.javadp.util.shim.patternmatching;

import java.util.function.Function;

public class StringPattern implements Pattern {

    private String pattern;

    private Function<String, Object> function;

    public StringPattern(String pattern, Function<String, Object> function) {
        this.pattern = pattern;
        this.function = function;
    }

    @Override
    public boolean matches(Object value) {
        if (value == null) return false;
        return pattern.equalsIgnoreCase(value.toString());
    }

    @Override
    public Object apply(Object value) {
        if (value == null) return null;
        return function.apply(value.toString());
    }
}
