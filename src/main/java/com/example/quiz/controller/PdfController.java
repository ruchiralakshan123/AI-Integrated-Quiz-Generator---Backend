package com.example.quiz.controller;

import com.example.quiz.model.Module;
import com.example.quiz.model.Question;
import com.example.quiz.repository.ModuleRepository;
import com.example.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins="http://localhost:5173")
public class PdfController {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/upload")
    public String uploadPdf(@RequestParam("file") MultipartFile file, @RequestParam("moduleId") int moduleId){
        try{
            Module module = moduleRepository.findById(moduleId).orElse(null);
            if(module == null) return "Module not found";

            // TEMP: read PDF as plain text (replace with PDF parsing library later)
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while((line = br.readLine()) != null){
                Question q = new Question();
                q.setModule(module);
                q.setQuestionText(line);
                q.setOption1("Option A");
                q.setOption2("Option B");
                q.setOption3("Option C");
                q.setOption4("Option D");
                q.setCorrectOption("Option A");
                questionRepository.save(q);
            }
            return "PDF uploaded successfully";
        } catch(Exception e){
            e.printStackTrace();
            return "Error uploading PDF";
        }
    }
}
