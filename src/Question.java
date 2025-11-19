import java.util.List;

interface Question {
    String getQuestionText();
    List<Answer> getAnswers();
    void print();
    boolean checkAnswer(int index);
}