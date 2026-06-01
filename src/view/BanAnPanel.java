package view;
import javax.swing.*;
import java.awt.*;

public class BanAnPanel extends JPanel {
    public BanAnPanel() {
        setBackground(new Color(50, 35, 25));
        setLayout(new BorderLayout());
        JLabel label = new JLabel("GIAO DIỆN QUẢN LÝ BÀN ĂN", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(210, 180, 140));
        add(label, BorderLayout.CENTER);
    }
}