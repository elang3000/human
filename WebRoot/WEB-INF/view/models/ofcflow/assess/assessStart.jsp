<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区公务员信息管理系统--发起考核" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>

					<smart:form id="editForm" action="ofcflow/assess/assessCollectSave">
						<smart:fromTokenTag />
						<smart:fieldSet title="发起考核" style="margin-top: 5px;" color="blue">

							<smart:gridRow>
								<smart:textInput type="hidden" name="assessmentType"
									value="${assessmentType}">
								</smart:textInput>
								<smart:gridColumn colPart="5">
									<smart:date labelName="考核年份" name="year" id="assessYear"
										isNotNull="true" verify="required" display="block"
										placeholder="年份"></smart:date>
								</smart:gridColumn>
							</smart:gridRow>

							<c:if test="${!isYearAssess}">
								<smart:gridRow>

									<smart:gridColumn colPart="12">
										<smart:singleSelect name="season" id="season" display="inline"
											labelName="考核季度" isNotNull="true" verify="required"
											data="[{'key':'','value':'请选择季度'},{'key':'1','value':'第一季度'},{'key':'2','value':'第二季度'},{'key':'3','value':'第三季度'}]"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>

							</c:if>
							<c:if test="${isYearAssess}">
								<smart:gridRow>
									<smart:gridColumn colPart="8">
											<smart:numberInput labelName="优秀初始比例：" name="draftOutstandingPercent" value="15" type="text" display="block" isNotNull="true" verify="required"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
							</c:if>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:textarea labelName="备注:" display="block" name="remark"
										placeholder="备注"></smart:textarea>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:fieldSet>
						<smart:gridRow>
							<smart:gridColumn>
								<smart:buttonGroup container="true">
									<smart:button size="sm" id="save" title="发起" other="lay-submit"
										theme="default">
										<smart:icon icon="plus"></smart:icon>&nbsp;发起
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