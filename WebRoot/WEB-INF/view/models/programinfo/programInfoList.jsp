<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--预警预告" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="预警预告"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="方案管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="方案管理"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="programinfo/add" show="false" eId="" itemName="新增方案"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="searchForm">
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="方案名称" display="block" name="name" placeholder="方案名称"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect data="[{'key':'1','value':'预警方案'},{'key':'2','value':'预告方案'}]"
								name="programType" labelName="方案类型：" display="block" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="2" colOffset="2">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 		</smart:button>
									<smart:button size="sm" method="history" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   						</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="programinfo/list" height="full-200" text="未找到有效数据！" doneCallBack="fixedCol">
							<tr>
								<smart:tableItem field="name" width=".2" sort="true">方案名称</smart:tableItem>
								<smart:tableItem field="programType" width=".2" sort="true">方案类型</smart:tableItem>
								<smart:tableItem field="createTime" width=".1" sort="true">创建日期</smart:tableItem>
								<smart:tableItem field="programTime" width=".2">执行时间</smart:tableItem>
								<smart:tableItem field="describe" width=".2">方案描述</smart:tableItem>
								<smart:tableItem field="newsContent" width=".3">通知内容</smart:tableItem>
								<smart:tableItem align="center"  fixed="right"
									unresize="true" toolbar="navListToolBar" width=".15">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
									{{#  if(d.programState=="2"){ }}
										<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="start"  title="启动">
											<i class="fa fa-arrow-up"></i>
										</a>
										<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="setTime"  title="设置时间">
											<i class="fa fa-edit"></i>
										</a>
										<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete"  title="删除">
											<i class="fa fa-trash"></i>
										</a>
 									{{#  } }}
									{{#  if(d.programState=="1"){ }}
										<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="close"  title="关闭">
											<i class="fa fa-arrow-down"></i>
										</a>
 									{{#  } }}
							</script>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<script type="text/javascript">
		var infoId;
		function getTimeValues(name,time){
			layui.use(['layer','table','smart'],function(){
				var $ = layui.jquery,layer=layui.layer,table=layui.table,smart=layui.smart;
				smart = smart();
				var requestConfig = {};
				requestConfig.url = "programinfo/save";
				requestConfig.data = {programTime:time,programTimeDescribe:name,id:infoId};
				requestConfig.success = function(result){
					if (result.success) {
						table.reload('navigationList');
						layer.closeAll();
					}
					layer.msg(result.message);
				}
				smart.request(requestConfig);
			});
		}
	</script>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" >
			reload : function(){
				table.reload('navigationList');
			}
			,start : function(data) {
				smart.confirm({
					title:'启动方案',
					message:'您确定要启动该'+data.data.programType+'？',
					url:'programinfo/start',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			}
			,close : function(data) {
				smart.confirm({
					title:'关闭方案',
					message:'您确定要关闭该'+data.data.programType+'？',
					url:'programinfo/close',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			}
			,delete : function(data) {
				smart.confirm({
					title:'删除提示',
					message:'您确定要删除该'+data.data.programType+'？',
					url:'programinfo/delete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			}
			,setTime : function(data) {
				infoId = data.data.id;
				smart.show({
					title : '修改时间',
					size : 'lg',
					url : "programinfo/setTime",
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('#searchForm'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>