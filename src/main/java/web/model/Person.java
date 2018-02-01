package web.model;

import java.util.Calendar;

public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private int age;

    public Person() {
    }

    public Person(String firstName, String lastName, String address, int age) {
        this.id = generateId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    private long generateId() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
