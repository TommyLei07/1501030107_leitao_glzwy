package parse;

import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Restaurant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RestParse {
	public static List<Restaurant> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<Restaurant> data = new ArrayList<Restaurant>();
        //Jsoup解析
        Document doc = Jsoup.parse(html);  
 
         /**
         * 测试单独内容
         */
        Elements elements=doc.select("div[class=shop-list J_shop-list shop-all-list]").select("li");
        
        for (Element ele:elements) {
			String RestName=ele.select("div[class=tit]").select("a").attr("title");
			String RestImg=ele.select("div[class=pic]").select("img").attr("data-src");
			String RestPoint=ele.select("div[class=comment]").select("span").attr("title");
			String Restlocation=ele.select("div[class=tag-addr]").select("span[class=addr]").text();
			
			String Restcomment=ele.select("span[class=comment-list]").text();      	
			String  price=ele.select("div[class=comment]").select("a[class=mean-price]").text();

	
			
			String Restprice=price.substring(4);

	
            //创建一个model对象
			Restaurant RestModel=new Restaurant();          
            //对象的值
			RestModel.setRestName(RestName);
			RestModel.setRestImg(RestImg);
			RestModel.setRestPoint(RestPoint);
			RestModel.setRestPrice(Restprice);
			RestModel.setRestLocation(Restlocation);
			RestModel.setRestComment(Restcomment);
		
               //将每一个对象的值，保存到List集合中	        
            data.add(RestModel);
        }
        //返回数据
        return data;
    }
}
