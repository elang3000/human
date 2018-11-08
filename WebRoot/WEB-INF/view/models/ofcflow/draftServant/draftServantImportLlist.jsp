<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请--导入记录" />
<!-- 录用 _导入记录 -->
<script type="text/javascript">
	var tabNum=0;
</script>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员录用" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<%@include file="modual/draftHeadTab.jsp" %>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="3">
								<smart:date labelName="年份" display="block" id="yearTip" name="yearTip" placeholder="选择年份" value="${yearTip }"></smart:date>
							</smart:gridColumn>
							<smart:gridColumn colPart="3">
								<smart:date labelName="导入时间" display="block" id="importDate" name="importDate" placeholder="选择导入时间" value="${importDate }"></smart:date>
							</smart:gridColumn>
							<smart:gridColumn colPart="1" colOffset="1">
								<smart:button theme="normal" size="sm" method="search"
									title="查询">
									<smart:icon icon="search"></smart:icon>
								</smart:button>
							</smart:gridColumn>
								<c:if test="${isBureau }">
							<smart:gridColumn colPart="1">
								<smart:buttonGroup container="true">
								
									<smart:button size="sm" method="draftImport" title="拟录用导入">
										<smart:icon icon="plus">&nbsp;拟录用导入</smart:icon>
									</smart:button>
									
								</smart:buttonGroup>
							</smart:gridColumn>
							</c:if>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/draftServant/importPageList"
							height="full-215" text="未获取到数据！">
							<tr>
								<smart:tableItem field="yearTip" width=".1" sort="true">年度</smart:tableItem>
								<smart:tableItem field="fileName" width=".2" sort="true">文件名称</smart:tableItem>
								<smart:tableItem field="importDate" width=".2" sort="true">导入时间</smart:tableItem>
								<smart:tableItem field="createUserName" width=".1" sort="false">创建者</smart:tableItem>
								<smart:tableItem field="publish" width=".1" sort="false">状态</smart:tableItem>
								<smart:tableItem field="servantType" width=".1" sort="false">人员类型</smart:tableItem>
								<smart:tableItem align="center"  fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>

				

			</smart:cardBody>
		</smart:card>
		<smart:columPanel show="false" id="formhtml">
			
		</smart:columPanel>
	</smart:grid>
	<script type="text/html" id="navListToolBar">
		{{#  if(d.publish === '未发布'){ }}
			<a class="layui-btn layui-btn-xs" lay-event="publish">发布</a>
			<a class="layui-btn layui-btn-xs" lay-event="delRecord">删除</a>
		{{#  } }}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="view">查看</a>
	</script>
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
	<smart:utils />
	<smart:fileUploadUtils uploadURL="test"/>
		<smart:dateRender id="yearTip" type="year" />
		<smart:dateRender id="importDate" />
		<smart:tableScriptAction tableId="navigationList" checkbox="true" sort="true" rowEdit="true">
			publish : function(data) {
				smart.confirm({
					title:'提示',
					message:'请确认，发布后无法取消。',
					url:'ofcflow/draftServant/publish',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			},
			delRecord : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/draftServant/importDel',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			},
			view : function(data) {
				smart.show({
					title : '拟录用名单列表',
					size : 'full',
					url : 'ofcflow/draftServant/beforeList?id='+data.data.id,
					scrollbar : false
				});
			},
			reload : function(){
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
			},
			draftImport : function() {
				smart.show({
					title:'录用导入',
					url:'ofcflow/draftServant/draftservantImport',
					scrollbar:false
				});
			}
		 </smart:buttonScriptAction>
		<smart:dateRender type="year" id="year" />
		$(".layui-tab-title li").removeClass("layui-this");
		$(".layui-tab-title li:eq("+tabNum+")").addClass("layui-this");
	</smart:scriptHead>
</smart:body>
</html>