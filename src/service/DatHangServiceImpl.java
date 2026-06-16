package service;

import model.ChiTietHoaDon;
import repository.*;
import java.util.List;

public class DatHangServiceImpl implements IDatHangService {
    private IDatHangRepository repo = new DatHangRepositoryImpl();

    @Override
    public List<ChiTietHoaDon> getByHoaDon(String maHoaDon) {
        return repo.findByMaHoaDon(maHoaDon);
    }

    @Override
    public void addOrUpdate(ChiTietHoaDon cthd) {
        if (repo.checkExists(cthd.getMaHoaDon(), cthd.getMaSanPham())) {
            repo.update(cthd);
        } else {
            repo.insert(cthd);
        }
    }

    @Override
    public void remove(String maHoaDon, String maSanPham) {
        repo.delete(maHoaDon, maSanPham);
    }
}