package com.example.quiz.controller;

import com.example.quiz.model.*;
import com.example.quiz.model.Module;
import com.example.quiz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins="http://localhost:5173")
public class QuizController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @PostMapping("/generate/{moduleId}")
    public List<Question> generateQuiz(@PathVariable int moduleId){
        Module module = moduleRepository.findById(moduleId).orElse(null);
        if(module != null){
            return questionRepository.findByModule(module);
        }
        return List.of();
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestBody Map<String,Object> data){
        Integer studentId = (Integer) data.get("studentId");
        Integer moduleId = (Integer) data.get("moduleId");
        Integer score = (Integer) data.get("score");

        Student student = studentRepository.findById(studentId).orElse(null);
        Module module = moduleRepository.findById(moduleId).orElse(null);

        if(student != null && module != null){
            QuizResult qr = new QuizResult();
            qr.setStudent(student);
            qr.setModule(module);
            qr.setScore(score);
            quizResultRepository.save(qr);
            return "Quiz Submitted";
        }
        return "Error submitting quiz";
    }

    @GetMapping("/result/{studentId}")
    public List<QuizResult> getResults(@PathVariable int studentId){
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student != null){
            return quizResultRepository.findByStudent(student);
        }
        return List.of();
    }
}
