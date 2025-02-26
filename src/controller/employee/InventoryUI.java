package controller.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoryUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField tfQueryBranch;
    private DefaultTableModel tableModel;
    
 // glass pane 上的時鐘標籤
    private JLabel clockLabel;

    private JTextField tfAddBranch, tfAddTea, tfAddMilk, tfAddPearls, tfAddTaroball;
    private JButton btnQuery, btnAdd, btnPrint;
    
    private static final String URL = "jdbc:mysql://localhost:3306/drink";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // 自訂 DocumentFilter 限制數字輸入
    public class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string.matches("[0-9]*")) {
                super.insertString(fb, offset, string, attr);
            }
        }
        
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("[0-9]*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // 設定 Nimbus LookAndFeel (若可用)
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // 忽略例外
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InventoryUI frame = new InventoryUI();
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
    public InventoryUI() {
        setTitle("庫存查詢系統");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 580);
        // 主視窗採用簡約白色背景與邊距
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        
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
        
        // 標題區
        JPanel panelTitle = new JPanel();
        panelTitle.setBackground(Color.WHITE);
        panelTitle.setBounds(0, 0, 730, 60);
        panelTitle.setLayout(new BorderLayout());
        contentPane.add(panelTitle);
        
        JLabel lblTitle = new JLabel("庫存查詢系統", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitle.setForeground(Color.DARK_GRAY);
        panelTitle.add(lblTitle, BorderLayout.CENTER);
        
        // 查詢區（上半部）
        JPanel panelQuery = new JPanel();
        panelQuery.setBackground(Color.WHITE);
        panelQuery.setBounds(0, 70, 730, 80);
        panelQuery.setLayout(null);
        contentPane.add(panelQuery);
        
        JLabel lblQueryBranch = new JLabel("分店名稱：");
        lblQueryBranch.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblQueryBranch.setForeground(Color.DARK_GRAY);
        lblQueryBranch.setBounds(20, 25, 100, 30);
        panelQuery.add(lblQueryBranch);
        
        tfQueryBranch = new JTextField();
        tfQueryBranch.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tfQueryBranch.setBounds(130, 25, 120, 30);
        panelQuery.add(tfQueryBranch);
        tfQueryBranch.setColumns(10);
        
        btnQuery = new JButton("查詢");
        btnQuery.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnQuery.setBounds(270, 25, 100, 30);
        btnQuery.setFocusPainted(false);
        btnQuery.setContentAreaFilled(false);
        btnQuery.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelQuery.add(btnQuery);
        
        JButton btnQuery_2 = new JButton("刪除");
        btnQuery_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		deleteOrderById(tfQueryBranch.getText());
        		loadOrders();
        		
        	}
        });
        btnQuery_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnQuery_2.setFocusPainted(false);
        btnQuery_2.setContentAreaFilled(false);
        btnQuery_2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnQuery_2.setBounds(397, 25, 100, 30);
        panelQuery.add(btnQuery_2);
        btnQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfQueryBranch.getText().trim().isEmpty()) {
                    loadOrders();
                } else {
                    loadOrders_specific(tfQueryBranch.getText().trim());
                }
            }
        });
        
        // 表格區（中間）
        tableModel = new DefaultTableModel(new String[]{"ID", "分店名稱", "茶葉", "牛奶", "珍珠", "芋圓"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 160, 730, 260);
        contentPane.add(scrollPane);
        
        // 下方區域（分為輸入區與按鈕區）
        JPanel panelLower = new JPanel();
        panelLower.setBackground(Color.WHITE);
        panelLower.setLayout(null);
        panelLower.setBounds(0, 430, 730, 110);
        contentPane.add(panelLower);
        
        // 輸入區：全部排列於同一行，調整高度為30
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setLayout(null);
        inputPanel.setBounds(0, 0, 730, 50);
        panelLower.add(inputPanel);
        
        // 分店名稱：label + text field
        JLabel lblAddBranch = new JLabel("分店名稱：");
        lblAddBranch.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblAddBranch.setForeground(Color.DARK_GRAY);
        lblAddBranch.setBounds(10, 10, 100, 30);
        inputPanel.add(lblAddBranch);
        tfAddBranch = new JTextField();
        tfAddBranch.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tfAddBranch.setBounds(110, 10, 120, 30);
        inputPanel.add(tfAddBranch);
        tfAddBranch.setColumns(10);
        
        // 茶葉：label + text field
        JLabel lblTea = new JLabel("茶葉：");
        lblTea.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblTea.setForeground(Color.DARK_GRAY);
        lblTea.setBounds(240, 10, 60, 30);
        inputPanel.add(lblTea);
        tfAddTea = new JTextField();
        tfAddTea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tfAddTea.setBounds(300, 10, 60, 30);
        inputPanel.add(tfAddTea);
        tfAddTea.setColumns(10);
        ((AbstractDocument) tfAddTea.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        
        // 牛奶：label + text field
        JLabel lblMilk = new JLabel("牛奶：");
        lblMilk.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblMilk.setForeground(Color.DARK_GRAY);
        lblMilk.setBounds(370, 10, 60, 30);
        inputPanel.add(lblMilk);
        tfAddMilk = new JTextField();
        tfAddMilk.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tfAddMilk.setBounds(430, 10, 60, 30);
        inputPanel.add(tfAddMilk);
        tfAddMilk.setColumns(10);
        ((AbstractDocument) tfAddMilk.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        
        // 珍珠：label + text field
        JLabel lblPearls = new JLabel("珍珠：");
        lblPearls.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblPearls.setForeground(Color.DARK_GRAY);
        lblPearls.setBounds(490, 10, 60, 30);
        inputPanel.add(lblPearls);
        tfAddPearls = new JTextField();
        tfAddPearls.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tfAddPearls.setBounds(550, 10, 60, 30);
        inputPanel.add(tfAddPearls);
        tfAddPearls.setColumns(10);
        ((AbstractDocument) tfAddPearls.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        
        // 芋圓：label + text field
        JLabel lblTaroball = new JLabel("芋圓：");
        lblTaroball.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblTaroball.setForeground(Color.DARK_GRAY);
        lblTaroball.setBounds(610, 10, 60, 30);
        inputPanel.add(lblTaroball);
        tfAddTaroball = new JTextField();
        tfAddTaroball.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tfAddTaroball.setBounds(670, 10, 60, 30);
        inputPanel.add(tfAddTaroball);
        tfAddTaroball.setColumns(10);
        ((AbstractDocument) tfAddTaroball.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        
        // 按鈕區：僅包含「新增」與「列印CSV」
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setLayout(null);
        btnPanel.setBounds(0, 70, 730, 40);
        panelLower.add(btnPanel);
        
        btnAdd = new JButton("新增");
        btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnAdd.setFocusPainted(false);
        btnAdd.setContentAreaFilled(false);
        btnAdd.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnAdd.setBounds(160, 5, 100, 30);
        btnPanel.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInventory();
            }
        });
        
        btnPrint = new JButton("列印CSV");
        btnPrint.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnPrint.setFocusPainted(false);
        btnPrint.setContentAreaFilled(false);
        btnPrint.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnPrint.setBounds(400, 5, 100, 30);
        btnPanel.add(btnPrint);
        
        JButton btnQuery_1 = new JButton("返回");
        btnQuery_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		OrderManager ooo = new OrderManager();
        		ooo.setVisible(true);
        		dispose();
        	}
        });
        btnQuery_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnQuery_1.setFocusPainted(false);
        btnQuery_1.setContentAreaFilled(false);
        btnQuery_1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnQuery_1.setBounds(620, 5, 100, 30);
        btnPanel.add(btnQuery_1);
        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportCSV();
            }
        });
        
        loadOrders();
    }
    
    private void deleteOrderById(String name) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM inventory WHERE branch = ?")) {
            
            pstmt.setString(1, name);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "成功刪除該筆資料", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "查無該筆資料", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "刪除失敗：" + e.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // 讀取所有庫存
    private void loadOrders() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM inventory")) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("branch"),
                    rs.getString("tea"),
                    rs.getString("milk"),
                    rs.getString("pearls"),
                    rs.getString("taroball")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 讀取特定庫存
    private void loadOrders_specific(String branchSearch) {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM inventory WHERE branch = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, branchSearch);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("branch"),
                        rs.getString("tea"),
                        rs.getString("milk"),
                        rs.getString("pearls"),
                        rs.getString("taroball")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 新增庫存
    private void addInventory() {
        String branch = tfAddBranch.getText().trim();
        String tea = tfAddTea.getText().trim();
        String milk = tfAddMilk.getText().trim();
        String pearls = tfAddPearls.getText().trim();
        String tarco = tfAddTaroball.getText().trim();
        
        if (branch.isEmpty() || tea.isEmpty() || milk.isEmpty() || pearls.isEmpty() || tarco.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請填寫完整資訊！");
            return;
        }
        
        try {
            int tea_i = Integer.parseInt(tea);
            int milk_i = Integer.parseInt(milk);
            int pearls_i = Integer.parseInt(pearls);
            int tarco_i = Integer.parseInt(tarco);
            
            String sql = "INSERT INTO inventory (branch, tea, milk, pearls, taroball) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, branch);
                stmt.setInt(2, tea_i);
                stmt.setInt(3, milk_i);
                stmt.setInt(4, pearls_i);
                stmt.setInt(5, tarco_i);
                stmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(this, "新增成功！");
            loadOrders();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "數量請輸入數字！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 匯出 CSV 檔案並開啟，採用 UTF-8 編碼與 BOM 以避免亂碼
    private void exportCSV() {
        StringBuilder csv = new StringBuilder();
        csv.append("ID,分店名稱,茶葉,牛奶,珍珠,芋圓\n");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                csv.append(tableModel.getValueAt(i, j));
                if (j < tableModel.getColumnCount() - 1) {
                    csv.append(",");
                }
            }
            csv.append("\n");
        }
        
        try {
            String filePath = System.getProperty("user.home") + File.separator + "inventory.csv";
            File csvFile = new File(filePath);
            // 使用 OutputStreamWriter 指定 UTF-8 編碼，並寫入 UTF-8 BOM
            try (PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(new FileOutputStream(csvFile), StandardCharsets.UTF_8))) {
                // 寫入 BOM (0xEF,0xBB,0xBF)
                writer.write("\uFEFF");
                writer.write(csv.toString());
            }
            JOptionPane.showMessageDialog(this, "CSV 檔案已儲存：" + filePath);
            
            // 使用 Desktop 開啟 CSV 檔案
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(csvFile);
            } else {
                JOptionPane.showMessageDialog(this, "系統不支援開啟CSV檔案", "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "匯出失敗：" + ex.getMessage());
        }
    }
}
