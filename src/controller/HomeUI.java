package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controller.customer.LoginUI;
import controller.employee.EmployeeLoginUI;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// glass pane 上的時鐘標籤
    private JLabel clockLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeUI frame = new HomeUI();
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
	public HomeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 177, 153));
		panel.setBounds(0, 0, 434, 71);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("主畫面");
		lblNewLabel.setBounds(164, 10, 189, 35);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(158, 227, 235));
		panel_1.setBounds(0, 70, 434, 191);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
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
		
		JButton btnNewButton = new JButton("員工登錄");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeLoginUI employeeWindow = new EmployeeLoginUI(); // 創建新視窗
		        employeeWindow.setVisible(true); // 顯示新視窗
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnNewButton.setBounds(142, 31, 134, 35);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("客戶登錄");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI customerWindow = new LoginUI(); // 創建新視窗
				customerWindow.setVisible(true); // 顯示新視窗
				
			}
		});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnNewButton_1.setBounds(142, 90, 134, 35);
		panel_1.add(btnNewButton_1);
	}

}
