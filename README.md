# HW5
 Drink Order Management System
 📌 專案介紹

本專案是一個 Java Swing 應用程式，提供會員登入、查詢訂單、計算金額、刪除訂單等功能，並與 MySQL 資料庫 進行連結。


✨ 功能特色

會員登入：使用者輸入帳號 (user) 和密碼 (password)，系統驗證後登入。

訂單管理：查詢訂單資訊，包括 紅茶、綠茶、奶茶數量 及 總額計算。

MySQL 數據庫連接：從 customer 和 porder 兩張表中獲取數據。

JFrame 介面：圖形化 UI，包含登入頁、訂單頁、錯誤提示頁。


🔧 環境設置

1️⃣ 安裝 MySQL 資料庫

請確保你的 MySQL 伺服器已安裝，並建立以下兩張表：

CREATE DATABASE drink_order;
USE drink_order;

CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    user VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);

CREATE TABLE porder (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    black INT DEFAULT 0,
    milk INT DEFAULT 0,
    green INT DEFAULT 0,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);


2️⃣ 設置 Java 連線 MySQL

請修改 LoginUI.java 中的 資料庫連線資訊：

private static final String URL = "jdbc:mysql://localhost:3306/drink_order";
private static final String USER = "root";
private static final String PASSWORD = "your_password";



3️⃣ 執行專案

在 IDE (如 IntelliJ IDEA / Eclipse) 中運行 Main.java

登入範例帳號：

帳號：root

密碼：1234

🖥️ 主要程式碼

🔹 MySQL 驗證會員登入 (LoginUI.java)

String sql = "SELECT c.name AS customer_name, p.black, p.milk, p.green, " +
             "(p.black * 30 + p.milk * 80 + p.green * 30) AS total_price " +
             "FROM customer c " +
             "JOIN porder p ON c.id = p.customer_id " +
             "WHERE c.user = ? AND c.password = ?";

🔹 訂單資訊顯示

if (rs.next()) {
    String customerName = rs.getString("customer_name");
    int total = rs.getInt("total_price");
    JOptionPane.showMessageDialog(null, "登入成功！\n客戶：" + customerName + "\n總額：" + total);
    new DrinkUI().setVisible(true);
} else {
    JOptionPane.showMessageDialog(null, "登入失敗！");
    new LoginErrorUI().setVisible(true);
}

