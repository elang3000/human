<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--信息维护" />
<style type="text/css">
.layui-body {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	z-index: 998;
	width: auto;
	overflow: hidden;
	overflow-y: auto;
	box-sizing: border-box
}

.layadmin-iframe {
	position: absolute;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0
}
</style>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="统一用户管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="组织机构管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="组织树管理" iLinkpath="system/organ/tree/index"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="维护组织节点信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="3">
						<smart:card>
							<smart:cardHead>
								<blockquote class="layui-elem-quote" style="padding:5px;">
									<div>&nbsp;&nbsp;&nbsp;组织结构树</div>
								</blockquote>
							</smart:cardHead>
							<smart:cardBody>
								<script>
									function loadOrganNodeById(event, treeId,
											treeNode) {
										var iframe = $("#organNodeDetailIframe");
										iframe.attr("src","system/organ/tree/node/detail?id=" + treeNode.id + "&organTreeId=${organTreeId}");
										iframe.height = document.documentElement.clientHeight;
										$(".layui-col-md9").css("height", iframe.height - 100 + 'px');
									};
								</script>
								<smart:customDynamicTree id="treeArea" url="system/organ/tree/node/query?organTreeId=${organTreeId}" style="border:1px solid #e6e6e6;height:100%;" cutSize="155" customEvent="true" funcType="onClick"
									funcName="loadOrganNodeById" />
							</smart:cardBody>
						</smart:card>
					</smart:gridColumn>
					<smart:gridColumn colPart="9">
						<iframe src="" frameborder="0" id="organNodeDetailIframe" class="layadmin-iframe"></iframe>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="layer,element">
	</smart:scriptHead>
	<script type="text/javascript">
		function refreshOrganNode(organNodeId) {
			var treeOrganTree = $.fn.zTree.getZTreeObj("treeArea");
			var nodes = treeOrganTree.getNodesByParam("id", organNodeId, null); 
			if (nodes.length>0) {
				treeOrganTree.reAsyncChildNodes(nodes[0], "refresh");
			} else {
				treeOrganTree.reAsyncChildNodes(null, "refresh");
			}
		}
	</script>
</smart:body>