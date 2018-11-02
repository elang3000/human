<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="处分信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
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
									<smart:title title="处分信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="处分原始文件号" infovalue="${punishServant.punishFileName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="处分原因" infovalue="${punishServant.punishReason.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="处分批准日期" infovalue="${punishServant.punishApprovalDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="处分期限（年）" infovalue="${punishServant.punishYear}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="处分名称" infovalue="${punishServant.punishCode.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn>
										<smart:infoShowerLabel infoname="处分说明" infovalue="${punishServant.content}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
				<smart:form id="editForm" action="ofcflow/punish/operationFlow">
					<smart:fromTokenTag/>
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
	<smart:scriptHead models="table,form,layer,element,laydate,selectResource">
		<smart:utils/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			},
		</smart:buttonScriptAction>
		<smart:dateRender id="punishApprovalDate"/>
	</smart:scriptHead>
</smart:body>
</html>