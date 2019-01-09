<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="查看重大事项申请"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="importantEventApply/save">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${importantEventApply.id }"></smart:textInput>
					<smart:textInput type="hidden" name="applyOrgan.id" value="${importantEventApply.applyOrgan.id}"></smart:textInput>
					<smart:textInput type="hidden" name="applyUser.id" value="${importantEventApply.applyUser.id}"></smart:textInput>
					<smart:gridRow>
						<smart:title title="事项信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:infoShowerLabel infoname="事项标题" infovalue="${importantEventApply.eventTitle}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="申请单位" infovalue="${importantEventApply.applyOrgan.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:infoShowerLabel infoname="事项说明" infovalue="${importantEventApply.description}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="事项说明" infovalue="${importantEventApply.eventType.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="1">
							<smart:fileUpload accept="file" buttonName="事项附件" dataName="filePath" auto="false" size="20000" data="${importantEventApply.filePath}" isView="true"/>
						</smart:gridColumn>
					</smart:gridRow>
					
					<c:if test="${importantEventApply.status>6 }">
						<smart:gridRow>
							<smart:title title="经办人选择" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:infoShowerLabel infoname="经办人姓名" infovalue="${importantEventApply.eventOperator.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
					</c:if>
					
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
							<smart:button theme="warm" size="sm" method="goBack" title="返回">
								<smart:icon icon="reply">&nbsp;返回</smart:icon>
							</smart:button>
						  </smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:utils/>
		<smart:fileUploadUtils/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>