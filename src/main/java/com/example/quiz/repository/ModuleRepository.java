package com.example.quiz.repository;

import com.example.quiz.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
    List<Module> findByDegree(String degree);
}
