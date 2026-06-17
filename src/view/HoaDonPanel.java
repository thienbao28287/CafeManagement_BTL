package view;

import components.CustomButton;
import components.HeaderPanel;
import components.TablePanel;
import controller.HoaDonController;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.ChiTietHoaDon;

public class HoaDonPanel extends JPanel {

    private TablePanel leftTablePanel;
    private JPanel rightDetailCard;
    private JLabel lblMaHoaDonValue, lblMaBanValue, lblTrangThaiValue;
    private JTable tableChiTietMon;
    private DefaultTableModel modelChiTietMon;
    private JLabel lblTongTienValue;
    private HoaDonController controller;
    private CustomButton btnXoa;
    public HoaDonPanel() {
        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new HeaderPanel(
                "Hóa đơn",
                "Quản lý danh sách và chi tiết hóa đơn bán hàng",
                new Color(120, 53, 4),
                new Color(194, 65, 12),
                new Color(234, 88, 12)
        ), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(24, 0));
        contentPanel.setOpaque(false);
        contentPanel.add(createLeftInvoiceTablePanel(), BorderLayout.CENTER);
        contentPanel.add(createRightDetailCard(), BorderLayout.EAST);

        add(contentPanel, BorderLayout.CENTER);

        controller = new HoaDonController(this);
        controller.loadData();
        controller.initEvents();
    }

    private JPanel createLeftInvoiceTablePanel() {
        String[] columns = {"MÃ HĐ", "BÀN", "TỔNG TIỀN", "TRẠNG THÁI"};
        leftTablePanel = new TablePanel(columns, "Tìm kiếm mã order, bàn...");
        btnXoa = new CustomButton("Xóa");
        btnXoa.setBackground(new Color(239, 68, 68)); // Màu đỏ
        btnXoa.setForeground(Color.WHITE);
        
        return leftTablePanel;
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
    @Override
    public void addNotify() {
        super.addNotify();
        if (controller != null) {
            controller.loadData(); // Tự động load lại danh sách HĐ mới nhất
        }
    }
    private JPanel createRightDetailCard() {
        rightDetailCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 14;
                int shadowSize = 6;
                for (int i = 1; i <= shadowSize; i++) {
                    float alpha = (float) (shadowSize - i + 1) / (shadowSize * 40);
                    g2.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
                    g2.fillRoundRect(shadowSize - i, shadowSize - i, getWidth() - (shadowSize - i) * 2, getHeight() - (shadowSize - i) * 2, arc, arc);
                }
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        rightDetailCard.setLayout(new BorderLayout(0, 16));
        rightDetailCard.setOpaque(false);
        rightDetailCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        rightDetailCard.setPreferredSize(new Dimension(500, 0));

        JPanel topInfoPanel = new JPanel(new GridBagLayout());
        topInfoPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 12);

        JLabel lblTitle = new JLabel("Chi tiết hóa đơn");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(new Color(40, 40, 40));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        topInfoPanel.add(lblTitle, gbc);

        gbc.gridy = 1; gbc.gridwidth = 2;
        topInfoPanel.add(Box.createVerticalStrut(8), gbc);
        gbc.gridwidth = 1; gbc.weightx = 0.5;

        JLabel lblMaHoaDon = new JLabel("Mã hóa đơn: ");
        lblMaHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblMaHoaDon.setForeground(Color.GRAY);
        lblMaHoaDonValue = new JLabel("-");
        lblMaHoaDonValue.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JPanel pnlMaHD = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlMaHD.setOpaque(false);
        pnlMaHD.add(lblMaHoaDon);
        pnlMaHD.add(lblMaHoaDonValue);
        gbc.gridx = 0; gbc.gridy = 2;
        topInfoPanel.add(pnlMaHD, gbc);

        JLabel lblMaBan = new JLabel("Mã bàn: ");
        lblMaBan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblMaBan.setForeground(Color.GRAY);
        lblMaBanValue = new JLabel("-");
        lblMaBanValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JPanel pnlMaBan = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlMaBan.setOpaque(false);
        pnlMaBan.add(lblMaBan);
        pnlMaBan.add(lblMaBanValue);
        gbc.gridx = 1; gbc.gridy = 2;
        topInfoPanel.add(pnlMaBan, gbc);

        JLabel lblTrangThai = new JLabel("Trạng thái: ");
        lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTrangThai.setForeground(Color.GRAY);
        lblTrangThaiValue = new JLabel(" - ");
        lblTrangThaiValue.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTrangThaiValue.setForeground(new Color(22, 163, 74));
        lblTrangThaiValue.setBackground(new Color(220, 252, 231));
        lblTrangThaiValue.setOpaque(true);
        lblTrangThaiValue.setBorder(BorderFactory.createLineBorder(new Color(187, 247, 208), 1, true));
        JPanel pnlTrangThai = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlTrangThai.setOpaque(false);
        pnlTrangThai.add(lblTrangThai);
        pnlTrangThai.add(lblTrangThaiValue);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        topInfoPanel.add(pnlTrangThai, gbc);

        rightDetailCard.add(topInfoPanel, BorderLayout.NORTH);

        JPanel centerGridPanel = new JPanel(new BorderLayout(0, 8));
        centerGridPanel.setOpaque(false);
        JLabel lblDanhSachMon = new JLabel("Danh sách món");
        lblDanhSachMon.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachMon.setForeground(new Color(60, 60, 60));
        centerGridPanel.add(lblDanhSachMon, BorderLayout.NORTH);

        String[] itemColumns = {"TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
        modelChiTietMon = new DefaultTableModel(itemColumns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        
        tableChiTietMon = new JTable(modelChiTietMon);
        tableChiTietMon.setRowHeight(32);
        tableChiTietMon.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tableChiTietMon.setShowGrid(false);
        tableChiTietMon.setIntercellSpacing(new Dimension(0, 0));
        
        JTableHeader header = tableChiTietMon.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);
        header.setBorder(null);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                lbl.setBackground(new Color(247, 247, 253));
                lbl.setForeground(new Color(139, 143, 199));
                lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                lbl.setOpaque(true);
                return lbl;
            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tableChiTietMon.getColumnCount(); i++) {
            tableChiTietMon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tableChiTietMon.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableChiTietMon.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableChiTietMon.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableChiTietMon.getColumnModel().getColumn(3).setPreferredWidth(80);

        JScrollPane scrollPaneDetail = new JScrollPane(tableChiTietMon);
        scrollPaneDetail.setBorder(null);
        scrollPaneDetail.setBorder(BorderFactory.createEmptyBorder()); // Bỏ viền của ScrollPane
        scrollPaneDetail.setViewportBorder(BorderFactory.createEmptyBorder()); // Bỏ viền của vùng hiển thị
        scrollPaneDetail.getViewport().setBackground(Color.WHITE);
        centerGridPanel.add(scrollPaneDetail, BorderLayout.CENTER);
        rightDetailCard.add(centerGridPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        bottomPanel.setOpaque(false);
        JLabel lblTongTien = new JLabel("Tổng tiền: ");
        lblTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTongTien.setForeground(Color.GRAY);
        lblTongTienValue = new JLabel("0 đ");
        lblTongTienValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongTienValue.setForeground(new Color(20, 20, 20));
        bottomPanel.add(lblTongTien);
        bottomPanel.add(lblTongTienValue);
        rightDetailCard.add(bottomPanel, BorderLayout.SOUTH);

        return rightDetailCard;
    }


    public void showInvoiceDetail(String maHoaDon, String maBan, String tongTien, String trangThai, List<ChiTietHoaDon> chiTietHoaDonList) {
        lblMaHoaDonValue.setText(maHoaDon == null ? "-" : maHoaDon);
        lblMaBanValue.setText(maBan == null ? "-" : maBan);
        lblTrangThaiValue.setText(trangThai == null ? "-" : trangThai);
        lblTongTienValue.setText(tongTien == null ? "0 đ" : tongTien + " đ");

        modelChiTietMon.setRowCount(0);
        if (chiTietHoaDonList != null) {
            for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonList) {
                modelChiTietMon.addRow(new Object[]{
                        chiTietHoaDon.getTenSanPham(),
                        chiTietHoaDon.getSoLuong(),
                        util.CurrencyUtil.formatCurrency(chiTietHoaDon.getDonGia()),
                        util.CurrencyUtil.formatCurrency(chiTietHoaDon.getThanhTien())
                });
            }
        }
    }
    public void refreshData() {
        if (controller != null) {
            controller.loadData();
        }
    }
    public TablePanel getTablePanel() { return leftTablePanel; }
    public DefaultTableModel getTableModel() { return (DefaultTableModel) leftTablePanel.getTable().getModel(); }
    public JTable getDetailTable() { return tableChiTietMon; }
    public DefaultTableModel getDetailTableModel() { return modelChiTietMon; }
}