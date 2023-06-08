import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProblemEditor extends JFrame {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton saveButton;

    public ProblemEditor(String question) {
        setTitle("Problem Editor");
        setSize(600, 800);
        setLayout(new BorderLayout());

        titleField = new JTextField();
        descriptionArea = new JTextArea();
        inputArea = new JTextArea();
        outputArea = new JTextArea();
        saveButton = new JButton("Save");

        // Add components to the frame...

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
