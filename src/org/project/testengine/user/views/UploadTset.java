package org.project.testengine.user.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.project.testengine.question.dao.QuestionDAO;
import org.project.testengine.question.dto.QuestionDTO;
import org.project.testengine.utils.ExcelReader;

public class UploadTset extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadTset frame = new UploadTset();
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
	private void asktoClose() {
	int choice=JOptionPane.showConfirmDialog(this, 
			"Do You Wanna Leave This Window","TestEngine",JOptionPane.YES_NO_OPTION);		
	if(choice==JOptionPane.YES_OPTION) {
		this.setVisible(false);
		this.dispose();
		
	}
	}
	
	String path="";
	JLabel filenamelbl = new JLabel("");
	JButton btnUpload = new JButton("Upload");
	
	private void browse() {
		JFileChooser open =new JFileChooser();
		open.showOpenDialog(this);
		File file=open.getSelectedFile();
		path=file.getAbsolutePath();
		filenamelbl.setText(path);
		if(path.trim().length()>0) {
			btnUpload.setEnabled(true);
			
		}
		
		
	}
	
	
	private void upload() {
		try {
			ArrayList<QuestionDTO> questions = ExcelReader.readExcel(path);
			QuestionDAO questionDAO = new QuestionDAO();
			try {
				boolean isUpload = questionDAO.isUpload(questions);
				String message = isUpload?"Uploaded ":"Not Uploaded";
				JOptionPane.showMessageDialog(this,message , "Test Engine", JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public UploadTset() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				asktoClose();
			}
		});
		setTitle("Upload Test");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browse();
			}
		});
		btnBrowse.setBounds(30, 63, 117, 29);
		contentPane.add(btnBrowse);
		
		
		filenamelbl.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		filenamelbl.setBounds(10, 116, 414, 29);
		contentPane.add(filenamelbl);
		
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upload();
			}
		});
		btnUpload.setEnabled(false);
		btnUpload.setBounds(197, 63, 117, 29);
		contentPane.add(btnUpload);
	}

}
