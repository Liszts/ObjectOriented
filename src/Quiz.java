import java.util.List;

interface Quiz extends Answer, Question {
    List<Question> getQuestions();
    void start();
}