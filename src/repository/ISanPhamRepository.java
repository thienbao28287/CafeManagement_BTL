package repository;

import model.SanPham;
import java.util.List;

public interface ISanPhamRepository {
    List<SanPham> findAll();
    boolean insert(SanPham sp);
    boolean update(SanPham sp);
    boolean delete(String maSanPham);
    List<SanPham> search(String keyword);
    boolean checkExists(String maSanPham);
}