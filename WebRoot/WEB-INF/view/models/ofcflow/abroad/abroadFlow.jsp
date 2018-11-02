<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="因公出国政审"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:grid>
							<smart:gridRow>
							<smart:title title="因公出国计划" style="margin-top: 5px;" color="blue" />
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="出国境团组名称" infovalue="${abroadYearPlan.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="出访国家" infovalue="${abroadYearPlan.country}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="停留天数" infovalue="${abroadYearPlan.day}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							
							<smart:gridRow>
									<smart:title title="公务员基本信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="身份证号" infovalue="${servant.cardNo}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="性别" infovalue="${servant.sex.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="出生日期" infovalue="${servant.birthDate}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="民族" infovalue="${servant.nation.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="政治面貌" infovalue="${servant.politics.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="出生地" infovalue="${servant.birthPlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="籍贯" infovalue="${servant.nativePlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="人员类别" infovalue="${servant.personType.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="健康状况" infovalue="${servant.health.name}"></smart:infoShowerLabel>
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
									<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="1">
										<smart:fileUpload accept="file" isView="true" buttonName="附件" dataName="abroadFtp" auto="false" name="filePathName" size="20000" multiple="true" number="7" data="${abroadPlan.abroadFtp}"/>
									</smart:gridColumn>
								</smart:gridRow>
						</smart:grid>
					</smart:gridColumn>
				</smart:gridRow>
				
				<smart:form id="editForm">
				<smart:fromTokenTag/>
				
					<smart:gridRow>
						<smart:textInput type="hidden" name="id"  value="${abroadPlan.id}" ></smart:textInput>
						<smart:textInput type="hidden" id="result" name="result" ></smart:textInput>
						<c:if test="${abroadPlan.status<=7}">
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="审批意见：" name="opinion" placeholder="审批意见"></smart:textInput>
						</smart:gridColumn>
						</c:if>
					</smart:gridRow>
						
					<smart:gridRow>
					<smart:line color="blue" />
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
						   <c:if test="${abroadPlan.status<=7}">
									<smart:button method="pass" size="sm" title="审批通过"
										theme="normal">
										<smart:icon icon="check">&nbsp;审批通过</smart:icon>
									</smart:button>
									<smart:button method="noPass" size="sm" title="审批不通过"
										theme="danger">
										<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
									</smart:button>
								</c:if>
								<c:if test="${abroadPlan.status==8}">
									<smart:button method="pass" size="sm" title="确认"
										theme="normal">
										<smart:icon icon="check">&nbsp;确认</smart:icon>
									</smart:button>
							</c:if>
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
	<smart:scriptHead models="table,form,layer,element,upload">
		<smart:utils/>
		<smart:fileUploadUtils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/abroad/operationPlan',
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
				smart.confirm({
					title:'确认审批不通过',
					message:'确认审批不通过吗？',
					url:'ofcflow/abroad/operationPlan',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		
	</smart:scriptHead>
</smart:body>
</html>