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
				<smart:fieldSet title="报表生成条件" color="blue" style="padding-top:1em;">
					<smart:form id="saveForm">
						<smart:textInput type="hidden" name="reportCode" value="${reportCode}"></smart:textInput>
						<smart:gridRow>
							<smart:gridColumn colPart="5" colOffset="3">
								<smart:linkSelect labelName="填报单位：" id="organNodeIdTag" display="block" />
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="5" colOffset="3">
								<smart:linkSelect labelName="报表格式：" id="reportFormatTag" display="block"/>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:buttonGroup container="true">
									<button class="layui-btn layui-btn-sm" type="button" lay-submit lay-filter="smart-submit" title="生成报表">
										<i class="fa fa-check"></i>&nbsp;生成报表
									</button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>
				</smart:fieldSet>
			</div>
		</div>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,linkSelect">
	<smart:initLinkSelect id="organNodeIdTag" name="reportOrganId" verify="required" tips="请选择填报单位" url="system/organ/node/query" params="{organTreeId:'394e21fa-1eb6-42ee-ba32-50655fa16517'}" />
	<smart:initLinkSelect id="reportFormatTag" name="format" tips="请选择报表格式" data="[{id:'pdf',name:'PDF格式'},{id:'html',name:'HTML格式'}]"/>
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {	
			var url = 'system/report/do',params = obj.field,
			paramStr = Object.keys(params).map(function(key){ 
				 return encodeURIComponent(key) + '=' + encodeURIComponent(params[key]); 
			}).join('&')
			url = url + '?' + paramStr;
			window.open(url, '_blank','${titel}');
		});
	</smart:scriptHead>
</smart:body>
</html>