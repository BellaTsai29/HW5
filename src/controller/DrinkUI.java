package controller;
import java.awt.EventQueue;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DrinkDaoImpl;
import model.Drink;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
//import java.io.IOException;
import java.io.*;
import javax.swing.JCheckBox;

public class DrinkUI extends JFrame {

	private DrinkDaoImpl drinkdaoimpl = new DrinkDaoImpl();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField black;
	private JTextField green;
	private JTextField milk;
	
	// glass pane 上的時鐘標籤
    private JLabel clockLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrinkUI frame = new DrinkUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
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
	 * Create the frame.
	 */
	public DrinkUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(142, 227, 196));
		panel.setBounds(0, 0, 455, 67);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("茶癮研究所");
		lblNewLabel.setBounds(145, 22, 185, 35);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("姓名:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 95, 61, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("紅茶:");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 135, 61, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("綠茶:");
		lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 176, 61, 28);
		contentPane.add(lblNewLabel_1_2);
		
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
		
		JLabel lblNewLabel_1_3 = new JLabel("奶茶");
		lblNewLabel_1_3.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(10, 220, 61, 33);
		contentPane.add(lblNewLabel_1_3);
		
		name = new JTextField();
		name.setBounds(74, 105, 96, 21);
		contentPane.add(name);
		name.setColumns(10);
		((javax.swing.text.AbstractDocument) name.getDocument()).setDocumentFilter(new NumericDocumentFilter());


		black = new JTextField();
		black.setColumns(10);
		black.setBounds(74, 145, 96, 21);
		contentPane.add(black);
		((javax.swing.text.AbstractDocument) black.getDocument()).setDocumentFilter(new NumericDocumentFilter());


		green = new JTextField();
		green.setColumns(10);
		green.setBounds(74, 185, 96, 21);
		contentPane.add(green);
		((javax.swing.text.AbstractDocument) green.getDocument()).setDocumentFilter(new NumericDocumentFilter());

		milk = new JTextField();
		milk.setColumns(10);
		milk.setBounds(74, 231, 96, 21);
		contentPane.add(milk);
		((javax.swing.text.AbstractDocument) milk.getDocument()).setDocumentFilter(new NumericDocumentFilter());

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(228, 77, 198, 90);
		contentPane.add(scrollPane);
		
		JTextArea total = new JTextArea();
		scrollPane.setViewportView(total);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("我是會員");
		chckbxNewCheckBox.setFont(new Font("新細明體", Font.PLAIN, 15));
		chckbxNewCheckBox.setBounds(10, 271, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("送出");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String Name;
				String Black;
				String Milk;
				String Green;
				
				if(name.getText().trim().isEmpty()) Name = "0";
				else Name=name.getText();
				if(black.getText().trim().isEmpty()) Black = "0";
				else Black=black.getText();
				if(milk.getText().trim().isEmpty()) Milk = "0";
				else Milk=milk.getText();
				if(green.getText().trim().isEmpty()) Green = "0";
				else Green=green.getText();

				
				Drink D=new Drink (Integer.parseInt(Milk),Integer.parseInt(Black),Integer.parseInt(Green),Name);
				
				Integer sum=D.setsum();
				
				if(chckbxNewCheckBox.isSelected()) sum = (int) (sum * 0.9);
				
				total.setText( "姓名: " + Name + "\n" +
			            "紅茶: " + Black + "\n" +
			            "綠茶: " + Green + "\n" +
			            "奶茶: " + Milk + "\n" +
			            "總金額: " + sum);
				
				drinkdaoimpl.add(D);
				
				JOptionPane.showMessageDialog(null, "新增成功", "提示", JOptionPane.INFORMATION_MESSAGE);

				
			}
		});
		btnNewButton.setBounds(341, 184, 85, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("列印");
		JFrame frame = new JFrame("列印 JTextArea 並存成 CSV");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				String text = total.getText();
				if(text.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "JTextArea 沒有內容,無法存成CSV!");
					return;
				}
				 // 選擇存檔位置
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("選擇存檔位置");
                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();

                    // 確保副檔名為 .csv
                    if (!filePath.toLowerCase().endsWith(".csv")) {
                        filePath += ".csv";
                      
                    }
 
                    try (FileOutputStream fos = new FileOutputStream(filePath);
                            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                            BufferedWriter bw = new BufferedWriter(osw);
                            PrintWriter writer = new PrintWriter(bw)) {

                           writer.write("\uFEFF"); // **加入 BOM 防止 Excel 亂碼**
                           writer.println(text);

                           JOptionPane.showMessageDialog(frame, "CSV 檔案已儲存：" + filePath);
                       } catch (IOException ex) {
                           JOptionPane.showMessageDialog(frame, "存檔失敗：" + ex.getMessage());
                    }
                }
            }
        });
    

        btnNewButton_1.setBounds(341, 230, 85, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("清除");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				black.setText("");
				milk.setText("");
				green.setText("");
				name.setText("");
				total.setText("");
				
			}
		});
		btnNewButton_1_1.setBounds(242, 184, 85, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("離開");
		btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_1_1.setBounds(242, 230, 85, 23);
		contentPane.add(btnNewButton_1_1_1);
		
		
		
		
	}
}
