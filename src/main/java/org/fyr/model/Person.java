package org.fyr.model;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(){

    }

    public Person(String firstName, String lastName, String email) {
        if((firstName== null || firstName.trim().length() == 0) ||
                (lastName == null || lastName.trim().length() == 0) ||
                (email == null || email.trim().length() == 0)){
            throw new NullPointerException("Cant be null");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
    }

    public Person(int id, String firstName, String lastName, String email) {
        this(firstName, lastName, email);
        this.id = id;
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

    public String getSummary(){
        return "{" +
                "id: " + id +
                ", name: " + firstName +" " + lastName  +
                ", email: " + email  +
                '}';

    }

}
