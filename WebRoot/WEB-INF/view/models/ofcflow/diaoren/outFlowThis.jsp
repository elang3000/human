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
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员调任管理" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<smart:textInput type="hidden" name="result" id="result"></smart:textInput>
					<smart:gridRow>
						<smart:title title="公务员基本信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="单位部门" infovalue="${s.departName}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
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
									<img alt="照片" src="static/image/20170705135600.jpg">
								</smart:gridColumn>
							</smart:gridRow>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:title title="调出信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="拟任职务" infovalue="${d.postName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="拟任职务属性" infovalue="${d.attribute.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="调任后人员类别" infovalue="${d.personType.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="调往单位名称" infovalue="${d.gotoUnitName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="调出本单位类别" infovalue="${d.category.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="调出本单位日期" infovalue="${d.outDate}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="提出调动类型" infovalue="${d.proposeType.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="8">
							<smart:infoShowerLabel infoname="调动原因" infovalue="${d.reasonType.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="职级名称" infovalue="${d.jobLevelName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="8">
							<smart:infoShowerLabel infoname="调出备注" infovalue="${d.remark}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="1">
							<smart:fileUpload accept="file" isView="true" buttonName="附件" dataName="filePath" auto="false" name="filePathName" size="20000" multiple="true" number="7" data="${d.filePath}"/>
						</smart:gridColumn>
					</smart:gridRow>
					<c:if test="${isFlow}">
						<c:if test="${d.status<6 }">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="审批意见:" name="opinion" id="opinion" placeholder="审批意见"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
					</c:if>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<c:if test="${isFlow}">
									<c:if test="${d.status<6 }">
										<smart:button method="pass" size="sm" title="审批通过"
												theme="normal">
											<smart:icon icon="check">&nbsp;审批通过</smart:icon>
										</smart:button>
										<smart:button method="noPass" size="sm" title="审批不通过"
											theme="danger">
											<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
										</smart:button>
									</c:if>
									<c:if test="${d.status==6 }">
										<smart:button method="pass" size="sm" title="确认"
												theme="normal">
											<smart:icon icon="check">&nbsp;确认</smart:icon>
										</smart:button>
									</c:if>
								</c:if>
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
		<smart:fileUploadUtils/>
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/diaorenOut/operationFlow',
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
					url:'ofcflow/diaorenOut/operationFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>