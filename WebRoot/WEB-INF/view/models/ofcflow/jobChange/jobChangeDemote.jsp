<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--公务员职务变动--${head }" />
</head>
<smart:body>
	<smart:card>
		<smart:cardHead>
			<smart:gridRow>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="职务变动"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="${head }"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:gridRow>
		</smart:cardHead>
		<smart:cardBody>
			<smart:form id="editForm"
				action="ofcflow/jobchange/operateDemoteFlow">
				<smart:fromTokenTag />
				<smart:gridRow colSpace="5">
					<smart:gridColumn>

						<smart:grid>
							<smart:title title="现职务信息" style="margin-top: 5px;" color="blue" />
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="姓名"
										infovalue="${servant.name}"></smart:infoShowerLabel>
									<smart:textInput type="hidden" name="prePost.id"
										value="${post.id}">
									</smart:textInput>
									<smart:textInput type="hidden" name="servant.id"
										value="${servant.id}"></smart:textInput>
									<c:if test="${!empty jobShift.id}">
										<smart:textInput type="hidden" name="id"
											value="${jobShift.id}"></smart:textInput>
									</c:if>
									<smart:textInput name="result" id="result" type="hidden"></smart:textInput>
									<smart:textInput name="isSubmit" id="isSubmit" type="hidden"></smart:textInput>
									<smart:textInput name="isShift" id="jobShift"
										value="${isShift }" type="hidden"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="现职务:"
										infovalue="${post.postCode.name}"></smart:infoShowerLabel>
									<smart:textInput type="hidden" name="formerPostName"
										value="${post.postCode.name}"></smart:textInput>
									<smart:textInput type="hidden" name="formerPostCode.id"
										value="${post.postCode.id}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="现职级"
										infovalue="${servant.nowJobLevel.name}"></smart:infoShowerLabel>
									<smart:textInput type="hidden" name="formerJobLevel.id"
										value="${servant.nowJobLevel.id}"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="现职务属性:"
										infovalue="${post.attribute.name}"></smart:infoShowerLabel>
									<smart:textInput type="hidden" name="formerPostAttribute.id"
										value="${post.attribute.id}"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:title title="职务变动信息" style="margin-top: 5px;" color="blue" />
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:singleSelect id="postCode" isAddDefaltOption="true"
										name="newPostCode.id" labelName="职务名称：" display="block" initCludeKey="[220A,220B,221A,221S,221T,224A]"
										url="dictquery/sub/code/GBT_12403_1990" verify="required"
										initSelectedKey="${jobShift.newPostCode.id}" isNotNull="true"
										isSaveShowName="true" inputShowName="postName"></smart:singleSelect>
								</smart:gridColumn>
								<c:if test="${!isShift }">
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="postCode" isAddDefaltOption="true"
											name="degradeType.id" labelName="降级原因：" display="block"
											url="dictquery/sub/code/DM114" verify="required"
											initSelectedKey="${jobShift.degradeType.id}" isNotNull="true"
											isSaveShowName="true" inputShowName="postName"></smart:singleSelect>
									</smart:gridColumn>
							</c:if>

								<smart:gridColumn colPart="4">
									<smart:date labelName="职务变动日期：" isNotNull="true"
										verify="required" display="block" name="postChangeDate"
										value="${jobShift.postChangeDate}" id="jobChangeDate"></smart:date>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>

								<smart:gridColumn colPart="4">
<%-- 									<smart:continuousSelect id="attribute" labelName="职务类别："
										inputName="newPostAttribute.id" codeTypeCode="DM049"
										valType="ID" inputVal="${jobShift.newPostAttribute.id }"
										widthPercent="0.33333" verify="required" isNotNull="true" /> --%>
										<smart:singleSelect id="attribute" isAddDefaltOption="true" name="newPostAttribute.id" labelName="职务属性："  
									display="block" url="dictquery/sub/code/DM049" initSelectedKey="${jobShift.newPostAttribute.id }" verify="required" isNotNull="true"></smart:singleSelect>
								</smart:gridColumn>
								
																<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="职级名称：" name="newJobLevel.id" display="block" url="dictquery/sub/code/GBT_12407_2008/1" isAddDefaltOption="true" initSelectedKey="${jobShift.newJobLevel.id}"  initCludeKey="[141,142,150,160]"
									verify="required" isNotNull="true" isSaveShowName="true" inputShowName="name"></smart:singleSelect>
								</smart:gridColumn>
								
							</smart:gridRow>
							
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:textarea isNotNull="true" verify="required"
										display="block" labelName="备注:" name="remark">${jobShift.remark }</smart:textarea>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:grid>
					</smart:gridColumn>
				</smart:gridRow>


				<smart:gridRow>
					<smart:gridColumn>
						<smart:buttonGroup container="true">
							<smart:button id="save" other="lay-submit" size="sm" method="add"
								title="提交当前表单">
								<smart:icon icon="check">提交</smart:icon>
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
		<smart:dateRender id="jobChangeDate" />
		<smart:continuousSelectAction />
		<smart:buttonScriptAction>
		goBack : function(data) {
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
			reload : function(){
				table.reload('postNavigationList');
			}
		</smart:tableScriptAction>
	</smart:scriptHead>
</smart:body>
</html>