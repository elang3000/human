<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="因公出国政审"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="启动因公出国计划" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm" action="ofcflow/abroadB/saveAbroadServant">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:textInput type="hidden" id="id" name="id" value="${abroadServant.id }"></smart:textInput>
									<smart:textInput type="hidden" id="servantIds" name="servantIds"></smart:textInput>
									<smart:textInput type="hidden" id="createOrganNodeId" name="createOrganNodeId" value="${x.id}"></smart:textInput>
									<smart:textInput type="hidden" id="id" name="organ.id" value="${x.id }"></smart:textInput>
									<smart:textInput type="hidden" id="status" name="status" value="1"></smart:textInput>
									<smart:textInput type="hidden" id="yearPlanId" name="yearPlan.id" value="${abroadYearPlan.id}"></smart:textInput>
									<smart:title title="因公出国信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="出境团组名称" infovalue="${abroadYearPlan.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="出境地区" infovalue="${abroadYearPlan.country}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="停留天数" infovalue="${abroadYearPlan.day}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:title title="因公出国人员列表" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								<smart:gridRow colSpace="5">
									<smart:gridColumn colPart="12" deviceType="md">
										<smart:buttonGroup container="true" align="right">
											<button class="layui-btn layui-btn-sm" type="button"
												data-method="select" title="选择人员">
												<i class="fa fa-search" style=""></i>&nbsp;选择人员
											</button>
											<smart:button method="del" size="sm" title="删除选中人员"
												theme="danger">
												<smart:icon icon="trash">&nbsp;删除选中人员</smart:icon>
											</smart:button>
										</smart:buttonGroup>
										<smart:table id="navigationList"
											url="ofcflow/abroadB/abroadPeopleList?abroadId=${abroadServant.id}&abroadYearId=${abroadYearPlan.id}"
											height="" text="未找到用户数据！" page="true" doneCallBack="fixedCol">
											<tr>
												<smart:tableItem isCheckbox="true">全选</smart:tableItem>
												<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
												<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
												<smart:tableItem field="cardNo" width=".15" sort="true">身份证号</smart:tableItem>
												<smart:tableItem field="isTime" width=".15" sort="false">是否其他批件时间内</smart:tableItem>
												<smart:tableItem field="departName" width=".12" sort="false">单位部门</smart:tableItem>
												<smart:tableItem field="postName" width=".12" sort="false">职务名称</smart:tableItem>
												<smart:tableItem field="postAttributeName" width=".12" sort="false">职务属性</smart:tableItem>
												<smart:tableItem field="jobLevel" width=".12" sort="false">职级名称</smart:tableItem>
												<smart:tableItem align="center" width=".13" fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
											</tr>
											<smart:tableToolBar id="navListToolBar">
												<smart:tableToolBtn theme="warm" event="view" title="查看">
													<smart:icon icon="eye"></smart:icon>
												</smart:tableToolBtn>

												<smart:tableToolBtn theme="danger" event="remove" title="删除">
													<smart:icon icon="trash"></smart:icon>
												</smart:tableToolBtn>
											</smart:tableToolBar>
										</smart:table>
									</smart:gridColumn>
								</smart:gridRow>
					
								<smart:gridRow>
									<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								<smart:gridRow>
								<smart:gridColumn colPart="1">
									<smart:fileUpload accept="file" buttonName="附件" dataName="abroadFtp" auto="false" name="filePathName" size="20000" multiple="true" number="7" data="${abroadServant.abroadFtp}"/>
								</smart:gridColumn>
								
								</smart:gridRow>
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
								<smart:button id="submit" other="lay-submit" size="sm" title="提交"
									theme="normal">
									<smart:icon icon="check">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button id="save" other="lay-submit" size="sm" title="暂存"
									theme="default">
									<smart:icon icon="plus">&nbsp;暂存</smart:icon>
								</smart:button>
								<smart:button theme="primary" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:utils />
		var ids =new Array();//存放选中复选框的id
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				remove : function(data) {
					smart.confirm({
						title:'删除人员信息',
						message:'确认删除人员信息？',
						type:'POST',
						url:'ofcflow/abroadB/deletePeople',
						params : {ids : data.data.id},
						callback : function() {
							layui.table.reload('navigationList');
						}
					});
				}, 
				view : function(data) {
					smart.show({
						title : '公务员信息',
						size : 'full',
						url : 'ofc/main/view?id='+data.data.id,
						scrollbar : false,
					});
				}
		</smart:tableScriptAction>
		<smart:fileUploadUtils/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			},
			select : function() {
			    smart.show({
					title : '选择人员',
					size : 'full',
					url : 'orgInfo/selectServants',
					params : {createOrganNodeId:'${x.id}',ids:$("#servantIds").val(),busClass:'com.wondersgroup.human.bo.ofcflow.AbroadPeople',busCol:'abroadServant.id',busId:'${abroadServant.id}'},
					scrollbar : false,
				});
			},
			del : function() {
					if(ids.length == 0){
			    		smart.message({
							message : "请选择人员！"
							,type : 'W' //S保存  I问号  W感叹号 E错误
						});
			    	}else{ 
			    		smart.confirm({
							title:'删除人员信息',
							message:'确认删除人员信息？',
							type:'POST',
							url:'ofcflow/abroadB/deletePeople',
							params : {ids : ids.join(",")},
							callback : function() {
								ids.splice(0,ids.length);
								layui.table.reload('navigationList');
							}
						});
					}
				}, 
		</smart:buttonScriptAction>
    
		form.on('submit(save)', function (data) {//表单保存
		var params=data.field;
		var url=data.form.action;
		var requestConfig = {};
		requestConfig.url = "ofcflow/abroadB/plan/validate";
		requestConfig.data = {yearPlanId:$("#yearPlanId").val(),servantIds:$("#servantIds").val(),id:$("#id").val()};
		requestConfig.success = function(result){
			if (result.success) {
				smart.confirm({
				title:'确认提交因公出国信息吗',
				message:'确认提交因公出国信息吗？',
				url:url,
				params : params,
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					}
				});
			}
		}
		smart.request(requestConfig);
	});
	
	form.on('submit(submit)', function (data) {//表单保存
		var params=data.field;
		params.result="1";
		var url="ofcflow/abroadB/operationFlow";
		var requestConfig = {};
		requestConfig.url = "ofcflow/abroadB/plan/validate";
		requestConfig.data = {yearPlanId:$("#yearPlanId").val(),servantIds:$("#servantIds").val(),id:$("#id").val()};
		requestConfig.success = function(result){
			if (result.success) {
				smart.confirm({
				title:'确认保存因公出国信息吗',
				message:'确认保存因公出国信息吗？',
				url:url,
				params : params,
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					}
				});
			}
		}
		smart.request(requestConfig);
	});
	
	setInterval(function(){
		    if($('#servantIds').val()!=""){
		    	if($('#servantIds').val()==""){
			 		smart.message({
						message : "请选择需要转任的人员！",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
			 	}
			 	var requestConfig = {};
			 	requestConfig.url = "ofcflow/abroadB/savePeople";
				requestConfig.data = {"id":'${abroadServant.id}',"yearPlan.id":'${abroadYearPlan.id}',"servantIds":$('#servantIds').val()};
	   			$("#servantIds").val("");
				$("#servantNames").val("");
				requestConfig.success = function(result){
					if(result.success){
						parent.layui.table.reload('navigationList');
						window.location.href="ofcflow/abroadB/editAbroadServant?id="+result.data;//新增成功刷新此页面
					}else{
						layer.alert(
		                result.message, 
		                {icon: 0,closeBtn:0 });
					}
				}
				smart.request(requestConfig);
		    }
		    
	},100);
		
	//复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
		Array.prototype.remove = function(val) { 
			var index = this.indexOf(val); 
				if (index > -1) { 
					this.splice(index, 1); 
				}
		};
        table.on('checkbox(navigationList)', function (obj) {
        	var cArr = table.cache.navigationList;//获取当前列表所有数据
        	cArr.forEach(function(value,i){
        		var c = cArr[i];
        		if(c.LAY_CHECKED){//选中状态
        			if(ids.indexOf(c.id)==-1){//如果不存在数组中，则添加
				     	ids.push(c.id);//id
                 	}
        		}else{//未选中状态，从数组中删除
		       		ids.remove(c.id);
        		}
	     	});
        });
	</smart:scriptHead>
</smart:body>
</html>