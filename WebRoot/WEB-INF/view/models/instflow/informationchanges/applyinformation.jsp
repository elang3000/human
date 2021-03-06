<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--人员信息变更" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="人员信息变更审核" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag />
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="人员信息变更审核" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput type="hidden" id="planState" name="planState"
									value="0"></smart:textInput>
								<smart:textInput type="hidden" id="result" name="result"></smart:textInput>
								<smart:textInput type="hidden" id="pid" name="pid"
									value="${publicinstitution.id}"></smart:textInput>
								<smart:textInput type="hidden" id="rootId" name="rootId"
									value="${root.id}"></smart:textInput>
								<smart:textInput type="hidden" id="currentUnitId"
									name="currentUnitId" value="${currentUnit.id}"></smart:textInput>
								<%-- <smart:textInput otherAttr="disabled" display="block" labelName="用户名:" value="${publicinstitution.name}" name="name" placeholder="用户名"></smart:textInput> --%>
								<%--  <smart:textInput labelName="用户名: " value="${publicinstitution.name}" name="infoChangePublicInstitution.name" placeholder="用户名: "></smart:textInput>  --%>
								<smart:infoShowerLabel infoname="姓名"
									infovalue="${publicinstitution.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="性别"
									infovalue="${publicinstitution.sex.name}"></smart:infoShowerLabel>
								<%-- <smart:singleSelect labelName="性别：" initSelectedKey="${publicinstitution.sex.id}" display="block" name="infoChangePublicInstitution.sex.id"  url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
								</smart:singleSelect> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="出生日期"
									infovalue="${publicinstitution.birthDate}"></smart:infoShowerLabel>
								<%-- <smart:textInput name="infoChangePublicInstitution.birthDate" labelName="出生日期:" value="${publicinstitution.birthDate}"  id="birthDate" placeholder="出生日期:"></smart:textInput> --%>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>

							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="民族"
									infovalue="${publicinstitution.nation.name}"></smart:infoShowerLabel>
								<%-- <smart:singleSelect isNotNull="true" verify="required" name="infoChangePublicInstitution.nation.id" labelName="民族：" initSelectedKey="${publicinstitution.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="政治面貌"
									infovalue="${publicinstitution.politics.name}"></smart:infoShowerLabel>
								<%-- <smart:singleSelect labelName="政治面貌：" name="infoChangePublicInstitution.politics.id" initSelectedKey="${publicinstitution.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect> --%>
							</smart:gridColumn>
							<%-- <smart:gridColumn colPart="4">
								<smart:singleSelect labelName="健康状况：" name="infoChangePublicInstitution.health.id" initSelectedKey="${publicinstitution.health.id}" display="block" url="dictquery/sub/code/GBT_2261_3_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn> --%>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="身份证"
									infovalue="${publicinstitution.cardNoView}"></smart:infoShowerLabel>
								<%-- <smart:textInput labelName="身份证:" value="${publicinstitution.cardNo}" name="infoChangePublicInstitution.cardNo" placeholder="身份证"></smart:textInput> --%>
							</smart:gridColumn>
						</smart:gridRow>

						<!-- ============================================================================================= -->

						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<%-- <smart:infoShowerLabel infoname="姓名" infovalue="${pubinst.name}"></smart:infoShowerLabel> --%>
										<smart:textInput type="hidden" name="id"
											value="${jobLevel.id }"></smart:textInput>
										<smart:textInput type="hidden" name="publicInstitution.id"
											value="${pubinst.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>



								<smart:gridRow>

									<smart:gridColumn colPart="8">
										<smart:gridRow>
											<smart:gridColumn colPart="16">
												<smart:continuousSelect labelName="职级名称："
													inputName="code.id" codeTypeCode="GBT_12407_2008/9"
													inputVal="${jobLevel.code.id}" valType="ID"
													verify="required" isNotNull="true" widthPercent="0.5"
													isSaveShowName="true" inputShowName="name" allOrLast="last" />
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								<%-- <smart:gridColumn colPart="4">
									<smart:linkSelect labelName="所在单位：" id="organNodeIdTag"
										display="block" />
								</smart:gridColumn> --%>
								<%--  <smart:gridColumn colPart="4">
							    <smart:infoShowerLabel infoname="当前单位:" infovalue="${root.name} ${currentUnit.name}"></smart:infoShowerLabel>
							</smart:gridColumn> --%>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:date labelName="职级批准日期：" display="block"
											name="approvalDate" id="approvalDate"
											value="${jobLevel.approvalDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:date labelName="职级终止日期：" display="block" name="endDate"
											id="endDate" value="${jobLevel.endDate}"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:singleSelect labelName="现行职级标识："
											name="currentIdentification.id" display="block"
											url="dictquery/sub/code/DM215" isAddDefaltOption="true"
											initSelectedKey="${jobLevel.currentIdentification.id }"
											verify="required" isNotNull="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="职级批准文号：" name="approvalNo"
											value="${jobLevel.approvalNo }"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:grid>
						</smart:gridColumn>

					</smart:gridRow>


					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<smart:button id="submit" other="lay-submit" size="sm"
									title="提交" theme="normal">
									<smart:icon icon="check">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button id="save" other="lay-submit" size="sm" title="暂存"
									theme="default">
									<smart:icon icon="plus">&nbsp;暂存</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>


					<%-- <smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<smart:button method="pass" size="sm" title="审批通过"
									theme="normal">
									<smart:icon icon="check">&nbsp;审批通过</smart:icon>
								</smart:button>
								<smart:button method="noPass" size="sm" title="审批不通过"
									theme="danger">
									<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow> --%>


				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate,linkSelect">
		<smart:utils />
		<smart:dateRender id="birthDate" />
		<smart:dateRender id="approvalDate" />
		<smart:dateRender id="endDate" />
		<smart:continuousSelectAction />
		
		 
		var linkOrganNodeSelect = function(value) {
			var params = {};
			params.organTreeId = value;
			organNodeIdTag.refresh(params);
		} 
		<%--  <smart:initLinkSelect id="organTreeIdTag" name="organTreeId" tips="请选择所属区域" selected="${organTreeId}" url="system/organ/tree/query" linkFunction="linkOrganNodeSelect" />--%>
		<%-- <smart:initLinkSelect id="organNodeIdTag" name="organNodeId"
			tips="请选择所属单位" selected="${organNodeId}"
			url="system/organ/node/query"
			params="{organTreeId:'394e21fa-1eb6-42ee-ba32-50655fa16517'}" /> --%>


		<smart:buttonScriptAction>
			goBack : function(data) {
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
				window.location.href='instflow/informationchanges/list';
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
				var url="instflow/informationchanges/submitinformation";
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
			
			
			
			
		 <%--   <smart:buttonScriptAction>
			 pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'instflow/informationchanges/submitinformation',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			noPass : function() {
				$("#result").val("0");//审批不通过
				smart.confirm({
					title:'确认审批不通过',
					message:'确认审批不通过吗？',
					url:'instflow/informationchanges/submitinformation',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			goBack : function(data) {
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
				window.location.href='instflow/informationchanges/list';
			}
			
		 </smart:buttonScriptAction>  --%>
	</smart:scriptHead>
</smart:body>
</html>