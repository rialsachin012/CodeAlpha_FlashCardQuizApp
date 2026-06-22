import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Flashcard {
    String question;
    String answer;

    Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}

public class FlashCardQuizApp extends JFrame {

    private ArrayList<Flashcard> flashcards = new ArrayList<>();
    private int currentIndex = 0;

    private JLabel questionLabel;
    private JLabel answerLabel;

    public FlashCardQuizApp() {

        setTitle("Flashcard Quiz App");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        flashcards.add(new Flashcard(
                "What is Java?",
                "Java is an Object-Oriented Programming Language."));

        flashcards.add(new Flashcard(
                "What is JVM?",
                "JVM stands for Java Virtual Machine."));

        flashcards.add(new Flashcard(
                "What is OOP?",
                "Object-Oriented Programming."));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        answerLabel = new JLabel("", SwingConstants.CENTER);
        answerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton showAnswerBtn = new JButton("Show Answer");
        JButton nextBtn = new JButton("Next");
        JButton prevBtn = new JButton("Previous");
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        showAnswerBtn.addActionListener(e -> {
            answerLabel.setText(
                    "<html><center>" +
                            flashcards.get(currentIndex).answer +
                            "</center></html>");
        });

        nextBtn.addActionListener(e -> {
            if (currentIndex < flashcards.size() - 1) {
                currentIndex++;
                updateCard();
            }
        });

        prevBtn.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateCard();
            }
        });

        addBtn.addActionListener(e -> {
            String question = JOptionPane.showInputDialog("Enter Question:");
            String answer = JOptionPane.showInputDialog("Enter Answer:");

            if (question != null && answer != null &&
                    !question.isEmpty() && !answer.isEmpty()) {

                flashcards.add(new Flashcard(question, answer));
                currentIndex = flashcards.size() - 1;
                updateCard();
            }
        });

        editBtn.addActionListener(e -> {

            Flashcard card = flashcards.get(currentIndex);

            String newQuestion = JOptionPane.showInputDialog(
                    "Edit Question:", card.question);

            String newAnswer = JOptionPane.showInputDialog(
                    "Edit Answer:", card.answer);

            if (newQuestion != null && newAnswer != null) {
                card.question = newQuestion;
                card.answer = newAnswer;
                updateCard();
            }
        });

        deleteBtn.addActionListener(e -> {

            if (flashcards.size() > 1) {
                flashcards.remove(currentIndex);

                if (currentIndex >= flashcards.size()) {
                    currentIndex = flashcards.size() - 1;
                }

                updateCard();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "At least one flashcard must remain!");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(showAnswerBtn);

        JPanel managePanel = new JPanel();
        managePanel.add(addBtn);
        managePanel.add(editBtn);
        managePanel.add(deleteBtn);

        setLayout(new BorderLayout());

        add(questionLabel, BorderLayout.NORTH);
        add(answerLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(managePanel, BorderLayout.PAGE_END);

        updateCard();
        setVisible(true);
    }

    private void updateCard() {
        questionLabel.setText(
                "<html><center>" +
                        flashcards.get(currentIndex).question +
                        "</center></html>");

        answerLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlashCardQuizApp());
    }
}
