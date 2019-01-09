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
							<smart:title title="职数信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						
						<smart:gridRow>
							<table class="layui-table" lay-size="sm">
								<thead>
									<tr>
										<td colspan="5" style="text-align:center"><b>管理人员</b></td>
										<td colspan="5" style="text-align:center"><b>专业技术人员</b></td>
										<td colspan="5" style="text-align:center"><b>工勤人员</b></td>
								    </tr>
								    <tr>
										<td style="width:7%"><b>名称</b></td>
										<td style="width:6.5%"><b>定编</b></td>
										<td style="width:6.5%"><b>实有</b></td>
										<td style="width:6.5%"><b>未调入</b></td>
										<td style="width:6.5%"><b>未调出</b></td>
										<td style="width:7%"><b>名称</b></td>
										<td style="width:6.5%"><b>定编</b></td>
										<td style="width:6.5%"><b>实有</b></td>
										<td style="width:6.5%"><b>未调入</b></td>
										<td style="width:6.5%"><b>未调出</b></td>
										<td style="width:7%"><b>名称</b></td>
										<td style="width:6.5%"><b>定编</b></td>
										<td style="width:6.5%"><b>实有</b></td>
										<td style="width:6.5%"><b>未调入</b></td>
										<td style="width:6.5%"><b>未调出</b></td>
								    </tr>
								    <tbody>
								    	<tr>
									    	<td>一级：</td>
									    	<td>${orgFormation.approveMgrLevelINum}</td>
									    	<td>${orgFormation.realMgrLevelINum}</td>
									    	<td>${orgFormation.notIntoMgrLevelINum}</td>
									    	<td>${orgFormation.notOutMgrLevelINum}</td>
									    	<td>一级：</td>
									    	<td>${orgFormation.approveTechLevelINum}</td>
									    	<td>${orgFormation.realTechLevelINum}</td>
									    	<td>${orgFormation.notIntoTechLevelINum}</td>
									    	<td>${orgFormation.notOutTechLevelINum}</td>
									    	<td>一级：</td>
									    	<td>${orgFormation.approveWorkLevelINum}</td>
									    	<td>${orgFormation.realWorkLevelINum}</td>
									    	<td>${orgFormation.notIntoWorkLevelINum}</td>
									    	<td>${orgFormation.notOutWorkLevelINum}</td>
								    	</tr>
								    	<tr>
									    	<td>二级：</td>
									    	<td>${orgFormation.approveMgrLevelIINum}</td>
									    	<td>${orgFormation.realMgrLevelIINum}</td>
									    	<td>${orgFormation.notIntoMgrLevelIINum}</td>
									    	<td>${orgFormation.notOutMgrLevelIINum}</td>
									    	<td>二级：</td>
									    	<td>${orgFormation.approveTechLevelIINum}</td>
									    	<td>${orgFormation.realTechLevelIINum}</td>
									    	<td>${orgFormation.notIntoTechLevelIINum}</td>
									    	<td>${orgFormation.notOutTechLevelIINum}</td>
									    	<td>二级：</td>
									    	<td>${orgFormation.approveWorkLevelIINum}</td>
									    	<td>${orgFormation.realWorkLevelIINum}</td>
									    	<td>${orgFormation.notIntoWorkLevelIINum}</td>
									    	<td>${orgFormation.notOutWorkLevelIINum}</td>
								    	</tr>
								    	<tr>
									    	<td>三级：</td>
									    	<td>${orgFormation.approveMgrLevelIIINum}</td>
									    	<td>${orgFormation.realMgrLevelIIINum}</td>
									    	<td>${orgFormation.notIntoMgrLevelIIINum}</td>
									    	<td>${orgFormation.notOutMgrLevelIIINum}</td>
									    	<td>三级：</td>
									    	<td>${orgFormation.approveTechLevelIIINum}</td>
									    	<td>${orgFormation.realTechLevelIIINum}</td>
									    	<td>${orgFormation.notIntoTechLevelIIINum}</td>
									    	<td>${orgFormation.notOutTechLevelIIINum}</td>
									    	<td>三级：</td>
									    	<td>${orgFormation.approveWorkLevelIIINum}</td>
									    	<td>${orgFormation.realWorkLevelIIINum}</td>
									    	<td>${orgFormation.notIntoWorkLevelIIINum}</td>
									    	<td>${orgFormation.notOutWorkLevelIIINum}</td>
								    	</tr>
								    	<tr>
									    	<td>四级：</td>
									    	<td>${orgFormation.approveMgrLevelIVNum}</td>
									    	<td>${orgFormation.realMgrLevelIVNum}</td>
									    	<td>${orgFormation.notIntoMgrLevelIVNum}</td>
									    	<td>${orgFormation.notOutMgrLevelIVNum}</td>
									    	<td>四级：</td>
									    	<td>${orgFormation.approveTechLevelIVNum}</td>
									    	<td>${orgFormation.realTechLevelIVNum}</td>
									    	<td>${orgFormation.notIntoTechLevelIVNum}</td>
									    	<td>${orgFormation.notOutTechLevelIVNum}</td>
									    	<td>四级：</td>
									    	<td>${orgFormation.approveWorkLevelIVNum}</td>
									    	<td>${orgFormation.realWorkLevelIVNum}</td>
									    	<td>${orgFormation.notIntoWorkLevelIVNum}</td>
									    	<td>${orgFormation.notOutWorkLevelIVNum}</td>
								    	</tr>
								    	<tr>
									    	<td>五级：</td>
									    	<td>${orgFormation.approveMgrLevelVNum}</td>
									    	<td>${orgFormation.realMgrLevelVNum}</td>
									    	<td>${orgFormation.notIntoMgrLevelVNum}</td>
									    	<td>${orgFormation.notOutMgrLevelVNum}</td>
									    	<td>五级：</td>
									    	<td>${orgFormation.approveTechLevelVNum}</td>
									    	<td>${orgFormation.realTechLevelVNum}</td>
									    	<td>${orgFormation.notIntoTechLevelVNum}</td>
									    	<td>${orgFormation.notOutTechLevelVNum}</td>
									    	<td>五级：</td>
									    	<td>${orgFormation.approveWorkLevelVNum}</td>
									    	<td>${orgFormation.realWorkLevelVNum}</td>
									    	<td>${orgFormation.notIntoWorkLevelVNum}</td>
									    	<td>${orgFormation.notOutWorkLevelVNum}</td>
								    	</tr>
								    	<tr>
									    	<td>六级：</td>
									    	<td>${orgFormation.approveMgrLevelVINum}</td>
									    	<td>${orgFormation.realMgrLevelVINum}</td>
									    	<td>${orgFormation.notIntoMgrLevelVINum}</td>
									    	<td>${orgFormation.notOutMgrLevelVINum}</td>
									    	<td>六级：</td>
									    	<td>${orgFormation.approveTechLevelVINum}</td>
									    	<td>${orgFormation.realTechLevelVINum}</td>
									    	<td>${orgFormation.notIntoTechLevelVINum}</td>
									    	<td>${orgFormation.notOutTechLevelVINum}</td>
									    	<td colspan=5 rowspan=8></td>
								    	</tr>
								    	<tr>
									    	<td>七级：</td>
									    	<td>${orgFormation.approveMgrLevelVIINum}</td>
									    	<td>${orgFormation.realMgrLevelVIINum}</td>
									    	<td>${orgFormation.notIntoMgrLevelVIINum}</td>
									    	<td>${orgFormation.notOutMgrLevelVIINum}</td>
									    	<td>七级：</td>
									    	<td>${orgFormation.approveTechLevelVIINum}</td>
									    	<td>${orgFormation.realTechLevelVIINum}</td>
									    	<td>${orgFormation.notIntoTechLevelVIINum}</td>
									    	<td>${orgFormation.notOutTechLevelVIINum}</td>
								    	</tr>
								    	<tr>
									    	<td>八级：</td>
									    	<td>${orgFormation.approveMgrLevelVIIINum}</td>
									    	<td>${orgFormation.realMgrLevelVIIINum}</td>
									    	<td>${orgFormation.notIntoMgrLevelVIIINum}</td>
									    	<td>${orgFormation.notOutMgrLevelVIIINum}</td>
									    	<td>八级：</td>
									    	<td>${orgFormation.approveTechLevelVIIINum}</td>
									    	<td>${orgFormation.realTechLevelVIIINum}</td>
									    	<td>${orgFormation.notIntoTechLevelVIIINum}</td>
									    	<td>${orgFormation.notOutTechLevelVIIINum}</td>
								    	</tr>
								    	<tr>
									    	<td>九级：</td>
									    	<td>${orgFormation.approveMgrLevelIXNum}</td>
									    	<td>${orgFormation.realMgrLevelIXNum}</td>
									    	<td>${orgFormation.notIntoMgrLevelIXNum}</td>
									    	<td>${orgFormation.notOutMgrLevelIXNum}</td>
									    	<td>九级：</td>
									    	<td>${orgFormation.approveTechLevelIXNum}</td>
									    	<td>${orgFormation.realTechLevelIXNum}</td>
									    	<td>${orgFormation.notIntoTechLevelIXNum}</td>
									    	<td>${orgFormation.notOutTechLevelIXNum}</td>
								    	</tr>
								    	<tr>
									    	<td>十级：</td>
									    	<td>${orgFormation.approveMgrLevelXNum}</td>
									    	<td>${orgFormation.realMgrLevelXNum}</td>
									    	<td>${orgFormation.notIntoMgrLevelXNum}</td>
									    	<td>${orgFormation.notOutMgrLevelXNum}</td>
									    	<td>十级：</td>
									    	<td>${orgFormation.approveTechLevelXNum}</td>
									    	<td>${orgFormation.realTechLevelXNum}</td>
									    	<td>${orgFormation.notIntoTechLevelXNum}</td>
									    	<td>${orgFormation.notOutTechLevelXNum}</td>
								    	</tr>
								    	<tr>
									    	<td colspan=5 rowspan=3></td>
									    	<td>十一级：</td>
									    	<td>${orgFormation.approveTechLevelXINum}</td>
									    	<td>${orgFormation.realTechLevelXINum}</td>
									    	<td>${orgFormation.notIntoTechLevelXINum}</td>
									    	<td>${orgFormation.notOutTechLevelXINum}</td>
								    	</tr>
								    	<tr>
									    	<td>十二级：</td>
									    	<td>${orgFormation.approveTechLevelXIINum}</td>
									    	<td>${orgFormation.realTechLevelXIINum}</td>
									    	<td>${orgFormation.notIntoTechLevelXIINum}</td>
									    	<td>${orgFormation.notOutTechLevelXIINum}</td>
								    	</tr>
								    	<tr>
									    	<td>十三级：</td>
									    	<td>${orgFormation.approveTechLevelXIIINum}</td>
									    	<td>${orgFormation.realTechLevelXIIINum}</td>
									    	<td>${orgFormation.notIntoTechLevelXIIINum}</td>
									    	<td>${orgFormation.notOutTechLevelXIIINum}</td>
								    	</tr>
								    </tbody>
								</thead>
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