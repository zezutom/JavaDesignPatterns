package org.zezutom.javadp.util.shim.patternmatching;

import java.util.function.Function;

public class OtherwisePattern implements Pattern {

    private final Function<Object, Object> function;

    public OtherwisePattern(Function<Object, Object> function) {
        this.function = function;
    }

    @Override
    public boolean matches(Object value) {
        return true;
    }

    @Override
    public Object apply(Object value) {
        return function.apply(value);
    }
}
