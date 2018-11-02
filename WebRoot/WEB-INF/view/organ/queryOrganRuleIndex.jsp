<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="组织类型规则查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<blockquote class="layui-elem-quote" style="padding:5px;">
										<div>组织类型： ${type.name}</div>
									</blockquote>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
					  				 	</smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
					   					</smart:button>
					   					<smart:button size="sm" method="new" title="新增组织类型规则" theme="normal">
											<smart:icon icon="plus"></smart:icon>&nbsp;新增组织类型规则
									   	</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="navigationList" url="system/organ/tree/rule/query/page?id=${type.id}" height="full-200" sortField="name" sortType="asc" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="code" width=".2" sort="true">组织类型规则编码</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="true">组织类型规则名称</smart:tableItem>
								<smart:tableItem field="superiorNodeTypeName" width=".15">上级节点类型</smart:tableItem>
								<smart:tableItem field="subordinateNodeTypeName" width=".15">下级节点类型</smart:tableItem>
								<smart:tableItem field="description" width=".15">描述信息</smart:tableItem>
								<smart:tableItem align="center" width=".15" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
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
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="navigationList">
			edit : function(data) {
				smart.show({
					title : '修改组织类型规则信息',
					url : 'system/organ/tree/rule/edit',
					params : {
						id : data.data.id,
						organTreeTypeId : '${type.id}'
					},
					size : 'lg',
					scrollbar : false
				});
			},
			remove : function(data) {
				smart.confirm({
					title:'删除组织类型规则',
					message:'确认删除组织类型规则？',
					type:'POST',
					url:'system/organ/tree/rule/remove/save',
					params : {
						id : data.data.id,
						organTreeTypeId : '${type.id}'
					},
					callback : function() {
						var params = smart.json($('#searchForm'));
						table.reload('navigationList', {
							where : params
						});
					}
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			new : function() {
				smart.show({
					title : '新增组织类型规则',
					size : 'lg',
					url : 'system/organ/tree/rule/create?organTreeTypeId=${type.id}',
					scrollbar : false
				});
			},
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('navigationList', {
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