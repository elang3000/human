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
		<div class="layui-card">
			<div class="layui-card-body">
				<smart:fieldSet title="新增系统报表参数" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput type="hidden" name="reportId" value="${reportId}"></smart:textInput>
								<smart:textInput labelName="参数代码：" autocomplete="off" placeholder="请输入系统报表参数代码" verify="required" name="code" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="参数名称：" autocomplete="off" placeholder="请输入系统报表参数名称" verify="required" name="name" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="参数类型：" autocomplete="off" placeholder="请输入系统报表参数类型" verify="required" name="type" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="默认值：" autocomplete="off" placeholder="请输入系统报表模板路径" name="defaultValue">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:buttonGroup container="true">
									<button class="layui-btn layui-btn-sm" type="button" lay-submit lay-filter="smart-submit" title="确认">
										<i class="fa fa-check"></i>&nbsp;添加
									</button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>
				</smart:fieldSet>
				<smart:gridRow>
					<smart:gridColumn>
						<smart:table id="systemReportList" url="system/report/param/page?reportId=${reportId}" height="full-250" sortField="name" sortType="asc" text="未找到系统报表数据！" page="true">
							<tr>
								<smart:tableItem field="code" width=".2" sort="true">参数代码</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="true">参数名称</smart:tableItem>
								<smart:tableItem field="type" width=".2" sort="false">参数类型</smart:tableItem>
								<smart:tableItem field="defaultValue" width=".2">默认值</smart:tableItem>
								<smart:tableItem align="center" width=".2" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="danger" event="remove" title="删除">
									<smart:icon icon="remove"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</div>
		</div>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="systemReportList">
			remove:function(res) {
				smart.confirm({
					title:'删除系统报表参数',
					message:'确认删除系统报表参数信息？',
					type:'POST',
					url:'system/report/param/remove/save',
					params : {
						reportId : '${reportId}',
						id : res.data.id
					},
					callback : function() {
						table.reload('systemReportList', {
							page : {
								curr : 1
							}
						});
					}
				});
			}
		</smart:tableScriptAction>
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			var mask = layer.load(1, {
			  	shade: [0.5,'#fff']
			});
			$.post('system/report/param/save',
				obj.field,
				function(result) {	
					layer.close(mask);
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						form.val('saveForm', {
							code : '',
							name : '',
							type: '',
							defaultValue : ''
						});
						table.reload('systemReportList', {
							where : {
								reportId : '${reportId}'
							},
							page : {
								curr : 1
							}
						});
					} else {							
						layer.msg(result.message, {
							time:1000
							,icon:2
							,anim:6
						});
					}
				},'json');
		});
	</smart:scriptHead>
</smart:body>
</html>