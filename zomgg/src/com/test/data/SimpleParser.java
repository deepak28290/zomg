package com.test.data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class SimpleParser {
	public static Connection getConnection(String environment){
		Connection connection = null;
		Statement stmt = null;
		String url = "jdbc:mysql://localhost:3306/asapdb";
		try {
			// the sql server driver string
			Class.forName("com.mysql.jdbc.Driver");

		
			// get the sql server database connection
			connection = DriverManager.getConnection(url, "root", "");
			
			stmt=connection.createStatement();
			String sql=new String();
			
			stmt.execute(sql);
			
		}catch(Exception e){
			e.printStackTrace();
		}
			return connection;
		
	}
	
	public static void main(String[] args) throws IOException, ParseException, SQLException{
		 JSONParser parser = new JSONParser();
		 Statement stmt = null;
		JSONArray a = (JSONArray) parser.parse(new FileReader("abb.txt"));
		int i=0;
		  for (Object o : a)
		  {
			  i++;
		    JSONObject person = (JSONObject) o;
		    JSONObject jsonObject =  (JSONObject) o;
		    JSONObject ab=(JSONObject) person.get("result");
		 System.out.println(  ab.get("cuisines"));
		    System.out.println(ab.get("has_delivery"));
		    System.out.println(ab.get("address"));
		    System.out.println(ab.get("name"));
		    Connection connection = null;
			
			String url = "jdbc:mysql://localhost:3306/asapdb";
			try {
				// the sql server driver string
				Class.forName("com.mysql.jdbc.Driver");

			
				// get the sql server database connection
				connection = DriverManager.getConnection(url, "root", "");
				
				stmt=connection.createStatement();
				String sql=new String();
				sql="INSERT INTO business_table values ("+i+",'"+ab.get("cuisines")+"','Bangalore','"+ab.get("name").toString().replace("'","")+"','"+ab.get("has_delivery")+"','"+ab.get("address")+"','Indiranagar','')";
				System.out.println(sql);
				stmt.execute(sql);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		    
		 		  }
		  stmt.close();
	}
}
