<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="申请新增机构信息查看"/>
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
						<smart:infoShowerLabel infoname="上级组织架构" infovalue="${orgInfoMgrFlow.parentOrgan.name}"></smart:infoShowerLabel>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位名称" infovalue="${orgInfoMgrFlow.unitBasicName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位简称" infovalue="${orgInfoMgrFlow.unitBasicSimpleName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位缩写" infovalue="${orgInfoMgrFlow.unitBasicShortName}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="社会信用代码" infovalue="${orgInfoMgrFlow.xydm}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="行政区划" infovalue="${orgInfoMgrFlow.unitDistrict.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位级别" infovalue="${orgInfoMgrFlow.unitLevel.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="隶属关系层次" infovalue="${orgInfoMgrFlow.unitSubjectionLevel.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
									
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="附属关系类型" infovalue="${orgInfoMgrFlow.unitSubsidiary.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
							
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="性质级别" infovalue="${orgInfoMgrFlow.unitPropertyLevel.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="隶属单位名称" infovalue="${orgInfoMgrFlow.unitSubordinateName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="隶属单位代码" infovalue="${orgInfoMgrFlow.unitSubordinateCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="隶属单位代码" infovalue="${orgInfoMgrFlow.unitLeaderDirectorName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="领导班子主管单位代码" infovalue="${orgInfoMgrFlow.unitLeaderDirectorCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="领导班子协管单位名称" infovalue="${orgInfoMgrFlow.unitLeaderAssistName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="领导班子协管单位代码" infovalue="${orgInfoMgrFlow.unitLeaderAssistCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="归口管理单位名称" infovalue="${orgInfoMgrFlow.unitReturnManagementName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="归口管理单位代码" infovalue="${orgInfoMgrFlow.unitReturnManagementCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位负责人" infovalue="${orgInfoMgrFlow.corporation}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准成立日期" infovalue="${orgInfoMgrFlow.approveDate}"></smart:infoShowerLabel>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准成立文号" infovalue="${orgInfoMgrFlow.approveNo}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="成立日期" infovalue="${orgInfoMgrFlow.buildDate}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准单位名称" infovalue="${orgInfoMgrFlow.approveUnitName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批准单位代码" infovalue="${orgInfoMgrFlow.approveUnitCode}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="单位说明 " infovalue="${orgInfoMgrFlow.unitInstruction}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="拨款形式 " infovalue="${orgInfoMgrFlow.appropriation.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="机构类别" infovalue="${orgInfoMgrFlow.orgCategory.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
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
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>