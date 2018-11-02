<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区人事管理信息系统--机构信息维护事项列表" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项办理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="机构信息维护事项列表" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="3">
						<smart:card>
							<smart:cardHead>
								<blockquote class="layui-elem-quote" style="padding:5px;">
									<div>&nbsp;&nbsp;&nbsp;组织结构树</div>
								</blockquote>
							</smart:cardHead>
							<smart:cardBody>
								<script>
									function loadListById(event, treeId,treeNode) {
 										$('.searchForm button[type="reset"]').click();//先重置表单
										$(".searchForm input[name='parentOrganId']").val(treeNode.id);//赋值当前选中单位id
										$('.searchForm button[search_btn]').click();//查询
									};
								</script>
								<smart:customDynamicTree id="treeArea" url="org/orgRelations"
									style="border:1px solid #e6e6e6;" cutSize="155"
									customEvent="true" funcType="onClick" funcName="loadListById" />
							</smart:cardBody>
						</smart:card>
					</smart:gridColumn>
					<smart:gridColumn colPart="9">
						<smart:gridRow>
							<smart:fieldSet title="查询条件" color="blue">
								<smart:form id="searchForm_list" clazz="searchForm">
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:textInput labelName="单位名称：" autocomplete="off" placeholder="输入关键字名称" name="unitBasicName"></smart:textInput>
											<smart:textInput type="hidden" name="parentOrganId"></smart:textInput>
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
					   						<smart:button size="sm" method="addOrgBtn" title="申请机构新增">
												<smart:icon icon="pencil-square-o">&nbsp;申请机构新增</smart:icon>
											</smart:button>
											<smart:button size="sm" method="updateOrgBtn" title="申请机构新增" theme="danger">
												<smart:icon icon="gears">&nbsp;申请机构调整</smart:icon>
											</smart:button>
											</smart:buttonGroup>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:form>
							</smart:fieldSet>
						</smart:gridRow>
						<smart:gridRow colSpace="5">
							<smart:gridColumn>
								<smart:table id="orgInfoMgrFlowList" url="orgInfoflow/pageList" text="未找到机构维护事项数据！" page="true" height="full-245">
									<tr>
										<smart:tableItem field="optionType" width=".2">事项名称</smart:tableItem>
										<smart:tableItem field="unitBasicName" width=".2">单位名称</smart:tableItem>
										<smart:tableItem field="createTime" width=".2">事项创建时间</smart:tableItem>
										<smart:tableItem field="statusName" width=".2">流程状态</smart:tableItem>
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
		<smart:tableScriptAction tableId="orgInfoMgrFlowList" checkbox="true"
			sort="true" rowEdit="true">    
			edit : function(data) {
				if(data.data.optionTypeCode == 1){
					smart.show({
						title:'申请机构新增',
						size:'full',
						url:'orgInfoflow/applyPage',
						params:{
							id:data.data.id
						},
						scrollbar:false
					});
				}else if(data.data.optionTypeCode == 2){
					smart.show({
						title:'申请机构调整',
						size:'full',
						url:'orgInfoflow/adjustPage',
						params:{
							id:data.data.id
						},
						scrollbar:false
					});
				}
					
			},
			view : function(data) {
				if(data.data.optionTypeCode == 1){
					smart.show({
						title:'新增申请信息查看',
						size:'full',
						url:'orgInfoflow/applyView',
						params:{
							id:data.data.id
						},
						scrollbar:false
					});
				}else if(data.data.optionTypeCode == 2){
					smart.show({
						title:'调整申请信息查看',
						size:'full',
						url:'orgInfoflow/adjustView',
						params:{
							id:data.data.id
						},
						scrollbar:false
					});
				}
				
			},
			delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'确认删除此项？',
					url:'orgInfoflow/deleteApply',
					params:{id:data.data.id},
					callback:orgInfoMgrFlowList_TableInvokeMethod.reload
				});
			},
			reload : function(){
				table.reload('orgInfoMgrFlowList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search_list : function() {
				var params = utils.json($('#searchForm_list'));
				table.reload('orgInfoMgrFlowList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			addOrgBtn : function(data) {
				var parentOrganId = $(".searchForm input[name='parentOrganId']").val();
				if(parentOrganId==''){
					smart.message({
						message : "请在左侧选择新增机构的父类节点",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
				}
				smart.show({
					title:'申请机构新增',
					size:'full',
					url:'orgInfoflow/applyPage',
					params:{
						parentOrganId:parentOrganId
					},
					scrollbar:false
				});
			},
			updateOrgBtn : function(data) {
				var parentOrganId = $(".searchForm input[name='parentOrganId']").val();
				if(parentOrganId==''){
					smart.message({
						message : "请在左侧选择机构节点",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
				}
				smart.show({
					title:'申请机构调整',
					size:'full',
					url:'orgInfoflow/adjustPage',
					params:{
						organId:parentOrganId
					},
					scrollbar:false
				});
			}
		</smart:buttonScriptAction>	
	</smart:scriptHead>
</smart:body>
</html>