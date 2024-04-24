package com.thenavigator.crud_backend.repository;

import com.thenavigator.crud_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
