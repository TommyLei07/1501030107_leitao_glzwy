package db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import model.Restaurant;

import org.apache.commons.dbutils.QueryRunner;

public class RestaurantSQLControl {
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
    public static void executeInsert(List<Restaurant> restaurantdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[restaurantdata.size()][6];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = restaurantdata.get(i).getRestName();
            params[i][1] = restaurantdata.get(i).getRestPoint();
            params[i][2] = restaurantdata.get(i).getRestLocation();
            params[i][3] = restaurantdata.get(i).getRestPrice();
            params[i][4] = restaurantdata.get(i).getRestComment();
            params[i][5] = restaurantdata.get(i).getRestImg();
            
        }     
      //  qr.update("truncate table restaurant_");
        qr.batch("insert " +"into restaurant_ (RestName,RestPoint,Restlocationt,Restprice,Restcomment,RestImg)"
                + "values (?,?,?,?,?,?)", params);
      //  System.out.println("执行美食数据库完毕！"+"成功插入数据："+restaurantdata.size()+"条");
    }
}
