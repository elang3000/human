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
				<smart:fieldSet title="维护组织类型规则信息" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="treeTypeId" value="${type.id}"></smart:textInput>
						<smart:textInput type="hidden" name="id" value="${rule.id}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<blockquote class="layui-elem-quote" style="padding:5px;">
									<div>组织类型： ${type.name}</div>
								</blockquote>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textInput labelName="类型规则编码：" autocomplete="off" placeholder="请输入组织类型规则编码" verify="required" name="code" value="${rule.code}" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textInput labelName="类型规则名称：" autocomplete="off" placeholder="请输入组织类型规则名称" verify="required" name="name" value="${rule.name}" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:linkSelect labelName="上级节点类型：" id="superiorNodeTypeTag" display="block"/>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:linkSelect labelName="下级节点类型：" id="subordinateNodeTypeTag" display="block"/>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="8">
								<smart:textarea name="description" labelName="描述信息" display="block">${rule.description}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:buttonGroup container="true">
									<button class="layui-btn layui-btn-sm" type="button" lay-submit lay-filter="smart-submit" title="确认">
										<i class="fa fa-check"></i>&nbsp;确认
									</button>
									<smart:button size="sm" theme="warm" type="reset" title="重置">
										<smart:icon icon="rotate-left"></smart:icon>&nbsp;重置
				   				</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>
				</smart:fieldSet>
			</div>
		</div>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,linkSelect">
		<smart:initLinkSelect id="superiorNodeTypeTag" name="superiorNodeTypeId" tips="请选择上级组织类型规则" selected="${rule.superiorNodeType.id}" verify="required" url="system/organ/node/type/query"/>
 		<smart:initLinkSelect id="subordinateNodeTypeTag" name="subordinateNodeTypeId" tips="请选择下级组织类型规则" selected="${rule.subordinateNodeType.id}" verify="required" url="system/organ/node/type/query"/>
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			var mask = layer.load(1, {
			  	shade: [0.5,'#fff']
			});
			$.post('system/organ/tree/rule/edit/save',
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
	</smart:scriptHead>
</smart:body>
</html>