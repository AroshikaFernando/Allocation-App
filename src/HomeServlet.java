
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
 
@WebServlet(urlPatterns = { "/home"})
public class HomeServlet extends HttpServlet implements ServletContextListener{
   private static final long serialVersionUID = 1L;
 
   public HomeServlet() {
       super();
   }

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletContextListener started");
	    try {
			JobDetail job = JobBuilder.newJob(HelloJob.class)
					.withIdentity("dummyJobName", "group1").build();
	
			// SimpleTrigger trigger = new SimpleTrigger();
			// trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
			// trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
			// trigger.setRepeatInterval(30000);
	
			// Trigger the job to run on the next round minute
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("dummyTriggerName", "group1")
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever())
					.build();
	
			// schedule it
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
	
	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
		
	}
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
 
	   Connection conn = MyUtils.getStoredConnection(request);
	   
	   String errorString = null;
	   List<String> userList = null;
	   List<String> qsplist = null;
	   try {
		   System.out.println("in home");
       	userList = DBUtils.getEngineerList(conn);
       	qsplist = DBUtils.getQSPList(conn);
       } catch (SQLException e) {
           e.printStackTrace();
           errorString = e.getMessage();
       }
	   
	   request.setAttribute("userList", userList);
	   request.setAttribute("errorString", errorString);
	   request.setAttribute("QSPList", qsplist);
	   
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
        
       dispatcher.forward(request, response);
        
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
       
   }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletContextListener destroyed");
		
	}


 
}