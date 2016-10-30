package org.zezutom.javadp.creational;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EqualsAndHashCodeTest {

    private final Person personA = Person.newInstance("Alice", 31, Person.Sex.FEMALE);

    private final Person personJustLikeA = Person.copyOf(personA);

    private final Person personB = Person.newInstance("Bob", 27, Person.Sex.MALE);

    @Test
    public void reflexivity() {
        assertTrue(personA.equals(personA));
    }

    @Test
    public void symmetry() {
        assertTrue(personA.equals(personJustLikeA));
        assertTrue(personJustLikeA.equals(personA));
    }

    @Test
    public void transitiveness() {
        Person anotherPersonJustLikeA = Person.copyOf(personA);
        assertTrue(personJustLikeA.equals(anotherPersonJustLikeA));
        assertTrue(personA.equals(anotherPersonJustLikeA));
    }

    @Test
    public void consistency() {
        for (int i = 0; i < 100; i++) {
            assertTrue(personA.equals(personJustLikeA));
            assertTrue(!personA.equals(personB));
        }
    }

    @Test
    public void nonNullity() {
        assertTrue(!personA.equals(null));
    }

    @Test
    public void equalObjectsHaveSameHashcode() {

    }
}
