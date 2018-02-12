package web.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Random;

@Entity
@Table(name = "person")
public class Person implements ModelObject {
    @Id
    private long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column
    private String address;
    @Column
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
        return Math.abs(new Random(Calendar.getInstance().getTimeInMillis()).nextLong());
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
