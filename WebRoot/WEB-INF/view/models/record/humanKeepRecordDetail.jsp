<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--备案详情" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="信息维护"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="备案详情" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:title title="人员基本信息" style="margin-top: 5px;" color="blue" />
						<smart:grid>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="单位部门"
												infovalue="${servant.departName}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="姓名"
												infovalue="${servant.name}"></smart:infoShowerLabel>
											<smart:textInput type="hidden" name="id"
												value="${servant.id}"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="姓名拼音"
												infovalue="${servant.pinYinName}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="姓名拼音缩写"
												infovalue="${servant.shortName}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="出生日期"
												infovalue="${servant.birthDate}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="性别"
												infovalue="${servant.sex.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="民族"
												infovalue="${servant.nation.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="政治面貌"
												infovalue="${servant.politics.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="出生地"
												infovalue="${servant.birthPlace.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="籍贯"
												infovalue="${servant.nativePlace.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="成长地"
												infovalue="${servant.growPlaceName}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="户籍所在地"
												infovalue="${servant.residencePlace}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:gridColumn>
								<smart:gridColumn colPart="3" colOffset="1">
									<smart:gridRow>
										<smart:gridColumn colPart="12">
											<img alt="照片"
												src="static/image/20170705135600.jpg">
										</smart:gridColumn>
									</smart:gridRow>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="婚姻状况"
											infovalue="${servant.marriage.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="健康状况"
											infovalue="${servant.health.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="健康状况描述"
										infovalue="${servant.healthDescribe}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="个人身份有效证件名称"
											infovalue="${servant.cardType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="个人身份有效证件号码"
											infovalue="${servant.cardTypeNo}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="公民身份证号"
										infovalue="${servant.cardNo}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="专业技术职务"
											infovalue="${servant.expertisePost}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="专长"
											infovalue="${servant.expertise}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="专业技术类公务员任职资格"
										infovalue="${servant.qualifications.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="入党时间"
											infovalue="${servant.joinParty}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="第二党派"
											infovalue="${servant.secondParty.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="第三党派"
										infovalue="${servant.thirdParty.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:title title="备案信息" style="margin-top: 5px;" color="blue" />
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="人事单位类型"
											infovalue="${humanKeepRecord.unitType}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="备案类型"
											infovalue="${humanKeepRecord.recordType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="备案状态"
										infovalue="${humanKeepRecord.returned.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:infoShowerLabel infoname="备案描述"
										infovalue="${humanKeepRecord.description}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:grid>
					</smart:gridColumn>
				</smart:gridRow>

				<smart:gridRow>
					<smart:gridColumn>
						<smart:buttonGroup container="true">
							<%-- <smart:button size="sm" method="printRMB" title="打印备案详情表">
							<smart:icon icon="print"></smart:icon>&nbsp;打印备案详情表
					    </smart:button> --%>
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
		<smart:buttonScriptAction>
				printRMB : function(data) {
				},
				goBack : function(data) {
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
				}
	</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>