package org.example.JavaObjectOrientedProject;

import java.util.ArrayList; // list implementesyonu
import java.util.List; // answer listesi icin
import java.util.UUID; // benzersiz id


public class SimpleQuestion implements Question<SimpleAnswer> { // interfaceyi impelent yapiyo
    private String id;
    private String text;
    private List<SimpleAnswer> answers = new ArrayList<>();


    // No-arg constructor for JSON
    public SimpleQuestion() { }

// soru metnini aliyo uuid bensersiz id olusturup cevap listesine defult olarak gliyo
    public SimpleQuestion(String text) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
    }


    public void addAnswer(SimpleAnswer a) { answers.add(a); } // yeni cevap ekler

      // Bu alttakiler cok onemli getter her seyi dondurur
    @Override
    public String getId() { return id; }


    @Override
    public String getText() { return text; }


    @Override
    public List<SimpleAnswer> getAnswers() { return answers; }


    @Override
    public boolean isCorrectAnswer(String answerId) { // cevap dogrumu degilmi onu kontrol eder
        return answers.stream().anyMatch(a -> a.getId().equals(answerId) && a.isCorrect());
    }

// jsondan yuklenen neslerde gson bu setterlari kullanarak doldurur sonra duzenlemek istersek setter giriyo devreye
    public void setId(String id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setAnswers(List<SimpleAnswer> answers) { this.answers = answers; }
}
