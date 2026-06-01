package view;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    public SidebarPanel(MainFrame mainFrame) {
        // 1. Cấu hình kích thước thanh Sidebar theo yêu cầu: Rộng 275px, Cao 910px
        setPreferredSize(new Dimension(275, 910));
        setBackground(new Color(0x121212)); 
        
        // PADDING CHO SIDEBARPANEL: Dọc 50px (Trên/Dưới), Ngang 40px (Trái/Phải)
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));
        
        // 2. Sử dụng GridBagLayout để kiểm soát chính xác vị trí các nút
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ép nút điền đầy khoảng ngang 195px còn lại
        gbc.insets = new Insets(0, 0, 12, 0);     // Khoảng cách 12px giữa các nút theo chiều dọc
    	
        // Sử dụng gbc.clone() để tạo bản sao riêng biệt cho từng nút, xóa bỏ cảnh báo gbc added more than once
    	JButton btnNewButton = new JButton("Trang chủ");
    	styleButton(btnNewButton);
    	btnNewButton.addActionListener(e -> mainFrame.switchInnerCard("PANEL_TRANG_CHU"));
    	add(btnNewButton, gbc.clone());
    	
    	JButton btnNewButton_1 = new JButton("Khách hàng");
    	styleButton(btnNewButton_1);
    	btnNewButton_1.addActionListener(e -> mainFrame.switchInnerCard("PANEL_KHACH_HANG"));
    	add(btnNewButton_1, gbc.clone());
    	
    	JButton btnNewButton_2 = new JButton("Nhân viên");
    	styleButton(btnNewButton_2);
    	btnNewButton_2.addActionListener(e -> mainFrame.switchInnerCard("PANEL_NHAN_VIEN"));
    	add(btnNewButton_2, gbc.clone());
    	
    	JButton btnNewButton_3 = new JButton("Bàn ăn");
    	styleButton(btnNewButton_3);
    	btnNewButton_3.addActionListener(e -> mainFrame.switchInnerCard("PANEL_BAN_AN"));
    	add(btnNewButton_3, gbc.clone());
    	
    	JButton btnNewButton_4 = new JButton("Đặt hàng");
    	styleButton(btnNewButton_4);
    	btnNewButton_4.addActionListener(e -> mainFrame.switchInnerCard("PANEL_DAT_HANG"));
    	add(btnNewButton_4, gbc.clone());
    	
    	// Nút cuối cùng cấu hình neo lên top, nhân bản riêng biệt
    	gbc.weighty = 1.0;
    	gbc.anchor = GridBagConstraints.NORTH;
    	JButton btnNewButton_5 = new JButton("Hóa đơn");
    	styleButton(btnNewButton_5);
    	btnNewButton_5.addActionListener(e -> mainFrame.switchInnerCard("PANEL_HOA_DON"));
    	add(btnNewButton_5, gbc.clone());
    }

    // Hàm custom nâng cao vẽ lại nút bo góc bất chấp mọi Look and Feel
    private void styleButton(JButton btn) {
        
        btn.setForeground(new Color(0xE0B988));        
        
        btn.setFont(new Font("Arial", Font.PLAIN, 20));  
        btn.setPreferredSize(new Dimension(195, 60));    
        btn.setFocusPainted(false);                    
        
        // Tắt tính năng vẽ đè của Windows Look and Feel
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);

        // Viết lại kiến trúc giao diện nút (Custom UI)
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                // Bật tính năng khử răng cưa để đường cong bo góc mượt mà
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                ButtonModel model = btn.getModel();
                if (model.isPressed()) {
                    g2.setColor(new Color(0x3B291E)); // Tối hơn khi click
                } else if (model.isRollover()) {
                    g2.setColor(new Color(0x5E4131)); // Sáng hơn một chút khi hover
                } else {
                    g2.setColor(new Color(0x4B3427)); // Giữ màu nền 4B3427
                }
                
                // Vẽ nền bo góc 16px
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 16, 16);
                
                g2.dispose();
                super.paint(g, c); // Vẽ đè nội dung chữ text (màu E0B988) lên trên nền vừa tô
            }
        });
    }
}