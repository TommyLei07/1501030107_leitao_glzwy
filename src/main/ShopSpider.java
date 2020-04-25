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
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import DAO.ShopDAO;
import model.Shop;

public class ShopSpider {
	
	private String uri = "http://www.tuniu.com/g705/specialty-0-0/";
	List<Shop> shops=new ArrayList<>();
	ShopDAO shopDAO=new ShopDAO();
	public void action() {
		List<Document> list = this.getDocument();
		System.out.println(list.size());
		for (Document doc : list) {
			this.readInformation(doc);
		
			
		}
		int i=1;
	
		shopDAO.truncate();
		for(Shop sp:shops){
			shopDAO.add(sp);
			
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
			int i=0;
			boolean flag = true;// 判断是不是还有下一页
			boolean first=false;
			while (flag) {

				DomNodeList<DomElement> pages =page.getElementsByTagName("a");
				boolean next=false;
				for (DomElement a : pages) {
					if(a.asText().equals("1")&&!first) {
						page = (HtmlPage) a.click();
						Document doc = Jsoup.parse(page.asXml());
						list.add(doc);
						i++;
						System.out.println("123");
						webClient.waitForBackgroundJavaScript(200);// 等待
						first=true;
						
					}
					if(a.asText().equals("下一页")) {
						page = (HtmlPage) a.click();
						Document doc = Jsoup.parse(page.asXml());
						list.add(doc);
						i++;
						System.out.println("456");
						webClient.waitForBackgroundJavaScript(200);// 等待
						next=true;
						if(a.getAttribute("class").equals("nextpage disabled")) {
							next=false;
						}
					
						break;
					}
				}
				if(!next) flag=false;
			}

		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		list.remove(list.size()-1);
		webClient.close();
		System.out.println("--------------------------------------------ajax请求完毕---------------------------------------------");
		System.out.println();
		System.out.println("--------------------------------------------关闭模拟浏览器---------------------------------------------");
		System.out.println();
		return list;
	}

	
	public void readInformation(Document doc) {
	

		
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
	            shops.add(ShopModel);
	        }
		
		
		
		
		
		
		
		
		
		
		
	
	}
	
}
