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
					<smart:breadcrumbNavMenuItem iname="同类别转任管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem turnurl="ofcflow/zrtlbIntoB/index" show="false"
								eId="" itemName="转入情况列表"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="转出情况列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/exchangeB/flow" show="false"
								eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="转出人员单位：" name="sourceOrgan.id"
								isSearch="true" display="block" isAddDefaltOption="true"
								url="system/organ/node/queryAll?organTreeId=394e21fa-1eb6-42ee-ba32-50655fa16517&organNodeType=d_class"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询"
											theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
			  				 			</smart:button>
										<smart:button size="sm" title="重置" theme="primary"
											type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
			   							</smart:button>
										<shiro:hasPermission name="ADD_EXCHANGE_OUT_BTN">
											<smart:button size="sm" method="add" title="新增">
												<smart:icon icon="plus">&nbsp;新增</smart:icon>
											</smart:button>
										</shiro:hasPermission>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/exchangeOutB/outList"
							height="full-210" text="未获取到数据！">
							<tr>
								<smart:tableItem field="sourceOrgan" width=".4">转出单位</smart:tableItem>
								<smart:tableItem field="createTime" width=".2">创建时间</smart:tableItem>
								<smart:tableItem field="statusName" width=".2" sort="true">状态</smart:tableItem>
								<smart:tableItem align="center" fixed="right" width=".2"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
										{{#  if(d.status=="0"){ }}
										<shiro:hasPermission name="EDIT_ZHUANREN_OUT_BTN">
											<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
												<i class="fa fa-edit"></i>
											</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="REMOVE_ZHUANREN_OUT_BTN">
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
			edit : function(data) {
				smart.show({
					title : '转出管理',
					size : 'full',
					url : "ofcflow/exchangeOutB/servantList?id="+data.data.id,
					scrollbar : false
				});
			}
			,delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/exchangeOutB/delete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			}
			,view : function(data) {
				smart.show({
					title : '转出管理',
					size : 'full',
					url : "ofcflow/exchangeOutB/view?id="+data.data.id,
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
					title : '转出管理',
					size : 'full',
					url : "ofcflow/exchangeOutB/selectOrg",
					scrollbar : false
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>