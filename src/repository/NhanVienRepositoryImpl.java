package repository;

import database.DBConnection;
import model.NhanVien;
import java.sql.*;
import java.util.*;

public class NhanVienRepositoryImpl implements INhanVienRepository {
	private Connection conn;
    // --- 1. CÁC PHƯƠNG THỨC CRUD (Create, Read, Update, Delete) ---

    @Override
    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (MaNhanVien, TenNhanVien, SoDienThoai, Email, ChucVu, GioiTinh, DiaChi, Luong) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getTenNhanVien());
            ps.setString(3, nv.getSoDienThoai());
            ps.setString(4, nv.getEmail());
            ps.setString(5, nv.getChucVu());
            ps.setString(6, nv.getGioiTinh());
            ps.setString(7, nv.getDiaChi());
            ps.setDouble(8, nv.getLuong());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return false; 
        }
    }

    @Override
    public List<NhanVien> findAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRowToNhanVien(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET TenNhanVien=?, SoDienThoai=?, Email=?, ChucVu=?, GioiTinh=?, DiaChi=?, Luong=? WHERE MaNhanVien=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nv.getTenNhanVien());
            ps.setString(2, nv.getSoDienThoai());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getChucVu());
            ps.setString(5, nv.getGioiTinh());
            ps.setString(6, nv.getDiaChi());
            ps.setDouble(7, nv.getLuong());
            ps.setString(8, nv.getMaNhanVien());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public boolean delete(String maNhanVien) {
        String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maNhanVien);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // --- 2. CÁC PHƯƠNG THỨC TÌM KIẾM & KIỂM TRA ---

    @Override
    public List<NhanVien> search(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE MaNhanVien LIKE ? OR TenNhanVien LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRowToNhanVien(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean checkExists(String maNhanVien) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE MaNhanVien = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maNhanVien);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // --- 3. PHƯƠNG THỨC HỖ TRỢ (PRIVATE HELPERS) ---

    private NhanVien mapRowToNhanVien(ResultSet rs) throws SQLException {
        return new NhanVien(
            rs.getString("MaNhanVien"), 
            rs.getString("TenNhanVien"),
            rs.getString("SoDienThoai"), 
            rs.getString("Email"),
            rs.getString("ChucVu"), 
            rs.getString("GioiTinh"),
            rs.getString("DiaChi"), 
            rs.getDouble("Luong")
        );
    }
}