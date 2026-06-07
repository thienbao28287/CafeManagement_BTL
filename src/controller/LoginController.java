package controller;

import service.ITaiKhoanService;
import view.LoginPanel;
import view.MainFrame;
import javax.swing.JOptionPane;

public class LoginController {
    private final LoginPanel loginPanel;
    private final ITaiKhoanService taiKhoanService;
    private final MainFrame mainFrame;

    // Constructor nhận đầy đủ 3 thành phần để dễ dàng điều hướng liên kết liên tầng
    public LoginController(LoginPanel loginPanel, ITaiKhoanService taiKhoanService, MainFrame mainFrame) {
        this.loginPanel = loginPanel;
        this.taiKhoanService = taiKhoanService;
        this.mainFrame = mainFrame;
    }

    public void handleLogin(String username, String password) {
        // Kiểm tra nhanh tính hợp lệ dữ liệu ở tầng điều khiển
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginPanel, "Vui lòng nhập đầy đủ tài khoản và mật khẩu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Gọi Service kiểm tra xuống tầng Repository tương tác database
        boolean success = taiKhoanService.login(username, password);
        
        if (success) {
            JOptionPane.showMessageDialog(loginPanel, "Đăng nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            
           //Ra lệnh cho MainFrame cập nhật thanh Sidebar theo quyền vừa nhận
            mainFrame.refreshSidebar(); 
            
            // Lật trang từ màn hình Đăng nhập sang màn hình chính tổng quan
            mainFrame.switchOuterCard("CARD_MAIN"); 
        } else {
            JOptionPane.showMessageDialog(loginPanel, "Tài khoản hoặc mật khẩu không chính xác!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
        }
    }
}