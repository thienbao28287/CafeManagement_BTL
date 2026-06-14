package service;

import model.KhachHang;
import repository.IKhachHangRepository;
import repository.KhachHangRepositoryImpl;
import exception.*;
import java.util.List;

public class KhachHangServiceImpl implements IKhachHangService {

    private final IKhachHangRepository repo = new KhachHangRepositoryImpl();

    @Override
    public List<KhachHang> getAll() {
        return repo.findAll();
    }

    @Override
    public void add(KhachHang kh) throws DuplicateException {
        // Kiểm tra logic: Nếu mã đã tồn tại thì ném ra Exception
        if (repo.checkExists(kh.getMaKhachHang())) {
            throw new DuplicateException("Mã khách hàng " + kh.getMaKhachHang() + " đã tồn tại!");
        }
        repo.insert(kh);
    }

    @Override
    public boolean update(KhachHang kh) {
        return repo.update(kh);
    }

    @Override
    public boolean delete(String maKhachHang) throws NotFoundException {
        // Kiểm tra logic: Nếu không tồn tại thì báo lỗi
        if (!repo.checkExists(maKhachHang)) {
            throw new NotFoundException("Không tìm thấy khách hàng với mã: " + maKhachHang);
        }
        return repo.delete(maKhachHang);
    }

    @Override
    public List<KhachHang> search(String keyword) {
        return repo.search(keyword);
    }

    @Override
    public boolean checkExists(String maKhachHang) {
        return repo.checkExists(maKhachHang);
    }
}