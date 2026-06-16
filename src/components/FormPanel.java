package components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class FormPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomButton btnLuu, btnLamMoi, btnXoa, btnHuy;

    // Bổ sung thêm tham số leftIcon và rightIcon vào constructor
    public FormPanel(String title, Component[] inputComponents, Icon leftIcon, Icon rightIcon) {
        setOpaque(false);
        setLayout(new BorderLayout());

        // 1. Tạo JPanel chứa nội dung (Card)
        // Thay đổi hgap thành 20 để tạo khoảng cách thông thoáng giữa Ảnh - Nhập liệu - Ảnh
        JPanel card = new JPanel(new BorderLayout(20, 8));
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(18, 18, 18, 18));

        // Title (Nằm ở phía trên cùng, trải dài từ trái sang phải)
        JLabel lblTitle = new JLabel(title);
        lblTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold 15; foreground: #323232");
        card.add(lblTitle, BorderLayout.NORTH);

        // --- Ảnh bên TRÁI (WEST) ---
        if (leftIcon != null) {
            JLabel lblLeftImg = new JLabel(leftIcon);
            lblLeftImg.setVerticalAlignment(JLabel.CENTER); // Căn giữa ảnh theo chiều dọc
            card.add(lblLeftImg, BorderLayout.WEST);
        }

        // --- Khu vực chứa Inputs (CENTER) ---
        JPanel inputContainer = new JPanel(new GridBagLayout());
        inputContainer.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6); // Tăng nhẹ khoảng cách giữa các input cho đẹp
        gbc.anchor = GridBagConstraints.WEST;

        int col = 0, row = 0;
        for (Component input : inputComponents) {
            gbc.gridx = col; gbc.gridy = row;
            inputContainer.add(input, gbc);
            col++;
            if (col > 1) { col = 0; row++; }
        }

        // Khu vực chứa Buttons (Nằm dưới cùng của GridBagLayout)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        buttonPanel.setOpaque(false);
        btnLuu = new CustomButton("Lưu");
        btnLamMoi = new CustomButton("Làm mới");
        btnXoa = new CustomButton("Xóa");
        btnHuy = new CustomButton("Hủy");
        buttonPanel.add(btnLuu); buttonPanel.add(btnLamMoi); buttonPanel.add(btnXoa); buttonPanel.add(btnHuy);

        gbc.gridx = 0; gbc.gridy = row + 1; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; gbc.insets = new Insets(15, 4, 2, 4);
        inputContainer.add(buttonPanel, gbc);
        
        card.add(inputContainer, BorderLayout.CENTER);

        // --- Ảnh bên PHẢI (EAST) ---
        if (rightIcon != null) {
            JLabel lblRightImg = new JLabel(rightIcon);
            lblRightImg.setVerticalAlignment(JLabel.CENTER); // Căn giữa ảnh theo chiều dọc
            card.add(lblRightImg, BorderLayout.EAST);
        }

        // 2. Tạo Shadow Panel (Card có bóng đổ đổ phía dưới)
        JPanel shadowPanel = createCard(); 
        shadowPanel.setLayout(new BorderLayout());
        shadowPanel.add(card, BorderLayout.CENTER);

        add(shadowPanel, BorderLayout.CENTER);
    }
    public CustomButton getBtnLuu() {
        return btnLuu;
    }

    public CustomButton getBtnLamMoi() {
        return btnLamMoi;
    }

    public CustomButton getBtnXoa() {
        return btnXoa;
    }

    public CustomButton getBtnHuy() {
        return btnHuy;
    }

    private JPanel createCard() {
        JPanel card = new JPanel() {
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
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(6, 6, 6, 6)); 
        return card;
    }
    public void addButtons(JButton[] buttons) {
        // Giả sử FormPanel của bạn có một JPanel chứa các nút, 
        // ví dụ tên là buttonPanel (hãy kiểm tra xem trong FormPanel của bạn nó tên gì)
        // Nếu chưa có, bạn có thể tạo mới một JPanel để chứa các nút này.
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        
        for (JButton btn : buttons) {
            buttonPanel.add(btn);
        }
        
        // Thêm panel chứa nút vào FormPanel. 
        // Tùy theo layout của FormPanel, bạn có thể dùng add(buttonPanel, BorderLayout.SOUTH);
        this.add(buttonPanel); 
        
        // Yêu cầu giao diện cập nhật lại
        this.revalidate();
        this.repaint();
    }
}