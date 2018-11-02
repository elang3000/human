<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="统一用户管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="用户组管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="普通群组管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="普通群组查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="普通群组代码：" autocomplete="off" placeholder="普通群组代码" name="code"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="普通群组名称：" autocomplete="off" placeholder="普通群组名称" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 </smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   				</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="dictTypeList" url="group/common/page" height="full-200" sortField="name" sortType="asc" text="未找到普通群组数据！" page="true" toolbar="toolbarDemo">
							<tr>
								<smart:tableItem field="code" width=".2" sort="true">普通群组代码</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="true">普通群组名称</smart:tableItem>
								<smart:tableItem field="type" width=".2" sort="true" templet="groupTypeTpl">群组类型</smart:tableItem>
								<smart:tableItem field="description" width=".2">描述信息</smart:tableItem>
								<smart:tableItem align="center" width=".2" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="normal" event="manageUser" title="群组人员维护">
									<smart:icon icon="user"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="remove" title="删除">
									<smart:icon icon="remove"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<script type="text/html" id="groupTypeTpl">
  		{{#  if(d.type == 0){ }}
	  		普通群组
		{{#  } else if (d.type == 1) { }}
			单位领导群组
		{{#  } else if (d.type == 2) { }}
			区级领导群组
		{{#  } }}
	</script>
	<script type="text/html" id="toolbarDemo">
  		<div class="layui-btn-container">
    		<button class="layui-btn layui-btn-sm" lay-event="create">新增群组</button>
  		</div>
	</script>
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="dictTypeList">
			create : function() {
				smart.show({
					title : '新增普通用户群组',
					url : 'group/common/create',
					scrollbar : false
				});
			},
			manageUser:function(data) {
				smart.show({
					title : '维护普通用户群组成员',
					url : 'group/common/user/index',
					params : {
						id : data.data.id
					},
					size:'full',
					scrollbar : false
				});
			},
			edit : function(data) {
				smart.show({
					title : '维护普通用户群组信息',
					url : 'group/common/edit',
					params : {
						id : data.data.id
					},
					scrollbar : false
				});
			},
			remove : function(data) {
				smart.confirm({
					title:'删除普通用户群组',
					message:'确认删除普通用户群组？',
					type:'POST',
					url:'group/common/remove',
					params : {id : data.data.id},
					callback : function() {
						var params = smart.json($('#searchForm'));
						table.reload('dictTypeList', {
							where : params
						});
					}
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('dictTypeList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
	<script type="text/javascript" src="ztree/js/jquery-1.4.4.min.js" charset="UTF-8"></script>
	<script type="text/javascript">
		function refresh() {
			$('#searchForm button[data-method="search"]').click();
		}
	</script>
</smart:body>