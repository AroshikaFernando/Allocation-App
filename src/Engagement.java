

public class Engagement {
 
   private String Engagement_id;
   private String Person; 
   private String Allocation_id;
   private String Allocation_type;
   private String Start;
   private String End;
   private String Status;
   public Engagement(){
 
   }
 
   public Engagement(String engagement_id, String person, String allocation_id, 
		   String allocation_type,String Start,String End ,String  Status ) {
       this.Engagement_id = engagement_id;
       this.Person = person;
       this.Allocation_id = allocation_id;
       this.Allocation_type = allocation_type;
       this.Start = Start;
       this.End= End;
       this.Status= Status;
   }
 
   public String getEnId() {
       return Engagement_id;
   }
 
 
   public String getPerson() {
       return Person;
   }
 
   public String getAlId() {
       return Allocation_id;
   }
 
   public String getStart() {
       return Start;
   }
   
   public String getEnd() {
       return End;
   }
   
   public String getStatus() {
       return Status;
   }
   
   public String getAltype() {
       return Allocation_type;
   }
 
}