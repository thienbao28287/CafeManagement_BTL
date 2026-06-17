package view;

import controller.DatHangController;
import controller.TrangChuController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout outerCardLayout;
    private JPanel outerPanel;
    private CardLayout innerCardLayout;
    private JPanel innerContentPanel;
    private SidebarPanel sidebarPanel;
    private LoginPanel loginPanel;
    
    // Lưu trữ các panel để có thể truy xuất hoặc làm mới nếu cần
    private DatHangPanel datHangPanel;
    private HoaDonPanel hoaDonPanel;
    private TrangChuController trangChuController;

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

        // Khởi tạo các Panel
        this.datHangPanel = new DatHangPanel();
        this.hoaDonPanel = new HoaDonPanel();

        // Khởi tạo Controller và thiết lập kết nối
        DatHangController datHangController = new DatHangController(datHangPanel);
        datHangController.setHoaDonPanel(hoaDonPanel);
        datHangController.initEvents();

        // Nạp các phân hệ chức năng vào CardLayout
        TrangChuPanel trangChuPanel = new TrangChuPanel();
        this.trangChuController = new TrangChuController(trangChuPanel);
        trangChuController.loadData();
        innerContentPanel.add(trangChuPanel, "PANEL_TRANG_CHU");
        innerContentPanel.add(new KhachHangPanel(), "PANEL_KHACH_HANG");
        innerContentPanel.add(new NhanVienPanel(), "PANEL_NHAN_VIEN");
        innerContentPanel.add(new SanPhamPanel(), "PANEL_SAN_PHAM");
        innerContentPanel.add(new BanAnPanel(), "PANEL_BAN_AN");
        innerContentPanel.add(datHangPanel, "PANEL_DAT_HANG");
        innerContentPanel.add(hoaDonPanel, "PANEL_HOA_DON");

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(innerContentPanel, BorderLayout.CENTER);

        innerCardLayout.show(innerContentPanel, "PANEL_TRANG_CHU");

        return mainPanel;
    }

    public LoginPanel getLoginPanel() {
        return this.loginPanel;
    }

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
        if ("PANEL_TRANG_CHU".equals(cardName) && trangChuController != null) {
            trangChuController.loadData();
        }
    }
}