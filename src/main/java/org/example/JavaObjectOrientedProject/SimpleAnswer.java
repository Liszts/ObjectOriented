package org.example.JavaObjectOrientedProject; // farkli bi id vermek icin uuid kullanioz

import java.util.UUID;


public class SimpleAnswer implements Answer { //ana sinifi implement edio
    private String id;
    private String text;
    private boolean correct;
                         // fields

    // Required for JSON libraries   bu olmasaydi jsondan alirdik
    public SimpleAnswer() {}

             // parametrik constructor otomatik id uretiyor
    public SimpleAnswer(String text, boolean correct) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.correct = correct;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getText() { return text; }

    @Override
    public boolean isCorrect() { return correct; }

    public void setId(String id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setCorrect(boolean correct) { this.correct = correct; }
}

               // yukleme Sirasinda gson bu setleri kullaniyor
