package service;

import model.BanAn;
import repository.IBanAnRepository;
import repository.BanAnRepositoryImpl;
import exception.*;
import java.util.List;

public class BanAnServiceImpl implements IBanAnService {

    // Service phụ thuộc vào Repository thông qua Interface
    private final IBanAnRepository repo = new BanAnRepositoryImpl();

    // --- 1. CÁC PHƯƠNG THỨC CRUD (Create, Read, Update, Delete) ---

    @Override
    public void add(BanAn ban) throws DuplicateException {

        if (repo.checkExists(ban.getMaBanAn())) {
            throw new DuplicateException(
                "Mã bàn ăn " + ban.getMaBanAn() + " đã tồn tại trong hệ thống!"
            );
        }

        repo.insert(ban);
    }

    @Override
    public List<BanAn> getAll() {
        return repo.findAll();
    }

    @Override
    public boolean update(BanAn ban) {
        return repo.update(ban);
    }

    @Override
    public boolean delete(String maBanAn) throws NotFoundException {

        if (!repo.checkExists(maBanAn)) {
            throw new NotFoundException(
                "Không tìm thấy bàn ăn có mã: " + maBanAn
            );
        }

        return repo.delete(maBanAn);
    }

    // --- 2. CÁC PHƯƠNG THỨC TÌM KIẾM & KIỂM TRA ---

    @Override
    public List<BanAn> search(String keyword) {
        return repo.search(keyword);
    }

    @Override
    public boolean checkExists(String maBanAn) {
        return repo.checkExists(maBanAn);
    }
}