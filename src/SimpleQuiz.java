import java.util.List;
import java.util.Scanner;

class SimpleQuiz implements Quiz {

    private final List<Question> questions;

    public SimpleQuiz(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (Question q : questions) {
            q.print();
            System.out.print("Your answer: ");
            int choice = scanner.nextInt() - 1;

            if (q.checkAnswer(choice)) {
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong!\n");
            }
        }
    }
}