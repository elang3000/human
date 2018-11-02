<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<smart:breadcrumbNavMenuItem iname="人员招录登记" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="登记人员"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="instflow/inforegister/flow" show="false"
								eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="姓名：" autocomplete="off" placeholder="输入姓名" name="name"></smart:textInput>
							</smart:gridColumn>
						    <smart:gridColumn colPart="4">
									<smart:singleSelect labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
									</smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
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
						<smart:gridColumn colPart="12" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<smart:button size="sm" method="add" title="新增">
									<smart:icon icon="plus">&nbsp;新增人员登记</smart:icon>
								</smart:button>
								<%-- 
								<smart:button theme="danger" size="sm" method="showPlanView"
									title="管理年度招录计划">
									<smart:icon icon="gears">&nbsp;管理年度招录计划</smart:icon>
								</smart:button> 
								--%>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList"
							url="ofcflow/recruit/employPlan/list" height="full-315"
							text="未找到有效数据！">
							<tr>
							    <smart:tableItem field="recuritType" width="110" sort="false">登记机关</smart:tableItem>
								<smart:tableItem field="recruitOrgan" width="110" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="employOrgan" width="110" sort="true">性别</smart:tableItem>
								<smart:tableItem field="yearPlan" width="140" sort="true">身份证</smart:tableItem>
								<smart:tableItem field="allowWeaveNum" width="90" sort="false">机构编制数</smart:tableItem>
								<smart:tableItem field="realNum" width="90" sort="false">机构实有人数</smart:tableItem>
								<smart:tableItem field="thisYearLackWeaveNum" width="110"
									sort="false">机构缺编数</smart:tableItem>
								<smart:tableItem field="chiefLackWeaveNum" width="110"
									sort="false">处级实职缺编人数</smart:tableItem>
								<smart:tableItem field="planEmployNum" width="110" sort="false">计划招录人数</smart:tableItem>
								<smart:tableItem field="planCutNum" width="110" sort="false">计划减员人数</smart:tableItem>
								<smart:tableItem field="planState" width="230" sort="false">状态</smart:tableItem>
								<smart:tableItem field="remark" width=".1" sort="false">备注</smart:tableItem>
								<smart:tableItem align="center" width=".2" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
										{{#  if(d.planStateSign=="8"){ }}
											<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="post"  title="职位上报">
												<i class="fa fa-cloud-upload"></i>
											</a>
										{{#  } }}
										{{#  if(d.planStateSign=="0"){ }}
										<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
											<i class="fa fa-edit"></i>
										</a>
										<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete"  title="删除">
											<i class="fa fa-trash"></i>
										</a>
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
				window.location.href = "instflow/inforegister/register";
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>