<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.wondersgroup.framework.util.SecurityUtils" %>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="流程审批" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<c:if test="${busType eq 'RecruitEmployPlan,RecruitPost'}">
								<smart:tabPanelItem show="false" turnurl="ofcflow/recruit/index"
									eId="" itemName="招录计划"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'ZhuanRenTLBIntoMgr_THIS,ZhuanRenTLBIntoMgr_OUTER,ZhuanRenTLBOutMgr'}">
								<smart:tabPanelItem turnurl="ofcflow/zrtlbIntoB/index" show="false" eId="" itemName="转入情况列表"></smart:tabPanelItem>
								<smart:tabPanelItem turnurl="ofcflow/zrtlbOutB/index" show="false" eId="" itemName="转出情况列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'ZhuanRenKLBIntoMgr_THIS,ZhuanRenKLBIntoMgr_OUTER,ZhuanRenKLBOutMgr'}">
								<smart:tabPanelItem turnurl="ofcflow/zrklbIntoB/index" show="false" eId="" itemName="转入情况列表"></smart:tabPanelItem>
								<smart:tabPanelItem turnurl="ofcflow/zrklbOutB/index" show="false" eId="" itemName="转出情况列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'DiaoRenIntoMgr_THIS,DiaoRenIntoMgr_OUTER,DiaoRenOutMgr_THIS'}">
								<smart:tabPanelItem turnurl="ofcflow/diaoren/index" show="false" eId="" itemName="调入情况列表"></smart:tabPanelItem>
								<smart:tabPanelItem turnurl="ofcflow/diaorenOut/index" show="false" eId="" itemName="调出情况列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'ReferenceExchange_THIS,ReferenceExchange_OUTER'}">
								<smart:tabPanelItem turnurl="ofcflow/exchangeB/index" show="false" eId="" itemName="调入情况列表"></smart:tabPanelItem>
								<smart:tabPanelItem turnurl="ofcflow/exchangeOutB/outindex" show="false" eId="" itemName="调出情况列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'ProbationServant,CancelProbationServant'}">
								<smart:tabPanelItem turnurl="ofcflow/probation/index" eId="" show="false" itemName="试用期名单列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'JOBSHIFT_PROMOTE,JOBSHIFT_DEMOTE,JOBSHIFT_DEPOSE,JOBSHIFT_SHIFT'}">
								<smart:tabPanelItem turnurl="ofcflow/jobchange/index" eId="" show="false" itemName="职务变动名单列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'Train'}">
								<smart:tabPanelItem turnurl="ofcflow/trainServant/index" eId="" show="false" itemName="培训学时考核列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'PunishServant'}">
								<smart:tabPanelItem turnurl="ofcflow/punish/index" eId="" show="false" itemName="处分备案列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'Abroad'}">
								<smart:tabPanelItem turnurl="ofcflow/abroadB/index" eId="" show="false" itemName="因公出国政审列表"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'ASSESS_REWARD'}">
								<smart:tabPanelItem turnurl="ofcflow/assess/assessIndex" eId="" show="false" itemName="考核记录"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'ResignServant'}">
								<smart:tabPanelItem turnurl="ofcflow/resignB/index" eId="" show="false" itemName="辞职申请"></smart:tabPanelItem>
								<smart:tabPanelItem turnurl="ofcflow/resignB/resignPeople/index" show="false" eId="" itemName="辞职人员"></smart:tabPanelItem>
							</c:if>
							<c:if test="${busType eq 'DeathServant'}">
								<smart:tabPanelItem turnurl="ofcflow/death/index" eId="" show="false" itemName="死亡列表"></smart:tabPanelItem>
							</c:if>
							<smart:tabPanelItem show="true" eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="来文单位：" name="sourceOrganNodeId"
								isSearch="true" display="block"
								url="system/organ/node/query?organTreeId=394e21fa-1eb6-42ee-ba32-50655fa16517"
								isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="2" >
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 			</smart:button>
									<smart:button size="sm" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   						</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="workflow/doing/page?busType=${busType}"
							height="full-200" text="未找到有效数据！">
							<tr>
								<smart:tableItem field="sourceOrganNode" width=".1" sort="true">来文单位</smart:tableItem>
								<smart:tableItem field="createTime" width=".1" sort="true">来文时间</smart:tableItem>
								<smart:tableItem field="targetOrganNode" width=".1" templet="topTpl">办理单位</smart:tableItem>
								<smart:tableItem field="targetSecurityUser" width=".1" templet="downTpl">办理人员</smart:tableItem>
								<smart:tableItem field="busName" width=".2">业务类型</smart:tableItem>
								<smart:tableItem field="ofcFlowDescription" width=".2">业务描述</smart:tableItem>
								<smart:tableItem align="center" width=".2" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar" >
								{{# if (d.targetSecurityUserId == '<%=SecurityUtils.getUserId()%>') { }}
								<smart:tableToolBtn theme="danger" event="shenpi" title="办理">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								{{# } }}
								<smart:tableToolBtn theme="normal" event="viewFlowRecord" title="查看流程记录">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			shenpi : function(data) {
				<c:if test="${busType eq 'RecruitEmployPlan,RecruitPost'}">
					var url = "ofcflow/recruit/planFlow";
				</c:if>
				<c:if test="${busType eq 'ZhuanRenTLBIntoMgr_THIS,ZhuanRenTLBIntoMgr_OUTER,ZhuanRenTLBOutMgr'}">
					var url = "ofcflow/zrtlbIntoB/workFlow";
				</c:if>
				<c:if test="${busType eq 'ZhuanRenKLBIntoMgr_THIS,ZhuanRenKLBIntoMgr_OUTER,ZhuanRenKLBOutMgr'}">
					var url = "ofcflow/zrklbIntoB/workFlow";
				</c:if>
				<c:if test="${busType eq 'DiaoRenIntoMgr_THIS,DiaoRenIntoMgr_OUTER,DiaoRenOutMgr_THIS'}">
					var url = "ofcflow/diaoren/workFlow";
				</c:if>
				<c:if test="${busType eq 'ReferenceExchange_THIS,ReferenceExchange_OUTER'}">
					var url = "ofcflow/exchangeB/workFlow";
				</c:if>
				<c:if test="${busType eq 'ProbationServant,CancelProbationServant'}">
					var url = "ofcflow/probation/workFlow";
				</c:if>
				<c:if test="${busType eq 'JOBSHIFT_PROMOTE,JOBSHIFT_DEMOTE,JOBSHIFT_DEPOSE,JOBSHIFT_SHIFT'}">
					var url = "ofcflow/jobchange/jobChangeFlow";
				</c:if>
				<c:if test="${busType eq 'Train'}">
					var url = "ofcflow/trainServant/trainFlow";
				</c:if>
				<c:if test="${busType eq 'PunishServant'}">
					var url = "ofcflow/punish/punishFlow";
				</c:if>
				<c:if test="${busType eq 'Abroad'}">
					var url = "ofcflow/abroad1/abroadFlow";
				</c:if>
				<c:if test="${busType eq 'ASSESS_REWARD'}">
					var url = "ofcflow/assess/assessFlow";
				</c:if>
				<c:if test="${busType eq 'ResignServant'}">
					var url = "ofcflow/resignB/resignFlow";
				</c:if>
				<c:if test="${busType eq 'DeathServant'}">
					var url = "ofcflow/death/deathFlow";
				</c:if>
				smart.show({
					title : '流程审批',
					size : 'full',
					url : url,
					params:{id:data.data.id},
					scrollbar : false
				});
			}
			,viewFlowRecord : function(record) {
				smart.show({
					title : '查看流程记录',
					url : 'workflow/detail/index',
					params : {
						id : record.data.id
					},
					size:'full',
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('.layui-form'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>