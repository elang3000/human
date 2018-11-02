<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="简历信息"/>
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
										<smart:textInput type="hidden" id="formerUnit" name="formerUnit" value="${e.formerUnit}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="简历：" verify="required" isNotNull="true" name="introduce" value="${e.introduce}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="曾在单位起始日期：" verify="required" isNotNull="true" display="block" name="startDate" id="startDate" value="${e.startDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="曾在单位终止日期：" verify="required" isNotNull="true" display="block" name="endDate" id="endDate" value="${e.endDate}"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true" id="formerUnitCode" name="formerUnitCode" labelName="曾在单位：" display="block" url="dictquery/sub/code/0302" initSelectedKey="${e.formerUnitCode}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="曾经担任职务：" verify="required" isNotNull="true" name="formerJob" value="${e.formerJob}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="formerJobCategory.id" labelName="曾在单位职务类别：" display="block" url="dictquery/sub/code/DM049" initSelectedKey="${e.formerJobCategory.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput verify="required" isNotNull="true" labelName="简历证明人：" name="reterence" value="${e.reterence}" ></smart:textInput>
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
		<smart:dateRender id="startDate"/>
		<smart:dateRender id="endDate"/>
		form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'保存简历信息',
				message:'确认提交保存吗？',
				url:'ofcflow/eventExperience/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('experienceNavigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		form.on('select(formerUnitCode)', function(data){
		    $('#formerUnit').val(data.elem[data.elem.selectedIndex].text)
		});
	</smart:scriptHead>
</smart:body>
</html>