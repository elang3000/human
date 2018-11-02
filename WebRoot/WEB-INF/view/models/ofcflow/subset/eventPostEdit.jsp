<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="职务信息"/>
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
										<smart:infoShowerLabel infoname="姓名" infovalue="${post.personName}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${post.id}"></smart:textInput>
										<smart:textInput type="hidden" name="personName" value="${post.personName}"></smart:textInput>
										<smart:textInput type="hidden" name="cardNo" value="${post.cardNo}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="任职机构名称：" verify="required" isNotNull="true" name="tenureName" value="${post.tenureName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="任职机构代码：" name="tenureCode" value="${post.tenureCode}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" verify="required" isNotNull="true" name="attribute.id" labelName="职务属性：" display="block" url="dictquery/sub/code/DM049" initSelectedKey="${post.attribute.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="postCode" isAddDefaltOption="true" verify="required" isNotNull="true" name="postCode.id" labelName="职务代码：" display="block" url="dictquery/sub/code/GBT_12403_1990" initSelectedKey="${post.postCode.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput id="postName" labelName="职务名称：" verify="required" isNotNull="true" name="postName" value="${post.postName }" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="tenureRange.id" labelName="任职范围类别 ：" display="block" url="dictquery/sub/code/DM218" initSelectedKey="${post.tenureRange.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput min="0" labelName="职务排序:" name="manyNo" value="${post.manyNo}"  display="block" type="text"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput min="0" verify="required" isNotNull="true" labelName="集体内排序号:" name="collectiveNo" value="${post.collectiveNo}"  display="block" type="text"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput verify="required" isNotNull="true" labelName="分管（从事）工作：" name="workContentRange" value="${post.workContentRange}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="任职时间：" verify="required" isNotNull="true" display="block" name="approvalDate" id="approvalDate" value="${post.approvalDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="任职文号：" name="approvalNumber" value="${post.approvalNumber}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="tenureForm.id" labelName="选拔任用方式：" display="block" url="dictquery/sub/code/0199" initSelectedKey="${post.tenureForm.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="tenureMode.id" labelName="任职方式：" display="block" url="dictquery/sub/code/DM003" initSelectedKey="${post.tenureMode.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="tenureReason.id" labelName="任职原因：" display="block" url="dictquery/sub/code/DM004" initSelectedKey="${post.tenureReason.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="tenureChange.id" labelName="任职变动类别：" display="block" url="dictquery/sub/code/DM006" initSelectedKey="${post.tenureChange.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="tenureStatus.id" labelName="任职状态：" display="block" url="dictquery/sub/code/DM007" initSelectedKey="${post.tenureStatus.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="免职时间：" display="block" name="approvalDismissalDate" id="approvalDismissalDate" value="${post.approvalDismissalDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="免职文号：" name="approvalDismissalNumber" value="${post.approvalDismissalNumber}" ></smart:textInput>
									</smart:gridColumn>
									
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="dismissalReason.id" labelName="免职原因类别：" display="block" url="dictquery/sub/code/DM009_ALL" initSelectedKey="${post.dismissalReason.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="communicationSign.id" labelName="交流标识：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.communicationSign.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="isBreakPromotion.id" labelName="是否破格提拔：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isBreakPromotion.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="isOpenSelect.id" labelName="是否公开遴选：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isOpenSelect.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="isOpenSelection.id" labelName="是否公开选调：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isOpenSelection.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" verify="required" isNotNull="true" name="isTeamMember.id" labelName="是否班子成员：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isTeamMember.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="memberType.id" labelName="成员类别：" display="block" url="dictquery/sub/code/0132" initSelectedKey="${post.memberType.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="isLowerLeader.id" labelName="是否兼任下级领导职务：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isLowerLeader.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" verify="required" isNotNull="true" name="isSkipLevel.id" labelName="是否越级提拔：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isSkipLevel.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="communicationType.id" labelName="交流方式：" display="block" url="dictquery/sub/code/0123" initSelectedKey="${post.communicationType.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="communicationReason.id" labelName="交流原因：" display="block" url="dictquery/sub/code/0124" initSelectedKey="${post.communicationReason.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="communicationWhere.id" labelName="交流去向：" display="block" url="dictquery/sub/code/0125" initSelectedKey="${post.communicationWhere.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect labelName="政法人员职级：" inputName="lawPersonLevel.id" codeTypeCode="0060" inputVal="${post.lawPersonLevel.id}" valType="ID" widthPercent="0.3333333"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:textarea labelName="职务变动原因综述：" name="postChangeReason" display="block">${post.postChangeReason}</smart:textarea>
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
		<smart:dateRender id="approvalDate"/>
		<smart:dateRender id="approvalDismissalDate"/>
		form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'保存职务信息',
				message:'确认提交保存吗？',
				url:'ofcflow/eventPost/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('postNavigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		form.on('select(postCode)', function(data){
		    $('#postName').val(data.elem[data.elem.selectedIndex].text)
		});
	</smart:scriptHead>
</smart:body>
</html>