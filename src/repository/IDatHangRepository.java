package repository;

import model.ChiTietHoaDon;
import java.util.List;

public interface IDatHangRepository {
    List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon);
    boolean insert(ChiTietHoaDon cthd);
    boolean update(ChiTietHoaDon cthd);
    boolean delete(String maHoaDon, String maSanPham);
    boolean checkExists(String maHoaDon, String maSanPham);
    // Thêm dòng này:
    ChiTietHoaDon getById(String maHoaDon, String maSanPham);
    List<ChiTietHoaDon> searchByMaSanPham(String maSanPham);
}