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
				<smart:fieldSet title="维护系统菜单信息" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="id" value="${menu.id}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="菜单代码：" value="${menu.code}" autocomplete="off" placeholder="请输入菜单代码" verify="required" name="code" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="菜单名称：" value="${menu.resourceName}" autocomplete="off" placeholder="请输入菜单名称" verify="required" name="resourceName" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="显示序列：" value="${menu.displayOrder}" autocomplete="off" placeholder="请输入菜单显示序列" verify="number" name="displayOrder" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="响应地址：" value="${menu.linkPath}" autocomplete="off" placeholder="请输入菜单响应地址" name="linkPath">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="弹出位置：" value="${menu.target}" autocomplete="off" placeholder="请输入菜单弹出位置" name="target">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="CSS样式：" value="${menu.cls}" autocomplete="off" placeholder="请输入菜单CSS样式" name="cls">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="JS事件：" value="${menu.handler}" autocomplete="off" placeholder="请输入菜单响应事件" name="handler">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<div class="layui-form-item">
									<label class="layui-form-label">提供快捷菜单</label>
									<div class="layui-input-block">
										<input type="radio" name="isShortcut" value="true" title="是" <c:if test="${menu.isShortcut}">checked</c:if>> 
										<input type="radio" name="isShortcut" value="false" title="否" <c:if test="${!menu.isShortcut}">checked</c:if>>
									</div>
								</div>
							</smart:gridColumn>
							<smart:gridColumn colPart="12">
								<smart:gridRow>
									<smart:gridColumn colPart="10">
										<smart:textInput labelName="菜单图标：" value="${menu.icon}" autocomplete="off" placeholder="请输入菜单响应事件" name="icon">
										</smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="2">
										<smart:button size="md" theme="primary" method="icon" title="修改系统图标">
											<smart:icon icon="plus-square-o"></smart:icon>
							   			</smart:button>
						   			</smart:gridColumn>
								</smart:gridRow>
								
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="12">
								<smart:textarea name="description" labelName="描述信息" display="block">${menu.description}</smart:textarea>
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
									<smart:button size="sm" theme="normal" type="button" method="addMenuResource" title="添加子菜单">
										<smart:icon icon="plus-square"></smart:icon>&nbsp;添加子菜单
						   			</smart:button>
									<smart:button size="sm" theme="danger" type="button" method="removeMenuResource" title="删除当前菜单">
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
			removeMenuResource : function() {
				smart.confirm({
					title:'删除菜单',
					message:'确认删除系统菜单？',
					type:'POST',
					url:'resource/menu/remove/save?id=${menu.id}',
					callback : function() {
						parent.refreshMenu('${menu.parentId}');
					}
				});
			},
			addMenuResource : function() {
				smart.show({
					title : '添加子菜单',
					url : 'resource/menu/create',
					params : {
						id : '${menu.id}',
					},
					size : 'full',
					scrollbar : false
				});
			},
			icon:function() {
				smart.show({
					title : '选择系统图标',
					url : 'resource/menu/icon',
					params : {
						id : '${menu.id}',
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
			$.post('resource/menu/edit/save',
				obj.field,
				function(result) {	
					layer.close(mask);
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						parent.refreshMenu('${menu.parentId}');
						parent.layer.close(index);
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
	<script type="text/javascript" src="ztree/js/jquery-1.4.4.min.js" charset="UTF-8"></script>
	<script type="text/javascript">
		function updateMenu(data) {
			$("[name='icon']").val(data.icon);
		}
	</script>
</smart:body>
</html>