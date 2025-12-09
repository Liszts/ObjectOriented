package org.example.JavaObjectOrientedProject;

import java.util.List;


public interface Quiz<Q extends Question<?>> {
    String getId();
    String getTitle();
    List<Q> getQuestions();
    void addQuestion(Q q);
    void removeQuestion(String questionId);
}
