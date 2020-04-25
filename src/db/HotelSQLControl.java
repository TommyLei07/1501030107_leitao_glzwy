package db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Hotel;


import org.apache.commons.dbutils.QueryRunner;

public class HotelSQLControl {

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
    public static void executeInsert(List<Hotel> hoteldata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[hoteldata.size()][7];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = hoteldata.get(i).gethotelName();
            params[i][1] = hoteldata.get(i).gethotelDesc();
            params[i][2] = hoteldata.get(i).gethotelPoint();
            params[i][3] = hoteldata.get(i).gethoteljudgement_score();
            params[i][4] = hoteldata.get(i).gethoteljudgement();
            params[i][5] = hoteldata.get(i).gethotelImg();
            params[i][6] = hoteldata.get(i).gethotelPrice();
        }
      
        
        qr.batch("insert into hotel_ (hotelname,hoteldesc,hotelpoint,hoteljudgement_score,hoteljudgement,hotelImg,hotelPrice)"
                + "values (?,?,?,?,?,?,?)", params);
        System.out.println("执行酒店数据库完毕！"+"成功插入数据："+hoteldata.size()+"条");

    }
}
