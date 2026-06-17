package service;

import model.NhanVien;
import repository.INhanVienRepository;
import repository.NhanVienRepositoryImpl;
import exception.*;
import java.util.List;

public class NhanVienServiceImpl implements INhanVienService {

    // Service phụ thuộc vào Repository thông qua Interface
    private final INhanVienRepository repo = new NhanVienRepositoryImpl();

    // --- 1. CÁC PHƯƠNG THỨC CRUD (Create, Read, Update, Delete) ---

    @Override
    public void add(NhanVien nv) throws DuplicateException {
        // Kiểm tra nghiệp vụ: Nếu mã đã tồn tại thì báo lỗi
        if (repo.checkExists(nv.getMaNhanVien())) {
            throw new DuplicateException("Mã nhân viên " + nv.getMaNhanVien() + " đã tồn tại trong hệ thống!");
        }
        repo.insert(nv);
    }

    @Override
    public List<NhanVien> getAll() {
        return repo.findAll();
    }

    @Override
    public boolean update(NhanVien nv) {
        return repo.update(nv);
    }

    @Override
    public boolean delete(String maNhanVien) throws NotFoundException {
        // Kiểm tra nghiệp vụ: Nếu không tìm thấy thì báo lỗi
        if (!repo.checkExists(maNhanVien)) {
            throw new NotFoundException("Không tìm thấy nhân viên có mã: " + maNhanVien);
        }
        return repo.delete(maNhanVien);
    }

    // --- 2. CÁC PHƯƠNG THỨC TÌM KIẾM & KIỂM TRA ---

    @Override
    public List<NhanVien> search(String keyword) {
        return repo.search(keyword);
    }

    @Override
    public boolean checkExists(String maNhanVien) {
        return repo.checkExists(maNhanVien);
    }

    
}