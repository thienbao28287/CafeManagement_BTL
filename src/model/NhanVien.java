package model;

public class NhanVien {
    private String maNhanVien;
    private String tenNhanVien, soDienThoai, email, chucVu, gioiTinh, diaChi;
    private double luong;

    public NhanVien() {}
    
    public NhanVien(String maNhanVien, String tenNhanVien, String soDienThoai, String email, 
            String chucVu, String gioiTinh, String diaChi, double luong) {
    	this.maNhanVien = maNhanVien;
    	this.tenNhanVien = tenNhanVien;
    	this.soDienThoai = soDienThoai;
    	this.email = email;
    	this.chucVu = chucVu;
    	this.gioiTinh = gioiTinh;
    	this.diaChi = diaChi;
    	this.luong = luong;
    }

	public String getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(String m) { this.maNhanVien = m; }
    public String getTenNhanVien() { return tenNhanVien; }
    public void setTenNhanVien(String t) { this.tenNhanVien = t; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String s) { this.soDienThoai = s; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public String getChucVu() { return chucVu; }
    public void setChucVu(String c) { this.chucVu = c; }
    public double getLuong() { return luong; }
    public void setLuong(double l) { this.luong = l; }
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String g) { this.gioiTinh = g; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String d) { this.diaChi = d; }
}