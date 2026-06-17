package main;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controller.LoginController;
import repository.ITaiKhoanRepository;
import repository.TaiKhoanRepositoryImpl;
import service.ITaiKhoanService;
import service.TaiKhoanServiceImpl;
import view.LoginPanel;
import view.MainFrame;

public class MainApplication {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            LoginPanel loginPanel = mainFrame.getLoginPanel();
            ITaiKhoanRepository repo = new TaiKhoanRepositoryImpl();
            ITaiKhoanService service = new TaiKhoanServiceImpl(repo);
            LoginController controller =
                    new LoginController(loginPanel, service, mainFrame);
            loginPanel.setController(controller);
            mainFrame.setVisible(true);
        });
    }
}