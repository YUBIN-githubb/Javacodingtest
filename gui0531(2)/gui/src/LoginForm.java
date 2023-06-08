import javax.swing.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private JTextField userIdField;
    private JPasswordField passwordField;

    public LoginForm() {
        // Initialize components...

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());

                if ("admin".equals(userId) && "admin".equals(password)) {
                    // If login succeeds
                    AuthManager.login(userId);

                    // Navigate to QuestionSelection page
                    QuestionSelection questionSelection = new QuestionSelection(userId);
                    questionSelection.setVisible(true);
                } else {
                    // If login fails
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password.");
                }
            }
        });
    }
}
