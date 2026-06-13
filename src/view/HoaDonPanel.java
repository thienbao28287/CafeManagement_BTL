package view;

import components.HeaderPanel;
import components.TablePanel;
import components.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HoaDonPanel extends JPanel {

    // Vùng bên trái: Sử dụng TablePanel có sẵn của bạn để hiển thị danh sách hóa đơn
    private TablePanel leftTablePanel;

    // Vùng bên phải: Panel chi tiết hóa đơn (Theo mẫu image_92653c.png)
    private JPanel rightDetailCard;
    private JLabel lblMaHoaDonValue, lblMaBanValue, lblTrangThaiValue;
    private JTable tableChiTietMon;
    private DefaultTableModel modelChiTietMon;
    private JLabel lblTongTienValue;

    public HoaDonPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Header chính (Đồng bộ tông màu ấm của hệ thống)
        add(new HeaderPanel(
            "🧾 Hóa đơn", 
            "Quản lý danh sách và chi tiết hóa đơn bán hàng", 
            new Color(120, 53, 4),   
            new Color(194, 65, 12),  
            new Color(234, 88, 12)   
        ), BorderLayout.NORTH);

        // 2. Thân chính phân bổ Trái (Danh sách từ TablePanel) - Phải (Chi tiết Card)
        JPanel contentPanel = new JPanel(new BorderLayout(24, 0));
        contentPanel.setOpaque(false);

        contentPanel.add(createLeftInvoiceTablePanel(), BorderLayout.CENTER); // Vùng bên trái dùng TablePanel
        contentPanel.add(createRightDetailCard(), BorderLayout.EAST);       // Vùng bên phải giữ nguyên Card chi tiết

        add(contentPanel, BorderLayout.CENTER);
        loadFakeData();
    }

    /**
     * Vùng bên trái: Khởi tạo TablePanel chính xác theo constructor của bạn
     */
    private JPanel createLeftInvoiceTablePanel() {
        // Định nghĩa các tiêu đề cột cho danh sách hóa đơn
        String[] columns = {"MÃ HĐ", "BÀN", "TỔNG TIỀN", "TRẠNG THÁI"};
        
        // Gọi chính xác constructor: TablePanel(String[] columnTitles, String searchPlaceholder)
        leftTablePanel = new TablePanel(columns, "Tìm kiếm mã order, bàn...");
        
        return leftTablePanel;
    }

    /**
     * Vùng bên phải: Panel hiển thị chi tiết hóa đơn theo mẫu image_92653c.png
     */
    private JPanel createRightDetailCard() {
        rightDetailCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 14;
                int shadowSize = 6;
                for (int i = 1; i <= shadowSize; i++) {
                    float alpha = (float) (shadowSize - i + 1) / (shadowSize * 40); 
                    g2.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
                    g2.fillRoundRect(shadowSize - i, shadowSize - i, getWidth() - (shadowSize - i) * 2, getHeight() - (shadowSize - i) * 2, arc, arc);
                }
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        rightDetailCard.setLayout(new BorderLayout(0, 16));
        rightDetailCard.setOpaque(false);
        rightDetailCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        rightDetailCard.setPreferredSize(new Dimension(500, 0));

        // --- 2.1 PHẦN TRÊN: Tiêu đề & Thông tin chung ---
        JPanel topInfoPanel = new JPanel(new GridBagLayout());
        topInfoPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 12);

        JLabel lblTitle = new JLabel("Chi tiết hóa đơn");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(new Color(40, 40, 40));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        topInfoPanel.add(lblTitle, gbc);

        gbc.gridy = 1; gbc.gridwidth = 2;
        topInfoPanel.add(Box.createVerticalStrut(8), gbc);

        gbc.gridwidth = 1; gbc.weightx = 0.5;
        
        JLabel lblMaHoaDon = new JLabel("Mã hóa đơn: ");
        lblMaHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblMaHoaDon.setForeground(Color.GRAY);
        lblMaHoaDonValue = new JLabel("dsa");
        lblMaHoaDonValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JPanel pnlMaHD = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlMaHD.setOpaque(false);
        pnlMaHD.add(lblMaHoaDon);
        pnlMaHD.add(lblMaHoaDonValue);
        
        gbc.gridx = 0; gbc.gridy = 2;
        topInfoPanel.add(pnlMaHD, gbc);

        JLabel lblMaBan = new JLabel("Mã bàn: ");
        lblMaBan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblMaBan.setForeground(Color.GRAY);
        lblMaBanValue = new JLabel("đá");
        lblMaBanValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JPanel pnlMaBan = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlMaBan.setOpaque(false);
        pnlMaBan.add(lblMaBan);
        pnlMaBan.add(lblMaBanValue);
        
        gbc.gridx = 1; gbc.gridy = 2;
        topInfoPanel.add(pnlMaBan, gbc);

        JLabel lblTrangThai = new JLabel("Trạng thái: ");
        lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTrangThai.setForeground(Color.GRAY);
        
        lblTrangThaiValue = new JLabel(" Đã thanh toán ");
        lblTrangThaiValue.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTrangThaiValue.setForeground(new Color(22, 163, 74));
        lblTrangThaiValue.setBackground(new Color(220, 252, 231));
        lblTrangThaiValue.setOpaque(true);
        lblTrangThaiValue.setBorder(BorderFactory.createLineBorder(new Color(187, 247, 208), 1, true));

        JPanel pnlTrangThai = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlTrangThai.setOpaque(false);
        pnlTrangThai.add(lblTrangThai);
        pnlTrangThai.add(lblTrangThaiValue);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        topInfoPanel.add(pnlTrangThai, gbc);

        rightDetailCard.add(topInfoPanel, BorderLayout.NORTH);

        // --- 2.2 PHẦN GIỮA: Bảng chi tiết món ăn ---
        JPanel centerGridPanel = new JPanel(new BorderLayout(0, 8));
        centerGridPanel.setOpaque(false);
        JLabel lblDanhSachMon = new JLabel("Danh sách món");
        lblDanhSachMon.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachMon.setForeground(new Color(60, 60, 60));
        centerGridPanel.add(lblDanhSachMon, BorderLayout.NORTH);
        String[] itemColumns = {"LOẠI", "TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
        modelChiTietMon = new DefaultTableModel(itemColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tableChiTietMon = new JTable(modelChiTietMon);
        tableChiTietMon.setRowHeight(32);
        tableChiTietMon.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableChiTietMon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableChiTietMon.getTableHeader().setBackground(Color.WHITE);
        tableChiTietMon.setGridColor(new Color(240, 240, 240));
        tableChiTietMon.setShowVerticalLines(false); 
        JScrollPane scrollPaneDetail = new JScrollPane(tableChiTietMon);
        scrollPaneDetail.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        scrollPaneDetail.getViewport().setBackground(Color.WHITE);       
        centerGridPanel.add(scrollPaneDetail, BorderLayout.CENTER);
        rightDetailCard.add(centerGridPanel, BorderLayout.CENTER);
        // --- 2.3 PHẦN DƯỚI: Tổng tiền kết toán ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        bottomPanel.setOpaque(false);
        JLabel lblTongTien = new JLabel("Tổng tiền: ");
        lblTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTongTien.setForeground(Color.GRAY);
        lblTongTienValue = new JLabel("225.000 đ");
        lblTongTienValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongTienValue.setForeground(new Color(20, 20, 20));
        bottomPanel.add(lblTongTien);
        bottomPanel.add(lblTongTienValue);
        rightDetailCard.add(bottomPanel, BorderLayout.SOUTH);
        return rightDetailCard;
    }
    private void loadFakeData() {
        // Lấy model bảng từ TablePanel bên trái để đổ dữ liệu hóa đơn
        DefaultTableModel modelDanhSachHoaDon = (DefaultTableModel) leftTablePanel.getTable().getModel(); 
        modelDanhSachHoaDon.addRow(new Object[]{"dsa", "đá", "225.000 đ", "Đã thanh toán"});
        modelDanhSachHoaDon.addRow(new Object[]{"123", "123", "265.000 đ", "Đang phục vụ"});
        modelDanhSachHoaDon.addRow(new Object[]{"12", "12", "110.000 đ", "Đã thanh toán"});
        for (int i = 4; i <= 25; i++) {
            String status = (i % 3 == 0) ? "Đang phục vụ" : "Đã thanh toán";
            modelDanhSachHoaDon.addRow(new Object[]{"HD" + i, "Bàn số " + i, (i * 30) + ".000 đ", status});
        }
        // Đổ dữ liệu bảng chi tiết hóa đơn bên phải
        modelChiTietMon.addRow(new Object[]{"Nước uống", "Trà sữa", "3", "35.000 đ", "105.000 đ"});
        modelChiTietMon.addRow(new Object[]{"Đồ ăn", "Bánh croissant", "4", "30.000 đ", "120.000 đ"});
    }

    
    
   
}