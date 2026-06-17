package components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusCard extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblIcon;
    private JLabel lblValue;
    private JLabel lblTitle;

// Trong class StatusCard.java
public StatusCard(String title, String value, Image image, Color bgColor, Color textColor) {
    setOpaque(true);
    setBackground(bgColor);
    setLayout(new BorderLayout(12, 0));
    setBorder(new EmptyBorder(16, 20, 16, 20));
    setPreferredSize(new Dimension(250, 90));
    putClientProperty(FlatClientProperties.STYLE, "arc: 18");

    // Xử lý ảnh: ép về 40x40 để trông cân đối
    if (image != null) {
        ImageIcon icon = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        lblIcon = new JLabel(icon);
        add(lblIcon, BorderLayout.WEST);
    }

    JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 0));
    textPanel.setOpaque(false);

    lblValue = new JLabel(value);
    lblValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
    lblValue.setForeground(textColor);

    lblTitle = new JLabel(title);
    lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    lblTitle.setForeground(textColor);

    textPanel.add(lblValue);
    textPanel.add(lblTitle);
    add(textPanel, BorderLayout.CENTER);
}
    public void setValue(String value) { lblValue.setText(value); }
    public void setTitle(String title) { lblTitle.setText(title); }
}