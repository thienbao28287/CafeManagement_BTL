package service;

import model.TaiKhoan;

public interface ITaiKhoanService {

    TaiKhoan login(
            String username,
            String password
    );
}