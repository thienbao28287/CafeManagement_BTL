package view;

import components.*;
import controller.KhachHangController;
import util.*;
import components.HeaderPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Point2D;

public class KhachHangPanel extends JPanel {
    private TablePanel tablePanel;
    private FormPanel formPanel;
    private KhachHangController controller;
    private JTextField txtMa, txtTen, txtSdt, txtDiaChi;

    public KhachHangPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(true);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new HeaderPanel("Khách hàng", "Quản lý danh sách khách hàng", 
            new Color(59, 26, 8), new Color(124, 58, 14), new Color(180, 83, 9)), 
            BorderLayout.NORTH);

        String[] columns = {"MÃ KH", "TÊN KH", "SĐT", "ĐỊA CHỈ"};
        tablePanel = new TablePanel(columns, "Tìm kiếm khách hàng...");

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

        formPanel = new FormPanel("Thông tin chi tiết khách hàng", inputs, 
            ImageUtil.getScaledIcon(getClass(), "/img/KH01.png", 220, 220), 
            ImageUtil.getScaledIcon(getClass(), "/img/KH02.png", 220, 220));

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);
        content.add(tablePanel, BorderLayout.CENTER);
        content.add(formPanel, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);

        this.controller = new KhachHangController(this);
        this.controller.loadData();
        this.controller.initEvents();
        
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablePanel.getTable().getSelectedRow() != -1) {
                fillFormFromSelectedRow();
            }
        });
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
    
    private void fillFormFromSelectedRow() {
        int row = tablePanel.getTable().getSelectedRow();
        
        txtMa.setText(getValue(row, 0));
        txtTen.setText(getValue(row, 1));
        txtSdt.setText(getValue(row, 2));
        txtDiaChi.setText(getValue(row, 3));
        
        txtMa.setEditable(false);
    }

    private String getValue(int row, int col) {
        Object value = tablePanel.getTable().getValueAt(row, col);
        return (value == null) ? "" : value.toString();
    }

    public void clearForm() {
        txtMa.setText(""); txtTen.setText(""); txtSdt.setText(""); txtDiaChi.setText("");
        txtMa.setEditable(true);
        tablePanel.getTable().clearSelection();
    }

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