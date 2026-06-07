package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HeaderPanel extends JPanel {
    public HeaderPanel(MainFrame mainFrame) {
    	setBackground(new Color(0x120A04));
    	
    	
    	setPreferredSize(new Dimension(1200, 65));
    	
    	
    	Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x9C8A7A));
    	
    	
    	Border emptyBorder = BorderFactory.createEmptyBorder(10, 40, 10, 40);
    	
    	
    	setBorder(BorderFactory.createCompoundBorder(bottomBorder, emptyBorder));
    	
        setLayout(new GridBagLayout());
        
        JPanel Logo = new JPanel();
        Logo.setBackground(new Color(0x120A04));
        Logo.setLayout(new BorderLayout(4, 0)); 
        
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/img/logo.png")));
        Logo.add(logoLabel, BorderLayout.WEST);
        
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(0x120A04));
        textPanel.setLayout(new GridLayout(2, 1, 0, 0));
        Logo.add(textPanel, BorderLayout.CENTER);
        
        JLabel tittle = new JLabel("COFFEE SHOP");
        tittle.setFont(new Font("Arial", Font.BOLD, 24));
        tittle.setForeground(new Color(0xE8C99A));
        textPanel.add(tittle);
        
        JLabel lblNewLabel_1 = new JLabel("MANAGEMENT SYSTEM");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1.setForeground(new Color(0x8A6040));
        textPanel.add(lblNewLabel_1);
        
        GridBagConstraints gbc_Logo = new GridBagConstraints();
        gbc_Logo.gridx = 0;
        gbc_Logo.gridy = 0;
        gbc_Logo.weightx = 1.0;
        gbc_Logo.anchor = GridBagConstraints.WEST;
        add(Logo, gbc_Logo);
        
        JPanel hello = new JPanel();
        hello.setBackground(new Color(0x120A04));
        
        JLabel lblNewLabel = new JLabel("Xin chào A");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel.setForeground(new Color(0x8A6040));
        lblNewLabel.setBackground(new Color(0x120A04));
        hello.add(lblNewLabel);
        
        GridBagConstraints gbc_hello = new GridBagConstraints();
        gbc_hello.gridx = 1;
        gbc_hello.gridy = 0;
        gbc_hello.weightx = 1.0;
        gbc_hello.anchor = GridBagConstraints.EAST;
        add(hello, gbc_hello);
        
    }
}