<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
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
				<smart:fieldSet title="维护字典信息" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="id" value="${info.id}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textInput labelName="字典名称：" autocomplete="off"
									placeholder="请输入字典名称" verify="required" name="name"
									value="${info.name}" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典代码：" autocomplete="off"
									placeholder="请输入字典代码" verify="required" name="code"
									value="${info.code}" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="显示序列：" autocomplete="off"
									placeholder="请输入显示序列" verify="number" name="dispOrder"
									value="${info.dispOrder}" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="12">
								<smart:textarea name="description" labelName="描述信息："
									display="block">${info.description}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名1：" autocomplete="off" placeholder="请输入字典别名" name="alias1" value="${info.alias1}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名2：" autocomplete="off" placeholder="请输入字典别名" name="alias2" value="${info.alias2}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名3：" autocomplete="off" placeholder="请输入字典别名" name="alias3" value="${info.alias3}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名4：" autocomplete="off" placeholder="请输入字典别名" name="alias4" value="${info.alias4}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名5：" autocomplete="off" placeholder="请输入字典别名" name="alias5" value="${info.alias5}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名6：" autocomplete="off" placeholder="请输入字典别名" name="alias6" value="${info.alias6}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名7：" autocomplete="off" placeholder="请输入字典别名" name="alias7" value="${info.alias7}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名8：" autocomplete="off" placeholder="请输入字典别名" name="alias8" value="${info.alias8}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名9：" autocomplete="off" placeholder="请输入字典别名" name="alias9" value="${info.alias9}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名10：" autocomplete="off" placeholder="请输入字典别名" name="alias10" value="${info.alias10}">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:buttonGroup container="true">
									<c:if test="${!empty info.id}">
										<button class="layui-btn layui-btn-sm" type="button"
											lay-submit lay-filter="smart-submit" title="确认">
											<i class="fa fa-check"></i>&nbsp;确认
										</button>
									</c:if>
									<smart:button size="sm" theme="normal" type="button"
										method="addRoot" title="添加根字典">
										<smart:icon icon="plus-square"></smart:icon>&nbsp;添加根菜单
						   			</smart:button>
									<c:if test="${!empty info.id}">
										<smart:button size="sm" theme="normal" type="button"
											method="addSub" title="添加子字典">
											<smart:icon icon="plus-square-o"></smart:icon>&nbsp;添加子字典
							   			</smart:button>
										<smart:button size="sm" theme="danger" type="button"
											method="remove" title="删除当前字典">
											<smart:icon icon="remove"></smart:icon>&nbsp;删除
							   			</smart:button>
									</c:if>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>
				</smart:fieldSet>
			</div>
		</div>
	</smart:grid>
	<smart:scriptHead models="form,layer,element">
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			var mask = layer.load(1, {
			  	shade: [0.5,'#fff']
			});
			$.post('system/dict/info/edit/save',
				obj.field,
				function(result) {	
					layer.close(mask);
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						parent.refresh();
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
		<smart:buttonScriptAction>
			remove : function() {
				smart.confirm({
					title:'删除字典',
					message:'确认删除字典？',
					type:'POST',
					url:'system/dict/info/remove/save?id=${info.id}',
					callback : function() {
						parent.refreshMenu('');
					}
				});
			},
			addRoot : function() {
				smart.show({
					title : '添加根字典',
					url : 'system/dict/info/create',
					size : 'full',
					params : {
						codeTypeId : '${codeTypeId}'
					},
					scrollbar : false
				});
			},
			addSub : function() {
				smart.show({
					title : '添加子字典',
					url : 'system/dict/info/create',
					params : {
						codeTypeId : '${codeTypeId}',
						parentId : '${info.id}'
					},
					size : 'full',
					scrollbar : false
				});
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>