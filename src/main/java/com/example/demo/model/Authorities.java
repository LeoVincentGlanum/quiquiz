package com.example.demo.model;


import javax.persistence.*;

@Entity
public class Authorities {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    private String username;

    private String authority;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
