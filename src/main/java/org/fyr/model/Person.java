package org.fyr.model;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    public Person(){

    }

    public Person(int id, String firstName, String lastName, String email, AppUser creds) {
        if((firstName== null || firstName.trim().length() == 0) ||
                (lastName == null || lastName.trim().length() == 0) ||
                (email == null || email.trim().length() == 0)){
            throw new NullPointerException("Cant be null");
        }
        this.id = id;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
        this.credentials = creds;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        if(email == null ||email.trim().length() == 0){
            throw new NullPointerException("Email cant be empty");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}
