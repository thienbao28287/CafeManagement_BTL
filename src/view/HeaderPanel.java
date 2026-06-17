package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Point2D;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HeaderPanel extends JPanel {

    private JLabel lblClock;

    public HeaderPanel(MainFrame mainFrame) {
        setPreferredSize(new Dimension(1200, 80));

        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 140));
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 40, 10, 40);
        setBorder(BorderFactory.createCompoundBorder(bottomBorder, emptyBorder));

        setLayout(new GridBagLayout());

        // --- PANEL LOGO ---
        JPanel logoPanel = new JPanel(new BorderLayout(15, 0));
        logoPanel.setOpaque(false);

        java.net.URL imgUrl = getClass().getResource("/img/logo2.png");
        if (imgUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imgUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoPanel.add(logoLabel, BorderLayout.WEST);
        }

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        textPanel.setOpaque(false);

        JLabel title = new JLabel("COFFEE SHOP");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.white);
        textPanel.add(title);

        JLabel subTitle = new JLabel("MANAGEMENT SYSTEM");
        subTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        subTitle.setForeground(new Color(252, 211, 77));
        textPanel.add(subTitle);

        logoPanel.add(textPanel, BorderLayout.CENTER);

        // --- PANEL THỜI GIAN & ĐĂNG XUẤT ---
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        actionPanel.setOpaque(false);

        // Khởi tạo Đồng hồ Real-time
        lblClock = new JLabel();
        lblClock.setFont(new Font("Arial", Font.BOLD, 14));
        lblClock.setForeground(new Color(210, 180, 140));
        
        Timer timer = new Timer(1000, e -> {
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            lblClock.setText(time);
        });
        timer.setInitialDelay(0);
        timer.start();

        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 12));
        btnLogout.setBackground(new Color(210, 180, 140));
        btnLogout.setForeground(new Color(43, 29, 20));
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn thoát ứng dụng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }
        });

        actionPanel.add(lblClock);
        actionPanel.add(btnLogout);

        // --- GẮN VÀO HEADER ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        add(logoPanel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        add(actionPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        float[] dist = {0.0f, 0.6f, 1.0f};
        Color[] colors = {new Color(59, 26, 8), new Color(92, 45, 14), new Color(124, 58, 14)};
        
        LinearGradientPaint lgp = new LinearGradientPaint(
                new Point2D.Float(0, 0),
                new Point2D.Float(getWidth(), getHeight()),
                dist, colors);

        g2d.setPaint(lgp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}