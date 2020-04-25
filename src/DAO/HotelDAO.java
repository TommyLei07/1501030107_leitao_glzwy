package DAO;
   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
  
import model.Hotel;
import util.DBUtil;
   
public class HotelDAO {
 /*  
    public HotelDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
   
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/1501030107雷涛glylzwy_2?characterEncoding=UTF-8", "root",
                "admin");
    }*/
   
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select count(*) from hotel_";
   
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
   
            System.out.println("total:" + total);
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return total;
    }
   
    public void add(Hotel hotel) {
   
        String sql = "insert into hotel_ values(null,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
   
            ps.setString(1, hotel.gethotelName());
            ps.setString(2, hotel.gethotelDesc());
            ps.setString(3, hotel.hotelPoint);
            ps.setString(4, hotel.gethoteljudgement_score());
            ps.setString(5, hotel.hoteljudgement);
            ps.setString(6, hotel.hotelImg);
            ps.setString(7, hotel.hotelPrice);
            ps.execute();
          
   
            System.out.println("插入成功"+hotel.gethotelName());
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
    public void truncate() {
    	   
    	
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
   
            String sql = "truncate table hotel_";
            s.executeQuery(sql);
            System.out.println("删除成功");
          
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    
    }
   
   
    public Hotel get(int id) {
    	Hotel hotel = null;
   
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
   
            String sql = "select * from hotel_ where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            if (rs.next()) {
                hotel = new Hotel();
                String name = rs.getString(2);
                String hp = rs.getString("hp");
                String damage = rs.getString(4);
                hotel.hotelName = name;
                hotel.hotelImg = hp;
                hotel.hoteljudgement = damage;
            
            }
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return hotel;
    }
   
    public List<Hotel> list() {
        return list(0, Short.MAX_VALUE);
    }
   
    public List<Hotel> list(int start, int count) {
        List<Hotel> hotels = new ArrayList<Hotel>();
   
        String sql = "select * from hotel_ order by id asc limit ?,? ";
   
        try (Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);) {
   
            ps.setInt(1, start);
            ps.setInt(2, count);
   
            ResultSet rs = ps.executeQuery();
   
            while (rs.next()) {
            	Hotel hotel = new Hotel();
                int id = rs.getInt(1);
                String hotelname = rs.getString(2);
                String hoteldesc=rs.getString(3);
                String hotelpoint=rs.getString(4);
                String hoteljudgement_score=rs.getString(5);
                String hoteljudgement=rs.getString(6);
                String hotehotelImg=rs.getString(7);
                String hotelPrice=rs.getString(8);
                hotel.id = id;
                hotel.hotelName = hotelname;
                hotel.hotelDesc=hoteldesc;
                hotel.hoteljudgement_score=hoteljudgement_score;
                hotel.hotelPoint=hotelpoint;
                hotel.hoteljudgement=hoteljudgement;
                hotel.hotelPrice=hotelPrice;
                hotel.hotelImg=hotehotelImg;
                hotels.add(hotel);
            }
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return hotels;
    }
   
}