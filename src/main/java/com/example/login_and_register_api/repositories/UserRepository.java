package com.example.login_and_register_api.repositories;

import com.example.login_and_register_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

// ENTIDADE E TIPO ID
public interface UserRepository extends JpaRepository<User, String> {
}
