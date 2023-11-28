package com.mdauDev.SecurityCurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomerModel{
    private String name;
    private String email;
    private String password;
    private String role;

    public CustomerModel(String name, String email, String password,String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role=role;
    }

    public CustomerModel() {
    }

    public String getPassword() {
        return password;
    }

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


    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
