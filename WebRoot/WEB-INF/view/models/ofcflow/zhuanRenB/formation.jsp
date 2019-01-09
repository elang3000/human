<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<smart:gridRow>
	<smart:gridColumn colPart="4">
		<smart:infoShowerLabel infoname="批准编制总数 "
			infovalue="${d.unitPlanningTotal}"></smart:infoShowerLabel>
	</smart:gridColumn>
	<smart:gridColumn colPart="4">
		<smart:infoShowerLabel infoname="实有人数" infovalue="${d.actualNumber}"></smart:infoShowerLabel>
	</smart:gridColumn>
	<smart:gridColumn colPart="4">
		<smart:infoShowerLabel infoname="空编人数"
			infovalue="${d.vacancyExcessNumber}"></smart:infoShowerLabel>
	</smart:gridColumn>
</smart:gridRow>
<smart:gridRow>
	<smart:gridColumn colPart="4">
		<smart:infoShowerLabel infoname="尚未调入人数 " infovalue="${d.notIntoNum}"></smart:infoShowerLabel>
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
				<td style="width: 20%">处级</td>
				<td style="width: 20%">${d.approveChuNum}</td>
				<td style="width: 20%">${d.realChuNum}</td>
				<td style="width: 20%"></td>
				<td style="width: 20%"></td>
			</tr>
			<tr>
				<td style="width: 20%">乡科级正职（领导）</td>
				<td style="width: 20%">${d.approvePlusKeLeaderNum}</td>
				<td style="width: 20%">${d.realPlusKeLeaderNum}</td>
				<td style="width: 20%">${d.notIntoPlusKeLeaderNum}</td>
				<td style="width: 20%">${d.notOutPlusKeLeaderNum}</td>
			</tr>
			<tr>
				<td style="width: 20%">乡科级正职（非领导）</td>
				<td style="width: 20%">${d.approvePlusKeNoLeaderNum}</td>
				<td style="width: 20%">${d.realPlusKeNoLeaderNum}</td>
				<td style="width: 20%">${d.notIntoPlusKeNoLeaderNum}</td>
				<td style="width: 20%">${d.notOutPlusKeNoLeaderNum}</td>
			</tr>
			<tr>
				<td style="width: 20%">乡科级副职（领导）</td>
				<td style="width: 20%">${d.approveMinusKeLeaderNum}</td>
				<td style="width: 20%">${d.realMinusKeLeaderNum}</td>
				<td style="width: 20%">${d.notIntoMinusKeLeaderNum}</td>
				<td style="width: 20%">${d.notOutMinusKeLeaderNum}</td>
			</tr>
			<tr>
				<td style="width: 20%">乡科级副职（非领导）</td>
				<td style="width: 20%">${d.approveMinusKeNoLeaderNum}</td>
				<td style="width: 20%">${d.realMinusKeNoLeaderNum}</td>
				<td style="width: 20%">${d.notIntoMinusKeNoLeaderNum}</td>
				<td style="width: 20%">${d.notOutMinusKeNoLeaderNum}</td>
			</tr>
		</tbody>
	</table>
</smart:gridRow>