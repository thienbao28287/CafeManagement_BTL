package view;
import javax.swing.*;
import java.awt.*;

public class NhanVienPanel extends JPanel {
    public NhanVienPanel() {
        setBackground(new Color(55, 38, 27));
        setLayout(new BorderLayout());
        JLabel label = new JLabel("GIAO DIỆN QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(210, 180, 140));
        add(label, BorderLayout.CENTER);
    }
}