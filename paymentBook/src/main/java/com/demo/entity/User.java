package com.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    private String name;

    @Id
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
