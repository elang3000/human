<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--参公交流管理" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="参公交流管理" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<smart:textInput type="hidden" name="servant.id" value="${s.id}"></smart:textInput>
					<smart:textInput type="hidden" name="name" value="${s.name}"></smart:textInput>
					<smart:textInput type="hidden" name="cardNo" value="${s.cardNo}"></smart:textInput>
					<smart:textInput type="hidden" name="result" id="result"></smart:textInput>
					<smart:gridRow>
						<smart:title title="公务员基本信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="姓名" infovalue="${s.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="身份证" infovalue="${s.cardNo}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="性别" infovalue="${s.sex.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="出生日期" infovalue="${s.birthDate}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="民族" infovalue="${s.nation.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="政治面貌" infovalue="${s.politics.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="出生地" infovalue="${s.birthPlaceName}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="籍贯" infovalue="${s.nativePlaceName}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="人员类别" infovalue="${s.personType.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="健康状况" infovalue="${s.health.name}"></smart:infoShowerLabel>
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
						<smart:title title="交流信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="原工作单位" infovalue="${s.departName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect id="postCode" isNotNull="true"
								verify="required" initSelectedKey="${d.postCode.id}"
								name="postCode.id" labelName="职务："
								url="dictquery/sub/code/GBT_12403_1990" display="block"
								isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect initSelectedKey="${d.reason.id}"
								name="reason.id" labelName="交流原因："
								url="dictquery/sub/code/DM083" display="block"
								isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:singleSelect labelName="职级名称：" name="jobLevelCode.id"
								display="block" url="dictquery/sub/code/GBT_12407_2008/1"
								isAddDefaltOption="true" initSelectedKey="${d.jobLevelCode.id }"
								verify="required" isNotNull="true" isSaveShowName="true"
								inputShowName="jobLevelName" initCludeKey="[141,142,150,160]"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect isNotNull="true" verify="required"
								name="personType.id" labelName="交流后人员类别：" display="block"
								initSelectedKey="${d.personType.id}"
								url="dictquery/sub/code/DM199/1"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
							<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="1">
								<smart:fileUpload accept="file" buttonName="附件" dataName="filePath" auto="false" name="filePathName" size="20000" multiple="true" number="7" data="${d.filePath}"/>
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
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:utils />
		<smart:fileUploadUtils/>
		<smart:dateRender id="startDate" />
		<smart:dateRender id="endDate" />
		<smart:buttonScriptAction>
			goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
			smart.confirm({
				title:'保存参公交流信息',
				message:'确认提交保存吗？',
				url:'ofcflow/exchange/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
 		form.on('submit(submit)', function (data) {//表单提交
 			$("#result").val("1");
 			smart.confirm({
				title:'保存参公交流信息',
				message:'确认提交保存吗？',
				url:'ofcflow/exchange/operationFlow',
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