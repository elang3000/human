<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--公务员职务变动--降职" />
</head>
<smart:body>
	<smart:card>
		<smart:cardHead>
			<smart:gridRow>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="职务变动"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="免职" cite="true"></smart:breadcrumbNavMenuItem>
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
									<smart:textInput type="hidden" name="post.id"
										value="${post.id}">
									</smart:textInput>
									<smart:textInput type="hidden" name="servant.id"
										value="${servant.id}"></smart:textInput>
									<c:if test="${not empty jobShiftDepose.id}">
										<smart:textInput type="hidden" name="id"
											value="${jobShiftDepose.id}"></smart:textInput>
									</c:if>
									<smart:textInput name="result" id="result" type="hidden"></smart:textInput>
									<smart:textInput name="isSubmit" id="isSubmit" type="hidden"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="免去职务:"
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
									<smart:textInput labelName="提出免职文号："
										name="nominationDismissalNumber" isNotNull="true"
										verify="required"
										value="${jobShiftDepose.nominationDismissalNumber}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:date labelName="提出免职日期：" isNotNull="true"
										verify="required" display="block" name="nominationDismissalDate"
										value="${jobShiftDepose.nominationDismissalDate}" id="nominationDismissalDate"></smart:date>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="决定或批准免职的文号："
										name="approvalDismissalNumber" isNotNull="true"
										verify="required"
										value="${jobShiftDepose.approvalDismissalNumber}"></smart:textInput>
								</smart:gridColumn>

							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:date labelName="决定或批准免职的日期：" isNotNull="true"
										verify="required" display="block"
										name="approvalDismissalDate"
										value="${jobShiftDepose.approvalDismissalDate}"
										id="approvalDismissalDate"></smart:date>
								</smart:gridColumn>

								<smart:gridColumn colPart="4">
									<smart:singleSelect isAddDefaltOption="true" isNotNull="true"
										verify="required" name="dismissalType.id" labelName="免职方式："
										display="block" url="dictquery/sub/code/DM008"
										initSelectedKey="${jobShiftDepose.dismissalType.id}"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect isAddDefaltOption="true"
										name="dismissalReason.id" labelName="免职原因类别：" display="block"
										url="dictquery/sub/code/DM009"
										initSelectedKey="${jobShiftDepose.dismissalReason.id}"></smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>

							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:singleSelect isAddDefaltOption="true"
										name="dismissalChange.id" labelName="免职变动类别：" display="block"
										url="dictquery/sub/code/DM006/4"
										initSelectedKey="${jobShiftDepose.dismissalChange.id}"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect isAddDefaltOption="true"
										name="dismissalMode.id" labelName="免职决定做出方式：" display="block"
										url="dictquery/sub/code/DM163"
										initSelectedKey="${jobShiftDepose.dismissalMode.id}"></smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>

							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:textarea display="block" labelName="备注:" name="remark">${jobShiftDepose.remark }</smart:textarea>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:grid>
					</smart:gridColumn>
				</smart:gridRow>


				<smart:gridRow>
					<smart:gridColumn>
						<smart:buttonGroup container="true">

							<c:if test="${!view}">
								<smart:button id="save" other="lay-submit" size="sm" method="add"
											  title="提交当前表单">
									<smart:icon icon="check">提交</smart:icon>
								</smart:button>
							</c:if>
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
		<smart:dateRender id="approvalDismissalDate" />
		<smart:dateRender id="nominationDismissalDate" />
		<smart:continuousSelectAction />
		<smart:buttonScriptAction>
		goBack : function(data) {
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
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
		
		
		<smart:tableScriptAction tableId="postNavigationList" checkbox="true"
			sort="true" rowEdit="true">
			reload : function(){
				table.reload('postNavigationList');
			}
		</smart:tableScriptAction>
	</smart:scriptHead>
</smart:body>
</html>