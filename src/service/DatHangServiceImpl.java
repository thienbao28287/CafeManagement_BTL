package service;

import model.ChiTietHoaDon;
import repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DBConnection;

public class DatHangServiceImpl implements IDatHangService {
    private IDatHangRepository repo = new DatHangRepositoryImpl();
    private ISanPhamRepository sanPhamRepo = new SanPhamRepositoryImpl();

    @Override
    public List<ChiTietHoaDon> getByHoaDon(String maHoaDon) {
        return repo.findByMaHoaDon(maHoaDon);
    }


    @Override
    public List<ChiTietHoaDon> searchByMaSanPham(String maSanPham) {
        return repo.searchByMaSanPham(maSanPham);
    }
  @Override
public void addOrUpdate(ChiTietHoaDon cthd) {
    Connection conn = null;
    try {
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        int soLuongCanTru;

        if (repo.checkExists(cthd.getMaHoaDon(), cthd.getMaSanPham())) {
            ChiTietHoaDon cthdCu = repo.getById(cthd.getMaHoaDon(), cthd.getMaSanPham());
            
            if (cthdCu != null) {
                soLuongCanTru = cthd.getSoLuong() - cthdCu.getSoLuong();
                repo.update(cthd);
            } else {
                // Nếu cthdCu bị null bất thường, coi như insert mới
                soLuongCanTru = cthd.getSoLuong();
                repo.insert(cthd);
            }
        } else {
            soLuongCanTru = cthd.getSoLuong();
            repo.insert(cthd);
        }

        // 2. Gọi hàm trừ/cộng kho trong SanPhamRepository
        // Truyền conn vào để cùng nằm trong một Transaction
        sanPhamRepo.updateSoLuong(conn, cthd.getMaSanPham(), -soLuongCanTru);

        conn.commit(); // Lưu thay đổi vào DB
    } catch (SQLException e) {
        e.printStackTrace();
        if (conn != null) {
            try {
                conn.rollback(); // Rollback nếu có lỗi xảy ra
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } finally {
        // Đóng kết nối sau khi hoàn thành
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Reset lại chế độ mặc định
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


    @Override
    public void remove(String maHoaDon, String maSanPham) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            
            // 1. Lấy số lượng cũ trong hóa đơn để cộng lại vào kho
            ChiTietHoaDon cthd = repo.getById(maHoaDon, maSanPham);
            if (cthd != null) {
                // 2. Xóa khỏi hóa đơn
                repo.delete(maHoaDon, maSanPham);
                // 3. Cộng ngược lại vào kho
                sanPhamRepo.updateSoLuong(conn, maSanPham, cthd.getSoLuong());
            }
            
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}