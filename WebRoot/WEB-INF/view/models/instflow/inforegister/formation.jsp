<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<smart:gridRow>
	<smart:accordionPanel id="panel" isAccordion="true">
		<smart:accordionPanelItem title="编制情况" isShow="false">
			<smart:gridRow>
				<smart:gridColumn colPart="4">
					<smart:infoShowerLabel infoname="批准编制总数 "
						infovalue="${d.unitPlanningTotal}"></smart:infoShowerLabel>
				</smart:gridColumn>
				<smart:gridColumn colPart="4">
					<smart:infoShowerLabel infoname="实有人数"
						infovalue="${d.actualNumber}"></smart:infoShowerLabel>
				</smart:gridColumn>
				<smart:gridColumn colPart="4">
					<smart:infoShowerLabel infoname="空编人数"
						infovalue="${d.vacancyExcessNumber}"></smart:infoShowerLabel>
				</smart:gridColumn>
			</smart:gridRow>
			<smart:gridRow>
				<smart:gridColumn colPart="4">
					<smart:infoShowerLabel infoname="尚未调入人数 "
						infovalue="${d.notIntoNum}"></smart:infoShowerLabel>
				</smart:gridColumn>
				<smart:gridColumn colPart="4">
					<smart:infoShowerLabel infoname="尚未调出人数" infovalue="${d.notOutNum}"></smart:infoShowerLabel>
				</smart:gridColumn>
			</smart:gridRow>

			<smart:gridRow>
				<smart:title title="职数信息" style="margin-top: 5px;" color="blue" />
			</smart:gridRow>

			<smart:gridRow>
				<table class="layui-table" lay-size="sm">
					<thead>
						<tr>
							<td style="width: 20%"><b>名称</b></td>
							<td style="width: 20%"><b>定编数</b></td>
							<td style="width: 20%"><b>实有数</b></td>
							<td style="width: 20%"><b>尚未调入数</b></td>
							<td style="width: 20%"><b>尚未调出数</b></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 20%">管理人员一级</td>
							<td style="width: 20%">${d.approveMgrLevelINum}</td>
							<td style="width: 20%">${d.realMgrLevelINum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelINum}</td>
							<td style="width: 20%">${d.notOutMgrLevelINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">管理人员二级</td>
							<td style="width: 20%">${d.approveMgrLevelIINum}</td>
							<td style="width: 20%">${d.realMgrLevelIINum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelIINum}</td>
							<td style="width: 20%">${d.notOutMgrLevelIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">管理人员三级</td>
							<td style="width: 20%">${d.approveMgrLevelIIINum}</td>
							<td style="width: 20%">${d.realMgrLevelIIINum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelIIINum}</td>
							<td style="width: 20%">${d.notOutMgrLevelIIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">管理人员四级</td>
							<td style="width: 20%">${d.approveMgrLevelIVNum}</td>
							<td style="width: 20%">${d.realMgrLevelIVNum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelIVNum}</td>
							<td style="width: 20%">${d.notOutMgrLevelIVNum}</td>
						</tr>
						<tr>
							<td style="width: 20%">管理人员五级</td>
							<td style="width: 20%">${d.approveMgrLevelVNum}</td>
							<td style="width: 20%">${d.realMgrLevelVNum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelVNum}</td>
							<td style="width: 20%">${d.notOutMgrLevelVNum}</td>
						</tr>
						<tr>
							<td style="width: 20%">管理人员六级</td>
							<td style="width: 20%">${d.approveMgrLevelVINum}</td>
							<td style="width: 20%">${d.realMgrLevelVINum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelVINum}</td>
							<td style="width: 20%">${d.notOutMgrLevelVINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">管理人员七级</td>
							<td style="width: 20%">${d.approveMgrLevelVIINum}</td>
							<td style="width: 20%">${d.realMgrLevelVIINum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelVIINum}</td>
							<td style="width: 20%">${d.notOutMgrLevelVIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">管理人员八级</td>
							<td style="width: 20%">${d.approveMgrLevelVIIINum}</td>
							<td style="width: 20%">${d.realMgrLevelVIIINum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelVIIINum}</td>
							<td style="width: 20%">${d.notOutMgrLevelVIIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">管理人员九级</td>
							<td style="width: 20%">${d.approveMgrLevelXNum}</td>
							<td style="width: 20%">${d.realMgrLevelXNum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelIXNum}</td>
							<td style="width: 20%">${d.notOutMgrLevelIXNum}</td>
						</tr>
	                   <tr>
							<td style="width: 20%">管理人员十级</td>
							<td style="width: 20%">${d.approveMgrLevelIXNum}</td>
							<td style="width: 20%">${d.realMgrLevelIXNum}</td>
							<td style="width: 20%">${d.notIntoMgrLevelXNum}</td>
							<td style="width: 20%">${d.notOutMgrLevelXNum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员一级</td>
							<td style="width: 20%">${d.approveTechLevelINum}</td>
							<td style="width: 20%">${d.realTechLevelINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelINum}</td>
							<td style="width: 20%">${d.notOutTechLevelINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员二级</td>
							<td style="width: 20%">${d.approveTechLevelIINum}</td>
							<td style="width: 20%">${d.realTechLevelIINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelIINum}</td>
							<td style="width: 20%">${d.notOutTechLevelIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员三级</td>
							<td style="width: 20%">${d.approveTechLevelIIINum}</td>
							<td style="width: 20%">${d.realTechLevelIIINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelIIINum}</td>
							<td style="width: 20%">${d.notOutTechLevelIIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员四级</td>
							<td style="width: 20%">${d.approveTechLevelIVNum}</td>
							<td style="width: 20%">${d.realTechLevelIVNum}</td>
							<td style="width: 20%">${d.notIntoTechLevelIVNum}</td>
							<td style="width: 20%">${d.notOutTechLevelIVNum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员五级</td>
							<td style="width: 20%">${d.approveTechLevelVNum}</td>
							<td style="width: 20%">${d.realTechLevelVNum}</td>
							<td style="width: 20%">${d.notIntoTechLevelVNum}</td>
							<td style="width: 20%">${d.notOutTechLevelVNum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员六级</td>
							<td style="width: 20%">${d.approveTechLevelVINum}</td>
							<td style="width: 20%">${d.realTechLevelVINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelVINum}</td>
							<td style="width: 20%">${d.notOutTechLevelVINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员七级</td>
							<td style="width: 20%">${d.approveTechLevelVIINum}</td>
							<td style="width: 20%">${d.realTechLevelVIINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelVIINum}</td>
							<td style="width: 20%">${d.notOutTechLevelVIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员八级</td>
							<td style="width: 20%">${d.approveTechLevelVIIINum}</td>
							<td style="width: 20%">${d.realTechLevelVIIINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelVIIINum}</td>
							<td style="width: 20%">${d.notOutTechLevelVIIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员九级</td>
							<td style="width: 20%">${d.approveTechLevelIXNum}</td>
							<td style="width: 20%">${d.realTechLevelIXNum}</td>
							<td style="width: 20%">${d.notIntoTechLevelIXNum}</td>
							<td style="width: 20%">${d.notOutTechLevelIXNum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员十级</td>
							<td style="width: 20%">${d.approveTechLevelXNum}</td>
							<td style="width: 20%">${d.realTechLevelXNum}</td>
							<td style="width: 20%">${d.notIntoTechLevelXNum}</td>
							<td style="width: 20%">${d.notOutTechLevelXNum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员十一级</td>
							<td style="width: 20%">${d.approveTechLevelXINum}</td>
							<td style="width: 20%">${d.realTechLevelXINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelXINum}</td>
							<td style="width: 20%">${d.notOutTechLevelXINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员十二级</td>
							<td style="width: 20%">${d.approveTechLevelXIINum}</td>
							<td style="width: 20%">${d.realTechLevelXIINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelXIINum}</td>
							<td style="width: 20%">${d.notOutTechLevelXIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">专业技术人员十三级</td>
							<td style="width: 20%">${d.approveTechLevelXIIINum}</td>
							<td style="width: 20%">${d.realTechLevelXIIINum}</td>
							<td style="width: 20%">${d.notIntoTechLevelXIIINum}</td>
							<td style="width: 20%">${d.notOutTechLevelXIIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">工勤人员一级</td>
							<td style="width: 20%">${d.approveWorkLevelINum}</td>
							<td style="width: 20%">${d.realWorkLevelINum}</td>
							<td style="width: 20%">${d.notIntoWorkLevelINum}</td>
							<td style="width: 20%">${d.notOutWorkLevelINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">工勤人员二级</td>
							<td style="width: 20%">${d.approveWorkLevelIINum}</td>
							<td style="width: 20%">${d.realWorkLevelIINum}</td>
							<td style="width: 20%">${d.notIntoWorkLevelIINum}</td>
							<td style="width: 20%">${d.notOutWorkLevelIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">工勤人员三级</td>
							<td style="width: 20%">${d.approveWorkLevelIIINum}</td>
							<td style="width: 20%">${d.realWorkLevelIIINum}</td>
							<td style="width: 20%">${d.notIntoWorkLevelIIINum}</td>
							<td style="width: 20%">${d.notOutWorkLevelIIINum}</td>
						</tr>
						<tr>
							<td style="width: 20%">工勤人员四级</td>
							<td style="width: 20%">${d.approveWorkLevelIVNum}</td>
							<td style="width: 20%">${d.realWorkLevelIVNum}</td>
							<td style="width: 20%">${d.notIntoWorkLevelIVNum}</td>
							<td style="width: 20%">${d.notOutWorkLevelIVNum}</td>
						</tr>
						<tr>
							<td style="width: 20%">工勤人员五级</td>
							<td style="width: 20%">${d.approveWorkLevelVNum}</td>
							<td style="width: 20%">${d.realWorkLevelVNum}</td>
							<td style="width: 20%">${d.notIntoWorkLevelVNum}</td>
							<td style="width: 20%">${d.notOutWorkLevelVNum}</td>
						</tr>
	
	
					</tbody>
				</table>
			</smart:gridRow>
		</smart:accordionPanelItem>
	</smart:accordionPanel>

</smart:gridRow>


