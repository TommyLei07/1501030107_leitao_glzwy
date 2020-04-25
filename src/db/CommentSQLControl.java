package db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Comment;


import org.apache.commons.dbutils.QueryRunner;

public class CommentSQLControl {
	 //根据自己的数据库地址修改
    static DataSource ds = SpiderDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/1501030107雷涛glylzwy_2");
    static QueryRunner qr = new QueryRunner(ds);
    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //第二类数据库操作方法
    public static void executeInsert(List<Comment> commentdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[commentdata.size()][5];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = commentdata.get(i).getCmFrom();
            params[i][1] = commentdata.get(i).getCmContent();
            params[i][2] = commentdata.get(i).getCmPoint();
            params[i][3] = commentdata.get(i).getCmUseful();
            params[i][4] = commentdata.get(i).getCmUserimg();
            
        }     
         qr.update("truncate table comment_");
     
        qr.batch("insert " +"into comment_(Cm_from,Cm_content,Cm_point,Cm_useful,Cm_userimg)"
                + "values (?,?,?,?,?)", params);
        System.out.println("执行评论数据库完毕！"+"成功插入数据："+commentdata.size()+"条");
    }
}
