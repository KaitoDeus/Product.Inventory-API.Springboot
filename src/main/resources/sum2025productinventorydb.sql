CREATE DATABASE sum2025productinventorydb;

USE sum2025productinventorydb;

-- Bảng người dùng hệ thống
CREATE TABLE SystemAccounts (
    AccountID INT PRIMARY KEY,
    Username VARCHAR(100) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role INT,
    IsActive BIT DEFAULT 1
);

INSERT INTO system_accounts (account_id, username, email, Password, role, is_active) VALUES
(1, 'adminpro', 'admin@system.com', 'admin123', 1, 1),
(2, 'manager1', 'manager@system.com', 'manager123', 2, 1),
(3, 'analyst1', 'analyst@system.com', 'analyst123', 3, 1),
(4, 'user1', 'user1@system.com', 'user123', 4, 1),
(5, 'suspended', 'blocked@system.com', 'nopass', 2, 0);

-- Bảng danh mục sản phẩm
CREATE TABLE Category (
    CategoryID INT PRIMARY KEY,
    CategoryName VARCHAR(255) NOT NULL,
    Description VARCHAR(500)
);

-- Bảng sản phẩm
CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    CategoryID INT,
    ProductName VARCHAR(255) NOT NULL,
    Material VARCHAR(100),
    Price DECIMAL(10, 2),
    Quantity INT,
    ReleaseDate DATE,
    CONSTRAINT fk_product_category FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID) ON DELETE CASCADE
);

-- Dữ liệu mẫu cho danh mục
INSERT INTO category (category_id, category_name, description) VALUES
(1, 'Electronics', 'Electronic devices and accessories'),
(2, 'Wearables', 'Smartwatches, fitness bands'),
(3, 'Home Appliances', 'Appliances for home use'),
(4, 'Books', 'Printed and digital books'),
(5, 'Gaming', 'Consoles, accessories and titles');

-- Dữ liệu mẫu cho sản phẩm
INSERT INTO product (product_id, category_id, product_name, material, price, quantity, release_date) VALUES
(1, 1, 'Wireless Earbuds Pro', 'Plastic', 199.99, 100, '2024-01-15'),
(2, 1, 'Smartphone X10', 'Aluminum & Glass', 999.00, 50, '2024-02-10'),
(3, 2, 'Smartwatch Z3', 'Metal', 149.99, 75, '2024-03-01'),
(4, 3, 'Air Purifier Pro', 'Steel', 259.00, 40, '2024-01-05'),
(5, 4, 'Artificial Intelligence 101', 'Paper', 29.99, 200, '2023-12-20'),
(6, 5, 'NextGen Console V', 'Plastic', 499.00, 30, '2024-02-20'),
(7, 5, 'Wireless Controller 2.0', 'Plastic', 69.99, 150, '2024-01-25'),
(8, 2, 'Fitness Band Plus', 'Rubber', 89.99, 90, '2024-03-10'),
(9, 3, 'Robot Vacuum Cleaner', 'Plastic', 299.00, 25, '2024-04-01'),
(10, 4, 'Data Structures Guidebook', 'Paper', 45.00, 120, '2024-01-18');
