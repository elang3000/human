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
					<smart:breadcrumbNavMenuItem iname="应用系统管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="基础数据管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="数据字典管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="数据字典类型查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="字典类型代码：" autocomplete="off" placeholder="字典类型代码" name="code"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="字典类型名称：" autocomplete="off" placeholder="字典类型名称" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 </smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   				</smart:button>
						   				<smart:button size="sm" method="new" title="新增数据字典类型" theme="normal">
											<smart:icon icon="plus"></smart:icon>&nbsp;新增
									   	</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="dictTypeList" url="system/dict/type/page" height="full-200" sortField="name" sortType="asc" text="未找到应用系统数据！" page="true">
							<tr>
								<smart:tableItem field="code" width=".2" sort="true">字典类型代码</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="true">字典类型名称</smart:tableItem>
								<smart:tableItem field="storageType" width=".1" templet="storeTpl">存储方式</smart:tableItem>
								<smart:tableItem field="level" width=".1">层级限制</smart:tableItem>
								<smart:tableItem field="description" width=".2">描述信息</smart:tableItem>
								<smart:tableItem align="center" width=".16" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="normal" event="info" title="数据字典维护">
									<smart:icon icon="book"></smart:icon>
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
	<script type="text/html" id="storeTpl">
  		{{#  if(d.storageType === 1){ }}
			外部表
  		{{#  } else { }}
    		内置表
  		{{#  } }}
	</script>
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="dictTypeList">
			edit : function(data) {
				smart.show({
					title : '维护数据字典类型信息',
					url : 'system/dict/type/edit',
					params : {
						id : data.data.id
					},
					size : 'lg',
					scrollbar : false
				});
			},
			info:function(data) {
				smart.show({
					title : '维护数据字典',
					url : 'system/dict/info/index',
					params : {
						codeTypeId : data.data.id
					},
					size:'full',
					scrollbar : false
				});
			},
			remove : function(data) {
				smart.confirm({
					title:'删除数据字典',
					message:'确认删除数据字典？',
					type:'POST',
					url:'system/dict/type/remove/save',
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
			new : function() {
				smart.show({
					title : '新增应用系统',
					url : 'system/dict/type/create',
					size :'lg',
					scrollbar : false
				});
			},
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