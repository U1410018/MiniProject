package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class MainPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static MainPage frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
					Config.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainPage() {
		setTitle("Inha Banking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();

		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 900, 600);
		panel.setLayout(null);
		contentPane.add(panel);
		
		
		JLabel lblUser = new JLabel("");
		lblUser.setBackground(new Color(255, 255, 255));
		Image img = new ImageIcon(this.getClass().getResource("/user150.png")).getImage();
		lblUser.setIcon(new ImageIcon(img));
		lblUser.setBackground(new Color(210, 210, 210));
		lblUser.setOpaque(true);
		lblUser.setBounds(20, 20, 150, 150);
		panel.add(lblUser);
		
		JLabel username = new JLabel("Bunyodbek", SwingConstants.CENTER);
		username.setForeground(new Color(1,1,1));
		username.setBackground(new Color(210, 210, 210));
		username.setOpaque(true);
		username.setBounds(20, 180, 150, 23);
		username.setFont(new Font("Rockwell", Font.PLAIN, 20));
		panel.add(username);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBackground(new Color(255, 255, 255));
		Image img1 = new ImageIcon(this.getClass().getResource("/ibs-logo-black2.jpg")).getImage();
		lblLogo.setIcon(new ImageIcon(img1));
		lblLogo.setBounds(610, 18, 260, 130);
		panel.add(lblLogo);
		
		
		JLabel txtmotto = new JLabel("Inha Banking System");
		txtmotto.setForeground(new Color(26,136,177));
		txtmotto.setBounds(610, 149, 260, 32);
		txtmotto.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(txtmotto);
		
		JLabel change = new JLabel("Change password", SwingConstants.CENTER);
		change.setForeground(new Color(26,136,177));
		change.setBackground(new Color(210, 210, 210));
		change.setBorder(BorderFactory.createLineBorder(new Color(26,136,177)));
		change.setOpaque(true);
		change.setBounds(20, 490, 150, 25);
		change.setFont(new Font("Rockwell", Font.PLAIN, 17));
		
		
		change.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePassword.frame = new ChangePassword();
				ChangePassword.frame.setVisible(true);
//				frame.dispose();
			}
		});
		panel.add(change);
		
		try {
			username.setText(Registration.CurrentUser.getUser_name());
		}catch(Exception ex) {
			System.out.println("error");
		}
		JLabel delete = new JLabel("Delete Account", SwingConstants.CENTER);
		delete.setForeground(new Color(255,35,98));
		delete.setBackground(new Color(210, 210, 210));
		delete.setOpaque(true);
		delete.setBounds(20, 526, 150, 23);
		delete.setBorder(BorderFactory.createLineBorder(new Color(255,35,98)));
		delete.setFont(new Font("Rockwell", Font.PLAIN, 17));
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to delete your account?", "Warning", dialogButton);
				if(dialogResult == 0) {
				  System.out.println("Yes option");
				  User u = Registration.CurrentUser;
				  if(u.deleteCurrentUser()) {
					  Login.frame = new Login();
					  Login.frame.setVisible(true);
					  frame.dispose();
				  }
				} else {
				  System.out.println("No Option");
				} 
			}
		});
		panel.add(delete);
		
		Panel left = new Panel();
		left.setBackground(new Color(210, 210, 210));
		left.setBounds(0, 0, 190, 572);
		panel.add(left);
		
		
		JLabel usersList = new JLabel("Information List", SwingConstants.CENTER);
		usersList.setBackground(new Color(210, 210, 210));
		usersList.setOpaque(true);
		usersList.setBounds(470, 230, 400, 24);
		usersList.setFont(new Font("Rockwell", Font.PLAIN, 18));
		panel.add(usersList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		DefaultListModel<String> model = new DefaultListModel<>();
		JList<String> list = new JList<>(model);
		list.setBorder(new EmptyBorder(3, 7, 5, 5));
		list.setFont(new Font("Rockwell", Font.PLAIN, 16));
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
		scrollPane.setBounds(470, 254, 400, 300);
		panel.add(scrollPane);
		scrollPane.setViewportView(list);
		
		
		JLabel usersList_1 = new JLabel("Costumers List", SwingConstants.CENTER);
		usersList_1.setBackground(new Color(229, 186, 163));
		usersList_1.setOpaque(true);
		usersList_1.setBounds(220, 230, 220, 24);
		usersList_1.setFont(new Font("Rockwell", Font.PLAIN, 18));
		panel.add(usersList_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		DefaultListModel<String> model_1 = new DefaultListModel<>();
		JList<String> list_1 = new JList<>(model_1);
		list_1.setBorder(new EmptyBorder(3, 7, 5, 5));
		list_1.setFont(new Font("Rockwell", Font.PLAIN, 16));
		for ( int i = 0; i < 10; i++ ){
			model_1.addElement("Bunyodbek Ibrokhimov");
			model.addElement("Ibrokhimov -------- Goes to golden ball \n Hello world !");
		}
		scrollPane_1.setBorder(BorderFactory.createLineBorder(new Color(229, 186, 163)));
		scrollPane_1.setBounds(220, 254, 220, 300);
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(list_1);
		
		JLabel lblBalance = new JLabel("Balance:", SwingConstants.LEFT);
		lblBalance.setBackground(new Color(255, 255, 255));
		lblBalance.setOpaque(true);
		lblBalance.setBounds(220, 20, 80, 22);
		lblBalance.setFont(new Font("Rockwell", Font.PLAIN, 18));
		panel.add(lblBalance);
		
		JLabel balance = new JLabel("183200", SwingConstants.LEFT);
		balance.setBackground(new Color(255, 255, 255));
		balance.setOpaque(true);
		balance.setBounds(300, 20, 140, 22);
		balance.setFont(new Font("Rockwell", Font.PLAIN, 18));
		panel.add(balance);
		
		JLabel deposit = new JLabel("Deposit", SwingConstants.CENTER);
		deposit.setForeground(new Color(26,136,177));
		deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				float amount = Float.parseFloat(JOptionPane.showInputDialog(null,
                        "How much do you want to deposit?", null));
			
//				System.out.println(amount);
			}
		});
		
		deposit.setBackground(new Color(255, 255, 255));
		deposit.setBorder(BorderFactory.createLineBorder(new Color(26,136,177)));
		deposit.setOpaque(true);
		deposit.setBounds(220, 90, 80, 25);
		deposit.setFont(new Font("Rockwell", Font.PLAIN, 18));
		panel.add(deposit);
		
		JLabel withdraw = new JLabel("Withdraw", SwingConstants.CENTER);
		withdraw.setForeground(new Color(255,35,98));
		withdraw.setBackground(new Color(255, 255, 255));
		withdraw.setBorder(BorderFactory.createLineBorder(new Color(255,35,98)));
		withdraw.setOpaque(true);
		withdraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				float amount = Float.parseFloat(JOptionPane.showInputDialog(null,
                        "How much do you want to withdraw?", null));
				System.out.println(amount);
			}
		});
		withdraw.setBounds(310, 90, 100, 25);
		withdraw.setFont(new Font("Rockwell", Font.PLAIN, 18));
		panel.add(withdraw);
		
		
		JLabel transfer = new JLabel("Transfer", SwingConstants.CENTER);
		transfer.setForeground(new Color(26,136,177));
		transfer.setBackground(new Color(255, 255, 255));
		transfer.setBorder(BorderFactory.createLineBorder(new Color(26,136,177)));
		transfer.setOpaque(true);
		transfer.setBounds(420, 90, 80, 25);
		transfer.setFont(new Font("Rockwell", Font.PLAIN, 18));
		panel.add(transfer);
		
		
//		JLabel back = new JLabel("");
//		back.setBackground(new Color(255, 255, 255));
//		Image img4 = new ImageIcon(this.getClass().getResource("/wall2.jpg")).getImage();
//		back.setIcon(new ImageIcon(img4));
//		back.setBounds(0, 0, 900, 572);
//		panel.add(back);
		
	}
}
