<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="处分信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="socialWorker/punish/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${socialWorker.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${punish.id }"></smart:textInput>
										<smart:textInput type="hidden" id = "socialWorkerId" name="socialWorker.id" value="${socialWorker.id }"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								 <%-- <smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="category" labelName="奖惩类别：" inputName="category.id" codeTypeCode="DM019" inputVal="${punish.category.id}" valType="ID" widthPercent="0.5" verify="required" isNotNull="true"/>
									</smart:gridColumn>
								</smart:gridRow>  --%>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="punishCode" labelName="惩戒名称：" inputName="punishCode.id" codeTypeCode="GBT_8563_3_2005" inputVal="${punish.punishCode.id}" valType="ID" widthPercent="0.3333" verify="required" isNotNull="true"  isSaveShowName="true" inputShowName="punishName" allOrLast="last"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="处分文件号：" name="punishNo" value="${punish.punishNo }" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="批准单位：" placeholder="惩戒批准单位名称" name="punishApprovalUnitName" value="${punish.punishApprovalUnitName }"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="批准单位级别：" name="punishApprovalUnitLevel.id" display="block" url="dictquery/sub/code/DM141" isAddDefaltOption="true" initSelectedKey="${punish.punishApprovalUnitLevel.id }"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="批准日期 ：" display="block" name="punishApprovalDate" id="punishApprovalDate" value="${punish.punishApprovalDate}" verify="required" isNotNull="true"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="处分原因：" name="punishReason.id" display="block" url="dictquery/sub/code/DM021_PUNISH" isAddDefaltOption="true" initSelectedKey="${punish.punishReason.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="处分说明：" name="punishDescription" value="${punish.punishDescription}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="解除日期：" display="block" name="punishRevokeDate" id="punishRevokeDate" value="${punish.punishRevokeDate}" verify="required" isNotNull="true"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="处分标志：" name="punishIdentification.id" display="block" url="dictquery/sub/code/DM063" isAddDefaltOption="true" initSelectedKey="${punish.punishIdentification.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否受处分：" name="punishmentIdentification.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${punish.punishmentIdentification.id }"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
						    <smart:button id="save" other="lay-submit" size="sm" title="保存" theme="normal">
								<smart:icon icon="check"></smart:icon>&nbsp;保存
							</smart:button>
							<smart:button size="sm" type="reset" title="重新填写">
								<smart:icon icon="refresh"></smart:icon>&nbsp;重新填写
						    </smart:button>
						     <smart:button size="sm" method="goBack" title="返回" theme="warm">
								<smart:icon icon="reply"></smart:icon>&nbsp;返回
							</smart:button>
						 </smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="punishApprovalDate"/>
		<smart:dateRender id="punishRevokeDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('punishNavigationList');
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