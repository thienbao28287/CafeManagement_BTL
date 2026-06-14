package view;

import components.*;
import components.HeaderPanel;
import controller.KhachHangController;
import util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KhachHangPanel extends JPanel {
    private TablePanel tablePanel;
    private FormPanel formPanel;
    private KhachHangController controller;
    
    private JTextField txtMa, txtTen, txtSdt, txtDiaChi;

    public KhachHangPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Header
        add(new HeaderPanel("👥 Khách hàng", "Quản lý danh sách khách hàng", 
            new Color(59, 26, 8), new Color(124, 58, 14), new Color(180, 83, 9)), 
            BorderLayout.NORTH);

        // 2. Bảng
        String[] columns = {"MÃ KH", "TÊN KH", "SĐT", "ĐỊA CHỈ"};
        tablePanel = new TablePanel(columns, "Tìm kiếm khách hàng...");

        // 3. Khởi tạo Inputs
        txtMa = UIFactory.createTextField();
        txtTen = UIFactory.createTextField();
        txtSdt = UIFactory.createTextField();
        txtDiaChi = UIFactory.createTextField();

        Component[] inputs = {
            new InputGroup("Mã KH:", txtMa),
            new InputGroup("Tên KH:", txtTen),
            new InputGroup("SĐT:", txtSdt),
            new InputGroup("Địa chỉ:", txtDiaChi)
        };

        // 4. Form Panel
        formPanel = new FormPanel("Thông tin chi tiết khách hàng", inputs, 
            ImageUtil.getScaledIcon(getClass(), "/img/leftNV.png", 220, 220), 
            ImageUtil.getScaledIcon(getClass(), "/img/rightNV.png", 220, 220));

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);
        content.add(tablePanel, BorderLayout.CENTER);
        content.add(formPanel, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);

        // 5. Khởi tạo Controller
        this.controller = new KhachHangController(this);
        this.controller.loadData();
        this.controller.initEvents();
        
        // Sự kiện chọn dòng
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablePanel.getTable().getSelectedRow() != -1) {
                fillFormFromSelectedRow();
            }
        });
    }

    private void fillFormFromSelectedRow() {
        int row = tablePanel.getTable().getSelectedRow();
        txtMa.setText(tablePanel.getTable().getValueAt(row, 0).toString());
        txtTen.setText(tablePanel.getTable().getValueAt(row, 1).toString());
        txtSdt.setText(tablePanel.getTable().getValueAt(row, 2).toString());
        txtDiaChi.setText(tablePanel.getTable().getValueAt(row, 3).toString());
        txtMa.setEditable(false);
    }

    public void clearForm() {
        txtMa.setText(""); txtTen.setText(""); txtSdt.setText(""); txtDiaChi.setText("");
        txtMa.setEditable(true);
        tablePanel.getTable().clearSelection();
    }

    // --- CÁC GETTER ---
    public JTable getTable() { return tablePanel.getTable(); }
    public DefaultTableModel getTableModel() { return (DefaultTableModel) tablePanel.getTable().getModel(); }
    public TablePanel getTablePanel() { return tablePanel; }
    public JTextField getTxtMa() { return txtMa; }
    public JTextField getTxtTen() { return txtTen; }
    public JTextField getTxtSdt() { return txtSdt; }
    public JTextField getTxtDiaChi() { return txtDiaChi; }
    public JButton getBtnLuu() { return formPanel.getBtnLuu(); }
    public JButton getBtnXoa() { return formPanel.getBtnXoa(); }
    public JButton getBtnLamMoi() { return formPanel.getBtnLamMoi(); }
}