package util;

public class UserSession {
    private static String loggedInUserName;

    public static void setLoggedInUserName(String name) {
        loggedInUserName = name;
    }

    public static String getLoggedInUserName() {
        return (loggedInUserName != null && !loggedInUserName.isEmpty()) ? loggedInUserName : "Khách";
    }
}