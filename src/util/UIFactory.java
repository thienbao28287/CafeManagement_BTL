package util;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;

public class UIFactory {
    public static JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.putClientProperty(FlatClientProperties.STYLE, "arc:8; borderWidth:1; borderColor:#E4E7F0; font: 14");
        txt.setPreferredSize(new Dimension(200, 40));
        return txt;
    }

    public static JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> cb = new JComboBox<>(items);
        cb.putClientProperty(FlatClientProperties.STYLE, "font: 14");
        cb.setPreferredSize(new Dimension(200, 40));
        return cb;
    }
}
