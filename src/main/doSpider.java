package main;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import DAO.HotelDAO;
import DAO.RestaurantDAO;
import db.HotelSQLControl;
import model.Comment;
import model.Hotel;
import model.Restaurant;
import util.URLFetch_Comment;
import util.URLFetch_Hotel;
import util.URLFetch_Rest;

public class doSpider {

	public static void start() throws Exception{
		  //初始化一个httpclient 
       HttpClient client = new DefaultHttpClient();
        /**
         * 重修修改过后的爬取模块
         */
        
        /*
         * 酒店住宿
         */
        
	      List<String>hotellinks=new ArrayList<String>();
	      for(int i=1;i<11;i++){
	    	 String url="http://hotels.ctrip.com/hotel/guilin33/p"+i+"/k1%E6%A1%82%E6%9E%97%E5%9B%AD%E6%9E%97%E6%A4%8D%E7%89%A9%E5%9B%AD";
	    	  
	    	  hotellinks.add(url);
	      }
	      List<List<Hotel>> hoteldatass=new ArrayList<List<Hotel>>();
	      
	      for(String hotellink:hotellinks){
	    	  List<Hotel> hoteldatas=URLFetch_Hotel.URLParser(client, hotellink);
	    	  hoteldatass.add(hoteldatas);
	      }
	      int k=1;
	      for(List<Hotel> hoteldatas:hoteldatass){
	    	  for(Hotel ht:hoteldatas){
	    		  System.out.println("--------第"+k+"-----------------");
	    		  System.out.println("hotelname:"+ht.gethotelName());
	              System.out.println("hotelDesc:"+ht.gethotelDesc());
	              System.out.println("hotelImg:"+ht.gethotelImg());
	              System.out.println("hoteljudgement_score:"+ht.gethoteljudgement_score());
	              System.out.println("hoteljudgement:"+ht.gethoteljudgement());
	              System.out.println("hotelPoint:"+ht.gethotelPoint());
	              System.out.println("hotelPrice:"+ht.gethotelPrice());
	              k++;
	              System.out.println("-------------------------------");
	    	  }
	      }
	      
	      HotelDAO hotelDAO=new HotelDAO();
	      hotelDAO.truncate();
	      for(List<Hotel> hoteldatas:hoteldatass){
	    	  for(Hotel ht:hoteldatas){
	    		  hotelDAO.add(ht);
	    	  }
	      }
        
        /*
         * 特色美食
         */
        List<String>Restaurantlinks=new ArrayList<String>();
	      for(int i=1;i<11;i++){
	    	 String url="http://www.dianping.com/guilin/ch10/r12531p"+i;
	    	  
	    	  Restaurantlinks.add(url);
	      }
	      
	      List<List<Restaurant>> Restaurantdatass=new ArrayList<List<Restaurant>>();
	      
	      for(String Restaurantlink:Restaurantlinks){
	    	  List<Restaurant> restaurantdatas =URLFetch_Rest.URLParser(client, Restaurantlink);
	    	  Restaurantdatass.add(restaurantdatas);
	      }
	      int j=1;
	     for(List<Restaurant> restaurantdatas:Restaurantdatass){
	      for(Restaurant re:restaurantdatas){
      
	    	  System.out.println("--------第"+j+"-----------------");
    		System.out.println("RestName"+re.getRestName());
    		System.out.println("RestImg"+re.getRestImg());
    		System.out.println("RestPoint"+re.getRestPoint());
    		System.out.println("Restlocation"+re.getRestLocation());
    		System.out.println("Restprice"+re.getRestPrice());
    		System.out.println("Restcomment"+re.getRestComment());
    		j++;
    		System.out.println("-------------------------------");
    		
        }
        System.out.println("=================================");
	     }
	     RestaurantDAO restaurantDAO=new RestaurantDAO();
	     restaurantDAO.truncate();
	      for(List<Restaurant> restaurantdatas:Restaurantdatass){
	    	  for(Restaurant rest:restaurantdatas){
	    		  restaurantDAO.add(rest);
	    	  }
	      }
        
       

        
	}
}
