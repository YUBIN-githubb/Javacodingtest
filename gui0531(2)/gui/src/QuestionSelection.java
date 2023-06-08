import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuestionSelection extends JFrame {
    private JLabel[] questionLabels;
    private JTextArea[] descriptionTextAreas;
    private JButton[] solveButtons;
    private JButton[] editButtons;
    private JButton logoutButton;
    private String[] questions;
    private String username;
    private CodeSubmission codeSubmission;

    public QuestionSelection(String username) {
        this.username = username;
        setTitle("Question Selection");
        setSize(630, 670);
        setLayout(null);

        questions = ProblemManager.getAllProblemTitles();

        int problemCount = questions.length;
        questionLabels = new JLabel[problemCount];
        descriptionTextAreas = new JTextArea[problemCount];
        solveButtons = new JButton[problemCount];
        editButtons = new JButton[problemCount];

        int yPosition = 30;
        int buttonWidth = 80;
        int buttonHeight = 30;
        int verticalSpacing = 10;

        for (int i = 0; i < problemCount; i++) {
            questionLabels[i] = new JLabel(questions[i]);
            questionLabels[i].setBounds(30, yPosition, 150, 30);
            add(questionLabels[i]);

            String problemDescription = ProblemManager.getProblemDescription(questions[i]);
            descriptionTextAreas[i] = new JTextArea(problemDescription);
            descriptionTextAreas[i].setBounds(117, yPosition, 350, 60);
            descriptionTextAreas[i].setEditable(false);
            add(descriptionTextAreas[i]);

            editButtons[i] = new JButton("Edit");
            editButtons[i].setBounds(550 - buttonWidth, yPosition + 60 + verticalSpacing, buttonWidth, buttonHeight);
            int editButtonIndex = i;
            editButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!AuthManager.isAdmin()) {
                        JOptionPane.showMessageDialog(QuestionSelection.this, "Only admin can access the question editing.");
                        return;
                    }

                    String question = questions[editButtonIndex];
                    ProblemEditor problemEditor = new ProblemEditor(question);
                    problemEditor.setVisible(true);
                }
            });
            add(editButtons[i]);

            solveButtons[i] = new JButton("Solve");
            solveButtons[i].setBounds(550 - buttonWidth * 2 - 10, yPosition + 60 + verticalSpacing, buttonWidth, buttonHeight);
            int buttonIndex = i;
            solveButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String question = questions[buttonIndex];
                    codeSubmission = new CodeSubmission(username, question);
                    codeSubmission.setVisible(true);
                    dispose();
                }
            });
            add(solveButtons[i]);

            yPosition += 60 + buttonHeight + verticalSpacing * 2;
        }
        
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, yPosition, buttonWidth, buttonHeight);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                CodingTestApp loginScreen = new CodingTestApp();
                loginScreen.setVisible(true);
            }
        });
        add(logoutButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
