<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<smart:tabPanelParent filter="tab"
	style="margin-left:10px;margin-right:10px;">
	<smart:tabPanel>
		<c:if test="${isUnit }">
			<div style="display:none">
		</c:if>
		<smart:tabPanelItem show="false" eId="" turnurl="ofcflow/draftServant/importList"  itemName="导入记录"></smart:tabPanelItem>
		<!-- 此div以及内部内容无用 -->
		<div style="display:none">		
			<smart:tabPanelItem show="false"  turnurl="ofcflow/draftServant/index" 
			eId="" itemName="拟录用名单列表"></smart:tabPanelItem>
		</div>
				
		<smart:tabPanelItem turnurl="ofcflow/draftServant/collectList"
			show="false" eId="" itemName="拟录用汇总"></smart:tabPanelItem>
		<smart:tabPanelItem turnurl="ofcflow/draftServantReportResult/index"
			show="false" eId="" itemName="汇总表单查看"></smart:tabPanelItem>
			<c:if test="${isUnit }">
				</div>
			</c:if>
		<smart:tabPanelItem turnurl="ofcflow/draftServant/employStatusList"
			show="false" eId="" itemName="录用查看"></smart:tabPanelItem>

	</smart:tabPanel>
</smart:tabPanelParent>


