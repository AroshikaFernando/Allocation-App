
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

  
public class MySQLConnUtils {
	 
	 static Properties prop = new Properties();
	 
	 public static Connection getMySQLConnection()
	         throws ClassNotFoundException, SQLException {
		 
	     //connect to DB
		 try{
			//read properties file
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
			InputStream in = classLoader.getResourceAsStream("config.properties"); 
			prop.load(in);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
			//load db credentials & URLs
	     String hostName =prop.getProperty("hostName");
	     String dbName = prop.getProperty("dbName");
	     String userName = prop.getProperty("userName");
	     String password = prop.getProperty("password");
	     return getMySQLConnection(hostName, dbName, userName, password);
	 }
	  
	 public static Connection getMySQLConnection(String hostName, String dbName,
	         String userName, String password) throws SQLException,
	         ClassNotFoundException {
	    
	     Class.forName(prop.getProperty("JDBC_DRIVER"));

	     String connectionURL = "jdbc:mysql://" + hostName + prop.getProperty("port") + dbName;
	  
	     Connection conn = DriverManager.getConnection(connectionURL, userName,
	             password);
	     return conn;
	 }
}