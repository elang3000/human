<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
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
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="3">
						<smart:card>
							<smart:cardHead>
								<blockquote class="layui-elem-quote" style="padding: 5px;">
									<div>&nbsp;&nbsp;&nbsp;系统字典详情</div>
								</blockquote>
							</smart:cardHead>
							<smart:cardBody>
								<script>
									function loadCodeInfoById(event, treeId, codeInfo) {
										var iframe = $("#codeInfoDetailIframe");
										iframe.attr("src","system/dict/info/load?codeTypeId=${type.id}&id=" + codeInfo.id);
										iframe.height = document.documentElement.clientHeight;
										$(".layui-col-md9").css("height", iframe.height - 100 + 'px');
									};
								</script>
								<smart:customDynamicTree id="treeArea"
									url="system/dict/info/query?codeTypeId=${type.id}"
									style="border:1px solid #e6e6e6;height:100%;" cutSize="155"
									customEvent="true" funcType="onClick"
									funcName="loadCodeInfoById" />
							</smart:cardBody>
						</smart:card>
					</smart:gridColumn>
					<smart:gridColumn colPart="9">
						<iframe
							src="system/dict/info/load?id=${info.id}&codeTypeId=${type.id}"
							frameborder="0" id="codeInfoDetailIframe" class="layadmin-iframe"
							onload="renderIframe()"></iframe>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="layer,element">
	</smart:scriptHead>
	<script type="text/javascript">
		function renderIframe() {
			var iframe = $("#codeInfoDetailIframe");
			iframe.height = document.documentElement.clientHeight;
			$(".layui-col-md9").css("height", iframe.height - 100 + 'px');
		}
		function refreshCodeInfo(codeInfoId) {
			var codeInfoTree = $.fn.zTree.getZTreeObj("treeArea");
			var nodes = codeInfoTree.getNodesByParam("id", codeInfoId, null); 
			if (nodes.length>0) {
				codeInfoTree.reAsyncChildNodes(nodes[0], "refresh");
			} else {
				codeInfoTree.reAsyncChildNodes(null, "refresh");
			}
		}
	</script>
</smart:body>