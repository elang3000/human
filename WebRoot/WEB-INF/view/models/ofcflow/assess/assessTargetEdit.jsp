<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区公务员信息管理系统--修改拟优秀人数" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<smart:form id="editForm" action="ofcflow/assess/assessTargetEditSave">
						<smart:fromTokenTag />
						<smart:fieldSet title="修改优秀人数" style="margin-top: 5px;" color="blue">

							<smart:gridRow>
								<smart:textInput type="hidden" name="id"
									value="${id}">
								</smart:textInput>
								
							</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="8">
											<smart:numberInput labelName="优秀人数" name="outstandingNumb" value="${outStandingNumb }" type="numb" display="block" isNotNull="true" verify="required"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
						</smart:fieldSet>
						<smart:gridRow>
							<smart:gridColumn>
								<smart:buttonGroup container="true">
									<smart:button size="sm" id="save" title="修改" other="lay-submit"
										theme="default">
										<smart:icon icon="plus"></smart:icon>&nbsp;修改
		  				 			</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>

				</smart:gridRow>

			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:dateRender id="assessYear" type="year" />
		<smart:buttonScriptAction>
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
			
			var params=smart.json("#editForm");
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
		                result.message, 
		                {icon: 1,closeBtn: 1 },
		                function () {
		                	parent.layui.table.reload('navigationList');
		                	var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
	                });
				}else{
					smart.message({
						message : result.message,
						type : 'E' //S保存  I问号  W感叹号 E错误
					});
				}
			});
			return false;
			
		});
		
	</smart:scriptHead>
</smart:body>
</html>