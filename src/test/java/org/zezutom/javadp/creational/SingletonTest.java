package org.zezutom.javadp.creational;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class SingletonTest {

    @Test
    public void lazilyInitialised() {
        Class<Singleton> singletonClass = Singleton.class;
        Field[] fields = singletonClass.getDeclaredFields();

        // Has no state
        final long instanceCount = Arrays.stream(fields).filter(f -> f.getType().equals(Singleton.class)).count();
        assertTrue(instanceCount == 0);

        // Only has a single nested class (which won't get eagerly loaded)
        Class[] nestedClasses = Singleton.class.getDeclaredClasses();
        assertTrue((nestedClasses.length == 1));
    }

    @Test
    public void threadSafe() throws InterruptedException, ExecutionException {
        final int threadCount = 100;
        Callable<Singleton> task = Singleton::getInstance;
        List<Callable<Singleton>> tasks = Collections.nCopies(threadCount, task);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<Singleton>> futures = executorService.invokeAll(tasks);

        final Singleton instance = Singleton.getInstance();
        for (Future<Singleton> future : futures) {
            assertEquals(instance, future.get());
        }
    }
    
    @Test(expected = InvocationTargetException.class)
    public void resistsReflection()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Singleton.getInstance();    // A legit instance, the one and only!
        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();  // This should fail, there is an instance already
        fail("It shouldn't be possible to create yet another instance!");
    }

    @Test
    public void isSerializableAsSingleton() throws IOException, ClassNotFoundException {
        Singleton serializedInstance = Singleton.getInstance();

        try (ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream("serializedInstance.ser"));
             ObjectInput objectInput = new ObjectInputStream(new FileInputStream("serializedInstance.ser"))) {
            objectOutput.writeObject(serializedInstance);
            Singleton deserializedInstance = (Singleton) objectInput.readObject();

            // If it's the same object then the hashcode must be the same as well
            assertTrue("No new instance should be created upon deserialization!",
                    serializedInstance.hashCode() == deserializedInstance.hashCode());
        }
    }

}
