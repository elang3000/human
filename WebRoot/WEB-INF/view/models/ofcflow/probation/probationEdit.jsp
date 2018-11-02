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
				<smart:form id="editForm">
					<smart:gridRow>
						<smart:textInput name="id" value="${p.id}" type="hidden"></smart:textInput>
						<smart:textInput name="result" id="result" type="hidden"></smart:textInput>
						<smart:textInput name="isSubmit" id="isSubmit" type="hidden"></smart:textInput>
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
								<smart:infoShowerLabel infoname="开始日期" infovalue="${p.probationStartDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:numberInput isNotNull="true" verify="required" name="probationDate" display="block" min="0" value="${p.probationDate}" labelName="试用时长(月)"></smart:numberInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<script type="text/javascript">
									function statusCallBack(){
										layui.use('form',function(){
											var $ = layui.jquery;
											if(${p.probationStatus!=null}){
												$('#probationStatus').next().find('.layui-anim').children('dd[lay-value="${p.probationStatus.id}"]').click();//触发layui select 属性改变事件
											}
										});
									}
									function probationStatusChange(data){
										var qualifiedDiv = data.othis.closest("div.layui-row").nextAll("div.layui-row").slice(0,1);//合格显示的div
										var delayDiv = data.othis.closest("div.layui-row").nextAll("div.layui-row").slice(1,2);//延长试用期显示的div
										var UNQualifiedDiv = data.othis.closest("div.layui-row").nextAll("div.layui-row").slice(2);//不合格和取消显示的div
										if(data.elem[data.elem.selectedIndex].text=="考核合格"){//隐藏延长试用期和取消录用的div，并去掉必填验证，显示合格div，并加上必填验证
											delayDiv.hide();
											delayDiv.find("[lay-verify]").attr("lay-verify","");
											UNQualifiedDiv.hide();
											UNQualifiedDiv.find("[lay-verify]").attr("lay-verify","");
											qualifiedDiv.show();
											qualifiedDiv.find("[lay-verify]").attr("lay-verify","required");
										}else if(data.elem[data.elem.selectedIndex].text=="延长试用期"){
											qualifiedDiv.hide();
											qualifiedDiv.find("[lay-verify]").attr("lay-verify","");
											UNQualifiedDiv.hide();
											UNQualifiedDiv.find("[lay-verify]").attr("lay-verify","");
											delayDiv.show();
											delayDiv.find("[lay-verify]").attr("lay-verify","required");
										}else{
											qualifiedDiv.hide();
											qualifiedDiv.find("[lay-verify]").attr("lay-verify","");
											delayDiv.hide();
											delayDiv.find("[lay-verify]").attr("lay-verify","");
											UNQualifiedDiv.show();
											UNQualifiedDiv.find("[lay-verify]").attr("lay-verify","required");
										}
									}
								</script>
								<smart:singleSelect id="probationStatus" isNotNull="true" callBack="statusCallBack" verify="required" isAddDefaltOption="true" display="block" labelName="考核结果:" initSelectedKey="${p.probationStatus.id}" name="probationStatus.id" url="dictquery/sub/code/PROBATION_SERVANT_STATUS" changeCallBack="probationStatusChange"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:date isNotNull="true" display="block" verify="required" labelName="入职转正日期:" value="${p.formalDate}" name="formalDate" id="formalDate" placeholder="入职转正日期"></smart:date>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="入职转正批准文号:" isNotNull="true" verify="required" value="${p.formalNumber}" name="formalNumber"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect id="checkResult" isNotNull="true" verify="required" display="block" labelName="试用期满考核结论:" initSelectedKey="${p.checkResult.id}" name="checkResult.id" url="dictquery/sub/code/DM018"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:numberInput isNotNull="true" verify="required" display="block" name="delayDate" min="0" value="${p.delayDate}" labelName="延期时长(月)"></smart:numberInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="延期理由:" isNotNull="true" verify="required" value="${p.delayReason}" name="delayReason"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea isNotNull="true" display="block" labelName="取消原因:" verify="required" name="cancelReason">${p.cancelReason}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="1">
								<smart:fileUpload accept="file" buttonName="取消公务员录用的请示" dataName="cancelLetterPath" auto="false" name="cancelLetter" size="20000" data="${p.cancelLetterPath}"/>
							</smart:gridColumn>
							<smart:gridColumn colPart="1" colOffset="1">
								<smart:fileUpload accept="file"  buttonName="取消录用的书面申请" dataName="cancelWrittenPath" auto="false" name="cancelWritten" size="20000" data="${p.cancelWrittenPath}"/>
							</smart:gridColumn>
							<smart:gridColumn colPart="1" colOffset="1">
								<smart:fileUpload accept="file" buttonName="取消录用审批备案表" dataName="cancelRecordsPath" auto="false" name="cancelRecords" size="20000" data="${p.cancelRecordsPath}"/>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<smart:button id="submit" other="lay-submit" size="sm" title="提交"
									theme="normal">
									<smart:icon icon="check">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button id="save" other="lay-submit" size="sm" title="暂存"
									theme="default">
									<smart:icon icon="plus">&nbsp;暂存</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="back" title="返回">
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
		<smart:utils/>
		<smart:buttonScriptAction>
			back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
		<smart:dateRender id="formalDate" />
		<smart:fileUploadUtils/>
		form.on('submit(save)', function(data) {
			smart.confirm({
				title:'保存试用期信息',
				message:'确认提交保存吗？',
				url:'ofcflow/probation/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		form.on('submit(submit)', function(data) {
			$("#result").val("1");
			$("#isSubmit").val("1");
			smart.confirm({
				title:'提交试用期信息',
				message:'确认提交审批吗？',
				url:'ofcflow/probation/operationFlow',
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