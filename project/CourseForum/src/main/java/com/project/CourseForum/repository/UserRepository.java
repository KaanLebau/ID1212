package com.project.CourseForum.repository;

import com.project.CourseForum.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
}