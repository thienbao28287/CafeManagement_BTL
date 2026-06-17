package components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardBanAnPanel extends JPanel {

    // Các hằng số cấu hình UI
    private static final int CARD_ARC = 16;
    private static final int SHADOW_SIZE = 6;
    private static final Color BG_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(50, 50, 50);

    private JLabel lblTong, lblTrong, lblDangDung, lblDaDat;

    public DashboardBanAnPanel() {
        initComponents();
    }

    private void initComponents() {
        setOpaque(false);
        setLayout(new BorderLayout());

        // Tạo Panel chính chứa thống kê
        JPanel card = createCard();
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.setPreferredSize(new Dimension(250, 0));

        // Panel tiêu đề
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        JLabel title = new JLabel("Thống kê");
        title.putClientProperty(FlatClientProperties.STYLE, "font: bold 15; foreground: #323232");
        titlePanel.add(title);

        // Panel chứa các mục thống kê
        JPanel statsPanel = new JPanel(new GridLayout(4, 1, 12, 12));
        statsPanel.setOpaque(false);

        lblTong = createItemLabel("Tổng bàn: 0", "/img/banBlack.png");
        lblTrong = createItemLabel("Bàn trống: 0", "/img/banBlack.png");
        lblDangDung = createItemLabel("Đang dùng: 0", "/img/banBlack.png");
        lblDaDat = createItemLabel("Đã đặt: 0", "/img/banBlack.png");

        statsPanel.add(lblTong);
        statsPanel.add(lblTrong);
        statsPanel.add(lblDangDung);
        statsPanel.add(lblDaDat);

        card.add(titlePanel, BorderLayout.NORTH);
        card.add(statsPanel, BorderLayout.CENTER);
        add(card, BorderLayout.CENTER);
    }

    private JLabel createItemLabel(String text, String iconPath) {
        JLabel lbl = new JLabel(text);
        lbl.putClientProperty(FlatClientProperties.STYLE, "font: bold 12; foreground: #323232");
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lbl.setIcon(new ImageIcon(img));
            lbl.setIconTextGap(12);
        } catch (Exception e) {
            // Log lỗi nếu không tìm thấy icon
        }

        lbl.setOpaque(true);
        lbl.setBackground(BG_COLOR);
        lbl.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        return lbl;
    }

    private JPanel createCard() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Vẽ hiệu ứng đổ bóng
                for (int i = 1; i <= SHADOW_SIZE; i++) {
                    float alpha = (float) (SHADOW_SIZE - i + 1) / (SHADOW_SIZE * 40);
                    g2.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
                    g2.fillRoundRect(SHADOW_SIZE - i, SHADOW_SIZE - i, 
                                     getWidth() - (SHADOW_SIZE - i) * 2, 
                                     getHeight() - (SHADOW_SIZE - i) * 2, CARD_ARC, CARD_ARC);
                }
                
                // Vẽ thân card
                g2.setColor(BG_COLOR);
                g2.fillRoundRect(SHADOW_SIZE, SHADOW_SIZE, 
                                 getWidth() - SHADOW_SIZE * 2, 
                                 getHeight() - SHADOW_SIZE * 2, CARD_ARC, CARD_ARC);
                g2.dispose();
                super.paintComponent(g);
            }
        };
    }

    public void updateDashboard(int tong, int trong, int dangDung, int daDat) {
        lblTong.setText("Tổng bàn: " + tong);
        lblTrong.setText("Bàn trống: " + trong);
        lblDangDung.setText("Đang dùng: " + dangDung);
        lblDaDat.setText("Đã đặt: " + daDat);
    }
}