package db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import model.Shop;

import org.apache.commons.dbutils.QueryRunner;

public class ShopSQLControl {
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
    public static void executeInsert(List<Shop> shopdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[shopdata.size()][5];
        for ( int i=0; i<params.length; i++ ){
        	 params[i][0] = shopdata.get(i).getShop_name();
             params[i][1] = shopdata.get(i).getShop_location();
             params[i][2] = shopdata.get(i).getShop_recommend();
             params[i][3] = shopdata.get(i).getShop_url();
             params[i][4] = shopdata.get(i).getShop_img();
          
        }
        qr.update("truncate table shop_");
        
        qr.batch("insert " +"into shop_(shop_name,shop_location,shop_recommend,shop_url,shop_img)"
                + "values (?,?,?,?,?)", params);
        System.out.println("执行购物数据库完毕！"+"成功插入数据："+shopdata.size()+"条");

    }
}
