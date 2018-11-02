<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
<link rel="stylesheet" href="layui/css/ace.min.css" media="all">
<link rel="stylesheet" href="layui/css/bootstrap.min.css" media="all">
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="业务处理记录" color="blue" style="padding-top:1em;">
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<ul class="steps">					
									<c:forEach items="${seqs}" var="seq">
										<li data-step="${seq.index}" class="<c:if test="${seq.complete}">complete</c:if> <c:if test="${seq.active}">active</c:if>">
											<span class="step">${seq.index}</span>
											<span class="title">${seq.name}</span>
										</li>
									</c:forEach>
								</ul>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<ul class="layui-timeline">
									<c:forEach items="${records}" var="record"  varStatus="status">
										<c:if test="${status.index == 0}">
											<li class="layui-timeline-item">
		    									<i class="layui-icon layui-timeline-axis" style="color:#d02525;">&#xe756;</i>
		   										<div class="layui-timeline-content layui-text" style="color:#d02525;">
		     										<h3 class="layui-timeline-title"  style="color:#d02525;">
		     											发文时间：${record.createTime}（${record.operationName}）<br>
		     										</h3>
		     										<p>
		     											业务描述：${record.ofcFlowDescription}<br>
		     											审批结果：${record.result}<br>
		     											审批意见：${record.opinion}<br>
		     											备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：${record.remark}
		     										</p>
		   										</div>
				 							</li>	
										</c:if>
										<c:if test="${status.index != 0  && fn:length(records) != status.index +1}">	
		  									<li class="layui-timeline-item">
		    									<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
		   										<div class="layui-timeline-content layui-text">
		     										<h3 class="layui-timeline-title">
		     											发文时间：${record.createTime}（${record.operationName}）<br>
		     										</h3>
		     										<p>
		     											业务描述：${record.ofcFlowDescription}<br>
		     											审批结果：${record.result}<br>
		     											审批意见：${record.opinion}<br>
		     											备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：${record.remark}
		     										</p>
		   										</div>
				 							</li>
			 							</c:if>
			 							<c:if test="${fn:length(records) == status.index +1}">
											<li class="layui-timeline-item">
		    									<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
		   										<div class="layui-timeline-content layui-text">
		     										<h3 class="layui-timeline-title">
		     											发文时间：${record.createTime}（${record.operationName}）<br>
		     										</h3>
		     										<p>
		     											业务描述：${record.ofcFlowDescription}<br>
		     										</p>
		   										</div>
				 							</li>
										</c:if>
		 							</c:forEach>
								</ul>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="close" title="关闭">
										<smart:icon icon="close"></smart:icon>&nbsp;关闭
					  				 	</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:fieldSet>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="form,layer,element">
		<smart:buttonScriptAction>
			close:function(){
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>