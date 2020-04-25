package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Restaurant;
import util.DBUtil;

public class RestaurantDAO {

   
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select count(*) from restaurant_";
   
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
   
        
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return total;
    }
   
    public void add(Restaurant restaurant) {
   
        String sql = "insert into restaurant_ values(null,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
   
            ps.setString(1, restaurant.getRestName());
            ps.setString(2, restaurant.getRestPoint());
            ps.setString(3, restaurant.getRestLocation());
            ps.setString(4, restaurant.getRestPrice());
            ps.setString(5, restaurant.getRestComment());
            ps.setString(6, restaurant.getRestImg());
           
            ps.execute();
          
   
            System.out.println("插入成功"+restaurant.getRestName());
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
    public void truncate() {
    	   
    	
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
   
            String sql = "truncate table restaurant_";
            s.executeQuery(sql);
            System.out.println("删除成功");
          
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    
    }
   
   

   
    public List<Restaurant> list() {
        return list(0, Short.MAX_VALUE);
    }
   
    public List<Restaurant> list(int start, int count) {
        List<Restaurant> rests = new ArrayList<Restaurant>();
   
        String sql = "select * from restaurant_ order by id asc limit ?,? ";
   
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
   
            ps.setInt(1, start);
            ps.setInt(2, count);
   
            ResultSet rs = ps.executeQuery();
   
            while (rs.next()) {
            	Restaurant restaurant=new Restaurant();
                int id = rs.getInt(1);
                String RestName = rs.getString(2);
                String RestPoint=rs.getString(3);
                String Restlocation=rs.getString(4);
                String RestPrice=rs.getString(5);
                String Restcomment=rs.getString(6);
                String RestImg=rs.getString(7);
               
                restaurant.id = id;
                restaurant.restName = RestName;
                restaurant.restPoint=RestPoint;
                restaurant.restLocation=Restlocation;
                restaurant.restPrice=RestPrice;
                restaurant.restComment=Restcomment;
                restaurant.restImg=RestImg;
         
                rests.add(restaurant);
            }
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return rests;
    }
}
