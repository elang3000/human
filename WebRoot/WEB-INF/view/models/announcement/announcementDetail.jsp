<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--消息中心" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="12">
						<table
							style="border: 0px solid black; height: 80%; width: 100%; padding: 2cm 4cm 3cm 4cm;">
							<th style="text-align: center; color: gray; font-size: 18px"><h2>${a.title}</h2></th>

							<tr>
								<td style="text-indent: 1%;">
									<h4>
										<i>${user}您好：</i>
									</h4>
								</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3" style="text-indent: 12%;">
									<h4>
										${a.content}!
									</h4>
								</td>
							</tr>
							<tr>
								<td colspan="3" style="text-align: right; color: gray">
									<h4>${organName}</h4>
								</td>
							</tr>
							<tr>
								<td colspan="3" style="text-align: right; color: gray">
									<h4>
										<fmt:formatDate value="${a.createTime}"
											pattern="yyyy-MM-dd" />
									</h4>
								</td>
							</tr>
						</table>
					</smart:gridColumn>
				</smart:gridRow>
				<smart:gridRow>
					<smart:buttonGroup container="true">
						<smart:button theme="warm" size="sm" method="goBack" title="返回">
							<smart:icon icon="reply">&nbsp;返回</smart:icon>
						</smart:button>
					</smart:buttonGroup>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:buttonScriptAction>
			goBack : function(data) {
				parent.layui.table.reload('navigationList');
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>