import java.util.List;

class SimpleQuestion implements Question {

    private final String questionText;
    private final List<Answer> answers;

    public SimpleQuestion(String questionText, List<Answer> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public void print() {
        System.out.println(questionText);
        for (int i = 0; i < answers.size(); i++) {
            System.out.println((i + 1) + ") " + answers.get(i).getText());
        }
    }

    @Override
    public boolean checkAnswer(int index) {
        if (index < 0 || index >= answers.size()) {
            return false;
        }
        return answers.get(index).isCorrect();
    }
}