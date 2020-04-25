package model;

public class Comment {
		public int id;
	    public String cmFrom;
	    public String cmContent;
	    public String cmPoint;
	    public String cmUseful;
	    public String cmUserimg;
	    
	    public int getId(){
	    	return id;
	    }
	    public void setId(int id){
	    	this.id=id;
	    }
	    public String getCmFrom() {
	        return cmFrom;
	    }
	    public void setCmFrom(String cmFrom) {
	        this.cmFrom = cmFrom;
	    }
	    public String getCmContent() {
	        return cmContent;
	    }
	    public void setCmContent(String cmContent) {
	        this.cmContent = cmContent;
	    }
	    public String getCmPoint() {
	        return cmPoint;
	    }
	    public void setCmPoint(String cmPoint) {
	        this.cmPoint = cmPoint;
	    }
	    public String getCmUseful() {
	        return cmUseful;
	    }
	    public void setCmUseful(String cmUseful) {
	        this.cmUseful = cmUseful;
	    }
	    public String getCmUserimg() {
	        return cmUserimg;
	    }
	    public void setCmUserimg(String cmUserimg) {
	        this.cmUserimg = cmUserimg;
	    }
}
