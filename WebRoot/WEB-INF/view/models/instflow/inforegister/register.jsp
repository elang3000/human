<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					<smart:breadcrumbNavMenuItem iname="职位简章"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="人员招录登记" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form action="ofcflow/recruit/employPlan/save">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<%@include file="../inforegister/formation.jsp" %>
						<smart:gridRow>
							<smart:title title="人员招录登记上报" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
						    <smart:gridColumn colPart="4">
						        <smart:textInput type="hidden" id="planState" name="planState" value="0"></smart:textInput>
								<smart:textInput isNotNull="true" verify="required" labelName="姓名:" value="${recruitemployplan.name}" name="name" placeholder="姓名"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" verify="required" labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
								</smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput name="birthDate" labelName="出生日期"
									value=""  id="birthDate" placeholder="出生日期"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
						    <smart:gridColumn colPart="4">
								<smart:singleSelect labelName="健康状况：" name="health.id" initSelectedKey="${servant.health.id}" display="block" url="dictquery/sub/code/GBT_2261_3_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" verify="required" name="nation.id" labelName="民族：" initSelectedKey="${servant.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="政治面貌：" name="politics.id" initSelectedKey="${servant.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput isNotNull="true" verify="required" labelName="身份证:" value="${recruitemployplan.name}" name="cardNo" placeholder="身份证"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="8">
								<smart:continuousSelect verify="required" id="nativePlace" labelName="籍贯：" inputName="nativePlace.id" codeTypeCode="GBT_2260_2007" inputVal="${servant.nativePlace.id}" valType="ID" widthPercent="0.3" isSaveShowName="true" inputShowName="nativePlaceName"/>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
						    <smart:gridColumn colPart="4">
							    <smart:singleSelect labelName="人员进入方式:" name="intoWay.id" initSelectedKey="${servant.marriage.id}" display="block" url="dictquery/sub/code/SY100" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="8">
							   <smart:continuousSelect labelName="职级名称："
															inputName="nowJobLevel.id" codeTypeCode="GBT_12407_2008/9"
															inputVal="${jobLevel.code.id}" valType="ID"
														verify="required" isNotNull="true" widthPercent="0.3"
														isSaveShowName="true"  allOrLast="last" /> 
						   </smart:gridColumn>
							<%-- 
							<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="所在区：" id="organTreeIdTag" display="block" />
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="所在单位：" id="organNodeIdTag" display="block" />
							</smart:gridColumn> 
							--%>
						</smart:gridRow>
						<smart:gridRow>
						   <smart:gridColumn colPart="4">
								<smart:singleSelect labelName="婚姻状况：" name="marriage.id" initSelectedKey="${servant.marriage.id}" display="block" url="dictquery/sub/code/GBT_2261_2_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
						   <smart:gridColumn colPart="4">
								<smart:textInput labelName="备注:" value="${recruitemployplan.remark}" name="personRemark" placeholder="备注"></smart:textInput>
						   </smart:gridColumn>
					    </smart:gridRow>
						<smart:gridRow>
							<smart:line color="blue" />
							<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
								<smart:buttonGroup container="true">
									<smart:button id="submit" other="lay-submit" size="sm" title="提交"
										theme="normal">
										<smart:icon icon="check">&nbsp;提交</smart:icon>
									</smart:button>
									<%-- <smart:button id="save" other="lay-submit" size="sm" title="暂存"
										theme="default">
										<smart:icon icon="plus">&nbsp;暂存</smart:icon>
									</smart:button> --%>
									<smart:button theme="warm" size="sm" method="goBack" title="返回">
										<smart:icon icon="reply">&nbsp;返回</smart:icon>
									</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate,linkSelect">
		<smart:utils/>
		<smart:dateRender id="birthDate" />
		<smart:dateRender id="workDate" />
		
		var linkOrganNodeSelect = function(value) {
			var params = {};
			params.organTreeId = value;
			organNodeIdTag.refresh(params);
		}
		<%--
		 <smart:initLinkSelect id="organTreeIdTag" name="organTreeId" tips="请选择所属区域" selected="${organTreeId}" url="system/organ/tree/query" linkFunction="linkOrganNodeSelect" />
		<smart:initLinkSelect id="organNodeIdTag" name="organNodeId" tips="请选择所属单位" selected="${organNodeId}" url="system/organ/node/query" params="{organTreeId:'${organTreeId}'}" /> 
		--%>
		
		<smart:continuousSelectAction/>
		
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				window.location.href='instflow/inforegister/index';
			}
		 </smart:buttonScriptAction>
	 		form.on('submit(save)', function (data) {//表单保存
				var params=data.field;
				var url=data.form.action;
				$.post(url,params,function(result){
					if(result.success){//保存成功
						layer.alert(
		                result.message, 
		                {icon: 1,closeBtn: 1 },
		                function () {
							parent.layui.table.reload('navigationList');
		                	var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
		                });
					}else{
						layer.alert(
		                result.message, 
		                {icon: 0,closeBtn:0 });
					}
				});
				return false;
			});
	 		form.on('submit(submit)', function (data) {//表单提交
				var params=data.field;
				params.result="1";
				var url="instflow/inforegister/operationRegister";
				$.post(url,params,function(result){
					if(result.success){//保存成功
						layer.alert(
		                result.message, 
		                {icon: 1,closeBtn: 1 },
		                function () {
		                	parent.layui.table.reload('navigationList');
		                	var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
		                });
					}else{
						layer.alert(
		                result.message, 
		                {icon: 0,closeBtn:0 });
					}
				});
				return false;
			});
	</smart:scriptHead>
</smart:body>
</html>