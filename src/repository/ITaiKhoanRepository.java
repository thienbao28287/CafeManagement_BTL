package repository;

import model.TaiKhoan;

public interface ITaiKhoanRepository {
    TaiKhoan checkLogin(String username, String password);
}