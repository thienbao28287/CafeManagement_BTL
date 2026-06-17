package view;

import components.CustomButton;
import components.HeaderPanel;
import components.InputGroup;
import components.TablePanel;
import controller.DatHangController;
import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import util.UIFactory;

public class DatHangPanel extends JPanel {

    private JTextField txtMaDatHang, txtMaBan,txtMaKhachHang;
    private JComboBox<String> cbTrangThai;
    private JComboBox<String> cbNuocUong, cbDoAn;
    private JSpinner spSoluongNuoc, spSoluongDoAn;
    private CustomButton btnThemNuoc, btnThemDoAn;
    private CustomButton btnLuu, btnXoa, btnLamMoi, btnCapNhat;
    private JLabel lblTongTien;
    private TablePanel tablePanel;
    private DatHangController controller;

    public DatHangPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new HeaderPanel(
                "Đặt hàng",
                "Tạo và quản lý đơn hàng",
                new Color(120, 53, 4),
                new Color(194, 65, 12),
                new Color(234, 88, 12)
        ), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setOpaque(false);
        contentPanel.add(createLeftOrderForm(), BorderLayout.WEST);
        contentPanel.add(createRightSelectedItems(), BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        controller = new DatHangController(this);
        controller.loadData();
        controller.initEvents();
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
private JPanel createLeftOrderForm() {
    JPanel leftPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int arc = 14; int shadowSize = 6;
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

    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setOpaque(false);
    leftPanel.setBorder(BorderFactory.createEmptyBorder(21, 21, 21, 21));
    leftPanel.setPreferredSize(new Dimension(400, 0));

    // Title
    JLabel lblTitle = new JLabel("Thông tin đặt hàng");
    lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
    lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    leftPanel.add(lblTitle);
    leftPanel.add(Box.createVerticalStrut(15));

    // Khởi tạo các Component
    txtMaDatHang = UIFactory.createTextField();
    txtMaBan = UIFactory.createTextField();
    txtMaKhachHang = UIFactory.createTextField();
    txtMaKhachHang.setText("Khách Vãng Lai"); // Giá trị mặc định
    cbTrangThai = UIFactory.createComboBox(new String[]{"Đang phục vụ", "Đã thanh toán", "Đã hủy"});
    
    // Nước uống
    cbNuocUong = UIFactory.createComboBox(new String[]{"-- Chọn nước uống --"});
    spSoluongNuoc = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    spSoluongNuoc.setPreferredSize(new Dimension(60, 30));
    btnThemNuoc = new CustomButton("Thêm");
    JPanel nuocRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    nuocRow.setOpaque(false);
    nuocRow.add(new JLabel("SL: ")); nuocRow.add(spSoluongNuoc); nuocRow.add(btnThemNuoc);
    JPanel nuocPanel = new JPanel(new BorderLayout(0, 5));
    nuocPanel.setOpaque(false);
    nuocPanel.add(cbNuocUong, BorderLayout.NORTH);
    nuocPanel.add(nuocRow, BorderLayout.CENTER);

    // Đồ ăn
    cbDoAn = UIFactory.createComboBox(new String[]{"-- Chọn món --"});
    spSoluongDoAn = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    spSoluongDoAn.setPreferredSize(new Dimension(60, 30));
    btnThemDoAn = new CustomButton("Thêm");
    JPanel doAnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    doAnRow.setOpaque(false);
    doAnRow.add(new JLabel("SL: ")); doAnRow.add(spSoluongDoAn); doAnRow.add(btnThemDoAn);
    JPanel doAnPanel = new JPanel(new BorderLayout(0, 5));
    doAnPanel.setOpaque(false);
    doAnPanel.add(cbDoAn, BorderLayout.NORTH);
    doAnPanel.add(doAnRow, BorderLayout.CENTER);

    // Thêm các nhóm vào Panel theo chiều dọc
    leftPanel.add(new InputGroup("Mã đặt hàng:", txtMaDatHang));
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(new InputGroup("Mã bàn:", txtMaBan));
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(new InputGroup("Mã KH:", txtMaKhachHang));
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(new InputGroup("Trạng thái:", cbTrangThai));
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(new InputGroup("Nước uống:", nuocPanel));
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(new InputGroup("Đồ ăn:", doAnPanel));
    leftPanel.add(Box.createVerticalStrut(20));

    // Action Buttons
    btnLuu = new CustomButton("Lưu");
    btnXoa = new CustomButton("Xóa");
    btnLamMoi = new CustomButton("Làm mới");
    btnCapNhat = new CustomButton("Cập nhật");
    
    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
    actionPanel.setOpaque(false);
    actionPanel.add(btnLuu); actionPanel.add(btnXoa); 
    actionPanel.add(btnLamMoi); actionPanel.add(btnCapNhat);
    leftPanel.add(actionPanel);
   
    return leftPanel;
}

    private JPanel createRightSelectedItems() {
        String[] columns = {"MÃ HĐ", "MÃ SP", "TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
        tablePanel = new TablePanel(columns, "Tìm kiếm mã sản phẩm...");

        JPanel rightWrapper = new JPanel(new BorderLayout(0, 10));
        rightWrapper.setOpaque(false);
        rightWrapper.add(tablePanel, BorderLayout.CENTER);

        // ===== FOOTER: BUTTONS (LEFT) + TOTAL (RIGHT) =====
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);

        // LEFT: Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        buttonPanel.setOpaque(false);

        buttonPanel.add(btnLuu);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);
        buttonPanel.add(btnCapNhat);

        // RIGHT: Total
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setOpaque(false);

        lblTongTien = new JLabel("Tổng tiền: 0 đ");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongTien.setForeground(new Color(194, 65, 12));

        totalPanel.add(lblTongTien);

        footerPanel.add(buttonPanel, BorderLayout.WEST);
        footerPanel.add(totalPanel, BorderLayout.EAST);

        rightWrapper.add(footerPanel, BorderLayout.SOUTH);

        return rightWrapper;
    }

    public void setProductOptions(String[] nuocOptions, String[] doAnOptions) {
        cbNuocUong.setModel(new DefaultComboBoxModel<>(nuocOptions));
        cbDoAn.setModel(new DefaultComboBoxModel<>(doAnOptions));
    }

    public void clearOrderInputs() {
        txtMaDatHang.setText("");
        txtMaBan.setText("");
        txtMaKhachHang.setText("");
        cbTrangThai.setSelectedIndex(0);
        spSoluongNuoc.setValue(1);
        spSoluongDoAn.setValue(1);
        lblTongTien.setText("Tổng tiền: 0 đ");
        tablePanel.getTable().clearSelection();
    }

    public void setSelectedQuantity(int soLuong) {
        spSoluongNuoc.setValue(soLuong);
        spSoluongDoAn.setValue(soLuong);
    }

    // Getters
    public JLabel getLblTongTien() { return lblTongTien; }
    public TablePanel getTablePanel() { return tablePanel; }
    public JTextField getTxtMaDatHang() { return txtMaDatHang; }
    public JTextField getTxtMaBan() { return txtMaBan; }
    public JComboBox<String> getCbTrangThai() { return cbTrangThai; }
    public JComboBox<String> getCbNuocUong() { return cbNuocUong; }
    public JComboBox<String> getCbDoAn() { return cbDoAn; }
    public JSpinner getSpSoluongNuoc() { return spSoluongNuoc; }
    public JSpinner getSpSoluongDoAn() { return spSoluongDoAn; }
    public CustomButton getBtnThemNuoc() { return btnThemNuoc; }
    public CustomButton getBtnThemDoAn() { return btnThemDoAn; }
    public CustomButton getBtnLuu() { return btnLuu; }
    public CustomButton getBtnXoa() { return btnXoa; }
    public CustomButton getBtnLamMoi() { return btnLamMoi; }

	public CustomButton getBtnCapNhat() {
		return btnCapNhat;
	}

	public void setBtnCapNhat(CustomButton btnCapNhat) {
		this.btnCapNhat = btnCapNhat;
	}
	public JTextField getTxtMaKhachHang() { return txtMaKhachHang; }
    
}