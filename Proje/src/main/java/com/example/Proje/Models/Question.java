package com.example.Proje.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String text;


    @ManyToOne   // many question to one quiz
    private Quiz quiz;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL) // sorunun birden fazla  cevabi oldugu icin
    private List<Answer> answers = new ArrayList<>();

    @Column(name = "correct_answer_index")
    private int correct; // stores which answer is correct


    public Question() {
    }

    public int getCorrect() { return correct; }
    public void setCorrect(int correct) { this.correct = correct; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}