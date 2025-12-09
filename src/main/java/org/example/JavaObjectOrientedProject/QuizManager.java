package org.example.JavaObjectOrientedProject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class QuizManager {
    private final Gson gson;

    public QuizManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveQuiz(QuizImpl quiz, String path) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            gson.toJson(quiz, fw);
        }
    }

    public QuizImpl loadQuiz(String path) throws IOException {
        try (FileReader fr = new FileReader(path)) {
            return gson.fromJson(fr, QuizImpl.class);
        }
    }
}