package org.project.testengine.user.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.project.testengine.user.dto.RightDTO;
import org.project.testengine.user.dto.UserDTO;
import org.project.testengine.utils.CommonConstants;

public class DashBoard extends JFrame {

	private JPanel contentPane;

	
	@SuppressWarnings("deprecation")
	private void loadScreen(String fullClassName){
		Object object;
		try {
			object = Class.forName(fullClassName).newInstance();
			Method method;
			
			method = object.getClass().getMethod("setVisible", boolean.class);
			
				method.invoke(object, true);
			} 
		catch (IllegalArgumentException e) {
				e.printStackTrace();
		} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private void createMenuBar(ArrayList<RightDTO> rights,String rollName) {
		JMenuBar menubar=new JMenuBar();
		this.setJMenuBar(menubar);
		JMenu file=new JMenu("File");
		for(RightDTO right:rights) {
			JMenuItem menuItem=new JMenuItem(right.getRightName());
			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String screenName=right.getScreenName();
					String fullClassName=CommonConstants.BASE_PATH+screenName;
					loadScreen(fullClassName);
					
				}});
			file.add(menuItem);
		}
		
		menubar.add(file);
		
	}
	
	public DashBoard(UserDTO userDTO) {
		setTitle("DashBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		createMenuBar(userDTO.getRights(),userDTO.getRoleName());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Msgibl = new JLabel("WELCOME *"+userDTO.getUserid()
		+"*"+"  Role |"+userDTO.getRoleName()+"|");
		Msgibl.setFont(new Font("Tahoma",Font.PLAIN,18));
		Msgibl.setHorizontalAlignment(SwingConstants.CENTER);
		Msgibl.setBounds(10, 11, 414, 49);
		contentPane.add(Msgibl);
		

	}
}
