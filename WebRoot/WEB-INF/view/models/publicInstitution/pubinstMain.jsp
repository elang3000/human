<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--事业单位人员登记"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="信息维护" ></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="人员登记信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true"  eId="" itemName="登记信息" ></smart:tabPanelItem>
							<smart:tabPanelItem show="false" eId="" itemName="基本信息" turnurl="publicInstitution/edit?id=${id}"></smart:tabPanelItem>
							<smart:tabPanelItem show="false" eId="" itemName="其他信息" turnurl="publicInstitution/extendlist?id=${id}"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:title title="人员登记信息" style="margin-top: 5px;" color="blue" />
						<smart:grid>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="姓名" infovalue="${pubinst.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="性别" infovalue="${pubinst.sex.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="出生日期" infovalue="${pubinst.birthDate}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="籍贯" infovalue="${pubinst.nativePlaceName}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="身份证号" infovalue="${pubinst.cardNoView}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="政治面貌" infovalue="${pubinst.politics.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="学历" infovalue="${pubinst.topEducation}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="学位" infovalue="${pubinst.topDegree}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
										<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="单位" infovalue="${pubinst.departName}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="现任职务" infovalue="${pubinst.nowPostName}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="健康状况" infovalue="${pubinst.health.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="婚姻状况" infovalue="${pubinst.marriage.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="民族" infovalue="${pubinst.nation.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="参加工作时间" infovalue="${pubinst.attendDate}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:gridColumn>
								<smart:gridColumn colPart="3" colOffset="1">
									<smart:gridRow>
										<smart:gridColumn colPart="12">
												<img style="width:150px;height:200px;min-width:150px;min-height:200px;" alt="照片" src="ftp/getImg?imgName=${pubinst.photoPath}">
										</smart:gridColumn>
									</smart:gridRow>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:infoShowerLabel infoname="个人情况备注" infovalue="${pubinst.personRemark}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
		<smart:title title="" style="margin-top: 5px;" color="blue" />
							
							
							
							<div style="margin-top:5px;" class="layui-row">
								<div class="layui-col-md12">
									<div class="layui-form-item">
										<label class="layui-form-label formLabel">工作简历：</label>
										<div class="layui-inline">
							                <div class="layui-form-mid">
							                	<c:forEach var="info" items="${experienceInfos}" varStatus="index">
											         ${info} <c:if test="${index.last==false}"><br/></c:if>
												</c:forEach>
							                </div>
							            </div>
									</div>
								</div>
							</div>
							
							
							<div style="margin-top:5px;" class="layui-row">
								<div class="layui-col-md12">
									<div class="layui-form-item">
										<label class="layui-form-label formLabel">奖惩情况：</label>
										<div class="layui-inline">
							                <div class="layui-form-mid">
							                	<c:forEach var="info" items="${rewardAndPunishInfos}" varStatus="index">
											         ${info} <c:if test="${index.last==false}"><br/></c:if>
												</c:forEach>
							                </div>
							            </div>
									</div>
								</div>
							</div>
							
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:infoShowerLabel infoname="所在机关意见" infovalue="同意登记"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:infoShowerLabel infoname="审核机关意见" infovalue="同意登记"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:infoShowerLabel infoname="审批机关意见" infovalue="同意登记"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							
							
						</smart:grid>
					</smart:gridColumn>
				</smart:gridRow>
				
				<smart:gridRow>
				   <smart:gridColumn>
				     <smart:buttonGroup container="true">
					    <%-- <smart:button size="sm" method="printDJB" title="打印公务员登记表" theme="normal">
							<smart:icon icon="print"></smart:icon>&nbsp;打印公务员登记表
						</smart:button>
						<smart:button size="sm" method="printRMB" title="打印公务员任免表">
							<smart:icon icon="print"></smart:icon>&nbsp;打印公务员任免表
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
				printDJB : function(data) {
				},
				printRMB : function(data) {
				},
				goBack : function(data) {
					 <!-- window.location.href='publicInstitution/list'; -->
					window.history.back(-1);
				}
	</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>