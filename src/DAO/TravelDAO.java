package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Travel;
import util.DBUtil;

public class TravelDAO {
	
	   
	    public int getTotal() {
	        int total = 0;
	        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
	   
	            String sql = "select count(*) from travel_";
	   
	            ResultSet rs = s.executeQuery(sql);
	            while (rs.next()) {
	                total = rs.getInt(1);
	            }
	   
	           
	   
	        } catch (SQLException e) {
	   
	            e.printStackTrace();
	        }
	        return total;
	    }
	   
	    public void add(Travel travel) {
	   
	        String sql = "insert into travel_ values(null,?,?,?,?,?,?)";
	        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	   
	            ps.setString(1, travel.getTravel_name());
	            ps.setString(2, travel.getTravel_point());
	            ps.setString(3, travel.getTravel_price());
	            ps.setString(4, travel.getTravel_comment());
	            ps.setString(5, travel.getTravel_img());
	            ps.execute();
	          
	   
	            System.out.println("TravelDAO插入成功"+travel.getTravel_name());
	        } catch (SQLException e) {
	   
	            e.printStackTrace();
	        }
	    }
	    public void truncate() {
	    	   
	    	
	        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
	   
	            String sql = "truncate table travel_";
	            s.executeQuery(sql);
	            System.out.println("删除成功");
	          
	   
	        } catch (SQLException e) {
	   
	            e.printStackTrace();
	        }
	    
	    }
	   
	   

	   
	    public List<Travel> list() {
	        return list(0, Short.MAX_VALUE);
	    }
	   
	    public List<Travel> list(int start, int count) {
	        List<Travel> travels = new ArrayList<Travel>();
	   
	        String sql = "select * from travel_ order by id asc limit ?,? ";
	   
	        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	   
	            ps.setInt(1, start);
	            ps.setInt(2, count);
	   
	            ResultSet rs = ps.executeQuery();
	   
	            while (rs.next()) {
	            	Travel travel = new Travel();
	                int id = rs.getInt(1);
	                String travel_name = rs.getString(2);
	                String travel_point=rs.getString(3);
	                String travel_price=rs.getString(4);
	                String travel_comment=rs.getString(5);
	                String travel_img=rs.getString(6);
	                String travel_url=rs.getString(7);
	                travel_comment=travel_comment.substring(0, 100);
	                
	        
	                travel.id = id;
	                travel.travel_name = travel_name;
	                travel.travel_point=travel_point;
	                travel.travel_price=travel_price;
	                travel.travel_comment=travel_comment;
	                travel.travel_img=travel_img;
	                travel.travel_url=travel_url;
	       
	                travels.add(travel);
	            }
	        } catch (SQLException e) {
	   
	            e.printStackTrace();
	        }
	        return travels;
	    }
	   
}
