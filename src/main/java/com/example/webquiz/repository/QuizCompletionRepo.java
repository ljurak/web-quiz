package com.example.webquiz.repository;

import com.example.webquiz.model.QuizCompletion;
import com.example.webquiz.model.QuizCompletionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletionRepo extends JpaRepository<QuizCompletion, Integer> {

    @Query(
            "select new com.example.webquiz.model.QuizCompletionDto(qc.quiz.id, qc.completedAt) " +
            "from QuizCompletion qc " +
            "where qc.user.email = :userEmail " +
            "order by qc.completedAt desc"
    )
    Page<QuizCompletionDto> findQuizCompletionByUser(@Param("userEmail") String userEmail, Pageable pageable);
}
