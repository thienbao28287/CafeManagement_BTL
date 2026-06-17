package components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class FormPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // Hằng số cấu hình
    private static final int SHADOW_SIZE = 6;
    private static final int CARD_ARC = 14;
    private static final Color BG_COLOR = Color.WHITE;

    private CustomButton btnLuu, btnLamMoi, btnXoa, btnHuy;

    public FormPanel(String title, Component[] inputComponents, Icon leftIcon, Icon rightIcon) {
        initComponents(title, inputComponents, leftIcon, rightIcon);
    }

    private void initComponents(String title, Component[] inputComponents, Icon leftIcon, Icon rightIcon) {
        setOpaque(false);
        setLayout(new BorderLayout());

        // 1. Panel nội dung chính
        JPanel card = new JPanel(new BorderLayout(20, 8));
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(18, 18, 18, 18));

        // Title
        JLabel lblTitle = new JLabel(title);
        lblTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold 15; foreground: #323232");
        card.add(lblTitle, BorderLayout.NORTH);

        // Icons (West & East)
        if (leftIcon != null) {
            JLabel lblLeftImg = new JLabel(leftIcon);
            lblLeftImg.setVerticalAlignment(JLabel.CENTER);
            card.add(lblLeftImg, BorderLayout.WEST);
        }

        if (rightIcon != null) {
            JLabel lblRightImg = new JLabel(rightIcon);
            lblRightImg.setVerticalAlignment(JLabel.CENTER);
            card.add(lblRightImg, BorderLayout.EAST);
        }

        // Input Container
        JPanel inputContainer = new JPanel(new GridBagLayout());
        inputContainer.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        int col = 0, row = 0;
        for (Component input : inputComponents) {
            gbc.gridx = col;
            gbc.gridy = row;
            inputContainer.add(input, gbc);
            col++;
            if (col > 1) { col = 0; row++; }
        }

        // Button Container
        JPanel buttonPanel = createButtonPanel();
        gbc.gridx = 0;
        gbc.gridy = row + 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 4, 2, 4);
        inputContainer.add(buttonPanel, gbc);

        card.add(inputContainer, BorderLayout.CENTER);

        // 2. Wrap trong Shadow Panel
        JPanel shadowPanel = createCard();
        shadowPanel.setLayout(new BorderLayout());
        shadowPanel.add(card, BorderLayout.CENTER);

        add(shadowPanel, BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        panel.setOpaque(false);
        btnLuu = new CustomButton("Lưu");
        btnLamMoi = new CustomButton("Làm mới");
        btnXoa = new CustomButton("Xóa");
        btnHuy = new CustomButton("Hủy");
        panel.add(btnLuu);
        panel.add(btnLamMoi);
        panel.add(btnXoa);
        panel.add(btnHuy);
        return panel;
    }

    private JPanel createCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Vẽ bóng đổ
                for (int i = 1; i <= SHADOW_SIZE; i++) {
                    float alpha = (float) (SHADOW_SIZE - i + 1) / (SHADOW_SIZE * 40);
                    g2.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
                    g2.fillRoundRect(SHADOW_SIZE - i, SHADOW_SIZE - i, 
                                     getWidth() - (SHADOW_SIZE - i) * 2, 
                                     getHeight() - (SHADOW_SIZE - i) * 2, CARD_ARC, CARD_ARC);
                }
                
                // Vẽ nền card
                g2.setColor(BG_COLOR);
                g2.fillRoundRect(SHADOW_SIZE, SHADOW_SIZE, 
                                 getWidth() - SHADOW_SIZE * 2, 
                                 getHeight() - SHADOW_SIZE * 2, CARD_ARC, CARD_ARC);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(6, 6, 6, 6));
        return card;
    }

    // Getters
    public CustomButton getBtnLuu() { return btnLuu; }
    public CustomButton getBtnLamMoi() { return btnLamMoi; }
    public CustomButton getBtnXoa() { return btnXoa; }
    public CustomButton getBtnHuy() { return btnHuy; }
}