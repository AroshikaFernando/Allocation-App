

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



@WebServlet(urlPatterns = { "/engagementList" })
public class EngagementsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public EngagementsList() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        List<Engagement> engagemnetsList = null;
        List<String> userList = null;
        List<String> qsplist = null;
        try {
        	engagemnetsList = DBUtils.queryEngagement(conn);
        	userList = DBUtils.getEngineerList(conn);
        	qsplist = DBUtils.getQSPList(conn);
        	
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        for (Engagement a: engagemnetsList){
        	System.out.println(a.getEnId());
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("engagementList", engagemnetsList);
        request.setAttribute("userList", userList);
        request.setAttribute("QSPList", qsplist);
        
        request.setAttribute("userName", LoginServlet.getUserName());
         
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/engagementListView.jsp");
        dispatcher.forward(request, response);
    }
    
    
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
