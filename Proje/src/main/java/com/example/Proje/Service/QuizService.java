package com.example.Proje.Service;

import com.example.Proje.Models.Quiz;
import com.example.Proje.Repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {


    private final QuizRepository quizRepository;


    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }


    public Quiz findById(Long id) {
        return quizRepository.findById(id).orElseThrow();
    }
}