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
				<smart:fieldSet title="新增应用系统信息" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="id" value="${appNode.id}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="应用系统编码：" autocomplete="off" placeholder="请输入应用系统编码" verify="required" name="code" isNotNull="true" value="${appNode.code}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="应用系统名称：" autocomplete="off" placeholder="请输入应用系统名称" verify="required" name="name" isNotNull="true" value="${appNode.name}">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="首页链接：" autocomplete="off" placeholder="请输入首页链接地址" verify="required" name="indexURL" isNotNull="true" value="${appNode.indexURL}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="开发商：" autocomplete="off" placeholder="请输入开发商名称" name="vendor" value="${appNode.vendor}">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="应用服务器：" autocomplete="off" placeholder="请输入应用服务器地址" name="appServer" value="${appNode.appServer}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="数据库：" autocomplete="off" placeholder="请输入数据库地址" name="dbServer" value="${appNode.dbServer}">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="框架版本：" autocomplete="off" placeholder="请输入应用系统框架版本" name="version" value="${appNode.version}">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="超时（分钟）：" autocomplete="off" placeholder="请输入超时失效时间" verify="number" name="maxInactiveInterval" value="${appNode.maxInactiveInterval}">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="12">
								<smart:textarea name="description" labelName="描述信息" display="block">${appNode.description}</smart:textarea>
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
			$.post('app/edit/save',
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