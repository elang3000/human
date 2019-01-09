<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区人事管理信息系统--重大事项申请列表" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="重大事项申请" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="12">
						<smart:gridRow>
							<smart:fieldSet title="查询条件" color="blue">
								<smart:form id="searchForm_list" clazz="searchForm">
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:singleSelect labelName="事项类型：" display="block" name="eventType.id" url="dictquery/sub/code/HUMAN_IMPORTANT_EVENT_TYPE" isAddDefaltOption="true">
											</smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="12">
											<smart:buttonGroup container="true" align="right">
												<smart:button size="sm" method="search_list" title="查询"
													theme="primary" other="search_btn">
													<smart:icon icon="search"></smart:icon>&nbsp;查询
					  				 		</smart:button>
												<smart:button size="sm" method="history" title="重置"
													theme="primary" type="reset">
													<smart:icon icon="history"></smart:icon>&nbsp;重置
					   						</smart:button>
											<smart:button size="sm" method="add" title="新增">
												<smart:icon icon="plus">&nbsp;新增</smart:icon>
											</smart:button>
											</smart:buttonGroup>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:form>
							</smart:fieldSet>
						</smart:gridRow>
						<smart:gridRow colSpace="5">
							<smart:gridColumn>
								<smart:table id="importantMgrList" url="importantEventApply/pageList" text="未找到重大事项申请数据！" page="true" height="full-245">
									<tr>
										<smart:tableItem field="eventTitle" width=".2">事项标题</smart:tableItem>
										<smart:tableItem field="eventType" width=".125">事项类型</smart:tableItem>
										<smart:tableItem field="createTime" width=".125">创建时间</smart:tableItem>
										<smart:tableItem field="applyOrganName" width=".2">申请单位</smart:tableItem>
										<smart:tableItem field="statusName" width=".15">流程状态</smart:tableItem>
										<smart:tableItem align="center" width=".2" fixed="right"
											unresize="true" toolbar="navListToolBar_list">操作</smart:tableItem>
									</tr>
									
										<script type="text/html" id="navListToolBar_list">
											{{#  if(d.status=="0"){ }}
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
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>

	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="importantMgrList" checkbox="true"
			sort="true" rowEdit="true">    
			edit : function(data) {
				smart.show({
					title:'重大事项申请修改',
					size:'full',
					url:'importantEventApply/edit',
					params:{
						id:data.data.id
					},
					scrollbar:false
				});
					
			},
			view : function(data) {
				smart.show({
					title:'重大事项申请查看',
					size:'full',
					url:'importantEventApply/view',
					params:{
						id:data.data.id
					},
					scrollbar:false
				});
				
			},
			delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'确认删除此项？',
					url:'importantEventApply/delete',
					params:{id:data.data.id},
					callback:importantMgrList_TableInvokeMethod.reload
				});
			},
			reload : function(){
				table.reload('importantMgrList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search_list : function() {
				var params = utils.json($('#searchForm_list'));
				table.reload('importantMgrList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			add : function(data) {
				smart.show({
					title:'重大事项申请新增',
					size:'full',
					url:'importantEventApply/edit',
					params:{},
					scrollbar:false
				});			
			}
		</smart:buttonScriptAction>	
	</smart:scriptHead>
</smart:body>
</html>