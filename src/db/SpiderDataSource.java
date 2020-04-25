package db;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class SpiderDataSource {
	 public static DataSource getDataSource(String connectURL){
	        BasicDataSource ds = new BasicDataSource();
	         //MySQL的jdbc驱动
	        ds.setDriverClassName("com.mysql.jdbc.Driver");
	        ds.setUsername("root");              //所要连接的数据库名
	        ds.setPassword("admin");                //MySQL的登陆密码
	        ds.setUrl(connectURL);
	        return ds;
	    }
}
