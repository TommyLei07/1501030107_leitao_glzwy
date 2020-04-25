package parse;

import java.util.ArrayList;
import java.util.List;

import model.IntroS;
import model.Restaurant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IntroSParse {
	public static List<IntroS> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<IntroS> data = new ArrayList<IntroS>();
        //Jsoup解析
        Document doc = Jsoup.parse(html);  
         /**
         * 测试单独内容
         */
        Elements elements=doc.select("div[class=s_sight_infor]");
    	Elements elements2=doc.select("div[class=toggle_s]");
        
        for (Element ele:elements) {
        	String addr=ele.select("p[class=s_sight_addr]").text();
        	String zonghe2=ele.select("dl[class=s_sight_in_list]").select("dd").text();
        	String zonghe=ele.select("span[class=s_sight_con]").text();
    		String location=addr.substring(3,15);	
    		String type=zonghe.substring(0,3);
    		String funtime=zonghe.substring(4,9);
    		//String tel=zonghe.substring(9,22);
    		String opentime=zonghe2.substring(0,10);
    		String ticket=zonghe2.substring(11,19);
    		//String website=zonghe.substring(23);
    
    		
    		String introl=elements2.select("div[class=text_style]").text();
    		
    		
            //创建一个model对象
			IntroS IntroSModel=new IntroS();          
            //对象的值
		IntroSModel.setlocation(location);
		IntroSModel.settype(type);
		IntroSModel.setfuntime(funtime);
		IntroSModel.setopentime(opentime);

		IntroSModel.setticket(ticket);
		IntroSModel.setintrol(introl);
			
			
		
               //将每一个对象的值，保存到List集合中	        
            data.add(IntroSModel);
        }
        //返回数据
        return data;
    }
}
