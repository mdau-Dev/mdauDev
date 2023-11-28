package com.mdauDev.SecurityCurity.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class Customer implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "my_customer_sequence",
            sequenceName = "my_customer_sequence",
            allocationSize = 3
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_customer_sequence"
    )
    private long customerId;
    private String name;
    @Column(
            length = 40,
            unique = true,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            length = 60
    )
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean enabled=false;

    public Customer(long id, String name,
                    String email, String password, Role role, boolean enabled) {
        this.customerId = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public Customer() {
    }

    public Customer(String name, String email,
                    String password, Role role, boolean enabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long id) {
        this.customerId = id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
