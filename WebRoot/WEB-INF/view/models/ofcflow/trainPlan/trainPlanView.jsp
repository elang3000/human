<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="培训信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:title title="培训信息" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训名称" infovalue="${trainPlan.trainName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="开始日期" infovalue="${trainPlan.startDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="结束日期" infovalue="${trainPlan.endDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训学时" infovalue="${trainPlan.hours}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="描述" infovalue="${trainPlan.description}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
				
				
				<smart:gridRow>
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/train/trainPersonList?planId=${trainPlan.id}"
							height="full-225" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="name" width="100" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="hours" width="100" sort="false">培训学时</smart:tableItem>
								<smart:tableItem field="startDate" width="150" sort="false">开始时间</smart:tableItem>
								<smart:tableItem field="endDate" width="150" sort="false">结束时间</smart:tableItem>
								<smart:tableItem field="nature" width="150" sort="false">培训性质</smart:tableItem>
								<smart:tableItem field="level" width="150" sort="false">培训组织单位级别</smart:tableItem>
								<smart:tableItem field="funds" width="150" sort="false">培训经费（万元）</smart:tableItem>
								<smart:tableItem field="isAbroad" width="125" sort="false">是否境外培训</smart:tableItem>
							</tr>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
				
					<smart:gridRow>
					<smart:line color="blue" />
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
						     <smart:button size="sm" method="goBack" title="返回" theme="warm">
								<smart:icon icon="reply"></smart:icon>&nbsp;返回
							</smart:button>
						 </smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
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