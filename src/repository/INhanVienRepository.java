package repository;

import model.NhanVien;
import java.util.List;

public interface INhanVienRepository {
    List<NhanVien> findAll();
    boolean insert(NhanVien nv);
    boolean update(NhanVien nv);
    boolean delete(String maNhanVien);
    List<NhanVien> search(String keyword);
    boolean checkExists(String maNhanVien);

}