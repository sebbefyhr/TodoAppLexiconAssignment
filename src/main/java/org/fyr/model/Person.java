package org.fyr.model;

import org.fyr.Sequencers.PersonIdSequencer;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private int id;
    private String firstName;
    private String lastName;

    private PersonIdSequencer personIdSequencer = PersonIdSequencer.getInstance();


    public Person(int id, String firstName, String lastName) {
        if((firstName== null || firstName.trim().length() == 0) ||
                (lastName == null || lastName.trim().length() == 0)){
            throw new NullPointerException("Cant be null");
        }
        this.id = id;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    public Person(String firstName, String lastName) {
        if((firstName== null || firstName.trim().length() == 0) ||
                (lastName == null || lastName.trim().length() == 0)){
            throw new NullPointerException("Cant be null");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }


    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null ||firstName.trim().length() == 0){
            throw new NullPointerException("First name cant be empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null ||lastName.trim().length() == 0){
            throw new NullPointerException("Last name cant be empty");
        }
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
