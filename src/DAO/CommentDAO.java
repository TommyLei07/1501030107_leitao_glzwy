package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Comment;
import util.DBUtil;



public class CommentDAO {
/*    public CommentDAO() {
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
   
            String sql = "select count(*) from comment_";
   
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
   
           
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return total;
    }
   
    public void add(Comment comment) {
   
        String sql = "insert into comment_ values(null,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
   
            ps.setString(1, comment.getCmFrom());
            ps.setString(2, comment.getCmContent());
            ps.setString(3, comment.getCmPoint());
            ps.setString(4, comment.getCmUseful());
            ps.setString(5, comment.getCmUserimg());
            ps.execute();
          
   
            System.out.println("CommentDAO插入成功"+comment.getCmFrom());
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    }
    public void truncate() {
    	   
    	
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
   
            String sql = "truncate table comment_";
            s.executeQuery(sql);
            System.out.println("删除成功");
          
   
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
    
    }
   
   

   
    public List<Comment> list() {
        return list(0, Short.MAX_VALUE);
    }
   
    public List<Comment> list(int start, int count) {
        List<Comment> comments = new ArrayList<Comment>();
   
        String sql = "select * from comment_ order by id asc limit ?,? ";
   
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
   
            ps.setInt(1, start);
            ps.setInt(2, count);
   
            ResultSet rs = ps.executeQuery();
   
            while (rs.next()) {
            	Comment comment = new Comment();
                int id = rs.getInt(1);
                String Cm_from = rs.getString(2);
                String Cm_content=rs.getString(3);
                String Cm_point=rs.getString(4);
                String Cm_useful=rs.getString(5);
                String Cm_userimg=rs.getString(6);
        
                comment.id = id;
                comment.cmFrom = Cm_from;
                comment.cmContent=Cm_content;
                comment.cmPoint=Cm_point;
                comment.cmUseful=Cm_useful;
                comment.cmUserimg=Cm_userimg;
       
                comments.add(comment);
            }
        } catch (SQLException e) {
   
            e.printStackTrace();
        }
        return comments;
    }
   
}
