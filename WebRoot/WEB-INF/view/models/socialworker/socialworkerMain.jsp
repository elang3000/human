<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="社工管理--查看人员信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="社工管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="查看人员信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true"  eId="" itemName="社工人员信息" ></smart:tabPanelItem>
							
	 							<%--  <smart:tabPanelItem show="false" eId="" itemName="基本信息集" turnurl="ofc/servant_edit?id=${id}"></smart:tabPanelItem>  
								<smart:tabPanelItem show="false" eId="" itemName="其他子集信息" turnurl="ofc/extend_list?id=${id}"></smart:tabPanelItem>
								 --%>
								<smart:tabPanelItem show="false" eId="" itemName="其他信息" turnurl="socialworker/extendlist?id=${id}"></smart:tabPanelItem>
							
							 
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:title title="社工人员登记表" style="margin-top: 5px;" color="blue" />
						<smart:grid>
							<smart:gridColumn colPart="8">
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="姓名" infovalue="${socialWorker.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="性别" infovalue="${socialWorker.sex.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="出生日期" infovalue="${socialWorker.birthDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="民族"
											infovalue="${socialWorker.nation.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="身份证号" infovalue="${socialWorker.cardNo}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="政治面貌" infovalue="${socialWorker.politics.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="学历" infovalue="${socialWorker.topEducation}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="学位" infovalue="${socialWorker.topDegree}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="人员类别" infovalue="${socialWorker.personType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="现职务名称" infovalue="${socialWorker.nowPostName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="单位部门" infovalue="${socialWorker.departName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="任职状态" infovalue="${socialWorker.isOnHold.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="健康状况" infovalue="${socialWorker.health.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="婚姻状况" infovalue="${socialWorker.marriage.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="参加工作时间"
											infovalue="${socialWorker.attendDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								
								<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:infoShowerLabel infoname="个人情况备注" infovalue="${socialWorker.personRemark}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>

								<%-- <smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="工作简历" infovalue="2018-04~ 至今     市安全生产监督管理局    科长"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="何时受过何种奖惩" infovalue="在2018-04-07公务员纪律处分"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow> --%>
								
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
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="所在机关意见" infovalue="同意登记"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="审核机关意见" infovalue="同意登记"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="审批机关意见" infovalue="同意登记"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<%-- <smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="备注" infovalue="${socialWorker.personRemark}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow> --%>
							
							</smart:gridColumn> 
							<smart:gridColumn colPart="3" colOffset="1">
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<img alt="照片" src="static/image/20170705135600.jpg">
									</smart:gridColumn>
								</smart:gridRow>
							</smart:gridColumn>
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
					window.location.href='socialworker/list';
				}
	</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>