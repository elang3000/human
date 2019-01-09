<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--年度考核奖励" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="年度考核奖励" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>

				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="考核记录"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/assess/flow" show="false"
								eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>


				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="jobChange_searchForm">
							<smart:gridColumn colPart="5">
								<smart:date labelName="考核年份" name="year" id="assessYear"
									display="block" placeholder="年份"></smart:date>
							</smart:gridColumn>
							<smart:gridColumn colPart="7">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 			</smart:button>
									<smart:button size="sm" method="history" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   							</smart:button>
									<c:if test="${isBureau }">
										<shiro:hasPermission name="ASSESS_START">
											<smart:button size="sm" method="history" id="seasonAssess"
												title="发起季度考核" theme="normal">
												<smart:icon icon="plus"></smart:icon>&nbsp;发起季度考核
											</smart:button>
											<smart:button size="sm" method="history" id="yearAssess"
												title="发起年度考核" theme="normal">
												<smart:icon icon="plus-circle"></smart:icon>&nbsp;发起年度考核
											</smart:button>
										</shiro:hasPermission>
		   							</c:if>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table  id="navigationList"
							url="ofcflow/assess/assessCollects" height="full-195"
							text="未找到有效数据！">
							<tr>
								<smart:tableItem field="assessmentTypeStr" width=".1"
									sort="true">考核类型</smart:tableItem>
								<smart:tableItem field="year" width=".1" sort="true">考核年度</smart:tableItem>
								<smart:tableItem field="seasonStr" width=".1" sort="true">考核季度</smart:tableItem>
								<smart:tableItem field="draftOutstandingPercentStr" width=".1" sort="true">默认优秀比例</smart:tableItem>
								<c:if test="${!isBureau }">
									<smart:tableItem field="createTime" width=".1" sort="true">创建时间</smart:tableItem>
									<smart:tableItem field="unitStatus" width=".1" sort="true">考核状态</smart:tableItem>
								</c:if>
								<c:if test="${isBureau }">
								    <smart:tableItem field="createTime" width=".1" sort="true">创建时间</smart:tableItem>
									<smart:tableItem field="statusStr" width=".1" sort="true">考核状态</smart:tableItem>
								</c:if>
								
								<smart:tableItem field="remark" width=".2" sort="false">考核备注</smart:tableItem>
								<smart:tableItem align="center" width=".2" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
								<c:if test="${isBureau }">
								<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="view"  title="查看考核状态">
											<i class="fa fa-eye"></i>
										</a>
									<shiro:hasPermission name="ASSESS_EDIT_NUM">
										{{#  if(d.assessmentType=="1"){ }}
											<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="editPercent"  title="编辑拟优秀人数">
												<i class="fa fa-edit"></i>
											</a>
										{{#  } }}
									</shiro:hasPermission>
								</c:if>

									<shiro:hasPermission name="ASSESS_EDIT_BTN">

										{{#  if(d.unitStatus=="考核完成"){ }}
											<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="unitCheck"  title="考核查看">
												<i class="fa fa-eye"></i>
											</a>
										{{#  } }}
										{{#  if(d.unitStatus!="考核完成"){ }}
											<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="unitCheck"  title="单位人员考核">
												<i class="fa fa-legal"></i>
											</a>
										{{#  } }}
									</shiro:hasPermission>
									<shiro:hasPermission name="ASSESS_START_FLOW">
										<%--<c:if test="${!isBureau }">--%>
											{{#  if(d.assessmentTypeStr=="年度考核"){ }}
												{{#  if(d.flowStatus!="0" && d.flowStatus!="1"){ }}
													<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="startUnitAssess"  title="发起考核流程">
														<i class="fa fa-send"></i>
													</a>
												{{#  } }}

												{{#  if(d.flowStatus=="0" || d.flowStatus=="1"){ }}
													<%--<a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event=""  title="流程已经启动">
														<i class="fa fa-send"></i>
													</a>--%>
												{{#  } }}
											{{#  } }}
										<%--</c:if>--%>
									</shiro:hasPermission>
							</script>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:dateRender id="assessYear" type="year" />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				unitCheck : function(data) {
					smart.show({
					title : '单位人员考核',
					size : 'full',
					url : 'ofcflow/assess/unitCheckIndexPage/'+data.data.id,
					scrollbar : false
					});
				},
				startUnitAssess : function(data) {
					smart.show({
					title : '发起单位人员考核',
					size : 'full',
					url : 'ofcflow/assess/assessProgressStartView/'+data.data.id,
					scrollbar : false
					});
				},
				editPercent : function(data) {
					smart.show({
					title : '编辑年度考核优秀比例',
					size : 'full',
					url : 'ofcflow/assess/assessTarget/'+data.data.id,
					scrollbar : false
					});
				},
				view : function(data) {
					smart.show({
					title : '查看考核状态',
					size : 'full',
					url : 'ofcflow/assess/assessProgressView/'+data.data.id,
					scrollbar : false
					});
				}
			</smart:tableScriptAction>
		var buttonInvokeMethod = {
			search : function() {
				var params = utils.json($('#jobChange_searchForm'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		};

		$('#jobChange_searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
		
		$('#seasonAssess').click(function(){
					smart.show({
					title : '季度考核',
					url : 'ofcflow/assess/assessStart/2',
					size : 'lg',
					scrollbar : false
					});
		});
		
		$('#yearAssess').click(function(){
					smart.show({
					title : '年度考核',
					url : 'ofcflow/assess/assessStart/1',
					size : 'lg',
					scrollbar : false
					});
		});
	</smart:scriptHead>
</smart:body>
</html>