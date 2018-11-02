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
				<smart:fieldSet title="维护用户基本资料" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="id" value="${user.id}"></smart:textInput>
						<c:if test="${not empty user.id}">
							<blockquote class="layui-elem-quote" style="padding:5px;">
								<div style="padding-left:10px;">所在组织节点名称： ${organNodeAllName}</div>
							</blockquote>
						</c:if>
						<c:if test="${empty user.id}">
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:linkSelect labelName="所属区域：" id="organTreeIdTag" display="block"/>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:linkSelect labelName="所属单位：" id="organNodeIdTag" display="block"/>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<c:if test="${not empty user.loginName}">
									<smart:textInput labelName="登录名：" autocomplete="off" placeholder="输入系统登录名" verify="required" name="loginName" value="${user.loginName}" otherAttr="readonly" isNotNull="true">
									</smart:textInput>
								</c:if>
								<c:if test="${empty user.loginName}">
									<smart:textInput labelName="登录名：" autocomplete="off" placeholder="输入系统登录名" verify="required" name="loginName" value="${user.loginName}" isNotNull="true">
									</smart:textInput>
								</c:if>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<c:if test="${not empty user.name}">
									<smart:textInput labelName="姓名：" autocomplete="off" placeholder="输入姓名" verify="required" name="name" value="${user.name}" otherAttr="readonly" isNotNull="true">
									</smart:textInput>
								</c:if>
								<c:if test="${empty user.name}">
									<smart:textInput labelName="姓名：" autocomplete="off" placeholder="输入姓名" verify="required" name="name" value="${user.name}" isNotNull="true">
									</smart:textInput>
								</c:if>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:checkbox isRadio="true" labelName="性别：" name="sex" data="[{'key':'1','value':'男'},{'key':'2','value':'女'}]" checkedKey="${user.sex}" />
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textInput labelName="电子邮件：" autocomplete="off" placeholder="输入姓名" verify="required|email" isNotNull="true" name="email" value="${user.email}">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textInput labelName="通讯地址：" autocomplete="off" placeholder="输入通讯" name="address" value="${user.address}">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textInput labelName="身份证号：" autocomplete="off" placeholder="输入身份证号" verify="required" isNotNull="true" name="officePhone" value="${user.idNumber}">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:textInput labelName="手机号码：" autocomplete="off" placeholder="输入手机号码" verify="required" isNotNull="true" name="honePhone" value="${user.mobile1}">
								</smart:textInput>
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
		var linkOrganNodeSelect = function(value) {
			var params = {};
			params.organTreeId = value;
			organNodeIdTag.refresh(params);
		}
		<smart:initLinkSelect id="organTreeIdTag" name="organTreeId" tips="请选择所属区域" verify="required" selected="${organTreeId}" url="system/organ/tree/query" linkFunction="linkOrganNodeSelect"/>
		<smart:initLinkSelect id="organNodeIdTag" name="organNodeId" tips="请选择所属单位" verify="required" selected="${organNodeId}" url="system/organ/node/query" params="{organTreeId:'${organTreeId}'}"/>
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			$.post('security/user/info/edit/save',
				obj.field,
				function(result) {	
					if (result.success) {
						layer.msg(result.message, {
							time: 200
							,icon:1
							,anim:5
						});
						parent.layer.close(index);
					} else {							
						layer.msg(result.message, {
							time:500
							,icon:2
							,anim:6
						});
					}
				},'json');
		});
	</smart:scriptHead>
</smart:body>
</html>