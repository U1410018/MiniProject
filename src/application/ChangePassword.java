package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.MessageFormat;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChangePassword extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtUsername;
	private JPasswordField passwordField;
	private JLabel txtOr;

	public static ChangePassword frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ChangePassword() {
		
		setResizable(false);
		setTitle("Change Password");
		
		setBounds(100, 100, 334, 473);
		setLocationRelativeTo(null);
		contentPane = new JPanel();

		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
//		Image img = new ImageIcon(this.getClass().getResource("/lbllogin.jpg")).getImage();
		contentPane.setLayout(null);
//		Image img1 = new ImageIcon(this.getClass().getResource("/lblpass.jpg")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("/banking-image2.jpg")).getImage();

		Panel panel = new Panel();
		panel.setBounds(0, 0, 334, 455);
		panel.setBackground(Color.WHITE);

		passwordField = new JPasswordField("");
		passwordField.setBorder(null);
//		passwordField.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				if (arg0.getKeyChar() == '\n') {
//					try {
//						getClient().getOutputStream().writeUTF(
//								MessageFormat.format("LOGIN|{0}|{1}", txtUsername.getText(), passwordField.getText()));
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//					}
//				}
//
//			}
//		});
		
		passwordField.addFocusListener(new FocusAdapter() {
			
		});
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.addMouseListener(new MouseAdapter() {
			

		});
		passwordField.setEchoChar('*');
		passwordField.setToolTipText("");
		passwordField.setForeground(new Color(26,177,136));
		passwordField.setBounds(56, 198, 265, 37);

		JLabel lblSignIn = new JLabel("Change Password");
		lblSignIn.setBounds(78, 94, 243, 49);
		lblSignIn.setForeground(new Color(26,177,136));
		lblSignIn.setFont(new Font("Dialog", Font.PLAIN, 30));

		JLabel label_2 = new JLabel("");
		label_2.setBounds(145, 121, 0, 0);
		panel.setLayout(null);

		txtUsername = new JPasswordField("");
		txtUsername.setBorder(null);
		txtUsername.setEchoChar('*');
		txtUsername.setBackground(new Color(255, 255, 255));
//		txtUsername.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				if (arg0.getKeyChar() == '\n') {
//					try {
//						getClient().getOutputStream().writeUTF(
//								MessageFormat.format("LOGIN|{0}|{1}", txtUsername.getText(), passwordField.getText()));
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//					}
//				}
//
//			}
//		});
		txtUsername.addMouseListener(new MouseAdapter() {
			

		});
		txtUsername.addFocusListener(new FocusAdapter() {
			
		});
		Image img3 = new ImageIcon(this.getClass().getResource("/or1.png")).getImage();
		txtOr = new JLabel("or");
		txtOr.setForeground(new Color(255,255,255));
		txtOr.setBounds(155, 331, 86, 20);
		panel.add(txtOr);
	
		
		JLabel lblOr = new JLabel("");
		lblOr.setIcon(new ImageIcon(img3));
		lblOr.setBounds(132, 312, 60, 60);
		panel.add(lblOr);
		
		
		JLabel lblLogin = new JLabel("Submit");
		lblLogin.setBounds(11, 322, 151, 40);
		lblLogin.setBackground(new Color(26,177,136));
		lblLogin.setOpaque(true);
		lblLogin.setForeground(new Color(255,255,255));
		lblLogin.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblLogin.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				try {
//					getClient().getOutputStream().writeUTF(
//							MessageFormat.format("LOGIN|{0}|{1}", txtUsername.getText(), passwordField.getText()));
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//			}

		});
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(11, 322, 151, 40);
		panel.add(lblLogin);
		

		JLabel lblCancel = new JLabel("Cancel");
		lblCancel.setBounds(170, 322, 151, 40);
		lblCancel.setBackground(new Color(26,177,136));
		lblCancel.setOpaque(true);
		lblCancel.setForeground(new Color(255,255,255));
		lblCancel.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPage.frame = new MainPage();
				MainPage.frame.setEnabled(true);
				frame.dispose();
			}

//			public void mouseEntered(MouseEvent arg0) {
//				lblCancel.setBounds(172, 322, 151, 40);
//			}
//
//			public void mouseExited(MouseEvent e) {
//				lblCancel.setBounds(170, 322, 151, 40);
//			}
		});

		
		lblCancel.setBounds(170, 322, 151, 40);
		panel.add(lblCancel);
		
		
		txtUsername.setForeground(new Color(26,177,136));
		txtUsername.setBounds(56, 154, 265, 37);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setColumns(10);
		panel.add(txtUsername);

		JLabel lblQ = new JLabel("OLD");
		lblQ.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ.setBackground(new Color(255,255,255));
		lblQ.setOpaque(true);
		lblQ.setForeground(new Color(26,177,136));
		lblQ.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblQ.setBounds(11, 154, 46, 37);
		panel.add(lblQ);
		panel.add(label_2);
		
		panel.add(passwordField);
		panel.add(lblSignIn);
		contentPane.add(panel);

		JLabel lblQ_1 = new JLabel("NEW");
		lblQ_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ_1.setBackground(new Color(255,255,255));
		lblQ_1.setOpaque(true);
		lblQ_1.setForeground(new Color(26,177,136));
		lblQ_1.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblQ_1.setBounds(11, 198, 46, 37);
		panel.add(lblQ_1);
		Image img6 = new ImageIcon(this.getClass().getResource("/ibs-logo-white2.png")).getImage();


		JLabel lblQ_2 = new JLabel("");
		lblQ_2.setBounds(0, -1, 334, 445);
		panel.add(lblQ_2);
		lblQ_2.setIcon(new ImageIcon(img2));
		
		

		addWindowListener(new WindowAdapter() {
			@Override
//			public void windowOpened(WindowEvent arg0) {
//				try {
//					setClient(new Client());
//					getClient().setConnection();
//					checkServerStatus(label_1);
//				} catch (Exception ex) {
//					JOptionPane.showMessageDialog(null, ex.getMessage());
//				}
//			}

			public void windowActivated(WindowEvent arg0) {

			}

//			public void windowClosing(WindowEvent arg0) {
//				SendMessageToServer("EXIT");
//				try {
//					client.getInputStream().close();
//					client.getOutputStream().close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
		});
	}

	public Thread readerThread;

	public void ReadMessage() {

	}


	public static Thread serverStatus;

//	public static void checkServerStatus(JLabel lb) {
//		serverStatus = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (!Client.getMessage().equals("EXIT")) {
//					
//					if (getClient().isServerStatus()) {
//						lb.setForeground(Color.green);
//						lb.setText("ON");
//						isServerOK = true;
//					} else {
//						isServerOK = false;
//						lb.setForeground(Color.red);
//						lb.setText("OFF");
//					}
//					try {
//						Thread.sleep(1000);
//					} catch (Exception ex) {
//					}
//				}
//			}
//		});
//		serverStatus.start();
//	}

	public static boolean isServerOK = false;

	public String Came = "";

//	public void SendMessageToServer(String mess) {
//		
//		try {
//			getClient().getOutputStream().writeUTF("Client " + getClient().getID() + ": " + mess);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

//	public static Client getClient() {
//		return client;
//	}

//	public static void setClient(Client client) {
//		identification.client = client;
//	}

	public static boolean isVerified = false;
}
