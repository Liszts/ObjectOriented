package org.example.JavaObjectOrientedProject; // single responsibility principle gson json donusumu

import com.google.gson.Gson; // java json a doner
import com.google.gson.GsonBuilder; // ayarlari yapar


import java.io.FileReader; // json dosyasini okumak icn
import java.io.FileWriter;
import java.io.IOException; // for error kismi

         // veriyi yonetir
public class QuizManager {
    private final Gson gson; //json donusturme islemi yapar

    public QuizManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    //ozellestirmeyi sagliyor ustteki pretty printing
               // quiz i jsona kaydetme
    public void saveQuiz(QuizImpl quiz, String path) throws IOException {
        try (FileWriter fw = new FileWriter(path)) { // for close
            gson.toJson(quiz, fw); // quizmpl nesnesini json stringe cevirir
        }
    }

    public QuizImpl loadQuiz(String path) throws IOException {
        try (FileReader fr = new FileReader(path)) { // jsonu okumak icin
            return gson.fromJson(fr, QuizImpl.class); // alan adlari doldurur ve nesne olusturmak icin
        }
    }
}