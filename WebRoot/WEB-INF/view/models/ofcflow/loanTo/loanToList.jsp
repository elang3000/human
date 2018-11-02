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
					<smart:breadcrumbNavMenuItem iname="借调管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="姓名" display="block" name="name" placeholder="姓名"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="去向单位" display="block" name="targetOrgan" placeholder="去向单位"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3" colOffset="1">
									<smart:button theme="normal" size="sm" method="search"
										title="查询">
										<smart:icon icon="search"></smart:icon>
									</smart:button>
									<shiro:hasPermission name="ADD_LOANTO_BTN">
										<smart:button size="sm" method="add" title="新增">
											<smart:icon icon="plus">&nbsp;新增</smart:icon>
										</smart:button>
									</shiro:hasPermission>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/loanto/infoList"
							height="full-150" text="未获取到数据！">
							<tr>
								<smart:tableItem field="name" width=".2" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".2" sort="false">身份证号</smart:tableItem>
								<smart:tableItem field="sourceOrgan" width=".2" sort="false">借出单位</smart:tableItem>
								<smart:tableItem field="targetOrgan" width=".2" sort="false">去向单位</smart:tableItem>
								<smart:tableItem align="center" fixed="right" width=".1" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
										{{#  if(d.status=="1"){ }}
										<shiro:hasPermission name="EDIT_LOANTO_BTN">
											<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
												<i class="fa fa-edit"></i>
											</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="REMOVE_LOANTO_BTN">
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
					title : '借调信息',
					size : 'full',
					url : "ofcflow/loanto/edit?id="+data.data.id,
					scrollbar : false
				});
			}
			,view : function(data) {
				smart.show({
					title : '借调信息',
					size : 'full',
					url : "ofcflow/loanto/toView?id="+data.data.id,
					scrollbar : false
				});
			}
			,delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/loanto/delete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
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
			}
			,add : function() {
				smart.show({
					title : '借调信息',
					size : 'full',
					url : "ofcflow/loanto/edit",
					scrollbar : false,
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>