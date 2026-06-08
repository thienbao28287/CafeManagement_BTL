package view;

import components.FormPanel;
import components.HeaderPanel;
import components.InputGroup;
import util.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DatHangPanel extends JPanel {

    // Component vùng thông tin đặt hàng (Bên trái)
    private JRadioButton rbThemMoi, rbSua;
    private JTextField txtMaDatHang, txtMaBan;
    private JComboBox<String> cbTrangThai;
    private JComboBox<String> cbNuocUong, cbDoAn;
    private JSpinner spSoluongNuoc, spSoluongDoAn;
    private JButton btnThemNuoc, btnThemDoAn;
    private JButton btnLuu, btnLamMoi, btnHuy;

    // Component vùng danh sách món đã chọn (Bên phải)
    private JTable tableMonDaChon;
    private DefaultTableModel tableModel;

    public DatHangPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Header (Đồng bộ dải màu gradient nâu/cam của bạn)
        add(new HeaderPanel(
            "📋 Đặt hàng", 
            "Tạo và quản lý đơn hàng", 
            new Color(120, 53, 4),   // Màu tối
            new Color(194, 65, 12),  // Màu trung gian
            new Color(234, 88, 12)   // Màu sáng
        ), BorderLayout.NORTH);

        // 2. Thân chính (Chia Trái - Phải)
        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setOpaque(false);

        contentPanel.add(createLeftOrderForm(), BorderLayout.WEST);
        contentPanel.add(createRightSelectedItems(), BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Tạo khu vực Thông tin đặt hàng bên trái
     */
    private JPanel createLeftOrderForm() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                "Thông tin đặt hàng",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14)
            ),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        leftPanel.setPreferredSize(new Dimension(340, 0));

        // --- Chế độ (Radio Buttons) ---
        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        modePanel.setOpaque(false);
        JLabel lblCheDo = new JLabel("Chế độ: ");
        rbThemMoi = new JRadioButton("Thêm mới");
        rbSua = new JRadioButton("Sửa", true);
        ButtonGroup bgMode = new ButtonGroup();
        bgMode.add(rbThemMoi);
        bgMode.add(rbSua);
        modePanel.add(lblCheDo);
        modePanel.add(rbThemMoi);
        modePanel.add(rbSua);

        // --- Inputs cơ bản ---
        txtMaDatHang = UIFactory.createTextField();
        txtMaBan = UIFactory.createTextField();
        cbTrangThai = UIFactory.createComboBox(new String[]{"Đang phục vụ", "Đã thanh toán", "Đã hủy"});

        // --- Khu vực chọn Nước uống ---
        cbNuocUong = UIFactory.createComboBox(new String[]{"Nước cam — 25.000 đ", "Coca Cola — 15.000 đ", "Trà sữa — 30.000 đ"});
        spSoluongNuoc = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        btnThemNuoc = new JButton("Thêm");
        styleAddButton(btnThemNuoc);

        JPanel nuocPanel = new JPanel(new BorderLayout(8, 0));
        nuocPanel.setOpaque(false);
        nuocPanel.add(cbNuocUong, BorderLayout.CENTER);
        
        JPanel nuocAction = new JPanel(new BorderLayout(5, 0));
        nuocAction.setOpaque(false);
        nuocAction.add(spSoluongNuoc, BorderLayout.WEST);
        nuocAction.add(btnThemNuoc, BorderLayout.EAST);
        nuocPanel.add(nuocAction, BorderLayout.EAST);

        // --- Khu vực chọn Đồ ăn ---
        cbDoAn = UIFactory.createComboBox(new String[]{"-- Chọn món --", "Phở bò — 45.000 đ", "Cơm rang — 40.000 đ"});
        spSoluongDoAn = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        btnThemDoAn = new JButton("Thêm");
        styleAddButton(btnThemDoAn);

        JPanel doAnPanel = new JPanel(new BorderLayout(8, 0));
        doAnPanel.setOpaque(false);
        doAnPanel.add(cbDoAn, BorderLayout.CENTER);

        JPanel doAnAction = new JPanel(new BorderLayout(5, 0));
        doAnAction.setOpaque(false);
        doAnAction.add(spSoluongDoAn, BorderLayout.WEST);
        doAnAction.add(btnThemDoAn, BorderLayout.EAST);
        doAnPanel.add(doAnAction, BorderLayout.EAST);

        // --- Các nút chức năng (Lưu, Làm mới, Hủy) ---
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        actionPanel.setOpaque(false);
        btnLuu = new JButton("Lưu");
        btnLamMoi = new JButton("Làm mới");
        btnHuy = new JButton("Hủy");

        // Custom style cho các nút hành động dưới cùng giống ảnh mẫu
        styleActionButton(btnLuu, new Color(180, 83, 9), Color.WHITE);
        styleActionButton(btnLamMoi, Color.WHITE, new Color(80, 80, 80));
        styleActionButton(btnHuy, Color.WHITE, new Color(180, 83, 9));
        
        actionPanel.add(btnLuu);
        actionPanel.add(btnLamMoi);
        actionPanel.add(btnHuy);

        // --- Đổ các nhóm vào panel chính (Dùng InputGroup như của bạn) ---
        leftPanel.add(modePanel);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(new InputGroup("Mã đặt hàng", txtMaDatHang));
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(new InputGroup("Mã bàn", txtMaBan));
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(new InputGroup("Trạng thái", cbTrangThai));
        leftPanel.add(Box.createVerticalStrut(15));
        
        leftPanel.add(new InputGroup("Nước uống", nuocPanel));
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(new InputGroup("Đồ ăn", doAnPanel));
        
        leftPanel.add(Box.createVerticalGlue()); // Đẩy cụm nút xuống đáy
        leftPanel.add(actionPanel);

        return leftPanel;
    }

    /**
     * Tạo khu vực Danh sách món đã chọn bên phải
     */
    private JPanel createRightSelectedItems() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                "Danh sách món đã chọn",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14)
            ),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Khởi tạo bảng danh sách món
        String[] columns = {"Tên món", "Loại", "Số lượng", "Đơn giá", "Thành tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        tableMonDaChon = new JTable(tableModel);
        
        // Custom nhẹ bảng để trống trải giống ảnh của bạn nếu chưa có data
        JScrollPane scrollPane = new JScrollPane(tableMonDaChon);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
        scrollPane.getViewport().setBackground(Color.WHITE);

        // Mẹo tạo dòng chữ gợi ý ẩn khi bảng chưa có dữ liệu giống ảnh UI của bạn
        tableMonDaChon.setFillsViewportHeight(true);
        
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        return rightPanel;
    }

    // --- Hàm phụ trợ Style cho đúng thiết kế giao diện ---
    private void styleAddButton(JButton btn) {
        btn.setBackground(new Color(194, 65, 12)); // Màu cam đậm nguyên bản
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.putClientProperty("JButton.arc", 10);
    }

    private void styleActionButton(JButton btn, Color background, Color foreground) {
        btn.setBackground(background);
        btn.setForeground(foreground);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(85, 32));
        btn.putClientProperty("JButton.arc", 15);
        if (background == Color.WHITE) {
            btn.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        }
    }
}