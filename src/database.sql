-- ===================================================================
-- BƯỚC 1: TẠO CÁC BẢNG ĐỘC LẬP (Không chứa khóa ngoại)
-- ===================================================================

-- 1. Bảng Chức Vụ
CREATE TABLE ChucVu (
    MaChucVu VARCHAR(50) PRIMARY KEY,
    TenChucVu NVARCHAR(100)
        CHECK (TenChucVu IN (
            N'Admin',
            N'Quản lý',
            N'Thu ngân',
            N'Phục vụ',
            N'Pha chế',
            N'Đầu bếp',
            N'Phụ bếp',
            N'Giám sát ca',
            N'Giao hàng'
        ))
);

-- 2. Bảng Khách Hàng
CREATE TABLE KhachHang (
    MaKhachHang VARCHAR(50) PRIMARY KEY,
    TenKhachHang NVARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(15),
    DiaChi NVARCHAR(255)
);

-- 3. Bảng Bàn Ăn
CREATE TABLE BanAn (
    MaBanAn VARCHAR(50) PRIMARY KEY,
    SoGhe INT,
    TrangThai NVARCHAR(50) DEFAULT N'Trống'
        CHECK (TrangThai IN (N'Trống', N'Đang dùng', N'Đặt trước')),
    ViTri NVARCHAR(100)
);

-- 4. Bảng Sản Phẩm
CREATE TABLE SanPham (
    MaSanPham VARCHAR(50) PRIMARY KEY,
    TenSanPham NVARCHAR(255) NOT NULL,
    SoLuong INT,
    Loai NVARCHAR(100),
    GiaBan DECIMAL(18,2),
    TrangThai NVARCHAR(50) DEFAULT N'Còn bán'
        CHECK (TrangThai IN (
            N'Còn bán',
            N'Hết hàng',
            N'Ngừng kinh doanh'
        ))
);

-- ===================================================================
-- BƯỚC 2: TẠO CÁC BẢNG PHỤ THUỘC (Có chứa khóa ngoại - Foreign Key)
-- ===================================================================

-- 5. Bảng Tài Khoản (Phụ thuộc vào bảng ChucVu)
CREATE TABLE TaiKhoan (
    MaTaiKhoan VARCHAR(50) PRIMARY KEY,
    TenTaiKhoan VARCHAR(100) NOT NULL,
    MatKhau VARCHAR(255) NOT NULL,
    HoTen NVARCHAR(100),
    VaiTro NVARCHAR(50),
    Luong DECIMAL(18, 2),
    MaChucVu VARCHAR(50),
    
    -- Khai báo khóa ngoại
    FOREIGN KEY (MaChucVu) REFERENCES ChucVu(MaChucVu)
);

-- 6. Bảng Hóa Đơn (Phụ thuộc vào TaiKhoan, KhachHang, BanAn)
CREATE TABLE HoaDon (
    MaHoaDon VARCHAR(50) PRIMARY KEY,
    TongTien DECIMAL(18,2),
    TrangThai NVARCHAR(50) DEFAULT N'Đang phục vụ'
        CHECK (TrangThai IN (N'Đang phục vụ', N'Đã thanh toán', N'Hủy')),
    NgayLap DATETIME,
    MaTaiKhoan VARCHAR(50),
    MaKhachHang VARCHAR(50),
    MaBanAn VARCHAR(50),

    FOREIGN KEY (MaTaiKhoan) REFERENCES TaiKhoan(MaTaiKhoan),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaBanAn) REFERENCES BanAn(MaBanAn)
);

-- 7. Bảng Chi Tiết Hóa Đơn (Phụ thuộc vào HoaDon và SanPham)
-- Lưu ý: Bảng này dùng khóa chính kép (Composite Key) gồm MaHoaDon và MaSanPham
CREATE TABLE ChiTietHoaDon (
    MaHoaDon VARCHAR(50),
    MaSanPham VARCHAR(50),
    SoLuong INT,
    DonGia DECIMAL(18, 2),
    ThanhTien DECIMAL(18, 2),
    
    -- Khai báo khóa chính kép
    PRIMARY KEY (MaHoaDon, MaSanPham),
    
    -- Khai báo các khóa ngoại
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
);

-- =========================
-- CHỨC VỤ
-- =========================
INSERT INTO ChucVu VALUES ('CV01', N'Admin');
INSERT INTO ChucVu VALUES ('CV02', N'Quản lý');
INSERT INTO ChucVu VALUES ('CV03', N'Thu ngân');
INSERT INTO ChucVu VALUES ('CV04', N'Phục vụ');
INSERT INTO ChucVu VALUES ('CV05', N'Pha chế');

-- =========================
-- TÀI KHOẢN
-- =========================
INSERT INTO TaiKhoan
VALUES ('TK01', 'admin', '123456', N'Nguyễn Văn Admin', N'Admin', 20000000, 'CV01');
INSERT INTO TaiKhoan
VALUES ('TK02', 'manager01', '123456', N'Trần Thị Quản Lý', N'Nhân viên', 15000000, 'CV02');
INSERT INTO TaiKhoan
VALUES ('TK03', 'cashier01', '123456', N'Lê Văn Thu Ngân', N'Nhân viên', 10000000, 'CV03');
INSERT INTO TaiKhoan
VALUES ('TK04', 'staff01', '123456', N'Phạm Văn Phục Vụ', N'Nhân viên', 8000000, 'CV04');
INSERT INTO TaiKhoan
VALUES ('TK05', 'barista01', '123456', N'Hoàng Văn Pha Chế', N'Nhân viên', 9000000, 'CV05');
-- =========================
-- KHÁCH HÀNG
-- =========================
INSERT INTO KhachHang
VALUES ('KH01', N'Nguyễn Minh Anh', '0988123456', N'Hà Nội');
INSERT INTO KhachHang
VALUES ('KH02', N'Trần Văn Bình', '0977234567', N'Hà Nội');
INSERT INTO KhachHang
VALUES ('KH03', N'Lê Thị Hương', '0966345678', N'Hà Nội');
-- =========================
-- BÀN ĂN
-- =========================
INSERT INTO BanAn
VALUES ('BA01', 2, N'Trống', N'Tầng 1');
INSERT INTO BanAn
VALUES ('BA02', 4, N'Đang dùng', N'Tầng 1');
INSERT INTO BanAn
VALUES ('BA03', 6, N'Đặt trước', N'Tầng 2');
INSERT INTO BanAn
VALUES ('BA04', 2, N'Trống', N'Tầng 2');
INSERT INTO BanAn
VALUES ('BA05', 4, N'Trống', N'Sân vườn');
-- =========================
-- SẢN PHẨM
-- =========================
INSERT INTO SanPham
VALUES ('SP01', N'Cà phê đen', 100, N'Cà phê', 25000, N'Còn bán');
INSERT INTO SanPham
VALUES ('SP02', N'Cà phê sữa', 0, N'Cà phê', 30000, N'Hết hàng');
INSERT INTO SanPham
VALUES ('SP03', N'Bạc xỉu', 50, N'Cà phê', 35000, N'Còn bán');
INSERT INTO SanPham
VALUES ('SP04', N'Trà đào cam sả', 0, N'Trà', 45000, N'Hết hàng');
INSERT INTO SanPham
VALUES ('SP05', N'Trà chanh', 20, N'Trà', 25000, N'Còn bán');
INSERT INTO SanPham
VALUES ('SP06', N'Sinh tố xoài', 10, N'Sinh tố', 50000, N'Còn bán');
INSERT INTO SanPham
VALUES ('SP07', N'Bánh Tiramisu', 0, N'Bánh ngọt', 55000, N'Ngừng kinh doanh');
-- =========================
-- HÓA ĐƠN
-- =========================
INSERT INTO HoaDon
VALUES ('HD01', 85000, N'Đã thanh toán', GETDATE(), 'TK03', 'KH01', 'BA01');
INSERT INTO HoaDon
VALUES ('HD02', 70000, N'Đang phục vụ', GETDATE(), 'TK03', 'KH02', 'BA02');
-- =========================
-- CHI TIẾT HÓA ĐƠN
-- =========================
INSERT INTO ChiTietHoaDon
VALUES ('HD01', 'SP01', 1, 25000, 25000);
INSERT INTO ChiTietHoaDon
VALUES ('HD01', 'SP04', 1, 45000, 45000);
INSERT INTO ChiTietHoaDon
VALUES ('HD01', 'SP08', 1, 15000, 15000);
INSERT INTO ChiTietHoaDon
VALUES ('HD02', 'SP02', 1, 30000, 30000);
INSERT INTO ChiTietHoaDon
VALUES ('HD02', 'SP03', 1, 35000, 35000);

-- Xem danh sách chức vụ
SELECT * FROM ChucVu;
-- Xem danh sách khách hàng
SELECT * FROM KhachHang;
-- Xem danh sách bàn ăn/bàn cà phê
SELECT * FROM BanAn;
-- Xem danh sách sản phẩm (Menu)
SELECT * FROM SanPham;
-- Xem danh sách tài khoản/nhân sự
SELECT * FROM TaiKhoan;
-- Xem danh sách hóa đơn tổng quan
SELECT * FROM HoaDon;
-- Xem chi tiết các món trong hóa đơn
SELECT * FROM ChiTietHoaDon;

-- Bước 1: Xóa bảng chi tiết (bảng con thấp nhất)
DELETE FROM ChiTietHoaDon;
-- Bước 2: Xóa bảng hóa đơn (bảng chứa khóa ngoại trỏ về Khách hàng, Bàn, Tài khoản)
DELETE FROM HoaDon;
-- Bước 3: Xóa bảng tài khoản (bảng chứa khóa ngoại trỏ về Chức vụ)
DELETE FROM TaiKhoan;
-- Bước 4: Xóa các bảng độc lập (Xóa bảng nào trước cũng được)
DELETE FROM SanPham;
DELETE FROM KhachHang;
DELETE FROM BanAn;
DELETE FROM ChucVu;