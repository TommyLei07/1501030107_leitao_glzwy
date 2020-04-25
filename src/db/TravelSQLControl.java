package db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import model.Travel;

import org.apache.commons.dbutils.QueryRunner;

public class TravelSQLControl {
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
    public static void executeInsert(List<Travel> traveldata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[traveldata.size()][6];
        for ( int i=0; i<params.length; i++ ){
        	 params[i][0] = traveldata.get(i).getTravel_name();
        	 params[i][1] = traveldata.get(i).getTravel_point();
        	 params[i][2] = traveldata.get(i).getTravel_price();
        	 params[i][3] = traveldata.get(i).getTravel_comment();
        	 params[i][4] = traveldata.get(i).getTravel_img();
        	 params[i][5] = traveldata.get(i).getTravel_url();
        	 

          
        }
        qr.update("truncate table travel_");
        
        qr.batch("insert " +"into travel_(travel_name,travel_point,travel_price,travel_comment,travel_img,travel_url)"
                + "values (?,?,?,?,?,?)", params);
        System.out.println("执行购物数据库完毕！"+"成功插入数据："+traveldata.size()+"条");

    }
}
