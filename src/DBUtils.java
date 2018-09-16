

//import java.net.URL;
//import com.mysql.jdbc.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
public class DBUtils{

	static UserAccount acc = null;
	
	public static UserAccount findUser(Connection conn, String userName) throws SQLException {
		 
	    String sql = "Select person from Engagement_details where person = ?";
	
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, userName);
	
	    ResultSet rs = pstm.executeQuery();
	    if(rs.next()){
	        //String person = rs.getString("person");
	    	acc = new UserAccount(userName);
	        return acc;
	    }	
	    return null;
	}
	
	public static List<Engagement> queryEngagement(Connection conn) throws SQLException {
		
	    String sql = "SELECT * FROM Engagement_details  WHERE person=?";
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1,acc.getUserName());
	
	    ResultSet rs = pstm.executeQuery();
	    List<Engagement> list = new ArrayList<Engagement>();
	    while (rs.next()) {
	        String en_id = rs.getString("engagement_id");
	        String person = rs.getString("person");
	        String al_id = rs.getString("allocation_id");
	        String al_type= rs.getString("allocation_type");
	        String start= rs.getString("start_date");
	        String end= rs.getString("end_date");
	        String status= rs.getString("status");
	        Engagement en = new Engagement(en_id,person,al_id,al_type,start,end,status);
	        list.add(en);
	    }
	    return list;
	}
	
	public static List<String> getEngineerList(Connection conn) throws SQLException{
		
		String sql = "SELECT DISTINCT person FROM Engagement_details ORDER BY person";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<String> userList = new ArrayList<String>();
		
		while(rs.next()){
			String person = rs.getString("person");
			userList.add(person);
		}
		
		
		return userList;
	}
	
	public static List<String> getQSPList(Connection conn) throws SQLException{
	        
	        List<String> QSPList = getEngineerList(conn);
	        ResultSet rs; 
	        String sql = "SELECT DISTINCT person,allocation_type FROM Engagement_details";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        rs = pstm.executeQuery();
	        
	        //HashMap <String,String> qsp = new HashMap <String,String>();    
	        while(rs.next()){
	            String person = rs.getString("person");
	            String type = rs.getString("allocation_type");
	            if(type.equals("QSP")&& QSPList.contains(person)){
	                QSPList.remove(person);
	            }
	        }
	        
	        return QSPList;
	    }
	
	 public static HashMap<String, int[]> getSummaryDetails(Connection conn) throws SQLException, ParseException{
	        String sql = "SELECT * FROM Engagement_details ORDER BY person";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        ResultSet rs = pstm.executeQuery();
	        HashMap <String,int[]> list = new HashMap <String,int[]>();
	        //array[0]-total no of dev services
	        //array[1]-total no of QSPs
	        //array[2]-QSps in last 12months
	        //array[3]-total no of dev services days
	        
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.YEAR, -1); // to get previous year -1
	        Date lastYear = cal.getTime();
	        
	        while (rs.next()) {
	            
	            String person = rs.getString("person");
	            if("ruchira@wso2.com ".equals(person)) System.out.println("equal");
	            String al_type= rs.getString("allocation_type");
	            String start= rs.getString("start_date");
	            Date startd=new SimpleDateFormat("yyyy-MM-dd").parse(start); 
	            String end= rs.getString("end_date");
	            Date endd=new SimpleDateFormat("yyyy-MM-dd").parse(end); 
	            
	            if(list.containsKey(person)){
	                //System.out.println("con "+person);
	                int[] array= list.get(person); //total no of dev
	                array[0]=array[0]+1;
	                long days = (endd.getTime() - startd.getTime())/(24 * 60 * 60 * 1000);
	                if(endd.equals(startd)) days= 1;
	                array[3]+=days;

	                if(al_type.equals("QSP")){
	                    array[1]+=1; //total no of QSPs
	                    if(endd.after(lastYear)){
	                        array[2]+=1;//QSPs in last 12months
	                    }
	                }
	                list.replace(person,array);
	            }else{
	                //System.out.println("ini "+person);
	                int[] array = new int[4];
	                array[0]=1;//total no of dev
	                long days = (endd.getTime() - startd.getTime())/(24 * 60 * 60 * 1000); //total no of dev days
	                if(endd.equals(startd)) days =1;
	                array[3]= (int) days;
	                if(al_type.equals("QSP")){
	                    array[1] =1; //total no of QSPs
	                    if(endd.after(lastYear)){
	                        array[2]=1;//QSPs in last 12 months
	                    }
	                }
	                list.put(person, array);
	            }
	        }
	        return list;
	    }
	


}