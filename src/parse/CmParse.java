package parse;

import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Hotel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CmParse {
	public static List<Comment> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<Comment> data = new ArrayList<Comment>();
        //Jsoup解析
        Document doc = Jsoup.parse(html);  
         /**
         * 测试单独内容
         */
        Elements elements=doc.select("div[class=comment_ctrip]").select("div[class=comment_single]");
        for (Element ele:elements) {
          	String from=ele.select("span[class=ellipsis]").text();
        	String content=ele.select("span[class=heightbox]").text();
			String point=ele.select("span[class=sblockline]").text();
			String userimg=ele.select("div[class=userimg]").select("img").attr("src");
		    if(point == null || point.length() <= 0){
		    	point="暂无评分";
		    }
			String useful=ele.select("span[class=useful]").select("em").text();        	
            //创建一个model对象
          Comment cmModel=new Comment();          
            //对象的值
            cmModel.setCmFrom(from);
            cmModel.setCmContent(content);
            cmModel.setCmPoint(point);
            cmModel.setCmUseful(useful);
            cmModel.setCmUserimg(userimg);
            
               //将每一个对象的值，保存到List集合中	        
            data.add(cmModel);
        }
        //返回数据
        return data;
    }
}
