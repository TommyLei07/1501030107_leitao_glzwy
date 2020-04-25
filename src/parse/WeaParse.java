package parse;

import java.util.ArrayList;
import java.util.List;

import model.Restaurant;
import model.Weather;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WeaParse {
	public static List<Weather> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<Weather> data = new ArrayList<Weather>();
        //Jsoup解析
        Document doc = Jsoup.parse(html);  
        System.out.println(doc);   
        
        
        
        
        
            
        System.out.println("++++++++++++++++++++++++++++++++++++++");
   
        Elements elements=doc.select("ul[class=t clearfix]").select("li");
        System.out.println(elements);
        
        for (Element ele:elements) {
        	String Wdate=ele.select("h1").text();
        	System.out.println(Wdate);
			 String Wwea=ele.getElementsByClass("wea").text();
			 String Wtem=ele.select("p[class=tem]").text();
			 String Wwin=ele.select("p[class=win]").text();
		
            //创建一个model对象
			Weather WeaModel=new Weather();          
            //对象的值
			WeaModel.setWdate(Wdate);
			WeaModel.setWwea(Wwea);
			WeaModel.setWtem(Wtem);
			WeaModel.setWwin(Wwin);
			
		
               //将每一个对象的值，保存到List集合中	        
            data.add(WeaModel);
        }
        //返回数据
        return data;
    }
}
