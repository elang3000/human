<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<smart:gridRow>
							<smart:title title="考试考核体检结果信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="录用单位" infovalue="${d.draftUnitName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="录用部门:" placeholder="录用部门" name="draftDeptName" value="${d.draftDeptName}"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="原单位名称:" placeholder="原单位名称" value="${d.yuanUnitName}" name="yuanUnitName"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" display="block" labelName="原单位性质:" initSelectedKey="${d.yuanUnitType.id}" name="yuanUnitType.id" url="dictquery/sub/code/DM142"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" display="block" labelName="免基层实习:" initSelectedKey="${d.isBasePractice.id}" name="isBasePractice.id" url="dictquery/sub/code/DM215"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:date isNotNull="true" display="block" labelName="所在单位意见时间:" shortName="单位意见时间" isDefaultVal="true" value="${d.deptOpinionDate}" name="deptOpinionDate" id="time" placeholder="时间"></smart:date>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:date display="block" labelName="上级主管单位意见时间:" shortName="上级意见时间"  id="time2" value="${d.unitOpinionDate}" name="unitOpinionDate" placeholder="时间"></smart:date>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" display="block" labelName="录用标识:" initSelectedKey="${d.employResult.id}" name="employResult.id" url="dictquery/sub/code/DM035"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect isNotNull="true" display="block" labelName="录用情况:" initSelectedKey="${d.employSituation.id}" name="employSituation.id" url="dictquery/sub/code/DraftServantEmployStatus"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea display="block" labelName="备注:" name="remark">${d.remark}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea isNotNull="true" display="block" labelName="录用鉴定（评语）:" shortName="录用评语" name="employComment">${d.employComment}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>