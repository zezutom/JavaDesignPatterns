package org.zezutom.javadp.creational;

import java.util.Objects;

class Person {

    enum Sex { MALE, FEMALE }

    private String name;

    private int age;

    private Sex sex;

    private Person(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        // Handles null and doesn't break Liskov's substitution principle (using getClass() would break it)
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return age == person.age && // primitive, can't be null
                Objects.equals(name, person.name) &&    // handles null values
                Objects.equals(sex, person.sex);

    }

    @Override
    public int hashCode() {
        // handles null values
        return Objects.hash(age, name, sex);
    }

    public static Person newInstance(String name, int age, Sex sex) {
        if (name == null || sex == null || age < 0) throw new IllegalArgumentException();
        return new Person(name, age, sex);
    }

    public static Person copyOf(Person person) {
        if (person == null) throw new IllegalArgumentException();
        return newInstance(person.getName(), person.getAge(), person.getSex());
    }
}