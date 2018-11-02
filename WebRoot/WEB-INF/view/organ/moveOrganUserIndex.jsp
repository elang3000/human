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
		<smart:fieldSet title="移动用户至新的组织节点" color="blue" style="padding-top:1em;">
			<smart:gridRow>
				<smart:gridColumn colPart="10">
					<smart:card>
						<smart:cardBody>
							<script>
								function loadOrganNodeById(event, treeId, treeNode) {
									$("#showTargetOrganNode").html("目标组织名称：" + treeNode.name);
									$("[name='targetOrangNodeId']").val(treeNode.id);
								};
							</script>
							<smart:customDynamicTree id="treeArea" url="system/organ/tree/node/query?organTreeId=${organTreeId}" style="border:1px solid #e6e6e6;height:60%;" cutSize="155" customEvent="true"
								funcType="onClick" funcName="loadOrganNodeById" />
						</smart:cardBody>
					</smart:card>
				</smart:gridColumn>
				<smart:gridColumn colPart="2">
					<blockquote class="layui-elem-quote" style="padding: 5px;">
						<div>原组织名称： ${organNode.allName}</div>
					</blockquote>
					<blockquote class="layui-elem-quote" style="padding: 5px;border-left:5px solid #cc0f16">
						<div id="showTargetOrganNode">目标组织名称：</div>
					</blockquote>
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="organNodeId" value="${organNode.id}"></smart:textInput>
						<smart:textInput type="hidden" name="targetOrangNodeId"></smart:textInput>
						<smart:textInput type="hidden" name="userId" value="${userId}"></smart:textInput>
						<smart:buttonGroup container="true">
							<button class="layui-btn layui-btn-sm" type="button" lay-submit lay-filter="smart-submit" title="确认">
								<i class="fa fa-check"></i>&nbsp;确认
							</button>
						</smart:buttonGroup>
					</smart:form>
				</smart:gridColumn>
			</smart:gridRow>
		</smart:fieldSet>
	</smart:grid>
	<smart:scriptHead models="form,layer,element">
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			$.post('system/organ/node/user/move/save',
				obj.field,
				function(result) {	
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						parent.refreshUser();
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