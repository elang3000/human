<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="处分信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="ofcflow/punish/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:title title="公务员基本信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:selectResource labelName="姓名：" id="selectNameTag" display="block"/>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="身份证号：" name="cardNo" display="block" value="${servant.cardNo}"></smart:textInput>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="性别：" name="sex" display="block" value="${servant.sex.name}"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="出生日期：" name="birthDate" display="block" value="${servant.birthDate}"></smart:textInput>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="民族：" name="nation" display="block" value="${servant.nation.name}"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="政治面貌：" name="politics" display="block" value="${servant.politics.name}"></smart:textInput>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="出生地：" name="birthPlaceName" display="block" value="${servant.birthPlaceName}"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="籍贯：" name="nativePlaceName" display="block" value="${servant.nativePlaceName}"></smart:textInput>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="人员类别：" name="personType" display="block" value="${servant.personType.name}"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput otherAttr="disabled" labelName="健康状况：" name="health" display="block" value="${servant.health.name}"></smart:textInput>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
									<smart:gridColumn colPart="3" colOffset="1">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<img alt="照片" src="static/image/20170705135600.jpg">
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:textInput type="hidden" id="id" name="id" value="${punishServant.id }"></smart:textInput>
									<smart:textInput type="hidden" id="id" name="organ.id" value="${organ.id }"></smart:textInput>
									<smart:textInput type="hidden" id="status" name="status" value="1"></smart:textInput>
									<smart:textInput type="hidden" id="sign" name="sign" value="1"></smart:textInput>
									
									<smart:title title="处分信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="处分原始文件号：" name="punishFileName" value="${punishServant.punishFileName}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="处分原因：" name="punishReason.id" display="block" url="dictquery/sub/code/DM021_PUNISH" isAddDefaltOption="true" verify="required" isNotNull="true" initSelectedKey="${punishServant.punishReason.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="处分批准日期 ：" value="${punishServant.punishApprovalDate}" display="block" name="punishApprovalDate" id="punishApprovalDate" verify="required" isNotNull="true"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="处分期限（年）："  value="${punishServant.punishYear}" name="punishYear" min="0" type="text"  isNotNull="true" verify="required" placeholder="处分期限（年）" display="block"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="处分名称：" name="punishCode.id" display="block" url="dictquery/sub/code/GBT_8563_3_2005/1" isAddDefaltOption="true" verify="required" isNotNull="true" initSelectedKey="${punishServant.punishCode.id }"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn>
										<smart:textarea labelName="处分说明：" display="block" name="content" >${punishServant.content}</smart:textarea>
									</smart:gridColumn>
								</smart:gridRow>
								
								
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
								<smart:button id="submit" other="lay-submit" size="sm" title="提交"
									theme="normal">
									<smart:icon icon="check">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button id="save" other="lay-submit" size="sm" title="暂存"
									theme="default">
									<smart:icon icon="plus">&nbsp;暂存</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate,selectResource">
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:initSelectResource value="${servant.name}" hiddenValue="${servant.id}" id = "selectNameTag" name="servant.name" hiddenName="servant.id" winSize="lg" winTitle="选择人员信息" winUrl="orgInfo/selectServant?createOrganNodeId=${createOrganNodeId}" 
		linkElement="{fieldName :'cardNo',fieldValue : 'cardNo'},{fieldName :'sex',fieldValue : 'sex'},{fieldName :'birthDate',fieldValue : 'birthDate'},{fieldName :'nation',fieldValue : 'nation'},{fieldName :'politics',fieldValue : 'politics'}
		,{fieldName :'birthPlaceName',fieldValue : 'birthPlaceName'},{fieldName :'nativePlaceName',fieldValue : 'nativePlaceName'},{fieldName :'personType',fieldValue : 'personType'},{fieldName :'health',fieldValue : 'health'},{fieldName :'organ.id',fieldValue : 'departId'}"/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="punishApprovalDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
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
					layer.alert(
	                result.message, 
	                {icon: 0,closeBtn:0 });
				}
			});
			return false;
		});
		form.on('submit(submit)', function (data) {//表单提交
				var params=data.field;
				params.result="1";
				debugger;
				var url="ofcflow/punish/operationFlow";
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