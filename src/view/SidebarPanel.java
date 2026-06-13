package view;

import javax.swing.*;
import java.awt.*;
import util.SessionUtil; 
import model.TaiKhoan;

public class SidebarPanel extends JPanel {
    
    private MainFrame mainFrame;
    private GridBagConstraints gbc;

    public SidebarPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        // 1. Cấu hình kích thước thanh Sidebar theo yêu cầu: Rộng 275px, Cao 910px
        setPreferredSize(new Dimension(275, 910));
        setBackground(new Color(0x121212)); 
        
        // PADDING CHO SIDEBARPANEL: Dọc 50px (Trên/Dưới), Ngang 40px (Trái/Phải)
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));
        
        // 2. Sử dụng GridBagLayout để kiểm soát chính xác vị trí các nút
        setLayout(new GridBagLayout());
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ép nút điền đầy khoảng ngang 195px còn lại
        gbc.insets = new Insets(0, 0, 12, 0);     // Khoảng cách 12px giữa các nút theo chiều dọc

        // LƯU Ý: Không add nút trực tiếp ở đây nữa để tránh việc chưa có dữ liệu User lúc khởi tạo
    }

    /**
     * 🛡️ HÀM PHÂN QUYỀN DỰA TRÊN DATABASE SQL
     * Hàm này tự động quét Session tài khoản để sinh ra các nút chức năng phù hợp
     */
    public void updatePermissions() {
        // Xóa sạch trạng thái nút cũ trước khi vẽ lại giao diện mới
        removeAll();
        
        // Khôi phục mặc định cấu hình vị trí cho GridBag
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Lấy thông tin tài khoản đang thao tác trong hệ thống
        TaiKhoan currentUser = SessionUtil.getCurrentUser();
        if (currentUser == null) {
            revalidate();
            repaint();
            return;
        }

        String vaiTro = currentUser.getVaiTro();       // Lấy giá trị 'Admin' hoặc 'Nhân viên' từ DB
        String maChucVu = currentUser.getMaChucVu();   // Lấy mã chức vụ để sơ phòng (CV01, CV02...)

        // 1. Nút Trang chủ (Tất cả mọi chức vụ đều có quyền sử dụng)
        JButton btnTrangChu = new JButton("Trang chủ");
        styleButton(btnTrangChu);
        btnTrangChu.addActionListener(e -> mainFrame.switchInnerCard("PANEL_TRANG_CHU"));
        add(btnTrangChu, gbc.clone());
        
        // 2. Nút Khách hàng (Tất cả mọi chức vụ đều có quyền sử dụng)
        JButton btnKhachHang = new JButton("Khách hàng");
        styleButton(btnKhachHang);
        btnKhachHang.addActionListener(e -> mainFrame.switchInnerCard("PANEL_KHACH_HANG"));
        add(btnKhachHang, gbc.clone());
        
        // 3. Nút Nhân viên (🛡️ CHỈ CÓ TÀI KHOẢN ADMIN MỚI ĐƯỢC PHÉP HIỂN THỊ)
        if ("Admin".equalsIgnoreCase(vaiTro) || "CV01".equals(maChucVu)) {
            JButton btnNhanVien = new JButton("Nhân viên");
            styleButton(btnNhanVien);
            btnNhanVien.addActionListener(e -> mainFrame.switchInnerCard("PANEL_NHAN_VIEN"));
            add(btnNhanVien, gbc.clone());
        }
        
        // 4. BỔ SUNG: Nút Sản phẩm (Tất cả chức vụ đều có quyền vào xem/tra cứu thực đơn)
        JButton btnSanPham = new JButton("Sản phẩm");
        styleButton(btnSanPham);
        btnSanPham.addActionListener(e -> mainFrame.switchInnerCard("PANEL_SAN_PHAM"));
        add(btnSanPham, gbc.clone());
        
        // 5. Nút Bàn ăn (Tất cả mọi chức vụ đều có quyền sử dụng)
        JButton btnBanAn = new JButton("Bàn ăn");
        styleButton(btnBanAn);
        btnBanAn.addActionListener(e -> mainFrame.switchInnerCard("PANEL_BAN_AN"));
        add(btnBanAn, gbc.clone());
        
        // 6. Nút Đặt hàng (Tất cả mọi chức vụ đều có quyền sử dụng)
        JButton btnDatHang = new JButton("Đặt hàng");
        styleButton(btnDatHang);
        btnDatHang.addActionListener(e -> mainFrame.switchInnerCard("PANEL_DAT_HANG"));
        add(btnDatHang, gbc.clone());
        
        // 7. Nút Hóa đơn (Nút cuối cùng cấu hình neo lên top, gánh weighty = 1.0 đẩy toàn bộ menu lên sát nhau)
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        JButton btnHoaDon = new JButton("Hóa đơn");
        styleButton(btnHoaDon);
        btnHoaDon.addActionListener(e -> mainFrame.switchInnerCard("PANEL_HOA_DON"));
        add(btnHoaDon, gbc.clone());

        // Yêu cầu Java vẽ lại layout ngay lập tức
        revalidate();
        repaint();
    }

    // Giữ nguyên hàm custom UI nâng cao bo góc nút 16px của bạn
    private void styleButton(JButton btn) {
        btn.setForeground(new Color(0xE0B988));        
        btn.setFont(new Font("Arial", Font.PLAIN, 20));  
        btn.setPreferredSize(new Dimension(195, 60));    
        btn.setFocusPainted(false);                    
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);

        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                ButtonModel model = btn.getModel();
                if (model.isPressed()) {
                    g2.setColor(new Color(0x3B291E)); 
                } else if (model.isRollover()) {
                    g2.setColor(new Color(0x5E4131)); 
                } else {
                    g2.setColor(new Color(0x4B3427)); 
                }
                
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 16, 16);
                g2.dispose();
                super.paint(g, c); 
            }
        });
    }
}