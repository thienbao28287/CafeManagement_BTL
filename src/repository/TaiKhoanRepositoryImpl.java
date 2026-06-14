package repository;

import database.DBConnection;
import model.TaiKhoan;
import java.sql.*;

public class TaiKhoanRepositoryImpl implements ITaiKhoanRepository {
    @Override
    public TaiKhoan login(String username, String password) {
        String sql = "SELECT * FROM TaiKhoan WHERE TenTaiKhoan = ? AND MatKhau = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                    rs.getString("MaTaiKhoan"),
                    rs.getString("TenTaiKhoan"),
                    rs.getString("MatKhau"),
                    rs.getString("VaiTro")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}