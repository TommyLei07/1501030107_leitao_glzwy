<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%
        String driverClass="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/test";
        String user = "root";
        String password = "admin";
        Connection conn;
        PreparedStatement pst = null;
        try{
            Class.forName(driverClass).newInstance();
            conn = DriverManager.getConnection(url,user,password);
            String sql = "select * from book";
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
             ResultSetMetaData rm = rs.getMetaData();//获取数据库中字段的名称、字段的值和属性
            
             List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();//存放数据库中的数据
	    	while (rs.next()) {//一个遍历数据中的数据，直到字段对应的值为null为止
	        Map<String, Object> m = new HashMap<String, Object>();//使用Map的键值对，来对数据库中的字段和字段对应的值进行存储
	        for (int i = 1; i <= rm.getColumnCount(); i++) {//rm.getColumnCount()是字段的个数
	            m.put(rm.getColumnName(i), rs.getObject(i));//rm.getColumnName(i)遍历的是字段的名称，rs.getObject(i)遍历是字段对应的值
	        }
	        list.add(m);//把Map集合的键值存放到List集合中
	    }
	    rs.close();//对用的完的资源进行关闭
	    stmt.close();
	    conn.close();           
     
                %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
 <body>
   <table border="1" align="center">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>author</th>
        <th>publishing</th>
        <th>price</th>
        <th>data</th>
    </tr>

        <tr>
            <td><%=list.get(2).get("id")%></td>
        <td><%=list.get(2).get("bookname")%></td>
        <td><%=list.get(2).get("writer")%></td>
        <td><%=list.get(2).get("publishing")%></td>
        <td><%=list.get(2).get("price")%></td>
        <td><%=list.get(2).get("date")%></td>
        </tr>
        <%
            
        }catch(Exception e){
            e.printStackTrace();
        }
        %>
       <tr>
        <td colspan="6" align="right">yes</td>
      </tr>
        
</table>
  </body>
</html>
