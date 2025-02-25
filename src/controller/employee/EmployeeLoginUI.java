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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JPasswordField;

public class EmployeeLoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField number;
	
	// glass pane 上的時鐘標籤
    private JLabel clockLabel;
	
	private static EmployeeServiceImpl employeeserviceimpl = new EmployeeServiceImpl();
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLoginUI frame = new EmployeeLoginUI();
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
	public EmployeeLoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(87, 213, 190));
		panel.setBounds(0, 0, 621, 78);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("優秀員工登錄系統");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		lblNewLabel.setBounds(160, 10, 272, 58);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		
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
        clockLabel.setBounds(405, 5, 100, 25);
        glass.add(clockLabel);
        
        Timer timer = new Timer(1000, new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(sdf.format(new Date()));
            }
        });
        timer.start();
		panel_1.setBackground(new Color(84, 207, 248));
		panel_1.setBounds(0, 77, 501, 257);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("員工編號:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(54, 27, 102, 23);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼:");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(100, 112, 102, 23);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("登錄");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Number=number.getText();
				String Password=password.getText();
				
				
				  // 呼叫員工登入的方法，該方法回傳符合條件的 Employee 物件
		        Employee employee = employeeserviceimpl.Login(Number, Password);
		        if (employee != null) {
		            // 登錄成功，進入後續流程
		            JOptionPane.showMessageDialog(null, "登錄成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		            // 例如：進入主介面
		             OrderManager mainUI = new OrderManager();
		             mainUI.setVisible(true);
		             dispose();
		        } else {
		            // 登錄失敗，查無此員工資料
		            JOptionPane.showMessageDialog(null, "查無此員工資料", "錯誤", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnNewButton.setBounds(328, 164, 87, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Tool.HomeUI();
				dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnNewButton_1.setBounds(126, 204, 87, 23);
		panel_1.add(btnNewButton_1);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBounds(181, 27, 96, 21);
		panel_1.add(number);
		
		JButton btnNewButton_2 = new JButton("註冊");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				   EmployeeAddUI employeeWindow = new EmployeeAddUI(); // 創建新視窗
			        employeeWindow.setVisible(true); // 顯示新視窗
			}
		});
		btnNewButton_2.setBounds(330, 207, 85, 23);
		panel_1.add(btnNewButton_2);
		
		password = new JPasswordField();
		password.setBounds(181, 112, 96, 23);
		panel_1.add(password);
		
		final JCheckBox showPassword = new JCheckBox("顯示密碼");
		showPassword.setBounds(291, 152, 122, 45);
		showPassword.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (showPassword.isSelected()) {
		            password.setEchoChar((char) 0); // 顯示密碼
		        } else {
		            password.setEchoChar('*'); // 遮蔽密碼
		        }
		    }
		});
		contentPane.add(showPassword);
	}
}
