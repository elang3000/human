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
					<smart:breadcrumbNavMenuItem iname="查看录用信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:form id="editForm">
						<smart:textInput name="id" value="${p.id}" type="hidden"></smart:textInput>
						<smart:textInput name="result" id="result" type="hidden"></smart:textInput>
						<smart:textInput name="draftServant.id" value="${d.id}" type="hidden"></smart:textInput>
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
								<smart:infoShowerLabel infoname="出生日期" infovalue="${d.birthDate }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="出生地" infovalue="${d.birthPlaceName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="籍贯" infovalue="${d.nativePlaceName }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="民族" infovalue="${d.nation.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="政治面貌" infovalue="${d.politics.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="是否退役士兵" infovalue="${d.isRetiredSoldier.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="是否退役大学生士兵" infovalue="${d.isRetiredCollegeStudentSoldier.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="是否残疾人" infovalue="${d.isdisabled.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="是否有海外留学经历" infovalue="${d.isStudyAbroad.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="留学年限" infovalue="${d.studyAbroadTime }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="是否有海外工作经历" infovalue="${d.isWorkAbroad.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="海外工作年限" infovalue="${d.workAbroadTime }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="来源" infovalue="${d.source.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:title title="招录信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="拟录用单位" infovalue="${d.draftUnitName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="拟录用部门" infovalue="${d.draftDeptName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="职位名称" infovalue="${d.employJobName }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="录用考试准考证号" infovalue="${d.ticketId }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="专业能力测试成绩" infovalue="${d.aptitudeTestScore }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="公共科目笔试成绩" infovalue="${d.publicSubjectTestScore }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="笔试（行政职业能力测试）成绩" infovalue="${d.writtenExamTestScore }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="笔试（申论）成绩" infovalue="${d.explainingScore }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="笔试（专业科目）成绩" infovalue="${d.professionalSubjectScore }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="面试成绩" infovalue="${d.interviewScore }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="其他科目成绩" infovalue="${d.otherSubjectScore }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="体检" infovalue="${d.physicalEXAM }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="政审考核" infovalue="${d.politicalExam }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:title title="录用信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="录用单位" infovalue="${d.draftUnitName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="录用部门" infovalue="${d.draftDeptName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="原单位名称" infovalue="${d.yuanUnitName }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="原单位性质" infovalue="${d.yuanUnitType.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="免基层实习" infovalue="${d.isBasePractice.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="所在单位意见时间" infovalue="${d.deptOpinionDate }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="上级主管单位意见时间" infovalue="${d.unitOpinionDate }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="录用标识" infovalue="${d.employResult.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="录用情况" infovalue="${d.employSituation.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="备注" infovalue="${d.remark }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="录用鉴定（评语）" infovalue="${d.employComment }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<c:if test="${'1' eq p.isDelay}">
							<smart:gridRow>
								<smart:title title="延期信息" style="margin-top: 5px;" color="blue" />
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="延期时长(月)" infovalue="${p.delayDateDone }"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="延期理由" infovalue="${p.delayReasonDone }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
						<smart:gridRow>
							<smart:title title="试用期信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="考核结果" infovalue="${p.probationStatus.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="开始日期" infovalue="${p.probationStartDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="结束日期" infovalue="${p.probationEndDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="试用时长(月)" infovalue="${p.probationDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<c:if test="${p.probationStatus.code==1}">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="入职转正日期" infovalue="${p.formalDate}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="入职转正批准文号" infovalue="${p.formalNumber}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="试用期满考核结论" infovalue="${p.checkResult.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
						<c:if test="${p.probationStatus.code==2}">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="延期时长(月)" infovalue="${p.delayDate}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="延期理由" infovalue="${p.delayReason}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
						<c:if test="${p.probationStatus.code==3||p.probationStatus.code==4}">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="取消原因" infovalue="${p.cancelReason}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="1">
									<smart:fileUpload accept="file" isView="true" buttonName="取消公务员录用的请示" dataName="cancelLetterPath" auto="false" name="cancelLetter" size="20000" data="${p.cancelLetterPath}"/>
								</smart:gridColumn>
								<smart:gridColumn colPart="1" colOffset="1">
									<smart:fileUpload accept="file" isView="true" buttonName="取消录用的书面申请" dataName="cancelWrittenPath" auto="false" name="cancelWritten" size="20000" data="${p.cancelWrittenPath}"/>
								</smart:gridColumn>
								<smart:gridColumn colPart="1" colOffset="1">
									<smart:fileUpload accept="file" isView="true" buttonName="取消录用审批备案表" dataName="cancelRecordsPath" auto="false" name="cancelRecords" size="20000" data="${p.cancelRecordsPath}"/>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
						<c:if test="${isFlow}">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="审批意见:" name="opinion" id="opinion" placeholder="审批意见"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
					</smart:form>
				</smart:gridRow>
				<smart:gridRow>
					<smart:line color="blue" />
					<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
						<smart:buttonGroup container="true">
							<c:if test="${isFlow}">
								<c:if test="${p.probationStatus.code==1||p.probationStatus.code==2}">
									<smart:button method="pass" size="sm" title="审批通过"
										theme="normal">
										<smart:icon icon="check">&nbsp;审批通过</smart:icon>
									</smart:button>
									<smart:button method="noPass" size="sm" title="审批不通过"
										theme="danger">
										<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
									</smart:button>
								</c:if>
								<c:if test="${p.probationStatus.code==3||p.probationStatus.code==4}">
									<smart:button method="pass" size="sm" title="确认"
										theme="normal">
										<smart:icon icon="check">&nbsp;确认</smart:icon>
									</smart:button>
								</c:if>
							</c:if>
							<smart:button theme="warm" size="sm" method="back" title="返回">
								<smart:icon icon="reply">&nbsp;返回</smart:icon>
							</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="element,upload">
		<smart:fileUploadUtils/>
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/probation/operationFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			noPass : function() {
				$("#result").val("0");//审批不通过
				if(!$("#opinion").val()){
					$("#opinion").val("不同意");
				}
				smart.confirm({
					title:'确认审批不通过',
					message:'确认审批不通过吗？',
					url:'ofcflow/probation/operationFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>