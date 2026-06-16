package repository;

import model.BanAn;
import java.util.List;

public interface IBanAnRepository {
    List<BanAn> findAll();
    boolean insert(BanAn ban);
    boolean update(BanAn ban);
    boolean delete(String maBan);
    List<BanAn> search(String keyword);
    boolean checkExists(String maBan);
}