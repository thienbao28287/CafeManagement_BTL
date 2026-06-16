package service;

import model.BanAn;
import exception.*;
import java.util.List;

public interface IBanAnService {
    List<BanAn> getAll();
    void add(BanAn nv) throws DuplicateException;
    boolean update(BanAn nv);
    boolean delete(String maBanAn) throws NotFoundException;
    List<BanAn> search(String keyword);
    boolean checkExists(String maBanAn);
}