package com.example.quiz.controller;

import com.example.quiz.model.Module;
import com.example.quiz.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@CrossOrigin(origins="http://localhost:5173")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepository;

    @GetMapping("/{degree}")
    public List<Module> getModulesByDegree(@PathVariable String degree){
        return moduleRepository.findByDegree(degree);
    }
}
