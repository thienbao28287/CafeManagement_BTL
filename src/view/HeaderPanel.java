package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HeaderPanel extends JPanel {
    
    public HeaderPanel(MainFrame mainFrame) {
        // Đồng bộ màu nền với LoginPanel
        setBackground(new Color(43, 29, 20)); 
        setPreferredSize(new Dimension(1200, 70));
        
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 140));
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 40, 10, 40);
        setBorder(BorderFactory.createCompoundBorder(bottomBorder, emptyBorder));
        
        setLayout(new GridBagLayout());

        // --- PANEL LOGO ---
        JPanel logoPanel = new JPanel(new BorderLayout(15, 0));
        logoPanel.setBackground(new Color(43, 29, 20));
        
        // Đảm bảo đường dẫn ảnh /img/logo.png tồn tại trong source folder
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/img/logo.png")));
        logoPanel.add(logoLabel, BorderLayout.WEST);
        
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        textPanel.setBackground(new Color(43, 29, 20));
        
        JLabel title = new JLabel("COFFEE SHOP");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(210, 180, 140));
        textPanel.add(title);
        
        JLabel subTitle = new JLabel("MANAGEMENT SYSTEM");
        subTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subTitle.setForeground(new Color(180, 150, 120));
        textPanel.add(subTitle);
        
        logoPanel.add(textPanel, BorderLayout.CENTER);
        
        // --- PANEL XIN CHÀO & ĐĂNG XUẤT ---
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        actionPanel.setBackground(new Color(43, 29, 20));
        
        JLabel lblHello = new JLabel("Xin chào, A");
        lblHello.setFont(new Font("Arial", Font.ITALIC, 14));
        lblHello.setForeground(new Color(210, 180, 140));
        
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 12));
        btnLogout.setBackground(new Color(210, 180, 140));
        btnLogout.setForeground(new Color(43, 29, 20));
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // SỰ KIỆN ĐĂNG XUẤT
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // 1. Đóng màn hình quản trị hiện tại
                mainFrame.dispose(); 
                
                // 2. Tạo JFrame mới để hiển thị LoginPanel mà không cần tạo file LoginForm riêng
                JFrame loginFrame = new JFrame("Đăng nhập hệ thống");
                loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginFrame.setSize(400, 500);
                loginFrame.setLocationRelativeTo(null);
                
                // 3. Khởi tạo LoginPanel (Truyền null vì màn hình Login không cần MainFrame)
                LoginPanel loginPanel = new LoginPanel(null);
                
                loginFrame.add(loginPanel);
                loginFrame.setVisible(true);
            }
        });

        actionPanel.add(lblHello);
        actionPanel.add(btnLogout);

        // --- GẮN VÀO HEADER ---
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        add(logoPanel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        add(actionPanel, gbc);
    }
}