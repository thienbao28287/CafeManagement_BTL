package model;

public class BanAn {
    private String maBanAn;
    private int soGhe;
    private String trangThai;
    private String viTri;

    public BanAn() {
    }

    public BanAn(String maBanAn, int soGhe, String trangThai, String viTri) {
        this.maBanAn = maBanAn;
        this.soGhe = soGhe;
        this.trangThai = trangThai;
        this.viTri = viTri;
    }

    public String getMaBanAn() {
        return maBanAn;
    }

    public void setMaBanAn(String maBanAn) {
        this.maBanAn = maBanAn;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }
}