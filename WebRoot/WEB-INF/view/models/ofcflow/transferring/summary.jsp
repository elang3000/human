<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="上海市公务员信息管理系统--事项申请" />
<style type="text/css">
	.center{
		text-align: center;
	}
	table{
		color: #707070;
		margin-bottom:30px;
	}
	table th,td{
		border: 1px solid #ddd;
	}
	table th{
		background: repeat-x #F2F2F2;
	}
</style>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="选调交流"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="选调交流汇总" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="选调机构" infovalue="${tra.recruitOrgan.allName}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<c:forEach items="${types}" var="type">
									<th style="vertical-align: middle" colspan="${fn:length(employType)}" class="center">${type.value}</th>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${types}" var="type">
									<c:forEach items="${employType}" var="e">
										<th style="vertical-align: middle" class="center">${e.name}</th>
									</c:forEach>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${number}" var="n">
									<td style="width: 2.5%; vertical-align: middle" class="center">${n.value}</td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
					<table class="table table-bordered table-hover" style="width: 100%;table-layout: fixed;">
						<thead>
							<tr>
								<th style="vertical-align: middle" colspan="10" class="center">计划招录人数</th>
							</tr>
							<tr>
								<c:forEach items="${employType}" var="e">
									<th style="vertical-align: middle" class="center">${e.name}</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${sum}" var="n">
									<td style="vertical-align: middle" class="center">${n.value}</td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th style="vertical-align: middle" class="center"></th>
								<c:forEach items="${type}" var="e">
									<th style="vertical-align: middle" colspan="${e.value}" class="center">${e.key}</th>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${postType}" var="e">
									<th style="vertical-align: middle" class="center">${e.name}</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${postList}" var="p">
								<tr>
									<c:forEach items="${p}" var="n">
										<td style="width: 5%; vertical-align: middle" class="center">
											<c:if test="${n.value == '1'}"><i class="fa fa-check" aria-hidden="true"></i></c:if>
											<c:if test="${n.value != '1' && n.value != '0'}">${n.value}</c:if>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</smart:gridRow>
				<smart:gridRow>
					<smart:line color="blue" />
					<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
						<smart:buttonGroup container="true">
							<smart:button size="sm" id="save" other="lay-submit" title="提交">
								<smart:icon icon="plus">&nbsp;提交</smart:icon>
							</smart:button>
							<smart:button theme="warm" size="sm" method="back" title="返回">
								<smart:icon icon="reply">&nbsp;返回</smart:icon>
							</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,linkSelect">
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('.layui-form'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
			,back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
		 form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'提交汇总',
				message:'确认提交汇总吗？',
				url:'ofcflow/transferring/postSave',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('postNavigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>