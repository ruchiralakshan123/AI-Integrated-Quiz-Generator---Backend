package com.example.quiz.repository;

import com.example.quiz.model.Question;
import com.example.quiz.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByModule(Module module);
}
