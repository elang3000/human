<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<smart:singleSelect labelName="汇总培训考核名称：" display="block" isNotNull="true" verify="required" id="myida" name="yearPlanId" url="ofcflow/train/year/find"></smart:singleSelect>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="3">
							<smart:linkSelect labelName="在学单位：" id="organNodeIdTag" display="block"/>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="3">
							<smart:textInput labelName="主办单位：" otherAttr="readonly"
								value="${inputOrgan.name }" placeholder="" display="block"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow style="padding-top:50px" >
						<smart:gridColumn >
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm"
									title="开始单位培训考核" theme="normal">
									<smart:icon icon="check"></smart:icon>&nbsp;开始单位培训考核
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,linkSelect">
		<smart:utils />
		
	<smart:initLinkSelect id="organNodeIdTag" name="organNodeId" tips="请选择所属单位" verify="required" selected="${organNodeId}" url="system/organ/node/query" params="{organTreeId:'394e21fa-1eb6-42ee-ba32-50655fa16517'}"/>
	form.on('submit(save)', function (data) {//表单保存
		var requestConfig = {};
		requestConfig.url = "ofcflow/train/plan/validate";
		requestConfig.data = {yearPlanId:$("#myida").val()};
		requestConfig.success = function(result){
			if (result.success) {
				var params='yearPlanId='+data.field.yearPlanId+'&trainOrganId='+data.field.organNodeId;
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.full(index);
				window.location.href = "ofcflow/train/trainPlan/plan?" +params;
			}
			layer.msg(result.message);
		}
		smart.request(requestConfig);
	});
	  
	</smart:scriptHead>
</smart:body>
</html>