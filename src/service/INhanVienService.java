package service;

import model.NhanVien;
import exception.*;
import java.util.List;

public interface INhanVienService {
    List<NhanVien> getAll();
    void add(NhanVien nv) throws DuplicateException;
    boolean update(NhanVien nv);
    boolean delete(String maNhanVien) throws NotFoundException;
    List<NhanVien> search(String keyword);
    boolean checkExists(String maNhanVien);
    
    
}