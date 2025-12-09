package org.example.JavaObjectOrientedProject;

import java.util.List;


public interface Question<A extends Answer> {
    String getId();
    String getText();
    List<A> getAnswers();
    boolean isCorrectAnswer(String answerId);
}