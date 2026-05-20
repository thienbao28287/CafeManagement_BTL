# ☕ Dự án Phần mềm Quản lý Quán Cà Phê (Coffee Shop Management)

## 📖 Giới thiệu
Dự án xây dựng phần mềm quản lý quán cà phê, hỗ trợ nhân viên thực hiện các nghiệp vụ như: đặt món, quản lý bàn, quản lý khách hàng và nhân sự. Hệ thống được cấu trúc rõ ràng, dễ bảo trì và dễ nâng cấp.

## 🛠️ Công cụ & Công nghệ sử dụng
* **Ngôn ngữ:** Java
* **Giao diện người dùng:** Java Swing
* **Cơ sở dữ liệu:** SQL (Cấu trúc bảng nằm trong file `database.sql`)
* **Quản lý mã nguồn:** Git & GitHub
* **Kiến trúc & Tiêu chuẩn:** * Mô hình phân lớp (Controller - Service - Repository).
  * Lập trình hướng đối tượng (OOP).
  * Áp dụng các nguyên lý SOLID (đặc biệt là sử dụng Interface để giảm thiểu sự phụ thuộc).

---

## 📅 Kế hoạch phát triển (20 Ngày)
*Dự án bắt đầu thực hiện từ ngày 18, được chia làm 4 đợt báo cáo tiến độ chi tiết như sau:*

### 📍 Đợt 1: Khởi tạo dự án và Kết nối CSDL (Ngày 18 - Ngày 22)
* Khởi tạo cấu trúc thư mục dự án chuẩn.
* Thiết lập Git, tạo kho lưu trữ trên GitHub và thực hành các lệnh `git pull`, `git push` để quản lý mã nguồn.
* Xây dựng file `database.sql` để tạo các bảng dữ liệu cần thiết.
* Code module kết nối hệ thống Java với Cơ sở dữ liệu (`src/database/DBConnection.java`).
* **Báo cáo Đợt 1:** Code đẩy lên GitHub thành công, test kết nối DB an toàn, không sinh lỗi.
