

public class UserAccount {
	
	   public UserAccount(){
		   
	   }
	
	   private String userName;
	   //private String password;
	    
	 
	   public UserAccount(String uName) {
	        this.userName = uName;
	   }
	    
	   public String getUserName() {
	       return userName;
	   }
	 
	   public void setUserName(String userName) {
	       this.userName = userName;
	   }
	 
//	   public String getPassword() {
//	       return password;
//	   }
//	 
//	   public void setPassword(String password) {
//	       this.password = password;
//	   }
	 
	}