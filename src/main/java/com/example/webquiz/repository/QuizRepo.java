package com.example.webquiz.repository;

import com.example.webquiz.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends CrudRepository<Quiz, Integer> {

}
