package org.zezutom.javadp.util.shim.patternmatching;

import java.util.function.Function;

/**
 * Kudos to Kerflyn's Blog: https://kerflyn.wordpress.com/2012/05/09/towards-pattern-matching-in-java/
 */
public interface Pattern {

    boolean matches(Object value);

    <T> T apply(Object value);

    static Pattern inCaseOf(String pattern, Function<String, Object> function) {
        return new StringPattern(pattern, function);
    }

    static Pattern otherwise(Function<Object, Object> function) {
        return new OtherwisePattern(function);
    }

}
