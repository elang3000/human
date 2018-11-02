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
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="隶属单位名称" infovalue="${orgInfo.unitSubordinateName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="隶属单位代码" infovalue="${orgInfo.unitSubordinateCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="隶属单位代码" infovalue="${orgInfo.unitLeaderDirectorName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="领导班子主管单位代码" infovalue="${orgInfo.unitLeaderDirectorCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="领导班子协管单位名称" infovalue="${orgInfo.unitLeaderAssistName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="领导班子协管单位代码" infovalue="${orgInfo.unitLeaderAssistCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="归口管理单位名称" infovalue="${orgInfo.unitReturnManagementName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="归口管理单位代码" infovalue="${orgInfo.unitReturnManagementCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位负责人" infovalue="${orgInfo.corporation}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准成立日期" infovalue="${orgInfo.approveDate}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准成立文号" infovalue="${orgInfo.approveNo}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="成立日期" infovalue="${orgInfo.buildDate}"></smart:infoShowerLabel>
						</smart:gridColumn>
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
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="机构类别" infovalue="${orgInfo.orgCategory.name}"></smart:infoShowerLabel>
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
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="编制批准单位名称" infovalue="${orgFormation.approveUnitName}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>	
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="编制批准单位代码" infovalue="${orgFormation.approveUnitCode}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="批准编制总数 " infovalue="${orgFormation.unitPlanningTotal}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="行政编制数" infovalue="${orgFormation.adminWeaveNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>		
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="事业编制数" infovalue="${orgFormation.institutionWeaveNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="参照公务员法管理事业编制数" infovalue="${orgFormation.causeWeaveNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="实有人数" infovalue="${orgFormation.actualNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>		
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="空编人数" infovalue="${orgFormation.vacancyExcessNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>		
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（定编）处级职数" infovalue="${orgFormation.approveDivisionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（实有）处级职数" infovalue="${orgFormation.divisionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（空缺）处级职数" infovalue="${orgFormation.vacancyDivisionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>		
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（定编）科级（领导）职数" infovalue="${orgFormation.approveSectionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（实有）科级（领导）职数" infovalue="${orgFormation.sectionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（空缺）科级（领导）职数" infovalue="${orgFormation.vacancySectionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>		
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（定编）科级（非领导）职数" infovalue="${orgFormation.approveNonLeaderSectionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（实有）科级（非领导）职数" infovalue="${orgFormation.nonLeaderSectionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="（空缺）科级（非领导）职数" infovalue="${orgFormation.vacancyNonLeaderSectionChiefLevelNumber}"></smart:infoShowerLabel>
							</smart:gridColumn>
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