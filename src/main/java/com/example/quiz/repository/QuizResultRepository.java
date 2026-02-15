package com.example.quiz.repository;

import com.example.quiz.model.QuizResult;
import com.example.quiz.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {
    List<QuizResult> findByStudent(Student student);
}
