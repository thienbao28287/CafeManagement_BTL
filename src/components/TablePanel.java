package components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TablePanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private JTextField txtTimKiem;
    private SearchButton btnSearch;
    public JTable getTable() { return table; }
    public JTextField getTxtTimKiem() { return txtTimKiem; }
    public SearchButton getBtnSearch() { return btnSearch; }
    public DefaultTableModel getTableModel() { return (DefaultTableModel) table.getModel(); }
    public TablePanel(String[] columnTitles, String searchPlaceholder) {
        setOpaque(false);
        setLayout(new BorderLayout());

        // 1. Tạo Card bao bọc toàn bộ nội dung
        JPanel tableCard = createCard();
        tableCard.setLayout(new BorderLayout(0, 15));

        // 2. Thanh tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        searchPanel.setOpaque(false);
        searchPanel.setBorder(new EmptyBorder(10, 0, 0, 0)); // Đẩy xuống 10px
        
        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, searchPlaceholder);
        txtTimKiem.putClientProperty(FlatClientProperties.STYLE, "arc:10; focusWidth:1; borderWidth:1; borderColor:#E4E7F0; font:14");
        txtTimKiem.setPreferredSize(new Dimension(400, 32));

       
        this.btnSearch = new SearchButton("Tìm kiếm");
        
        searchPanel.add(txtTimKiem);
        searchPanel.add(this.btnSearch);
        
        table = new JTable(new DefaultTableModel(columnTitles, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(35);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setBorder(null);

        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        styleTableHeader();
        
       
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(null);
        scrollPane.getViewport().setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "border:0,0,0,0");

        
        tableCard.add(searchPanel, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        add(tableCard, BorderLayout.CENTER);
    }

    private void styleTableHeader() {
        JTableHeader header = table.getTableHeader();
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
                return lbl;
            }
        });
    }

    private JPanel createCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int arc = 14;
                int shadowSize = 6;
                
                // Vẽ bóng đổ 4 hướng nhạt
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
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(6, 6, 6, 6)); 
        return card;
    }
    public JTextField getSearchField() {
        return txtTimKiem;
    }
	public void setTable(JTable table) {
		this.table = table;
	}
	public void setTxtTimKiem(JTextField txtTimKiem) {
		this.txtTimKiem = txtTimKiem;
	}
	public void setBtnSearch(SearchButton btnSearch) {
		this.btnSearch = btnSearch;
	}
}