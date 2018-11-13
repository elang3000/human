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
				<smart:form id="editForm" action="ofc/post/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${post.id}"></smart:textInput>
										<smart:textInput type="hidden" name="servant.id" value="${servant.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="postCode" isAddDefaltOption="true" name="postCode.id" labelName="职务名称：" display="block" url="dictquery/sub/code/GBT_12403_1990" initSelectedKey="${post.postCode.id}" verify="required" isNotNull="true" isSaveShowName="true" inputShowName="postName"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="attribute" isAddDefaltOption="true" name="attribute.id" labelName="职务属性：" display="block" url="dictquery/sub/code/DM049" initSelectedKey="${post.attribute.id}" verify="required" isNotNull="true"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="任职机构名称：" name="tenureName" value="${post.tenureName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="tenureRange.id" labelName="任职范围类别 ：" display="block" url="dictquery/sub/code/DM218" initSelectedKey="${post.tenureRange.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="主管（从事）工作：" name="workContentRange" value="${post.workContentRange}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput min="0" labelName="多职务主次序号:" name="manyNo" value="${post.manyNo}"  display="block" type="text"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput min="0" labelName="集体内排列顺序号:" name="collectiveNo" value="${post.collectiveNo}"  display="block" type="text"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="决定或批准任职的文号：" name="approvalNumber" value="${post.approvalNumber}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="决定或批准任职的日期：" display="block" name="approvalDate" id="approvalDate" value="${post.approvalDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect id="tenureReason" labelName="任职原因：" inputName="tenureReason.id" codeTypeCode="DM004" inputVal="${post.tenureReason.id}" valType="ID" widthPercent="0.33333"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="tenureMode.id" labelName="任职方式：" display="block" url="dictquery/sub/code/DM003" initSelectedKey="${post.tenureMode.id}"></smart:singleSelect>
									</smart:gridColumn>
									
									<smart:gridColumn colPart="8">
										<smart:continuousSelect id="tenureChange" labelName="任职变动类别：" inputName="tenureChange.id" codeTypeCode="DM006" inputVal="${post.tenureChange.id}" valType="ID" widthPercent="0.33333"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="tenureStatus.id" labelName="任职状态：" display="block" url="dictquery/sub/code/DM007" initSelectedKey="${post.tenureStatus.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="communicationSign.id" labelName="交流标识：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.communicationSign.id}"></smart:singleSelect>
									</smart:gridColumn>
									
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="决定或批准免职的文号：" name="approvalDismissalNumber" value="${post.approvalDismissalNumber}" ></smart:textInput>
									</smart:gridColumn>
									
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="决定或批准免职的日期：" display="block" name="approvalDismissalDate" id="approvalDismissalDate" value="${post.approvalDismissalDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect id="dismissalReason" labelName="免职原因类别：" inputName="dismissalReason.id" codeTypeCode="DM009" inputVal="${post.dismissalReason.id}" valType="ID" widthPercent="0.33333"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="isBreakPromotion.id" labelName="是否破格提拔：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isBreakPromotion.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="isOpenSelect.id" labelName="是否公开遴选：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isOpenSelect.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="isOpenSelection.id" labelName="是否公开选调：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${post.isOpenSelection.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="现任职务标识：" id="nowPostSign" name="nowPostSign.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${post.nowPostSign.id }" verify="required" isNotNull="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<script type="text/javascript">
											function topChangeEvent(data){
												if(data.elem[data.elem.selectedIndex].text=="是"){
													layui.use('smart',function(){
														var $ = layui.jquery;
														var smart=layui.smart;
														smart=smart(); 
														var nowPost = $('#nowPostSign').next().find('.layui-anim').find('dd.layui-this').text();
														if(nowPost != '是'){
															smart.message({
																message : "最高职务必要条件为当前职务为现任职务",
																type : 'W' //S保存  I问号  W感叹号 E错误
															});
														}
													});
												}
											}
										</script>
										<smart:singleSelect id="highestPostSign" labelName="最高职务标识：" name="highestPostSign.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${post.highestPostSign.id }" verify="required" isNotNull="true" changeCallBack="topChangeEvent"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否兼任下级领导职务：" id="isLowerLeader" name="isLowerLeader.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${post.isLowerLeader.id }" verify="required" isNotNull="true"></smart:singleSelect>
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
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			var topPost = $('#highestPostSign').next().find('.layui-anim').find('dd.layui-this').text();
			var nowPost = $('#nowPostSign').next().find('.layui-anim').find('dd.layui-this').text();
			if(topPost== '是'&& nowPost != '是'){
				smart.message({
					message : "最高职务必要条件为当前职务为现任职务",
					type : 'E' //S保存  I问号  W感叹号 E错误
				});
				return;
			}
			
			
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('postNavigationList');
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