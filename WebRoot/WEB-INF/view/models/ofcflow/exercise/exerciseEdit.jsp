<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--同类别转任管理" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="挂职锻炼" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<smart:gridRow>
						<smart:title title="公务员基本信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:selectResource labelName="姓名:" id="selectNameTag" display="block"/>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="身份证号：" name="cardNo" display="block" value="${s.cardNo}"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="性别：" name="sex" display="block" value="${s.sex.name}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="出生日期：" name="birthDate" display="block" value="${s.birthDate}"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="民族：" name="nation" display="block" value="${s.nation.name}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="政治面貌：" name="politics" display="block" value="${s.politics.name}"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="出生地：" name="birthPlaceName" display="block" value="${s.birthPlaceName}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="籍贯：" name="nativePlaceName" display="block" value="${s.nativePlaceName}"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="人员类别：" name="personType" display="block" value="${s.personType.name}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:textInput otherAttr="disabled" labelName="健康状况：" name="health" display="block" value="${s.health.name}"></smart:textInput>
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
						<smart:title title="挂职锻炼信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="去向单位名称:" isNotNull="true" verify="required" value="${d.targetOrgan}" name="targetOrgan"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:date isNotNull="true" verify="required"
										labelName="挂职锻炼开始时间：" name="startDate" id="startDate"
										value="${d.startDate}" display="block"></smart:date>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:date isNotNull="true" verify="required"
										labelName="挂职锻炼结束时间：" name="endDate" id="endDate"
										value="${d.endDate}" display="block"></smart:date>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="12">
							<smart:textarea labelName="备注：" name="remark"  display="block">${d.remark}</smart:textarea>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<smart:button id="submit" other="lay-submit" size="sm"
									title="备案" theme="normal">
									<smart:icon icon="check">&nbsp;备案</smart:icon>
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
	<smart:scriptHead models="table,form,layer,element,selectResource,laydate">
		<smart:dateRender id="startDate" />
		<smart:dateRender id="endDate" />
		<smart:initSelectResource value="${s.name}" hiddenValue="${s.id}" id = "selectNameTag" name="servant.name" hiddenName="servant.id" winSize="lg" winTitle="选择人员信息" winUrl="orgInfo/selectServant?createOrganNodeId=${createOrganNodeId}" 
		linkElement="{fieldName :'cardNo',fieldValue : 'cardNo'},{fieldName :'sex',fieldValue : 'sex'},{fieldName :'birthDate',fieldValue : 'birthDate'},{fieldName :'nation',fieldValue : 'nation'},{fieldName :'politics',fieldValue : 'politics'}
		,{fieldName :'birthPlaceName',fieldValue : 'birthPlaceName'},{fieldName :'nativePlaceName',fieldValue : 'nativePlaceName'},{fieldName :'personType',fieldValue : 'personType'},{fieldName :'health',fieldValue : 'health'}"/>
		<smart:buttonScriptAction>
			goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
			smart.confirm({
				title:'保存挂职锻炼信息',
				message:'确认提交保存吗？',
				url:'ofcflow/exercise/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
 		form.on('submit(submit)', function (data) {//表单提交
 			smart.confirm({
				title:'保存挂职锻炼信息',
				message:'确认提交保存吗？',
				url:'ofcflow/exercise/operationFlow',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>