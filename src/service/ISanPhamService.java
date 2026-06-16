package service;



import java.util.List;
import exception.DuplicateException;
import exception.NotFoundException;
import model.SanPham;

public interface ISanPhamService {
		List<SanPham> getAll();
	    void add(SanPham sp) throws DuplicateException;
	    boolean update(SanPham sp);
	    boolean delete(String maSanPham) throws NotFoundException;
	    List<SanPham> search(String keyword);
	    boolean checkExists(String maSanPham);
}
