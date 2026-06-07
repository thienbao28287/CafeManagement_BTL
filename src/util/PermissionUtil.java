package util;

import model.TaiKhoan;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionUtil {

    // Định nghĩa danh sách các Chức năng (Khớp với các Panel của bạn)
    public static final String VIEW_TRANG_CHU = "TrangChu";
    public static final String VIEW_NHAN_VIEN = "NhanVien";
    public static final String VIEW_KHACH_HANG = "KhachHang";
    public static final String VIEW_BAN_AN     = "BanAn";
    public static final String VIEW_SAN_PHAM   = "SanPham";
    public static final String VIEW_DAT_HANG   = "DatHang";
    public static final String VIEW_HOA_DON    = "HoaDon";

    // Ma trận phân quyền: Chức vụ nào (MaChucVu) được phép vào những chức năng nào
    private static final Map<String, List<String>> permissions = new HashMap<>();

    static {
        // CV01 (Admin): Có toàn quyền
        permissions.put("CV01", Arrays.asList(
            VIEW_TRANG_CHU, VIEW_NHAN_VIEN, VIEW_KHACH_HANG, 
            VIEW_BAN_AN, VIEW_SAN_PHAM, VIEW_DAT_HANG, VIEW_HOA_DON
        ));

        // CV02 (Quản lý): Xem được tất cả ngoại trừ sửa thông tin Nhân viên hệ thống
        permissions.put("CV02", Arrays.asList(
            VIEW_TRANG_CHU, VIEW_KHACH_HANG, VIEW_BAN_AN, 
            VIEW_SAN_PHAM, VIEW_DAT_HANG, VIEW_HOA_DON
        ));

        // CV03 (Thu ngân) & CV04 (Phục vụ): Chỉ được Đặt hàng, Xem bàn ăn, Khách hàng, Hóa đơn
        List<String> staffPermissions = Arrays.asList(
            VIEW_TRANG_CHU, VIEW_KHACH_HANG, VIEW_BAN_AN, VIEW_DAT_HANG, VIEW_HOA_DON
        );
        permissions.put("CV03", staffPermissions); // Thu ngân
        permissions.put("CV04", staffPermissions); // Phục vụ
        permissions.put("CV05", staffPermissions); // Pha chế
    }

    /**
     * Kiểm tra xem người dùng hiện tại có quyền vào chức năng này không
     */
    public static boolean hasPermission(String viewName) {
        TaiKhoan user = SessionUtil.getCurrentUser();
        if (user == null) return false;

        String maChucVu = user.getMaChucVu();
        List<String> allowedViews = permissions.get(maChucVu);

        return allowedViews != null && allowedViews.contains(viewName);
    }
}