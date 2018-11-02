<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="职位简章"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="流程信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList"
							url="ofcflow/flow/busList?busId=${busId}" height="full-315" page="false" limit="10000"
							text="未找到有效数据！">
							<tr>
								<smart:tableItem field="sourceOrganNode" width=".1" sort="true">发文单位</smart:tableItem>
								<smart:tableItem field="createTime" width=".1" sort="true">发文时间</smart:tableItem>
								<smart:tableItem field="targetOrganNode" width=".1" sort="true">操作单位</smart:tableItem>
								<smart:tableItem field="targetSecurityUser" width=".1" sort="false">操作人</smart:tableItem>
								<smart:tableItem field="remark" width=".1" sort="false">审批招录人数</smart:tableItem>
								<smart:tableItem field="opinion" width=".2" sort="false">操作意见</smart:tableItem>
								<smart:tableItem field="result" width=".1" sort="false">操作结果</smart:tableItem>
								<smart:tableItem field="ofcFlowDescription" width=".2" sort="false">业务状态</smart:tableItem>
							</tr>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
				<smart:gridRow>
					<smart:line color="blue" />
					<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
						<smart:buttonGroup container="true">
							<smart:button theme="warm" size="sm" method="goBack" title="返回">
								<smart:icon icon="reply">&nbsp;返回</smart:icon>
							</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>