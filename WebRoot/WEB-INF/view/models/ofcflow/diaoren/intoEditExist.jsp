<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--公务员调任管理" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员调任管理" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<c:if test="${sourceType eq '1'}">
						<smart:textInput type="hidden" name="publicInstitution.id" value="${s.id}"></smart:textInput>
					</c:if>
					<c:if test="${sourceType eq '2'}">
						<smart:textInput type="hidden" name="nationalCompany.id" value="${s.id}"></smart:textInput>
					</c:if>
					<smart:textInput type="hidden" name="name" value="${s.name}"></smart:textInput>
					<smart:textInput type="hidden" name="cardNo" value="${s.cardNo}"></smart:textInput>
					<smart:textInput type="hidden" name="sourceType" value="${sourceType}"></smart:textInput>
					<smart:textInput type="hidden" name="result" id="result"></smart:textInput>
					<smart:textInput type="hidden" name="allowWeaveNum"  value="${d.allowWeaveNum}" ></smart:textInput>
					<smart:textInput type="hidden" name="realNum"  value="${d.realNum}" ></smart:textInput>
					<smart:textInput type="hidden" name="thisYearLackWeaveNum"  value="${d.thisYearLackWeaveNum}" ></smart:textInput>
					<smart:textInput type="hidden" name="chiefLackWeaveNum"  value="${d.chiefLackWeaveNum}" ></smart:textInput>
					<smart:textInput type="hidden" name="vacancySectionChiefLevelNumber"  value="${d.vacancySectionChiefLevelNumber}" ></smart:textInput>
					<smart:textInput type="hidden" name="vacancyNonLeaderSectionChiefLevelNumber"  value="${d.vacancyNonLeaderSectionChiefLevelNumber}" ></smart:textInput>
					<smart:textInput type="hidden" name="notIntoSectionChiefNum"  value="${d.notIntoSectionChiefNum}" ></smart:textInput>
					<smart:textInput type="hidden" name="notIntoDeputySectionChiefNum"  value="${d.notIntoDeputySectionChiefNum}" ></smart:textInput>
					<smart:textInput type="hidden" name="notIntoNum"  value="${d.notIntoNum}" ></smart:textInput>
					<smart:gridRow>
						<smart:title title="机构编制信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="机构编制数" infovalue="${d.allowWeaveNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="机构实有人数" infovalue="${d.realNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="机构缺编数" infovalue="${d.thisYearLackWeaveNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="处级实职缺编人数" shortName="处级实职缺编数" infovalue="${d.chiefLackWeaveNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="科级(领导)实职缺编人数" infovalue="${d.vacancySectionChiefLevelNumber}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="科级(非领导)实职缺编人数" infovalue="${d.vacancyNonLeaderSectionChiefLevelNumber}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="科级领导未调入数 " infovalue="${d.notIntoSectionChiefNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="科级非领导未调入数" infovalue="${d.notIntoDeputySectionChiefNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="尚未调入人数" infovalue="${d.notIntoNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:title title="人员基本信息" style="margin-top: 5px;" color="blue" />
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
									<img style="width:150px;height:200px;min-width:150px;min-height:200px;" alt="照片" src="ftp/getImg?imgName=${s.photoPath}">
								</smart:gridColumn>
							</smart:gridRow>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:title title="调入信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:textInput id="postName" name="postName" type="hidden" value="${d.postName}"></smart:textInput>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="原工作单位" infovalue="${s.departName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect id="postCode" isNotNull="true" verify="required"
								initSelectedKey="${d.postCode.id}" name="postCode.id"
								labelName="拟任职务：" url="dictquery/sub/code/GBT_12403_1990"
								display="block" isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect initSelectedKey="${d.attribute.id}" name="attribute.id"
								labelName="拟任职务属性：" url="dictquery/sub/code/DM049"
								display="block" isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:singleSelect isNotNull="true" verify="required"
								name="personType.id" labelName="调任后人员类别：" display="block"
								initSelectedKey="${d.personType.id}"
								url="dictquery/sub/code/DM199/1"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect initSelectedKey="${d.enterTheUnitChangeType.id}" name="enterTheUnitChangeType.id"
								labelName="进入本单位变动类别：" shortName="变动类别" isNotNull="true" verify="required" url="dictquery/sub/code/GBT_12405_2008/10"
								display="block" isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect labelName="职级名称：" name="jobLevelCode.id"
								display="block" url="dictquery/sub/code/GBT_12407_2008/1"
								isAddDefaltOption="true" initSelectedKey="${d.jobLevelCode.id }"
								verify="required" isNotNull="true" isSaveShowName="true" id="jobLevelName"
								inputShowName="jobLevelName" initIncludeKey="141,142,150,160"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:continuousSelect id="enterReason" labelName="进入本单位原因：" inputName="enterReason.id" codeTypeCode="DM015" inputVal="${d.enterReason.id}" valType="ID"/>
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
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="enterLocalRegionDate" />
		<smart:dateRender id="enterTheSystemDate" />
		<smart:dateRender id="selectionDate" />
		<smart:dateRender id="employDate" />
		form.on('submit(save)', function (data) {//表单保存
			smart.confirm({
				title:'保存调任信息',
				message:'确认提交保存吗？',
				url:'ofcflow/diaoren/save',
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
				title:'保存调任信息',
				message:'确认提交保存吗？',
				url:'ofcflow/diaoren/operationFlow',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		form.on('select(postCode)', function(data){
		    $('#postName').val(data.elem[data.elem.selectedIndex].text)
		});
	</smart:scriptHead>
</smart:body>
</html>