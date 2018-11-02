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
						<c:if test="${!empty parent}">
							<smart:textInput type="hidden" name="parentId"
								value="${parent.id}"></smart:textInput>
						</c:if>
						<smart:textInput type="hidden" name="codeTypeId"
							value="${codeTypeId}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<blockquote class="layui-elem-quote" style="padding: 5px;">
									<c:if test="${!empty parent}">
										<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;父类字典名称：
											${parent.name}</div>
									</c:if>
									<c:if test="${empty parent}">
										<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此为根字典</div>
									</c:if>
								</blockquote>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典名称：" autocomplete="off"
									placeholder="请输入字典名称" verify="required" name="name"
									isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典代码：" autocomplete="off"
									placeholder="请输入字典代码" verify="required" name="code"
									isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="显示序列：" autocomplete="off"
									placeholder="请输入显示序列" verify="number" name="dispOrder"
									isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="12">
								<smart:textarea name="description" labelName="描述信息："
									display="block"></smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>					
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名1：" autocomplete="off" placeholder="请输入字典名称别名" name="alias1">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名2：" autocomplete="off" placeholder="请输入字典名称别名" name="alias2">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名3：" autocomplete="off" placeholder="请输入字典名称别名" name="alias3">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名4：" autocomplete="off" placeholder="请输入字典名称别名" name="alias4">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名5：" autocomplete="off" placeholder="请输入字典名称别名" name="alias5">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名6：" autocomplete="off" placeholder="请输入字典名称别名" name="alias6">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名7：" autocomplete="off" placeholder="请输入字典名称别名" name="alias7">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名8：" autocomplete="off" placeholder="请输入字典名称别名" name="alias8">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名9：" autocomplete="off" placeholder="请输入字典名称别名" name="alias9">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="字典别名10：" autocomplete="off" placeholder="请输入字典名称别名" name="alias10">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:buttonGroup container="true">
									<button class="layui-btn layui-btn-sm" type="button" lay-submit
										lay-filter="smart-submit" title="确认">
										<i class="fa fa-check"></i>&nbsp;确认
									</button>
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
			$.post('system/dict/info/create/save',
				obj.field,
				function(result) {	
					layer.close(mask);
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						parent.parent.refreshCodeInfo();
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