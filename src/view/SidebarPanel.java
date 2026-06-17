package view;

import javax.swing.*;
import java.awt.*;
import util.PermissionUtil;
import util.SessionUtil;
import model.TaiKhoan;

public class SidebarPanel extends JPanel {
    
    private MainFrame mainFrame;
    private GridBagConstraints gbc;

    public SidebarPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(new Color(0x2C1E14));
        setPreferredSize(new Dimension(265, 910));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 40, 20));
        
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
    }

    public void updatePermissions() {
        removeAll();
        TaiKhoan currentUser = SessionUtil.getCurrentUser();
        if (currentUser == null) return;

        boolean isAdmin = PermissionUtil.isAdmin();
        boolean isNhanVien = PermissionUtil.isNhanVien();

        if (!isNhanVien) {
            addNavButton("Trang chủ", "/img/house.png", "PANEL_TRANG_CHU");
            addNavButton("Khách hàng", "/img/khachHang.png", "PANEL_KHACH_HANG");
        }
        if (isAdmin) addNavButton("Nhân viên", "/img/nhanVien1.png", "PANEL_NHAN_VIEN");
        
        addNavButton("Bàn ăn", "/img/banAn.png", "PANEL_BAN_AN");
        addNavButton("Đặt hàng", "/img/dathang1.png", "PANEL_DAT_HANG");
        addNavButton("Hóa đơn", "/img/hoaDon.png", "PANEL_HOA_DON");
        
        if (!isNhanVien) addNavButton("Sản phẩm", "/img/sanPham.png", "PANEL_SAN_PHAM");

        gbc.weighty = 1.0;
        add(Box.createGlue(), gbc);
        revalidate();
        repaint();
    }

    private void addNavButton(String text, String iconPath, String cardName) {
        JButton btn = new GradientButton(text);
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            btn.setIcon(new ImageIcon(icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        } catch (Exception e) {}

        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(15);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(225, 60));

        btn.addActionListener(e -> {
            resetAllButtons();
            ((GradientButton) btn).setActive(true);
            mainFrame.switchInnerCard(cardName);
            btn.repaint();
        });

        add(btn, gbc.clone());
    }

    private void resetAllButtons() {
        for (Component comp : getComponents()) {
            if (comp instanceof GradientButton) {
                ((GradientButton) comp).setActive(false);
                comp.repaint();
            }
        }
    }

    private class GradientButton extends JButton {
        private boolean active = false;

        public GradientButton(String text) { super(text); }
        public void setActive(boolean active) { this.active = active; }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (active) {
                GradientPaint gp = new GradientPaint(0, 0, new Color(217, 119, 6), 
                                                     getWidth(), getHeight(), new Color(245, 158, 11));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                setForeground(Color.WHITE);
            } else {
                setForeground(new Color(0xE0B988));
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }
}