package util;

public class PermissionUtil {

    private PermissionUtil() {
    }

    public static boolean isAdmin() {

        return SessionUtil.getCurrentUser() != null
                && SessionUtil.getCurrentUser()
                        .getVaiTro()
                        .equalsIgnoreCase("Admin");
    }

    public static boolean isNhanVien() {

        return SessionUtil.getCurrentUser() != null
                && SessionUtil.getCurrentUser()
                        .getVaiTro()
                        .equalsIgnoreCase("NhanVien");
    }

    public static boolean canManageEmployee() {
        return isAdmin();
    }

    public static boolean canManageAccount() {
        return isAdmin();
    }

    public static boolean canManageCustomer() {
        return isAdmin();
    }

    public static boolean canManageProduct() {
        return isAdmin();
    }

    public static boolean canManageTable() {
        return isAdmin() || isNhanVien();
    }

    public static boolean canOrder() {
        return isAdmin() || isNhanVien();
    }

    public static boolean canViewInvoice() {
        return isAdmin() || isNhanVien();
    }

    public static boolean canPayment() {
        return isAdmin();
    }
}