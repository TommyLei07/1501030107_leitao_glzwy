package parse;

import java.util.ArrayList;
import java.util.List;

import model.Shop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpParse {
	public static List<Shop> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<Shop> data = new ArrayList<Shop>();
        //Jsoup解析
        Document doc = Jsoup.parse(html);  
         /**
         * 测试单独内容
         */
        Elements elements=doc.select("div[class=list wrapper clearfix]").select("ul").select("li");
        
        for (Element ele:elements) {
        	String shop_name=ele.select("div[class=main]").select("a[class=name1]").text();
			String shop_location=ele.select("div[class=place]").select("a").attr("title");
			String shop_recommend=ele.select("div[class=main]").select("div[class=info]").text();
			String Surl=ele.select("div[class=main]").select("a[class=name1]").attr("href");
			String shop_img=ele.select("div[class=main]").select("a[class=top]").select("img").attr("src");
			String shop_url="http://www.tuniu.com"+Surl;
			if(shop_location == null || shop_location == ""){
				shop_location="可在任意商场选购";
				
			}  	
            //创建一个model对象
			Shop ShopModel=new Shop();          
            //对象的值
			ShopModel.setShop_name(shop_name);
			ShopModel.setShop_location(shop_location);
			ShopModel.setShop_recommend(shop_recommend);
			ShopModel.setShop_url(shop_url);
			ShopModel.setShop_img(shop_img);
			
		
               //将每一个对象的值，保存到List集合中	        
            data.add(ShopModel);
        }
        //返回数据
        return data;
    }
}
