-- =============================================
-- XÓA DATABASE NẾU ĐÃ TỒN TẠI
-- =============================================
USE master;
GO

IF EXISTS (
    SELECT * FROM sys.databases
    WHERE name = 'CoffeeShopManagement_JSON'
)
BEGIN
    ALTER DATABASE CoffeeShopManagement_JSON
    SET SINGLE_USER
    WITH ROLLBACK IMMEDIATE;

    DROP DATABASE CoffeeShopManagement_JSON;
END
GO

-- =============================================
-- TẠO DATABASE
-- =============================================
CREATE DATABASE CoffeeShopManagement_JSON;
GO

USE CoffeeShopManagement_JSON;
GO

-- =============================================
-- 1. TABLE CoffeeTable
-- =============================================
CREATE TABLE CoffeeTable (
    TableID VARCHAR(20) PRIMARY KEY,

    SeatCount INT NOT NULL,

    Status NVARCHAR(50)
    DEFAULT N'Trống'
    CHECK (
        Status IN (
            N'Trống',
            N'Đang dùng',
            N'Đặt trước'
        )
    ),

    Location NVARCHAR(100)
);
GO

-- =============================================
-- 2. TABLE Customer
-- =============================================
CREATE TABLE Customer (
    CustomerID VARCHAR(20) PRIMARY KEY,

    CustomerName NVARCHAR(100) NOT NULL,

    PhoneNumber VARCHAR(15),

    Address NVARCHAR(255)
);
GO

-- =============================================
-- 3. TABLE Employee
-- =============================================
CREATE TABLE Employee (
    EmployeeID VARCHAR(20) PRIMARY KEY,

    EmployeeName NVARCHAR(100) NOT NULL,

    PhoneNumber VARCHAR(15),

    Position NVARCHAR(50),

    Salary DECIMAL(18,2)
);
GO

-- =============================================
-- 4. TABLE Orders
-- =============================================
CREATE TABLE Orders (
    OrderID VARCHAR(20) PRIMARY KEY,

    TableID VARCHAR(20) NOT NULL,

    CustomerID VARCHAR(20),

    EmployeeID VARCHAR(20),

    TotalAmount DECIMAL(18,2),

    Status NVARCHAR(50)
    DEFAULT N'Đang phục vụ'
    CHECK (
        Status IN (
            N'Đang phục vụ',
            N'Đã thanh toán',
            N'Hủy'
        )
    ),

    CreatedAt DATETIME DEFAULT GETDATE(),

    FOREIGN KEY (TableID)
        REFERENCES CoffeeTable(TableID),

    FOREIGN KEY (CustomerID)
        REFERENCES Customer(CustomerID),

    FOREIGN KEY (EmployeeID)
        REFERENCES Employee(EmployeeID)
);
GO

-- =============================================
-- 5. TABLE OrderDrink
-- =============================================
CREATE TABLE OrderDrink (
    DrinkID INT IDENTITY(1,1) PRIMARY KEY,

    OrderID VARCHAR(20) NOT NULL,

    DrinkName NVARCHAR(100) NOT NULL,

    Price DECIMAL(18,2) NOT NULL,

    Quantity INT NOT NULL,

    FOREIGN KEY (OrderID)
        REFERENCES Orders(OrderID)
        ON DELETE CASCADE
);
GO

-- =============================================
-- 6. TABLE OrderFood
-- =============================================
CREATE TABLE OrderFood (
    FoodID INT IDENTITY(1,1) PRIMARY KEY,

    OrderID VARCHAR(20) NOT NULL,

    FoodName NVARCHAR(100) NOT NULL,

    Price DECIMAL(18,2) NOT NULL,

    Quantity INT NOT NULL,

    FOREIGN KEY (OrderID)
        REFERENCES Orders(OrderID)
        ON DELETE CASCADE
);
GO

-- =============================================
-- INSERT DATA: CoffeeTable
-- =============================================
INSERT INTO CoffeeTable
(TableID, SeatCount, Status, Location)
VALUES
('BAN01', 2, N'Trống', N'Tầng 1 - Cạnh cửa sổ'),
('BAN02', 4, N'Đang dùng', N'Tầng 1 - Trung tâm'),
('BAN03', 6, N'Đặt trước', N'Tầng 2 - Ban công'),
('BAN04', 2, N'Trống', N'Tầng 2 - Góc yên tĩnh'),
('BAN05', 8, N'Đang dùng', N'Tầng 1 - Khu gia đình');
GO

-- =============================================
-- INSERT DATA: Customer
-- =============================================
INSERT INTO Customer
(CustomerID, CustomerName, PhoneNumber, Address)
VALUES
('KH01', N'Nguyễn Văn An', '0901234567', N'Cầu Giấy, Hà Nội'),
('KH02', N'Trần Thị Bình', '0912345678', N'Đống Đa, Hà Nội'),
('KH03', N'Lê Minh Hoàng', '0988888888', N'Hà Đông, Hà Nội'),
('KH04', N'Phạm Thu Hà', '0977777777', N'Long Biên, Hà Nội');
GO

-- =============================================
-- INSERT DATA: Employee
-- =============================================
INSERT INTO Employee
(EmployeeID, EmployeeName, PhoneNumber, Position, Salary)
VALUES
('NV01', N'Lê Minh Cường', '0987654321', N'Thu ngân', 7000000),
('NV02', N'Phạm Thu Dung', '0976543210', N'Phục vụ', 5000000),
('NV03', N'Nguyễn Quốc Huy', '0966666666', N'Pha chế', 6500000),
('NV04', N'Trịnh Văn Nam', '0955555555', N'Quản lý', 12000000);
GO

-- =============================================
-- INSERT DATA: Orders
-- =============================================
INSERT INTO Orders
(OrderID, TableID, CustomerID, EmployeeID, TotalAmount, Status)
VALUES
('ORD01', 'BAN02', 'KH01', 'NV01', 115000, N'Đang phục vụ'),

('ORD02', 'BAN01', NULL, 'NV02', 45000, N'Đã thanh toán'),

('ORD03', 'BAN05', 'KH03', 'NV01', 245000, N'Đang phục vụ'),

('ORD04', 'BAN03', 'KH02', 'NV04', 80000, N'Hủy');
GO

-- =============================================
-- INSERT DATA: OrderDrink
-- =============================================
INSERT INTO OrderDrink
(OrderID, DrinkName, Price, Quantity)
VALUES

('ORD01', N'Cà phê sữa đá', 35000, 2),
('ORD01', N'Trà đào cam sả', 45000, 1),

('ORD02', N'Sinh tố xoài', 45000, 1),

('ORD03', N'Latte', 50000, 2),
('ORD03', N'Cappuccino', 55000, 1),

('ORD04', N'Trà vải', 40000, 2);
GO

-- =============================================
-- INSERT DATA: OrderFood
-- =============================================
INSERT INTO OrderFood
(OrderID, FoodName, Price, Quantity)
VALUES

('ORD01', N'Bánh tiramisu', 30000, 1),

('ORD03', N'Khoai tây chiên', 45000, 2),
('ORD03', N'Bánh mì bơ tỏi', 35000, 1),

('ORD04', N'Bánh sừng trâu', 20000, 2);
GO
-- Truy vấn dữ liệu
select * from OrderDrink
select * from OrderFood
select * from Employee
select * from Orders
select * from Customer
select * from CoffeeTable

-- Chi tiết đơn hàng
SELECT 
    N'Nước uống' AS Loai, 
    DrinkName AS TenMon, 
    Quantity AS SoLuong, 
    Price AS DonGia, 
    (Quantity * Price) AS ThanhTien
FROM OrderDrink
WHERE OrderID = 'ORD01'

UNION ALL

SELECT 
    N'Đồ ăn' AS Loai, 
    FoodName AS TenMon, 
    Quantity AS SoLuong, 
    Price AS DonGia, 
    (Quantity * Price) AS ThanhTien
FROM OrderFood
WHERE OrderID = 'ORD01';