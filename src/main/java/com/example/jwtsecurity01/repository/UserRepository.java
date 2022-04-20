package com.example.jwtsecurity01.repository;

import com.example.jwtsecurity01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
