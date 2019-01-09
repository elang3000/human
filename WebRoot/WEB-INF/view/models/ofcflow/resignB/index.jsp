<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--辞职管理" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="辞职管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="辞职申请"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/resignB/resignPeople/index" show="false" eId="" itemName="辞职人员"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/resignB/flow" show="false" eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:gridRow>
					<smart:fieldSet title="查询条件" style="margin-top: 5px;" color="blue">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="批次名：" autocomplete="off"
										placeholder="批次名" name="resignName">
									</smart:textInput>
								</smart:gridColumn>
								
								<smart:gridColumn colPart="5">
									<div class="layui-form-item">
										<div class="layui-inline">
											<label class="layui-form-label" title="辞职人数">辞职人数：</label>
											<div class="layui-input-inline" style="width: 100px;">
												<input onblur="numberValidate(this,true);" type="text"
													autocomplete="off" class="layui-input" max="1000" min="0"
													step="1" name="numLow" title="辞职人数起"
													placeholder="辞职人数起">
											</div>
											<div class="layui-form-mid">-</div>
											<div class="layui-input-inline" style="width: 100px;">
												<input onblur="numberValidate(this,true);" type="text"
													autocomplete="off" class="layui-input" max="1000" min="0"
													step="1" name="numHigh" title="辞职人数止"
													placeholder="辞职人数止">
											</div>
										</div>
									</div>
								</smart:gridColumn>

								<smart:gridColumn colPart="3">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询"
											theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
				  				 </smart:button>
										<smart:button size="sm" method="history" title="重置"
											theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
				   				</smart:button>
				   						<shiro:hasPermission name="ADD_RESIGN_SERVANT_BTN">
										<smart:button size="sm" method="add" title="新增辞职">
											<smart:icon icon="plus"></smart:icon>&nbsp;新增辞职
										</smart:button>
										</shiro:hasPermission>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/resignB/resignList" height="full-190" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="resignName" width=".3">批次名</smart:tableItem>
								<smart:tableItem field="resignNumber" width=".15">批次人数</smart:tableItem>
								<%-- <smart:tableItem field="createUser" width=".2">创建人</smart:tableItem> --%>
								<smart:tableItem field="createTime" width=".2">创建时间</smart:tableItem>
								<smart:tableItem field="status" width=".2">状态</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true"
									toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
										{{#  if(d.statusSign==1){ }}
										<shiro:hasPermission name="EDIT_RESIGN_SERVANT_BTN">
										<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
											<i class="fa fa-edit"></i>
										</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="REMOVE_RESIGN_SERVANT_BTN">
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
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />

		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title : '编辑辞职申请',
						size : 'full',
						url : 'ofcflow/resignB/editResign?id='+data.data.id,
						scrollbar : false,
					});
				},
				remove : function(data) {
					smart.confirm({
						title:'删除批量辞职信息',
						message:'确认删除批量辞职信息？',
						type:'POST',
						url:'ofcflow/resignB/deleteResign',
						params : {id : data.data.id},
						callback : function() {
							layui.table.reload('navigationList');
						}
					});
				},
				view : function(data) {
					smart.show({
						title : '批量辞职信息',
						size : 'full',
						url : 'ofcflow/resignB/resignView?id='+data.data.id,
						scrollbar : false,
					});
				}
			</smart:tableScriptAction>
			var buttonInvokeMethod = {
			search : function() {
				var params = utils.json($('#searchForm'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			add:function(){
				smart.show({
					title : '新增辞职申请',
					size : 'full',
					url : 'ofcflow/resignB/editResign',
					scrollbar : false
				});
			}
		};

		$('#searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
	</smart:scriptHead>

</smart:body>
</html>