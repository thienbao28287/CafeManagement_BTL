package repository;

import database.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;

public class HoaDonRepositoryImpl implements IHoaDonRepository {

    @Override
    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        // Sửa câu lệnh SQL nếu cần, nhưng SELECT * là đủ nếu các cột khớp
        String sql = "SELECT * FROM HoaDon";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new HoaDon(
                    rs.getString("MaHoaDon"),
                    rs.getDouble("TongTien"),
                    rs.getString("TrangThai"),
                    rs.getTimestamp("NgayLap"), // Dùng getTimestamp để lấy cả giờ
                    rs.getString("MaNhanVien"),
                    rs.getString("MaKhachHang"),
                    rs.getString("MaBanAn")    // Đã sửa thành MaBanAn
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HoaDon getById(String maHoaDon) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHoaDon);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new HoaDon(
                        rs.getString("MaHoaDon"),
                        rs.getDouble("TongTien"),
                        rs.getString("TrangThai"),
                        rs.getTimestamp("NgayLap"), // Đã sửa thành NgayLap
                        rs.getString("MaNhanVien"),
                        rs.getString("MaKhachHang"),
                        rs.getString("MaBanAn")    // Đã sửa thành MaBanAn
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean insert(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (MaHoaDon, TongTien, TrangThai, NgayLap, MaNhanVien, MaKhachHang, MaBanAn) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, hd.getMaHoaDon());
            ps.setDouble(2, hd.getTongTien());
            ps.setString(3, hd.getTrangThai());
            ps.setTimestamp(4, new java.sql.Timestamp(hd.getNgayLap().getTime()));
            ps.setString(5, hd.getMaNhanVien());
            
            // Kiểm tra nếu MaKhachHang là null
            if (hd.getMaKhachHang() == null) ps.setNull(6, java.sql.Types.VARCHAR);
            else ps.setString(6, hd.getMaKhachHang());
            
            ps.setString(7, hd.getMaBanAn());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // <--- XEM KỸ DÒNG NÀY TRONG CONSOLE
            return false;
        }
    }

    @Override
    public boolean update(HoaDon hd) {
        String sql = "UPDATE HoaDon SET TongTien = ?, TrangThai = ?, MaNhanVien = ?, MaKhachHang = ?, MaBanAn = ? WHERE MaHoaDon = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, hd.getTongTien());
            ps.setString(2, hd.getTrangThai());
            ps.setString(3, hd.getMaNhanVien());
            ps.setString(4, hd.getMaKhachHang());
            ps.setString(5, hd.getMaBanAn()); // Đã sửa thành MaBanAn
            ps.setString(6, hd.getMaHoaDon());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String maHoaDon) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHoaDon);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}