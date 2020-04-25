package main;

import java.util.List;

import model.Comment;
import model.Hotel;
import model.IntroS;
import model.Restaurant;
import model.Shop;
import model.Travel;
import model.Weather;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import util.URLFetch_Hotel;
import util.URLFetch_Comment;
import util.URLFetch_IntroS;
import util.URLFetch_Rest;
import util.URLFetch_Shop;
import util.URLFetch_Travel;
import util.URLFetch_Weather;
import db.CommentSQLControl;
import db.HotelSQLControl;
import db.IntroSSQLControl;
import db.RestaurantSQLControl;
import db.ShopSQLControl;
import db.TravelSQLControl;
import db.WeatherSQLControl;
public class SpiderMain {
	static Logger logger = Logger.getLogger(SpiderMain.class);

	public static void main(String[] args) throws Exception {
		 doSpider.start();
		new CommentSpider().action();
		new ShopSpider().action();
		new WeatherSpider().action();
	

	}
}