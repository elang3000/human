<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="录聘信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="pubinst/employ/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${pubinst.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${employ.id }"></smart:textInput>
										<smart:textInput type="hidden" name="publicInstitution.id" value="${pubinst.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否考试录用人员：" name="isExamEmploy.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${employ.isExamEmploy.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="录用考试准考证号：" name="admissionTicket" value="${employ.admissionTicket }"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="专业能力测试成绩：" name="aptitudeTestScore" value="${employ.aptitudeTestScore }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="公共科目笔试成绩 ：" name="publicSubjectTestScore" value="${employ.publicSubjectTestScore }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="笔试（行政职业能力测试）成绩 ：" name="writtenExamTestScore" value="${employ.writtenExamTestScore }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="面试成绩：" name="interviewScore" value="${employ.interviewScore }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="其他科目成绩：" name="otherSubjectScore" value="${employ.otherSubjectScore }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="批准录用日期 ：" display="block" name="approveEmployDate" id="approveEmployDate" value="${employ.approveEmployDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="录用部门：" name="employDept" value="${employ.employDept }"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="录用职位：" name="employJob.id" display="block" url="dictquery/sub/code/GBT_12403_1990" isAddDefaltOption="true" initSelectedKey="${employ.employJob.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="笔试（申论）成绩：" name="explainingScore" value="${employ.explainingScore }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="笔试（专业科目）成绩：" name="professionalSubjectScore" value="${employ.professionalSubjectScore }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="录用时政治面貌：" name="politics.id" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true" initSelectedKey="${employ.politics.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="录用时基层工作时间：" name="basicWorkTime" value="${employ.basicWorkTime }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否退役士兵：" name="isRetiredSoldier.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${employ.isRetiredSoldier.id }"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否退役大学生士兵 ：" name="isRetiredCollegeStudentSoldier.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${employ.isRetiredCollegeStudentSoldier.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否残疾人 ：" name="isdisabled.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${employ.isdisabled.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="留学年限 ：" name="studyAbroadTime" value="${employ.studyAbroadTime }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="海外工作年限 ：" name="workAbroadTime" value="${employ.workAbroadTime }" type="text" display="block"></smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否有海外留学经历：" name="isStudyAbroad.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${employ.isStudyAbroad.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否有海外工作经历 ：" name="isWorkAbroad.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${employ.isWorkAbroad.id }"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect labelName="来源 ：" inputName="shSource.id" codeTypeCode="0126" inputVal="${employ.shSource.id}" valType="ID" widthPercent="0.3333333"/>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="基层项目人员标识 ：" name="isBasicWork.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${employ.isBasicWork.id }"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect labelName="录用时学历：" inputName="educationCode.id" codeTypeCode="GBT_4658_2006" inputVal="${employ.educationCode.id}" valType="ID" widthPercent="0.3333333" isSaveShowName="true" inputShowName="educationName" allOrLast="last"/>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:continuousSelect labelName="录用时学位：" inputName="degreeCode.id" codeTypeCode="GBT_6864_2003" inputVal="${employ.degreeCode.id}" valType="ID" widthPercent="0.3333333" isSaveShowName="true" inputShowName="degreeName" allOrLast="last"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:textarea labelName="人员来源情况：" name="sourceSituation" display="block">${employ.sourceSituation}</smart:textarea>
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
		<smart:dateRender id="approveEmployDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('employNavigationList');
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