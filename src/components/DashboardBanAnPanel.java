package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;

public class DashboardBanAnPanel extends JPanel {
    private JLabel lblTong, lblTrong, lblDangDung, lblDaDat;

    public DashboardBanAnPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel card = createCard();
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.setPreferredSize(new Dimension(250, 0));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Căn giữa
        titlePanel.setOpaque(false); // Đảm bảo trong suốt để giữ style card
        JLabel title = new JLabel("Thống kê");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.putClientProperty(FlatClientProperties.STYLE, "font: bold 15; foreground: #323232");
        titlePanel.add(title); // Thêm nhãn vào panel căn giữa

        card.add(titlePanel, BorderLayout.NORTH); // Thêm panel vào card

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
        }

        lbl.setOpaque(true);
        lbl.setBackground(new Color(255, 255, 255));
        
        // Bỏ viền và thêm khoảng cách (padding)
        lbl.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        return lbl;
    }

    public void updateDashboard(int tong, int trong, int dangDung, int daDat) {
        lblTong.setText("Tổng bàn: " + tong);
        lblTrong.setText("Bàn trống: " + trong);
        lblDangDung.setText("Đang dùng: " + dangDung);
        lblDaDat.setText("Đã đặt: " + daDat);
    }

    private JPanel createCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 16, shadowSize = 6;
                for (int i = 1; i <= shadowSize; i++) {
                    float alpha = (float) (shadowSize - i + 1) / (shadowSize * 40);
                    g2.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
                    g2.fillRoundRect(shadowSize - i, shadowSize - i, getWidth() - (shadowSize - i) * 2, getHeight() - (shadowSize - i) * 2, arc, arc);
                }
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(6, 6, 6, 6));
        return card;
    }
}