
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HelloJob implements Job {
	
	Properties prop = new Properties();
	InputStream input = null;
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		Connection conn = null;
		Statement stmt = null;

		try{
			//read the properties file
			Properties prop = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
			InputStream in = classLoader.getResourceAsStream("config.properties"); 
			prop.load(in);
			
			//load db credentials & URLs
			final String DB_URL = prop.getProperty("DB_URL");
			final String USER = prop.getProperty("userName");
			final String PASS =prop.getProperty("password");
			final String alURL =prop.getProperty("allocationURL");
			final String enURL =prop.getProperty("engagementsURL");
			final String JDBC_DRIVER =prop.getProperty("JDBC_DRIVER");

			//doc builder to read xml input
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc;
			
			PreparedStatement pstmt;

			//creating DB & tables

			//Register JDBC driver
			Class.forName(JDBC_DRIVER);

			//Open a connection
			System.out.println("Connecting to DATABASE...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			//creating db & tables
			
			stmt = conn.createStatement();

			String sql = "DROP DATABASE IF EXISTS Engagements";
			stmt.executeUpdate(sql);

			sql = "CREATE DATABASE Engagements";
			stmt.executeUpdate(sql);

			sql = "USE Engagements";
			stmt.executeUpdate(sql);

			sql ="CREATE TABLE Allocation_Types "+"(id VARCHAR(50), "+" allocation_type VARCHAR(255),"+"PRIMARY KEY ( id ))";
			stmt.executeUpdate(sql);
			System.out.println("Allocation_Types table created successfully");

			sql ="CREATE TABLE Engagement_details "+ " (person VARCHAR(100)," +
					"allocation_id VARCHAR(50), allocation_type VARCHAR(100),engagement_id VARCHAR(50)," +
					"start_date VARCHAR(25),end_date VARCHAR(25),status VARCHAR(25))";
			stmt.executeUpdate(sql);
			
			System.out.println("hi 0");
			
			System.out.println("Engagement_details table created successfully");
			
			System.out.println("hi 1");
			
			sql ="CREATE TABLE Client_details "+ "(ClientName VARCHAR(100), ProjectName VARCHAR(150), engagement_id VARCHAR(50), start_date VARCHAR(25), end_date VARCHAR(25))";
			System.out.println("hi 2");
			stmt.executeUpdate(sql);
			System.out.println("hi 3");
			System.out.println("Client_details table created successfully");

			//Reading xml response & storing allocation data

			doc = db.parse(new URL(alURL).openStream());
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Allocation_type");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String id = eElement.getElementsByTagName("id").item(0).getTextContent();
					String type = eElement.getElementsByTagName("allocationType").item(0).getTextContent();

					pstmt = conn.prepareStatement(
							"INSERT INTO Allocation_Types "+ "VALUES (?,?)");

					pstmt.setString(1,id);
					pstmt.setString(2,type);

					pstmt.executeUpdate();
				}

			}
			System.out.println("----------allocation data inserted------------------");

			//Reading xml response & storing engagement details

			doc = db.parse(new URL(enURL).openStream());
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("Engagement");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String All_id = eElement.getElementsByTagName("allocation_id").item(0).getTextContent();
					String All_type = eElement.getElementsByTagName("allocation_type").item(0).getTextContent();
					String person = eElement.getElementsByTagName("person_email").item(0).getTextContent();
					
					person = person.toLowerCase();
					
					String En_id = eElement.getElementsByTagName("id").item(0).getTextContent();

					  String Start,start,end,End;
	                    if(En_id.contains("EL")){
	                        start = eElement.getElementsByTagName("start_date").item(0).getTextContent();
	                        end = eElement.getElementsByTagName("end_date").item(0).getTextContent();
	                    
	                    }else{
	                        start = eElement.getElementsByTagName("allocation_start_date").item(0).getTextContent();
	                        end = eElement.getElementsByTagName("allocation_end_date").item(0).getTextContent();
	                    }
	                    
	                    if(start.length()== 0) Start = "";
	                    else Start = start.substring(0,10);
	                    
	                    if(end.length()== 0) End = "";
	                    else End= end.substring(0,10);   

					String Status = eElement.getElementsByTagName("status").item(0).getTextContent();

					
					if(!person.equals("") && !person.equals("null")){
						if(person.substring(0, 1).equals(" ")){
							person = person.substring(1);
							
						}
						
						if(person.endsWith(" ")){
							person = person.substring(0, person.length()-1);
						}
						
						pstmt = conn.prepareStatement(
								"SELECT allocation_type FROM Allocation_Types WHERE id = ?");

						pstmt.setString(1,All_type);

						ResultSet allocation = pstmt.executeQuery();
						String al = "";

						while ( allocation.next() ) {
							al = allocation.getString("allocation_type");
						}

						pstmt = conn.prepareStatement(
								"INSERT INTO Engagement_details "+ "VALUES (?,?,?,?,?,?,?)");

						pstmt.setString(1,person);
						pstmt.setString(2,All_id);
						pstmt.setString(3,al);
						pstmt.setString(4,En_id);
						pstmt.setString(5,Start);
						pstmt.setString(6,End);
						pstmt.setString(7,Status);


						pstmt.executeUpdate();
						
					}
				}
			}

			System.out.println("------------engagement data inserted----------------");
			
			doc = db.parse(new URL(enURL).openStream());
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("Engagement");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String ClientName = eElement.getElementsByTagName("customer_name").item(0).getTextContent();
					String ProjectName = eElement.getElementsByTagName("project_name").item(0).getTextContent();
					String engagement_id = eElement.getElementsByTagName("id").item(0).getTextContent();
					String start = eElement.getElementsByTagName("start_date").item(0).getTextContent();
					String end = eElement.getElementsByTagName("end_date").item(0).getTextContent();
					
					String start_date , end_date;
					
                    if(start.length()== 0) start_date = "";
                    else start_date = start.substring(0,10);
                    
                    if(end.length()== 0) end_date = "";
                    else end_date= end.substring(0,10);   

					pstmt = conn.prepareStatement(
							"INSERT INTO Client_details "+ "VALUES (?,?,?,?,?)");

					pstmt.setString(1,ClientName);
					pstmt.setString(2,ProjectName);
					pstmt.setString(3,engagement_id);
					pstmt.setString(4,start_date);
					pstmt.setString(5,end_date);

					pstmt.executeUpdate();
				}

			}
			System.out.println("----------client data inserted------------------");
			
			
		}catch(SQLException se){ //Handle errors for JDBC
			se.printStackTrace(); 
		}catch(IOException e){ //Handle errors for IO
			e.printStackTrace(); 
		}catch(Exception e){ //Handle other exceptions
			e.printStackTrace();
		}finally{ 
			
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try{
				if(stmt!=null)  stmt.close();
			}catch(SQLException se2){}
			try{
				if(conn!=null) conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("Connection closed!");
	}
}

