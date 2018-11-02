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
				action="ofcflow/jobchange/operatePromoteFlow">
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
									<smart:textInput type="hidden" name="id" value="${jobShift.id}"></smart:textInput>
									<smart:textInput type="hidden" name="status"
										value="${jobShift.status}"></smart:textInput>
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
									<smart:infoShowerLabel infoname="晋升类别"
										infovalue="${jobShift.postTenureChange.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="晋升原因"
										infovalue="${jobShift.promotionType.name}"></smart:infoShowerLabel>
								</smart:gridColumn>

								<smart:gridColumn colPart="4">
									<%-- 									<smart:singleSelect id="postCode" isAddDefaltOption="true"
										name="newPostCode.id" labelName="职务名称：" display="block"
										url="dictquery/sub/code/GBT_12403_1990"
										initSelectedKey="${post.postCode.id}" verify="required"
										isNotNull="true" isSaveShowName="true"
										inputShowName="postName"></smart:singleSelect> --%>
									<smart:infoShowerLabel infoname="职务名称"
										infovalue="${jobShift.newPostCode.name}"></smart:infoShowerLabel>
								</smart:gridColumn>

							</smart:gridRow>
							<smart:gridRow>

								<smart:gridColumn colPart="4">
									<%-- 									<smart:continuousSelect id="attribute" labelName="职务类别："
										inputName="newPostAttribute.id" codeTypeCode="DM049"
										inputVal="${post.attribute.id}" valType="ID"
										widthPercent="0.33333" verify="required" isNotNull="true" /> --%>
									<smart:infoShowerLabel infoname="职务类别"
										infovalue="${jobShift.newPostAttribute.name}"></smart:infoShowerLabel>

								</smart:gridColumn>

								<smart:gridColumn colPart="4">
									<%-- 									<smart:singleSelect id="postCode" isAddDefaltOption="true"
										name="newJobLevel.id" labelName="职务级别：" display="block"
										url="dictquery/sub/code/GBT_12407_2008"
										initSelectedKey="${servant.nowJobLevel.id}" verify="required"
										isNotNull="true" isSaveShowName="true"
										inputShowName="postName"></smart:singleSelect> --%>
									<smart:infoShowerLabel infoname="职务级别"
										infovalue="${jobShift.newJobLevel.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<%-- 									<smart:date labelName="职务变动日期：" isNotNull="true"
										verify="required" display="block" name="postChangeDate"
										id="jobChangeDate"></smart:date> --%>
									<smart:infoShowerLabel infoname="职务变动日期"
										infovalue="${jobShift.postChangeDate}"></smart:infoShowerLabel>
								</smart:gridColumn>


							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:infoShowerLabel infoname="备注"
										infovalue="${jobShift.remark}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="1">
								<smart:fileUpload accept="file" buttonName="人员信息任免表文件" isView="true" dataName="appointSheetFilePath"  auto="false" name="appointSheetFilePath1" size="20000" data="${jobShift.appointSheetFilePath}"/>
								
								<smart:fileUpload accept="file" buttonName="考核材料文件"  isView="${isStep7 }" dataName="examineFilePath" auto="false" name="examineFilePath1" size="20000" data="${jobShift.examineFilePath}"/>
								
								<smart:fileUpload accept="file" buttonName="公示人员信息附件文件" isView="${isStep12 }" dataName="personInfoFilePath" auto="false" name="personInfoFilePath1" size="20000" data="${jobShift.personInfoFilePath}"/>
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
						
							<c:if test="${isStep7&&isStep12}">
								<smart:button method="pass" size="sm" title="审批通过" theme="normal">
									<smart:icon icon="check">&nbsp;审批通过</smart:icon>
								</smart:button>
								<smart:button method="noPass" size="sm" title="审批不通过"
									theme="danger">
									<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
								</smart:button>
							</c:if>
							
							<c:if test="${ !(isStep7&&isStep12)}">
								<smart:button id="save" other="lay-submit" size="sm" method="add" title="提交">
									<smart:icon icon="check">&nbsp;提交</smart:icon>
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
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:fileUploadUtils/>
		<smart:utils />
		<smart:dateRender id="jobChangeDate" />
		<smart:continuousSelectAction />
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/jobchange/operatePromoteFlow',
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
					url:'ofcflow/jobchange/operatePromoteFlow',
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
		<smart:dateRender id="jobchangeDate" />
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
			promote : function(data) {
			},
			depose : function(data) {
			},
			demote : function(data) {
			},
			shift : function(data) {
			},
			reload : function(){
				table.reload('postNavigationList');
			}
		</smart:tableScriptAction>
	</smart:scriptHead>
</smart:body>
</html>