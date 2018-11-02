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
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="普通群组人员查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="用户姓名：" autocomplete="off" placeholder="用户姓名" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="移动电话：" autocomplete="off" placeholder="用户移动电话" name="mobile1"></smart:textInput>
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
						<smart:table id="dictTypeList" url="group/common/user/page?groupId=${group.id}" height="full-200" sortField="name" sortType="asc" text="未找到应用系统数据！" page="true" toolbar="toolbarDemo">
							<tr>
								<smart:tableItem field="loginName" width=".1" sort="true">登录名</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sexStr" width=".05" sort="true">性别</smart:tableItem>
								<smart:tableItem field="statusName" width=".1">账户状态</smart:tableItem>
								<smart:tableItem field="accountTypeName" width=".1">账户类型</smart:tableItem>
								<smart:tableItem field="departmentName" width=".15">所属单位</smart:tableItem>
								<smart:tableItem field="email" width=".1">电子邮箱</smart:tableItem>
								<smart:tableItem field="address" width=".1">联系地址</smart:tableItem>
								<smart:tableItem field="mobile1" width=".1">移动电话</smart:tableItem>
								<smart:tableItem align="center" width=".1" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
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
	<script type="text/html" id="toolbarDemo">
  		<div class="layui-btn-container">
    		<button class="layui-btn layui-btn-sm" lay-event="addUser">
			<smart:icon icon="plus">新增人员</smart:icon></button>
  		</div>
	</script>
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="dictTypeList">
			addUser:function(data) {
				smart.show({
					title : '新增普通用户群组成员',
					url : 'group/common/user/add/index',
					params : {
						id : '${group.id}'
					},
					size:'lg',
					scrollbar : false
				});
			},
			remove : function(data) {
				smart.confirm({
					title:'删除用户',
					message:'确认从${group.name}群组中删除用户？',
					type:'POST',
					url:'group/common/user/remove',
					params : {
						groupId : '${group.id}',
						userId : data.data.id
					},
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