package controller.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.EmployeeDaoImpl;
import model.Employee;
import service.impl.EmployeeServiceImpl;
import util.Tool;

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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class EmployeeAddUI extends JFrame {

	private EmployeeDaoImpl employeedaoimpl = new EmployeeDaoImpl();
	private EmployeeServiceImpl employeeserviceimpl= new EmployeeServiceImpl();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField address;
	private JTextField phone;
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
					EmployeeAddUI frame = new EmployeeAddUI();
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
	public EmployeeAddUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(239, 236, 116));
		panel.setBounds(0, 0, 465, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("歡迎加入");
		lblNewLabel.setBounds(137, 10, 142, 35);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(166, 244, 136));
		panel_1.setBounds(0, 65, 418, 254);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("姓名:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 20, 62, 33);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼:");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 77, 62, 33);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("地址:");
		lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 146, 62, 33);
		panel_1.add(lblNewLabel_1_2);
		
		name = new JTextField();
		name.setBounds(89, 31, 96, 21);
		panel_1.add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(89, 146, 96, 21);
		panel_1.add(address);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.employee.EmployeeLoginUI eLogin=new controller.employee.EmployeeLoginUI();
				eLogin.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnNewButton.setBounds(293, 170, 87, 23);
		panel_1.add(btnNewButton);
		
        
		JButton btnNewButton_1 = new JButton("註册");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			 String Number = "A" + String.valueOf(employeedaoimpl.FindMaxID() + 1);
			        
			       
			   if (new EmployeeServiceImpl().findByNumber(Number) != null) {
			        EmployeeAddUI addmembererror = new EmployeeAddUI();
			        addmembererror.setVisible(true);
			          dispose(); 
			   } else {
			           
			         String Name = name.getText();
			         String Phone = phone.getText();
			         String Address = address.getText();
			         String Password = password.getText();
			            
			    if (Name.isEmpty() || Phone.isEmpty() || Address.isEmpty() || Password.isEmpty()) {
			         JOptionPane.showMessageDialog(null, "請填寫所有欄位！");
			         return;
			          }
			    
			    int phone_2;
			    try {
		            phone_2 = Integer.parseInt(Phone);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "電話號碼必須為數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
			    
			    if (!Password.matches("^(?=(?:.*[A-Za-z]){2,})(?=(?:.*\\d){4,})[A-Za-z\\d]{6}$")) {
		            JOptionPane.showMessageDialog(null, "密碼必須為6個字元，包含至少2個英文字母與4個數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
			            
			       Employee employee = new Employee(Number, Name,Integer.parseInt(Phone), Address, Password);
			       
			       //新增員工資料
			       employeeserviceimpl.add(employee);
			       
			        }
			   
			   // 註冊成功提示
	            JOptionPane.showMessageDialog(null, "註冊成功！");

	            // 清空輸入欄位
	            name.setText("");
	            phone.setText("");
	            address.setText("");
	            password.setText("");
			
			    }
			});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnNewButton_1.setBounds(293, 218, 87, 23);
		panel_1.add(btnNewButton_1);
		
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
		
		JLabel lblNewLabel_1_2_1 = new JLabel("電話:");
		lblNewLabel_1_2_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(10, 211, 62, 33);
		panel_1.add(lblNewLabel_1_2_1);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(89, 222, 96, 21);
		panel_1.add(phone);
		
		password = new JPasswordField();
		password.setBounds(89, 88, 96, 21);
		panel_1.add(password);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
		JCheckBox chckbxNewCheckBox = new JCheckBox("是否顯示密碼");
		chckbxNewCheckBox.setBounds(267, 87, 113, 23);
		panel_1.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (chckbxNewCheckBox.isSelected()) {
		            // 不遮蔽密碼
		        	password.setEchoChar((char) 0);
		        } else {
		            // 恢復遮蔽，預設 '*' 可依需求調整
		        	password.setEchoChar('*');
		        }
		    }
		});
	}
}
