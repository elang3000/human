<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--人事管理详情" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="信息维护"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="人事管理详情" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
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
												<smart:infoShowerLabel infoname="单位部门" infovalue="${servant.departName}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
												<smart:textInput type="hidden" name="id" value="${servant.id}"></smart:textInput>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="姓名拼音" infovalue="${servant.pinYinName}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="姓名拼音缩写" infovalue="${servant.shortName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="出生日期" infovalue="${servant.birthDate}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="性别" infovalue="${servant.sex.name}"></smart:infoShowerLabel>
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
											<smart:gridColumn colPart="12">
												<smart:infoShowerLabel infoname="出生地" infovalue="${servant.birthPlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:infoShowerLabel infoname="籍贯" infovalue="${servant.nativePlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:infoShowerLabel infoname="成长地" infovalue="${servant.growPlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
									<smart:gridColumn colPart="3" colOffset="1">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<img style="width:150px;height:200px;min-width:150px;min-height:200px;" alt="照片" src="ftp/getImg?imgName=${servant.photoPath}">
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="户籍所在地" infovalue="${servant.residencePlace}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="婚姻状况" infovalue="${servant.marriage.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="有效证件类型" infovalue="${servant.cardType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="有效证件号码" infovalue="${servant.cardTypeNo}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="公民身份证号" infovalue="${servant.cardNoView}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="入党时间" infovalue="${servant.joinParty}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="第二党派" infovalue="${servant.secondParty.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="第三党派" infovalue="${servant.thirdParty.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="个人情况" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="健康状况" infovalue="${servant.health.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="8">
										<smart:infoShowerLabel infoname="健康状况描述" infovalue="${servant.healthDescribe}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>	
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="办公电话" infovalue="${servant.officePhone}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="住宅电话" infovalue="${servant.homePhone}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="移动电话" infovalue="${servant.mobilePhone}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="秘书电话" infovalue="${servant.secretaryPhone}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="电子邮箱" infovalue="${servant.email}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="邮政编码" infovalue="${servant.addressPost}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="家庭住址" infovalue="${servant.homeAddress}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:infoShowerLabel infoname="专长" infovalue="${servant.expertise}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:infoShowerLabel infoname="爱好" infovalue="${servant.interested}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="最高学历" infovalue="${servant.topEducation}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="最高学位" infovalue="${servant.topDegree}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="毕业时间" infovalue="${servant.graduateDateStr}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:infoShowerLabel infoname="从事专业" infovalue="${servant.doingSpecialty.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="工作情况" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="参加工作日期" infovalue="${servant.attendDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="连续工龄" infovalue="${servant.workYear}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="基层工作单位" infovalue="${servant.grassRootUnit}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="基础工作年限" infovalue="${servant.grassRootYear}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel shortName="现任最高职务" infoname="现任最高职务名称" infovalue="${servant.nowPostCode.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="职务属性" infovalue="${servant.nowPostAttribute.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="职级名称" infovalue="${servant.nowJobLevel.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="管理信息" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="管理类别" infovalue="${servant.levelManage.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="人员类别" infovalue="${servant.personType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="离岗退养标识" infovalue="${servant.isLeavePost.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<div class="layui-col-md12">
									<div class="layui-form-item">
										<label class="layui-form-label formLabel">备注：</label>
										<div class="layui-inline">
							                <div class="layui-form-mid">
							                	${servant.personRemark}
							                </div>
							            </div>
									</div>
								</div>
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					
							<smart:title title="人事管理信息" style="margin-top: 5px;" color="blue" />
							<smart:gridRow>
								<smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="人事管理类型"
											infovalue="${managerRecord.recordType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<c:if test="${managerRecord.itemType==0}">
										<smart:gridColumn colPart="4">
											<smart:infoShowerLabel infoname="进出管理类型"
												infovalue="进"></smart:infoShowerLabel>
										</smart:gridColumn>
									</c:if>
									<c:if test="${managerRecord.itemType==1}">
										<smart:gridColumn colPart="4">
											<smart:infoShowerLabel infoname="进出管理类型"
												infovalue="出"></smart:infoShowerLabel>
										</smart:gridColumn>
									</c:if>
									<c:if test="${managerRecord.itemType==2}">
										<smart:gridColumn colPart="4">
											<smart:infoShowerLabel infoname="进出管理类型"
												infovalue="管理"></smart:infoShowerLabel>
										</smart:gridColumn>
									</c:if>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="记录时间"
											infovalue="${managerRecord.recordTime}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridColumn>
								
								<smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="事项发生时单位"
											infovalue="${managerRecord.organName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridColumn>
							</smart:gridRow>

				<smart:gridRow>
					<smart:gridColumn>
						<smart:buttonGroup container="true">
							<smart:button size="sm" method="goBack" title="返回" theme="primary">
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
				goBack : function(data) {
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
				}
	</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>