<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var orgId='${orgId}';
</script>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--通用统计" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="通用统计" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>

				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form >
							<smart:gridColumn colPart="3">
								<script type="text/javascript">
                                    function singleChange(data){
                                        layui.use('echarts',function(){
                                            var $ = layui.jquery,echarts = layui.echarts,
                                                echartOptions = [];

                                            var myChart = echarts.init(document.getElementById('chart1'));

											var url="";
                                            if(data.elem[data.elem.selectedIndex].text=="性别"){
                                                url="analysis/generalCount/chartData/sex/${orgId}";
											}else if(data.elem[data.elem.selectedIndex].text=="民族"){
												url="analysis/generalCount/chartData/nation/${orgId}";
											}else if(data.elem[data.elem.selectedIndex].text=="学历"){
                                                url="analysis/generalCount/chartData/education/${orgId}";
											}else if(data.elem[data.elem.selectedIndex].text=="学位"){
                                                url="analysis/generalCount/chartData/degree/${orgId}";
											}else if(data.elem[data.elem.selectedIndex].text=="职务"){
                                                url="analysis/generalCount/chartData/post/${orgId}";
											}else if(data.elem[data.elem.selectedIndex].text=="职级"){
                                                url="analysis/generalCount/chartData/joblevel/${orgId}";
											}
											var selectData=data;
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
                                                    // myChart.setOption(option);
                                                    myChart.setOption({
                                                        title : {
                                                            text: '单位'+selectData.elem[selectData.elem.selectedIndex].text+'统计',
                                                            x:'center'
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
                                                            bottom: 20,
                                                            data: data.nameList
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
                                                        series : [
                                                            {
                                                                name: selectData.elem[selectData.elem.selectedIndex].text,
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




                                        });
                                    }
								</script>
								<smart:singleSelect name="season" id="season" display="inline" changeCallBack="singleChange"
													labelName="统计类型" data="[{'key':'5','value':'职务'},{'key':'1','value':'性别'},{'key':'2','value':'民族'},{'key':'3','value':'学历'},{'key':'4','value':'学位'},{'key':'6','value':'职级'}]"></smart:singleSelect>
							</smart:gridColumn>

						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header">组织机构信息</div>
						<div class="layui-card-body">
							<div id="chart1" style="width: 100%;height: 500px">


							</div>

						</div>
					</div>
				</div>


			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
        var ids =new Array();//存放选中复选框的id
        var table_data=new Array();
        <smart:tableScriptAction tableId="navigationList"
			checkbox="true" sort="true" rowEdit="true">
            view : function(data) {
            smart.show({
            title : '单位详情查看',
            size : 'full',
            url : 'analysis/generalCount/unitPage/'+data.data.id,
            scrollbar : false
            });
            }
        </smart:tableScriptAction>
        var buttonInvokeMethod = {
	        search : function() {
		        var params = utils.json($('#jobChange_searchForm'));
			        table.reload('navigationList', {
			        where : params,
			        page : {
			        curr : 1
			        }
		        });
	        }
        };

        $('#jobChange_searchForm button').on('click', function() {
	        var othis = $(this), method = othis.data('method');
	        buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
        });





    </smart:scriptHead>
	<script>
        layui.config({
            base: 'layadmin/'
        }).extend({
            index: 'lib/index'
        }).use(['index','generalCount/generalCountUnit']);
	</script>
</smart:body>
</html>