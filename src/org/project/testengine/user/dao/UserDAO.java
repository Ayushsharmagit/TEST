package org.project.testengine.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.project.testengine.user.dto.RightDTO;
import org.project.testengine.user.dto.UserDTO;
import org.project.testengine.utils.CommonDAO;
import org.project.testengine.utils.SQLConstants;

public class UserDAO {
	private Logger logger=Logger.getLogger(UserDAO.class);
	
	public UserDTO doLogin(String userid,String password) throws ClassNotFoundException, SQLException {
		logger.debug("Inside Do Login");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserDTO userDTO=null;
		ArrayList <RightDTO> rights=new ArrayList<>();
		//String message="Invalid Userid args And Password";
		
		try {
			logger.debug(" Connection Created");
		con=CommonDAO.getConnection();
		pstmt= con.prepareStatement(SQLConstants.LOGIN_SQL);//userid And 
		pstmt.setString(1, userid);//'1'is for first question Mark,'2'is for second one.
		pstmt.setString(2, password);
		 rs=pstmt.executeQuery();//table record
		while(rs.next()) {
			if(userDTO==null) {
				userDTO =new UserDTO();
				userDTO.setUserid(rs.getString("usereid"));
				userDTO.setPassword(rs.getString("password"));
				userDTO.setRoleName(rs.getString("rolename"));
				userDTO.setRights(rights);
				}
			RightDTO rightDTO =new RightDTO(rs.getString("rightname"),rs.getString("screen"));
			rights.add(rightDTO);
		
		}
		logger.debug("Fetch Data From DB by User "+userid);

		return userDTO;
		
		}
		finally {
			if(rs!=null) {
				
				rs.close();}
					if (pstmt!=null) {
				pstmt.close();
					}
					if (con!=null) {
				con.close();	
				logger.debug("All resources Close");

			}
			
		}
	}
	
	
	
	
/*	public static void main(String[] args)  {
		Connection con=null;
		ResultSet rs=null;
		//step-1 load a driver/class
		PreparedStatement pstmt=null;
			System.out.println("Driver Load");
			
			
			//step-2 Create A connection
			 con =null;
			
			if(con!=null){
				System.out.println("Connection Created");
			}
			//Db Query
			pstmt= con.prepareStatement("select * from users");
			 rs=pstmt.executeQuery();//table record
			while(rs.next()) { 
				System.out.println("\n userid:-"+rs.getString("userid")+
						"\n Password:-"+rs.getString("password"));	
			}
			
				
				
		finally {
				if(rs!=null) {
		
			rs.close();}
				if (pstmt!=null) {
			pstmt.close();
				}
				if (con!=null) {
			con.close();
				
			}
			
			}
		}*/
}