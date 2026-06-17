package repository;

import database.DBConnection;
import model.KhachHang;
import java.sql.*;
import java.util.*;

public class KhachHangRepositoryImpl implements IKhachHangRepository {

    // --- 1. CÁC PHƯƠNG THỨC CRUD (Create, Read, Update, Delete) ---

    @Override
    public boolean insert(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (MaKhachHang, TenKhachHang, SoDienThoai, DiaChi) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getTenKhachHang());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getDiaChi());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return false; 
        }
    }

    @Override
    public List<KhachHang> findAll() {
        List<KhachHang> list = new ArrayList<>();

        String sql = "SELECT * FROM KhachHang WHERE MaKhachHang != 'KH_VANGLAI'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Repo: " + rs.getString("MaKhachHang"));

                list.add(mapRowToKhachHang(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean update(KhachHang kh) {
        String sql = "UPDATE KhachHang SET TenKhachHang=?, SoDienThoai=?, DiaChi=? WHERE MaKhachHang=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, kh.getTenKhachHang());
            ps.setString(2, kh.getSoDienThoai());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getMaKhachHang());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public boolean delete(String maKhachHang) {
        String sql = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maKhachHang);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // --- 2. CÁC PHƯƠNG THỨC TÌM KIẾM & KIỂM TRA ---

    @Override
    public List<KhachHang> search(String keyword) {
        List<KhachHang> list = new ArrayList<>();

        String sql = "SELECT * FROM KhachHang WHERE (MaKhachHang LIKE ? OR TenKhachHang LIKE ?) AND MaKhachHang != 'KH_VANGLAI'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    // DEBUG
                    System.out.println("KH: " + rs.getString("MaKhachHang"));

                    list.add(mapRowToKhachHang(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean checkExists(String maKhachHang) {
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE MaKhachHang = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maKhachHang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // --- 3. PHƯƠNG THỨC HỖ TRỢ (PRIVATE HELPERS) ---

    private KhachHang mapRowToKhachHang(ResultSet rs) throws SQLException {
        return new KhachHang(
            rs.getString("MaKhachHang"), 
            rs.getString("TenKhachHang"),
            rs.getString("SoDienThoai"), 
            rs.getString("DiaChi")
        );
    }
}