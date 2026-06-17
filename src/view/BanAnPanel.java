package view;

import components.*;
import components.HeaderPanel;
import controller.BanAnController;
import util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Point2D;

public class BanAnPanel extends JPanel {

    private TablePanel tablePanel;
    private FormPanel formPanel;
    private BanAnController controller;
    private DashboardBanAnPanel dashboardPanel;
    
    private JTextField txtMa, txtSoGhe, txtViTri;
    private JComboBox<String> cbTrangThai;

    public BanAnPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(true);
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        add(new HeaderPanel("Bàn ăn", "Quản lý bàn ăn", 
            new Color(59, 26, 8), new Color(124, 58, 14), new Color(180, 83, 9)), 
            BorderLayout.NORTH);
        
        initTableAndForm();
        
        this.controller = new BanAnController(this);
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

    private void initTableAndForm() {
        String[] columns = {"MÃ BÀN", "SỐ GHẾ", "TRẠNG THÁI", "VỊ TRÍ"};
        tablePanel = new TablePanel(columns, "Tìm kiếm bàn ăn...");

        txtMa = UIFactory.createTextField();
        txtSoGhe = UIFactory.createTextField();
        txtViTri = UIFactory.createTextField();
        cbTrangThai = UIFactory.createComboBox(new String[]{"Trống", "Đang dùng", "Đã đặt"});

        Component[] inputs = {
            new InputGroup("Mã bàn", txtMa),
            new InputGroup("Số ghế", txtSoGhe),
            new InputGroup("Trạng thái", cbTrangThai),
            new InputGroup("Vị trí", txtViTri)
        };

        formPanel = new FormPanel("Thông tin bàn ăn", inputs, 
        	    ImageUtil.getScaledIcon(getClass(), "/img/ba01.png", 220, 220), 
        	    ImageUtil.getScaledIcon(getClass(), "/img/ba02.png", 220, 220));

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);
        
        dashboardPanel = new DashboardBanAnPanel();
        JPanel topPanel = new JPanel(new BorderLayout(15, 0));
        topPanel.setOpaque(false);
        topPanel.add(dashboardPanel, BorderLayout.WEST);
        topPanel.add(tablePanel, BorderLayout.CENTER);

        content.add(topPanel, BorderLayout.CENTER);
        content.add(formPanel, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);

        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablePanel.getTable().getSelectedRow() != -1) {
                fillFormFromSelectedRow();
            }
        });
    }

    private void fillFormFromSelectedRow() {
        int row = tablePanel.getTable().getSelectedRow();
        txtMa.setText(tablePanel.getTable().getValueAt(row, 0).toString());
        txtSoGhe.setText(tablePanel.getTable().getValueAt(row, 1).toString());
        cbTrangThai.setSelectedItem(tablePanel.getTable().getValueAt(row, 2).toString());
        txtViTri.setText(tablePanel.getTable().getValueAt(row, 3).toString());
        txtMa.setEditable(false); 
    }

    public void clearForm() {
        txtMa.setText(""); txtSoGhe.setText(""); txtViTri.setText("");
        cbTrangThai.setSelectedIndex(0);
        getTable().clearSelection();
        txtMa.setEditable(true); 
    }

    public JTable getTable() { return tablePanel.getTable(); }
    public DefaultTableModel getTableModel() { return (DefaultTableModel) tablePanel.getTable().getModel(); }
    public TablePanel getTablePanel() { return tablePanel; } 
    
    public JTextField getTxtMa() { return txtMa; }
    public JTextField getTxtSoGhe() { return txtSoGhe; }
    public JTextField getTxtViTri() { return txtViTri; }
    public JComboBox<String> getCbTrangThai() { return cbTrangThai; }
    
    public JButton getBtnLuu() { return formPanel.getBtnLuu(); }
    public JButton getBtnXoa() { return formPanel.getBtnXoa(); }
    public JButton getBtnLamMoi() { return formPanel.getBtnLamMoi(); }
    public DashboardBanAnPanel getDashboardPanel() { return dashboardPanel; }
}