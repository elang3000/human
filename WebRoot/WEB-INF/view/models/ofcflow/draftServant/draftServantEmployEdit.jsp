<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
					<smart:breadcrumbNavMenuItem iname="拟录用名单列表"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="编辑录用信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:form id="editForm">
						<smart:textInput name="id" value="${d.id}" type="hidden"></smart:textInput>
						<smart:gridRow>
							<smart:title title="人员信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="姓名" infovalue="${d.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="身份证号"
									infovalue="${d.cardNo }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="性别" infovalue="${d.sex.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
<%-- 								<smart:infoShowerLabel infoname="出生日期" infovalue="${d.birthDate }"></smart:infoShowerLabel> --%>
								<smart:date isNotNull="true" verify="required" labelName="出生日期：" value="${d.birthDate}" name="birthDate" id="birthDate" display="block"></smart:date>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="出生地" infovalue="${d.birthPlaceName }"></smart:infoShowerLabel> --%>
								<smart:singleSelect isSearch="true" isNotNull="true" verify="required" name="birthPlace.id" labelName="出生地：" initSelectedKey="${d.birthPlace.id}" display="block" url="dictquery/sub/id/GBT_2260_2007/null" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="籍贯" infovalue="${d.nativePlaceName }"></smart:infoShowerLabel> --%>
								<smart:singleSelect isNotNull="true" verify="required" name="nativePlace.id" labelName="籍贯：" initSelectedKey="${d.nativePlace.id}" display="block" url="dictquery/sub/id/GBT_2260_2007/null" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="民族" infovalue="${d.nation.name }"></smart:infoShowerLabel> --%>
								<smart:singleSelect isNotNull="true" verify="required" name="nation.id" labelName="民族：" initSelectedKey="${d.nation.id}" display="block" url="dictquery/sub/id/GBT_3304_1991/null" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="政治面貌" infovalue="${d.politics.name }"></smart:infoShowerLabel> --%>
								<smart:singleSelect labelName="政治面貌：" name="politics.id" initSelectedKey="${d.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="是否退役士兵" infovalue="${d.isRetiredSoldier.name }"></smart:infoShowerLabel> --%>
								<smart:singleSelect labelName="是否退役士兵：" name="isRetiredSoldier.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${d.isRetiredSoldier.id }"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="是否退役大学生士兵" infovalue="${d.isRetiredCollegeStudentSoldier.name }"></smart:infoShowerLabel> --%>
								<smart:singleSelect labelName="是否退役大学生士兵 ：" name="isRetiredCollegeStudentSoldier.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${d.isRetiredCollegeStudentSoldier.id }"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="是否残疾人" infovalue="${d.isdisabled.name }"></smart:infoShowerLabel> --%>
								<smart:singleSelect labelName="是否残疾人 ：" name="isdisabled.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${d.isdisabled.id }"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="是否有海外留学经历" infovalue="${d.isStudyAbroad.name }"></smart:infoShowerLabel> --%>
								<%-- <smart:singleSelect labelName="是否有海外留学经历：" name="isStudyAbroad.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${d.isStudyAbroad.id }"></smart:singleSelect> --%>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="留学年限" infovalue="${d.studyAbroadTime }"></smart:infoShowerLabel> --%>
								<smart:singleSelect labelName="是否有海外留学经历：" name="isStudyAbroad.id" display="block" url="dictquery/sub/code/DM215"  isAddDefaltOption="true" initSelectedKey="${d.isStudyAbroad.id }"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="是否有海外工作经历" infovalue="${d.isWorkAbroad.name }"></smart:infoShowerLabel> --%>
								<smart:singleSelect labelName="是否有海外工作经历 ：" name="isWorkAbroad.id" display="block" url="dictquery/sub/code/DM215" isAddDefaltOption="true" initSelectedKey="${d.isWorkAbroad.id }"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<%-- <smart:infoShowerLabel infoname="海外工作年限" infovalue="${d.workAbroadTime }"></smart:infoShowerLabel> --%>
								<smart:numberInput labelName="海外工作年限 ：" name="workAbroadTime" value="${d.workAbroadTime }" type="text" display="block"></smart:numberInput>
							</smart:gridColumn>
						</smart:gridRow>
						<%-- <smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="来源" infovalue="${d.source.name }"></smart:infoShowerLabel>
								<smart:continuousSelect labelName="来源 ：" inputName="shSource.id" codeTypeCode="0126" inputVal="${d.source.id}" valType="ID" widthPercent="0.3333333"/>
							</smart:gridColumn>
						</smart:gridRow> --%>
						<smart:gridRow>
							<smart:title title="招录信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
	<%-- 						<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="拟录用单位" infovalue="${d.draftUnitName }"></smart:infoShowerLabel>
								<smart:singleSelect labelName="拟录用单位" name="draftUnit.id" display="block" url="ofcflow/draftServant/getUnitSelect" isAddDefaltOption="true" initSelectedKey="${d.draftUnit.id}"></smart:singleSelect>
							</smart:gridColumn> --%>
							<smart:gridColumn colPart="4">
								<%-- <smart:textInput labelName="体检" name="physicalEXAM"  verify="required" value="${d.physicalEXAM }"></smart:textInput> --%>
								<smart:textInput labelName="拟录用部门" name="draftDeptName"  value="${d.draftDeptName}"></smart:textInput>
								<%-- <smart:infoShowerLabel infoname="拟录用部门" infovalue="${d.draftDeptName }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								
								<smart:textInput labelName="职位名称" name="employJobName"  value="${d.employJobName }"></smart:textInput>
								<%-- <smart:infoShowerLabel infoname="职位名称" infovalue="${d.employJobName }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
														<smart:gridColumn colPart="4">
								<smart:textInput labelName="政审考核" name="politicalExam" value="${d.politicalExam }"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
							<smart:textInput labelName="录用考试准考证号：" name="ticketId" value="${d.ticketId }"></smart:textInput>
								<%-- <smart:infoShowerLabel infoname="录用考试准考证号" infovalue="${d.ticketId }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							<smart:numberInput labelName="专业能力测试成绩：" name="aptitudeTestScore" value="${d.aptitudeTestScore }" type="text" display="block"></smart:numberInput>
								<%-- <smart:infoShowerLabel infoname="专业能力测试成绩" infovalue="${d.aptitudeTestScore }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							<smart:numberInput labelName="公共科目笔试成绩 ：" name="publicSubjectTestScore" value="${d.publicSubjectTestScore }" type="text" display="block"></smart:numberInput>
<%-- 								<smart:infoShowerLabel infoname="公共科目笔试成绩" infovalue="${d.publicSubjectTestScore }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
							<smart:numberInput labelName="笔试（行政职业能力测试）成绩 ：" name="writtenExamTestScore" value="${d.writtenExamTestScore }" type="text" display="block"></smart:numberInput>
<%-- 								<smart:infoShowerLabel infoname="笔试（行政职业能力测试）成绩" infovalue="${d.writtenExamTestScore }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							<smart:numberInput labelName="笔试（申论）成绩：" name="explainingScore" value="${d.explainingScore }" type="text" display="block"></smart:numberInput>
								<%-- <smart:infoShowerLabel infoname="笔试（申论）成绩" infovalue="${d.explainingScore }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							<smart:numberInput labelName="笔试（专业科目）成绩：" name="professionalSubjectScore" value="${d.professionalSubjectScore }" type="text" display="block"></smart:numberInput>
								<%-- <smart:infoShowerLabel infoname="笔试（专业科目）成绩" infovalue="${d.professionalSubjectScore }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
							<smart:numberInput labelName="面试成绩：" name="interviewScore" value="${d.interviewScore }" type="text" display="block"></smart:numberInput>
								<%-- <smart:infoShowerLabel infoname="面试成绩" infovalue="${d.interviewScore }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
							<smart:numberInput labelName="其他科目成绩：" name="otherSubjectScore" value="${d.otherSubjectScore }" type="text" display="block"></smart:numberInput>
								<%-- <smart:infoShowerLabel infoname="其他科目成绩" infovalue="${d.otherSubjectScore }"></smart:infoShowerLabel> --%>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="体检" name="physicalEXAM"  value="${d.physicalEXAM }"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<c:if test="${eduList.size()>1 }">
							<smart:gridRow>
								<smart:title title="学历学位信息" style="margin-top: 5px;" color="blue" />
							</smart:gridRow>
                        </c:if>
                        <c:forEach items="${eduList}" var="edu" varStatus="status">
                        	<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="学历学位信息${status.index+1 }" infovalue=""></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="学历名称" infovalue="${edu.educationName }"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="学位名称" infovalue="${edu.degreeName }"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="学习形式" infovalue="${edu.studyForm.name }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="学制（月）" infovalue="${edu.eductionalSystem }"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="毕（肄）业日期" infovalue="${edu.graduateDate }"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="所学专业名称" infovalue="${edu.professionName }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="学校" infovalue="${edu.schoolName }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
                       	</c:forEach>
						<smart:gridRow>
							<smart:title title="录用信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="录用单位" infovalue="${d.draftUnitName }"></smart:infoShowerLabel>
							</smart:gridColumn>
<%-- 							<smart:gridColumn colPart="4">
								<smart:textInput labelName="录用部门:" placeholder="录用部门" name="draftDeptName" value="${d.draftDeptName}"></smart:textInput>
							</smart:gridColumn> --%>
							<smart:gridColumn colPart="4">
								<smart:date display="block" labelName="参加工作年月" id="time3" value="${d.attendDate}" name="attendDate" isNotNull="true" verify="required"></smart:date>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="原单位名称:" placeholder="原单位名称" value="${d.yuanUnitName}" name="yuanUnitName"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" verify="required" display="block" labelName="原单位性质:" initSelectedKey="${d.yuanUnitType.id}" name="yuanUnitType.id" url="dictquery/sub/code/DM142"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" verify="required" display="block" labelName="免基层实习:" initSelectedKey="${d.isBasePractice.id}" name="isBasePractice.id" url="dictquery/sub/code/DM215"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:date isNotNull="true" verify="required" display="block" labelName="所在单位意见时间:" isDefaultVal="true" value="${d.deptOpinionDate}" name="deptOpinionDate" id="time" placeholder="时间"></smart:date>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
						
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="上级主管"  value="${d.supervisor}" name="supervisor"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:date display="block" labelName="上级主管单位意见时间:" id="time2" value="${d.unitOpinionDate}" name="unitOpinionDate" placeholder="时间"></smart:date>
							</smart:gridColumn>
							
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" verify="required" display="block" labelName="录用情况:" initSelectedKey="${d.employSituation.id}" name="employSituation.id" url="dictquery/sub/code/DraftServantEmployStatus"></smart:singleSelect>
							</smart:gridColumn>

						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:date display="block" id="probationStartTime" labelName="试用期开始时间:" isNotNull="true"  verify="required" value="${d.probationStartTime}" name="probationStartTime" placeholder="时间"></smart:date>
							</smart:gridColumn>
							<c:if test="${!empty report.isAgree}">
							<smart:gridColumn colPart="4"  colOffset="1">
								<smart:button id="download" size="sm" method="download" title="下载电子介绍信">
									<smart:icon icon="plus">&nbsp;下载电子介绍信</smart:icon>
								</smart:button>
																		<c:if test="${!empty d.isElecLetter}">
												已确认电子介绍信
										</c:if>
							</smart:gridColumn>
							</c:if>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea display="block" labelName="备注:" name="remark">${d.remark}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea isNotNull="true" display="block" verify="required" labelName="录用鉴定（评语）:" name="employComment">${d.employComment}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:line color="blue" />
							<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
								<smart:buttonGroup container="true">
									<c:if test="${!empty report.isAgree}">
										<c:if test="${empty d.isElecLetter}">
											<c:if test="${isCurrentOrgan}">
												<smart:button id="save" other="lay-submit" size="sm" method="add" title="确认电子介绍信,录用人员试用期开始">
													<smart:icon icon="check">&nbsp;确认电子介绍信</smart:icon>
												</smart:button>
											</c:if>
										</c:if>
									</c:if>
									<smart:button size="sm" method="temporary" title="暂存">
										<smart:icon icon="plus">&nbsp;暂存</smart:icon>
									</smart:button>
									<smart:button theme="warm" size="sm" method="back" title="返回">
										<smart:icon icon="reply">&nbsp;返回</smart:icon>
									</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>
					
				</smart:gridRow>

			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils/>

		<smart:buttonScriptAction>
			back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			},
			download : function() {
			
				var probationStartTime="${d.probationStartTime }";
				if(probationStartTime==null||probationStartTime==undefined||probationStartTime==""){
						layer.alert(
		               "请先保存试用期开始时间!", 
		                {icon: 0,closeBtn:0 });
				}else{
					//通过form下载
		            var $eleForm = $("<form method='post'></form>");
		 
		            $eleForm.attr("action","ofcflow/draftServantReportResult/exportDzjsx?id=${d.id}");
		 
		            $(document.body).append($eleForm);
		 
		            //提交表单，实现下载
		            $eleForm.submit();
				}
				
			},
			temporary:function(){
				smart.confirm({
					title:'保存录用信息',
					message:'确认提交保存吗？',
					url:'ofcflow/draftServant/updateDraft',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			}
		 </smart:buttonScriptAction>
		<smart:dateRender id="time" />
		<smart:dateRender id="time2" />
		<smart:dateRender id="time3" />
		<smart:dateRender id="birthDate"/>
		<smart:dateRender id="probationStartTime"/>
		
		form.on('submit(save)', function (data) {//确认电子介绍信
		
				smart.confirm({
					title:'保存录用信息',
					message:'确认提交保存吗？',
					url:'ofcflow/draftServant/confirmLetter',
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