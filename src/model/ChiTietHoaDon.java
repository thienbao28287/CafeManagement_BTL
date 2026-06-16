package model;

public class ChiTietHoaDon {
    private String maHoaDon;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ChiTietHoaDon(String maHoaDon, String maSanPham, String tenSanPham, int soLuong, double donGia) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    public String getMaHoaDon() { return maHoaDon; }
    public String getMaSanPham() { return maSanPham; }
    public String getTenSanPham() { return tenSanPham; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }
    public double getThanhTien() { return thanhTien; }
}