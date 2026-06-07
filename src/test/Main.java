package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.DBConnection;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== BẮT ĐẦU KIỂM TRA KẾT NỐI VÀ LẤY DỮ LIỆU ===");
        
        try (Connection conn = DBConnection.getConnection()) {
            
            if (conn != null) {
                System.out.println("👉 Kết nối SQL Server thành công!");
                System.out.println("------------------------------------------------------------------------\n");                              
                hienThiDanhSachSanPham(conn);                
                System.out.println("\n------------------------------------------------------------------------\n");                               
                hienThiDanhSachBanAn(conn);
            }            
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối hoặc thực thi câu lệnh SQL!");
            e.printStackTrace();
        }
    }

    private static void hienThiDanhSachSanPham(Connection conn) {
        String sql = "SELECT MaSanPham, TenSanPham, Loai, GiaBan, TrangThai FROM SanPham";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("[DANH SÁCH SẢN PHẨM / MENU]");
            System.out.printf("%-10s %-20s %-15s %-12s %-15s\n", "Mã SP", "Tên Sản Phẩm", "Loại", "Giá Bán", "Trạng Thái");
            System.out.println("------------------------------------------------------------------------");
            
            while (rs.next()) {
                String maSP = rs.getString("MaSanPham");
                String tenSP = rs.getString("TenSanPham");
                String loai = rs.getString("Loai");
                double giaBan = rs.getDouble("GiaBan");
                String trangThai = rs.getString("TrangThai");
                
                
                System.out.format("%-10s %-20s %-15s %,12.0f VNĐ  %-15s\n", maSP, tenSP, loai, giaBan, trangThai);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy danh sách sản phẩm!");
            e.printStackTrace();
        }
    }

   
    private static void hienThiDanhSachBanAn(Connection conn) {
        String sql = "SELECT MaBanAn, SoGhe, TrangThai, ViTri FROM BanAn";       
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {            
            System.out.println("[DANH SÁCH BÀN ĂN / BÀN CÀ PHÊ]");
            System.out.printf("%-10s %-10s %-15s %-15s\n", "Mã Bàn", "Số Ghế", "Trạng Thái", "Vị Trí");
            System.out.println("------------------------------------------------------------------------");            
            while (rs.next()) {
                String maBan = rs.getString("MaBanAn");
                int soGhe = rs.getInt("SoGhe");
                String trangThai = rs.getString("TrangThai");
                String viTri = rs.getString("ViTri");                
                System.out.format("%-10s %-10d %-15s %-15s\n", maBan, soGhe, trangThai, viTri);
            }           
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy danh sách bàn ăn!");
            e.printStackTrace();
        }
    }
}