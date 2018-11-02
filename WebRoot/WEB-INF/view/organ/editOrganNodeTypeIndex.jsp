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
				<smart:fieldSet title="维护组织节点类型信息" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="id" value="${type.id}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textInput labelName="节点类型编码：" value="${type.code}" autocomplete="off" placeholder="请输入组织节点类型编码" verify="required" name="code" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textInput labelName="节点类型名称：" value="${type.name}" autocomplete="off" placeholder="请组织节点类型名称" verify="required" name="name" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="8">
								<div class="layui-form-item">
									<label class="layui-form-label">顶层组织</label>
									<div class="layui-input-block">
										<input type="radio" name="top" value="1" title="是" <c:if test="${type.top == 1}">checked</c:if>> 
										<input type="radio" name="top" value="0" title="否" <c:if test="${type.top != 1}">checked</c:if>>
									</div>
								</div>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="8">
								<div class="layui-form-item">
									<label class="layui-form-label">包含下层组织</label>
									<div class="layui-input-block">
										<input type="radio" name="down" value="1" title="是" <c:if test="${type.down == 1}">checked</c:if>> 
										<input type="radio" name="down" value="0" title="否" <c:if test="${type.down != 1}">checked</c:if>>
									</div>
								</div>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="8">
								<div class="layui-form-item">
									<label class="layui-form-label">包含人员</label>
									<div class="layui-input-block">
										<input type="radio" name="people" value="1" title="是" <c:if test="${type.people == 1}">checked</c:if>>
										<input type="radio" name="people" value="0" title="否" <c:if test="${type.people != 1}">checked</c:if>>
									</div>
								</div>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="8">
								<div class="layui-form-item">
									<label class="layui-form-label">存在上下级关系</label>
									<div class="layui-input-block">
										<input type="radio" name="relation" value="1" title="是" <c:if test="${type.relation == 1}">checked</c:if>>
										<input type="radio" name="relation" value="0" title="否" <c:if test="${type.relation != 1}">checked</c:if>>
									</div>
								</div>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="8">
								<smart:textarea name="description" labelName="描述信息" display="block">${type.description}</smart:textarea>
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
	<smart:scriptHead models="form,layer,element">
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			$.post('system/organ/node/type/edit/save',
				obj.field,
				function(result) {	
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