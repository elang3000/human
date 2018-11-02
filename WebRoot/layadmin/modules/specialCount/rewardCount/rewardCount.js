;layui.define(function(e){
        layui.use(["carousel","echarts"],function(){
            var $ = layui.$,
                jquery = layui.jquery,
                carousel = layui.carousel,
                echarts = layui.echarts,
                echartOptions = [];
            
            var departId = $('#departId').val();
            // 指定图表的配置项和数据
            var option = {
        			title:{
        				text:"单位奖励分布统计",
        				textStyle:{
        					fontSize:14
        				}
        			},
        			color: ['#006699', '#4cabce'],
        			url:'analysis/specialCount/reward/echarts?departId='+departId,
        			 tooltip: {
        			        trigger: 'axis',
        			        formatter: '{b0}<br/>{a0}:{c0}人<br/>{a1}:{c1}%',
        			        axisPointer: {
        			            type: 'shadow'
        			        }
        			    },
        		    legend: {
        		    	data: ['人数', '占比']
        		    },
        		    calculable: true,
        		    xAxis: [
        		        {
        		            type: 'category',
        		            axisTick: {show: false},
        		            data: []
        		        }
        		    ],
        		    yAxis: [
        		        {
        		            type: 'value'
        		        }
        		    ],
        		    series: [
        		        {
        		            name: '人数',
        		            type: 'bar',
        		            barGap: 0,
        		            data: []
        		        },
        		        {
        		            name: '占比',
        		            type: 'bar',
        		            data: []
        		        }
        		    ]
        		};

            // 使用刚指定的配置项和数据显示图表。
            var myChart = echarts.init(document.getElementById('chart1'));
            jquery.get(option.url,function(result){
            	debugger;
            	option.xAxis[0].data = result.xAxis;
            	option.series[0].data = result.series0;
            	option.series[1].data = result.series1;
				myChart.setOption(option);
			});
        }),
        e("bizConsole", {

        })
});