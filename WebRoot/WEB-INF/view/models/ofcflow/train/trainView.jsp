<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="培训信息" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form>
					<smart:fromTokenTag />
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>

								<smart:gridRow>
									<smart:textInput type="hidden" name="train.id"
										value="${train.id}"></smart:textInput>
									<smart:title title="培训信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训班名称"
											infovalue="${train.trainClassName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训类别"
											infovalue="${train.studyType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训机构名"
											infovalue="${train.trainOrganName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训机构类别"
											infovalue="${train.trainOrganType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="主办单位"
											infovalue="${train.hostOrgan.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训时长（天）"
											infovalue="${train.days}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训学时（时）"
											infovalue="${train.hours}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="开始日期"
											infovalue="${train.startDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="结束日期 "
											infovalue="${train.endDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训性质 "
											infovalue="${train.nature.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="组织单位级别"
											infovalue="${train.level.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel shortName="出境、市标识" infoname="出境、出市学习标识"
											infovalue="${train.isAbroad.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="资金（万元）"
											infovalue="${train.funds}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>

					<smart:gridRow>
						<smart:gridColumn>
							<smart:table id="navigationList"
								url="ofcflow/trainServant/pageTrainServant?id=${train.id}"
								text="未找到用户数据！" page="true">
								<tr>
									<smart:tableItem field="name" width=".12" sort="true">姓名</smart:tableItem>
									<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
									<smart:tableItem field="cardNo" width=".22" sort="true">身份证号</smart:tableItem>
									<smart:tableItem field="departName" width=".14" sort="false">单位部门</smart:tableItem>
									<smart:tableItem field="postName" width=".14" sort="false">职务名称</smart:tableItem>
									<smart:tableItem field="postAttributeName" width=".14"
										sort="false">职务属性</smart:tableItem>
									<smart:tableItem field="jobLevel" width=".14" sort="false">职级名称</smart:tableItem>
								</tr>
							</smart:table>
						</smart:gridColumn>
					</smart:gridRow>

					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
							<smart:buttonGroup container="true">
								<smart:button theme="primary" size="sm" method="goBack" title="返回">
									<smart:icon icon="pencil">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="false"
			sort="true" rowEdit="true">
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>