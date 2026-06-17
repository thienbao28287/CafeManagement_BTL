package repository;

import database.DBConnection;
import model.ThongKeDashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeRepositoryImpl implements IThongKeRepository {

    @Override
    public ThongKeDashboard getDashboardStats() {
        int soKhachHang = count("KhachHang");
        int soNhanVien = count("NhanVien");
        int soBanAn = count("BanAn");
        int soOrders = count("HoaDon");
        int soOrdersDaThanhToan = countWhere("HoaDon", "TrangThai = N'Đã thanh toán'");
        double doanhThuHomNay = sumDoanhThu("CAST(NgayLap AS DATE) = CAST(GETDATE() AS DATE)");
        double doanhThuThangNay = sumDoanhThu(
                "MONTH(NgayLap) = MONTH(GETDATE()) AND YEAR(NgayLap) = YEAR(GETDATE())");
        double tongDoanhThu = sumDoanhThu(null);

        return new ThongKeDashboard(
                soKhachHang, soNhanVien, soBanAn, soOrders,
                doanhThuHomNay, doanhThuThangNay, tongDoanhThu, soOrdersDaThanhToan);
    }

    private int count(String table) {
        String sql = "SELECT COUNT(*) FROM " + table;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int countWhere(String table, String condition) {
        String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + condition;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private double sumDoanhThu(String dateCondition) {
        String sql = "SELECT ISNULL(SUM(TongTien), 0) FROM HoaDon WHERE TrangThai = N'Đã thanh toán'";
        if (dateCondition != null) {
            sql += " AND " + dateCondition;
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getDouble(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
