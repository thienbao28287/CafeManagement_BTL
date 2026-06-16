package view;

import components.*;
import components.HeaderPanel;
import controller.SanPhamController;
import util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SanPhamPanel extends JPanel {

    // 1. Các thành phần giao diện
    private TablePanel tablePanel;
    private FormPanel formPanel;
    private SanPhamController controller;
    
    private JTextField txtMa, txtTen, txtLoai, txtSoLuong, txtGiaBan;
    private JComboBox<String> cbTrangThai;

    // 2. Constructor
    public SanPhamPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        add(new HeaderPanel("📦 Sản phẩm", "Quản lý danh mục sản phẩm", 
                new Color(59, 26, 8), new Color(124, 58, 14), new Color(180, 83, 9)), 
                BorderLayout.NORTH);
        
        initTableAndForm();
        
        // Khởi tạo Controller
        this.controller = new SanPhamController(this);
        this.controller.loadData();
        this.controller.initEvents(); 
    }

    // 3. Khởi tạo Table và Form
    private void initTableAndForm() {
        String[] columns = {"MÃ SP", "TÊN SẢN PHẨM", "LOẠI", "SỐ LƯỢNG", "GIÁ BÁN", "TRẠNG THÁI"};
        tablePanel = new TablePanel(columns, "Tìm kiếm sản phẩm...");

        txtMa = UIFactory.createTextField();
        txtTen = UIFactory.createTextField();
        txtLoai = UIFactory.createTextField();
        txtSoLuong = UIFactory.createTextField();
        txtGiaBan = UIFactory.createTextField();
        cbTrangThai = UIFactory.createComboBox(new String[]{"Còn hàng", "Hết hàng", "Ngừng bán"});

        Component[] inputs = {
            new InputGroup("Mã sản phẩm", txtMa),
            new InputGroup("Tên sản phẩm", txtTen),
            new InputGroup("Loại sản phẩm", txtLoai),
            new InputGroup("Số lượng", txtSoLuong),
            new InputGroup("Giá bán", txtGiaBan),
            new InputGroup("Trạng thái", cbTrangThai)
        };

        formPanel = new FormPanel("Thông tin sản phẩm", inputs, 
            ImageUtil.getScaledIcon(getClass(), "/img/leftNV.png", 220, 220), 
            ImageUtil.getScaledIcon(getClass(), "/img/rightNV.png", 220, 220));

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);
        content.add(tablePanel, BorderLayout.CENTER);
        content.add(formPanel, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);

        // Sự kiện: Chọn dòng trên bảng
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablePanel.getTable().getSelectedRow() != -1) {
                fillFormFromSelectedRow();
            }
        });
    }

    // 4. Xử lý logic giao diện
    private void fillFormFromSelectedRow() {
        int row = tablePanel.getTable().getSelectedRow();
        txtMa.setText(tablePanel.getTable().getValueAt(row, 0).toString());
        txtTen.setText(tablePanel.getTable().getValueAt(row, 1).toString());
        txtLoai.setText(tablePanel.getTable().getValueAt(row, 2).toString());
        txtSoLuong.setText(tablePanel.getTable().getValueAt(row, 3).toString());
        txtGiaBan.setText(tablePanel.getTable().getValueAt(row, 4).toString().replace(" đ", "").replace(".", ""));
        cbTrangThai.setSelectedItem(tablePanel.getTable().getValueAt(row, 5).toString());
        txtMa.setEditable(false); // Khóa mã khi chỉnh sửa
    }

    public void clearForm() {
        txtMa.setText(""); txtTen.setText(""); txtLoai.setText(""); 
        txtSoLuong.setText(""); txtGiaBan.setText("");
        cbTrangThai.setSelectedIndex(0);
        tablePanel.getTable().clearSelection();
        txtMa.setEditable(true);
    }

    // 5. Getters cho Controller
    public JTable getTable() { return tablePanel.getTable(); }
    public DefaultTableModel getTableModel() { return (DefaultTableModel) tablePanel.getTable().getModel(); }
    public TablePanel getTablePanel() { return tablePanel; }
    
    public JTextField getTxtMa() { return txtMa; }
    public JTextField getTxtTen() { return txtTen; }
    public JTextField getTxtLoai() { return txtLoai; }
    public JTextField getTxtSoLuong() { return txtSoLuong; }
    public JTextField getTxtGiaBan() { return txtGiaBan; }
    public JComboBox<String> getCbTrangThai() { return cbTrangThai; }
    
    public JButton getBtnLuu() { return formPanel.getBtnLuu(); }
    public JButton getBtnXoa() { return formPanel.getBtnXoa(); }
    public JButton getBtnLamMoi() { return formPanel.getBtnLamMoi(); }
}