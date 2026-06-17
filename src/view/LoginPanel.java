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

        setLayout(new BorderLayout());

        // =========================
        // PANEL ẢNH BÊN TRÁI
        // =========================
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(500, 0));

        JLabel lblImage = new JLabel(
                new ImageIcon(
                        getClass().getResource("/img/staburk2.png")
                )
        );

        lblImage.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(lblImage, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

        // =========================
        // PANEL FORM BÊN PHẢI
        // =========================
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(0xF5EBD7));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel lblTitle = new JLabel(
                "CHÚC BẠN MỘT NGÀY TỐT LÀNH",
                SwingConstants.CENTER
        );

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0x2B1D14));

        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 25, 20);
        rightPanel.add(lblTitle, gbc);

        // =========================
        // TÀI KHOẢN
        // =========================
        JLabel lblUsername = new JLabel("TÀI KHOẢN");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUsername.setForeground(new Color(0x7A6A58));

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 0, 20);
        rightPanel.add(lblUsername, gbc);

        txtUsername = new JTextField(20);
        txtUsername.setPreferredSize(new Dimension(300, 42));

        gbc.gridy = 2;
        gbc.insets = new Insets(8, 20, 12, 20);
        rightPanel.add(txtUsername, gbc);

        // =========================
        // MẬT KHẨU
        // =========================
        JLabel lblPassword = new JLabel("MẬT KHẨU");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPassword.setForeground(new Color(0x7A6A58));

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 20, 0, 20);
        rightPanel.add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        txtPassword.setPreferredSize(new Dimension(300, 42));

        gbc.gridy = 4;
        gbc.insets = new Insets(8, 20, 20, 20);
        rightPanel.add(txtPassword, gbc);

        // =========================
        // NÚT ĐĂNG NHẬP
        // =========================
        btnLogin = new JButton("ĐĂNG NHẬP");

        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogin.setPreferredSize(new Dimension(300, 48));

        btnLogin.setBackground(new Color(0x5C230F));
        btnLogin.setForeground(Color.WHITE);

        btnLogin.setFocusPainted(false);

        gbc.gridy = 5;
        gbc.insets = new Insets(15, 20, 10, 20);
        rightPanel.add(btnLogin, gbc);

        add(rightPanel, BorderLayout.CENTER);

        // =========================
        // SỰ KIỆN ĐĂNG NHẬP
        // =========================
        btnLogin.addActionListener(e -> {

            String username = txtUsername.getText().trim();
            String password = new String(
                    txtPassword.getPassword()
            ).trim();

            if (controller != null) {
                controller.handleLogin(username, password);
            }
        });
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }
}