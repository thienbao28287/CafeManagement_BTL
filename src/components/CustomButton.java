package components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    private static final long serialVersionUID = 1L;

    // Định nghĩa màu sắc
    private final Color normalBg = new Color(254, 249, 238);
    private final Color normalText = new Color(146, 64, 14);
    private final Color hoverBg = new Color(146, 64, 14);
    private final Color hoverText = Color.WHITE;

    // Các chuỗi cấu hình FlatLaf
    private final String normalStyle = "arc:12; margin:8,12,8,12; background:#FEF9EE; foreground:#92400E; borderColor:#FDE9C0";
    private final String hoverStyle = "arc:12; margin:8,12,8,12; background:#92400E; foreground:#FFFFFF; borderColor:#D97706";

    public CustomButton(String text) {
        super(text);

        // Thiết lập cơ bản
        setFont(new Font("SansSerif", Font.BOLD, 12));
        setPreferredSize(new Dimension(100, 34));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusPainted(false);
        setBackground(normalBg);
        setForeground(normalText);

        // Áp dụng style mặc định
        putClientProperty(FlatClientProperties.STYLE, normalStyle);

        // Xử lý sự kiện di chuột
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBg);
                setForeground(hoverText);
                putClientProperty(FlatClientProperties.STYLE, hoverStyle);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalBg);
                setForeground(normalText);
                putClientProperty(FlatClientProperties.STYLE, normalStyle);
                repaint();
            }
        });
    }
}