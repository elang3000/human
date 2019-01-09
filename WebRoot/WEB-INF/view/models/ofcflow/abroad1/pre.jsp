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
						<smart:gridColumn colPart="4">
							<smart:singleSelect labelName="因公出国计划："  display="block" isNotNull="true" id="myida" verify="required" name="yearPlanId" initSelectedKey="${abroadYearPlan.id}" url="ofcflow/abroadB/year/find2" isSearch="true"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow style="padding-top:50px" >
						<smart:gridColumn >
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm"
									title="开始因公出国政审" theme="normal">
									<smart:icon icon="check"></smart:icon>&nbsp;开始因公出国政审
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
		requestConfig.url = "ofcflow/abroadB/plan/validate";
		requestConfig.data = {yearPlanId:$("#myida").val()};
		requestConfig.success = function(result){
			if (result.success) {
				var params='yearPlayId='+data.field.yearPlanId;
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.full(index);
				window.location.href = "ofcflow/abroadB/editAbroadServant?" +params;
			}
			layer.msg(result.message);
		}
		smart.request(requestConfig);
	});
	  
	</smart:scriptHead>
</smart:body>
</html>