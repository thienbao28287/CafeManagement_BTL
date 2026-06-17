package components;

import javax.swing.*;
import java.awt.*;

public class InputGroup extends JPanel {

    private static final long serialVersionUID = 1L;

    // Hằng số cấu hình
    private static final int GAP = 10;
    private static final int LABEL_WIDTH = 100;
    private static final int COMPONENT_HEIGHT = 32;
    private static final Color LABEL_COLOR = new Color(75, 85, 99);
    private static final Font LABEL_FONT = new Font("SansSerif", Font.BOLD, 14);

    public InputGroup(String labelText, JComponent inputComponent) {
        initComponents(labelText, inputComponent);
    }

    private void initComponents(String labelText, JComponent inputComponent) {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, GAP, 0));

        // Khởi tạo và thiết lập nhãn
        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        label.setForeground(LABEL_COLOR);
        label.setPreferredSize(new Dimension(LABEL_WIDTH, COMPONENT_HEIGHT));

        add(label);
        add(inputComponent);
    }
}