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
							<smart:title title="交流详情" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
							    <smart:textInput type="hidden" id="planState" name="planState" value="0"></smart:textInput>
							    <smart:textInput type="hidden" id="result" name="result" ></smart:textInput>
							    <smart:textInput type="hidden" id="pid" name="pid" value="${publicInstitution.id}"></smart:textInput>
							    <smart:textInput type="hidden" id="rootId" name="rootId" value="${root.id}"></smart:textInput>
							    <smart:textInput type="hidden" id="currentUnitId" name="currentUnitId" value="${currentUnit.id}"></smart:textInput>
								<smart:infoShowerLabel infoname="用户名" infovalue="${publicInstitution.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="性别：" initSelectedKey="${publicInstitution.sex.id}" display="block" name="sex.id"  url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
								</smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput name="birthDate" labelName="出生日期" value="${publicInstitution.birthDate}"  id="birthDate" placeholder="出生日期"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
						    <smart:gridColumn colPart="4">
							    <smart:infoShowerLabel infoname="当前单位:" infovalue="${root.name} ${currentUnit.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" verify="required" name="nation.id" labelName="民族：" initSelectedKey="${publicInstitution.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="政治面貌：" name="politics.id" initSelectedKey="${publicInstitution.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="健康状况：" name="health.id" initSelectedKey="${publicInstitution.health.id}" display="block" url="dictquery/sub/code/GBT_2261_3_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="婚姻状况：" name="marriage.id" initSelectedKey="${publicInstitution.marriage.id}" display="block" url="dictquery/sub/code/GBT_2261_2_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="身份证:" value="${publicInstitution.cardNo}" name="cardNo" placeholder="身份证"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
							<smart:gridColumn colPart="4">
 							     <smart:infoShowerLabel infoname="籍贯" infovalue="${publicInstitution.nativePlaceName}"></smart:infoShowerLabel>
 					         </smart:gridColumn>
							<smart:gridColumn colPart="4">
							    <smart:singleSelect labelName="人员进入方式:" name="intoWay.id" initSelectedKey="${publicInstitution.intoWay.id}" display="block" url="dictquery/sub/code/SY100" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="当前单位" infovalue="${currentUnit.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
					       <smart:gridColumn colPart="4">
								<smart:textInput name="alterrotaDate" labelName="任职日期:" value=""  id="alterrotaDate" placeholder="任职日期"></smart:textInput>
							</smart:gridColumn>
							<%-- <smart:gridColumn colPart="4">
									<smart:linkSelect labelName="去向区域：" id="organTreeIdTag" display="block" />
							</smart:gridColumn> --%>
							<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="交流单位：" id="organNodeIdTag" display="block" />
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isAddDefaltOption="true" name="communicationSign.id" labelName="交流标识：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.communicationSign.id}"></smart:singleSelect>
							</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
							 <smart:gridColumn colPart="4">
								<smart:textInput labelName="变动原因:" value="" name="alterrotaReason" placeholder="变动原因"></smart:textInput>
							 </smart:gridColumn>
							 
							 <smart:gridColumn colPart="4">
								<smart:textInput labelName="任职机构：" placeholder="任职机构名称" name="tenureName" value="${post.tenureName}" ></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							    <smart:singleSelect id="postCode" isAddDefaltOption="true" name="postCode.id" labelName="职务名称：" display="block" url="dictquery/sub/code/GBT_12403_1990" initSelectedKey="${post.postCode.id}" verify="required" isNotNull="true" inputShowName="postName"></smart:singleSelect>
						    </smart:gridColumn>
					</smart:gridRow>
					
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
								<smart:textInput labelName="备注:" value="" name="remark" placeholder="备注"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="8">
								<smart:continuousSelect id="attribute" labelName="职务属性：" inputName="attribute.id" codeTypeCode="DM049" inputVal="${post.attribute.id}" valType="ID" widthPercent="0.33333"  isNotNull="true"/>
						</smart:gridColumn>
						
					</smart:gridRow>
					
					<smart:gridRow>
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
		<smart:initLinkSelect id="organNodeIdTag" name="organNodeId" tips="请选择所属单位" selected="${organNodeId}" url="system/organ/node/query" params="{organTreeId:'394e21fa-1eb6-42ee-ba32-50655fa16517'}" />
		
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