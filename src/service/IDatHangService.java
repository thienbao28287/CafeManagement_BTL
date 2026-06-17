package service;

import model.ChiTietHoaDon;
import java.util.List;

public interface IDatHangService {
    List<ChiTietHoaDon> getByHoaDon(String maHoaDon);
    void addOrUpdate(ChiTietHoaDon cthd);
    void remove(String maHoaDon, String maSanPham);
    List<ChiTietHoaDon> searchByMaSanPham(String maSanPham);
}