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
					<smart:breadcrumbNavMenuItem iname="培训学时考核"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="年度培训考核" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="searchForm">
							<smart:gridColumn colPart="4">
								<smart:textInput name="startDate" labelName="开始日期："
									  id="startDate" placeholder="开始日期"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput name="endDate" labelName="结束日期："
									 id="endDate" placeholder="开始日期"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="2" colOffset="1">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 		</smart:button>
									<smart:button size="sm" method="history" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   						</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
						<smart:form id="fileForm" action="ofcflow/recruit/exportPlan">
							<smart:textInput name="id" id="planId" type="hidden"></smart:textInput>
						</smart:form>
						<smart:gridColumn colPart="12" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<shiro:hasPermission name="ADD_TRAIN_YEAR_PLAN_BTN">
								<smart:button size="sm" method="add" title="新增年度培训考核">
									<smart:icon icon="plus">&nbsp;新增年度培训考核</smart:icon>
								</smart:button>
								</shiro:hasPermission>
								<smart:button theme="warm" size="sm" method="back" title="返回单位培训考核列表">
									<smart:icon icon="pencil">&nbsp;返回单位培训考核列表</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/train/year/yearplanlist"
							height="full-190" 
							text="未找到有效数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="false">年度培训考核名称</smart:tableItem>
								<smart:tableItem field="startDate" width=".2" sort="false">开始时间</smart:tableItem>
								<smart:tableItem field="endDate" width=".2" sort="false">结束时间</smart:tableItem>
								<smart:tableItem field="description" width=".1" sort="false">描述</smart:tableItem>
								<smart:tableItem field="state" width=".07" sort="false">状态</smart:tableItem>
								<smart:tableItem align="center"  width=".18" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<shiro:hasPermission name="EDIT_TRAIN_YEAR_PLAN_BTN">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								</shiro:hasPermission>
								<shiro:hasPermission name="REMOVE_TRAIN_YEAR_PLAN_BTN">
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
								</shiro:hasPermission>
								<shiro:hasPermission name="EXPORT_TRAIN_YEAR_PLAN_BY_UNIT_BTN">
								<smart:tableToolBtn event="createPlan" title="生成统计学时表（按单位）">
									<smart:icon icon="book"></smart:icon>
								</smart:tableToolBtn>
								</shiro:hasPermission>
								<shiro:hasPermission name="EXPORT_TRAIN_YEAR_PLAN_BY_PERSON_BTN">
								<smart:tableToolBtn theme="normal" event="createPerson" title="生成人员统计表">
									<smart:icon icon="envelope"></smart:icon>
								</smart:tableToolBtn>
								</shiro:hasPermission>
							</smart:tableToolBar>
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
			delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'确认删除此年度培训考核？',
					url:'ofcflow/train/year/delete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			},
			createPlan : function(data) {
			   $("#planId").val(data.data.id);
			   $("#fileForm").attr("action","ofcflow/train/exportByUnit");
		       smart.confirm({
					title:'提示',
					message:'确认生成此年度计划下的统计学时表（按单位）？',
					url:'ofcflow/train/checkExport',
					params:{id:data.data.id},
					callback:function(){
						$("#fileForm").submit();
					}
				});
			},
			createPerson : function(data) {
			   $("#planId").val(data.data.id);
			   $("#fileForm").attr("action","ofcflow/train/exportByPerson");
			    smart.confirm({
					title:'提示',
					message:'确认生成此年度计划下的人员统计表？',
					url:'ofcflow/train/checkExport',
					params:{id:data.data.id},
					callback:function(){
						$("#fileForm").submit();
					}
				});
			},
			edit : function(data) {
			   window.location.href='ofcflow/train/year/edit?id='+data.data.id;
			},
			reload : function(){
				table.reload('navigationList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('#searchForm'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			add : function() {
			    window.location.href="ofcflow/train/year/edit";
			},
			back : function(data) {
				window.location.href='ofcflow/train/index';
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>