package model;

public class ThongKeDashboard {
    private final int soKhachHang;
    private final int soNhanVien;
    private final int soBanAn;
    private final int soOrders;
    private final double doanhThuHomNay;
    private final double doanhThuThangNay;
    private final double tongDoanhThu;
    private final int soOrdersDaThanhToan;

    public ThongKeDashboard(int soKhachHang, int soNhanVien, int soBanAn, int soOrders,
                            double doanhThuHomNay, double doanhThuThangNay, double tongDoanhThu,
                            int soOrdersDaThanhToan) {
        this.soKhachHang = soKhachHang;
        this.soNhanVien = soNhanVien;
        this.soBanAn = soBanAn;
        this.soOrders = soOrders;
        this.doanhThuHomNay = doanhThuHomNay;
        this.doanhThuThangNay = doanhThuThangNay;
        this.tongDoanhThu = tongDoanhThu;
        this.soOrdersDaThanhToan = soOrdersDaThanhToan;
    }

    public int getSoKhachHang() { return soKhachHang; }
    public int getSoNhanVien() { return soNhanVien; }
    public int getSoBanAn() { return soBanAn; }
    public int getSoOrders() { return soOrders; }
    public double getDoanhThuHomNay() { return doanhThuHomNay; }
    public double getDoanhThuThangNay() { return doanhThuThangNay; }
    public double getTongDoanhThu() { return tongDoanhThu; }
    public int getSoOrdersDaThanhToan() { return soOrdersDaThanhToan; }
}
