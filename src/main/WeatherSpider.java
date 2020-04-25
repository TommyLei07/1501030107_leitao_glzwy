package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import DAO.WeatherDAO;
import model.Weather;

public class WeatherSpider {
	
	private String uri = "http://www.weather.com.cn/weather/101300501.shtml";


	List<Weather> weathers=new ArrayList<>();
	WeatherDAO weatherDAO=new WeatherDAO();
	public void action() {
		List<Document> list = this.getDocument();
		for (Document doc : list) {
			this.readInformation(doc);
			
		}
		int i=1;
		weatherDAO.truncate();
		for(Weather wea:weathers){
			weatherDAO.add(wea);
		}
		
	}
	public List<Document> getDocument() {
		List<Document> list = new ArrayList<>();
		System.out.println("------------------------------------------开始模拟游览器----------------------------------------------");
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 设置Ajax异步

		HtmlPage page;
		try {
			page = webClient.getPage(uri);
			Document doc = Jsoup.parse(page.asXml());
			
			list.add(doc);


		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	/*	list.remove(list.size()-1);*/
		webClient.close();
		System.out.println("--------------------------------------------ajax请求完毕---------------------------------------------");
		System.out.println();
		System.out.println("--------------------------------------------关闭模拟浏览器---------------------------------------------");
		System.out.println();
		return list;
	}

	
	public void readInformation(Document doc) {
		
		 Elements elements=doc.select("ul[class=t clearfix]").select("li");
	       
	        
	        for (Element ele:elements) {
	        	String Wdate=ele.select("h1").text();
	        	
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
	            weathers.add(WeaModel);
	        }
	        //返回数据
	
		
	
	}
	
}
