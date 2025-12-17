package org.example.JavaObjectOrientedProject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class QuizImpl implements Quiz<SimpleQuestion> { //quiz interfacesini implemente eder cnuku generic
    private String id;   // alanlar
    private String title;
    private List<SimpleQuestion> questions = new ArrayList<>();
              // generics collection

    // No-arg constructor for JSON
    public QuizImpl() { }

// uui generate ediyor
    public QuizImpl(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
    }

// zorunlu methodlar bunlar interface icin quiz bilgisine erisilir
    @Override
    public String getId() { return id; }


    @Override
    public String getTitle() { return title; }


    @Override
    public List<SimpleQuestion> getQuestions() { return questions; }

        //soru ekleme
    @Override
    public void addQuestion(SimpleQuestion q) { questions.add(q); }


    @Override
    public void removeQuestion(String questionId) {
        questions.removeIf(q -> q.getId().equals(questionId));
    }


    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setQuestions(List<SimpleQuestion> questions) { this.questions = questions; }
}