package repository;

import model.KhachHang;
import java.util.List;

public interface IKhachHangRepository {
    List<KhachHang> findAll();
    boolean insert(KhachHang kh);
    boolean update(KhachHang kh);
    boolean delete(String maKhachHang);
    List<KhachHang> search(String keyword);
    boolean checkExists(String maKhachHang);
}