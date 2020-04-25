package model;

public class Restaurant {
	
		public int id;
		public String restName;
		public String restImg;
		public String restPoint;
		public String restLocation;
		public String restPrice;
		public String restComment;
		public int getId(){
			return id;
		}
		public void setId(int id){
			this.id=id;
		}
	    public String getRestName() {
	        return restName;
	    }
	    public void setRestName(String RestName) {
	        this.restName = RestName;
	    }
	    public String getRestImg() {
	        return restImg;
	    }
	    public void setRestImg(String RestImg) {
	        this.restImg = RestImg;
	    }
	    public String getRestPoint() {
	        return restPoint;
	    }
	    public void setRestPoint(String RestPoint) {
	        this.restPoint = RestPoint;
	    }
	    public String getRestLocation() {
	        return restLocation;
	    }
	    public void setRestLocation(String Restlocation) {
	        this.restLocation = Restlocation;
	    }
	    
	    public String getRestPrice() {
	        return restPrice;
	    }
	    public void setRestPrice(String Restprice) {
	        this.restPrice = Restprice;
	    }
	    public String getRestComment() {
	        return restComment;
	    }
	    public void setRestComment(String Restcomment) {
	        this.restComment = Restcomment;
	    }
	   
	    
}
