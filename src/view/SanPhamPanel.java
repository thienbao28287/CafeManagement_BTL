package view;

import components.FormPanel;
import components.HeaderPanel;
import components.InputGroup;
import components.TablePanel;
import util.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SanPhamPanel extends JPanel {
    private TablePanel tablePanel;
    private JTextField txtMa, txtTen, txtLoai, txtSoLuong, txtGiaBan;
    private JComboBox<String> cbTrangThai;

    public SanPhamPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Header (Giữ tông màu ấm đồng bộ với hệ thống)
        add(new HeaderPanel("📦 Sản phẩm", "Quản lý danh mục sản phẩm", 
                new Color(59, 26, 8), new Color(124, 58, 14), new Color(180, 83, 9)), 
                BorderLayout.NORTH);

        // 2. Bảng hiển thị danh sách sản phẩm
        String[] columns = {"MÃ SP", "TÊN SẢN PHẨM", "LOẠI", "SỐ LƯỢNG", "GIÁ BÁN", "TRẠNG THÁI"};
        tablePanel = new TablePanel(columns, "Tìm kiếm sản phẩm...");

        // 3. Khởi tạo các ô nhập liệu (Inputs)
        txtMa = UIFactory.createTextField();
        txtTen = UIFactory.createTextField();
        txtLoai = UIFactory.createTextField();
        txtSoLuong = UIFactory.createTextField();
        txtGiaBan = UIFactory.createTextField();
        
        // Trạng thái sản phẩm (Ví dụ: Còn hàng, Hết hàng, Ngừng kinh doanh)
        cbTrangThai = UIFactory.createComboBox(new String[]{"Còn hàng", "Hết hàng", "Ngừng bán"});

        // 4. Gom nhóm Inputs vào mảng Component[] qua InputGroup
        Component[] sanPhamInputs = {
            new InputGroup("Mã SP:", txtMa),
            new InputGroup("Tên sản phẩm:", txtTen),
            new InputGroup("Loại sản phẩm:", txtLoai),
            new InputGroup("Số lượng:", txtSoLuong),
            new InputGroup("Giá bán:", txtGiaBan),
            new InputGroup("Trạng thái:", cbTrangThai)
        };
        
        // Tải ảnh minh họa cho phần Sản phẩm sử dụng ImageUtil chống vỡ hình
        ImageIcon leftIcon = util.ImageUtil.getScaledIcon(getClass(), "/img/leftNV.png", 220, 220);
        ImageIcon rightIcon = util.ImageUtil.getScaledIcon(getClass(), "/img/rightNV.png", 220, 220);

        // 5. Khởi tạo FormPanel nhập liệu thông tin sản phẩm
        FormPanel sanPhamForm = new FormPanel("Thông tin sản phẩm", sanPhamInputs, leftIcon, rightIcon);

        // 6. Layout tổng thể (Bảng ở CENTER, Form nhập liệu ở SOUTH)
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);
        content.add(tablePanel, BorderLayout.CENTER);
        content.add(sanPhamForm, BorderLayout.SOUTH); 

        add(content, BorderLayout.CENTER);
        loadFakeData();
    }

    /**
     * Nạp dữ liệu chạy thử (Fake data) cho bảng sản phẩm
     */
    private void loadFakeData() {
        DefaultTableModel model = (DefaultTableModel) tablePanel.getTable().getModel();
        
        // Thêm vài dòng dữ liệu mẫu trực quan
        model.addRow(new Object[]{"SP01", "Cà phê Muối", "Cà phê", "50", "35.000 đ", "Còn hàng"});
        model.addRow(new Object[]{"SP02", "Trà sữa Ô long", "Trà sữa", "100", "40.000 đ", "Còn hàng"});
        model.addRow(new Object[]{"SP03", "Bánh Croissant", "Bánh ngọt", "0", "30.000 đ", "Hết hàng"});
        
        // Vòng lặp tạo thêm dữ liệu cuộn cho TablePanel
        for (int i = 4; i <= 20; i++) {
            String status = (i % 5 == 0) ? "Hết hàng" : "Còn hàng";
            model.addRow(new Object[]{
                "SP0" + i, 
                "Sản phẩm mẫu " + i, 
                "Đồ uống", 
                String.valueOf(10 + i), 
                (25 + i) + ".000 đ", 
                status
            });
        }
    }

    // --- Hệ thống Getters đóng vai trò cầu nối dữ liệu sang Controller để xử lý sự kiện ---
    public TablePanel getTablePanel() { return tablePanel; }
    public JTable getTable() { return tablePanel.getTable(); }
    public JTextField getTxtTimKiem() { return tablePanel.getTxtTimKiem(); }

    public JTextField getTxtMa() { return txtMa; }
    public JTextField getTxtTen() { return txtTen; }
    public JTextField getTxtLoai() { return txtLoai; }
    public JTextField getTxtSoLuong() { return txtSoLuong; }
    public JTextField getTxtGiaBan() { return txtGiaBan; }
    public JComboBox<String> getCbTrangThai() { return cbTrangThai; }
}