package repository;

import database.DBConnection;
import model.SanPham;
import util.ComboItem;

import java.sql.*;
import java.util.*;

public class SanPhamRepositoryImpl implements ISanPhamRepository {
    @Override
    public boolean insert(SanPham sp) {
        String sql = "INSERT INTO SanPham VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setInt(3, sp.getSoLuong());
            ps.setString(4, sp.getLoai());
            ps.setDouble(5, sp.getGiaBan());
            ps.setString(6, sp.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public List<SanPham> findAll() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean update(SanPham sp) {
        String sql = "UPDATE SanPham SET TenSanPham=?, SoLuong=?, Loai=?, GiaBan=?, TrangThai=? WHERE MaSanPham=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getSoLuong());
            ps.setString(3, sp.getLoai());
            ps.setDouble(4, sp.getGiaBan());
            ps.setString(5, sp.getTrangThai());
            ps.setString(6, sp.getMaSanPham());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public boolean delete(String ma) {
        String sql = "DELETE FROM SanPham WHERE MaSanPham = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ma);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public List<SanPham> search(String key) {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE MaSanPham LIKE ? OR TenSanPham LIKE ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String p = "%" + key + "%";
            ps.setString(1, p); ps.setString(2, p);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean checkExists(String ma) {
        String sql = "SELECT COUNT(*) FROM SanPham WHERE MaSanPham = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ma);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) { return false; }
    }

    private SanPham mapRow(ResultSet rs) throws SQLException {
        return new SanPham(rs.getString("MaSanPham"), rs.getString("TenSanPham"), 
                           rs.getInt("SoLuong"), rs.getString("Loai"), 
                           rs.getDouble("GiaBan"), rs.getString("TrangThai"));
    }
    public List<ComboItem> getComboData() {
        List<ComboItem> list = new ArrayList<>();
        String sql = "SELECT MaSanPham, TenSanPham FROM SanPham";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Key là Mã, Value là Tên hiển thị
                list.add(new ComboItem(rs.getString("MaSanPham"), rs.getString("TenSanPham")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    @Override
    public void updateSoLuong(Connection conn, String maSP, int soLuongThayDoi) throws SQLException {
        String sql = "UPDATE SanPham SET SoLuong = SoLuong + ? WHERE MaSanPham = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongThayDoi);
            ps.setString(2, maSP);
            ps.executeUpdate();
        }
    }
}