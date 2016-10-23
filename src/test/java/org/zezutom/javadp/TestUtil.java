package org.zezutom.javadp;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class TestUtil {

    private TestUtil() {}

    public static<T> void testThreadSafety(Callable<T> task) {
        try {
            final int threadCount = 100;
            List<Callable<T>> tasks = Collections.nCopies(threadCount, task);
            ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            List<Future<T>> futures = executorService.invokeAll(tasks);

            final T instance = task.call();
            for (Future<T> future : futures) {
                assertTrue(instance == future.get());
            }
        } catch (Exception e) {
           fail(String.format("Thread safety test failed due to '%s'", e.getMessage()));
        }
    }

    public static<A, B extends A> void isInstanceOf(A instance, Class<B> expectedClass) {
        assertNotNull(instance);
        assertTrue(instance.getClass().equals(expectedClass));
    }
}
