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
					<smart:fieldSet title="组织查询" color="blue" style="padding-top:1em;">
						<smart:textInput type="hidden" id="id" name="id" value="${id}"></smart:textInput>
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="组织区域：" id="organTreeId"  display="block" />
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="组织名称：" autocomplete="off" placeholder="请输入组织名称" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 </smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   				</smart:button>
						   				<smart:button size="sm" method="add" title="通知">
											<smart:icon icon="plus">&nbsp;通知</smart:icon>
										</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="organList" url="org/info/page" height="full-140" sortField="name" sortType="asc" text="未找到系统群组数据！" page="true">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="treeTypeName" width=".1">所在区域</smart:tableItem>
								<smart:tableItem field="code" width=".1" sort="true">组织编码</smart:tableItem>
								<smart:tableItem field="name" width=".15" sort="true">组织名称</smart:tableItem>
								<smart:tableItem field="allName" width=".2" sort="true">组织全名</smart:tableItem>
								<smart:tableItem field="organNodeTypeName" width=".1">组织类型</smart:tableItem>
								<smart:tableItem field="deptAddress" width=".1">组织地址</smart:tableItem>
								<smart:tableItem field="principal" width=".1">法人代表</smart:tableItem>
								<smart:tableItem field="description" width=".12">描述信息</smart:tableItem>
							</tr>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,table,linkSelect">
		<smart:initLinkSelect verify="required" id="organTreeId" name="organTreeId" tips="请选择所用所在组织数" url="system/organ/tree/query"/>
		<smart:utils />
		var idStr = '${ids}';
		var ids =new Array();//存放选中复选框的id
		<smart:tableScriptAction tableId="organList" checkbox="true">
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('organList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			add : function() {
					if(ids.length == 0){
			    		smart.message({
							message : "请选择单位！"
							,type : 'W' //S保存  I问号  W感叹号 E错误
						});
			    	}else{ 
			    		var params = {id:$('#id').val(),organIds:ids.join(",")};
						var url = '${url}';
						$.post(url,params,function(result){
							if(result.success){//保存成功
								layer.alert(
				                result.message, 
				                {icon: 1,closeBtn: 1 },
				                function () {
									parent.layui.table.reload('navigationList');
				                	var index=parent.layer.getFrameIndex(window.name);
									parent.layer.close(index);
				                });
							}else{
								layer.alert(
				                result.message, 
				                {icon: 0,closeBtn:0 });
							}
						});
						return false;
					}
				},
		</smart:buttonScriptAction>
		Array.prototype.remove = function(val) { 
			var index = this.indexOf(val); 
				if (index > -1) { 
					this.splice(index, 1); 
				}
		};
        table.on('checkbox(organList)', function (obj) {
        	var cArr = table.cache.organList;//获取当前列表所有数据
        	cArr.forEach(function(value,i){
        		var c = cArr[i];
        		if(c.LAY_CHECKED){//选中状态
        			if(ids.indexOf(c.id)==-1){//如果不存在数组中，则添加
				     	ids.push(c.id);//id
                 	}
        		}else{//未选中状态，从数组中删除
		       		ids.remove(c.id);
        		}
	     	});
        });
	</smart:scriptHead>
</smart:body>