<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="iolayer.SQLRead"%>

<%@page import="iolayer.SQLRead"%>
<%@include file="include/header.jsp"%>
<%@include file="include/nav_no.jsp"%>
<%
          SQLRead test=new SQLRead();
List<Map<String, Object>> list_hotel = (List<Map<String, Object>>) test.getInfo("select*from hotel_");//对servlet中的数据的接收
List<Map<String, Object>> list_restaurant = (List<Map<String, Object>>) test.getInfo("select*from restaurant_");//对servlet中的数据的接收
List<Map<String, Object>> list_weather = (List<Map<String, Object>>) test.getInfo("select*from weather_");//对servlet中的数据的接收
List<Map<String, Object>> list_comment = (List<Map<String, Object>>) test.getInfo("select*from comment_");//对servlet中的数据的接收
List<Map<String, Object>> list_intros = (List<Map<String, Object>>) test.getInfo("select*from intros_");//对servlet中的数据的接收
List<Map<String, Object>> list_shop = (List<Map<String, Object>>) test.getInfo("select*from shop_");//对servlet中的数据的接收
List<Map<String, Object>> list_travel = (List<Map<String, Object>>) test.getInfo("select*from travel_");//对servlet中的数据的接收
        int comment=list_comment.size();int hotel=list_hotel.size();int intros=list_intros.size();int travel=list_travel.size();
 		int xc=comment+hotel+intros+travel;
 		int zgtqw=list_weather.size();
 		int tuniu=list_shop.size();
 		int dzdp=list_restaurant.size();
 		
               
               List<Integer> htnums = new ArrayList<Integer>();
               List<Integer> weah = new ArrayList<Integer>();
               List<Integer> weal = new ArrayList<Integer>();
               
               
               
               
                int price;
                int sum=0;
                int top4=0,top3=0,top2=0,top1=0;      
         		String temh,teml;
         		
               for (int i = 0; i < list_hotel.size(); i++)
               {	price=Integer.parseInt(list_hotel.get(i).get("hotelPrice").toString());
      				htnums.add(price);
      				 sum=sum+price;     		
      				 if(price<=100){
      				top4++;
      				 }
      				 if(price>100&&price<=200){
      				 top3++;}
      				 if(price>200&&price<=300){
      				 top2++;}
      				if(price>300){
      				top1++;}				 			 	
              }      
              int htzuida = Collections.max(htnums);
              int htzuidi=Collections.min(htnums);
              int htpingjun=sum/list_hotel.size();            
              for (int i = 0; i < list_weather.size(); i++){
              
              String tem=list_weather.get(i).get("Wtem").toString();
              temh=tem.substring(0,2);
              teml=tem.substring(5,7);
              
              weah.add(Integer.parseInt(temh));
              weal.add(Integer.parseInt(teml));
              
              
              }
                         
          %>

        
          <div class="header-text"><span>数据统计</span> <a href="tongjien.jsp">English Version </a></div>
        
     
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div class=hotel_tongji>
        
         <div id="main1" style="width: 600px;height:400px;"></div>
        <div id="main2" style="width: 600px;height:400px;"></div>
        
        </div>
       
        <div id="main3" style="width: 800px;height:600px;"></div>       
        <div id="main4" style="width: 1000px;height:800px;"></div>   

             
        <script type="text/javascript">
        
        
            // 基于准备好的dom，初始化echarts实例
            var myChart1 = echarts.init(document.getElementById('main1'));
			var myChart2 = echarts.init(document.getElementById('main2'));
			var myChart3 = echarts.init(document.getElementById('main3'));
			var myChart4 = echarts.init(document.getElementById('main4'));
            // 指定图表的配置项和数据
            var option1 = {
                title: {
                    text: '酒店信息统计'
                },
                tooltip: {},
                legend: {
                    data:['价格']
                },
                xAxis: {
                    data: ["预订最高价格","预订最低价格","预订平均价格"]
                },
                yAxis: {},
                series: [{
                    name: '价格',
                    type: 'bar',
                    data: [<%=htzuida%>,<%=htzuidi%>,<%=htpingjun%>]
                }]
            };
            var option2 = {
					        title : {
					        text: '酒店价格预区间',
					        subtext: '来源：携程网',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        orient: 'vertical',
					        left: 'left',
					        data: ['小于100','100~200','200~300','大于300']
					    },
					    series : [
					        {
					            name: '访问来源',
					            type: 'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:[
					                {value:<%=top4%>, name:'小于100'},
					                {value:<%=top3%>, name:'100~200'},
					                {value:<%=top2%>, name:'200~300'},
					                {value:<%=top1%>, name:'大于300'},
					                
					            ],
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
    ]
              
            };
            
            
            var option3 = {
    title: {
        text: '未来一周气温变化',
        subtext: '来源：中国天气网'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['最高气温','最低气温']
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            },
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['<%=list_weather.get(0).get("Wdate")%>',
        	   '<%=list_weather.get(1).get("Wdate")%>',
       		   '<%=list_weather.get(2).get("Wdate")%>',
       		   '<%=list_weather.get(3).get("Wdate")%>',
       		   '<%=list_weather.get(4).get("Wdate")%>',
       		   '<%=list_weather.get(5).get("Wdate")%>',
       		   '<%=list_weather.get(6).get("Wdate")%>']
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value} °C'
        }
    },
    series: [
        {
            name:'最高气温',
            type:'line',
            data:[<%=weah.get(0)%>, <%=weah.get(1)%>, <%=weah.get(2)%>, <%=weah.get(3)%>, <%=weah.get(4)%>, <%=weah.get(5)%>, <%=weah.get(6)%>],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'最低气温',
            type:'line',
            data:[<%=weal.get(0)%>, <%=weal.get(1)%>, <%=weal.get(2)%>, <%=weal.get(3)%>, <%=weal.get(4)%>, <%=weal.get(5)%>, <%=weal.get(6)%>],
           //date:[1,2,3,4,5,6,7],
            markPoint: {
                data: [
                    {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                ]
            },
            markLine: {
                data: [
                    {type: 'average', name: '平均值'},
                    [{
                        symbol: 'none',
                        x: '90%',
                        yAxis: 'max'
                    }, {
                        symbol: 'circle',
                        label: {
                            normal: {
                                position: 'start',
                                formatter: '最大值'
                            }
                        },
                        type: 'max',
                        name: '最高点'
                    }]
                ]
            }
        }
    ]
};
            



var option4 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:['携程网','途牛网','中国天气网','大众点评网','景区评论','美食','酒店','天气','购物','周边景点','景区介绍']
    },
    series: [
        {
            name:'数据来源',
            type:'pie',
            selectedMode: 'single',
            radius: [0, '30%'],

            label: {
                normal: {
                    position: 'inner'
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:<%=xc%>, name:'携程网', selected:true},
                {value:<%=tuniu%>, name:'途牛网'},
                {value:<%=zgtqw%>, name:'中国天气网'},
                {value:<%=dzdp%>, name:'大众点评网'}
            ]
        },
        {
            name:'访问来源',
            type:'pie',
            radius: ['40%', '55%'],
            label: {
                normal: {
                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                    backgroundColor: '#eee',
                    borderColor: '#aaa',
                    borderWidth: 1,
                    borderRadius: 4,
                    
                    rich: {
                        a: {
                            color: '#999',
                            lineHeight: 22,
                            align: 'center'
                        },
                       
                        hr: {
                            borderColor: '#aaa',
                            width: '100%',
                            borderWidth: 0.5,
                            height: 0
                        },
                        b: {
                            fontSize: 16,
                            lineHeight: 33
                        },
                        per: {
                            color: '#eee',
                            backgroundColor: '#334455',
                            padding: [2, 4],
                            borderRadius: 2
                        }
                    }
                }
            },
            data:[
            
      
                {value:<%=comment%>, name:'景区评论'},
                {value:<%=dzdp%>, name:'美食'},
                {value:<%=hotel%>, name:'酒店'},
                {value:<%=zgtqw%>, name:'天气'},
                {value:<%=tuniu%>, name:'购物'},
                {value:<%=travel%>, name:'周边景点'},
                {value:<%=intros%>, name:'景区介绍'},
          
            ]
        }
    ]
};

            // 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(option1);
              myChart2.setOption(option2);
              myChart3.setOption(option3);
               myChart4.setOption(option4);
  
        </script>


  <%@include file="include/footer.jsp"%>  