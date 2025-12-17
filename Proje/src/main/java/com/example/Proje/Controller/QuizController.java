package com.example.Proje.Controller;

import com.example.Proje.Models.Answer;
import com.example.Proje.Models.Question;
import com.example.Proje.Models.Quiz;
import com.example.Proje.Repository.QuizRepository;
import com.example.Proje.Service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/quizzes")
public class QuizController {


    private final QuizService quizService;

    private final QuizRepository quizRepository;



    public QuizController(QuizService quizService, QuizRepository quizRepository) {
        this.quizService = quizService;
        this.quizRepository = quizRepository;
    }


    @GetMapping
    public String quizList(Model model) {
        model.addAttribute("quizzes", quizService.findAll());
        return "quiz-list";
    }


    @GetMapping("/{id}")
    public String takeQuiz(@PathVariable Long id, Model model) {
        model.addAttribute("quiz", quizService.findById(id));
        return "quiz";
    }


    @PostMapping("/{id}")
    public String submitQuiz(@PathVariable Long id, @RequestParam Map<String, String> answers, Model model) {
        Quiz quiz = quizService.findById(id);
        int score = 0;


        for (Question q : quiz.getQuestions()) {
            String chosen = answers.get("question_" + q.getId());
            for (Answer a : q.getAnswers()) {
                if (a.isCorrect() && a.getId().toString().equals(chosen)) {
                    score++;
                }
            }
        }


        model.addAttribute("score", score);
        model.addAttribute("total", quiz.getQuestions().size());
        return "result";
    }


    @GetMapping("/new")
    public String showQuizForm(Model model) {
        Quiz quiz = new Quiz();

        // initialize one empty question with 4 empty answers
        Question q = new Question();
        for (int i = 0; i < 4; i++) {
            q.getAnswers().add(new Answer());
        }
        quiz.getQuestions().add(q);

        model.addAttribute("quiz", quiz);
        return "add-quiz";
    }

    @GetMapping("/quiz-created")
    public String quizCreated() {
        return "quiz-created";
    }


    // 2️⃣ Handle quiz form submission
    @PostMapping
    public String saveQuiz(@ModelAttribute Quiz quiz) {
// Ensure each question has answers linked correctly
        for (Question question : quiz.getQuestions()) {
            question.setQuiz(quiz);
            int correctIndex = question.getCorrect(); // integer from radio button
            for (int i = 0; i < question.getAnswers().size(); i++) {
                Answer answer = question.getAnswers().get(i);
                answer.setQuestion(question);
                answer.setCorrect(i == correctIndex);
            }
        }
        quizRepository.save(quiz);
        return "redirect:/quizzes/quiz-created";
    }
}