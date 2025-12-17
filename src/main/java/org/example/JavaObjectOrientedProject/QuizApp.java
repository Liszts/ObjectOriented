package org.example.JavaObjectOrientedProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuizApp extends JFrame {
    private QuizImpl quiz = new QuizImpl("Untitled Quiz");
    private QuizManager manager = new QuizManager();

    // UI components
    private final JLabel titleLabel = new JLabel("Quiz: " + quiz.getTitle());
    private final JButton loadBtn = new JButton("Load Quiz");
    private final JButton saveBtn = new JButton("Save Quiz");
    private final JButton addSampleBtn = new JButton("Add Sample Question");
    private final JButton startBtn = new JButton("Start Quiz");
    private final JPanel mainPanel = new JPanel(new BorderLayout());

    public QuizApp() {
        super("Simple Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        JPanel top = new JPanel();
        top.add(titleLabel);
        top.add(loadBtn);
        top.add(saveBtn);
        top.add(addSampleBtn);
        top.add(startBtn);

        mainPanel.add(top, BorderLayout.NORTH);
        add(mainPanel);

        // Button actions
        loadBtn.addActionListener(this::onLoad);
        saveBtn.addActionListener(this::onSave);
        addSampleBtn.addActionListener(e -> addSampleQuestion());
        startBtn.addActionListener(e -> startQuiz());
    }
      // burasi json yukleme bolumu    burasi swing
    private void onLoad(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int ret = fc.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            try { //dosya secilmesi istenir kullanicidan daha sonra jsondan quiz objecesi olusur
                quiz = manager.loadQuiz(f.getAbsolutePath());
                titleLabel.setText("Quiz: " + quiz.getTitle());
                JOptionPane.showMessageDialog(this, "Loaded quiz with " + quiz.getQuestions().size() + " questions.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to load: " + ex.getMessage());
            }
        }
    }
          // burasi kayit  etme bolumu dikkat
    private void onSave(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int ret = fc.showSaveDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            try {
                manager.saveQuiz(quiz, f.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Saved quiz to: " + f.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save: " + ex.getMessage());
            }
        }
    }
     //ornek soru ekleme bolumu
    private void addSampleQuestion() {
        SimpleQuestion q = new SimpleQuestion("What is the capital of France?");
        q.addAnswer(new SimpleAnswer("Paris", true));
        q.addAnswer(new SimpleAnswer("Berlin", false));
        q.addAnswer(new SimpleAnswer("Madrid", false));
        quiz.addQuestion(q);
        titleLabel.setText("Quiz: " + quiz.getTitle() + " (" + quiz.getQuestions().size() + " questions)");
    }

    private void startQuiz() {
        List<SimpleQuestion> questions = quiz.getQuestions();
        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions - add some or load a quiz first.");
            return;
        }
                         //burada  jframe donmasin diye thread olusturdum
        new Thread(() -> runQuiz(questions)).start();
    }
          //her soru icin bir oencere acilir
    private void runQuiz(List<SimpleQuestion> questions) {
        int score = 0;
        for (SimpleQuestion q : questions) {
            String chosenId = askQuestionDialog(q);
            if (chosenId == null) break; // user cancelled
            if (q.isCorrectAnswer(chosenId)) score++;
        }
        JOptionPane.showMessageDialog(this, "Quiz finished! Score: " + score + " / " + questions.size());
    }
           //diyalog icin pencere aciliyor
    private String askQuestionDialog(SimpleQuestion q) {
        JDialog dlg = new JDialog(this, "Question", true);
        dlg.setLayout(new BorderLayout());
        dlg.setSize(500, 300);
            //soru metni
        JLabel qLabel = new JLabel(q.getText());
        dlg.add(qLabel, BorderLayout.NORTH);
        // cgruplamak icin bi tane secim yapilabilir
        ButtonGroup bg = new ButtonGroup();
        JPanel answersPanel = new JPanel();
        answersPanel.setLayout(new BoxLayout(answersPanel, BoxLayout.Y_AXIS));
                     // cevaplarin liste halinde gosterilmesi alt  kisimda
        for (SimpleAnswer a : q.getAnswers()) {
            JRadioButton rb = new JRadioButton(a.getText());
            rb.setActionCommand(a.getId());
            bg.add(rb);
            answersPanel.add(rb);
        }

        dlg.add(new JScrollPane(answersPanel), BorderLayout.CENTER);
            // cancel panel kismi
        JPanel bottom = new JPanel();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        bottom.add(ok);
        bottom.add(cancel);
        dlg.add(bottom, BorderLayout.SOUTH);

        final String[] chosen = {null};
        ok.addActionListener(ev -> {
            ButtonModel sel = bg.getSelection(); // to get answer
            if (sel != null) chosen[0] = sel.getActionCommand();
            dlg.dispose();
        });
        cancel.addActionListener(ev -> {
            chosen[0] = null;
            dlg.dispose();
        });

        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
        return chosen[0];
    }
}