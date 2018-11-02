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
	layui.use(["carousel","echarts"],function(){
		var $ = layui.$,
		jquery = layui.jquery,
		carousel = layui.carousel,
		echarts = layui.echarts,
		echartOptions = [],
		cpuTimeout,
		memoryTimeout,
		options = [{
			title:{
				text:"服务器CPU使用情况（每分钟）",
				textStyle:{
					fontSize:14
				}
			},
			tooltip:{
				trigger:"axis"
			},
			url : "main/cpu/query",
			xAxis:[{
				type:"category",
				boundaryGap:false,
				data:[]
			}],
			yAxis:[{
				type:"value",
				axisLabel: {
		            formatter: '{value}%'
		        }
			}],
			series:[{
		    	name:'CPU使用率',
		        type:'line',
		        smooth: true,
		        symbolSize: 0,     
		        showAllSymbol: false,
		        itemStyle: {
	                normal: {
	                    lineStyle: {
		                    width: 2,
		                    type: 'solid',
		                    shadowBlur: 5,
		                    shadowOffsetX: 3,
		                    shadowOffsetY: 3
		                },
						areaStyle: {
							type:"default",
			            }
	                }
	            },
		        data:[]
			}]
		} , {
			title:{
				text:"服务器内存使用情况",
				x:"center",
				textStyle:{
					fontSize:14
				}
			},
			tooltip:{
				trigger:"item",
				formatter:"{a} <br/>{b} : {c}M({d}%)"
			},
			legend:{
				orient:"vertical",
				x:"left",
				data:["已使用交换内存","空闲交换内存","已使用物理内存","空闲物理内存"]
			},
			url:'main/memory/query',
			itemStyle: {
				normal:{
			        labelLine:{
			            show:true
			        }
			    },
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },
			series:[{
				name:"交换内存情况",
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
				data:[]
			},{
				name:"物理内存情况",
				type:'pie',
	            radius: ['40%', '55%'],
	            label: {
	                normal: {
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
				data:[]
			}]
		}],
		renderCpu = function(el, index) {
			jquery.get(el.url,function(result){
				if (el.xAxis[0].data.length >= 60) {
					el.xAxis[0].data.shift();
				} 
				el.xAxis[0].data.push(result.x);
				if (el.series[0].data.length >= 60){
					el.series[0].data.shift();
				}
				el.series[0].data.push(result.y);
				echartOptions[index].setOption(el);
			});
		},
		renderMemory = function(el, index) {
			jquery.get(el.url,function(result) {
				el.series[0].data = [];
				var data = {
					name: result.swap.swapUsed.name,
					value : result.swap.swapUsed.value
				};
				el.series[0].data.push(data);
				data = {
					name: result.swap.swapFree.name,
					value : result.swap.swapFree.value
				};
				el.series[0].data.push(data);
					
				el.series[1].data = [];
				data = {
					name: result.mem.memUsed.name,
					value : result.mem.memUsed.value
				};
				el.series[1].data.push(data);
				data = {
					name: result.mem.memFree.name,
					value : result.mem.memFree.value
				};
				el.series[1].data.push(data);
				echartOptions[index].setOption(el);
			});
		},
		renderDisk = function(el, index) {
			
		},
		node = $("#smart-index-dataview").children("div"),
		render = function(index) {
			if (index == 0) {
				if (!echartOptions[index] ) {
					echartOptions[index] = echarts.init(node[index], layui.echartsTheme);
					renderCpu(options[index], index);
				}
				if (!cpuTimeout) {
					cpuTimeout = window.setInterval(function(){
						renderCpu(options[0], 0);
					}, 30000);
				}
			} else if (index == 1) {
				if (!echartOptions[index] ) {
					echartOptions[index] = echarts.init(node[index], layui.echartsTheme);
					renderMemory(options[index], index);
				}
				if (!memoryTimeout) {
					cpuTimeout = window.setInterval(function(){
						renderMemory(options[1], 1);
					}, 60000);
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
	e("systemConsole", {
		
	})
});