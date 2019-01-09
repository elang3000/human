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
		createTpl = function(optionData) {
			var html = '<div class="layui-progress" lay-showpercent="true" lay-filter="' + optionData.code + '">'
				html += '<h3>' + optionData.name + '</h3>'
				var p =  Math.round(optionData.doingNum / optionData.allNum * 1000) / 100;
				if (p >= 80) {
					html += '<div class="layui-progress-bar" lay-percent="' + p + '%" style="width:' + p + '%;">';
					html += '<span class="layui-progress-text">' + p +'%</span>'
					html += '</div>';
				} else {
					html += '<div class="layui-progress-bar layui-bg-red" lay-percent="' + p + '%" style="width:' + p + '%;">';
					html += '<span class="layui-progress-text">' + p +'%</span>'
					html += '</div>';
				}
				html += '</div>'
			return html;			
		},
		renderCount = function() {
			var el = $('.layadmin-takerates');
			jquery.get("main/model/doing/counter",function(result) {
				$.each(result, function(key) {
					el.append(createTpl(result[key]));
				});
			});
		}
		renderCount.call();
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
				x:'left'
			},
			url:'statist/years',
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        type: 'scroll',
		        orient: 'horizontal',
		        x:"center",
		        y: 'bottom',
		        data: []
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            dataView : {
		            	show: true, 
		            	readOnly: false
		            },
		            restore : {
		            	show: true
		            },
		            saveAsImage : {
		            	show: true
		            }
		        }
		    },
		    series: [
		    	{
		            name: '年龄统计',
		            type: 'pie',
		            radius: ['20%', '70%'],
		            center: ['50%','45%'],
		            selectedMode: 'single',
		            selectedOffset : 15,
		            voidLabelOverlap: true,
		            hoverAnimation: true,
		            data: [],
		            itemStyle: {
		            	normal :{
		            		label:{
		            			show:true,
		            			position:'outer',
		            			formatter: '{b}：人数[{c}],百分比[{d}%]',
		            		}
		            	},
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
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
		        orient: 'horizontal',
		        x:"center",
		        y: 'bottom',
		        data: []
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            dataView : {
		            	show: true, 
		            	readOnly: false
		            },
		            restore : {
		            	show: true
		            },
		            saveAsImage : {
		            	show: true
		            }
		        }
		    },
		    series : [{
	            name: '学历',
	            type: 'pie',
	            radius: ['20%', '70%'],
	            center: ['50%','45%'],
	            selectedMode: 'single',
	            selectedOffset : 15,
	            voidLabelOverlap: true,
	            hoverAnimation: true,
	            data: [],
	            itemStyle: {
	            	normal :{
	            		label:{
	            			show:true,
	            			position:'outer',
	            			formatter: '{b}：人数[{c}],百分比[{d}%]',
	            		}
	            	},
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }]
		},{
			  title : {
			        text: '毕业学校性质统计',
			        x:'left'
			    },
			    url:'statist/schoolnature',
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        type: 'scroll',
			        orient: 'horizontal',
			        x:"center",
			        y: 'bottom',
			        data: []
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {
			            	show: true, 
			            	readOnly: false
			            },
			            restore : {
			            	show: true
			            },
			            saveAsImage : {
			            	show: true
			            }
			        }
			    },
			    series : [
			        {
			            name: '学校性质',
			            type: 'pie',
			            radius: ['20%', '70%'],
			            center: ['50%','45%'],
			            selectedMode: 'single',
			            selectedOffset : 15,
			            voidLabelOverlap: true,
			            hoverAnimation: true,
			            data: [],
			            itemStyle: {
			            	normal :{
			            		label:{
			            			show:true,
			            			position:'outer',
			            			formatter: '{b}：人数[{c}],百分比[{d}%]',
			            		}
			            	},
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			},{
				  title : {
				        text: '310统计',
				        x:'left'
				    },
				    url:'statist/isshlocal',
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				    	type: 'scroll',
				        orient: 'horizontal',
				        x:"center",
				        y: 'bottom',
				        data: []
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            dataView : {
				            	show: true, 
				            	readOnly: false
				            },
				            restore : {
				            	show: true
				            },
				            saveAsImage : {
				            	show: true
				            }
				        }
				    },
				    series : [
				        {
				            name: '是否310',
				            type: 'pie',
				            radius: ['20%', '70%'],
				            center: ['50%','45%'],
				            selectedMode: 'single',
				            selectedOffset : 15,
				            voidLabelOverlap: true,
				            hoverAnimation: true,
				            data: [],
				            itemStyle: {
				            	normal :{
				            		label:{
				            			show:true,
				            			position:'outer',
				            			formatter: '{b}：人数[{c}],百分比[{d}%]',
				            		}
				            	},
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
		renderSchoolNature = function(el, index) {
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
		renderIsshLocal = function(el, index) {
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
				if (!echartOptions[index] ) {
					echartOptions[index] = echarts.init(node[index], layui.echartsTheme);
					renderSchoolNature(options[index], index);
				}
			} 
			else if (index == 3) {
				if (!echartOptions[index] ) {
					echartOptions[index] = echarts.init(node[index], layui.echartsTheme);
					renderIsshLocal(options[index], index);
				}
			}else {
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