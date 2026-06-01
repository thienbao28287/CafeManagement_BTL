package view;
import javax.swing.*;
import java.awt.*;

public class DatHangPanel extends JPanel {
    public DatHangPanel() {
        setBackground(new Color(55, 38, 27));
        setLayout(new BorderLayout());
        JLabel label = new JLabel("GIAO DIỆN ĐẶT HÀNG (ORDER)", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(210, 180, 140));
        add(label, BorderLayout.CENTER);
    }
}