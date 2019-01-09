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
				<smart:form id="fileForm">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridColumn colPart="3" >
							<smart:textInput name="id" id="id" value="${abroadYearPlan.id}" type="hidden"></smart:textInput>
							<smart:date labelName="批件开始时间 ：" display="block" value="${abroadYearPlan.approveStartDate}" name="approveStartDate" id="approveStartDate" verify="required" isNotNull="true"></smart:date>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow style="padding-top:40px" >
						<smart:gridColumn >
							<smart:buttonGroup container="true">
								<smart:button id="export" other="lay-submit" size="sm"
									title="导出" theme="normal">
									<smart:icon icon="check"></smart:icon>&nbsp;导出
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
		<smart:dateRender id="approveStartDate" />
    	form.on('submit(export)', function (data) {//表单保存
		   $("#fileForm").attr("action","ofcflow/abroadB/exportAbroad");
		   smart.confirm({
				title:'提示',
				message:'确认生成批件？',
				url:'ofcflow/abroadB/checkExport',
				params:{id:$('#id').val()},
				callback:function(){
					$("#fileForm").submit();
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>