<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:textInput value="${id}" name="id" type="hidden"></smart:textInput>
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="管理试用期时长" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:numberInput isNotNull="true" verify="required" name="probationDate" display="block" min="0" value="${probationDate}" labelName="试用时长(月)"></smart:numberInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textInput labelName="备注:" value="${remark}" name="remark"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm" title="提交"
									theme="normal">
									<smart:icon icon="plus">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="goBack" title="返回">
									<smart:icon icon="pencil">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
			smart.confirm({
				title:'保存试用期时长',
				message:'确认保存试用期时长吗？',
				url:'ofcflow/probation/timeSave',
				params : smart.json("#editForm"),
				callback : function(){
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		}); 	
	</smart:scriptHead>
</smart:body>
</html>