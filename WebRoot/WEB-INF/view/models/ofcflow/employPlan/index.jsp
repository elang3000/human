<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
					<smart:breadcrumbNavMenuItem iname="职位简章" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="招录计划"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/recruit/flow" show="false"
								eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:singleSelect display="block" isAddDefaltOption="true" labelName="年度招录计划："
										name="yearPlan.id" url="ofcflow/recruit/year/find"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="用人机关：" placeholder="请输入用人机关"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="2">
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
							</smart:gridRow>
						</smart:form>
						<smart:gridColumn colPart="12" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<shiro:hasPermission name="ADD_EMPLOY_PLAN_BTN">
									<smart:button size="sm" method="add" title="新增" theme="normal">
										<smart:icon icon="plus">&nbsp;新增单位招录计划</smart:icon>
									</smart:button>
								</shiro:hasPermission>
								<shiro:hasPermission name="MANAGE_EMPLOY_YEARPLAN_BTN">
									<smart:button theme="danger" size="sm" method="showPlanView"
										title="管理年度招录计划">
										<smart:icon icon="gears">&nbsp;管理年度招录计划</smart:icon>
									</smart:button>
								</shiro:hasPermission>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList"
							url="ofcflow/recruit/employPlan/list" height="full-250"
							text="未找到有效数据！">
							<tr>
								<smart:tableItem field="employOrgan" width=".1" sort="true">用人机关</smart:tableItem>
								<smart:tableItem field="yearPlan" width=".2" sort="true">年度招录计划</smart:tableItem>
								<smart:tableItem field="recuritType" width=".1" sort="false">编制类型</smart:tableItem>
								<smart:tableItem field="allowWeaveNum" width=".1" sort="false">机构编制数</smart:tableItem>
								<smart:tableItem field="realNum" width=".1" sort="false">机构实有人数</smart:tableItem>
								<smart:tableItem field="thisYearLackWeaveNum" width=".1" sort="false">机构缺编数</smart:tableItem>
								<smart:tableItem field="planEmployNum" width=".1" sort="false">计划招录人数</smart:tableItem>
								<smart:tableItem field="planState" width=".1" sort="false">状态</smart:tableItem>
								<smart:tableItem align="center" width=".1" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
										{{#  if(d.planStateSign=="8"){ }}
											<shiro:hasPermission name="POST_REPORT_BTN">
												<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="post"  title="职位上报">
													<i class="fa fa-cloud-upload"></i>
												</a>
											</shiro:hasPermission>
										{{#  } }}
										{{#  if(d.planStateSign=="0"){ }}
											<shiro:hasPermission name="EDIT_EMPLOY_PLAN_BTN">
												<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
													<i class="fa fa-edit"></i>
												</a>
											</shiro:hasPermission>
											<shiro:hasPermission name="REMOVE_EMPLOY_PLAN_BTN">
												<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete"  title="删除">
													<i class="fa fa-trash"></i>
												</a>
											</shiro:hasPermission>
 										{{#  } }}
									<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="view"  title="查看">
										<i class="fa fa-eye"></i>
									</a>
							</script>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			post : function(data) {
				smart.show({
					title : '职位上报',
					size : 'full',
					url : "ofcflow/recruit/employPlan/post",
					params:{employPlanId:data.data.id},
					scrollbar : false
				});
			},
			delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/recruit/employPlan/delete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			},
			edit : function(data) {
				smart.show({
					title : '组织招录计划',
					size : 'full',
					url : "ofcflow/recruit/employPlan/plan",
					params:{employPlanId:data.data.id},
					scrollbar : false
				});
			}
			,view : function(data) {
				smart.show({
					title : '查看招录计划',
					size : 'full',
					url : "ofcflow/recruit/planView",
					params:{id:data.data.id},
					scrollbar : false
				});
			}
			,flowView : function(data) {
				smart.show({
					title : '流程信息查看',
					size : 'full',
					url : "ofcflow/recruit/planFlowInfo",
					params:{id:data.data.id},
					scrollbar : false
				});
			}
		    ,reload : function(){
				table.reload('navigationList');
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
			},
			add : function() {
				smart.show({
					title : '新增招录计划',
					size : 'full',
					url : "ofcflow/recruit/pre",
					size:'xs',
					scrollbar : false
				});
			},
			showPlanView : function(data) {
				 window.location.href="ofcflow/recruit/year/list";
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>