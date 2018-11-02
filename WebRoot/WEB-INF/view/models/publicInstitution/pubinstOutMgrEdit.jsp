<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="退出（调出）信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="pubinst/outMgr/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${pubinst.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${outMgr.id }"></smart:textInput>
										<smart:textInput type="hidden" name="publicInstitution.id" value="${pubinst.id}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="提出调动类型：" name="proposeType.id" display="block" url="dictquery/sub/code/DM039" isAddDefaltOption="true" initSelectedKey="${outMgr.proposeType.id }"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="category" labelName="调出类别：" inputName="category.id" codeTypeCode="GBT_12405_2008" inputVal="${outMgr.category.id}" valType="ID" widthPercent="0.3333"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="reasonType" labelName="调动原因：" inputName="reasonType.id" codeTypeCode="DM015" inputVal="${outMgr.reasonType.id}" valType="ID" widthPercent="0.3333"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="调出日期：" display="block" name="outDate" id="outDate" value="${outMgr.outDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="调往单位：" name="gotoUnitName" value="${outMgr.gotoUnitName}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:textarea labelName="调出备注：" name="remark"  display="block">${outMgr.remark}</smart:textarea>
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
		<smart:dateRender id="outDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('outMgrNavigationList');
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