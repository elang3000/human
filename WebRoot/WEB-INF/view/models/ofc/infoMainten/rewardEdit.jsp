<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="奖励信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="ofc/reward/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${reward.id }"></smart:textInput>
										<smart:textInput type="hidden" name="servant.id" value="${servant.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<%-- <smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="category" labelName="奖惩类别：" inputName="category.id" codeTypeCode="DM019" inputVal="${reward.category.id}" valType="ID" widthPercent="0.5" verify="required" isNotNull="true"/>
									</smart:gridColumn>
								</smart:gridRow> --%>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="rewardCode" labelName="奖励名称：" inputName="rewardCode.id" codeTypeCode="GBT_8563_1_2005" inputVal="${reward.rewardCode.id}" valType="ID" widthPercent="0.333" verify="required" isNotNull="true"   isSaveShowName="true" inputShowName="rewardName" allOrLast="last"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="奖励决定原始文件：" shortName="奖励决定文件" name="rewardNo" value="${reward.rewardNo}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="批准单位名称：" shortName="批准单位名称" name="rewardApprovalUnitName" value="${reward.rewardApprovalUnitName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="rewardApprovalUnitLevel.id" shortName="批准单位级别" labelName="批准单位级别：" display="block" url="dictquery/sub/code/DM141" initSelectedKey="${reward.rewardApprovalUnitLevel.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="奖励批准日期：" display="block" name="rewardApprovalDate" id="rewardApprovalDate" value="${reward.rewardApprovalDate}" verify="required" isNotNull="true"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="rewardReason.id" labelName="受奖励原因：" display="block" url="dictquery/sub/code/DM021_REWARD" initSelectedKey="${reward.rewardReason.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="受奖励说明：" name="rewardDescription" value="${reward.rewardDescription}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="honoraryLevel.id" labelName="荣誉称号级别：" display="block" url="dictquery/sub/code/GBT_8563_2_2005" initSelectedKey="${reward.honoraryLevel.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="荣誉称号名称：" name="honoraryName" value="${reward.honoraryName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="荣誉称号授予单位：" shortName="称号授予单位" name="honoraryGrantingUnit" value="${reward.honoraryGrantingUnit}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="奖励撤销日期：" display="block" name="rewardRevokeDate" id="rewardRevokeDate" value="${reward.rewardRevokeDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="rewardRevokeReason.id" labelName="奖励撤销原因：" display="block" url="dictquery/sub/code/DM189" initSelectedKey="${reward.rewardRevokeReason.id}"></smart:singleSelect>
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
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:continuousSelectAction/>
		<smart:dateRender id="rewardApprovalDate"/>
		<smart:dateRender id="rewardRevokeDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('rewardNavigationList');
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