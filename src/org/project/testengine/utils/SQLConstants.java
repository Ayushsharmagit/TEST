package org.project.testengine.utils;

public interface SQLConstants {
	
	//String LOGIN_SQL="select userid,password from users where userid=? and password=?";//
	
	//"select * from users where userid = ' " + userid + " ' and password = ' " + password + " ' ";


	String LOGIN_SQL = "select user_mst.usereid, user_mst.password, role_mst.name as rolename," + 
			"right_mst.name as rightname, right_mst.screen from user_mst, role_mst, user_role" + 
			"_mapping, right_mst, role_right_mapping where user_mst.uid=user_role_mapping.uid" + 
			" and role_mst.roleid=user_role_mapping.roleid and role_mst.roleid=role_right_map" + 
			"ping.role and role_mst.roleid=role_right_mapping.role and right_mst.rightid=role" + 
			"_right_mapping.rightid and user_mst.usereid=? and user_mst.password=?";
	
	String QUESTION_UPLOAD_SQL = "insert into questions (id,name,optiona,optionb,optionc,optiond,rightanswer,score) values(?,?,?,?,?,?,?,?)";
	String FETCH_QUESTION_SQL="select id ,name,optiona,optionb,optionc,optiond,rightanswer,score from questions";
	//id, name and all should be same as table component in dataBase.
}
	