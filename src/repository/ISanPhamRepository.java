package repository;

import model.SanPham;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ISanPhamRepository {
    List<SanPham> findAll();
    boolean insert(SanPham sp);
    boolean update(SanPham sp);
    boolean delete(String maSanPham);
    List<SanPham> search(String keyword);
    boolean checkExists(String maSanPham);
    void updateSoLuong(Connection conn, String maSP, int soLuongThayDoi) throws SQLException;
}