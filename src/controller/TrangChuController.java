package controller;

import model.ThongKeDashboard;
import service.IThongKeService;
import service.ThongKeServiceImpl;
import util.CurrencyUtil;
import view.TrangChuPanel;

public class TrangChuController {
    private final TrangChuPanel view;
    private final IThongKeService service = new ThongKeServiceImpl();

    public TrangChuController(TrangChuPanel view) {
        this.view = view;
    }

    public void loadData() {
        ThongKeDashboard stats = service.getDashboardStats();
        view.updateStats(
                String.valueOf(stats.getSoKhachHang()),
                String.valueOf(stats.getSoNhanVien()),
                String.valueOf(stats.getSoBanAn()),
                String.valueOf(stats.getSoOrders()),
                CurrencyUtil.formatCurrency(stats.getDoanhThuHomNay()) + " đ",
                CurrencyUtil.formatCurrency(stats.getDoanhThuThangNay()) + " đ",
                CurrencyUtil.formatCurrency(stats.getTongDoanhThu()) + " đ",
                String.valueOf(stats.getSoOrdersDaThanhToan())
        );
    }
}
