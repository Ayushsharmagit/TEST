package org.project.testengine.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.project.testengine.question.dto.QuestionDTO;
import org.project.testengine.utils.CommonDAO;
import org.project.testengine.utils.SQLConstants;

public class QuestionDAO {
	public ArrayList<QuestionDTO> getQuestions() throws SQLException, ClassNotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QuestionDTO> questions = new ArrayList<>();
		try {
		    con = CommonDAO.getConnection();
		    pstmt = con.prepareStatement(SQLConstants.FETCH_QUESTION_SQL);
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()) {
			QuestionDTO questionDTO = new QuestionDTO();
			questionDTO.setId(rs.getInt("id"));
			questionDTO.setName(rs.getString("name"));
			String options[] = new String[4];
			options[0] = rs.getString("optiona");//should be same as table componet in DB.
			options[1] = rs.getString("optionb");
			options[2] = rs.getString("optionc");
			options[3] = rs.getString("optiond");
			questionDTO.setOption(options);
			questionDTO.setRightAnswer(rs.getString("rightanswer"));
			questionDTO.setScore(rs.getInt("score"));
			questions.add(questionDTO);
		    }
		}
		finally {
		    if(rs!=null) {
			rs.close();
		    }
		    if(pstmt!=null) {
			pstmt.close();
		    }
		    if(con!=null) {
			con.close();
		    }
		}
		return questions;
	    }
	
	public boolean isUpload(ArrayList<QuestionDTO>questions) throws ClassNotFoundException, SQLException {
	boolean	isUpload=false;
	Connection con =null;
	PreparedStatement pstmt=null;
	try {
	if(questions!=null&&questions.size()>0) {
		con=CommonDAO.getConnection();
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(SQLConstants.QUESTION_UPLOAD_SQL);
		for(QuestionDTO question:questions) {
			pstmt.setInt(1, question.getId());
			pstmt.setString(2, question.getName());
			int position=3;
			for(String option:question.getOption()) {
				pstmt.setString(position, option);
				position++;
			}
			pstmt.setString(7, question.getRightAnswer());
			pstmt.setInt(8, question.getScore());
			pstmt.addBatch();
		}
	  int results[] = pstmt.executeBatch();//exectue batch into the result array
	                                       //in the form of 0 & 1
		boolean fail = false;
		for(int r : results) {
			if(r<1) {
				fail = true;
				break;
			}
		}
		if(fail) {
			con.rollback();
		}
		else {
			con.commit();//set setAutoCommit=true
			return true;
		}
	}
	}
	finally {
		if(pstmt!=null) {
			pstmt.close();
		}
		if(con!=null) {
			con.close();
		}
	}
		return isUpload;
}
}