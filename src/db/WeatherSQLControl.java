package db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import model.Weather;

import org.apache.commons.dbutils.QueryRunner;

public class WeatherSQLControl {
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
    public static void executeInsert(List<Weather> weatherdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[weatherdata.size()][4];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = weatherdata.get(i).getWdate();
            params[i][1] = weatherdata.get(i).getWwea();
            params[i][2] = weatherdata.get(i).getWtem();
            params[i][3] = weatherdata.get(i).getWwin();      
        }     
        
        qr.update("truncate table weather_");
        qr.batch("insert " +"into weather_(Wdate,Wwea,Wtem,Wwin)"
                + "values (?,?,?,?)", params);
        System.out.println("执行天气数据库完毕！"+"成功插入数据："+weatherdata.size()+"条");
    }
}
