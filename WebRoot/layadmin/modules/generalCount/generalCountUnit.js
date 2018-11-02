;layui.define(function(e){
        layui.use(["carousel","echarts"],function(){
            var $ = layui.$,
                jquery = layui.jquery,
                carousel = layui.carousel,
                echarts = layui.echarts,
                echartOptions = [];
            var myChart = echarts.init(document.getElementById('chart1'));

            // 指定图表的配置项和数据
            var  url="analysis/generalCount/chartData/post/"+orgId;
            $.post(url, { },
                function(data){
                    var ydata = [];
                    for(var i=0;i<data.data.length;i++){
                        if(data.data[i].VALUE!=0){
                            var pdata={};
                            pdata.name=data.data[i].NAME;
                            pdata.value=data.data[i].VALUE;
                            ydata.push(pdata);
                        }
                    }
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption({
                        title : {
                            text: '单位职务统计',
                            x:'center'
                        },
                        toolbox: {
                            show: true,
                            x:'right',
                            y:20,
                            orient:'vertical',
                            feature: {
                                dataView : {show: true, readOnly: false},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            type: 'scroll',
                            orient: 'vertical',
                            x:"left",
                            right: 10,
                            top: 20,
                            bottom: 20,
                            data: data.nameList
                        },
                        series : [
                            {
                                name: '职务',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '50%'],
                                data: ydata,
                                itemStyle: {
                                    normal:{
                                        label: {
                                            formatter: "{b} : {c} ({d}%)"
                                        },
                                    },
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    });
                });

        }),
        e("bizConsole", {

        })
});