package org.example.JavaObjectOrientedProject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class QuizImpl implements Quiz<SimpleQuestion> {
    private String id;
    private String title;
    private List<SimpleQuestion> questions = new ArrayList<>();


    // No-arg constructor for JSON
    public QuizImpl() { }


    public QuizImpl(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
    }


    @Override
    public String getId() { return id; }


    @Override
    public String getTitle() { return title; }


    @Override
    public List<SimpleQuestion> getQuestions() { return questions; }


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