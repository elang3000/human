<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请--拟录用汇总" />
<!-- 录用_拟录用汇总 -->
<script type="text/javascript">
	var tabNum=2;
</script>
<style type="text/css">
	.labelwidth{
		witdh:50px;
	}
	.layui-form-label{
		width:80px;
	}
	.layui-input-block{
		margin-left:110px;
	}
</style>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员录用" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<%@include file="modual/draftHeadTab.jsp" %>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:date  labelName="年份" display="block" id="yearTip" placeholder="年份" name="yearTip" value="${yearTip}"></smart:date>
								</smart:gridColumn>
								<smart:gridColumn colPart="4" colOffset="1">
									<smart:singleSelect   name="servantType" display="block" labelName="人员类型"
											data="[{'key':'-1','value':'请选择人员类型'},{'key':'1','value':'公务员'},{'key':'2','value':'参公人员'}]"></smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
								<smart:textInput labelName="姓名" display="block" name="name"
									placeholder="姓名"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4" colOffset="1">
								<smart:textInput labelName="身份证" display="block" name="cardNo"
									placeholder="身份证"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="2" >
								<smart:buttonGroup container="true">
								<smart:button theme="normal" size="sm" method="search"
									title="查询">
									<smart:icon icon="search"></smart:icon>
								</smart:button>
									<smart:button size="sm" method="add" title="汇总上报提交">
										<smart:icon icon="plus">&nbsp;汇总上报提交</smart:icon>
									</smart:button>
<%-- 									<smart:button  theme="warm" size="sm" method="batchCancel" title="批量取消">
										<smart:icon icon="reply">&nbsp;批量取消</smart:icon>
									</smart:button> --%>
<%-- 									<smart:button  theme="danger" size="sm" method="add" title="录用总结上报">
										<smart:icon icon="check">&nbsp;录用总结上报</smart:icon>
									</smart:button> --%>
								</smart:buttonGroup>
							</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/draftServantSummary/collectPage"
							height="full-215" text="未获取到数据！" limits="10,50,100,1000">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width="150" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="cardNo" width="200" sort="false">身份证</smart:tableItem>
								<smart:tableItem field="draftUnitName" width="150" sort="true">录用单位</smart:tableItem>
								<smart:tableItem field="draftDeptName" width="150" sort="true">录用部门</smart:tableItem>
								<smart:tableItem field="yearTip" width="150" sort="true">年度</smart:tableItem>
								<smart:tableItem field="sex" width="150" sort="false">性别</smart:tableItem>
								<smart:tableItem field="servantType" width="118" sort="true">人员类型</smart:tableItem>
								<smart:tableItem field="employComment" width="200" sort="false">录用鉴定评语</smart:tableItem>
								<smart:tableItem field="employSituation" width="80" sort="false">录用情况</smart:tableItem>
								<smart:tableItem field="employResult" width="80" sort="false">录用标识</smart:tableItem>
								<smart:tableItem align="center" width="150"  fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
<%-- 								<smart:tableToolBtn theme="danger" event="cancel" title="取消汇总">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn> --%>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card> 
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		var ids =new Array();//存放选中复选框的id
		var table_data=new Array();
		var employSituationArr = new Array();//存放选中数据的录用情况
		var table_data2=new Array();
		<smart:dateRender id="yearTip" type="year"/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
				sort="true" rowEdit="true">
				edit : function(data) {
				   smart.show({
						title : '编辑录用信息',
						size : 'full',
						url : "ofcflow/draftServant/employInfoEditPage?id="+data.data.id,
						scrollbar : false
					});
				},
				cancel:function(data){
					smart.confirm({
						title:'取消汇总',
						message:'确定取消汇总？',
						url:'ofcflow/draftServantSummary/cancelCollectById',
						params : {"id":data.data.id},
						callback : function(data){
							var params = utils.json($('.layui-form'));
							table.reload('navigationList', {
								where : params,
								page : {
									curr : 1
								}
							});
						}
					});
				}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
				search : function() {
					var params = utils.json($('.layui-form'));
					table.reload('navigationList', {
						where : params,
						page : {
							curr : 1
						}
					});
				},
				add : function() {
					if(ids.length == 0){
			    		smart.message({
							message : "请选择汇总上报数据！"
							,type : 'W' //S保存  I问号  W感叹号 E错误
						});
			    	}else{ 
						for(var i=1;i<employSituationArr.length;i++){
							if(employSituationArr[0]!=employSituationArr[i]){
								smart.message({
									message : "请选择录用情况一致的人员上报！"
									,type : 'W' //S保存  I问号  W感叹号 E错误
								});
								return;
							}
						}
			    		smart.show({
							title : '汇总上报',
							size : 'full',
							url : "ofcflow/draftServantSummary/draftServantReport",
							params:{"ids":ids.join(",")},
							scrollbar : false
						});
					}
				},
				batchCancel: function(data) {
					if(ids.length == 0){
			    		smart.message({
							message : "请选择取消汇总数据！"
							,type : 'W' //S保存  I问号  W感叹号 E错误
						});
			    	}else{
						smart.confirm({
							title:'取消汇总',
							message:'确定批量取消汇总？',
							url:'ofcflow/draftServantSummary/cancelCollectById',
							params : {"id":ids.join(",")},
							callback : function(data){
								var params = utils.json($('.layui-form'));
								table.reload('navigationList', {
									where : params,
									page : {
										curr : 1
									}
								});
							}
						});
					}
				}
		 </smart:buttonScriptAction>
 		 //复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
        table.on('checkbox(navigationList)', function (obj) {
           table_data.splice(0,table_data.length);//清空数组 
           var checkStatus = table.checkStatus('navigationList').data;
           for(var i=0;i<checkStatus.length;i++){
                 var da=checkStatus[i];
			     table_data.push(da.id);
           }
           ids=table_data;
        });
	    
		$(".layui-tab-title li").removeClass("layui-this");
		$(".layui-tab-title li:eq("+tabNum+")").addClass("layui-this");
	</smart:scriptHead>
</smart:body>
</html>