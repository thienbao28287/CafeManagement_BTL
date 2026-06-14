package view;

import components.FormPanel;
import components.HeaderPanel;
import components.InputGroup;
import components.CustomButton; 
import util.UIFactory;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BanAnPanel extends JPanel {

    private JTextField txtMaSoGhe;
    private JTextField txtViTri;
    private JComboBox<String> cbTrangThai;
    private JComboBox<String> cbTang;
    private JPanel gridPanel;

    public BanAnPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new HeaderPanel("🪑 Bàn ăn", "Quản lý bàn ăn", 
            new Color(30, 41, 59), new Color(51, 65, 85), new Color(71, 85, 105)), 
            BorderLayout.NORTH);

        txtMaSoGhe = UIFactory.createTextField();
        txtViTri = UIFactory.createTextField();
        cbTrangThai = UIFactory.createComboBox(new String[]{"Trống", "Đang sử dụng", "Đã đặt trước"});

        Component[] banAnInputs = {
            new InputGroup("Mã bàn:", txtMaSoGhe),
            new InputGroup("Trạng thái:", cbTrangThai),
            new InputGroup("Vị trí:", txtViTri)
        };

        ImageIcon leftIcon = util.ImageUtil.getScaledIcon(getClass(), "/img/leftNV.png", 220, 220);
        ImageIcon rightIcon = util.ImageUtil.getScaledIcon(getClass(), "/img/rightNV.png", 220, 220);

        FormPanel formPanel = new FormPanel("Thông tin bàn ăn", banAnInputs, leftIcon, rightIcon);

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);
        content.add(createDashboardPanel(), BorderLayout.CENTER);
        content.add(formPanel, BorderLayout.SOUTH);

        add(content, BorderLayout.CENTER);
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setOpaque(false);
        panel.add(createStatsPanel(), BorderLayout.WEST);
        panel.add(createTableMapPanel(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 0, 12));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(220, 0));
        panel.add(createStatCard("Tổng bàn", "36"));
        panel.add(createStatCard("Trống", "12"));
        panel.add(createStatCard("Đang dùng", "12"));
        panel.add(createStatCard("Đặt trước", "12"));
        return panel;
    }

    private JPanel createStatCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));
        card.putClientProperty("FlatLaf.style", "arc: 15");

        JLabel lblTitle = new JLabel(title);
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 18));

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        return card;
    }

    private JPanel createTableMapPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(10, 10));
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(new EmptyBorder(15, 15, 15, 15));
        wrapper.putClientProperty("FlatLaf.style", "arc: 15");

        // Header: Tinh chỉnh đậm nét hơn
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        JLabel lblTitle = new JLabel("Sơ đồ bàn ăn");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(30, 41, 59));
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        topPanel.setOpaque(false);
        cbTang = new JComboBox<>(new String[]{"Tầng 1", "Tầng 2"});
        cbTang.setPreferredSize(new Dimension(100, 30));
        topPanel.add(new JLabel("Tầng:"));
        topPanel.add(cbTang);
        
        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(topPanel, BorderLayout.EAST);
        wrapper.add(headerPanel, BorderLayout.NORTH);

        gridPanel = new JPanel(new GridLayout(3, 6, 12, 12));
        gridPanel.setOpaque(false);
        wrapper.add(gridPanel, BorderLayout.CENTER);

        loadTables(1);
        cbTang.addActionListener(e -> loadTables(cbTang.getSelectedIndex() + 1));
        return wrapper;
    }

    private void loadTables(int tang) {
        gridPanel.removeAll();
        int start = (tang == 1) ? 1 : 19;
        for (int i = 0; i < 18; i++) {
            String maBan = String.format("B%02d", start + i);
            String trangThai = ((start + i) % 3 == 0) ? "TRONG" : (((start + i) % 3 == 1) ? "DANG_DUNG" : "DAT_TRUOC");
            gridPanel.add(createTableButton(maBan, trangThai, "Tầng " + tang));
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private CustomButton createTableButton(String maBan, String trangThai, String viTri) {
        CustomButton btn = new CustomButton("<html><center>" + maBan + "<br><font size='2'>4 Ghế</font></center></html>");
        
        // Màu sắc trạng thái
        Color bg = switch (trangThai) {
            case "TRONG" -> new Color(34, 197, 94);
            case "DANG_DUNG" -> new Color(239, 68, 68);
            default -> new Color(245, 158, 11);
        };
        
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        // Bỏ bớt logic hover mặc định nếu cần trong CustomButton để màu nền không bị ghi đè

        btn.addActionListener(e -> {
            txtMaSoGhe.setText(maBan);
            txtViTri.setText(viTri);
            cbTrangThai.setSelectedItem(trangThai.equals("TRONG") ? "Trống" : 
                (trangThai.equals("DANG_DUNG") ? "Đang sử dụng" : "Đã đặt trước"));
        });

        return btn;
    }
}