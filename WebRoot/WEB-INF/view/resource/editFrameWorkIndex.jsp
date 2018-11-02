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
				<smart:fieldSet title="维护系统功能信息" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="id" value="${resource.id}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<blockquote class="layui-elem-quote" style="padding:5px;">
									<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;父类功能名称： ${resource.parentResource.resourceName}</div>
								</blockquote>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="权限代码：" value="${resource.code}" autocomplete="off" placeholder="请输入功能权限代码" verify="required" name="code" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="功能名称：" value="${resource.resourceName}" autocomplete="off" placeholder="请输入功能名称" verify="required" name="resourceName" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="执行顺序：" autocomplete="off" placeholder="请输入执行顺序" verify="required|number" name="executeOrder" value="${resource.executeOrder}" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="12">
								<smart:textarea name="description" labelName="描述信息：" display="block">${resource.description}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:buttonGroup container="true">
									<button class="layui-btn layui-btn-sm" type="button" lay-submit lay-filter="smart-submit" title="确认">
										<i class="fa fa-check"></i>&nbsp;确认
									</button>
									<smart:button size="sm" theme="warm" type="reset" title="重置">
										<smart:icon icon="rotate-left"></smart:icon>&nbsp;重置
						   			</smart:button>
									<smart:button size="sm" theme="normal" type="button" method="addResource" title="添加子功能">
										<smart:icon icon="plus-square"></smart:icon>&nbsp;添加子功能
						   			</smart:button>
									<smart:button size="sm" theme="danger" type="button" method="removeResource" title="删除功能">
										<smart:icon icon="remove"></smart:icon>&nbsp;删除
						   			</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>
				</smart:fieldSet>
			</div>
		</div>
	</smart:grid>
	<smart:scriptHead models="form,layer,element">

		<smart:buttonScriptAction>
			removeResource : function() {
				smart.confirm({
					title:'删除系统功能',
					message:'确认删除系统功能？',
					type:'POST',
					url:'resource/console/remove/save?id=${resource.id}',
					callback : function() {
						parent.refreshMenu('${resource.parentId}');
					}
				});
			},
			addResource : function() {
				smart.show({
					title : '添加系统功能',
					url : 'resource/console/create',
					params : {
						id : '${resource.id}',
					},
					size : 'full',
					scrollbar : false
				});
			}
		</smart:buttonScriptAction>
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			var mask = layer.load(1, {
			  	shade: [0.5,'#fff']
			});
			$.post('resource/console/edit/save',
				obj.field,
				function(result) {	
					layer.close(mask);
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						parent.refreshMenu('${resource.parentId}');
						parent.layer.close(index);
					} else {							
						layer.msg(result.message, {
							time:1000
							,icon:2
							,anim:6
						});
					}
				},
			'json');
		});
	</smart:scriptHead>
</smart:body>
</html>