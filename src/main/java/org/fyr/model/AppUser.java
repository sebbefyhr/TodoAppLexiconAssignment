package org.fyr.model;

import java.util.Objects;

public class AppUser {
    private String username;
    private String password;
    private AppRole role;


    public AppUser(String username, String password, AppRole role){
        if((username == null || username.isEmpty()) ||(password == null || password.isEmpty())){
            throw new NullPointerException("Username or password cant be empty or null");
        }
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        if(username == null || username.isEmpty()){
            throw new NullPointerException("Username cant be empty or null");
        }
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if(password == null || password.isEmpty()){
            throw new NullPointerException("Password cant be empty or null");
        }
        this.password = password;
    }
    public AppRole getRole() {
        return role;
    }
    public void setRole(AppRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}
