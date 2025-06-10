package com.prince.springJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prince.springJwt.model.Role;
import com.prince.springJwt.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User findByRole(Role role);

    Optional<User> findByEmail(String email);
}
