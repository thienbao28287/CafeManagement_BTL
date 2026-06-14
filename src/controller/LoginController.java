package controller;

import javax.swing.JOptionPane;

import model.TaiKhoan;
import service.ITaiKhoanService;
import util.PermissionUtil;
import view.LoginPanel;
import view.MainFrame;

public class LoginController {

    private final LoginPanel loginPanel;
    private final ITaiKhoanService taiKhoanService;
    private final MainFrame mainFrame;

    public LoginController(
            LoginPanel loginPanel,
            ITaiKhoanService taiKhoanService,
            MainFrame mainFrame) {

        this.loginPanel = loginPanel;
        this.taiKhoanService = taiKhoanService;
        this.mainFrame = mainFrame;
    }

    /**
     * Xử lý đăng nhập
     */
    public void handleLogin(
            String username,
            String password) {

        try {

            // =========================
            // Kiểm tra dữ liệu đầu vào
            // =========================

            if (username == null || username.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        loginPanel,
                        "Vui lòng nhập tài khoản!",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            if (password == null || password.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        loginPanel,
                        "Vui lòng nhập mật khẩu!",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // =========================
            // Gọi Service đăng nhập
            // =========================

            TaiKhoan taiKhoan =
                    taiKhoanService.login(
                            username.trim(),
                            password.trim());

            // =========================
            // Đăng nhập thất bại
            // =========================

            if (taiKhoan == null) {

                JOptionPane.showMessageDialog(
                        loginPanel,
                        "Sai tài khoản hoặc mật khẩu!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            // =========================
            // Đăng nhập thành công
            // =========================

            JOptionPane.showMessageDialog(
                    loginPanel,
                    "Đăng nhập thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            // Cập nhật Sidebar theo quyền
            mainFrame.refreshSidebar();

            // Chuyển sang màn hình chính
            mainFrame.switchOuterCard("CARD_MAIN");

            // =========================
            // Điều hướng theo quyền
            // =========================

            if (PermissionUtil.isNhanVien()) {

                mainFrame.switchInnerCard(
                        "PANEL_BAN_AN");

            } else if (PermissionUtil.isAdmin()) {

                mainFrame.switchInnerCard(
                        "PANEL_TRANG_CHU");

            } else {

                mainFrame.switchInnerCard(
                        "PANEL_TRANG_CHU");
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    loginPanel,
                    "Có lỗi xảy ra khi đăng nhập!",
                    "Lỗi hệ thống",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Đăng xuất
     */
    public void handleLogout() {

        int result = JOptionPane.showConfirmDialog(
                loginPanel,
                "Bạn có muốn đăng xuất không?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {

            util.SessionUtil.logout();

            mainFrame.switchOuterCard("CARD_LOGIN");

            JOptionPane.showMessageDialog(
                    loginPanel,
                    "Đăng xuất thành công!");
        }
    }
}