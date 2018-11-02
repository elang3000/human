<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--公务员登记"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="信息维护"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员登记表" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true"  eId="" itemName="公务员登记表" ></smart:tabPanelItem>
							<smart:tabPanelItem show="false" eId="" itemName="基本信息集" turnurl="ofc/servant_edit?id=${id}"></smart:tabPanelItem>
							<smart:tabPanelItem show="false" eId="" itemName="其他子集信息" turnurl="ofc/extend_list?id=${id}"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:title title="公务员登记表" style="margin-top: 5px;" color="blue" />
						<smart:grid>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="性别" infovalue="${servant.sex.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="出生日期" infovalue="${servant.birthDate}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="籍贯" infovalue="${servant.nativePlaceName}"></smart:infoShowerLabel>
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
											<smart:infoShowerLabel infoname="健康状况" infovalue="${servant.health.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="婚姻状况" infovalue="${servant.marriage.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="身份证号" infovalue="${servant.cardNo}"></smart:infoShowerLabel>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
											<smart:infoShowerLabel infoname="参加工作时间" infovalue="${servant.attendDate}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:gridColumn>
								<smart:gridColumn colPart="3" colOffset="1">
									<smart:gridRow>
										<smart:gridColumn colPart="12">
											<img style="width:150px;height:200px;min-width:80px;min-height:120px;" alt="照片" src="static/image/20170705135600.jpg">
										</smart:gridColumn>
									</smart:gridRow>
								</smart:gridColumn>
							</smart:gridRow>
							
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
										<label class="layui-form-label formLabel">何时受过何种奖惩：</label>
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
							
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:infoShowerLabel infoname="个人基本情况备注" infovalue="${servant.personRemark}"></smart:infoShowerLabel>
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
					window.location.href='ofc/list';
				}
	</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>