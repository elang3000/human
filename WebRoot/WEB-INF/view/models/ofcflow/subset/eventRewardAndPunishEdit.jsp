<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="奖惩信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${e.personName}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${e.id}"></smart:textInput>
										<smart:textInput type="hidden" name="personName" value="${e.personName}"></smart:textInput>
										<smart:textInput type="hidden" name="cardNo" value="${e.cardNo}"></smart:textInput>
										<smart:textInput type="hidden" id="rewardName" name="rewardName" value="${e.rewardName}"></smart:textInput>
										<smart:textInput type="hidden" id="rewardApprovalUnitName" name="rewardApprovalUnitName" value="${e.rewardApprovalUnitName}"></smart:textInput>
										
										<smart:textInput type="hidden" id="punishName" name="punishName" value="${e.punishName}"></smart:textInput>
										<smart:textInput type="hidden" id="punishApprovalUnitName" name="punishApprovalUnitName" value="${e.punishApprovalUnitName}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect initSelectedKey="${e.category.id}" name="category.id" labelName="奖惩类别：" verify="required" isNotNull="true" display="block" url="dictquery/sub/code/DM019" isAddDefaltOption="true" ></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="奖励决定原始文件号：" name="rewardNo" value="${e.rewardNo}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" id="rewardCode" initSelectedKey="${e.rewardCode.id}" name="rewardCode.id" labelName="奖励：" display="block" url="dictquery/sub/code/GBT_8563_1_2005"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" initSelectedKey="${e.rewardReason.id}" name="rewardReason.id" labelName="奖励原因：" display="block" url="dictquery/sub/code/DM021_REWARD"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="奖励说明：" name="rewardDescription" value="${e.rewardDescription}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" id="rewardApprovalUnitCode" initSelectedKey="${e.rewardApprovalUnitCode}" name="rewardApprovalUnitCode" labelName="奖励批准单位：" display="block" url="dictquery/sub/code/0302"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" initSelectedKey="${e.rewardApprovalUnitLevel.id}" name="rewardApprovalUnitLevel.id" labelName="奖励批准单位级别：" display="block" url="dictquery/sub/code/DM141"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="荣誉称号名称：" name="honoraryName" value="${e.honoraryName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="荣誉称号授予单位：" name="honoraryGrantingUnit" value="${e.honoraryGrantingUnit}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" initSelectedKey="${e.honoraryLevel.id}" name="honoraryLevel.id" labelName="荣誉称号级别：" display="block" url="dictquery/sub/code/GBT_8563_2_2005_LEVEL"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="奖励批准日期：" display="block" name="rewardApprovalDate" id="rewardApprovalDate" value="${e.rewardApprovalDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="奖励撤销日期：" display="block" name="rewardRevokeDate" id="rewardRevokeDate" value="${e.rewardRevokeDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" initSelectedKey="${e.rewardRevokeReason.id}" name="rewardRevokeReason.id" labelName="奖励撤销原因：" display="block" url="dictquery/sub/code/DM189"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="处分决定原始文件号：" name="punishNo" value="${e.punishNo}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" id="punishCode" initSelectedKey="${e.punishCode.id}" name="punishCode.id" labelName="惩戒：" display="block" url="dictquery/sub/code/0122"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" initSelectedKey="${e.punishReason.id}" name="punishReason.id" labelName="惩戒原因：" display="block" url="dictquery/sub/code/DM021_PUNISH"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="惩戒说明：" name="punishDescription" value="${e.punishDescription}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" id="punishApprovalUnitCode" initSelectedKey="${e.punishApprovalUnitCode}" name="punishApprovalUnitCode" labelName="惩戒批准单位：" display="block" url="dictquery/sub/code/0302"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" initSelectedKey="${e.punishApprovalUnitLevel.id}" name="punishApprovalUnitLevel.id" labelName="惩戒批准单位级别：" display="block" url="dictquery/sub/code/DM141"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="惩戒批准日期：" display="block" name="punishApprovalDate" id="punishApprovalDate" value="${e.punishApprovalDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="惩戒解除日期：" display="block" name="punishRevokeDate" id="punishRevokeDate" value="${e.punishRevokeDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" initSelectedKey="${e.punishIdentification.id}" name="punishIdentification.id" labelName="是否监察机关直接给予惩戒的标识：" display="block" url="dictquery/sub/code/DM063"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" initSelectedKey="${e.punishmentIdentification.id}" name="punishmentIdentification.id" labelName="是否受处分标识：" display="block" url="dictquery/sub/code/DM215"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:textarea display="block" labelName="奖惩综述：" name="reviews">${e.reviews}</smart:textarea>
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
		<smart:dateRender id="rewardApprovalDate"/>
		<smart:dateRender id="rewardRevokeDate"/>
		<smart:dateRender id="punishApprovalDate"/>
		<smart:dateRender id="punishRevokeDate"/>
		form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'保存奖惩信息',
				message:'确认提交保存吗？',
				url:'ofcflow/eventRewardAndPunish/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('rewardAndPunishNavigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		form.on('select(rewardCode)', function(data){
		    $('#rewardName').val(data.elem[data.elem.selectedIndex].text)
		});
		form.on('select(rewardApprovalUnitCode)', function(data){
		    $('#rewardApprovalUnitName').val(data.elem[data.elem.selectedIndex].text)
		});
		form.on('select(punishCode)', function(data){
		    $('#punishName').val(data.elem[data.elem.selectedIndex].text)
		});
		form.on('select(punishApprovalUnitCode)', function(data){
		    $('#punishApprovalUnitName').val(data.elem[data.elem.selectedIndex].text)
		});
	</smart:scriptHead>
</smart:body>
</html>