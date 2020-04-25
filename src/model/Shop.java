package model;

public class Shop {
		public int id;
	   public String shop_name;
	   public String shop_location;
	   public String shop_recommend;
	   public String shop_url;
	   public String shop_img;
	  
	   public int getId(){
		   return id;
	   }
	   public void setId(int id){
		   this.id=id;
	   }
	    public String getShop_name(){
	    	return shop_name;
	    }
	    public void setShop_name(String shop_name){
	    	this.shop_name=shop_name;
	    }
	    public String getShop_location(){
	    	return shop_location;
	    }
	    public void setShop_location(String shop_location){
	    	this.shop_location=shop_location;
	    }
	    public String getShop_recommend(){
	    	return shop_recommend;
	    }
	    public void setShop_recommend(String shop_recommend) {
	    	this.shop_recommend=shop_recommend;
			
		}
	    public String getShop_url(){
	    	return shop_url;
	    }
	    public void setShop_url(String shop_url) {
	    	this.shop_url=shop_url;
			
		}
	    public String getShop_img(){
	    	return shop_img;
	    }
	    public void setShop_img(String shop_img) {
	    	this.shop_img=shop_img;
			
		}
	    
}
