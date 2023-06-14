package com.example.demo.DummyObject;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "custom_user_id")
    private CustomUser user;
}
