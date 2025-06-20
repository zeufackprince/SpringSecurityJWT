package com.helloIftekhar.springJwt.repository;

import com.helloIftekhar.springJwt.model.Role;
import com.helloIftekhar.springJwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User findByRole(Role role);

    Optional<User> findByEmail(String email);
}
