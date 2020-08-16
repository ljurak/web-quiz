package com.example.webquiz.repository;

import com.example.webquiz.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}
