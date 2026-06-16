package service;

import model.HoaDon;
import java.util.List;

public interface IHoaDonService {
    List<HoaDon> getAllHoaDon();
    HoaDon getHoaDonById(String maHoaDon);
    boolean addHoaDon(HoaDon hoaDon);
    boolean updateHoaDon(HoaDon hoaDon);
    boolean deleteHoaDon(String maHoaDon);
}