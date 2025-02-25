package controller.customer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controller.DrinkUI;
import util.Tool;

public class LoginUI extends JFrame {

    private JTextField userField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private static final String URL = "jdbc:mysql://localhost:3306/drink";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    
    // glass pane 上的時鐘標籤
    private JLabel clockLabel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
         EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginUI frame = new LoginUI();
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
    public LoginUI() {
        getContentPane().setBackground(new Color(135, 206, 235));
        setTitle("會員登錄");
        setSize(477, 334);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
       
        JLabel userLabel = new JLabel("帳號:");
        userLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        userLabel.setBounds(43, 97, 122, 45);
        contentPane.add(userLabel);
        
        userField = new JTextField();
        userField.setBounds(134, 97, 122, 45);
        contentPane.add(userField);
        userField.setColumns(10);
        
        JLabel passwordLabel = new JLabel("密碼:");
        passwordLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        passwordLabel.setBounds(43, 152, 122, 45);
        contentPane.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(134, 152, 122, 45);
        passwordField.setEchoChar('*'); 
        contentPane.add(passwordField);
        
        showPassword = new JCheckBox("顯示密碼");
        showPassword.setBounds(291, 152, 122, 45);
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
        contentPane.add(showPassword);
        
        JButton registerButton = new JButton("註冊");
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { 
                new RegisterUI().setVisible(true);
                dispose();
            }
        });
        registerButton.setBounds(27, 220, 122, 45);
        contentPane.add(registerButton);
        
        JButton loginButton = new JButton("登錄");
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                
                if (validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "登錄成功！");
                    new DrinkUI().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "登錄失敗，請檢查帳號或密碼！");
                }
            }
        });
        loginButton.setBounds(159, 220, 122, 45);
        contentPane.add(loginButton);
        
        JButton backButton = new JButton("返回");
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tool.HomeUI();
                dispose();
            }
        });
        backButton.setBounds(291, 220, 122, 45);
        contentPane.add(backButton);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(172, 217, 216));
        panel.setBounds(0, 0, 463, 64);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("會員登錄");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
        lblNewLabel.setBounds(168, 20, 191, 34);
        panel.add(lblNewLabel);
        
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
        clockLabel.setBounds(350, 5, 100, 25);
        glass.add(clockLabel);
        
        Timer timer = new Timer(1000, new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(sdf.format(new Date()));
            }
        });
        timer.start();
        
        // 可選擇在視窗尺寸變動時重新調整時鐘位置
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e) {
                int glassWidth = getGlassPane().getWidth();
                clockLabel.setBounds(glassWidth - 110, 5, 100, 25);
            }
        });
        
        setVisible(true);
    }
    
    private boolean validateLogin(String user, String password) {
        String sql = "SELECT * FROM customer WHERE user = ? AND password = ?";
        try (Connection conn = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // 若有資料代表登入成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
