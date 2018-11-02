 ;layui.define(function(e){
	 layui.use(["admin","carousel"],function(){
		 var $ = layui.$,
		 carousel = (layui.admin,layui.carousel),
		 element = layui.element,
		 device = layui.device();
		 $(".layadmin-carousel").each(function(){
			 var el = $(this);
			 carousel.render({
				 elem : this,
				 width : "100%",
				 arrow : "none",
				 interval : el.data("interval"),
				 autoplay : el.data("autoplay") === true,
				 trigger : device.ios || device.android ? "click" : "hover",
				 anim : el.data("anim")
			})
		}),
		element.render("progress")
	}),
	layui.use(["element"],function(){
		var $ = layui.$,
		jquery = layui.jquery,
		element = layui.element,
		servant = $("#servantDoing p cite"),
		biz = $("#bizDoing p cite"),
		organ = $("#organDoing p cite"),
		plan = $("#planDoing p cite"),
		renderCount = function () {
			jquery.get("main/doing/counter",function(result) {
				console.log(result);
				var counterList = result.data;
				for (var i = 0; i < result.length; i++) {
					if (i == 0) {
						servant.html(result[i]);
					}
					if (i == 1) {
						biz.html(result[i]);					
					}
					if (i == 2) {
						organ.html(result[i]);
					}
					if (i == 3) {
						plan.html(result[i]);
					}
				}
			});
		};
		renderCount.call();
	}),
	layui.use(["carousel","echarts"],function(){
		var $ = layui.$,
		jquery = layui.jquery,
		carousel = layui.carousel,
		echarts = layui.echarts,
		echartOptions = [],
		options = [{
			title:{
				text:"单位年龄段分布统计",
				textStyle:{
					fontSize:14
				}
			},
			color: ['#006699', '#4cabce'],
			url:'statist/years',
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
		},{
		  title : {
		        text: '单位学历统计',
		        x:'left'
		    },
		    url:'statist/education',
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        type: 'scroll',
		        orient: 'vertical',
		        x:"right",
		        right: 10,
		        top: 20,
		        bottom: 20,
		        data: []
		    },
		    series : [
		        {
		            name: '学历',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '50%'],
		            data: [],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		}],
		renderYears = function(el, index) {
			jquery.get(el.url,function(result){
				el.xAxis[0].data = result.xAxis;
				el.series[0].data = result.series0;
				el.series[1].data = result.series1;
				echartOptions[index].setOption(el);
			});
		},
		renderTopEducation = function(el, index) {
			jquery.get(el.url,function(result) {
				var legendData = [];
			    var seriesData = [];
				for (var i = 0; i < result.length; i++) {
					var data = result[i];
					legendData.push(data.name);
					seriesData.push({
			            name: data.name,
			            value: data.counter
			        });
				}
				el.legend.data = legendData;
				el.series[0].data = seriesData;
				echartOptions[index].setOption(el);
			});
		},
		node = $("#smart-index-dataview").children("div"),
		render = function(index) {
			if (index == 0) {
				if (!echartOptions[index] ) {
					echartOptions[index] = echarts.init(node[index], layui.echartsTheme);
					renderYears(options[index], index);
				}
			} else if (index == 1) {
				if (!echartOptions[index] ) {
					echartOptions[index] = echarts.init(node[index], layui.echartsTheme);
					renderTopEducation(options[index], index);
				}
			} else if (index == 2) {
				
			} else {
				if (!echartOptions[index] ) {
					echartOptions[index] = echarts.init(node[index], layui.echartsTheme);
				}
				echartOptions[index].setOption(options[index]);
			}
			window.onresize = echartOptions[index].resize;
		};
		if (node[0]) {
			render(0);
			var index = 0;
			carousel.on("change(smart-index-dataview)",
				function(el) {
					render(index = el.index)
				}
			),
			layui.admin.on("side", function() {
				setTimeout(function(){
					render(index);
				},300)
			}),
			layui.admin.on("hash(tab)", function() {
				layui.router().path.join("") || r(o)
			})
		}
	}),
	e("bizConsole", {
		
	})
});