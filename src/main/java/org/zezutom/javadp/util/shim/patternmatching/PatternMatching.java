package org.zezutom.javadp.util.shim.patternmatching;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Kudos to Kerflyn's Blog: https://kerflyn.wordpress.com/2012/05/09/towards-pattern-matching-in-java/
 */
public class PatternMatching {

    private final List<Pattern> patterns;

    public PatternMatching(Pattern... patterns) {
        this.patterns = Arrays.asList(patterns);
    }

    public <T> T matchFor(Object value) {
        Optional<Pattern> pattern = patterns.stream().filter(p -> p.matches(value)).findFirst();
        if (pattern.isPresent()) {
            return pattern.get().apply(value);
        }
        throw new IllegalArgumentException("Cannot match value: " + value);
    }
}

