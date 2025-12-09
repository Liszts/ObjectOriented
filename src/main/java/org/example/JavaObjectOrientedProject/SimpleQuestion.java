package org.example.JavaObjectOrientedProject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SimpleQuestion implements Question<SimpleAnswer> {
    private String id;
    private String text;
    private List<SimpleAnswer> answers = new ArrayList<>();


    // No-arg constructor for JSON
    public SimpleQuestion() { }


    public SimpleQuestion(String text) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
    }


    public void addAnswer(SimpleAnswer a) { answers.add(a); }


    @Override
    public String getId() { return id; }


    @Override
    public String getText() { return text; }


    @Override
    public List<SimpleAnswer> getAnswers() { return answers; }


    @Override
    public boolean isCorrectAnswer(String answerId) {
        return answers.stream().anyMatch(a -> a.getId().equals(answerId) && a.isCorrect());
    }


    public void setId(String id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setAnswers(List<SimpleAnswer> answers) { this.answers = answers; }
}
