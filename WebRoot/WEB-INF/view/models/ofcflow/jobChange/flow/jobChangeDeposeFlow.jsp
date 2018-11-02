<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--公务员职务变动--降职流程审批" />
</head>
<smart:body>
	<smart:card>
		<smart:cardHead>
			<smart:gridRow>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="职务变动"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="流程审批" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:gridRow>
		</smart:cardHead>
		<smart:cardBody>
			<smart:form id="editForm"
				action="ofcflow/jobchange/operateDeposeFlow">
				<smart:fromTokenTag />
				<smart:gridRow colSpace="5">
					<smart:gridColumn>

						<smart:grid>
							<smart:title title="现职务信息" style="margin-top: 5px;" color="blue" />
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="姓名"
										infovalue="${servant.name}"></smart:infoShowerLabel>
									<smart:textInput name="result" id="result" type="hidden"></smart:textInput>
									<smart:textInput name="isSubmit" id="isSubmit" type="hidden"></smart:textInput>
									<smart:textInput type="hidden" name="id"
										value="${jobShiftDepose.id}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="现职务:"
										infovalue="${post.postCode.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="现职级"
										infovalue="${servant.nowJobLevel.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="现职务类别:"
										infovalue="${post.attribute.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:title title="职务变动信息" style="margin-top: 5px;" color="blue" />
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="提出免职文号"
										infovalue="${jobShiftDepose.nominationDismissalNumber}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="提出免职日期"
										infovalue="${jobShiftDepose.nominationDismissalDate}"></smart:infoShowerLabel>

								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="决定或批准免职的文号"
										infovalue="${jobShiftDepose.approvalDismissalNumber}"></smart:infoShowerLabel>

								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="决定或批准免职的日期"
										infovalue="${jobShiftDepose.approvalDismissalDate}"></smart:infoShowerLabel>

								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="免职方式"
										infovalue="${jobShiftDepose.dismissalType.name}"></smart:infoShowerLabel>

								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="免职原因类别"
										infovalue="${jobShiftDepose.dismissalReason.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="免职决定做出方式"
										infovalue="${jobShiftDepose.dismissalMode.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:infoShowerLabel infoname="备注"
										infovalue="${jobShiftDepose.remark}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:textarea labelName="审批意见:" display="block"
										name="opinion" placeholder="审批意见"></smart:textarea>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:grid>
					</smart:gridColumn>
				</smart:gridRow>


				<smart:gridRow>
					<smart:gridColumn>
						<smart:buttonGroup container="true">
							<smart:button method="pass" size="sm" title="审批通过" theme="normal">
								<smart:icon icon="check">&nbsp;审批通过</smart:icon>
							</smart:button>
							<smart:button method="noPass" size="sm" title="审批不通过"
								theme="danger">
								<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
							</smart:button>
							<smart:button theme="warm" size="sm" title="返回" method="goBack">
								<smart:icon icon="reply"></smart:icon>&nbsp;返回
							</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:form>
		</smart:cardBody>

	</smart:card>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:continuousSelectAction />
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/jobchange/operateDeposeFlow',
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
					url:'ofcflow/jobchange/operateDeposeFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			goBack : function(data) {
				parent.location.reload(); // 父页面刷新
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				parent.layer.close(index);
			}	
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>