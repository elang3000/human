<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--跨类别转任管理" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="跨类别转任管理" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<smart:textInput type="hidden" name="result" id="result"></smart:textInput>
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:textInput isNotNull="true" verify="required"
										labelName="姓名：" name="name" id="name"
										value="${d.name}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:textInput isNotNull="true" verify="required"
										labelName="身份证：" name="cardNo" 
										id="cardNo" value="${d.cardNo}"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:singleSelect isNotNull="true" verify="required"
										name="sex.id" labelName="性别：" display="block"
										initSelectedKey="${d.sex.id}"  isAddDefaltOption="true"
										url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:date style="aa" isNotNull="true" verify="required"
										labelName="出生日期：" name="birthDate" id="birthDate"
										value="${d.birthDate}" display="block"></smart:date>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:singleSelect isNotNull="true" verify="required"
										name="nation.id" labelName="民族：" display="block"
										initSelectedKey="${d.nation.id}" isSearch="true" isAddDefaltOption="true"
										url="dictquery/sub/code/GBT_3304_1991"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:singleSelect labelName="政治面貌：" name="politics.id"  isAddDefaltOption="true"
										initSelectedKey="${d.politics.id}" display="block" isSearch="true"
										url="dictquery/sub/code/GBT_4762_1984"></smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:singleSelect isNotNull="true" verify="required"  isAddDefaltOption="true"
										name="personType.id" labelName="人员类别：" display="block"
										initSelectedKey="${d.personType.id}"
										url="dictquery/sub/code/DM199/1"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:singleSelect labelName="健康状况：" name="health.id"
										initSelectedKey="${d.health.id}" display="block"  isAddDefaltOption="true"
										url="dictquery/sub/code/GBT_2261_3_2003"></smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:continuousSelect verify="required" id="birthPlace" labelName="出生地：" inputName="birthPlace.id" codeTypeCode="GBT_2260_2007" inputVal="${d.birthPlace.id}" valType="ID" widthPercent="0.4" isSaveShowName="true" inputShowName="birthPlaceName"/>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:continuousSelect verify="required" id="nativePlace" labelName="籍贯：" inputName="nativePlace.id" codeTypeCode="GBT_2260_2007" inputVal="${d.nativePlace.id}" valType="ID" widthPercent="0.4" isSaveShowName="true" inputShowName="nativePlaceName"/>
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
						<smart:title title="转入信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:textInput id="postName" name="postName" type="hidden"
						value="${d.postName}"></smart:textInput>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:singleSelect id="postCode" isNotNull="true"
								verify="required" initSelectedKey="${d.postCode.id}"
								name="postCode.id" labelName="拟任职务："
								url="dictquery/sub/code/GBT_12403_1990" display="block"
								isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect initSelectedKey="${d.attribute.id}"
								name="attribute.id" labelName="拟任职务属性："
								url="dictquery/sub/code/DM049" display="block"
								isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect initSelectedKey="${d.causeType.id}"
								name="causeType.id" labelName="转任事由：" isNotNull="true"
								verify="required" url="dictquery/sub/code/causeType"
								display="block" isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="12">
							<smart:textarea name="specificCause" labelName="具体事由："
								display="block">${d.specificCause}</smart:textarea>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:singleSelect
								initSelectedKey="${d.enterTheUnitChangeType.id}"
								name="enterTheUnitChangeType.id" labelName="进入本单位变动类别："
								isNotNull="true" verify="required"
								url="dictquery/sub/code/GBT_12405_2008/40" display="block"
								isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
						<smart:gridColumn colPart="8">
							<smart:continuousSelect id="enterReason" labelName="进入本单位原因："
								inputName="enterReason.id" codeTypeCode="DM015"
								inputVal="${d.enterReason.id}" valType="ID" />
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
							<smart:date labelName="进入本单位日期：" display="block"
								name="enterTheUnitDate" id="enterTheUnitDate"
								value="${d.enterTheUnitDate}"></smart:date>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:numberInput min="0" labelName="进入本单位时基层工作经历时间（月）:"
								name="intoBasicWorkTime" value="${d.intoBasicWorkTime}"
								display="block" type="text"></smart:numberInput>
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
								<smart:button id="submit" other="lay-submit" size="sm"
									title="提交" theme="normal">
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
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:fileUploadUtils/>
		<smart:dateRender id="birthDate" />
		<smart:dateRender id="enterTheUnitDate" />
		form.on('submit(save)', function (data) {//表单保存
			smart.confirm({
				title:'保存转任信息',
				message:'确认提交保存吗？',
				url:'ofcflow/zrklbInto/saveOuter',
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
				title:'保存转任信息',
				message:'确认提交保存吗？',
				url:'ofcflow/zrklbInto/operationFlowOuter',
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