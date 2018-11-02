<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="试用信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="ofc/probation/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${probation.id }"></smart:textInput>
										<smart:textInput type="hidden" name="servant.id" value="${servant.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="试用类别：" name="type.id" display="block" url="dictquery/sub/code/DM058" isAddDefaltOption="true" initSelectedKey="${probation.type.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="试用单位：" name="unitName" value="${probation.unitName }"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="试用起始日期 ：" display="block" name="startDate" id="startDate" value="${probation.startDate}"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									
									<smart:gridColumn colPart="4">
										<smart:date labelName="试用终止日期 ：" display="block" name="endDate" id="endDate" value="${probation.endDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="入职转正日期：" display="block" name="becomeDate" id="becomeDate" value="${probation.becomeDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="入职转正批准文号：" name="becomeNo" value="${probation.becomeNo }"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect id="conclusion" labelName="试用期满考核结论：" inputName="conclusion.id" codeTypeCode="DM018" inputVal="${probation.conclusion.id}" valType="ID" widthPercent="0.3333"/>
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
		<smart:dateRender id="startDate"/>
		<smart:dateRender id="endDate"/>
		<smart:dateRender id="becomeDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('probationNavigationList');
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