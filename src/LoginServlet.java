

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    static String userName ;
 
    public LoginServlet() {
    	super();
    }
 
    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("in get");
    	
        userName = request.getParameter("userName");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
        
        if (userName == null || userName.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
            System.out.println("us");
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            System.out.println("else");
            try {
                // Find the user in the DB.
                user = DBUtils.findUser(conn, userName);
                System.out.println("asaas");
                
                if (user== null) {
                	//System.out.println(user.getUserName());
                    hasError = true;
                    errorString = "No engagements found!";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /WEB-INF/views/homeView.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward to /WEB-INF/views/login.jsp
            //....................................................................
           Connection conn = MyUtils.getStoredConnection(request);
           List<String> userList = null;
     	   try {
     		   System.out.println("in home");
            	userList = DBUtils.getEngineerList(conn);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
     	   
     	   request.setAttribute("userList", userList);
     	   
     	   //.......................................................................
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
 
            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
 
            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }
 
            // Redirect to EngagementList page.
            response.sendRedirect(request.getContextPath() + "/engagementList");
        }
    	
    	
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
//        RequestDispatcher dispatcher //
//                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
// 
//        dispatcher.forward(request, response);
 
    }
 
    // When the user enters userName & password, and click Submit.
    // This method will be executed.
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		
    		doGet(request , response);
        
    }
    
    public static String getUserName(){
    	return userName;
    }
 
}