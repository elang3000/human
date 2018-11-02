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
				<smart:fieldSet title="新增组织节点信息" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="organTreeId" value="${organTreeId}"></smart:textInput>
						<smart:textInput type="hidden" name="parentOrganNodeId" value="${parentOrganNode.id}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<blockquote class="layui-elem-quote" style="padding:5px;">
									<c:if test="${not empty parentId}">
										<div style="padding-left:60px;">上级组织节点： ${parentOrganNode.allName}</div>
									</c:if>
									<c:if test="${empty parentId}">
										<div style="padding-left:60px;">上级组织节点： 根节点组织</div>
									</c:if>
								</blockquote>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="组织名称：" autocomplete="off" placeholder="请输入组织名称" verify="required" name="name" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="组织全名：" autocomplete="off" placeholder="请输入组织全名" verify="required" name="allName" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="组织编码：" autocomplete="off" placeholder="请输入组织编码" verify="required" name="code" isNotNull="true">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:linkSelect labelName="组织类型：" id="organNodeTypeTag" display="block" />
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:linkSelect labelName="业务类型：" id="organNodeBizTypeTag" display="block" />
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:linkSelect labelName="管理人员：" id="organNodeAdminUserTag" display="block" />
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="地址：" autocomplete="off" placeholder="请输入组织地址" name="deptAddress">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="传真：" autocomplete="off" placeholder="请输入组织传真" name="fax">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="组织负责人：" autocomplete="off" placeholder="请输入组织负责人" name="principal">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="6">
								<smart:textInput labelName="联系电话：" autocomplete="off" placeholder="请输入联系电话" name="principalPhone">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow style="margin-top:0px;">
							<smart:gridColumn colPart="12">
								<smart:textarea name="description" labelName="描述信息" display="block"></smart:textarea>
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
	<smart:scriptHead models="form,layer,element,linkSelect">
		<smart:initLinkSelect id="organNodeTypeTag" name="organNodeTypeId" tips="请选择组织类型" verify="required" url="system/organ/node/type/query/accredit?organTreeId=${organTreeId}&organNodeId=${parentId}"/>
 		<smart:initLinkSelect id="organNodeBizTypeTag" name="bizType" tips="请选择组织业务类型" url="dictquery/sub/code/orgBizType" keyName="code" titleName="name"/>
	    <smart:initLinkSelect id="organNodeAdminUserTag" name="adminUserId" tips="请选择组织管理人员" url="security/user/query/list" />
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			$.post('system/organ/tree/node/create/save',
				obj.field,
				function(result) {	
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						parent.parent.refreshOrganNode('${parentId}');
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