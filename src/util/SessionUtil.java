package util;

import model.TaiKhoan;

public class SessionUtil {
    // Biến static lưu trữ thông tin phiên làm việc của người dùng hiện tại
    private static TaiKhoan currentUser = null;

    public static void setCurrentUser(TaiKhoan taiKhoan) {
        currentUser = taiKhoan;
    }

    public static TaiKhoan getCurrentUser() {
        return currentUser;
    }

    public static void clearSession() {
        currentUser = null; // Dùng khi bấm nút Đăng xuất (Logout)
    }
}