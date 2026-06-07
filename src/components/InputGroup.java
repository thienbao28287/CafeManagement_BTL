package components;

import javax.swing.*;
import java.awt.*;

public class InputGroup extends JPanel {

    public InputGroup(String labelText, JComponent inputComponent) {

        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(new Color(75, 85, 99));
        label.setPreferredSize(new Dimension(100, 32));

        add(label);
        add(inputComponent);
    }
}