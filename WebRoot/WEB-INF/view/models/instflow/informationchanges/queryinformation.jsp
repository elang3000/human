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
					<smart:fromTokenTag />
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="申请信息上报" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput type="hidden" id="result" name="result"></smart:textInput>
								<smart:textInput type="hidden" id="id" name="id"
									value="${informationchange.id}"></smart:textInput>
								<smart:textInput type="hidden" id="pid" name="pid"
									value="${informationchange.publicInstitution.id}"></smart:textInput>
								<%-- <smart:infoShowerLabel infoname="用户名" infovalue="${informationchange.publicInstitution.name}"></smart:infoShowerLabel> --%>

								<smart:textInput otherAttr="disabled" display="block"
									labelName="用户名:"
									value="${informationchange.publicInstitution.name}" name="name"
									placeholder="用户名"></smart:textInput>

							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="性别" infovalue="${informationchange.publicInstitution.sex.name }"></smart:infoShowerLabel> --%>
								<smart:textInput otherAttr="disabled" display="block"
									labelName="性别:"
									value="${informationchange.publicInstitution.sex.name}"
									name="name" placeholder="性别"></smart:textInput>
								<%--  <smart:singleSelect labelName="性别：" initSelectedKey="${informationchange.publicInstitution.sex.id}" display="block" name="sex.id"  url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
								</smart:singleSelect>  --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%--   									<smart:textInput name="birthDate" labelName="出生日期" value="${informationchange.publicInstitution.birthDate}"  id="birthDate" placeholder="出生日期"></smart:textInput>
 --%>
								<smart:textInput otherAttr="disabled" labelName="出生日期："
									name="birthDate" display="block"
									value="${informationchange.publicInstitution.birthDate}"></smart:textInput>

								<%-- <smart:infoShowerLabel infoname="出生日期" infovalue="${informationchange.publicInstitution.birthDate}"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
						</smart:gridRow>


						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<%--  										<smart:textInput labelName="身份证:" value="${informationchange.publicInstitution.cardNo}" name="cardNo" placeholder="身份证"></smart:textInput>
 --%>
								<smart:textInput otherAttr="disabled" labelName="身份证号："
									name="cardNo" display="block"
									value="${informationchange.publicInstitution.cardNoView}"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput otherAttr="disabled" labelName="民族："
									name="nation" display="block"
									value="${informationchange.publicInstitution.nation.name}"></smart:textInput>

								<%-- <smart:infoShowerLabel infoname="民族" infovalue="${informationchange.publicInstitution.nation.name }"></smart:infoShowerLabel> --%>
								<%-- 								<smart:singleSelect isNotNull="true" verify="required" name="nation.id" labelName="民族：" initSelectedKey="${informationchange.publicInstitution.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect>
 --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput otherAttr="disabled" labelName="政治面貌："
									name="politics" display="block"
									value="${informationchange.publicInstitution.politics.name}"></smart:textInput>

								<%-- <smart:infoShowerLabel infoname="政治面貌" infovalue="${informationchange.publicInstitution.politics.name }"></smart:infoShowerLabel> --%>
								<%-- 								<smart:singleSelect labelName="政治面貌：" name="politics.id" initSelectedKey="${informationchange.publicInstitution.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
 --%>
							</smart:gridColumn>
						</smart:gridRow>



						<smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridRow>
										<smart:gridColumn colPart="16">
											<smart:continuousSelect labelName="职级名称：" inputName="code.id"
												codeTypeCode="GBT_12407_2008/9"
												inputVal="${informationchange.code.id}" valType="ID"
												verify="required" isNotNull="true" widthPercent="0.5"
												isSaveShowName="true" inputShowName="name" allOrLast="last" />
										</smart:gridColumn>
									</smart:gridRow>
								</smart:gridColumn>
							</smart:gridRow>

							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:date labelName="职级批准日期：" display="block"
										name="approvalDate" id="approvalDate"
										value="${informationchange.approvalDate}"></smart:date>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:date labelName="职级终止日期：" display="block" name="endDate"
										id="endDate" value="${informationchange.endDate}"></smart:date>
								</smart:gridColumn>
							</smart:gridRow>





							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:singleSelect labelName="现行职级标识："
										name="currentIdentification.id" display="block"
										url="dictquery/sub/code/DM215" isAddDefaltOption="true"
										initSelectedKey="${informationchange.currentIdentification.id }"
										verify="required" isNotNull="true"></smart:singleSelect>
								</smart:gridColumn>

								<smart:gridColumn colPart="6">
									<smart:textInput labelName="职级批准文号：" name="approvalNo"
										value="${informationchange.approvalNo }"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>

							<smart:gridRow>
								<%-- <smart:gridColumn colPart="4">
									<smart:linkSelect labelName="所在单位：" id="organNodeIdTag"
										display="block" />
								</smart:gridColumn> --%>
								 <%-- <smart:gridColumn colPart="4">
							    <smart:infoShowerLabel infoname="当前单位:" infovalue="${informationchange.formerUnit.name}"></smart:infoShowerLabel>
							</smart:gridColumn> --%>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="审批意见:" name="opinion"
										placeholder="审批意见"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>

						</smart:gridRow>
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
															${record.createTime}&nbsp;&nbsp;
															${record.ofcFlowDescription}<br>
														</p>
													</div></li>
											</c:if>
											<c:if
												test="${status.index != 0  && fn:length(records) != status.index +1}">
												<li class="layui-timeline-item"><i
													class="layui-icon layui-timeline-axis">&#xe63f;</i>
													<div class="layui-timeline-content layui-text">
														<h3 class="layui-timeline-title"></h3>
														<p>
															${record.createTime}&nbsp;&nbsp;
															${record.ofcFlowDescription}<br>
														</p>
													</div></li>
											</c:if>
											<c:if test="${fn:length(records) == status.index +1}">
												<li class="layui-timeline-item"><i
													class="layui-icon layui-timeline-axis">&#xe63f;</i>
													<div class="layui-timeline-content layui-text">
														<h3 class="layui-timeline-title"></h3>
														<p>
															${record.createTime}&nbsp;&nbsp;
															${record.ofcFlowDescription}<br>
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
		<smart:utils />
		<smart:dateRender id="birthDate" />
		<smart:dateRender id="workDate" />
		<smart:dateRender id="alterrotaDate" />
		<smart:continuousSelectAction />
		var linkOrganNodeSelect = function(value) {
			var params = {};
			params.organTreeId = value;
			organNodeIdTag.refresh(params);
		}
		<%-- <smart:initLinkSelect id="organTreeIdTag" name="organTreeId" tips="请选择所属区域" selected="${organTreeId}" url="system/organ/tree/query" linkFunction="linkOrganNodeSelect" /> --%>
		<%-- <smart:initLinkSelect id="organNodeIdTag" name="organNodeId"
			tips="请选择所属单位" selected="${informationchange.formerUnit.id}"
			url="system/organ/node/query"
			params="{organTreeId:'394e21fa-1eb6-42ee-ba32-50655fa16517'}" /> --%>

		<smart:buttonScriptAction>
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
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>