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
			<smart:fieldSet title="组织节点查询" color="blue" style="padding-top:1em;">
					<smart:form id="searchForm">
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:linkSelect labelName="组织树：" id="organTreeId"  display="block" />
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="组织代码：" autocomplete="off" placeholder="请输入组织代码" name="code"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="组织名称：" autocomplete="off" placeholder="请输入组织名称" name="name"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:linkSelect labelName="组织类型：" id="organNodeTypeTag" display="block"/>
							</smart:gridColumn>
							<smart:gridColumn colPart="4" colOffset="4">
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
					<smart:table id="organNodeList" url="security/user/assist/node/page?securityUserId=${securityUserId}" height="full-200" sortField="name" sortType="asc" text="未找到组织数据！" page="true" toolbar="toolbar">
						<tr>
							<smart:tableItem isCheckbox="true">全选</smart:tableItem>
							<smart:tableItem field="treeTypeName" width=".1">所在区域</smart:tableItem>
							<smart:tableItem field="code" width=".15" sort="true">组织代码</smart:tableItem>
							<smart:tableItem field="name" width=".1" sort="true">组织名称</smart:tableItem>
							<smart:tableItem field="allName" width=".2" sort="true">组织全名</smart:tableItem>
							<smart:tableItem field="organNodeTypeName" width=".1">组织类型</smart:tableItem>
							<smart:tableItem field="deptAddress" width=".1">组织地址</smart:tableItem>
							<smart:tableItem field="principal" width=".1">法人代表</smart:tableItem>
							<smart:tableItem field="description" width=".125">描述信息</smart:tableItem>
						</tr>
					</smart:table>
				</smart:gridColumn>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<script type="text/html" id="toolbar">
  		<div class="layui-btn-container">
    		<button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
			<button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="cancel">取消</button>
  		</div>
	</script>
	<smart:scriptHead models="form,layer,element,table,linkSelect">
		var cancelTableItem = [],addTableItem=[];
		<smart:initLinkSelect id="organNodeTypeTag" name="organNodeTypeId" tips="请选择组织类型" verify="required"  url="system/organ/node/type/query"/>	
		<smart:initLinkSelect verify="required" id="organTreeId" name="organTreeId" tips="请选择所在组织树" url="system/organ/tree/query"/>
		<smart:tableScriptAction tableId="organNodeList" checkbox="true">
			add:function() {
				var checkStatus = table.checkStatus('organNodeList'),data = checkStatus.data,selectData=[];
				if (addTableItem.length > 0) {
					smart.confirm({
						title:'添加用户',
						message:'确认添加'+ addTableItem.length + '个组织节点为用户的主管单位？',
						headers : {
					        'Accept': 'application/json',
					        'Content-Type': 'application/json;charset=utf-8'
					    },
						url:'security/user/assist/add/${securityUserId}',
						params : JSON.stringify(addTableItem.distinct()),
						callback : buttonInvokeMethod.search
					});
				}
			},
			cancel:function() {			
				if (cancelTableItem.length > 0) {
					smart.confirm({
						title:'添加用户',
						message:'确认取消用用户的'+ cancelTableItem.length +'个主管单位？',
						headers : {
					        'Accept': 'application/json',
					        'Content-Type': 'application/json;charset=utf-8'
					    },
						url:'security/user/assist/cancel/${securityUserId}',
						params : JSON.stringify(cancelTableItem.distinct()),
						callback : buttonInvokeMethod.search
					});
				}
			},
			checkbox:function(object) {
				if (object.checked) {
					addTableItem.push(object.data.id);
				} else {
					cancelTableItem.push(object.data.id);
				}
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				cancelTableItem = [];
				addTableItem = [];
				var params = smart.json($('#searchForm'));
				table.reload('organNodeList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>