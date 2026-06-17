package repository;

import database.DBConnection;
import model.ChiTietHoaDon;
import java.sql.*;
import java.util.*;

public class DatHangRepositoryImpl implements IDatHangRepository {
	public List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon) {
	    List<ChiTietHoaDon> list = new ArrayList<>();
	    // Dùng JOIN để lấy TenSanPham từ bảng SanPham
	    String sql = "SELECT ct.*, sp.TenSanPham " +
	                 "FROM ChiTietHoaDon ct " +
	                 "JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham " +
	                 "WHERE ct.MaHoaDon = ?";
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, maHoaDon);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                list.add(new ChiTietHoaDon(
	                    rs.getString("MaHoaDon"),
	                    rs.getString("MaSanPham"),
	                    rs.getString("TenSanPham"), // Lấy từ bảng SanPham qua JOIN
	                    rs.getInt("SoLuong"),
	                    rs.getDouble("DonGia")
	                ));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

    @Override
    public boolean insert(ChiTietHoaDon cthd) {
        String sql = "INSERT INTO ChiTietHoaDon VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cthd.getMaHoaDon()); ps.setString(2, cthd.getMaSanPham());
            ps.setInt(3, cthd.getSoLuong()); ps.setDouble(4, cthd.getDonGia());
            ps.setDouble(5, cthd.getThanhTien());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean update(ChiTietHoaDon cthd) {
        String sql = "UPDATE ChiTietHoaDon SET SoLuong=?, ThanhTien=? WHERE MaHoaDon=? AND MaSanPham=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cthd.getSoLuong()); ps.setDouble(2, cthd.getThanhTien());
            ps.setString(3, cthd.getMaHoaDon()); ps.setString(4, cthd.getMaSanPham());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean delete(String maHoaDon, String maSanPham) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon=? AND MaSanPham=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHoaDon); ps.setString(2, maSanPham);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean checkExists(String maHoaDon, String maSanPham) {
        String sql = "SELECT COUNT(*) FROM ChiTietHoaDon WHERE MaHoaDon=? AND MaSanPham=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHoaDon); ps.setString(2, maSanPham);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public ChiTietHoaDon getById(String maHoaDon, String maSanPham) {
        // Sửa câu SQL thành JOIN bảng SanPham
        String sql = "SELECT cthd.*, sp.TenSanPham " +
                     "FROM ChiTietHoaDon cthd " +
                     "JOIN SanPham sp ON cthd.MaSanPham = sp.MaSanPham " +
                     "WHERE cthd.MaHoaDon = ? AND cthd.MaSanPham = ?";
                     
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maHoaDon);
            ps.setString(2, maSanPham);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }    

    @Override
    public List<ChiTietHoaDon> searchByMaSanPham(String maSanPham) {
        List<ChiTietHoaDon> list = new java.util.ArrayList<>();
        String sql = "SELECT ct.*, sp.TenSanPham FROM ChiTietHoaDon ct " +
                     "JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham " +
                     "WHERE ct.MaSanPham LIKE ?";
        try (java.sql.Connection conn = database.DBConnection.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + maSanPham + "%");
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (java.sql.SQLException e) { e.printStackTrace(); }
        return list;
    }
    private ChiTietHoaDon mapRow(ResultSet rs) throws SQLException {
        return new ChiTietHoaDon(
            rs.getString("MaHoaDon"),
            rs.getString("MaSanPham"),
            rs.getString("TenSanPham"),
            rs.getInt("SoLuong"),
            rs.getDouble("DonGia")
        );
    }
}