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
					<smart:breadcrumbNavMenuItem iname="申请交流轮岗" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="申请信息上报" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
							     <smart:textInput type="hidden" id="result" name="result" ></smart:textInput>
							     <smart:textInput type="hidden" id="pid" name="pid" value="${alternatingRotation.publicInstitution.id}"></smart:textInput>
							     <smart:textInput type="hidden" id="pid" name="id" value="${alternatingRotation.id}"></smart:textInput>
<%-- 								<smart:infoShowerLabel infoname="用户名" infovalue="${alternatingRotation.publicInstitution.name}"></smart:infoShowerLabel>
 --%>								
 									<smart:textInput labelName="用户名:" value="${alternatingRotation.publicInstitution.name}" name="name" placeholder="用户名"></smart:textInput>
								
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="性别：" initSelectedKey="${alternatingRotation.publicInstitution.sex.id}" display="block" name="sex.id"  url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
								</smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput name="birthDate" labelName="出生日期" value="${alternatingRotation.publicInstitution.birthDate}"  id="birthDate" placeholder="出生日期"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
						    <smart:gridColumn colPart="4">
<%-- 							    <smart:infoShowerLabel infoname="当前单位:" infovalue="${alternatingRotation.oldOrgan.name}"></smart:infoShowerLabel>
 --%>								<smart:textInput labelName="当前单位:" value="${alternatingRotation.oldOrgan.name }" name="name" placeholder="当前单位:"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" verify="required" name="nation.id" labelName="民族：" initSelectedKey="${alternatingRotation.publicInstitution.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="政治面貌：" name="politics.id" initSelectedKey="${alternatingRotation.publicInstitution.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="健康状况：" name="health.id" initSelectedKey="${alternatingRotation.publicInstitution.health.id}" display="block" url="dictquery/sub/code/GBT_2261_3_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="婚姻状况：" name="marriage.id" initSelectedKey="${alternatingRotation.publicInstitution.marriage.id}" display="block" url="dictquery/sub/code/GBT_2261_2_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="身份证:" value="${alternatingRotation.publicInstitution.cardNo}" name="cardNo" placeholder="身份证"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
							<smart:gridColumn colPart="4">
<%--  							     <smart:infoShowerLabel infoname="籍贯" infovalue="${alternatingRotation.publicInstitution.nativePlaceName}"></smart:infoShowerLabel>
 --%> 					        	<smart:textInput labelName="籍贯:" value="${alternatingRotation.publicInstitution.nativePlaceName}" name="nativePlaceName" placeholder="籍贯:"></smart:textInput>
 					         </smart:gridColumn>
							<smart:gridColumn colPart="4">
							    <smart:singleSelect labelName="人员进入方式:" name="intoWay.id" initSelectedKey="${alternatingRotation.publicInstitution.intoWay.id}" display="block" url="dictquery/sub/code/SY100" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							    <smart:singleSelect id="postCode" isAddDefaltOption="true" name="postCode.id" initSelectedKey="${alternatingRotation.postCode.id}" labelName="职务名称：" display="block" url="dictquery/sub/code/GBT_12403_1990"  verify="required" isNotNull="true" inputShowName="postName"></smart:singleSelect>
						    </smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
					       <smart:gridColumn colPart="4">
								<smart:textInput name="alterrotaDate" labelName="任职日期:" value="${alternatingRotation.alterrotaDate}"  id="alterrotaDate" placeholder="任职日期"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="交流单位：" id="organNodeIdTag" display="block" />
							</smart:gridColumn>
						    <smart:gridColumn colPart="4">
								<smart:singleSelect isAddDefaltOption="true" name="communicationSign.id" initSelectedKey="${alternatingRotation.communicationSign.id}" labelName="交流标识：" display="block" url="dictquery/sub/code/DM215" ></smart:singleSelect>
							</smart:gridColumn> 
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="变动原因:"
								value="${alternatingRotation.alterrotaReason}"
								name="alterrotaReason" placeholder="变动原因"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="8">
						<smart:continuousSelect id="attribute" labelName="职务属性："
								inputName="attribute.id" codeTypeCode="DM049"
								inputVal="${alternatingRotation.attribute.id}" valType="ID"
								widthPercent="0.33333" isNotNull="true" />
						</smart:gridColumn>

					</smart:gridRow>
					
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
								<smart:textInput labelName="审批意见:" name="opinion" placeholder="审批意见"></smart:textInput>
					    </smart:gridColumn>
					</smart:gridRow>
					<smart:line color="blue" />

					<smart:gridRow>
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
											<c:if
												test="${status.index != 0  && fn:length(records) != status.index +1}">
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
					</smart:gridRow>


					<smart:gridRow>
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
					</smart:gridRow>
				</smart:form>
				
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	
						
	<smart:scriptHead models="table,form,layer,element,laydate,linkSelect">
		<smart:utils/>
		<smart:dateRender id="birthDate" />
		<smart:dateRender id="workDate" />
		<smart:dateRender id="alterrotaDate" />
		<smart:continuousSelectAction/>
		var linkOrganNodeSelect = function(value) {
			var params = {};
			params.organTreeId = value;
			organNodeIdTag.refresh(params);
		}
		<%-- <smart:initLinkSelect id="organTreeIdTag" name="organTreeId" tips="请选择所属区域" selected="${organTreeId}" url="system/organ/tree/query" linkFunction="linkOrganNodeSelect" /> --%>
		<smart:initLinkSelect id="organNodeIdTag" name="organNodeId" tips="请选择所属单位" selected="${alternatingRotation.alterRotaOrgan.id}" url="system/organ/node/query" params="{organTreeId:'394e21fa-1eb6-42ee-ba32-50655fa16517'}" />
		
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'instflow/alternatingrotation/registerAlternatingRotation',
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
					url:'instflow/inforegister/operationRegister',
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