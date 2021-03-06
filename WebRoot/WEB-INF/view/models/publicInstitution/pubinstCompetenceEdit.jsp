<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="专业技术任职资格信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="pubinst/competence/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${pubinst.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${competence.id }"></smart:textInput>
										<smart:textInput type="hidden" name="publicInstitution.id" value="${pubinst.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="code" labelName="任职资格名称 ："  isNotNull="true" verify="required" inputName="code.id" codeTypeCode="GBT_8561_2001" inputVal="${competence.code.id}" valType="ID" widthPercent="0.5" isSaveShowName="true" inputShowName="name" allOrLast="last"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="lvl" labelName="任职资格级别 ：" isNotNull="true" verify="required" inputName="lvl.id" codeTypeCode="GBT_12407_2008" inputVal="${competence.lvl.id}" valType="ID" widthPercent="0.5"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:singleSelect isAddDefaltOption="true"  name="gainWay.id" labelName="获得资格途径：" display="block" url="dictquery/sub/code/DM010" initSelectedKey="${competence.gainWay.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:date labelName="获得资格日期：" placeholder="专业技术资格获取日期" display="block" name="gainDate" id="gainDate" value="${competence.gainDate}"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="获取资格途径：" placeholder="专业资格评委会或考试名称：" name="jury" value="${competence.jury}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="资格审批单位：" placeholder="专业技术任职资格审批单位" name="approvalUnit" value="${competence.approvalUnit}" ></smart:textInput>
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
		<smart:dateRender id="gainDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('competenceNavigationList');
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