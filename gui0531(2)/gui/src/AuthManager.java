public class AuthManager {
    private static String loggedInUser = null;

    public static void login(String userId) {
        loggedInUser = userId;
    }

    public static void logout() {
        loggedInUser = null;
    }

    public static boolean isAdmin() {
        return "admin".equals(loggedInUser);
    }
}
