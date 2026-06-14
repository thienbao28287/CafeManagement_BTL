package repository;

import model.TaiKhoan;

public interface ITaiKhoanRepository {

    TaiKhoan login(
            String username,
            String password
    );
}