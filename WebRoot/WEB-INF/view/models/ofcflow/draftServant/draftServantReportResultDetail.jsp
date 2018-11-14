<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
<head>
<style type="text/css">
	.labelwp{
		width:150px !important;
		padding-right:50px !important;
	}
</style>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员录用" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:title title="汇总信息" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="总共汇总人数" infovalue="${dsr.total}人"></smart:infoShowerLabel>
					</smart:gridColumn>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="总共310开头总人数"
							infovalue="${dsr.total310}人"></smart:infoShowerLabel>
					</smart:gridColumn>
				</smart:gridRow>
				<smart:gridRow>
					<smart:title title="公务员汇总表" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="公务员总人数"
							infovalue="${dsr.totalGwy}人"></smart:infoShowerLabel>
					</smart:gridColumn>
					<smart:gridColumn colPart="4" >
						<smart:infoShowerLabel infoname="公务员310开头总人数"
							infovalue="${dsr.totalGwy310}人"></smart:infoShowerLabel>
					</smart:gridColumn>
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList1"
							url="ofcflow/draftServantSummary/reportPage?ids=${ids}&servantType=1"
							height="full-400" text="未获取到数据！" limits="10,50,100,500">
							<tr>
								<smart:tableItem field="name" width="150" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="cardNo" width="170" sort="false">身份证</smart:tableItem>
								<smart:tableItem field="draftUnitName" width="150" sort="true">录用单位</smart:tableItem>
								<smart:tableItem field="draftDeptName" width="150" sort="true">录用部门</smart:tableItem>
								<smart:tableItem field="yearTip" width="150" sort="true">年度</smart:tableItem>
								<smart:tableItem field="sex" width="150" sort="false">性别</smart:tableItem>
								<smart:tableItem field="servantType" width="100" sort="true">人员类型</smart:tableItem>
								<smart:tableItem field="employComment" width="200" sort="false">录用鉴定评语</smart:tableItem>
								<smart:tableItem field="employSituation" width="80" sort="false">录用情况</smart:tableItem>
								<smart:tableItem field="employResult" width="80" sort="false">录用标识</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true"
									width="150" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
								<c:if test="${dsr.isAgree==1}">
									<smart:tableToolBtn theme="normal" event="download" title="下载电子介绍信">
										<smart:icon icon="download"></smart:icon>
									</smart:tableToolBtn>
								</c:if>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>


				<smart:gridRow>
					<smart:title title="参公汇总表" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="参公总人数"
							infovalue="${dsr.totalCg}人"></smart:infoShowerLabel>
					</smart:gridColumn>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="参公310开头总人数"
							infovalue="${dsr.totalCg310}人"></smart:infoShowerLabel>
					</smart:gridColumn>
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList2"
							url="ofcflow/draftServantSummary/reportPage?ids=${ids}&servantType=2"
							height="full-400" text="未获取到数据！" limits="10,50,100,500">
							<tr>
								<smart:tableItem field="name" width="150" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="cardNo" width="170" sort="false">身份证</smart:tableItem>
								<smart:tableItem field="draftUnitName" width="150" sort="true">录用单位</smart:tableItem>
								<smart:tableItem field="draftDeptName" width="150" sort="true">录用部门</smart:tableItem>
								<smart:tableItem field="yearTip" width="150" sort="true">年度</smart:tableItem>
								<smart:tableItem field="sex" width="150" sort="false">性别</smart:tableItem>
								<smart:tableItem field="servantType" width="100" sort="true">人员类型</smart:tableItem>
								<smart:tableItem field="employComment" width="200" sort="false">录用鉴定评语</smart:tableItem>
								<smart:tableItem field="employSituation" width="80" sort="false">录用情况</smart:tableItem>
								<smart:tableItem field="employResult" width="80" sort="false">录用标识</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true"
									width="150" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
								<c:if test="${dsr.isAgree==1}">
									<smart:tableToolBtn theme="normal" event="download" title="下载电子介绍信">
										<smart:icon icon="download"></smart:icon>
									</smart:tableToolBtn>
								</c:if>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>

				<smart:gridRow>
					<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<smart:gridRow>
					<smart:form id="editForm">
						<smart:textInput type="hidden" name="type"></smart:textInput>

						<%-- <smart:gridColumn colPart="12">
							<smart:fileUpload accept="file" exts="xls|xlsx|doc|docx" auto="false" buttonName="请示或函" dataName="askForFilePath" name="askForFile" size="20000"/>
							<smart:fileUpload accept="file" exts="xls|xlsx|doc|docx" auto="false" buttonName="审核报告" dataName="repostFilePath" name="repostFile" size="20000"/>
						</smart:gridColumn>--%>
						<smart:gridColumn colPart="6">


						</smart:gridColumn>

						<smart:gridColumn colPart="6">
							<div style="width: 100%;">
								<label class="layui-form-label formLabel labelwp"> 公务员请示文件 ： </label> 
								<smart:button size="sm" method="downloadgwy1" title="公务员请示文件下载">
										下载
								</smart:button>
							</div>
						</smart:gridColumn>

						<smart:gridColumn colPart="6">
								<label class="layui-form-label formLabel labelwp"> 参公请示文件 ： </label> 
								 <smart:button size="sm" method="downloadcg1" title="公务员请示文件下载">
										下载
								</smart:button>
						</smart:gridColumn>

						<smart:gridColumn colPart="6">
							<div style="width: 100%;">
								<label class="layui-form-label formLabel labelwp">公务员审核文件 ： </label>  
									<smart:button
										size="sm" method="downloadgwy2" title="公务员请示文件下载">
										下载
									</smart:button>
							</div>
						</smart:gridColumn>

						<smart:gridColumn colPart="6">
							<div style="width: 100%;">
								<label class="layui-form-label formLabel labelwp">参公审核文件 ： </label> 
									 <smart:button
										size="sm" method="downloadcg2" title="公务员请示文件下载">
										下载
									</smart:button>
							</div>
						</smart:gridColumn>
						<smart:gridColumn colPart="6">
							<div style="width: 100%;">
								 <label class="layui-form-label formLabel labelwp">公务员非310开头人员:</label>
								  <smart:button
										size="sm" method="downloadgwy3" title="公务员请示文件下载">
										下载
									</smart:button>
								
							</div>
						</smart:gridColumn>

						<smart:gridColumn colPart="6">
							<div style="width: 100%;">
								<label class="layui-form-label formLabel labelwp">参公非“310”开头人员:
								</label> 
								<smart:button
									size="sm" method="downloadcg3" title="公务员请示文件下载">
									下载
								</smart:button>
								
							</div>
						</smart:gridColumn>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="签发单位名称"
								infovalue="${dsr.unitName}"></smart:infoShowerLabel>
						</smart:gridColumn>

						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="编号"
								infovalue="${dsr.serialNumber}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="6">
							<smart:infoShowerLabel infoname="签发人" infovalue="${dsr.signer}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:form>
				</smart:gridRow>
				<smart:gridRow>
					<smart:line color="blue" />
					<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
						<smart:buttonGroup container="true">
						
							<c:if test="${empty dsr.isAgree}">
								<smart:button theme="normal" size="sm" method="agree" title="市局批复同意">
									<smart:icon icon="check">市局批复同意</smart:icon>
								</smart:button>
							</c:if>
								
							<smart:button theme="warm" size="sm" method="back" title="返回">
								<smart:icon icon="reply">&nbsp;返回</smart:icon>
							</smart:button>

						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,upload">
		<smart:utils />
		<smart:fileUploadUtils />
		<smart:tableScriptAction tableId="navigationList1" checkbox="true"
			sort="true" rowEdit="true">
			view : function(data) {
				smart.show({
					title : '信息表',
					size : 'full',
					url : "ofcflow/draftServantSummary/draftServantReportView?id="+data.data.id,
					scrollbar : false
				});
			},
			download : function(data) {
				smart.show({
					title : '信息表',
					size : 'full',
					url : "ofcflow/draftServant/employInfoEditPage?id="+data.data.id,
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:tableScriptAction tableId="navigationList2" checkbox="true"
			sort="true" rowEdit="true">
			view : function(data) {
				smart.show({
					title : '信息表',
					size : 'full',
					url : "ofcflow/draftServantSummary/draftServantReportView?id="+data.data.id,
					scrollbar : false
				});
			},
			download : function(data) {
				smart.show({
					title : '信息表',
					size : 'full',
					url : "ofcflow/draftServant/employInfoEditPage?id="+data.data.id,
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			agree : function() {
				smart.confirm({
					title:'确认提交',
					message:'确认要提交吗？',
					type:'POST',
					url:'ofcflow/draftServantReportResult/agreeSummary?id=${dsr.id}',
					callback : function() {
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			},
			download:function(fileName,fileShowName){
							//通过form下载
	            var $eleForm = $("<form method='post'></form>");
	 
	            $eleForm.attr("action","ofcflow/draftServantSummary/downloadDraftServantFile?fileName="+fileName);
	 
	            $(document.body).append($eleForm);
	 
	            //提交表单，实现下载
	            $eleForm.submit();
	         },
			downloadgwy1:function(){
				//通过form下载
	            var $eleForm = $("<form method='post'></form>");
	 
	            $eleForm.attr("action","ofcflow/draftServantSummary/downloadDraftServantFile?fileName=${dsr.askForFilePathGWY}&fileShowName=公务员报市局请示.doc");
	 
	            $(document.body).append($eleForm);
	 
	            //提交表单，实现下载
	            $eleForm.submit();
			},
			downloadgwy2:function(){
				//通过form下载
	            var $eleForm = $("<form method='post'></form>");
	 
	            $eleForm.attr("action","ofcflow/draftServantSummary/downloadDraftServantFile?fileName=${dsr.repostFilePathGWY}&fileShowName=单位审核报告（公务员）.doc");
	 
	            $(document.body).append($eleForm);
	 
	            //提交表单，实现下载
	            $eleForm.submit();
			},
			downloadgwy3:function(){
				//通过form下载
	            var $eleForm = $("<form method='post'></form>");
	 
	            $eleForm.attr("action","ofcflow/draftServantSummary/downloadDraftServantFile?fileName=${dsr.no310FilePathGWY}&fileShowName=公务员单位审核情况汇总表.xls");
	 
	            $(document.body).append($eleForm);
	 
	            //提交表单，实现下载
	            $eleForm.submit();
			},
			downloadcg1:function(){
				//通过form下载
	            var $eleForm = $("<form method='post'></form>");
	 
	            $eleForm.attr("action","ofcflow/draftServantSummary/downloadDraftServantFile?fileName=${dsr.askForFilePathCG}&fileShowName=参公报市局请示.doc");
	 
	            $(document.body).append($eleForm);
	 
	            //提交表单，实现下载
	            $eleForm.submit();
			},
			downloadcg2:function(){
				//通过form下载
	            var $eleForm = $("<form method='post'></form>");
	 
	            $eleForm.attr("action","ofcflow/draftServantSummary/downloadDraftServantFile?fileName=${dsr.repostFilePathCG}&fileShowName=单位审核报告（参公）.doc");
	 
	            $(document.body).append($eleForm);
	 
	            //提交表单，实现下载
	            $eleForm.submit();
			},
			downloadcg3:function(){
				//通过form下载
	            var $eleForm = $("<form method='post'></form>");
	 
	            $eleForm.attr("action","ofcflow/draftServantSummary/downloadDraftServantFile?fileName=${dsr.no310FilePathCG}&fileShowName=参公单位审核情况汇总表.xls");
	 
	            $(document.body).append($eleForm);
	 
	            //提交表单，实现下载
	            $eleForm.submit();
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>