package view;

import components.*;
import components.HeaderPanel;
import controller.NhanVienController;
import util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Point2D;

public class NhanVienPanel extends JPanel {

    // 1. Các thành phần giao diện (Fields)
    private TablePanel tablePanel;
    private FormPanel formPanel;
    private NhanVienController controller;
    
    private JTextField txtMa, txtTen, txtSdt, txtEmail, txtChucVu, txtLuong, txtDiaChi;
    private JComboBox<String> cbGioiTinh;

    // 2. Constructor
    public NhanVienPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Khởi tạo giao diện
        add(new HeaderPanel("Nhân viên", "Quản lý nhân viên", 
            new Color(59, 26, 8), new Color(124, 58, 14), new Color(180, 83, 9)), 
            BorderLayout.NORTH);
        
        initTableAndForm();
        
        // Khởi tạo Controller sau khi giao diện đã sẵn sàng
        this.controller = new NhanVienController(this);
        this.controller.loadData();
        this.controller.initEvents(); 
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
    // 3. Khởi tạo Table và Form
    private void initTableAndForm() {
        String[] columns = {"MÃ NV", "TÊN NV", "SĐT", "EMAIL", "CHỨC VỤ", "LƯƠNG", "GIỚI TÍNH", "ĐỊA CHỈ"};
        tablePanel = new TablePanel(columns, "Tìm kiếm nhân viên...");
        tablePanel.setOpaque(false);
        // Khởi tạo các Input
        txtMa = UIFactory.createTextField();
        txtTen = UIFactory.createTextField();
        txtSdt = UIFactory.createTextField();
        txtEmail = UIFactory.createTextField();
        txtChucVu = UIFactory.createTextField();
        txtLuong = UIFactory.createTextField();
        txtDiaChi = UIFactory.createTextField();
        cbGioiTinh = UIFactory.createComboBox(new String[]{"Nam", "Nữ"});

        Component[] inputs = {
            new InputGroup("Mã nhân viên", txtMa),
            new InputGroup("Tên nhân viên", txtTen),
            new InputGroup("Số điện thoại", txtSdt),
            new InputGroup("Email", txtEmail),
            new InputGroup("Chức vụ", txtChucVu),
            new InputGroup("Lương", txtLuong),
            new InputGroup("Giới tính", cbGioiTinh),
            new InputGroup("Địa chỉ", txtDiaChi)
        };
        
        formPanel = new FormPanel("Thông tin nhân viên", inputs, 
            ImageUtil.getScaledIcon(getClass(), "/img/NV01.png", 220, 220), 
            ImageUtil.getScaledIcon(getClass(), "/img/NV02.png", 220, 220));

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

        // Sự kiện: Tìm kiếm bằng nút
        tablePanel.getBtnSearch().addActionListener(e -> {
            controller.searchNV(tablePanel.getSearchField().getText());
        });
    }

    // 4. Các phương thức logic xử lý giao diện
    private void fillFormFromSelectedRow() {
        int row = tablePanel.getTable().getSelectedRow();
        txtMa.setText(tablePanel.getTable().getValueAt(row, 0).toString());
        txtTen.setText(tablePanel.getTable().getValueAt(row, 1).toString());
        txtSdt.setText(tablePanel.getTable().getValueAt(row, 2).toString());
        txtEmail.setText(tablePanel.getTable().getValueAt(row, 3).toString());
        txtChucVu.setText(tablePanel.getTable().getValueAt(row, 4).toString());
        txtLuong.setText(tablePanel.getTable().getValueAt(row, 5).toString());
        cbGioiTinh.setSelectedItem(tablePanel.getTable().getValueAt(row, 6).toString());
        txtDiaChi.setText(tablePanel.getTable().getValueAt(row, 7).toString());
        txtMa.setEditable(false); 
    }

    public void clearForm() {
        txtMa.setText(""); txtTen.setText(""); txtSdt.setText(""); txtEmail.setText("");
        txtChucVu.setText(""); txtLuong.setText(""); txtDiaChi.setText("");
        cbGioiTinh.setSelectedIndex(0);
        getTable().clearSelection();
        txtMa.setEditable(true); 
    }

    // 5. Các Getter (Cung cấp cho Controller)
    public JTable getTable() { return tablePanel.getTable(); }
    public DefaultTableModel getTableModel() { return (DefaultTableModel) tablePanel.getTable().getModel(); }
    public TablePanel getTablePanel() { return tablePanel; } 
    
    public JTextField getTxtMa() { return txtMa; }
    public JTextField getTxtTen() { return txtTen; }
    public JTextField getTxtSdt() { return txtSdt; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtChucVu() { return txtChucVu; }
    public JTextField getTxtLuong() { return txtLuong; }
    public JTextField getTxtDiaChi() { return txtDiaChi; }
    public JComboBox<String> getCbGioiTinh() { return cbGioiTinh; }
    
    public JButton getBtnLuu() { return formPanel.getBtnLuu(); }
    public JButton getBtnXoa() { return formPanel.getBtnXoa(); }
    public JButton getBtnLamMoi() { return formPanel.getBtnLamMoi(); }
}