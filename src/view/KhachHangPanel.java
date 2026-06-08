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

public class KhachHangPanel extends JPanel {
    private TablePanel tablePanel;
    private JTextField txtMa, txtTen, txtSdt, txtDiaChi, txtEmail, txtNgaySinh;

    public KhachHangPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Header
        add(new HeaderPanel("👥 Khách hàng", "Quản lý danh sách", 
        		new Color(59, 26, 8), new Color(124, 58, 14), new Color(180, 83, 9)),  
        	    BorderLayout.NORTH);

        // 2. Bảng
        String[] columns = {"MÃ KH", "TÊN KH", "SĐT", "ĐIẠ CHỈ", "EMAIL", "NGÀY SINH"};
        tablePanel = new TablePanel(columns, "Tìm kiếm khách hàng...");

        // 3. Khởi tạo Inputs
        txtMa = UIFactory.createTextField();
        txtTen = UIFactory.createTextField();
        txtSdt = UIFactory.createTextField();
        txtDiaChi = UIFactory.createTextField();
        txtEmail = UIFactory.createTextField();
        txtNgaySinh = UIFactory.createTextField();

        // 4. Gom nhóm Inputs vào mảng Component[]
        Component[] khachHangInputs = {
            new InputGroup("Mã KH:", txtMa),
            new InputGroup("Tên KH:", txtTen),
            new InputGroup("SĐT:", txtSdt),
            new InputGroup("Địa chỉ:", txtDiaChi),
            new InputGroup("Email:", txtEmail),
            new InputGroup("Ngày sinh:", txtNgaySinh)
        };

     // Tải ảnh minh họa cho phần Khách hàng sử dụng ImageUtil để chống vỡ ảnh
        ImageIcon leftIcon = util.ImageUtil.getScaledIcon(getClass(), "/img/leftNV.png", 220, 220);
        ImageIcon rightIcon = util.ImageUtil.getScaledIcon(getClass(), "/img/rightNV.png", 220, 220);

        // 5. Khởi tạo FormPanel (Truyền 2 ảnh đã được làm mịn vào hai bên)
        FormPanel formPanel = new FormPanel("Thông tin chi tiết khách hàng", khachHangInputs, leftIcon, rightIcon);

        // 6. Layout tổng thể
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);
        content.add(tablePanel, BorderLayout.CENTER);
        content.add(formPanel, BorderLayout.SOUTH);

        add(content, BorderLayout.CENTER);
        loadFakeData();
    }

    private void loadFakeData() {
        DefaultTableModel model = (DefaultTableModel) tablePanel.getTable().getModel();
        model.addRow(new Object[]{"KH001", "Nguyễn Văn A", "0901234567", "Hà Nội", "vana@gmail.com", "15/05/1995"});
        model.addRow(new Object[]{"KH002", "Trần Thị B", "0912345678", "Đà Nẵng", "thib@gmail.com", "20/08/1998"});
    }
}