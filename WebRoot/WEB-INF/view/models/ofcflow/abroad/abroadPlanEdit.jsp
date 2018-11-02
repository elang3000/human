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
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="因公出国政审"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="启动因公出国计划" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm" action="ofcflow/abroad/saveAbroadPlan">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:textInput type="hidden" id="id" name="id" value="${abroadPlan.id }"></smart:textInput>
									<smart:textInput type="hidden" id="status" name="status" value="1"></smart:textInput>
									<smart:title title="因公出国信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="因公出国计划：" display="block" isNotNull="true" id="myida" verify="required" name="yearPlan.id" initSelectedKey="${abroadYearPlan.id}" url="ofcflow/abroad/year/find"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
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
									<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								<smart:gridRow>
								<smart:gridColumn colPart="1">
									<smart:fileUpload accept="file" buttonName="附件" dataName="abroadFtp" auto="false" name="filePathName" size="20000" multiple="true" number="7" data="${abroadPlan.abroadFtp}"/>
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
	<smart:scriptHead models="table,form,layer,element,laydate,,selectResource,upload">
		<smart:utils />
		<smart:fileUploadUtils/>
		<smart:initSelectResource value="${servant.name}" hiddenValue="${servant.id}" id = "selectNameTag" name="servant.name" hiddenName="servant.id" winSize="lg" winTitle="选择人员信息" winUrl="orgInfo/selectServant?createOrganNodeId=${x.id}" 
		linkElement="{fieldName :'cardNo',fieldValue : 'cardNo'},{fieldName :'sex',fieldValue : 'sex'},{fieldName :'birthDate',fieldValue : 'birthDate'},{fieldName :'nation',fieldValue : 'nation'},{fieldName :'politics',fieldValue : 'politics'}
		,{fieldName :'birthPlaceName',fieldValue : 'birthPlaceName'},{fieldName :'nativePlaceName',fieldValue : 'nativePlaceName'},{fieldName :'personType',fieldValue : 'personType'},{fieldName :'health',fieldValue : 'health'},{fieldName :'organ.id',fieldValue : 'departId'}"/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
		var params=data.field;
		var url=data.form.action;
		var requestConfig = {};
		requestConfig.url = "ofcflow/abroad/plan/validate";
		requestConfig.data = {yearPlanId:$("#myida").val()};
		requestConfig.success = function(result){
			if (result.success) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.full(index);
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
			}
			layer.msg(result.message);
		}
		smart.request(requestConfig);
	});
	
	form.on('submit(submit)', function (data) {//表单保存
		var params=data.field;
		params.result="1";
		var url="ofcflow/abroad/operationPlan";
		var requestConfig = {};
		requestConfig.url = "ofcflow/abroad/plan/validate";
		requestConfig.data = {yearPlanId:$("#myida").val()};
		requestConfig.success = function(result){
			if (result.success) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.full(index);
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
			}
			layer.msg(result.message);
		}
		smart.request(requestConfig);
	});
	</smart:scriptHead>
</smart:body>
</html>