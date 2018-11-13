<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区公务员信息管理系统--启动考核流程" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>

					<smart:form id="editForm" action="ofcflow/assess/operateAssessProgress">
						<smart:fromTokenTag />
						<smart:fieldSet title="发起考核" style="margin-top: 5px;" color="blue">

							<smart:gridRow>
								<smart:textInput type="hidden" name="id"
									value="${percent.id}">
								</smart:textInput>
								<smart:textInput type="hidden" name="result" id="result"></smart:textInput>
								<smart:textInput type="hidden" name="isSubmit" id="isSubmit"></smart:textInput>
								
								<smart:gridColumn colPart="5">
								<smart:infoShowerLabel infoname="考核年份"
										infovalue="${collect.year}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>

							<smart:gridRow>
								<smart:gridColumn colPart="8">
								<smart:infoShowerLabel infoname="拟定优秀比例"
										infovalue="${collect.draftOutstandingPercent }%"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
														<smart:gridRow>
								<smart:gridColumn colPart="8">
								<smart:infoShowerLabel infoname="拟定优秀人数"
										infovalue="${percent.outstandingNumb }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
								<smart:infoShowerLabel infoname="提示"
										infovalue="${collect.remark }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<c:if test="${empty percent.flowRecord }" >
										<smart:gridColumn colPart="8">
												<smart:numberInput labelName="申请优秀人数" name="unitOutStandingNumb" value="${percent.outstandingNumb }" type="text" display="block" isNotNull="true" verify="required"></smart:numberInput>
										</smart:gridColumn>
								</c:if>
								<c:if test="${not empty percent.flowRecord }" >
																<smart:infoShowerLabel infoname="申请优秀人数:"
										infovalue="${percent.unitOutStandingNumb }"></smart:infoShowerLabel>
								</c:if>
							</smart:gridRow>
							<smart:gridRow>
							<smart:gridColumn colPart="8">
							<c:if test="${empty percent.flowRecord }" >
									<smart:textarea 
										display="block" labelName="备注:" name="remark"></smart:textarea>
										</c:if>
										<c:if test="${not empty percent.flowRecord }" >
											<smart:infoShowerLabel infoname="备注:"
										infovalue="${percent.remark }"></smart:infoShowerLabel>
										</c:if>
								</smart:gridColumn>
							</smart:gridRow>
							<c:if test="${not empty percent.flowRecord }" >
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:textarea labelName="审批意见:" display="block" name="opinion" placeholder="审批意见"></smart:textarea>
								</smart:gridColumn>
							</smart:gridRow>
							</c:if>
						</smart:fieldSet>
						<smart:gridRow>
							<smart:gridColumn>
								<smart:buttonGroup container="true">
								<c:if test="${not empty percent.flowRecord }" >
									<smart:button method="pass" size="sm" title="审批通过"
										theme="normal">
										<smart:icon icon="check">&nbsp;审批通过</smart:icon>
									</smart:button>
									<smart:button method="noPass" size="sm" title="审批不通过"
										theme="danger">
										<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
									</smart:button>
								</c:if>
								<c:if test="${empty percent.flowRecord }" >
									<smart:button size="sm" id="save" title="发起年度考核的流程" other="lay-submit"
										theme="default">
										<smart:icon icon="plus"></smart:icon>&nbsp;提交
		  				 			</smart:button>
	  				 			</c:if>
									<smart:button theme="warm" size="sm" method="goBack" title="返回">
										<smart:icon icon="reply">&nbsp;返回</smart:icon>
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
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/assess/operateAssessProgress',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			noPass : function() {
				$("#result").val("0");//审批不通过
				smart.confirm({
					title:'确认审批不通过',
					message:'确认审批不通过吗？',
					url:'ofcflow/assess/operateAssessProgress',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
			$("#result").val("1");
			$("#isSubmit").val("1");
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