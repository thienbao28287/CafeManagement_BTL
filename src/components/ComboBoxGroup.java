package components;

import javax.swing.*;
import util.UIFactory;
import java.awt.*;

public class ComboBoxGroup extends JPanel {

    private static final long serialVersionUID = 1L;
    private final JComboBox<String> comboBox;

    public ComboBoxGroup(String labelText, String[] items) {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(new Color(75, 85, 99));
        label.setPreferredSize(new Dimension(100, 32));

        comboBox = UIFactory.createComboBox(items);
        comboBox.setPreferredSize(new Dimension(200, 32));

        add(label);
        add(comboBox);
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public String getSelectedValue() {
        return (String) comboBox.getSelectedItem();
    }
}