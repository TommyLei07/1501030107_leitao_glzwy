package iolayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class SQLRead {
	
	 public  List<Map<String, Object>> getInfo(String sql){
		  Connection conn = null;  
	      PreparedStatement ps = null;  
	      ResultSet rs = null;  
	      
	      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();//存放数据库中的数据
	      try {  
	    	  String driverClass="com.mysql.jdbc.Driver";
	         try {
				Class.forName(driverClass);
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	          conn =  DBUtil.getConnection();
	          ps = conn.prepareStatement(sql);  
	          rs = ps.executeQuery();  
	          ResultSetMetaData rm = rs.getMetaData();//获取数据库中字段的名称、字段的值和属性
	          list = new ArrayList<Map<String, Object>>();//存放数据库中的数据
	          while(rs.next()){  
	        	//使用Map的键值对，来对数据库中的字段和字段对应的值进行存储
	        	  Map<String, Object> m = new HashMap<String, Object>();
	        	//rm.getColumnCount()是字段的个数
	              for (int i = 1; i <= rm.getColumnCount(); i++) {
	            	  
	            	//rm.getColumnName(i)遍历的是字段的名称，rs.getObject(i)遍历是字段对应的值
	                  m.put(rm.getColumnName(i), rs.getObject(i));
	                  
	              }
	            //把Map集合的键值存放到List集合中
	              list.add(m);	              
	          }  	        	          
	      } catch (SQLException e) {  
	          e.printStackTrace();  
	      }finally {  
	          try {  
	              if(rs != null){  
	                  rs.close();  
	              }  
	              if(ps!=null){  
	                  ps.close();  
	              }  
	              if(conn!=null){  
	                  conn.close();  
	              }  
	          } catch (SQLException e) {  
	              e.printStackTrace();  
	          }  
	      }  
	      return list;
	  }
}
