import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminQuestionManagement extends JFrame {
    private JTextArea problemArea;
    private JButton saveButton;

    public AdminQuestionManagement() {
        setTitle("Admin Question Management");
        setSize(600, 400);
        setLayout(new BorderLayout());

        problemArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(problemArea);
        add(scrollPane, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String problemData = problemArea.getText();
                ProblemManager.saveProblemData(problemData);
                JOptionPane.showMessageDialog(AdminQuestionManagement.this, "Problem data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(saveButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
