package view;

import components.HeaderPanel;
import components.InputGroup;
import components.TablePanel;
import components.CustomButton; 
import util.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DatHangPanel extends JPanel {

    // Component vùng thông tin đặt hàng (Bên trái)
    private JRadioButton rbThemMoi, rbSua;
    private JTextField txtMaDatHang, txtMaBan;
    private JComboBox<String> cbTrangThai;
    private JComboBox<String> cbNuocUong, cbDoAn;
    private JSpinner spSoluongNuoc, spSoluongDoAn;
    
    // Thay đổi từ JButton thành CustomButton của dự án
    private CustomButton btnThemNuoc, btnThemDoAn;

    // Component vùng danh sách món đã chọn (Bên phải)
    private TablePanel tablePanel;

    public DatHangPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Header (Dải màu gradient đồng bộ)
        add(new HeaderPanel(
            "📋 Đặt hàng", 
            "Tạo và quản lý đơn hàng", 
            new Color(120, 53, 4),   
            new Color(194, 65, 12),  
            new Color(234, 88, 12)   
        ), BorderLayout.NORTH);

        // 2. Thân chính (Bố cục Trái - Phải)
        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setOpaque(false);

        // Đặt vùng Thông tin đặt hàng ở phía Tây (Bên trái)
        contentPanel.add(createLeftOrderForm(), BorderLayout.WEST);
        // Đặt vùng Danh sách món đã chọn ở giữa (Tràn sang phải)
        contentPanel.add(createRightSelectedItems(), BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
        loadFakeData();
    }

    /**
     * Khu vực bên trái: Thông tin đặt hàng (Sử dụng viền đổ bóng Shadow Card)
     */
    private JPanel createLeftOrderForm() {
        // Áp dụng thuật toán tự vẽ shadow và bo góc của FormPanel lên leftPanel gốc
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 14;
                int shadowSize = 6;
                // Vẽ các lớp bóng mờ xếp chồng phía sau
                for (int i = 1; i <= shadowSize; i++) {
                    float alpha = (float) (shadowSize - i + 1) / (shadowSize * 40); 
                    g2.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
                    g2.fillRoundRect(shadowSize - i, shadowSize - i, getWidth() - (shadowSize - i) * 2, getHeight() - (shadowSize - i) * 2, arc, arc);
                }
                // Vẽ nền trắng bo góc đè lên trên lớp bóng đổ
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false); 
        
        // Đệm lề EmptyBorder
        leftPanel.setBorder(BorderFactory.createEmptyBorder(21, 21, 21, 21));
        leftPanel.setPreferredSize(new Dimension(340, 0));

        // --- ĐÃ SỬA: Tiêu đề của vùng đặt hàng ép căn lề trái (X) ---
        JLabel lblTitle = new JLabel("Thông tin đặt hàng");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(new Color(50, 50, 50));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT); // Ép component bám lề trái trong BoxLayout
        leftPanel.add(lblTitle);

        // --- Khởi tạo các Inputs từ UIFactory ---
        txtMaDatHang = UIFactory.createTextField();
        txtMaBan = UIFactory.createTextField();
        cbTrangThai = UIFactory.createComboBox(new String[]{"Đang phục vụ", "Đã thanh toán", "Đã hủy"});

        // --- Khu vực chọn Nước uống phức hợp (Đưa nút và spinner xuống dưới) ---
        cbNuocUong = UIFactory.createComboBox(new String[]{"Nước cam — 25.000 đ", "Coca Cola — 15.000 đ", "Trà sữa — 30.000 đ"});
        spSoluongNuoc = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spSoluongNuoc.setPreferredSize(new Dimension(60, 28)); // Đặt size vừa vặn cho Spinner
        btnThemNuoc = new CustomButton("Thêm"); 

        // Dòng chứa số lượng và nút bấm ở phía dưới JComboBox
        JPanel nuocActionRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        nuocActionRow.setOpaque(false);
        nuocActionRow.add(new JLabel("SL: "));
        nuocActionRow.add(spSoluongNuoc);
        nuocActionRow.add(Box.createHorizontalStrut(8));
        nuocActionRow.add(btnThemNuoc);

        // Cụm nước uống xếp chồng dọc
        JPanel nuocPanel = new JPanel();
        nuocPanel.setLayout(new BoxLayout(nuocPanel, BoxLayout.Y_AXIS));
        nuocPanel.setOpaque(false);
        nuocPanel.add(cbNuocUong);
        nuocPanel.add(Box.createVerticalStrut(6)); // Khoảng cách nhỏ giữa combobox và hàng action
        nuocPanel.add(nuocActionRow);

        // --- Khu vực chọn Đồ ăn phức hợp (Đã đưa nút và spinner xuống dưới) ---
        cbDoAn = UIFactory.createComboBox(new String[]{"-- Chọn món --", "Phở bò — 45.000 đ", "Cơm rang — 40.000 đ"});
        spSoluongDoAn = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spSoluongDoAn.setPreferredSize(new Dimension(60, 28));
        btnThemDoAn = new CustomButton("Thêm");

        // Dòng chứa số lượng và nút bấm ở phía dưới JComboBox
        JPanel doAnActionRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        doAnActionRow.setOpaque(false);
        doAnActionRow.add(new JLabel("SL: "));
        doAnActionRow.add(spSoluongDoAn);
        doAnActionRow.add(Box.createHorizontalStrut(8));
        doAnActionRow.add(btnThemDoAn);

        // Cụm đồ ăn xếp chồng dọc
        JPanel doAnPanel = new JPanel();
        doAnPanel.setLayout(new BoxLayout(doAnPanel, BoxLayout.Y_AXIS));
        doAnPanel.setOpaque(false);
        doAnPanel.add(cbDoAn);
        doAnPanel.add(Box.createVerticalStrut(6));
        doAnPanel.add(doAnActionRow);

        // --- Gom nhóm các hàng nhập liệu bằng InputGroup ---
        // Thêm thuộc tính AlignmentX cho các nhóm input để bám dọc thẳng lề trái
        Component item1 = new InputGroup("Mã đặt hàng:", txtMaDatHang);
        ((JComponent)item1).setAlignmentX(Component.LEFT_ALIGNMENT);
        Component item2 = new InputGroup("Mã bàn:", txtMaBan);
        ((JComponent)item2).setAlignmentX(Component.LEFT_ALIGNMENT);
        Component item3 = new InputGroup("Trạng thái:", cbTrangThai);
        ((JComponent)item3).setAlignmentX(Component.LEFT_ALIGNMENT);
        Component item4 = new InputGroup("Nước uống:", nuocPanel);
        ((JComponent)item4).setAlignmentX(Component.LEFT_ALIGNMENT);
        Component item5 = new InputGroup("Đồ ăn:", doAnPanel);
        ((JComponent)item5).setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(item1);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(item2);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(item3);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(item4);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(item5);
        
        leftPanel.add(Box.createVerticalGlue());

        return leftPanel;
    }

    /**
     * Khu vực bên phải: Sử dụng TablePanel
     */
    private JPanel createRightSelectedItems() {
        String[] columns = {"TÊN MÓN", "LOẠI", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
        tablePanel = new TablePanel(columns, "Tìm kiếm món ăn trong đơn này...");
        return tablePanel;
    }

    private void loadFakeData() {
        DefaultTableModel model = (DefaultTableModel) tablePanel.getTable().getModel();
        model.addRow(new Object[]{"Nước cam", "Nước uống", "2", "25.000 đ", "50.000 đ"});
        model.addRow(new Object[]{"Phở bò", "Đồ ăn", "1", "45.000 đ", "45.000 đ"});
    }

    // --- Các hàm Getter ---
    public TablePanel getTablePanel() { return tablePanel; }
    public JRadioButton getRbThemMoi() { return rbThemMoi; }
    public JRadioButton getRbSua() { return rbSua; }
    public JTextField getTxtMaDatHang() { return txtMaDatHang; }
    public JTextField getTxtMaBan() { return txtMaBan; }
    public JComboBox<String> getCbTrangThai() { return cbTrangThai; }
    public JComboBox<String> getCbNuocUong() { return cbNuocUong; }
    public JComboBox<String> getCbDoAn() { return cbDoAn; }
    public JSpinner getSpSoluongNuoc() { return spSoluongNuoc; }
    public JSpinner getSpSoluongDoAn() { return spSoluongDoAn; }
    
    public CustomButton getBtnThemNuoc() { return btnThemNuoc; }
    public CustomButton getBtnThemDoAn() { return btnThemDoAn; }
}