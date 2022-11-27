import javax.swing.*;

public class Game {
    private JPanel GamePanel;
    private JPanel GuessPanel;
    private JPanel ButtonPanel;
    private JPanel ConstraintPanel;
    private JPanel ScorePanel;
    private JTextField guessField;
    private JButton guessNumberButton;
    private JSpinner minSpinner;
    private JSpinner maxSpinner;
    private JLabel scoreLabel;
    private JButton resetButton;
    private int guessNumber;
    private int secretNumber;
    private int score;

    public Game() {
        JFrame frame = new JFrame("Guess The Number!");
        frame.setContentPane(GamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        reset();

        guessField.addActionListener(e -> checkGuess(checkInput(frame), frame));
        guessNumberButton.addActionListener(e -> checkGuess(checkInput(frame), frame));

        minSpinner.addChangeListener(e -> {
            checkConstraints(frame);
            generateNum();
        });
        maxSpinner.addChangeListener(e -> {
            checkConstraints(frame);
            generateNum();
        });
        resetButton.addActionListener(e -> reset());
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void generateNum() {
        int min = (int) minSpinner.getValue();
        int max = (int) maxSpinner.getValue();

        // Gets a random number within a specific range
        secretNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public int checkInput(JFrame frame) {
        try {
            guessNumber = Integer.parseInt(guessField.getText());
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(frame,
                    "Input is not a number! Try again.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return guessNumber;
    }

    public void checkGuess(int guess, JFrame frame) {
        if (guess > secretNumber) {
            JOptionPane.showMessageDialog(frame,
                    "Lower!",
                    "Hint",
                    JOptionPane.WARNING_MESSAGE);
        } else if (guess < secretNumber) {
            JOptionPane.showMessageDialog(frame,
                    "Higher!",
                    "Hint",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame,
                    "That's correct!");
            generateNum();
            setScore(++score);
        }
    }

    public void checkConstraints(JFrame frame) {
        if (maxSpinner.getValue() == minSpinner.getValue()) {
            JOptionPane.showMessageDialog(frame,
                    "Constraints can't be the same.",
                    "Cheating Alert",
                    JOptionPane.ERROR_MESSAGE);
            minSpinner.setValue(minSpinner.getPreviousValue());
        }
    }

    public void reset() {
        guessField.setText("");
        score = 0;
        setScore(score);
        minSpinner.setValue(1);
        maxSpinner.setValue(100);
        generateNum();
    }
}
