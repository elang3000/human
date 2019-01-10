<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="国企管理--查看人员信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="国企管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="查看人员信息" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true"  eId="" itemName="国企人员信息" ></smart:tabPanelItem>
							<%-- 
							<smart:tabPanelItem show="false" eId="" itemName="基本信息集" turnurl="ofc/servant_edit?id=${id}"></smart:tabPanelItem>
							<smart:tabPanelItem show="false" eId="" itemName="其他子集信息" turnurl="ofc/extend_list?id=${id}"></smart:tabPanelItem>
							 --%>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:title title="公务员登记表" style="margin-top: 5px;" color="blue" />
						<smart:grid>
							<smart:gridColumn colPart="8">
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="姓名" infovalue="${nationalCompany.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="性别" infovalue="${nationalCompany.sex.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="出生日期" infovalue="${nationalCompany.birthDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="单位部门" infovalue="${nationalCompany.departName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									 <smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="部门地址" infovalue=""></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="政治面貌" infovalue="${nationalCompany.politics.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="学位" infovalue="${nationalCompany.topDegree}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="人员类别" infovalue="${nationalCompany.personType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="身份证号" infovalue="${nationalCompany.cardNo}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="任职状态" infovalue="${nationalCompany.isOnHold.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="现职务名称" infovalue="${nationalCompany.nowPostName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="学历" infovalue="${nationalCompany.topEducation}"></smart:infoShowerLabel>
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
						</smart:grid>
					</smart:gridColumn>
				</smart:gridRow>
				
				<smart:gridRow>
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