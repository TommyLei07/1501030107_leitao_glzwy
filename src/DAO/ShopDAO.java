package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Shop;
import util.DBUtil;

public class ShopDAO {
	
	   
	    public int getTotal() {
	        int total = 0;
	        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
	   
	            String sql = "select count(*) from shop_";
	   
	            ResultSet rs = s.executeQuery(sql);
	            while (rs.next()) {
	                total = rs.getInt(1);
	            }
	   
	           
	   
	        } catch (SQLException e) {
	   
	            e.printStackTrace();
	        }
	        return total;
	    }
	   
	    public void add(Shop shop) {
	   
	        String sql = "insert into shop_ values(null,?,?,?,?,?)";
	        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	   
	            ps.setString(1, shop.getShop_name());
	            ps.setString(2, shop.getShop_location());
	            ps.setString(3, shop.getShop_recommend());
	            ps.setString(4, shop.getShop_url());
	            ps.setString(5, shop.getShop_img());
	            ps.execute();
	          
	   
	            System.out.println("ShopDAO插入成功"+shop.getShop_name());
	        } catch (SQLException e) {
	   
	            e.printStackTrace();
	        }
	    }
	    public void truncate() {
	    	   
	    	
	        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
	   
	            String sql = "truncate table shop_";
	            s.executeQuery(sql);
	            System.out.println("删除成功");
	          
	   
	        } catch (SQLException e) {
	   
	            e.printStackTrace();
	        }
	    
	    }
	   
	   

	   
	    public List<Shop> list() {
	        return list(0, Short.MAX_VALUE);
	    }
	   
	    public List<Shop> list(int start, int count) {
	        List<Shop> shops = new ArrayList<Shop>();
	   
	        String sql = "select * from shop_ order by id asc limit ?,? ";
	   
	        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	   
	            ps.setInt(1, start);
	            ps.setInt(2, count);
	   
	            ResultSet rs = ps.executeQuery();
	   
	            while (rs.next()) {
	            	Shop shop = new Shop();
	                int id = rs.getInt(1);
	                String shop_name = rs.getString(2);
	                String shop_location=rs.getString(3);
	                String shop_recommend=rs.getString(4);
	                String shop_url=rs.getString(5);
	                String shop_img=rs.getString(6);
	        
	                shop.id = id;
	                shop.shop_name = shop_name;
	                shop.shop_location=shop_location;
	                shop.shop_recommend=shop_recommend;
	                shop.shop_url=shop_url;
	                shop.shop_img=shop_img;
	       
	                shops.add(shop);
	            }
	        } catch (SQLException e) {
	   
	            e.printStackTrace();
	        }
	        return shops;
	    }
	   
}
