<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区人事管理信息系统--机构人员查看" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="查询条件" color="blue">
						<smart:form id="searchForm_ofcList" clazz="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="姓名：" autocomplete="off" placeholder="姓名" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="身份证号：" autocomplete="off" placeholder="身份证号" name="cardNo"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
									</smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:buttonGroup container="true" align="right">
										<smart:button size="sm" method="search_ofcList" title="查询"
											theme="primary" other="search_btn">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
			  				 			</smart:button>
										<smart:button size="sm" method="history" title="重置"
											theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
			   							</smart:button>
			   							<smart:button size="sm" method="add" title="添加">
												<smart:icon icon="plus">&nbsp;添加</smart:icon>
										</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<c:if test="${personType eq '1' }">
							<smart:table id="navigationList_ofcList" url="ofc/pageListWithCheckBox?departId=${createOrganNodeId}&ids=${ids}&busClass=${busClass}&busCol=${busCol}&busId=${busId}&type=${type}&busParentClass=${busParentClass}" 
							text="未找到用户数据！">
								<tr>
									<smart:tableItem isCheckbox="true">全选</smart:tableItem>
									<smart:tableItem field="name" width=".15" sort="true">姓名</smart:tableItem>
									<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
									<smart:tableItem field="cardNo" width=".2" sort="true">身份证号</smart:tableItem>
									<smart:tableItem field="departName" width=".1" sort="false">单位部门</smart:tableItem>
									<smart:tableItem field="postName" width=".1" sort="false">职务名称</smart:tableItem>
									<smart:tableItem field="postAttributeName" width=".1" sort="false">职务属性</smart:tableItem>
									<smart:tableItem field="jobLevel" width=".11" sort="false">职级名称</smart:tableItem>
									<smart:tableItem field="isOnHold" width=".1" sort="false">状态</smart:tableItem>
								</tr>
							</smart:table>
						</c:if>
						<c:if test="${personType eq '2' }">
							<smart:table id="navigationList_ofcList" url="publicInstitution/pageListWithCheckBox?departId=${createOrganNodeId}&ids=${ids}&busClass=${busClass}&busCol=${busCol}&busId=${busId}&type=${type}&busParentClass=${busParentClass}" 
							text="未找到用户数据！">
								<tr>
									<smart:tableItem isCheckbox="true">全选</smart:tableItem>
									<smart:tableItem field="name" width="110" sort="true">姓名</smart:tableItem>
									<smart:tableItem field="sex" width="80" sort="true">性别</smart:tableItem>
									<smart:tableItem field="cardNo" width="240" sort="true">身份证号</smart:tableItem>
									<smart:tableItem field="departName" width="190" sort="false">单位部门</smart:tableItem>
									<smart:tableItem field="postName" width="120" sort="false">职务名称</smart:tableItem>
									<smart:tableItem field="isOnHold" width="120" sort="false">状态</smart:tableItem>
								</tr>
							</smart:table>
						</c:if>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		var idStr = '${ids}';
		var nameStr = '${names}';
		var ids =new Array();//存放选中复选框的id
		var names =new Array();//存放选中复选框的姓名
		if(idStr&&idStr!=""){
			ids = idStr.split(",");
		}
		if(nameStr&&nameStr!=""){
			names = nameStr.split(",");
		}
		<smart:tableScriptAction tableId="navigationList_ofcList" checkbox="true">
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search_ofcList : function() {
				var params = utils.json($('#searchForm_ofcList'));
				params.ids = ids.join(',');
				table.reload('navigationList_ofcList', {
					where : params,
					<c:if test="${personType eq '1' }">
					url	: 'ofc/pageListWithCheckBox?departId=${createOrganNodeId}&busClass=${busClass}&busCol=${busCol}&busId=${busId}&type=${type}&busParentClass=${busParentClass}',
					</c:if>
					<c:if test="${personType eq '2' }">
					url	: 'publicInstitution/pageListWithCheckBox?departId=${createOrganNodeId}&busClass=${busClass}&busCol=${busCol}&busId=${busId}&type=${type}&busParentClass=${busParentClass}',
					</c:if>
					page : {
						curr : 1
					}
				});
			},
			add : function() {
					if(ids.length == 0){
			    		smart.message({
							message : "请选择人员！"
							,type : 'W' //S保存  I问号  W感叹号 E错误
						});
			    	}else{ 
			    		var index = parent.layer.getFrameIndex(window.name);
			    		var servantIds = ids.join(",");
			    		parent.layui.$('#servantIds').val(servantIds);
			    		parent.layui.$('#servantNames').val(names.join(","));
			    		parent.layui.table.reload('navigationList', {
							where : {servantIds:servantIds,status:'0'},
						});
						parent.layer.close(index);
					}
				},
		</smart:buttonScriptAction>	
		//复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
		Array.prototype.remove = function(val) { 
			var index = this.indexOf(val); 
				if (index > -1) { 
					this.splice(index, 1); 
				}
		};
        table.on('checkbox(navigationList_ofcList)', function (obj) {
        	var cArr = table.cache.navigationList_ofcList;//获取当前列表所有数据
        	cArr.forEach(function(value,i){
        		var c = cArr[i];
        		if(c.LAY_CHECKED){//选中状态
        			if(ids.indexOf(c.id)==-1){//如果不存在数组中，则添加
				     	ids.push(c.id);//id
				     	names.push(c.name);
                 	}
        		}else{//未选中状态，从数组中删除
		       		ids.remove(c.id);
		       		names.remove(c.name);
        		}
	     	});
        });
        table.reload('navigationList_ofcList', {//列表重新加载，传入动态的ids值，只能通过方法返回的方式传递
			<c:if test="${personType eq '1' }">
			url	: 'ofc/pageListWithCheckBox?departId=${createOrganNodeId}&busClass=${busClass}&busCol=${busCol}&busId=${busId}&type=${type}&busParentClass=${busParentClass}',
			</c:if>
			<c:if test="${personType eq '2' }">
			url	: 'publicInstitution/pageListWithCheckBox?departId=${createOrganNodeId}&busClass=${busClass}&busCol=${busCol}&busId=${busId}&type=${type}&busParentClass=${busParentClass}',
			</c:if>
		  	where: {ids:function (){return ids.join(',')}}
		});
	</smart:scriptHead>
</smart:body>
</html>