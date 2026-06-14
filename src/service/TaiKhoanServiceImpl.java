package service;

import model.TaiKhoan;
import repository.ITaiKhoanRepository;
import util.SessionUtil;

public class TaiKhoanServiceImpl
        implements ITaiKhoanService {

    private final ITaiKhoanRepository repository;

    public TaiKhoanServiceImpl(
            ITaiKhoanRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public TaiKhoan login(
            String username,
            String password
    ) {

        TaiKhoan tk =
                repository.login(
                        username,
                        password
                );

        if (tk != null) {

            SessionUtil.setCurrentUser(tk);
        }

        return tk;
    }
}