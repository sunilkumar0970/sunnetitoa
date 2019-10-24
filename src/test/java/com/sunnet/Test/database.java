package com.sunnet.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

public class database {
	@Test
	 public void databasequeries() throws Exception {
	 try{  
		 System.out.println("started executig the script");
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.101.248:1525/pdborcl.mdc.anblicks.com","toa_trunk","toa_trunk1");  
		  
		String query = "select *  from toa_sys_menus";	

		//Create Statement Object		
		Statement stmt = con.createStatement();					

		// Execute the SQL Query. Store results in ResultSet		
			ResultSet rs= stmt.executeQuery(query);							
			// While Loop to iterate through all data and print results	
//			System.out.println(rs.getFetchSize());
			while (rs.next()){
		  		String myName = rs.getString(1);								        
		            String myA = rs.getString(2);	
		            String myAg = rs.getString(3);	
		            String myAge = rs.getString(4);	
		            String myAges = rs.getString(5);	
		            System. out.println(myName+"  "+myA+"  "+myAg+"  "+myAge+"  "+myAges);		
		     }	
			//Delete statements of the project module
			stmt.executeQuery("delete from TOA_PROJECTS_LINKS where created_date>=TO_DATE('2019-09-01', 'YYYY-MM-DD')");
			stmt.executeQuery("delete from toa_project_wo_link where created_date>=TO_DATE('2019-09-01', 'YYYY-MM-DD')");
			stmt.executeQuery("delete from toa_app_person where MODULE_ID=7");
			stmt.executeQuery("delete from toa_project_transaction_detail where transaction_date>=TO_DATE('2019-09-01', 'YYYY-MM-DD')");
			stmt.executeQuery("delete from toa_projects where created_date>=TO_DATE('2019-09-01', 'YYYY-MM-DD')");
			stmt.executeQuery("commit");
			//truncate statements of the project module
		/*	stmt.executeQuery("TRUNCATE table TOA_PROJECTS_LINKS");
			stmt.executeQuery("TRUNCATE table toa_project_wo_link");
			stmt.executeQuery("TRUNCATE table toa_app_person where MODULE_ID=7");
			stmt.executeQuery("TRUNCATE table toa_project_transaction_detail");
			stmt.executeQuery("TRUNCATE table toa_projects");
			stmt.executeQuery("commit");*/
		con.close();    
		}
	catch(Exception e){ 
		System.out.println(e);}  
		  
		}  
}

