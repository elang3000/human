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
				<smart:form>
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridColumn colPart="3" >
							<smart:singleSelect labelName="年度招录计划：" isSearch="true" display="block" isNotNull="true" verify="required" id="myida" name="yearPlanId" url="ofcflow/recruit/year/find"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="3">
							<smart:textInput labelName="招录机关：" otherAttr="readonly"
								value="${root.name }" placeholder="" display="block"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="3">
							<smart:textInput labelName="用人机关：" otherAttr="readonly"
								value="${OrganNode.name }" placeholder="" display="block"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow style="padding-top:50px" >
						<smart:gridColumn >
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm"
									title="开始招录计划" theme="normal">
									<smart:icon icon="check"></smart:icon>&nbsp;开始招录计划
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
 	
	form.on('submit(save)', function (data) {//表单保存
		var requestConfig = {};
		requestConfig.url = "ofcflow/recruit/employPlan/plan/validate";
		requestConfig.data = {yearPlanId:$("#myida").val(),employOrganCode:"${OrganNode.code}"};
		requestConfig.success = function(result){
			if (result.success) {
				var params='yearPlanId='+data.field.yearPlanId;
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.full(index);
				window.location.href = "ofcflow/recruit/employPlan/plan?" +params;
			}
			layer.msg(result.message);
		}
		smart.request(requestConfig);
	});
	  
	</smart:scriptHead>
</smart:body>
</html>