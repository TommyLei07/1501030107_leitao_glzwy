package db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import model.IntroS;

import org.apache.commons.dbutils.QueryRunner;

public class IntroSSQLControl {
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
    public static void executeInsert(List<IntroS> introsdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[introsdata.size()][6];
        for ( int i=0; i<params.length; i++ ){
        	 params[i][0] = introsdata.get(i).getlocation();
             params[i][1] = introsdata.get(i).gettype();
             params[i][2] = introsdata.get(i).getfuntime();
             params[i][3] = introsdata.get(i).getopentime();
             params[i][4] = introsdata.get(i).getticket();
             params[i][5] = introsdata.get(i).getintrol();
        }
        qr.update("truncate table intros_");
        
        qr.batch("insert " +"into intros_(location,type,funtime,opentime,ticket,introl)"
                + "values (?,?,?,?,?,?)", params);
        System.out.println("执行简介数据库完毕！"+"成功插入数据："+introsdata.size()+"条");

    }
}
