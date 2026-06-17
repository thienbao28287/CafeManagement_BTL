package view;

import components.StatusCard;
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
        banner.setLayout(new GridBagLayout());
        banner.setBorder(new EmptyBorder(20, 32, 20, 32));
        banner.setPreferredSize(new Dimension(0, 200));

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        titleRow.setOpaque(false);
        titleRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/logo2.png"));
        Image scaledSmall = icon.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
        titleRow.add(new JLabel(new ImageIcon(scaledSmall)));
        
        JLabel lblSub = new JLabel("COFFEE SHOP");
        lblSub.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSub.setForeground(new Color(0xD2A06F));
        titleRow.add(lblSub);
        leftPanel.add(titleRow);

        JLabel lblWelcome = new JLabel("Chào mừng đến");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(lblWelcome);

        JLabel lblSystemName = new JLabel("Management System");
        lblSystemName.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblSystemName.setForeground(new Color(0xFCD34D));
        lblSystemName.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(lblSystemName);

        JLabel lblDesc = new JLabel("<html>Hệ thống quản lý quán cà phê — quản lý khách hàng, nhân viên,<br> bàn ăn và đơn hàng một cách hiệu quả, chuyên nghiệp.</html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(0xE0E0E0));
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(lblDesc);

        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.weightx = 1.0; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        banner.add(leftPanel, gbc);

        ImageIcon mainIcon = new ImageIcon(getClass().getResource("/img/image4.png")); 
        Image scaledBig = mainIcon.getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH);
        JLabel lblImage = new JLabel(new ImageIcon(scaledBig));
        
        gbc.gridx = 1; gbc.gridy = 0; 
        gbc.weightx = 0; 
        gbc.insets = new Insets(0, 20, 0, 0);
        banner.add(lblImage, gbc);

        return banner;
    }

    private JPanel createOverviewRow() {
        JPanel row = new JPanel(new GridLayout(1, 4, 16, 0));
        row.setOpaque(false);
        
        cardKhachHang = new StatusCard("Khách hàng", "0", getImg("/img/khachHang02.png"), new Color(180, 83, 9), Color.WHITE);
        cardNhanVien = new StatusCard("Nhân viên", "0", getImg("/img/nhanVien02.png"), new Color(120, 53, 4), Color.WHITE);
        cardBanAn = new StatusCard("Bàn ăn", "0", getImg("/img/banAn021.png"), new Color(234, 88, 12), Color.WHITE);
        cardOrders = new StatusCard("Orders", "0", getImg("/img/hoaDon02.png"), new Color(5, 150, 105), Color.WHITE);
        
        row.add(cardKhachHang); row.add(cardNhanVien); row.add(cardBanAn); row.add(cardOrders);
        return row;
    }

    private JPanel createRevenueRow() {
        JPanel row = new JPanel(new GridLayout(1, 4, 16, 0));
        row.setOpaque(false);
        
        cardHomNay = new StatusCard("Hôm nay", "0 đ", getImg(""), new Color(0xA7F3D0), new Color(60,60,60));
        cardThangNay = new StatusCard("Tháng này", "0 đ", getImg(""), new Color(0xFEF3C7), new Color(60,60,60));
        cardTongDoanhThu = new StatusCard("Tổng doanh thu", "0 đ", getImg(""), new Color(0xFFEDD5), new Color(60,60,60));
        cardOrdersDaTT = new StatusCard("Orders đã TT", "0", getImg(""), new Color(0xFEF9C3), new Color(60,60,60));
        
        row.add(cardHomNay); row.add(cardThangNay); row.add(cardTongDoanhThu); row.add(cardOrdersDaTT);
        return row;
    }

    private Image getImg(String path) {
        try {
            return new ImageIcon(getClass().getResource(path)).getImage();
        } catch (Exception e) {
            return null;
        }
    }

    public void updateStats(String khachHang, String nhanVien, String banAn, String orders,
                            String homNay, String thangNay, String tongDoanhThu, String ordersDaTT) {
        cardKhachHang.setValue(khachHang);
        cardNhanVien.setValue(nhanVien);
        cardBanAn.setValue(banAn);
        cardOrders.setValue(orders);
        cardHomNay.setValue(homNay);
        cardThangNay.setValue(thangNay);
        cardTongDoanhThu.setValue(tongDoanhThu);
        cardOrdersDaTT.setValue(ordersDaTT);
    }
}