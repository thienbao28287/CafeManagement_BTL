package service;

import model.HoaDon;
import repository.IHoaDonRepository;
import repository.HoaDonRepositoryImpl;
import java.util.List;

public class HoaDonServiceImpl implements IHoaDonService {
    private IHoaDonRepository repo = new HoaDonRepositoryImpl();

    @Override
    public List<HoaDon> getAllHoaDon() {
        return repo.getAll();
    }

    @Override
    public HoaDon getHoaDonById(String maHoaDon) {
        return repo.getById(maHoaDon);
    }

    @Override
    public boolean addHoaDon(HoaDon hoaDon) {
        // 1. Kiểm tra dữ liệu đầu vào (Rất quan trọng)
        if (hoaDon == null || hoaDon.getMaHoaDon() == null || hoaDon.getMaHoaDon().isEmpty()) {
            return false; 
        }

        // 2. Kiểm tra trùng lặp
        if (repo.getById(hoaDon.getMaHoaDon()) != null) {
            System.out.println("Cảnh báo: Mã hóa đơn đã tồn tại!");
            return false; 
        }

        // 3. Thực hiện insert và kiểm tra kết quả trả về từ Repository
        try {
            return repo.insert(hoaDon);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateHoaDon(HoaDon hoaDon) {
        return repo.update(hoaDon);
    }

    @Override
    public boolean deleteHoaDon(String maHoaDon) {
        return repo.delete(maHoaDon);
    }
    
}