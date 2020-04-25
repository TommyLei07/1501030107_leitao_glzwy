package parse;

import java.util.ArrayList;
import java.util.List;


import model.Travel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TrParse {
	public static List<Travel> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<Travel> data = new ArrayList<Travel>();
        //Jsoup解析
        Document doc = Jsoup.parse(html);  
         /**
         * 测试单独内容
         */
        Elements elements=doc.select("div[class=card_list in_card]").select("li");
        
        
        for (Element ele:elements) {
        	String travel_img=ele.select("span[class=ttd_nopic220]").select("img").attr("src");
        	String pn=ele.select("a[class=all_link]").attr("title");

        	String travel_name=pn.substring(0,pn.length()-16);
        	String travel_point=pn.substring(pn.length()-15);
        	
        	String travel_price=ele.select("span[class=ticket]").text();
        	String travel_comment=ele.select("p[class=ellipsis]").text();
        	
        	String travelurl=ele.select("a[class=all_link]").attr("href");
        	String travel_url="http://you.ctrip.com"+travelurl;
        	
        
        	
        	
				
            //创建一个model对象
			 Travel TrModel=new Travel();
            //对象的值
			 TrModel.setTravel_img(travel_img);
			 TrModel.setTravel_name(travel_name);
			 TrModel.setTravel_point(travel_point);
			 TrModel.setTravel_price(travel_price);
			 TrModel.setTravel_comment(travel_comment);
			 TrModel.setTravel_url(travel_url);
			 
			
		
               //将每一个对象的值，保存到List集合中	        
            data.add(TrModel);
        }
        //返回数据
        return data;
    }
}
