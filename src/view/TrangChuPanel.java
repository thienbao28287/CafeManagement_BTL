package view;

import components.StatusCard;
import util.ImageUtil;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

public class TrangChuPanel extends JPanel {

    private StatusCard cardKhachHang, cardNhanVien, cardBanAn, cardOrders;
    private StatusCard cardHomNay, cardThangNay, cardTongDoanhThu, cardOrdersDaTT;

    public TrangChuPanel() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(24, 24, 24, 24));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(createWelcomeBanner(), gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 24, 0);
        add(createOverviewRow(), gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 4, 12, 0);
        JLabel lblDoanhThuTitle = new JLabel("DOANH THU");
        lblDoanhThuTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDoanhThuTitle.setForeground(new Color(156, 102, 68));
        add(lblDoanhThuTitle, gbc);

        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(createRevenueRow(), gbc);
    }

    // Đổ nền trực tiếp vào TrangChuPanel thay vì dùng BasePanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        LinearGradientPaint gradient = new LinearGradientPaint(
                new Point2D.Double(0, 0),
                new Point2D.Double(getWidth(), getHeight()),
                new float[]{0f, 0.5f, 1f},
                new Color[]{new Color(250, 246, 241), new Color(254, 249, 243), new Color(255, 250, 245)}
        );
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }

    private JPanel createWelcomeBanner() {
        JPanel banner = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                LinearGradientPaint gradient = new LinearGradientPaint(
                        0, 0, getWidth(), getHeight(),
                        new float[]{0f, 1f},
                        new Color[]{new Color(36, 17, 10), new Color(173, 85, 23)}
                );
                g2.setPaint(gradient);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        banner.setOpaque(false);
        banner.setLayout(new BorderLayout(20, 0));
        banner.setBorder(new EmptyBorder(20, 32, 20, 32));
        banner.setPreferredSize(new Dimension(0, 180));

        JPanel leftTextPanel = new JPanel(new GridBagLayout());
        leftTextPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.anchor = GridBagConstraints.WEST; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;

        JLabel lblSub = new JLabel("☕  COFFEE SHOP");
        lblSub.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSub.setForeground(new Color(0xD2A06F)); 
        gbc.gridy = 0; leftTextPanel.add(lblSub, gbc);

        JLabel lblWelcome = new JLabel("Chào mừng đến");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        lblWelcome.setForeground(Color.WHITE);
        gbc.gridy = 1; gbc.insets = new Insets(4, 0, 0, 0);
        leftTextPanel.add(lblWelcome, gbc);

        JLabel lblSystemName = new JLabel("Management System");
        lblSystemName.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblSystemName.setForeground(new Color(0xF3C68F)); 
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 6, 0);
        leftTextPanel.add(lblSystemName, gbc);

        JLabel lblDesc = new JLabel("<html>Hệ thống quản lý quán cà phê — quản lý khách hàng, nhân viên, bàn ăn<br>và đơn hàng một cách hiệu quả, chuyên nghiệp.</html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(0xDCDCDC)); 
        gbc.gridy = 3; leftTextPanel.add(lblDesc, gbc);

        banner.add(leftTextPanel, BorderLayout.CENTER);
        return banner;
    }

    private JPanel createOverviewRow() {
        JPanel row = new JPanel(new GridLayout(1, 4, 16, 0));
        row.setOpaque(false);
        cardKhachHang = new StatusCard("Khách hàng", "1", null, new Color(180, 83, 9), Color.WHITE);
        cardNhanVien = new StatusCard("Nhân viên", "7", null, new Color(120, 53, 4), Color.WHITE);
        cardBanAn = new StatusCard("Bàn ăn", "1", null, new Color(234, 88, 12), Color.WHITE);
        cardOrders = new StatusCard("Orders", "7", null, new Color(5, 150, 105), Color.WHITE);
        row.add(cardKhachHang); row.add(cardNhanVien); row.add(cardBanAn); row.add(cardOrders);
        return row;
    }

    private JPanel createRevenueRow() {
        JPanel row = new JPanel(new GridLayout(1, 4, 16, 0));
        row.setOpaque(false);
        cardHomNay = new StatusCard("Hôm nay", "0 đ", null, new Color(0xA7F3D0), new Color(60,60,60));
        cardThangNay = new StatusCard("Tháng này", "225.000 đ", null, new Color(0xFEF3C7), new Color(60,60,60));
        cardTongDoanhThu = new StatusCard("Tổng doanh thu", "520.000 đ", null, new Color(0xFFEDD5), new Color(60,60,60));
        cardOrdersDaTT = new StatusCard("Orders đã TT", "4", null, new Color(0xFEF9C3), new Color(60,60,60));
        row.add(cardHomNay); row.add(cardThangNay); row.add(cardTongDoanhThu); row.add(cardOrdersDaTT);
        return row;
    }
}