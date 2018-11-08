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
					<smart:form>
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
								<smart:infoShowerLabel infoname="出生地" infovalue="${d.birthPlace.name }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="籍贯" infovalue="${d.nativePlace.name }"></smart:infoShowerLabel>
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
								<%-- <smart:infoShowerLabel infoname="来源" infovalue="${d.source.name }"></smart:infoShowerLabel> --%>
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
						<c:if test="${isReport!=null}">
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
<%-- 								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="录用标识" infovalue="${d.employResult.name }"></smart:infoShowerLabel>
								</smart:gridColumn> --%>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="录用情况" infovalue="${d.employSituation.name }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="录用鉴定（评语）" infovalue="${d.employComment }"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="备注" infovalue="${d.remark }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
					</smart:form>
				</smart:gridRow>
				<smart:gridRow>
					<smart:line color="blue" />
					<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
						<smart:buttonGroup container="true">
							<smart:button theme="warm" size="sm" method="back" title="返回">
								<smart:icon icon="reply">&nbsp;返回</smart:icon>
							</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:buttonScriptAction>
			back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>