package controller.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DrinkUI;
import model.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderManager extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField, nameField, drinkField, quantityField;
    
 // glass pane 上的時鐘標籤
    private JLabel clockLabel;

	/**
	 * Launch the application.
	 */
   private static final String URL = "jdbc:mysql://localhost:3306/drink";
    private static final String USER = "root";  
    private static final String PASSWORD = "1234";

    
	public static void main1(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderManager frame = new OrderManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderManager() {
		setTitle("訂單管理系統");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // 建立表格
        tableModel = new DefaultTableModel(new String[]{"ID", "客戶", "飲料", "數量"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        
     // ----------------------------
        // 新增 glass pane 時鐘（不影響 contentPane 的布局）
        // ----------------------------
        JPanel glass = new JPanel(null) {
            @Override
            public boolean contains(int x, int y) {
                return false; // 不攔截滑鼠事件
            }
        };
        glass.setOpaque(false);
        setGlassPane(glass);
        glass.setVisible(true);
        
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        clockLabel.setOpaque(true);
        clockLabel.setBackground(Color.WHITE);
        clockLabel.setForeground(Color.BLACK);
        clockLabel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GRAY, 1));
        // 初始位置設定：右上角，您可以依需要調整
        clockLabel.setBounds(600, 5, 100, 25);
        glass.add(clockLabel);
        
        Timer timer = new Timer(1000, new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(sdf.format(new Date()));
            }
        });
        timer.start();
        // 上方查詢區塊
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("輸入客戶姓名:"));
        searchField = new JTextField(10);
        searchPanel.add(searchField);
        JButton searchButton = new JButton("查詢");
        searchPanel.add(searchButton);
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        
        JButton searchButton_1 = new JButton("分店庫存");
        searchButton_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		InventoryUI inventoryui = new InventoryUI(); // 創建新視窗
        		inventoryui.setVisible(true); // 顯示新視窗
        		dispose();
        	}
        });
        searchPanel.add(searchButton_1);

        // 下方新增/刪除區塊
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 4, 10, 10));

        inputPanel.add(new JLabel("客戶名稱:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("飲料:"));
        drinkField = new JTextField();
        inputPanel.add(drinkField);

        inputPanel.add(new JLabel("數量:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        JButton addButton = new JButton("新增");
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("刪除");
        inputPanel.add(deleteButton);

        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        // 監聽按鈕事件
//        searchButton.addActionListener(e -> searchOrder());
//        addButton.addActionListener(e -> addOrder());
//        deleteButton.addActionListener(e -> deleteOrder());

        // 載入所有訂單
//        loadOrders();
    }
/*
    // 讀取所有訂單
    private void loadOrders() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM porder")) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getString("drink_name"),
                        rs.getInt("quantity")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
//	 public static void login(String user, String password) {
//	        String sql = "SELECT c.name AS customer_name, p.black, p.milk, p.green, " +
//	                     "(p.black * 30 + p.milk * 80 + p.green * 30) AS total_price " +
//	                     "FROM customer c " +
//	                     "JOIN porder p ON c.id = p.customer_id " +
//	                     "WHERE c.user = ? AND c.password = ?";
//
//	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//	            pstmt.setString(1, user);
//	            pstmt.setString(2, password);
//	            ResultSet rs = pstmt.executeQuery();
//
//	            if (rs.next()) {
//	                String customerName = rs.getString("customer_name");
//	                int black = rs.getInt("black");
//	                int milk = rs.getInt("milk");
//	                int green = rs.getInt("green");
//	                int total = rs.getInt("total_price");
//
//	                JOptionPane.showMessageDialog(null, 
//	                    "登入成功！\n客戶：" + customerName + 
//	                    "\n紅茶：" + black + "\n綠茶：" + green + 
//	                    "\n奶茶：" + milk + "\n總額：" + total);
//
//	                // 轉跳到 DrinkUI
//	                DrinkUI drinkUI = new DrinkUI();
//	                drinkUI.setVisible(true);
//	            } else {
//	                JOptionPane.showMessageDialog(null, "登入失敗！");
//	                // 轉跳到 LoginErrorUI
//	                LoginErrorUI errorUI = new LoginErrorUI();
//	                errorUI.setVisible(true);
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}
//	
//    // 查詢訂單
//    private void searchOrder() {
//        String customerName = searchField.getText().trim();
//        if (customerName.isEmpty()) {
//            loadOrders();
//            return;
//        }
//
//        tableModel.setRowCount(0);
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM porder WHERE customer_name LIKE ?")) {
//            stmt.setString(1, "%" + customerName + "%");
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                tableModel.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getString("customer_name"),
//                      //  rs.getString("drink_name"),
//                        //rs.getInt("quantity")
//                });
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // 新增訂單
//    private void addOrder() {
//        String customer = nameField.getText().trim();
//        String drink = drinkField.getText().trim();
//        String quantityText = quantityField.getText().trim();
//
//        if (customer.isEmpty() || drink.isEmpty() || quantityText.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "請填寫完整資訊！");
//            return;
//        }
//
//        try {
//            int quantity = Integer.parseInt(quantityText);
//            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//                 PreparedStatement stmt = conn.prepareStatement(
//                "INSERT INTO porder (customer_name, drink_name, quantity) VALUES (?, ?, ?)")) {
//                stmt.setString(1, customer);
//                stmt.setString(2, drink);
//                stmt.setInt(3, quantity);
//                stmt.executeUpdate();
//            }
//            JOptionPane.showMessageDialog(this, "新增成功！");
//            loadOrders();
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "數量請輸入數字！");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 刪除訂單
//    private void deleteOrder() {
//        int selectedRow = table.getSelectedRow();
//        if (selectedRow == -1) {
//            JOptionPane.showMessageDialog(this, "請選擇要刪除的訂單！");
//            return;
//        }
//
//        int id = (int) tableModel.getValueAt(selectedRow, 0);
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement stmt = conn.prepareStatement("DELETE FROM porder WHERE id = ?")) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//            JOptionPane.showMessageDialog(this, "刪除成功！");
//            loadOrders();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrderManager().setVisible(true));
    }
}

