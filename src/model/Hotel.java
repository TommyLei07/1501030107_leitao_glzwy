package model;

public class Hotel {
    public String hotelName;
    public String hotelDesc;
    public String hotelImg;
    public String hoteljudgement_score;
    public String hoteljudgement;
    public String hotelPoint;
    public String hotelPrice;
    public int id;
    
    public int getId(){
    	return id;
    	
    }
    public void setId(int id){
    	this.id=id;
    }
    
    public String gethotelName() {
        return hotelName;
    }
    public void sethotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    
    public String gethotelPoint() {
        return hotelPoint;
    }
    public void sethotelPoint(String hotelPoint) {
        this.hotelPoint = hotelPoint;
    }
    
    public String gethotelDesc() {
        return hotelDesc;
    }
    public void sethotelDesc(String hotelDesc) {
        this.hotelDesc = hotelDesc;
    }
    
    public String gethotelImg() {
        return hotelImg;
    }
    public void sethotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }
    
    
    public String gethoteljudgement_score() {
        return hoteljudgement_score;
    }
    public void sethoteljudgement_score(String hoteljudgement_score) {
        this.hoteljudgement_score = hoteljudgement_score;
    }
    
    public String gethoteljudgement() {
        return hoteljudgement;
    }
    public void sethoteljudgement(String hoteljudgement) {
        this.hoteljudgement = hoteljudgement;
    }
    
    public String gethotelPrice() {
        return hotelPrice;
    }
    public void sethotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }
}
