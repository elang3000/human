<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="职务变动信息" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="ofc/jobChange/save">
					<smart:fromTokenTag />
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${jobChange.id }"></smart:textInput>
										<smart:textInput type="hidden" name="servant.id" value="${servant.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="原单位代码：" name="formerUnitCode" value="${jobChange.formerUnitCode}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="原单位名称：" name="formerUnitName" value="${jobChange.formerUnitName}" verify="required" isNotNull="true"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="formerPostCode" isAddDefaltOption="true" name="formerPostCode.id" labelName="原职务名称：" display="block" url="dictquery/sub/code/GBT_12403_1990"
											initSelectedKey="${jobChange.formerPostCode.id}" verify="required" isNotNull="true" isSaveShowName="true" inputShowName="formerPostName"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="原职务级别：" id="formerJobLevel" name="formerJobLevel.id" display="block" url="dictquery/sub/code/GBT_12407_2008/1" isAddDefaltOption="true"
											initSelectedKey="${jobChange.formerJobLevel.id }" verify="required" isNotNull="true" initIncludeKey="131,132,141,142,150,160"></smart:singleSelect>
									</smart:gridColumn>

									<smart:gridColumn colPart="4">
										<smart:singleSelect id="formerPostAttribute" isAddDefaltOption="true" name="formerPostAttribute.id" labelName="原职务类别：" display="block" url="dictquery/sub/code/DM049"
											initSelectedKey="${jobChange.formerPostAttribute.id}" verify="required" isNotNull="true"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="新单位代码：" name="newUnitCode" value="${jobChange.newUnitCode}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="新单位名称：" name="newUnitName" value="${jobChange.newUnitName}" verify="required" isNotNull="true"></smart:textInput>
									</smart:gridColumn>

								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="newPostCode" isAddDefaltOption="true" name="newPostCode.id" labelName="新职务名称：" display="block" url="dictquery/sub/code/GBT_12403_1990"
											initSelectedKey="${jobChange.newPostCode.id}" verify="required" isNotNull="true" isSaveShowName="true" inputShowName="newPostName"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="新职务级别：" id="newJobLevel" name="newJobLevel.id" display="block" url="dictquery/sub/code/GBT_12407_2008/1" isAddDefaltOption="true"
											initSelectedKey="${jobChange.newJobLevel.id }" verify="required" isNotNull="true" initIncludeKey="131,132,141,142,150,160"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="newPostAttribute" isAddDefaltOption="true" name="newPostAttribute.id" labelName="新职务类别：" display="block" url="dictquery/sub/code/DM049"
											initSelectedKey="${jobChange.newPostAttribute.id}" verify="required" isNotNull="true"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="职务变动日期：" display="block" name="postChangeDate" id="postChangeDate" value="${jobChange.postChangeDate}" verify="required" isNotNull="true"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="postTenureChange" isAddDefaltOption="true" name="postTenureChange.id" labelName="职务变动类别：" display="block" url="dictquery/sub/code/DM006"
											initSelectedKey="${jobChange.postTenureChange.id}" verify="required" isNotNull="true"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="promotionType" isAddDefaltOption="true" name="promotionType.id" labelName="晋级原因：" display="block" url="dictquery/sub/code/DM113"
											initSelectedKey="${jobChange.promotionType.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect id="degradeType" isAddDefaltOption="true" name="degradeType.id" labelName="降级原因：" display="block" url="dictquery/sub/code/DM114"
											initSelectedKey="${jobChange.degradeType.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm" title="保存" theme="normal">
									<smart:icon icon="check"></smart:icon>&nbsp;保存
							</smart:button>
								<smart:button size="sm" type="reset" title="重新填写">
									<smart:icon icon="refresh"></smart:icon>&nbsp;重新填写
						    </smart:button>
								<smart:button size="sm" method="goBack" title="返回" theme="warm">
									<smart:icon icon="reply"></smart:icon>&nbsp;返回
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
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="postChangeDate" />
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('jobChangeNavigationList');
	                	var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
	                });
				}else{
					layer.alert(
	                result.message, 
	                {icon: 0,closeBtn:0 });
				}
			});
			return false;
		});
	</smart:scriptHead>
</smart:body>
</html>