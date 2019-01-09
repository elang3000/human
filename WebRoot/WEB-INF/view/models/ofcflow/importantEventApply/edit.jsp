<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="编辑重大事项"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="importantEventApply/save">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${importantEventApply.id }"></smart:textInput>
					<smart:textInput type="hidden" name="applyOrgan.id" value="${importantEventApply.applyOrgan.id}"></smart:textInput>
					<smart:textInput type="hidden" name="applyUser.id" value="${importantEventApply.applyUser.id}"></smart:textInput>
					<smart:gridRow>
						<smart:title title="事项信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:textInput labelName="事项标题：" id="eventTitle" name="eventTitle" value="${importantEventApply.eventTitle }" isNotNull="true" verify="required"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="申请单位" infovalue="${importantEventApply.applyOrgan.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:textarea labelName="事项说明：" name="description"  display="block">${importantEventApply.description}</smart:textarea>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect isAddDefaltOption="true"  name="eventType.id" labelName="事项类型：" display="block" url="dictquery/sub/code/HUMAN_IMPORTANT_EVENT_TYPE" initSelectedKey="${importantEventApply.eventType.id}" isNotNull="true" verify="required"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="1">
							<smart:fileUpload accept="file" buttonName="事项附件" dataName="filePath" auto="false" size="20000" data="${importantEventApply.filePath}"/>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
					     	<smart:button id="save" other="lay-submit" size="sm" title="暂存" theme="default">
								<smart:icon icon="plus">&nbsp;暂存</smart:icon>
							</smart:button>
							
					     	<smart:button id="submit" other="lay-submit" size="sm" title="提交" theme="normal">
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
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:utils/>
		<smart:fileUploadUtils/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		
		form.on('submit(save)', function (data) {//表单保存
				var params=data.field;
				var url=data.form.action;
				$.post(url,params,function(result){
					if(result.success){//保存成功
						layer.alert(
		                result.message, 
		                {icon: 1,closeBtn: 1 },
		                function () {
		                	parent.layui.table.reload('navigationList');
							parent.layui.table.reload('importantMgrList');
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
		
		form.on('submit(submit)', function(data) {
			var params=data.field;
			var url="importantEventApply/applySubmit";
			smart.confirm({
				title:'提示',
				message:'确认提交申请吗？',
				url:url,
				params : params,
				callback : function(){
					parent.layui.table.reload('navigationList');
					parent.layui.table.reload('importantMgrList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>