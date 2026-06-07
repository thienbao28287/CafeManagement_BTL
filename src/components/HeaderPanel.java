package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {
    // Các biến lưu màu
    private Color colorStart;
    private Color colorMid;
    private Color colorEnd;

    // Constructor mới: nhận thêm 3 màu sắc
    public HeaderPanel(String title, String description, Color c1, Color c2, Color c3) {
        this.colorStart = c1;
        this.colorMid = c2;
        this.colorEnd = c3;

        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 90));
        setBorder(new EmptyBorder(16, 24, 16, 24));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel lblDescription = new JLabel(description);
        lblDescription.setForeground(new Color(253, 230, 138));
        lblDescription.setFont(new Font("SansSerif", Font.PLAIN, 14));

        textPanel.add(lblTitle);
        textPanel.add(Box.createVerticalStrut(2));
        textPanel.add(lblDescription);

        add(textPanel, BorderLayout.WEST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        
        // Sử dụng các biến màu đã lưu
        LinearGradientPaint gradient = new LinearGradientPaint(
                0, 0, w, h,
                new float[]{0f, 0.6f, 1f},
                new Color[]{colorStart, colorMid, colorEnd}
        );
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, w, h, 20, 20);
        g2.dispose();
        super.paintComponent(g);
    }
}