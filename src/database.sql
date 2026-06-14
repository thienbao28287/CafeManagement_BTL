-- ==========================================
-- XÓA DATABASE NẾU TỒN TẠI
-- ==========================================

USE master;
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'quanLyCaPhe')
BEGIN
    ALTER DATABASE quanLyCaPhe SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE quanLyCaPhe;
END
GO

-- ==========================================
-- TẠO DATABASE
-- ==========================================

CREATE DATABASE quanLyCaPhe;
GO

USE quanLyCaPhe;
GO

-- ==========================================
-- BẢNG TÀI KHOẢN
-- ==========================================

CREATE TABLE TaiKhoan
(
    MaTaiKhoan INT IDENTITY(1,1) PRIMARY KEY,
    TenTaiKhoan NVARCHAR(50) NOT NULL UNIQUE,
    MatKhau NVARCHAR(100) NOT NULL,
    VaiTro NVARCHAR(30) NOT NULL
);
GO

-- ==========================================
-- BẢNG NHÂN VIÊN
-- ==========================================

CREATE TABLE NhanVien
(
    MaNhanVien INT IDENTITY(1,1) PRIMARY KEY,
    TenNhanVien NVARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(15),
    Email VARCHAR(100),
    ChucVu NVARCHAR(50),
    Luong DECIMAL(18,2),
    GioiTinh NVARCHAR(10),
    DiaChi NVARCHAR(255),

    MaTaiKhoan INT UNIQUE,

    CONSTRAINT FK_NhanVien_TaiKhoan
        FOREIGN KEY (MaTaiKhoan)
        REFERENCES TaiKhoan(MaTaiKhoan)
);
GO

-- ==========================================
-- BẢNG KHÁCH HÀNG
-- ==========================================

CREATE TABLE KhachHang
(
    MaKhachHang INT IDENTITY(1,1) PRIMARY KEY,
    TenKhachHang NVARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(15),
    DiaChi NVARCHAR(255)
);
GO

-- ==========================================
-- BẢNG BÀN ĂN
-- ==========================================

CREATE TABLE BanAn
(
    MaBanAn INT IDENTITY(1,1) PRIMARY KEY,
    SoGhe INT NOT NULL,
    TrangThai NVARCHAR(30)
        CONSTRAINT DF_BanAn_TrangThai
        DEFAULT N'Trống',

    ViTri NVARCHAR(100),

    CONSTRAINT CK_BanAn_TrangThai
        CHECK (TrangThai IN
        (
            N'Trống',
            N'Đang dùng',
            N'Đặt trước'
        ))
);
GO

-- ==========================================
-- BẢNG SẢN PHẨM
-- ==========================================

CREATE TABLE SanPham
(
    MaSanPham INT IDENTITY(1,1) PRIMARY KEY,
    TenSanPham NVARCHAR(100) NOT NULL,
    SoLuong INT NOT NULL,
    Loai NVARCHAR(50),
    GiaBan DECIMAL(18,2) NOT NULL,
    TrangThai NVARCHAR(30)
);
GO

-- ==========================================
-- BẢNG HÓA ĐƠN
-- ==========================================

CREATE TABLE HoaDon
(
    MaHoaDon INT IDENTITY(1,1) PRIMARY KEY,

    TongTien DECIMAL(18,2)
        CONSTRAINT DF_HoaDon_TongTien
        DEFAULT 0,

    TrangThai NVARCHAR(30)
        CONSTRAINT DF_HoaDon_TrangThai
        DEFAULT N'Đang phục vụ',

    NgayLap DATETIME
        CONSTRAINT DF_HoaDon_NgayLap
        DEFAULT GETDATE(),

    MaNhanVien INT NOT NULL,
    MaKhachHang INT NULL,
    MaBanAn INT NOT NULL,

    CONSTRAINT FK_HoaDon_NhanVien
        FOREIGN KEY (MaNhanVien)
        REFERENCES NhanVien(MaNhanVien),

    CONSTRAINT FK_HoaDon_KhachHang
        FOREIGN KEY (MaKhachHang)
        REFERENCES KhachHang(MaKhachHang),

    CONSTRAINT FK_HoaDon_BanAn
        FOREIGN KEY (MaBanAn)
        REFERENCES BanAn(MaBanAn),

    CONSTRAINT CK_HoaDon_TrangThai
        CHECK (TrangThai IN
        (
            N'Đang phục vụ',
            N'Đã thanh toán',
            N'Hủy'
        ))
);
GO

-- ==========================================
-- BẢNG CHI TIẾT HÓA ĐƠN
-- ==========================================

CREATE TABLE ChiTietHoaDon
(
    MaHoaDon INT NOT NULL,
    MaSanPham INT NOT NULL,

    SoLuong INT NOT NULL,
    DonGia DECIMAL(18,2) NOT NULL,
    ThanhTien DECIMAL(18,2) NOT NULL,

    PRIMARY KEY (MaHoaDon, MaSanPham),

    CONSTRAINT FK_CTHD_HoaDon
        FOREIGN KEY (MaHoaDon)
        REFERENCES HoaDon(MaHoaDon),

    CONSTRAINT FK_CTHD_SanPham
        FOREIGN KEY (MaSanPham)
        REFERENCES SanPham(MaSanPham)
);
GO

INSERT INTO TaiKhoan(TenTaiKhoan, MatKhau, VaiTro)
VALUES
('admin', '123456', 'Admin'),
('quanly', '123456', 'QuanLy'),
('thungan', '123456', 'ThuNgan'),
('nhanvien1', '123456', 'NhanVien'),
('nhanvien2', '123456', 'NhanVien');

-- ==========================================
-- NHÂN VIÊN
-- ==========================================

INSERT INTO NhanVien
(
    TenNhanVien,
    SoDienThoai,
    Email,
    ChucVu,
    Luong,
    GioiTinh,
    DiaChi,
    MaTaiKhoan
)
VALUES
(N'Nguyễn Văn An', '0988888881', 'an@gmail.com', N'Quản lý', 15000000, N'Nam', N'Hà Nội', 2),
(N'Trần Thị Bình', '0988888882', 'binh@gmail.com', N'Thu ngân', 10000000, N'Nữ', N'Hà Nội', 3),
(N'Lê Văn Cường', '0988888883', 'cuong@gmail.com', N'Phục vụ', 8000000, N'Nam', N'Hà Nội', 4),
(N'Phạm Thị Dung', '0988888884', 'dung@gmail.com', N'Phục vụ', 8000000, N'Nữ', N'Hà Nội', 5);

-- ==========================================
-- KHÁCH HÀNG
-- ==========================================

INSERT INTO KhachHang
(
    TenKhachHang,
    SoDienThoai,
    DiaChi
)
VALUES
(N'Nguyễn Văn A', '0911111111', N'Hà Nội'),
(N'Trần Văn B', '0922222222', N'Hải Phòng'),
(N'Lê Thị C', '0933333333', N'Nam Định'),
(N'Phạm Văn D', '0944444444', N'Hưng Yên'),
(N'Hoàng Thị E', '0955555555', N'Hà Nam');

-- ==========================================
-- BÀN ĂN
-- ==========================================

INSERT INTO BanAn
(
    SoGhe,
    TrangThai,
    ViTri
)
VALUES
(2, N'Trống', N'Tầng 1'),
(4, N'Trống', N'Tầng 1'),
(4, N'Đang dùng', N'Tầng 1'),
(6, N'Đặt trước', N'Tầng 2'),
(8, N'Trống', N'Tầng 2'),
(10, N'Trống', N'VIP');

-- ==========================================
-- SẢN PHẨM
-- ==========================================

INSERT INTO SanPham
(
    TenSanPham,
    SoLuong,
    Loai,
    GiaBan,
    TrangThai
)
VALUES
(N'Cà phê đen', 100, N'Cà phê', 25000, N'Còn bán'),
(N'Cà phê sữa', 100, N'Cà phê', 30000, N'Còn bán'),
(N'Bạc xỉu', 80, N'Cà phê', 35000, N'Còn bán'),
(N'Trà đào', 60, N'Trà', 40000, N'Còn bán'),
(N'Trà chanh', 70, N'Trà', 25000, N'Còn bán'),
(N'Nước cam', 50, N'Nước ép', 45000, N'Còn bán'),
(N'Sinh tố bơ', 40, N'Sinh tố', 50000, N'Còn bán'),
(N'Bánh tiramisu', 30, N'Bánh ngọt', 45000, N'Còn bán'),
(N'Bánh flan', 50, N'Bánh ngọt', 25000, N'Còn bán'),
(N'Cookie', 100, N'Bánh ngọt', 15000, N'Còn bán');

-- ==========================================
-- HÓA ĐƠN
-- ==========================================

INSERT INTO HoaDon
(
    TongTien,
    TrangThai,
    NgayLap,
    MaNhanVien,
    MaKhachHang,
    MaBanAn
)
VALUES
(85000, N'Đã thanh toán', GETDATE(), 3, 1, 1),
(95000, N'Đang phục vụ', GETDATE(), 4, 2, 3),
(120000, N'Hủy', GETDATE(), 3, 3, 2);

-- ==========================================
-- CHI TIẾT HÓA ĐƠN
-- ==========================================

INSERT INTO ChiTietHoaDon
(
    MaHoaDon,
    MaSanPham,
    SoLuong,
    DonGia,
    ThanhTien
)
VALUES
(1, 1, 1, 25000, 25000),
(1, 8, 1, 45000, 45000),
(1, 10, 1, 15000, 15000),

(2, 2, 1, 30000, 30000),
(2, 4, 1, 40000, 40000),
(2, 9, 1, 25000, 25000),

(3, 6, 2, 45000, 90000),
(3, 10, 2, 15000, 30000);
GO

SELECT * FROM TaiKhoan;
SELECT * FROM NhanVien;
SELECT * FROM KhachHang;
SELECT * FROM BanAn;
SELECT * FROM SanPham;
SELECT * FROM HoaDon;
SELECT * FROM ChiTietHoaDon;