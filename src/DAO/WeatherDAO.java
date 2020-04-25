package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Weather;
import util.DBUtil;

public class WeatherDAO {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection();Statement s = c.createStatement();) {
   
            String sql = "select count(*) from weather_";
   
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
   
           
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return total;
    }
   
    public void add(Weather weather) {
   
        String sql = "insert into weather_ values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
   
            ps.setString(1, weather.getWdate());
            ps.setString(2, weather.getWwea());
            ps.setString(3, weather.getWtem());
            ps.setString(4, weather.getWwin());
            ps.execute();
          
   
            System.out.println("WeatherDAO插入成功"+weather.getWdate());
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
    public void truncate() {
    	   
    	
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
   
            String sql = "truncate table weather_";
            s.executeQuery(sql);
            System.out.println("删除成功");
          
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    
    }
   
   

   
    public List<Weather> list() {
        return list(0, Short.MAX_VALUE);
    }
   
    public List<Weather> list(int start, int count) {
        List<Weather> weathers = new ArrayList<Weather>();
   
        String sql = "select * from weather_ order by id asc limit ?,? ";
   
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
   
            ps.setInt(1, start);
            ps.setInt(2, count);
   
            ResultSet rs = ps.executeQuery();
   
            while (rs.next()) {
            	Weather weather = new Weather();
                int id = rs.getInt(1);
                String Wdate = rs.getString(2);
                String Wwea=rs.getString(3);
                String Wtem=rs.getString(4);
                String Wwin=rs.getString(5);
      
        
                weather.id = id;
                weather.Wdate = Wdate;
                weather.Wwea=Wwea;
                weather.Wtem=Wtem;
                weather.Wwin=Wwin;
         
       
                weathers.add(weather);
            }
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return weathers;
    }
   
}
