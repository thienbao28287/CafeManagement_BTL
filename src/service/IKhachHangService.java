package service;

import model.KhachHang;
import exception.*;
import java.util.List;

public interface IKhachHangService {
    List<KhachHang> getAll();
    void add(KhachHang kh) throws DuplicateException;
    boolean update(KhachHang kh);
    boolean delete(String maKhachHang) throws NotFoundException;
    List<KhachHang> search(String keyword);
    boolean checkExists(String maKhachHang);
}