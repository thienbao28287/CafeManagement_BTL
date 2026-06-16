package model;

import java.util.Date;

import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private double tongTien;
    private String trangThai;
    private Date ngayLap;
    private String maNhanVien;
    private String maKhachHang; // Có thể null
    private String maBanAn;

    public HoaDon() {}

	public HoaDon(String maHoaDon, double tongTien, String trangThai, Date ngayLap, String maNhanVien,
			String maKhachHang, String maBanAn) {
		super();
		this.maHoaDon = maHoaDon;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
		this.ngayLap = ngayLap;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.maBanAn = maBanAn;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaBanAn() {
		return maBanAn;
	}

	public void setMaBanAn(String maBanAn) {
		this.maBanAn = maBanAn;
	}
    
   
}

    