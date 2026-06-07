
package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout outerCardLayout;
    private JPanel outerPanel;
    private CardLayout innerCardLayout;
    private JPanel innerContentPanel;
    private SidebarPanel sidebarPanel;
    private LoginPanel loginPanel; 

    public MainFrame() {
        setTitle("COFFEE SHOP MANAGEMENT SYSTEM");
        setSize(1200, 950); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
   
        outerCardLayout = new CardLayout();
        outerPanel = new JPanel(outerCardLayout);
        this.loginPanel = new LoginPanel(this);
        
        outerPanel.add(loginPanel, "CARD_LOGIN");
        outerPanel.add(createMainDashboard(), "CARD_MAIN");
        add(outerPanel);
        outerCardLayout.show(outerPanel, "CARD_LOGIN");
    }

    private JPanel createMainDashboard() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        HeaderPanel headerPanel = new HeaderPanel(this);
        sidebarPanel = new SidebarPanel(this);

        innerCardLayout = new CardLayout();
        innerContentPanel = new JPanel(innerCardLayout);

        innerContentPanel.add(new TrangChuPanel(), "PANEL_TRANG_CHU");
        innerContentPanel.add(new KhachHangPanel(), "PANEL_KHACH_HANG");
        innerContentPanel.add(new NhanVienPanel(), "PANEL_NHAN_VIEN");
        innerContentPanel.add(new BanAnPanel(), "PANEL_BAN_AN");
        innerContentPanel.add(new DatHangPanel(), "PANEL_DAT_HANG");
        innerContentPanel.add(new HoaDonPanel(), "PANEL_HOA_DON");

        mainPanel.add(headerPanel, BorderLayout.NORTH);       
        mainPanel.add(sidebarPanel, BorderLayout.WEST);       
        mainPanel.add(innerContentPanel, BorderLayout.CENTER); 

        innerCardLayout.show(innerContentPanel, "PANEL_TRANG_CHU");

        return mainPanel;
    }

    // Hàm lấy LoginPanel ra ngoài phục vụ liên kết Controller
    public LoginPanel getLoginPanel() {
        return this.loginPanel;
    }

    // Hàm làm mới quyền cho SidebarPanel
    public void refreshSidebar() {
        if (this.sidebarPanel != null) {
            this.sidebarPanel.updatePermissions();
        }
    }

    public void switchOuterCard(String cardName) {
        outerCardLayout.show(outerPanel, cardName);
    }

    public void switchInnerCard(String cardName) {
        innerCardLayout.show(innerContentPanel, cardName);
    }
}