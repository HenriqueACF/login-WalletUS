package com.example.login_and_register_api.repositories;

import com.example.login_and_register_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// ENTIDADE E TIPO ID
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
