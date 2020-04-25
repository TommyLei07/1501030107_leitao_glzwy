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

import DAO.CommentDAO;
import model.Comment;
public class CommentSpider {
	
	private String uri = "http://you.ctrip.com/sight/hechi838/1409352.html";

	List<Comment> Cmdatas=new ArrayList<>();
	CommentDAO commentDAO=new CommentDAO();


	
	public void action() {
		List<Document> list = this.getDocument();
		System.out.println(list.size());
		for (Document doc : list) {
			this.readInformation(doc);
			
		}
		int i=1;
		
		commentDAO.truncate();
		for(Comment cm:Cmdatas){
			System.out.println("====第"+i+"条数据====");
			System.out.println(cm.cmFrom);
			System.out.println(cm.cmContent);
			i++;
			
			commentDAO.add(cm);
			
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
				HtmlDivision pagediv = (HtmlDivision) page.getElementById("sightcommentbox");
				DomNodeList<HtmlElement> pageas = pagediv.getElementsByTagName("a");
				boolean next=false;
				for (HtmlElement a : pageas) {
					if(a.asText().equals("1")&&!first) {
						page = (HtmlPage) a.click();
						Document doc = Jsoup.parse(page.asXml());
						
						list.add(doc);
						System.out.println("123");
					/*	i++;
						System.out.println("Firsti=:"+i);*/
						webClient.waitForBackgroundJavaScript(200);// 等待
						first=true;
					}
					if(a.asText().equals("下一页")) {
						page = (HtmlPage) a.click();
						Document doc = Jsoup.parse(page.asXml());
						list.add(doc);
				/*		i++;
						System.out.println("Secondi=:"+i);*/
						System.out.println("456");
						webClient.waitForBackgroundJavaScript(200);// 等待
						next=true;
						if(a.getAttribute("class").equals("nextpage disabled")) {
							next=false;
						}
						/*if(i==3){
							next=false;
						}*/
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
            Cmdatas.add(cmModel);
        }
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
	
}
