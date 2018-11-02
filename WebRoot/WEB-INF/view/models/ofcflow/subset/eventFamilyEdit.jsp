<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="家庭成员信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${e.personName}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${e.id}"></smart:textInput>
										<smart:textInput type="hidden" name="personName" value="${e.personName}"></smart:textInput>
										<smart:textInput type="hidden" name="cardNo" value="${e.cardNo}"></smart:textInput>
										<smart:textInput type="hidden" id="relationName" name="relationName" value="${e.relationName}" ></smart:textInput>
										<smart:textInput type="hidden" id="politicalName" name="politicalName" value="${e.politicalName}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="姓名：" name="name" value="${e.name}" isNotNull="true" verify="required"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" isNotNull="true" verify="required" id="relationCode" name="relationCode.id" labelName="关系：" display="block" url="dictquery/sub/code/GBT_4761_2008" initSelectedKey="${e.relationCode.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="出生日期：" verify="required" isNotNull="true" display="block" name="birthDate" id="birthDate" value="${e.birthDate}"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="sex.id" labelName="性别：" display="block" url="dictquery/sub/code/GBT_2261_1_2003" initSelectedKey="${e.sex.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="身份证号：" verify="required" isNotNull="true" name="identityNo" value="${e.identityNo }" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="工作单位及职务：" verify="required" isNotNull="true" name="unitAndJob" value="${e.unitAndJob }" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="nationality.id" initSelectedKey="${e.nationality.id}" labelName="国籍：" display="block" url="dictquery/sub/code/GBT_2260_2007"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="nation.id" initSelectedKey="${e.nation.id}" labelName="民族：" display="block" url="dictquery/sub/code/GBT_3304_1991" ></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="education.id" initSelectedKey="${e.education.id}" labelName="学历：" display="block" url="dictquery/sub/code/GBT_4658_2006" ></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" id="politicalCode" name="politicalCode.id" initSelectedKey="${e.politicalCode.id}" labelName="政治面貌：" display="block" url="dictquery/sub/code/GBT_4762_1984" ></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="夫妻两地分居日期：" display="block" name="separationStartDate" id="separationStartDate" value="${e.separationStartDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="socialPosition.id" initSelectedKey="${e.socialPosition.id}" labelName="身份：" display="block" url="dictquery/sub/code/GBT_2261_4_2003" ></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="职务：" name="post" value="${e.post}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="rank.id" initSelectedKey="${e.rank.id}" labelName="职级：" display="block" url="dictquery/sub/code/GBT_12407_2008" ></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" name="presentSituation.id" initSelectedKey="${e.presentSituation.id}" labelName="现状：" display="block" url="dictquery/sub/code/DM125" ></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput min="0" labelName="序号:" name="sortNo" value="${post.sortNo}"  display="block" type="text"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="备注：" name="remark" value="${e.remark}" ></smart:textInput>
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
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="birthDate"/>
		<smart:dateRender id="separationStartDate"/>
		form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'保存家庭成员信息',
				message:'确认提交保存吗？',
				url:'ofcflow/eventFamily/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('familyNavigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		form.on('select(relationCode)', function(data){
		    $('#relationName').val(data.elem[data.elem.selectedIndex].text)
		});
		form.on('select(politicalCode)', function(data){
		    $('#politicalName').val(data.elem[data.elem.selectedIndex].text)
		});
	</smart:scriptHead>
</smart:body>
</html>