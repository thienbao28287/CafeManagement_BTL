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

## 📂 Cấu trúc thư mục dự án

```text
src/
├── component/          # Chứa các thành phần giao diện nhỏ, dùng đi dùng lại nhiều lần
│   ├── ButtonCustom.java   # Nút bấm được thiết kế riêng
│   ├── HeaderPanel.java    # Thanh tiêu đề phía trên
│   ├── SidebarPanel.java   # Thanh menu điều hướng bên cạnh
│   └── TableCustom.java    # Bảng hiển thị dữ liệu tùy chỉnh
│
├── controller/         # "Người điều phối" - Nhận thao tác click chuột từ View, gọi Service xử lý
│   ├── CustomerController.java
│   ├── EmployeeController.java
│   ├── LoginController.java
│   ├── MainController.java
│   ├── OrderController.java
│   └── TableController.java
│
├── database/           # Chứa code để kết nối Java với hệ quản trị CSDL
│   └── DBConnection.java
│
├── exception/          # Các file tự định nghĩa lỗi riêng biệt để dễ kiểm soát khi code chạy sai
│   └── DatabaseException.java
│
├── main/               # Nơi chứa hàm main() - Trái tim khởi động toàn bộ ứng dụng
│   └── MainApplication.java
│
├── model/              # Định nghĩa cấu trúc các đối tượng thực tế (giống hệt các bảng trong Database)
│   ├── CoffeeTable.java    
│   ├── Customer.java       
│   ├── Employee.java       
│   ├── OrderDrink.java     
│   ├── OrderFood.java      
│   └── Orders.java         
│
├── repository/         # "Thợ đào mỏ" - Tầng chuyên viết câu lệnh SQL (INSERT, UPDATE, DELETE, SELECT)
│   ├── CoffeeTableRepoImpl.java
│   ├── CustomerRepoImpl.java
│   ├── EmployeeRepoImpl.java
│   ├── ICoffeeTableRepo.java    # (Các file bắt đầu bằng chữ I là Interface - bộ khung quy tắc)
│   ├── ICustomerRepo.java
│   ├── IEmployeeRepo.java
│   ├── IOrderDrinkRepo.java
│   ├── IOrderFoodRepo.java
│   ├── IOrderRepo.java
│   ├── OrderDrinkRepoImpl.java
│   ├── OrderFoodRepoImpl.java
│   └── OrderRepoImpl.java
│
├── service/            # Tầng xử lý logic, tính toán, kiểm tra đúng/sai trước khi gọi Repository
│   ├── CoffeeTableServiceImpl.java
│   ├── CustomerServiceImpl.java
│   ├── EmployeeServiceImpl.java
│   ├── ICoffeeTableService.java 
│   ├── ICustomerService.java
│   ├── IEmployeeService.java
│   ├── IOrderDrinkService.java
│   ├── IOrderFoodService.java
│   ├── IOrderService.java
│   ├── OrderDrinkServiceImpl.java
│   └── OrderServiceImpl.java
│
├── test/               # Thư mục nháp để chạy thử nhanh các đoạn code logic nhỏ
│   └── Main.java
│
├── util/               # Chứa các công cụ dùng chung ở mọi nơi trong dự án
│   ├── FormatUtil.java     # Định dạng tiền tệ, ngày tháng...
│   ├── MessageUtil.java    # Hiển thị các hộp thoại thông báo (thành công, thất bại...)
│   └── SessionUtil.java    # Lưu phiên đăng nhập hiện tại của nhân viên
│
├── view/               # Tầng giao diện người dùng (các màn hình cửa sổ hiện lên)
│   ├── CustomerPanel.java  # Màn hình quản lý khách hàng
│   ├── EmployeePanel.java  # Màn hình quản lý nhân viên
│   ├── HomePanel.java      # Màn hình trang chủ
│   ├── LoginFrame.java     # Cửa sổ đăng nhập
│   ├── MainFrame.java      # Khung cửa sổ chính bọc các Panel bên trong
│   ├── OrderPanel.java     # Màn hình đặt món
│   └── TablePanel.java     # Màn hình chọn bàn
│
└── database.sql        # File text chứa mã nguồn SQL để tạo bảng và dữ liệu mẫu dưới Database
---

## 📅 Kế hoạch phát triển (20 Ngày)
*Dự án bắt đầu thực hiện từ ngày 18, được chia làm 4 đợt báo cáo tiến độ chi tiết như sau:*

### 📍 Đợt 1: Khởi tạo dự án và Kết nối CSDL (Ngày 18 - Ngày 22)
* Khởi tạo cấu trúc thư mục dự án chuẩn.
* Thiết lập Git, tạo kho lưu trữ trên GitHub và thực hành các lệnh `git pull`, `git push` để quản lý mã nguồn.
* Xây dựng file `database.sql` để tạo các bảng dữ liệu cần thiết.
* Code module kết nối hệ thống Java với Cơ sở dữ liệu (`src/database/DBConnection.java`).
* **Báo cáo Đợt 1:** Code đẩy lên GitHub thành công, test kết nối DB an toàn, không sinh lỗi.
