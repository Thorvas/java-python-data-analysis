package com.example.demo.Repositories;

import com.example.demo.DummyObject.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    Optional<CustomUser> findByUsername(String username);
}
