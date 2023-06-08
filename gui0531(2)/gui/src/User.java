import java.util.HashMap;
import java.util.Map;

public class User {
    private static Map<String, User> userMap = new HashMap<>();

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static void addUser(String username, String password) {
        User user = new User(username, password);
        userMap.put(username, user);
    }

    public static User getUser(String username) {
        return userMap.get(username);
    }

    public boolean authenticateUser(String password) {
        return this.password.equals(password);
    }
}
