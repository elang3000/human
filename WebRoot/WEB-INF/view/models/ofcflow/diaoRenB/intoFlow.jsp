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
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<c:if test="${areaType eq '1' }">
						<smart:textInput type="hidden" name="servant.id" value="${s.id}"></smart:textInput>
					</c:if>
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
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="拟任职务" infovalue="${d.postName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="拟任职务属性" infovalue="${d.attribute.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<c:if test="${areaType eq '1' }">
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="原职级名称" infovalue="${d.sourceRealJobLevelName}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</c:if>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="职级名称" infovalue="${d.jobLevelName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="职级属性" infovalue="${d.isLeaderView}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="原工作单位" infovalue="${d.formerUnitName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="进入本单位变动类别" shortName="变动类别" infovalue="${d.enterTheUnitChangeType.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<c:if test="${areaType eq '1' }">
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="调任后人员类别" infovalue="${d.personType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</c:if>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:infoShowerLabel infoname="进入本单位原因" infovalue="${d.enterReason.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<c:if test="${status==6 &&isFlow}">
						<smart:gridRow>
							<smart:title title="调出信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput type="hidden" name="diaoRenOut.id" value="${o.id}"></smart:textInput>
								<smart:infoShowerLabel infoname="调往单位名称" infovalue="${o.gotoUnitName}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect initSelectedKey="${o.category.id}" name="diaoRenOut.category.id"
								labelName="调出本单位类别：" isNotNull="true" verify="required" url="dictquery/sub/code/GBT_12405_2008/70"
								display="block" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:date labelName="调出本单位日期：" display="block" name="diaoRenOut.outDate" id="outDate" value="${o.outDate}"></smart:date>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="提出调动类型：" name="diaoRenOut.proposeType.id" display="block" url="dictquery/sub/code/DM039" isAddDefaltOption="true" initSelectedKey="${o.proposeType.id }"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="8">
								<smart:continuousSelect labelName="调动原因：" inputName="diaoRenOut.reasonType.id" codeTypeCode="DM015" inputVal="${o.reasonType.id}" valType="ID"/>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textarea labelName="调出备注：" name="diaoRenOut.remark"  display="block">${o.remark}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
					</c:if>
					<c:if test="${status>6 && areaType eq '1'}">
						<smart:gridRow>
							<smart:title title="调出信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput type="hidden" name="diaoRenOut.id" value="${o.id}"></smart:textInput>
								<smart:infoShowerLabel infoname="调往单位名称" infovalue="${o.gotoUnitName}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="调出本单位类别" infovalue="${o.category.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="调出本单位日期" infovalue="${o.outDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="提出调动类型" infovalue="${o.proposeType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="8">
								<smart:infoShowerLabel infoname="调动原因" infovalue="${o.reasonType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:infoShowerLabel infoname="调出备注" infovalue="${o.remark}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
					</c:if>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<c:if test="${isFlow}">
									<c:if test="${status==6 }">
										<smart:button id="save" other="lay-submit" size="sm" title="保存"
												theme="normal">
											<smart:icon icon="check">&nbsp;保存</smart:icon>
										</smart:button>
									</c:if>
								</c:if>
								<smart:button theme="primary" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:dateRender id="outDate"/>
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
			$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/diaorenB/saveThis',
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