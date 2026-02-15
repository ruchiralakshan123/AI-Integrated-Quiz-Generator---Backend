package com.example.quiz.controller;

import com.example.quiz.model.Student;
import com.example.quiz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="http://localhost:5173")
public class AuthController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> loginData){
        String email = loginData.get("email");
        String password = loginData.get("password");

        Student student = studentRepository.findByEmail(email);
        if(student != null && student.getPassword().equals(password)){
            return "Login Successful";
        }
        return "Invalid Credentials";
    }
}
