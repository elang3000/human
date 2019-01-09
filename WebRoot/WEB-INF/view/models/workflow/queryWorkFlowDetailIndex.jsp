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
<style type="text/css">
	.layui-layer-msg-bg .layui-layer-content{
		background-color:#C4C6BF; 
		color: #fff;
		filter:alpha(opacity:50); 
		opacity:0.5;  
		-moz-opacity:0.5;
		-khtml-opacity: 0.5;
	}
</style>
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
										<li data-step="${seq.index}" onmousedown="displayStepDetails(this,'${busId}','${seq.code}')" class="<c:if test="${seq.complete}">complete</c:if> <c:if test="${seq.active}">active</c:if>">
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
											<c:if test="${record.busState == 0}">
												<li class="layui-timeline-item">
			    									<i class="layui-icon layui-timeline-axis" style="color:#d02525;">&#xe756;</i>
			   										<div class="layui-timeline-content layui-text" style="color:#d02525;">
			     										<h3 class="layui-timeline-title"  style="color:#d02525;">
			     											办文时间：${record.busFinishedTime}（${record.operationName}）<br>
			     										</h3>
			     										<p>
			     											业务描述：${record.ofcFlowDescription}<br>
			     											办理人员：${record.targetSecurityUser}（${record.targetOrganNode}）<br>
			     											<c:if test="${not empty record.result}">
			     												审批结果：${record.result}<br>
			     											</c:if>
			     											<c:if test="${not empty record.opinion}">
			     												审批意见：${record.opinion}<br>
			     											</c:if>
			     											<c:if test="${not empty record.remark}">
			     												备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：${record.remark}
			     											</c:if>
			     										</p>
			   										</div>
					 							</li>	
				 							</c:if>
				 							<c:if test="${record.busState == 1}">
					 							<li class="layui-timeline-item">
			    									<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
			   										<div class="layui-timeline-content layui-text">
			     										<h3 class="layui-timeline-title">
			     											办文时间：${record.busFinishedTime}（${record.operationName}）<br>
			     										</h3>
			     										<p>
			     											业务描述：${record.ofcFlowDescription}<br>
			     											办理人员：${record.targetSecurityUser}（${record.targetOrganNode}）<br>
			     											<c:if test="${not empty record.result}">
			     												审批结果：${record.result}<br>
			     											</c:if>
			     											<c:if test="${not empty record.opinion}">
			     												审批意见：${record.opinion}<br>
			     											</c:if>
			     											<c:if test="${not empty record.remark}">
			     												备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：${record.remark}
			     											</c:if>
			     										</p>
			   										</div>
					 							</li>
				 							</c:if>
										</c:if>
										<c:if test="${status.index != 0  && fn:length(records) != status.index +1}">	
		  									<li class="layui-timeline-item">
		    									<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
		   										<div class="layui-timeline-content layui-text">
		     										<h3 class="layui-timeline-title">
		     											办文时间：${record.busFinishedTime}（${record.operationName}）<br>
		     										</h3>
		     										<p>
		     											业务描述：${record.ofcFlowDescription}<br>
		     											办理人员：${record.targetSecurityUser}（${record.targetOrganNode}）<br>
		     											<c:if test="${not empty record.result}">
		     												审批结果：${record.result}<br>
		     											</c:if>
		     											<c:if test="${not empty record.opinion}">
		     												审批意见：${record.opinion}<br>
		     											</c:if>
		     											<c:if test="${not empty record.remark}">
		     												备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：${record.remark}
		     											</c:if>
		     										</p>
		   										</div>
				 							</li>
			 							</c:if>
			 							<c:if test="${fn:length(records) == status.index +1}">
											<li class="layui-timeline-item">
		    									<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
		   										<div class="layui-timeline-content layui-text">
		     										<h3 class="layui-timeline-title">
		     											来文时间：${record.createTime}（${record.operationName}）<br>
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
					  				<c:if test="${not empty linkFlowRecordId}">
						  				<smart:button size="sm" method="look" title="关联业务查询" theme="warm">
											<smart:icon icon="eye"></smart:icon>&nbsp;关联业务查询
						  				</smart:button>
					  				</c:if>
					  				<c:if test="${not empty back}">
						  				<smart:button size="sm" method="back" title="返回" theme="warm">
											<smart:icon icon="backward"></smart:icon>&nbsp;返回
						  				</smart:button>
					  				</c:if>
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
			},
			look : function (){
				location.href = 'workflow/detail/index?back=yes&id=${linkFlowRecordId}';
			},
			back : function () {
				history.back();
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
	<script type="text/javascript">
		function displayStepDetails(object,busId,code) {
			var top = object.offsetHeight + (object.scrollHeight / 2),
			left = object.offsetLeft + (object.scrollWidth / 2);
			if (code == 'START' || code == 'END') {
				return;
			}
			layui.use(['layer'],function() {
				var $ = layui.jquery,layer=layui.layer;
				$.getJSON("workflow/step/detail?busId=" + busId + "&code=" + code, function(result){
				    var content = '';
				    $.each(result, function(idex, obj){
				    	content += '<p style="text-align:left">' + obj.targetSecurityUser + "（"+ obj.targetOrganNode +'）：' + obj.ofcFlowDescription +'</p>'
				    })
				    if (content != '') {
				    	layer.msg(content,{
							offset: [ top + 'px',  left + 'px'],
							anim: 5
						}); 
					}
				});
			}); 
		}
	</script>
</smart:body>