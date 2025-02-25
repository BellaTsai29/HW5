package controller.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.employee.EmployeeAddUI;
import model.Customer;
import model.Employee;
import service.impl.CustomerServiceImpl;
import util.DbConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class RegisterUI extends JFrame {
	
	private static Connection conn=DbConnection.getDb();
	private static CustomerServiceImpl cs=new CustomerServiceImpl();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField user;
	private JTextField phone;
	private JTextField address;
	private JPasswordField password;
	// glass pane 上的時鐘標籤
    private JLabel clockLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI frame = new RegisterUI();
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
	public RegisterUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 353);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBounds(0, 0, 541, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
        
		JLabel lblNewLabel = new JLabel("會員註冊");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		lblNewLabel.setBounds(171, 10, 235, 57);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("名字:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(20, 102, 54, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("帳號:");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(20, 140, 54, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("密碼:");
		lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(20, 178, 54, 28);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("電話:");
		lblNewLabel_1_2_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(20, 222, 54, 28);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("地址:");
		lblNewLabel_1_2_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1.setBounds(20, 264, 54, 28);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		name = new JTextField();
		name.setBounds(86, 111, 142, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(86, 149, 142, 21);
		contentPane.add(user);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(86, 231, 142, 21);
		contentPane.add(phone);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(86, 273, 142, 21);
		contentPane.add(address);
		
		// ----------------------------
        // 新增 glass pane 時鐘（不影響 contentPane 的布局）
        // ----------------------------
        JPanel glass = new JPanel(null) {
            @Override
            public boolean contains(int x, int y) {
                return false; 
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
       
        clockLabel.setBounds(350, 5, 100, 25);
        glass.add(clockLabel);
        
        Timer timer = new Timer(1000, new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(sdf.format(new Date()));
            }
        });
        timer.start();
		
       
        
		JButton btnNewButton = new JButton("註冊");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String User=user.getText().trim();
				String Password = new String(password.getPassword()).trim();
			    String Name = name.getText().trim();
			    String PhoneStr = phone.getText().trim();  
			    String Address = address.getText().trim();
			    
			    if (User.isEmpty() || Password.isEmpty() || Name.isEmpty() || PhoneStr.isEmpty()|| Address.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "請填寫所有欄位！", "錯誤", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        int phone;
		        try {
		            phone = Integer.parseInt(PhoneStr);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "電話號碼必須為數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        if (!Password.matches("^(?=(?:.*[A-Za-z]){2,})(?=(?:.*\\d){4,})[A-Za-z\\d]{6}$")) {
		            JOptionPane.showMessageDialog(null, "密碼必須為6個字元，包含至少2個英文字母與4個數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        
		        // 檢查該帳號是否已存在
		        if (new CustomerServiceImpl().findByUser(User) != null) {
		            RegisterUI addmembererror = new RegisterUI();
		            addmembererror.setVisible(true);
		            dispose(); 
		        } else {
		            
		            Customer customer = new Customer(Name,User,Password, phone, Address);
		            cs.add(customer);
		            // 新增成功提示
		            JOptionPane.showMessageDialog(null, "註冊成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		        
		        
		        String sql = "INSERT INTO customer (user, password, name, phone,address) VALUES (?, ?, ?, ?,?)";
		        try {
		            PreparedStatement ps = conn.prepareStatement(sql);
		            ps.setString(1, User);
		            ps.setString(2, Password);
		            ps.setString(3, Name);
		            ps.setInt(4, phone);
		            ps.setString(5, Address);
		            
		            int count = ps.executeUpdate();
		            if (count > 0) {
		                JOptionPane.showMessageDialog(null, "註冊成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		                LoginUI loginui = new LoginUI();
		                loginui.setVisible(true);
		                dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "註冊失敗", "錯誤", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "註冊失敗: " + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		}});
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		btnNewButton.setBounds(311, 231, 85, 38);
		contentPane.add(btnNewButton);
		
		password = new JPasswordField();
		password.setBounds(84, 187, 144, 21);
		contentPane.add(password);
		
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     setLocationRelativeTo(null);
	     getContentPane().setLayout(null);
	        
		JCheckBox chckbxNewCheckBox = new JCheckBox("顯示密碼");
		chckbxNewCheckBox.setBounds(296, 182, 112, 31);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (chckbxNewCheckBox.isSelected()) {
		           
		            password.setEchoChar((char)0);
		        } else {
		           
		            password.setEchoChar('*');
		        }
		    }
		});
		contentPane.add(chckbxNewCheckBox);
		password.setEchoChar('*'); 
		}}
