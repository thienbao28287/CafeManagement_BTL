	package repository;
	
	import database.DBConnection;
	import model.BanAn;
import util.ComboItem;

import java.sql.*;
	import java.util.*;
	
	public class BanAnRepositoryImpl implements IBanAnRepository {
	
	    // --- 1. CÁC PHƯƠNG THỨC CRUD (Create, Read, Update, Delete) ---
	
	    @Override
	    public boolean insert(BanAn ban) {
	
	        String sql = "INSERT INTO BanAn (MaBanAn, SoGhe, TrangThai, ViTri) VALUES (?,?,?,?)";
	
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	
	            ps.setString(1, ban.getMaBanAn());
	            ps.setInt(2, ban.getSoGhe());
	            ps.setString(3, ban.getTrangThai());
	            ps.setString(4, ban.getViTri());
	
	            return ps.executeUpdate() > 0;
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	    @Override
	    public List<BanAn> findAll() {
	
	        List<BanAn> list = new ArrayList<>();
	
	        String sql = "SELECT * FROM BanAn";
	
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	
	            while (rs.next()) {
	                list.add(mapRowToBanAn(rs));
	            }
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	
	        return list;
	    }
	
	    @Override
	    public boolean update(BanAn ban) {
	
	        String sql = "UPDATE BanAn SET SoGhe=?, TrangThai=?, ViTri=? WHERE MaBanAn=?";
	
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	
	            ps.setInt(1, ban.getSoGhe());
	            ps.setString(2, ban.getTrangThai());
	            ps.setString(3, ban.getViTri());
	            ps.setString(4, ban.getMaBanAn());
	
	            return ps.executeUpdate() > 0;
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	    @Override
	    public boolean delete(String maBanAn) {
	
	        String sql = "DELETE FROM BanAn WHERE MaBanAn = ?";
	
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	
	            ps.setString(1, maBanAn);
	
	            return ps.executeUpdate() > 0;
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	    // --- 2. CÁC PHƯƠNG THỨC TÌM KIẾM & KIỂM TRA ---
	
	    @Override
	    public List<BanAn> search(String keyword) {
	
	        List<BanAn> list = new ArrayList<>();
	
	        String sql =
	                "SELECT * FROM BanAn " +
	                "WHERE MaBanAn LIKE ? " +
	                "OR TrangThai LIKE ? " +
	                "OR ViTri LIKE ?";
	
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	
	            String pattern = "%" + keyword + "%";
	
	            ps.setString(1, pattern);
	            ps.setString(2, pattern);
	            ps.setString(3, pattern);
	
	            try (ResultSet rs = ps.executeQuery()) {
	
	                while (rs.next()) {
	                    list.add(mapRowToBanAn(rs));
	                }
	            }
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	
	        return list;
	    }
	
	    @Override
	    public boolean checkExists(String maBanAn) {
	
	        String sql = "SELECT COUNT(*) FROM BanAn WHERE MaBanAn = ?";
	
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	
	            ps.setString(1, maBanAn);
	
	            try (ResultSet rs = ps.executeQuery()) {
	
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	
	        return false;
	    }
	
	    // --- 3. PHƯƠNG THỨC HỖ TRỢ (PRIVATE HELPERS) ---
	
	    private BanAn mapRowToBanAn(ResultSet rs) throws SQLException {
	
	        return new BanAn(
	                rs.getString("MaBanAn"),
	                rs.getInt("SoGhe"),
	                rs.getString("TrangThai"),
	                rs.getString("ViTri")
	        );
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
	}