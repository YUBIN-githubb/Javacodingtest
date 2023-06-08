import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CodeSubmission extends JFrame {
    private JTextArea codeArea;
    private JTextArea problemArea;
    private JLabel resultLabel;

    private String username;
    private String question;

    public CodeSubmission(String username, String question) {
        this.username = username;
        this.question = question;

        setTitle("Question " + question.substring(question.length() - 1) + ". " + question);
        setSize(500, 500);
        setLayout(new BorderLayout());

        JPanel problemPanel = new JPanel();
        problemPanel.setLayout(new BorderLayout());

        problemArea = new JTextArea();
        problemArea.setEditable(false);
        problemArea.setLineWrap(true);
        problemArea.setWrapStyleWord(true);
        JScrollPane problemScrollPane = new JScrollPane(problemArea);
        problemPanel.add(problemScrollPane, BorderLayout.CENTER);

        add(problemPanel, BorderLayout.PAGE_START);

        codeArea = new JTextArea();
        add(new JScrollPane(codeArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        resultLabel = new JLabel();
        buttonPanel.add(resultLabel);

        add(buttonPanel, BorderLayout.PAGE_END);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                QuestionSelection questionSelection = new QuestionSelection(username);
                questionSelection.setVisible(true);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = codeArea.getText();
                boolean isCorrect = compareOutput(code, question);
                if (isCorrect) {
                    resultLabel.setIcon(new ImageIcon("circle.png"));
                } else {
                    resultLabel.setIcon(new ImageIcon("cross.png"));
                    resultLabel.setHorizontalAlignment(JLabel.CENTER);
                    resultLabel.setVerticalAlignment(JLabel.CENTER);
                    resultLabel.setHorizontalTextPosition(JLabel.CENTER);
                    resultLabel.setVerticalTextPosition(JLabel.CENTER);
                }
            }
        });

        // Load problem data
        ProblemData problemData = ProblemManager.getProblemData(question);
        if (problemData != null) {
            problemArea.setText(problemData.getTitle() + "\n\n" +
                    "Description:\n" + problemData.getDescription() + "\n\n" +
                    "Input:\n" + problemData.getInput() + "\n\n" +
                    "Output:\n" + problemData.getOutput());
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean compareOutput(String code, String question) {
        String expectedOutput = ProblemManager.getProblemData(question).getOutput();
        String codeOutput = executeCode(code);

        return expectedOutput.equals(codeOutput);
    }

    private String executeCode(String code) {
        String output = "";

        try {
            // Save code to a Java file
            String fileName = "CodeSubmission.java";
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(code);
            fileWriter.close();

            // Compile the Java file
            Process compileProcess = Runtime.getRuntime().exec("javac " + fileName);
            int compileResult = compileProcess.waitFor();

            if (compileResult == 0) {
                // Compilation successful, execute the Java program
                Process executeProcess = Runtime.getRuntime().exec("java CodeSubmission");
                BufferedReader reader = new BufferedReader(new InputStreamReader(executeProcess.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output += line + "\n";
                }

                reader.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return output;
    }
}
