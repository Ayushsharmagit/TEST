package org.project.testengine.user.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.project.testengine.user.dao.UserDAO;
import org.project.testengine.user.dto.UserDTO;
import org.project.testengine.utils.CommonUtils;

public class LoginView extends JFrame {
	private Logger logger=Logger.getLogger(LoginView.class);

	private JPanel contentPane;
	private JTextField UserTxt;
	private JPasswordField passwordField_1;

	
	public static void main(String[] args) {
				
					LoginView frame = new LoginView();
					frame.setVisible(true);
			
	}

	private void checkLogin() {
		String userid=UserTxt.getText();
		String password=new String(passwordField_1.getPassword());
		UserDAO userDAO=new UserDAO();
		try {
			logger.debug("Check login called:- "+userid);
			UserDTO userDTO= userDAO.doLogin(userid, password);
			logger.debug("After Checking Logging "+userDTO);
		if(userDTO!=null) {
				DashBoard dashboard=new DashBoard(userDTO);//passing obj"userDTO".
				dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dashboard.setVisible(true);
			}
			else {
			JOptionPane.showMessageDialog(this, "*Invalid User id And password*", "Test Engine", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(CommonUtils.getStringofStackTrace(e));
			//e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Some Db Issue", "Test Engine", JOptionPane.WARNING_MESSAGE);
			
		} 
	}
	
	public LoginView() {
		logger.debug("LoginView Design Starts");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(160, 31, 96, 34);
		contentPane.add(lblLogin);
		
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setBounds(66, 98, 46, 14);
		contentPane.add(lblUserid);
		
		UserTxt = new JTextField();
		UserTxt.setBounds(146, 95, 254, 20);
		contentPane.add(UserTxt);
		UserTxt.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBounds(66, 144, 70, 28);
		contentPane.add(lblPassword);
		
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(146, 152, 264, 20);
		contentPane.add(passwordField_1);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkLogin();
			}
		});
		btnLogin.setBounds(47, 202, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(206, 202, 89, 23);
		contentPane.add(btnReset);
		logger.debug("Login View Desgin Ends");
	}
}
