<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="学位信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="formationControl/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="机构名称" infovalue="${formationControl.organ.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${formationControl.id }"></smart:textInput>
										<smart:textInput type="hidden" name="organ.id" value="${formationControl.organ.id }"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否编控：" name="isOpenControl.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${formationControl.isOpenControl.id }" verify="required" isNotNull="true"></smart:singleSelect>
									</smart:gridColumn> 
									<c:if test="${isDisabledLowToHigh==false}">
										<smart:gridColumn colPart="4">
											<smart:singleSelect labelName="是否高职低，实职虚用：" name="isLowToHigh.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${formationControl.isLowToHigh.id }" verify="required" isNotNull="true"></smart:singleSelect>
										</smart:gridColumn>
									</c:if>
									<c:if test="${isDisabledLowToHigh==true}">
										<smart:textInput type="hidden" name="isLowToHigh.id" value="${formationControl.isLowToHigh.id }"></smart:textInput>
									</c:if>	
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="编制溢出规则：" name="overflowRule" value="${formationControl.overflowRule}"></smart:textInput>
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
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('formationControlList');
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