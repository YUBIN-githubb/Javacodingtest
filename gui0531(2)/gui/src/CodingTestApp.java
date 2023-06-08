import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CodingTestApp {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private JLabel titleLabel,idLabel,pwLabel;
    private Map<String, String> users;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CodingTestApp window = new CodingTestApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public CodingTestApp() {
        users = new HashMap<>();
        loadUsers();

        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Java Coding Test");
        
        idLabel = new JLabel("ID : ");
        pwLabel = new JLabel("PW : ");
        idLabel.setBounds(40, 50, 50, 20);
        pwLabel.setBounds(40, 80, 50, 20);
        frame.getContentPane().add(idLabel);
        frame.getContentPane().add(pwLabel);
        
        titleLabel = new JLabel("Java Coding Test");
        frame.getContentPane().add(titleLabel);
        titleLabel.setFont(new Font("Arial",Font.PLAIN,25));
        titleLabel.setBounds(55, 10, 200, 30);

        usernameField = new JTextField();
        usernameField.setBounds(100, 50, 100, 20);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 80, 100, 20);
        frame.getContentPane().add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 80, 20);
        frame.getContentPane().add(loginButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 133, 80, 20);
        frame.getContentPane().add(signUpButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (users.containsKey(username)) {
                    String savedPassword = users.get(username);
                    if (password.equals(savedPassword)) {
                        // Login successful
                        JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        if (username.equals("admin")) {
                            AdminQuestionManagement adminQuestionManagement = new AdminQuestionManagement();
                            adminQuestionManagement.setVisible(true);
                        } else {
                            QuestionSelection questionSelection = new QuestionSelection(username);
                            questionSelection.setVisible(true);
                        }
                    } else {
                        // Incorrect password
                        JOptionPane.showMessageDialog(frame, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // User not found
                    JOptionPane.showMessageDialog(frame, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (users.containsKey(username)) {
                    // User already exists
                    JOptionPane.showMessageDialog(frame, "User already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    users.put(username, password);
                    saveUsers();
                    JOptionPane.showMessageDialog(frame, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void loadUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String username = parts[0];
                    String password = parts[1];
                    users.put(username, password);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsers() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
