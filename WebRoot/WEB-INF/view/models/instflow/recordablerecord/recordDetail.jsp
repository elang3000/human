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
					<smart:breadcrumbNavMenuItem iname="事项办理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="离退人员详情信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="离退备案" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
							    <smart:textInput type="hidden" id="result" name="result" ></smart:textInput>
							    <smart:textInput type="hidden" id="id" name="id" value="${recordablerecord.id}"></smart:textInput>
<%-- 								<smart:infoShowerLabel infoname="用户名" infovalue="${recordablerecord.publicInstitution.name}"></smart:infoShowerLabel>
 --%>							
								<%-- <smart:textInput labelName="用户名:" value="${recordablerecord.publicInstitution.name}" name="name" placeholder="用户名"></smart:textInput> --%>
								<smart:infoShowerLabel infoname="用户名" infovalue="${recordablerecord.publicInstitution.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="性别" infovalue="${recordablerecord.publicInstitution.sex.name }"></smart:infoShowerLabel>
								<%-- <smart:singleSelect labelName="性别：" initSelectedKey="${recordablerecord.publicInstitution.sex.id}" display="block" name="sex.id"  url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
								</smart:singleSelect> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="出生日期" infovalue="${recordablerecord.publicInstitution.birthDate }"></smart:infoShowerLabel>
<%-- 								<smart:textInput name="birthDate" labelName="出生日期" value="${recordablerecord.publicInstitution.birthDate}"  id="birthDate" placeholder="出生日期"></smart:textInput>
 --%>							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="身份证" infovalue="${recordablerecord.publicInstitution.cardNo }"></smart:infoShowerLabel>
<%-- 								<smart:textInput labelName="身份证:" value="${recordablerecord.publicInstitution.cardNo}" name="cardNo" placeholder="身份证"></smart:textInput>
 --%>							</smart:gridColumn>
							 <smart:gridColumn colPart="4">
							 <smart:infoShowerLabel infoname="民族" infovalue="${recordablerecord.publicInstitution.nation.name }"></smart:infoShowerLabel>
<%-- 								<smart:singleSelect isNotNull="true" verify="required" name="nation.id" labelName="民族：" initSelectedKey="${recordablerecord.publicInstitution.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect>
 --%>							 </smart:gridColumn>
							 <smart:gridColumn colPart="4">
							 	<smart:infoShowerLabel infoname="政治面貌" infovalue="${recordablerecord.publicInstitution.politics.name }"></smart:infoShowerLabel>
<%-- 								<smart:singleSelect labelName="政治面貌：" name="politics.id" initSelectedKey="${recordablerecord.publicInstitution.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
 --%>							 </smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="健康状况" infovalue="${recordablerecord.publicInstitution.health.name }"></smart:infoShowerLabel>
<%-- 								<smart:singleSelect labelName="健康状况：" name="health.id" initSelectedKey="${recordablerecord.publicInstitution.health.id}" display="block" url="dictquery/sub/code/GBT_2261_3_2003" isAddDefaltOption="true"></smart:singleSelect>
 --%>							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="婚姻状况" infovalue="${recordablerecord.publicInstitution.marriage.name }"></smart:infoShowerLabel>
								<%-- <smart:singleSelect labelName="婚姻状况：" name="marriage.id" initSelectedKey="${recordablerecord.publicInstitution.marriage.id}" display="block" url="dictquery/sub/code/GBT_2261_2_2003" isAddDefaltOption="true"></smart:singleSelect> --%>
							</smart:gridColumn>
							 <smart:gridColumn colPart="4">
							 	<smart:infoShowerLabel infoname="籍贯" infovalue="${recordablerecord.publicInstitution.nativePlaceName}"></smart:infoShowerLabel>
<%-- 								<smart:continuousSelect verify="required" id="nativePlace" labelName="籍贯：" inputName="nativePlace.id" codeTypeCode="GBT_2260_2007" inputVal="${recordablerecord.publicInstitution.nativePlace.id}" valType="ID" widthPercent="0.3" isSaveShowName="true" inputShowName="nativePlaceName"/>
 --%>							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							 <smart:gridColumn colPart="4">
							 	<smart:infoShowerLabel infoname="离退日期" infovalue="${recordablerecord.recordDate }"></smart:infoShowerLabel>
<%-- 								<smart:textInput name="recordDate.id" labelName="离退日期:" value="${recordablerecord.recordDate }"  id="recordDate" placeholder="离退日期"></smart:textInput>
 --%>							</smart:gridColumn>
							 <smart:gridColumn colPart="4">
							 	<smart:infoShowerLabel infoname="离退备案类型" infovalue="${recordablerecord.recodeWay.name}"></smart:infoShowerLabel>
<%-- 							    <smart:singleSelect labelName="离退备案类型:" name="recodeWay.id" initSelectedKey="${recordablerecord.recodeWay.id }" display="block" url="dictquery/sub/code/SY101" isAddDefaltOption="true"></smart:singleSelect>
 --%>							</smart:gridColumn>
 								 <smart:gridColumn colPart="4">
 					         	<smart:infoShowerLabel infoname="备注" infovalue="${recordablerecord.remark}"></smart:infoShowerLabel>
<%-- 								<smart:textInput labelName="备注:" value="${recordablerecord.remark}" name="remark" placeholder="备注"></smart:textInput>
 --%>							</smart:gridColumn>
							
						</smart:gridRow>
					</smart:gridRow>
					


					<smart:line color="blue" />
					<%-- <smart:gridRow>
						<smart:fieldSet title="业务处理记录" color="blue"
							style="padding-top:1em;width:955px;margin-left:20px;">

							<smart:gridRow>
								<smart:gridColumn colPart="10">
									<ul class="layui-timeline">
										<c:forEach items="${records}" var="record" varStatus="status">
											<c:if test="${status.index == 0}">
												<li class="layui-timeline-item"><i
													class="layui-icon layui-timeline-axis"
													style="color: #d02525;">&#xe756;</i>
													<div class="layui-timeline-content layui-text"
														style="color: #d02525;">
														<h3 class="layui-timeline-title" style="color: #d02525;">
														</h3>
														<p>
															${record.createTime}&nbsp;&nbsp; ${record.ofcFlowDescription}<br>
														</p>
													</div></li>
											</c:if>
											
											<c:if test="${fn:length(records) == status.index +1}">
												<li class="layui-timeline-item"><i
													class="layui-icon layui-timeline-axis">&#xe63f;</i>
													<div class="layui-timeline-content layui-text">
														<h3 class="layui-timeline-title">
														</h3>
														<p>
															${record.createTime}&nbsp;&nbsp; ${record.ofcFlowDescription}<br>
														</p>
													</div></li>
											</c:if>
										</c:forEach>
									</ul>
								</smart:gridColumn>
							</smart:gridRow>

						</smart:fieldSet>
					</smart:gridRow> --%>

					<smart:gridRow>
						<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<smart:button theme="warm" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'instflow/recordablerecord/submitregister',
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
					url:'instflow/recordablerecord/submitregister',
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
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>