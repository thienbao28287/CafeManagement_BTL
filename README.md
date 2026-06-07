# ☕ Dự án Phần mềm Quản lý Quán Cà Phê (Coffee Shop Management)

## 📖 Giới thiệu
Dự án xây dựng phần mềm quản lý quán cà phê, hỗ trợ nhân viên thực hiện các nghiệp vụ như: đặt món, quản lý bàn, quản lý khách hàng và nhân sự. Hệ thống được cấu trúc rõ ràng, dễ bảo trì và dễ nâng cấp.

## 🛠️ Công cụ & Công nghệ sử dụng
* **Ngôn ngữ:** Java
* **Giao diện người dùng:** Java Swing
* **Cơ sở dữ liệu:** SQL Sever (Cấu trúc bảng nằm trong file `database.sql`)
* **Quản lý mã nguồn:** Git & GitHub
* **Kiến trúc & Tiêu chuẩn:** * Mô hình phân lớp (Controller - Service - Repository).
  * Lập trình hướng đối tượng (OOP).
  * Áp dụng các nguyên lý SOLID (đặc biệt là sử dụng Interface để giảm thiểu sự phụ thuộc).


## 📂 Cấu trúc thư mục dự án

```text
src/
│
├── main/
│   └── MainApplication.java
│
├── database/
│   └── DBConnection.java
│
├── model/
│   ├── TaiKhoan.java
│   ├── ChucVu.java
│   ├── KhachHang.java
│   ├── BanAn.java
│   ├── SanPham.java
│   ├── HoaDon.java
│   └── ChiTietHoaDon.java
│
├── repository/
│   ├── ITaiKhoanRepository.java
│   ├── TaiKhoanRepositoryImpl.java
│   │
│   ├── IKhachHangRepository.java
│   ├── KhachHangRepositoryImpl.java
│   │
│   ├── IBanAnRepository.java
│   ├── BanAnRepositoryImpl.java
│   │
│   ├── ISanPhamRepository.java
│   ├── SanPhamRepositoryImpl.java
│   │
│   ├── IHoaDonRepository.java
│   ├── HoaDonRepositoryImpl.java
│   │
│   ├── IChiTietHoaDonRepository.java
│   └── ChiTietHoaDonRepositoryImpl.java
│
├── service/
│   ├── ITaiKhoanService.java
│   ├── TaiKhoanServiceImpl.java
│   │
│   ├── IKhachHangService.java
│   ├── KhachHangServiceImpl.java
│   │
│   ├── IBanAnService.java
│   ├── BanAnServiceImpl.java
│   │
│   ├── ISanPhamService.java
│   ├── SanPhamServiceImpl.java
│   │
│   ├── IHoaDonService.java
│   ├── HoaDonServiceImpl.java
│   │
│   ├── IChiTietHoaDonService.java
│   └── ChiTietHoaDonServiceImpl.java
│
├── controller/
│   ├── LoginController.java
│   ├── MainController.java
│   │
│   ├── NhanVienController.java
│   ├── KhachHangController.java
│   ├── BanAnController.java
│   ├── SanPhamController.java
│   ├── DatHangController.java
│   └── HoaDonController.java
│
├── view/
│   ├── LoginPanel.java
│   ├── MainFrame.java
│   │
│   ├── HeaderPanel.java
│   ├── SidebarPanel.java
│   │
│   ├── TrangChuPanel.java
│   ├── NhanVienPanel.java
│   ├── KhachHangPanel.java
│   ├── BanAnPanel.java
│   ├── SanPhamPanel.java
│   ├── DatHangPanel.java
│   ├── HoaDonPanel.java
│   └── ChiTietHoaDonPanel.java
│
├── util/
│   ├── SessionUtil.java
│   ├── PermissionUtil.java
│   ├── ValidationUtil.java
│   ├── FormatUtil.java
│   ├── DateUtil.java
│   └── MessageUtil.java
│
├── exception/
│   ├── DatabaseException.java
│   ├── ValidationException.java
│   └── AuthenticationException.java
│

│   ├── icons/
│   │   ├── home.png
│   │   ├── employee.png
│   │   ├── customer.png
│   │   ├── table.png
│   │   ├── product.png
│   │   ├── order.png
│   │   └── invoice.png
│   │
│   └── images/
│
└── database.sql

---
