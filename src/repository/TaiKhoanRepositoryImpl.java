package repository;

import database.DBConnection;
import model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanRepositoryImpl implements ITaiKhoanRepository {

    @Override
    public TaiKhoan checkLogin(String username, String password) {
        String sql = "SELECT * FROM TaiKhoan WHERE TenTaiKhoan = ? AND MatKhau = ?";
        Connection conn = DBConnection.getConnection();
        
        if (conn == null) return null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TaiKhoan tk = new TaiKhoan();
                    tk.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                    tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                    tk.setMatKhau(rs.getString("MatKhau"));
                    tk.setHoTen(rs.getString("HoTen"));
                    tk.setVaiTro(rs.getString("VaiTro"));
                    tk.setLuong(rs.getDouble("Luong"));
                    tk.setMaChucVu(rs.getString("MaChucVu"));
                    return tk;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn SQL Đăng nhập: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(conn);
        }
        return null;
    }
}