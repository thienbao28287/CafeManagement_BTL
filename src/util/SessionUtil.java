package util;

import model.TaiKhoan;

public class SessionUtil {

    private static TaiKhoan currentUser;

    private SessionUtil() {
    }

    public static TaiKhoan getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(TaiKhoan currentUser) {
        SessionUtil.currentUser = currentUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLogin() {
        return currentUser != null;
    }
}