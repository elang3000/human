<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--文件下载" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="文件下载" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="业务类型" isAddDefaltOption="true" name="busType" 
								display="block" data="${busTypeList}" isSearch="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
									<smart:textInput labelName="材料名：" autocomplete="off"
										placeholder="材料名" name="name">
									</smart:textInput>
								</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 			</smart:button>
									<smart:button size="sm" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   							</smart:button>
		   							<smart:button size="sm"  theme="normal" method="download" title="批量下载">
										<smart:icon icon="download">&nbsp;批量下载</smart:icon>
									</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/material/pageList" 
							height="full-140" text="未找到用户数据！" page="true" doneCallBack="fixedCol">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="flowNumber" width=".1" sort="false">业务编号</smart:tableItem>
								<smart:tableItem field="busName" width=".2" sort="false">业务类型</smart:tableItem>
								<smart:tableItem field="name" width=".55" sort="false">材料名</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="down" title="下载">
									<smart:icon icon="download"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		var ids =new Array();//存放选中复选框的id
		var flowNumbers =new Array();//存放选中复选框的flowNumber
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				down : function(data) {
					var $eleForm = $("<form method='post'></form>");
	 
		            $eleForm.attr("action","ofcflow/material/downLoad?id="+data.data.busId+"&name="+data.data.flowNumber);
		 
		            $(document.body).append($eleForm);
		 
		            //提交表单，实现下载
		            $eleForm.submit();
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
			download : function() {
				if(ids.length == 0){
			    		smart.message({
							message : "请选择下载业务！"
							,type : 'W' //S保存  I问号  W感叹号 E错误
						});
			    	}else{ 
			    		var $eleForm = $("<form method='post'></form>");
			    		var name = '（批量下载）编号'+flowNumbers[0]+'等业务';
	 					var url = 'ofcflow/material/downLoad?id='+ids.join(',')+'&name='+name;
			            $eleForm.attr("action",url);
			            $(document.body).append($eleForm);
			            //提交表单，实现下载
			            $eleForm.submit();
			            //下载完成 清空数组
			            ids.splice(0,ids.length);
			            flowNumbers.splice(0,flowNumbers.length);
			            table.reload('navigationList', {
							page : {
								curr : 1
							}
						});
					}
			}
		 </smart:buttonScriptAction>
		 //复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
		Array.prototype.remove = function(val) { 
			var index = this.indexOf(val); 
				if (index > -1) { 
					this.splice(index, 1); 
				}
		};
        table.on('checkbox(navigationList)', function (obj) {
        	var cArr = table.cache.navigationList;//获取当前列表所有数据
        	for(var i in cArr){
        		var c = cArr[i];
        		if(c.LAY_CHECKED){//选中状态
        			if(ids.indexOf(c.busId)==-1){//如果不存在数组中，则添加
				     	ids.push(c.busId);//id
				     	flowNumbers.push(c.flowNumber);
                 	}
        		}else{//未选中状态，从数组中删除
		       		ids.remove(c.busId);
		       		flowNumbers.remove(c.flowNumber);
        		}
	     	}
        });
	</smart:scriptHead>
</smart:body>
</html>