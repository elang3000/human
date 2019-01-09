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
							<smart:title title="人员信息" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput isNotNull="true" verify="required" labelName="姓名:" value="" name="publicInstitution.name" placeholder="姓名"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							     <smart:textInput type="hidden" id="planState" name="planState" value="0"></smart:textInput>
						         <smart:textInput type="hidden" id="result" name="result" value="1"></smart:textInput>
								<smart:singleSelect isNotNull="true" verify="required" labelName="性别：" initSelectedKey="" display="block" name="publicInstitution.sex.id"  url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
								</smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput name="publicInstitution。birthDate" labelName="出生日期"
									value=""  id="birthDate" placeholder="出生日期"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
						   <%--  <smart:gridColumn colPart="4">
							    <smart:infoShowerLabel infoname="当前单位:" infovalue="${root.name} ${currentUnit.name}"></smart:infoShowerLabel>
							</smart:gridColumn> --%>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" verify="required" name="publicInstitution.nation.id" labelName="民族：" initSelectedKey="${publicInstitution.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="政治面貌：" name="publicInstitution.politics.id" initSelectedKey="${publicInstitution.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput isNotNull="true" verify="required" labelName="身份证:" value="" name="publicInstitution.cardNo" placeholder="身份证"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="健康状况：" name="publicInstitution.health.id" initSelectedKey="" display="block" url="dictquery/sub/code/GBT_2261_3_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="婚姻状况：" name="publicInstitution.marriage.id" initSelectedKey="" display="block" url="dictquery/sub/code/GBT_2261_2_2003" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:continuousSelect isNotNull="true" verify="required" id="nativePlace" labelName="籍贯：" inputName="publicInstitution.nativePlace.id" codeTypeCode="GBT_2260_2007" inputVal="" valType="ID" widthPercent="0.3" isSaveShowName="true" inputShowName="nativePlaceName"/>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
							<smart:title title="轮岗信息" style="margin-top: 5px;"
								color="blue" />
					</smart:gridRow>
					<smart:gridRow>
					       <smart:gridColumn colPart="4">
								<smart:textInput name="alterrotaDate" labelName="任职日期:" value=""  id="alterrotaDate" placeholder="任职日期"></smart:textInput>
							</smart:gridColumn>
							<%-- <smart:gridColumn colPart="4">
									<smart:linkSelect labelName="去向区域：" id="organTreeIdTag" display="block" />
							</smart:gridColumn> --%>
							<%-- <smart:gridColumn colPart="4">
									<smart:linkSelect  labelName="交流单位：" id="organNodeIdTag" display="block" />
							</smart:gridColumn> --%>
							<smart:gridColumn colPart="4">
							    <smart:infoShowerLabel infoname="交流单位:" infovalue="${currentUnit.name}"></smart:infoShowerLabel>
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
								<smart:textInput isNotNull="true" verify="required" labelName="原来任职机构：" placeholder="任职机构名称" name="oldDepartmentName" value="" ></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							    <smart:singleSelect id="postCode" isAddDefaltOption="true" name="postCode.id" labelName="职务名称：" display="block" url="dictquery/sub/code/GBT_12403_1990" initSelectedKey="${post.postCode.id}" verify="required" isNotNull="true" inputShowName="postName"></smart:singleSelect>
						    </smart:gridColumn>
					</smart:gridRow>
					
					
					<smart:gridRow>
					    <smart:gridColumn colPart="4">
							    <smart:singleSelect labelName="人员进入方式:" name="intoWay.id" initSelectedKey="${publicInstitution.intoWay.id}" display="block" url="dictquery/sub/code/SY100" isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="8">
								<smart:continuousSelect id="attribute" labelName="职务属性：" inputName="attribute.id" codeTypeCode="DM049" inputVal="${post.attribute.id}" valType="ID" widthPercent="0.33333"  isNotNull="true"/>
						</smart:gridColumn>
						
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
								<smart:textInput labelName="备注:" value="" name="remark" placeholder="备注"></smart:textInput>
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
			
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
		form.on('submit(submit)', function (data) {//表单提交
				var params=data.field;
				params.result="1";
				var url="instflow/alternatingrotation/registerAlternatingRotation";
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