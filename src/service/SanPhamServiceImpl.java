package service;

import model.SanPham;
import repository.*;
import exception.*;
import java.util.List;

public class SanPhamServiceImpl implements ISanPhamService {
    private final ISanPhamRepository repo = new SanPhamRepositoryImpl();

    @Override
    public void add(SanPham sp) throws DuplicateException {
        if (repo.checkExists(sp.getMaSanPham())) throw new DuplicateException("Mã SP đã tồn tại!");
        repo.insert(sp);
    }

    @Override
    public List<SanPham> getAll() { return repo.findAll(); }
    @Override
    public boolean update(SanPham sp) { return repo.update(sp); }
    @Override
    public boolean delete(String ma) throws NotFoundException {
        if (!repo.checkExists(ma)) throw new NotFoundException("Không tìm thấy mã SP!");
        return repo.delete(ma);
    }
    @Override
    public List<SanPham> search(String k) { return repo.search(k); }
    @Override
    public boolean checkExists(String ma) { return repo.checkExists(ma); }
}