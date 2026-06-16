package util;

import exception.InvalidInputException;

public class Validator {
    // --- Validate cho Nhân Viên ---
    public static void validateNhanVien(String ten, String sdt, String chucVu, String luong) throws InvalidInputException {
        if (ten.matches(".*\\d.*")) throw new InvalidInputException("Tên không được chứa số!");
        if (!sdt.matches("\\d+")) throw new InvalidInputException("Số điện thoại chỉ được chứa số!");
        if (chucVu.matches(".*\\d.*")) throw new InvalidInputException("Chức vụ không được chứa số!");
        try {
            Double.parseDouble(luong);
        } catch (Exception e) {
            throw new InvalidInputException("Lương phải là số!");
        }
    }
    // --- Validate cho Khách Hàng (Mới thêm) ---
    public static void validateKhachHang(String ten, String sdt, String diaChi) throws InvalidInputException {
        // 1. Kiểm tra bỏ trống tất cả các trường
        if (ten.trim().isEmpty() || sdt.trim().isEmpty() || diaChi.trim().isEmpty()) {
            throw new InvalidInputException("Tên, Số điện thoại và Địa chỉ không được bỏ trống!");
        }
        
        // 2. Kiểm tra Tên không chứa số
        if (ten.matches(".*\\d.*")) {
            throw new InvalidInputException("Tên khách hàng không được chứa số!");
        }
        
        // 3. Kiểm tra SĐT chỉ chứa số
        if (!sdt.matches("\\d+")) {
            throw new InvalidInputException("Số điện thoại chỉ được chứa ký tự số!");
        }
    }

 public static void validateSanPham(String ten, String loai, String soLuong, String giaBan) throws InvalidInputException {
    // 1. Kiểm tra bỏ trống tất cả các trường
    if (ten.trim().isEmpty() || loai.trim().isEmpty() || soLuong.trim().isEmpty() || giaBan.trim().isEmpty()) {
        throw new InvalidInputException("Vui lòng điền đầy đủ các thông tin!");
    }

    // 2. Kiểm tra Loại không được chứa số
    if (loai.matches(".*\\d.*")) {
        throw new InvalidInputException("Loại sản phẩm không được chứa số!");
    }

    // 3. Kiểm tra định dạng số cho Số lượng
    try {
        int sl = Integer.parseInt(soLuong);
        if (sl < 0) throw new Exception();
    } catch (Exception e) {
        throw new InvalidInputException("Số lượng phải là số nguyên không âm!");
    }

    // 4. Kiểm tra định dạng số cho Giá bán
    try {
        double gia = Double.parseDouble(giaBan);
        if (gia < 0) throw new Exception();
    } catch (Exception e) {
        throw new InvalidInputException("Giá bán phải là số thực không âm!");
    }
}
}