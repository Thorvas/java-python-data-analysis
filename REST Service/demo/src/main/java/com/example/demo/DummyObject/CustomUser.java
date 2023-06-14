package com.example.demo.DummyObject;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "custom_user")
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_unlocked")
    private boolean isAccountNonLocked;

    @Column(name = "is_actual")
    private boolean isAccountNonExpired;

    @Column(name = "credentials_actual")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private Set<Authority> authorities;

}
