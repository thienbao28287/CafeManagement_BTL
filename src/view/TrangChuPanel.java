package view;
import javax.swing.*;
import java.awt.*;

public class TrangChuPanel extends JPanel {
    public TrangChuPanel() {
        setBackground(new Color(55, 38, 27));
        setLayout(new BorderLayout());
        JLabel label = new JLabel("TỔNG QUAN HỆ THỐNG (TRANG CHỦ)", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(210, 180, 140));
        add(label, BorderLayout.CENTER);
    }
}