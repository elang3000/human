<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="机构信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:gridRow>
						<smart:title title="机构信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位名称" infovalue="${orgInfo.unitBasicName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位简称" infovalue="${orgInfo.unitBasicSimpleName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位缩写" infovalue="${orgInfo.unitBasicShortName}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="社会信用代码" infovalue="${orgInfo.xydm}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="行政区划" infovalue="${orgInfo.unitDistrict.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位级别" infovalue="${orgInfo.unitLevel.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="隶属关系层次" infovalue="${orgInfo.unitSubjectionLevel.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
									
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="附属关系类型" infovalue="${orgInfo.unitSubsidiary.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
							
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="性质级别" infovalue="${orgInfo.unitPropertyLevel.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="成立日期" infovalue="${orgInfo.buildDate}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位负责人" infovalue="${orgInfo.corporation}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="隶属单位代码" infovalue="${orgInfo.unitSubordinateCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="隶属单位名称" infovalue="${orgInfo.unitSubordinateName}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="主管单位代码" infovalue="${orgInfo.unitLeaderDirectorName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="主管单位代码" infovalue="${orgInfo.unitLeaderDirectorCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="协管单位代码" infovalue="${orgInfo.unitLeaderAssistCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="协管单位名称" infovalue="${orgInfo.unitLeaderAssistName}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="归管单位代码" infovalue="${orgInfo.unitReturnManagementCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="归管单位名称" infovalue="${orgInfo.unitReturnManagementName}"></smart:infoShowerLabel>
						</smart:gridColumn>						
					</smart:gridRow>
					<smart:gridRow>					
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准成立日期" infovalue="${orgInfo.approveDate}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准成立文号" infovalue="${orgInfo.approveNo}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准单位名称" infovalue="${orgInfo.approveUnitName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准单位代码" infovalue="${orgInfo.approveUnitCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位说明 " infovalue="${orgInfo.unitInstruction}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="拨款形式 " infovalue="${orgInfo.appropriation.name}"></smart:infoShowerLabel>
						</smart:gridColumn>

					</smart:gridRow>
					<c:if test="${orgFormation != null}">
						<smart:gridRow>
							<smart:title title="编制信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="批准编制日期" infovalue="${orgFormation.approveDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="批准编制文号 " infovalue="${orgFormation.approveNo}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>	
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="批准单位名称" infovalue="${orgFormation.approveUnitName}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="批准单位代码" infovalue="${orgFormation.approveUnitCode}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>		
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="批准编制总数 " infovalue="${orgFormation.unitPlanningTotal}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="实有人数" infovalue="${orgFormation.actualNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="空编人数" infovalue="${orgFormation.vacancyExcessNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>		
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="尚未调入人数 " infovalue="${orgFormation.notIntoNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="尚未调出人数" infovalue="${orgFormation.notOutNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						
						<smart:gridRow>
							<smart:title title="职数信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						
						<smart:gridRow>
							<table class="layui-table" lay-size="sm">
								<thead>
									<tr>
										<td style="width:20%"><b>名称</b></td>
										<td style="width:20%"><b>定编数</b></td>
										<td style="width:20%"><b>实有数</b></td>
										<td style="width:20%"><b>尚未调入数</b></td>
										<td style="width:20%"><b>尚未调出数</b></td>
								    </tr>
							    </thead>
							    <tbody>
							    	<tr>
										<td style="width:20%">处级</td>
										<td style="width:20%">${orgFormation.approveChuNum}</td>
										<td style="width:20%">${orgFormation.realChuNum}</td>
										<td style="width:20%"></td>
										<td style="width:20%"></td>
								    </tr>
								    <tr>
										<td style="width:20%">乡科级正职（领导）</td>
										<td style="width:20%">${orgFormation.approvePlusKeLeaderNum}</td>
										<td style="width:20%">${orgFormation.realPlusKeLeaderNum}</td>
										<td style="width:20%">${orgFormation.notIntoPlusKeLeaderNum}</td>
										<td style="width:20%">${orgFormation.notOutPlusKeLeaderNum}</td>
								    </tr>
								    <tr>
										<td style="width:20%">乡科级正职（非领导）</td>
										<td style="width:20%">${orgFormation.approvePlusKeNoLeaderNum}</td>
										<td style="width:20%">${orgFormation.realPlusKeNoLeaderNum}</td>
										<td style="width:20%">${orgFormation.notIntoPlusKeNoLeaderNum}</td>
										<td style="width:20%">${orgFormation.notOutPlusKeNoLeaderNum}</td>
								    </tr>
								    <tr>
										<td style="width:20%">乡科级副职（领导）</td>
										<td style="width:20%">${orgFormation.approveMinusKeLeaderNum}</td>
										<td style="width:20%">${orgFormation.realMinusKeLeaderNum}</td>
										<td style="width:20%">${orgFormation.notIntoMinusKeLeaderNum}</td>
										<td style="width:20%">${orgFormation.notOutMinusKeLeaderNum}</td>
								    </tr>
								    <tr>
										<td style="width:20%">乡科级副职（非领导）</td>
										<td style="width:20%">${orgFormation.approveMinusKeNoLeaderNum}</td>
										<td style="width:20%">${orgFormation.realMinusKeNoLeaderNum}</td>
										<td style="width:20%">${orgFormation.notIntoMinusKeNoLeaderNum}</td>
										<td style="width:20%">${orgFormation.notOutMinusKeNoLeaderNum}</td>
								    </tr>
								 </tbody>  
							</table>
						</smart:gridRow>
						
						<smart:gridRow>
							<smart:title title="三定方案附件" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="1">
								<smart:fileUpload accept="file" isView="true" buttonName="三定方案" dataName="planPath" auto="false" name="plan" size="20000" data="${orgFormation.planPath}"/>
							</smart:gridColumn>
						</smart:gridRow>
					</c:if>
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
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
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils/>
		<smart:fileUploadUtils/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>