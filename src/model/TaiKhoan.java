package model;

public class TaiKhoan {
    private String maTaiKhoan;
    private String tenTaiKhoan;
    private String matKhau;
    private String hoTen;
    private String vaiTro;
    private double luong;
    private String maChucVu;

    public TaiKhoan() {}

    public TaiKhoan(String maTaiKhoan, String tenTaiKhoan, String matKhau, String hoTen, String vaiTro, double luong, String maChucVu) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
        this.luong = luong;
        this.maChucVu = maChucVu;
    }

    // Các hàm Getter và Setter để bảo toàn đóng gói dữ liệu
    public String getMaTaiKhoan() { return maTaiKhoan; }
    public void setMaTaiKhoan(String maTaiKhoan) { this.maTaiKhoan = maTaiKhoan; }

    public String getTenTaiKhoan() { return tenTaiKhoan; }
    public void setTenTaiKhoan(String tenTaiKhoan) { this.tenTaiKhoan = tenTaiKhoan; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) { this.vaiTro = vaiTro; }

    public double getLuong() { return luong; }
    public void setLuong(double luong) { this.luong = luong; }

    public String getMaChucVu() { return maChucVu; }
    public void setMaChucVu(String maChucVu) { this.maChucVu = maChucVu; }
}