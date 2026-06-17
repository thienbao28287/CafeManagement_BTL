
package view;

import javax.swing.*;
import java.awt.*;
import controller.LoginController;

public class LoginPanel extends JPanel {
    
    private LoginController controller; 
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginPanel(MainFrame mainFrame) {
        setBackground(new Color(43, 29, 20));
        setLayout(new GridBagLayout()); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Nhãn & Ô nhập Tài khoản
        JLabel lblUsername = new JLabel("TÀI KHOẢN:");
        lblUsername.setForeground(new Color(210, 180, 140));
        lblUsername.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblUsername, gbc);

        gbc.gridy = 1;
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        txtUsername.setPreferredSize(new Dimension(250, 35));
        add(txtUsername, gbc);

        // 2. Nhãn & Ô nhập Mật khẩu
        gbc.gridy = 2;
        JLabel lblPassword = new JLabel("MẬT KHẨU:");
        lblPassword.setForeground(new Color(210, 180, 140));
        lblPassword.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblPassword, gbc);

        gbc.gridy = 3;
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        txtPassword.setPreferredSize(new Dimension(250, 35));
        add(txtPassword, gbc);

        // 3. Nút bấm Đăng nhập
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 10, 10, 10);
        btnLogin = new JButton("ĐĂNG NHẬP HỆ THỐNG");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setPreferredSize(new Dimension(250, 45));
        btnLogin.setBackground(new Color(210, 180, 140));
        btnLogin.setForeground(new Color(43, 29, 20));
        btnLogin.setFocusPainted(false);

        // 🌟 SỰ KIỆN: Ủy quyền toàn bộ xử lý logic cho Controller khi click nút
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            
            if (this.controller != null) {
                // Đẩy sang cho Controller xử lý kiểm tra
                this.controller.handleLogin(username, password);
            } else {
                System.err.println("Lỗi: Chưa gán Controller cho LoginPanel!");
            }
        });

        add(btnLogin, gbc);
    }
    

    // Hàm nhận Controller từ tầng Main truyền vào (Dependency Injection)
    public void setController(LoginController controller) {
        this.controller = controller;
    }
}