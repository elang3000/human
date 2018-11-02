<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="学历信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="pubinst/education/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${pubinst.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${education.id}"></smart:textInput>
										<smart:textInput type="hidden" name="publicInstitution.id" value="${pubinst.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="学校名称：" placeholder="毕业院校或单位名称" name="shoolName" value="${education.shoolName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect id="eductionCode" labelName="学历名称：" inputName="code.id" codeTypeCode="GBT_4658_2006" inputVal="${education.code.id}" valType="ID" widthPercent="0.33333" verify="required" isNotNull="true" isSaveShowName="true" inputShowName="name" allOrLast="last"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="入学日期：" placeholder="入学日期：" display="block" name="enterDate" id="enterDate" value="${education.enterDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="毕业日期：" placeholder="毕（肄、结）业日期：" display="block" name="graduateDate" id="graduateDate" value="${education.graduateDate}" verify="required" isNotNull="true"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="学制：" placeholder="学制年限" name="eductionalSystem" value="${education.eductionalSystem}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="学历证书号：" placeholder="学历证书号" name="educationNo" value="${education.educationNo}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect id="eductionalType" labelName="教育类别：" inputName="eductionalType.id" codeTypeCode="DM024" inputVal="${education.eductionalType.id}" valType="ID" widthPercent="0.33333"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="最高学历标识：" name="topFlag.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${education.topFlag.id }" ></smart:singleSelect>
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
		<smart:dateRender id="enterDate"/>
		<smart:dateRender id="graduateDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('educationNavigationList');
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