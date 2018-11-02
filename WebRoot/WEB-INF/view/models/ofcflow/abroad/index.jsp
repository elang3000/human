<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--因公出国政审" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="因公出国政审" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="因公出国政审列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/abroad/flow" show="false" eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block" isAddDefaltOption="true" labelName="因公出国计划："
									name="yearPlan.id" url="ofcflow/abroad/year/find"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="姓名：" placeholder="请输入姓名" name="servant.name"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="出境国家（地区）：" placeholder="请输入出境国家（地区）" name="yearPlan.country"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6"  colOffset="6">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 			</smart:button>
									<smart:button size="sm" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   							</smart:button>
		   							<shiro:hasPermission name="ADD_ABROAD_PLAN_BTN">
		   							<smart:button size="sm" method="add" title="新增因公出国事项">
										<smart:icon icon="plus">&nbsp;新增因公出国事项</smart:icon>
									</smart:button>
									</shiro:hasPermission>
									
									<shiro:hasPermission name="MANGER_ABROAD_YEAR_PLAN_BTN">
									<smart:button theme="danger" size="sm" method="showPlanView"
										title="管理因公出国计划">
										<smart:icon icon="gears">&nbsp;管理因公出国计划</smart:icon>
									</smart:button>
									</shiro:hasPermission>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/abroad/pageList" 
							height="full-255" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="yearPlanName" width=".15" sort="false">因公出国计划</smart:tableItem>
								<smart:tableItem field="country" width=".15" sort="false">因公出国国家（地区）</smart:tableItem>
								<smart:tableItem field="day" width=".1" sort="false">停留天数</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="departName" width=".1" sort="false">工作单位</smart:tableItem>
								<smart:tableItem field="postName" width=".1" sort="false">职务职称</smart:tableItem>
								<smart:tableItem field="status" width=".13" sort="false">状态</smart:tableItem>
								<smart:tableItem align="center" width=".13" fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
										{{#  if(d.statusSign==1){ }}
										<shiro:hasPermission name="EDIT_ABROAD_PLAN_BTN">
										<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="edit"  title="编辑">
											<i class="fa fa-edit"></i>
										</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="REMOVE_ABROAD_PLAN_BTN">
										<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"  title="删除">
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
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:dateRender id="startDate" />
		<smart:dateRender id="endDate" />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
					title : '编辑因公出国事项',
					size : 'full',
					url : 'ofcflow/abroad/addAbroadPlan?id='+data.data.id,
					scrollbar : false,
					});
				},
				upload : function(data) {
					smart.show({
					title : '因公出国事项上报',
					size : 'full',
					url : 'ofcflow/abroad/uploadAbroadPlan?id='+data.data.id,
					scrollbar : false,
					});
				},
				addUser : function(data) {
					smart.show({
					title : '因公出国事项人员添加',
					size : 'full',
					url : 'ofcflow/abroad/addAbroadPerson?id='+data.data.id,
					scrollbar : false,
					});
				},
				remove : function(data) {
					smart.confirm({
						title:'删除因公出国事项',
						message:'确认删除因公出国事项？',
						type:'POST',
						url:'ofcflow/abroad/remove',
						params : {id : data.data.id},
						callback : function() {
							var params = smart.json($('#searchForm'));
							table.reload('navigationList', {
								where : params
							});
						}
				});
				},
				view : function(data) {
				smart.show({
					title : '查看因公出国事项',
					size : 'full',
					url : "ofcflow/abroad/view",
					params:{id:data.data.id},
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
			},
			add : function() {
				smart.show({
					title : '新增因公出国事项',
					size : 'full',
					url : "ofcflow/abroad/addAbroadPlan",
					size:'full',
					scrollbar : false
				});
			},
			showPlanView : function(data) {
				 window.location.href="ofcflow/abroad/year/list";
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>