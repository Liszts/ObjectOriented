import java.util.*;

public class Main {
    public static void main(String[] args) {

        Answer a1 = new SimpleAnswer("Paris", true);
        Answer a2 = new SimpleAnswer("Berlin", false);
        Answer a3 = new SimpleAnswer("Rome", false);

        Question q1 = new SimpleQuestion(
                "What is the capital of France?",
                Arrays.asList(a1, a2, a3)
        );

        Quiz quiz = new SimpleQuiz(List.of(q1));
        quiz.start();

        Answer a7 = new SimpleAnswer("Paris", false);
        Answer a8 = new SimpleAnswer("Berlin", true);
        Answer a9 = new SimpleAnswer("Rome", false);

        Question q2 = new SimpleQuestion(
                "What is the capital of Germany?",
                Arrays.asList(a7, a8, a9)
        );

        Quiz quiz1 = new SimpleQuiz(List.of(q2));
        quiz1.start();
    }
}