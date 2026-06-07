package view;

import components.FormPanel;
import components.HeaderPanel;
import components.InputGroup;
import components.TablePanel;
import util.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.*;

public class NhanVienPanel extends JPanel {
    private TablePanel tablePanel;
    private JTextField txtMa, txtTen, txtSdt, txtEmail, txtChucVu, txtLuong, txtDiaChi;
    private JComboBox<String> cbGioiTinh;

    public NhanVienPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Header
        add(new HeaderPanel("🧑‍💼 Nhân viên", "Quản lý nhân viên", 
        	    new Color(59, 26, 8), new Color(124, 58, 14), new Color(180, 83, 9)), 
        	    BorderLayout.NORTH);

        // 2. Bảng
        String[] columns = {"MÃ NV", "TÊN", "SĐT", "EMAIL", "CHỨC VỤ", "LƯƠNG", "GIỚI TÍNH", "ĐIẠ CHỈ"};
        tablePanel = new TablePanel(columns, "Tìm kiếm nhân viên...");

        // 3. Khởi tạo Inputs
        txtMa = UIFactory.createTextField();
        txtTen = UIFactory.createTextField();
        txtSdt = UIFactory.createTextField();
        txtEmail = UIFactory.createTextField();
        txtChucVu = UIFactory.createTextField();
        txtLuong = UIFactory.createTextField();
        txtDiaChi = UIFactory.createTextField();
        cbGioiTinh = UIFactory.createComboBox(new String[]{"Nam", "Nữ", "Khác"});

        // 4. Gom nhóm Inputs vào mảng Component[]
        Component[] nhanVienInputs = {
            new InputGroup("Mã NV:", txtMa),
            new InputGroup("Tên:", txtTen),
            new InputGroup("SĐT:", txtSdt),
            new InputGroup("Email:", txtEmail),
            new InputGroup("Chức vụ:", txtChucVu),
            new InputGroup("Lương:", txtLuong),
            new InputGroup("Giới tính:", cbGioiTinh),
            new InputGroup("Địa chỉ:", txtDiaChi)
        };
        
        // Tải ảnh minh họa cho phần Nhân viên
        ImageIcon leftIcon = new ImageIcon(new ImageIcon(getClass().getResource("/img/leftNV.png"))
                .getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));

        ImageIcon rightIcon = new ImageIcon(new ImageIcon(getClass().getResource("/img/rightNV.png"))
                .getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));

        
        // 5. Khởi tạo FormPanel (Đã sửa truyền đúng mảng dữ liệu nhanVienInputs)
        FormPanel nhanVienForm = new FormPanel("Thông tin nhân viên", nhanVienInputs, leftIcon, rightIcon);

        // 6. Layout tổng thể
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);
        content.add(tablePanel, BorderLayout.CENTER);
        content.add(nhanVienForm, BorderLayout.SOUTH); // Đã sửa từ formPanel thành nhanVienForm

        add(content, BorderLayout.CENTER);
        loadFakeData();
    }

    private void loadFakeData() {
        DefaultTableModel model = (DefaultTableModel) tablePanel.getTable().getModel();
        for (int i = 1; i <= 20; i++) {
            model.addRow(new Object[]{"NV0"+i, "Nhân viên "+i, "09000000"+i, "nv"+i+"@test.com", "NV", "8tr", "Nam", "Hà Nội"});
        }
    }
}