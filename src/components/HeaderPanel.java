package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // Cấu hình hằng số
    private static final int CORNER_RADIUS = 20;
    private static final int PREFERRED_HEIGHT = 90;
    private static final float[] GRADIENT_FRACTIONS = {0f, 0.6f, 1f};

    private final Color colorStart;
    private final Color colorMid;
    private final Color colorEnd;

    public HeaderPanel(String title, String description, Color c1, Color c2, Color c3) {
        this.colorStart = c1;
        this.colorMid = c2;
        this.colorEnd = c3;
        
        initComponents(title, description);
    }

    private void initComponents(String title, String description) {
        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, PREFERRED_HEIGHT));
        setBorder(new EmptyBorder(16, 24, 16, 24));

        // Khởi tạo phần hiển thị chữ
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

        // Tạo gradient và vẽ nền bo góc
        LinearGradientPaint gradient = new LinearGradientPaint(
                0, 0, w, h,
                GRADIENT_FRACTIONS,
                new Color[]{colorStart, colorMid, colorEnd}
        );
        
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, w, h, CORNER_RADIUS, CORNER_RADIUS);
        
        g2.dispose();
        super.paintComponent(g);
    }
}