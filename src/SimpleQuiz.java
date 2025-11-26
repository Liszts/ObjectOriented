import java.util.List;
import java.util.Scanner;

class SimpleQuiz extends AbstractQuizHandler implements Quiz {

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
            print();
            System.out.print("Your answer: ");
            int choice = scanner.nextInt() - 1;
            String answer = q.getAnswers().get(choice).getText();

            if (checkAnswer(q.getAnswers(), answer)) {
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong!\n");
            }
        }
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public String getQuestionText() {
        return "";
    }

    @Override
    public List<Answer> getAnswers() {
        return List.of();
    }

    @Override
    public void print() {

    }
}