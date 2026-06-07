package service;

import model.TaiKhoan;
import repository.ITaiKhoanRepository;
import util.SessionUtil;

public class TaiKhoanServiceImpl implements ITaiKhoanService {
    
    private final ITaiKhoanRepository taiKhoanRepository;

    // Kỹ thuật Dependency Injection nhận Repo từ ngoài vào để lỏng liên kết (Loose Coupling)
    public TaiKhoanServiceImpl(ITaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Override
    public boolean login(String username, String password) {
        TaiKhoan taiKhoan = taiKhoanRepository.checkLogin(username, password);
        
        if (taiKhoan != null) {
            // 🌟 ĐĂNG NHẬP THÀNH CÔNG -> Lưu thông tin người dùng vào Session hệ thống
            SessionUtil.setCurrentUser(taiKhoan);
            return true;
        }
        return false;
    }
}