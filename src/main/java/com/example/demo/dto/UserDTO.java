package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotEmpty(message = "username ne peut être vide.")
    private String userName;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String firstName;

    private Integer palier;

    public Integer getPalier() {
        return palier;
    }

    public void setPalier(Integer palier) {
        this.palier = palier;
    }

    private String lastName;
    @NotEmpty(message = "password ne peut être vide.")
    @Size(min = 5, max = 250)
    private String password;

    @Column(unique = true)
    @NotEmpty(message = "email ne peut être vide.")
    @Size(min = 5, max = 250)
    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserDTO [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
                + password + ", email=" + email + "]";
    }
}