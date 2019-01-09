<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="考核信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="pubinst/assessment/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${pubinst.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${assessment.id }"></smart:textInput>
										<smart:textInput type="hidden" name="publicInstitution.id" value="${pubinst.id}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="考核年度：" isNotNull="true" verify="required" display="block" name="assessmentYear" id="assessmentYear" value="${assessment.assessmentYear}" format="yyyy"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date labelName="起始日期：" display="block" name="startDate" id="startDate" value="${assessment.startDate}" ></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="截止日期：" display="block" name="endDate" id="endDate" value="${assessment.endDate}" ></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="组织名称：" isNotNull="true" verify="required" placeholder="负责考核组织名称" name="organizationName" value="${assessment.organizationName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="组织成员：" placeholder="负责考核组织成员姓名" name="allParticipantName" value="${assessment.allParticipantName}" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="category" labelName="考核类别：" isNotNull="true" verify="required" inputName="category.id" codeTypeCode="DM017" inputVal="${assessment.category.id}" valType="ID" widthPercent="0.3333"/>
									</smart:gridColumn>
								</smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="conclusionCategory" labelName="考核结论类别：" inputName="conclusionCategory.id" codeTypeCode="DM017" inputVal="${assessment.conclusionCategory.id}" valType="ID" widthPercent="0.33333"/>
									</smart:gridColumn>
								<smart:gridRow>
									
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect id="uncommittedReason" labelName="缺席考核原因：" inputName="uncommittedReason.id" codeTypeCode="DM057" inputVal="${assessment.uncommittedReason.id}" valType="ID" widthPercent="0.33333"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:textarea labelName="原因说明：" placeholder="参加考核不定等次的原因" name="uncertainLvlReason"  display="block">${assessment.uncertainLvlReason}</smart:textarea>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:textarea labelName="考核意见：" placeholder="考核意见" name="opinion"  display="block">${assessment.opinion}</smart:textarea>
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
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:continuousSelectAction/>
		<smart:dateRender id="assessmentYear" format="yyyy"/>
		<smart:dateRender id="startDate"/>
		<smart:dateRender id="endDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('assessmentNavigationList');
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