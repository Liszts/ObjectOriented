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
}