package view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(MainFrame mainFrame) {
        // Đồng bộ màu nâu tối cho quán cafe
        setBackground(new Color(43, 29, 20));
        setLayout(new GridBagLayout()); // Căn giữa nút bấm

        JButton btnLogin = new JButton("ĐĂNG NHẬP HỆ THỐNG");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setPreferredSize(new Dimension(250, 50));
        btnLogin.setBackground(new Color(210, 180, 140));
        btnLogin.setForeground(new Color(43, 29, 20));

        // SỰ KIỆN: Bấm nút -> Gọi MainFrame lật sang giao diện chính
        btnLogin.addActionListener(e -> mainFrame.switchOuterCard("CARD_MAIN"));

        add(btnLogin);
    }
}