package parse;

import java.util.ArrayList;
import java.util.List;

import model.Hotel;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtParse {
	 public static List<Hotel> getData (String html) throws Exception{
	        //获取的数据，存放在集合中
	        List<Hotel> data = new ArrayList<Hotel>();
	        //Jsoup解析
	        Document doc = Jsoup.parse(html);
	      /*  下面经测试可用*/
	       // Element element=doc.getElementById("hotel_list");
			//获取结果
	        
			Elements elements=doc.getElementsByClass("hotel_new_list");
		        for (Element ele:elements) {
		        	
	            String hoteladdr=ele.getElementsByClass("hotel_item_htladdress").text();
	            String hotelDesc=hoteladdr.substring(0,hoteladdr.length()-2);
	            String hotelImg=ele.getElementsByTag("img").attr("src");      
	        	String hotelName=ele.getElementsByTag("img").attr("alt");    
	        	String hotelPoint=ele.select("span[class=hotel_value]").text();
	         	String hoteljudgement_score=ele.select("span[class=total_judgement_score]").text();
	         	String hoteljudgement=ele.select("span[class=hotel_judgement]").text();
	         	String hotelPrice=ele.select("span[class=J_price_lowList ]").text();
	         	
	         	
	         	//String hotelPrice=ele.getElementsByClass("hotel_price").text();
	        	
	            //创建一个model对象
	            Hotel htModel=new Hotel();	            
	            //对象的值
	            htModel.sethotelName(hotelName);
	            htModel.sethotelDesc(hotelDesc);
	           htModel.sethotelImg(hotelImg);
	           htModel.sethoteljudgement_score(hoteljudgement_score);
	           htModel.sethoteljudgement(hoteljudgement);
	           htModel.sethotelPoint(hotelPoint);
	           htModel.sethotelPrice(hotelPrice);
	          
	            //将每一个对象的值，保存到List集合中	        
	            data.add(htModel);
	        }
	        //返回数据
	        return data;
	    }
}
