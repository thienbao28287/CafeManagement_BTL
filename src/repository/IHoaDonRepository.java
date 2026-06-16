package repository;

import model.HoaDon;
import java.util.List;

public interface IHoaDonRepository {
    List<HoaDon> getAll();
    boolean insert(HoaDon hoaDon);
    boolean update(HoaDon hoaDon);
    boolean delete(String maHoaDon);
    HoaDon getById(String maHoaDon);
}