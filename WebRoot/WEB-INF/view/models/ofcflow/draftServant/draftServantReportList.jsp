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
					<smart:breadcrumbNavMenuItem iname="公务员录用" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
			<smart:gridRow>
					<smart:title title="汇总信息" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
						<smart:gridRow colSpace="5">
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="总共汇总人数"
											infovalue="${total}人"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="总共310开头总人数"
											infovalue="${total310}人"></smart:infoShowerLabel>
									</smart:gridColumn>
									</smart:gridRow>
				<smart:gridRow>
					<smart:title title="公务员汇总表" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
						<smart:gridRow colSpace="5">
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="公务员总人数"
											infovalue="${total_gwy}人"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="公务员310开头总人数"
											infovalue="${total_gwy310}人"></smart:infoShowerLabel>
									</smart:gridColumn>
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/draftServantSummary/reportPage?ids=${ids}&servantType=1"
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
								<smart:tableItem align="center" fixed="right" unresize="true" width="150" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
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
											infovalue="${total_cg}人"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="参公310开头总人数"
											infovalue="${total_cg310}人"></smart:infoShowerLabel>
									</smart:gridColumn>
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/draftServantSummary/reportPage?ids=${ids}&servantType=2"
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
								<smart:tableItem align="center" fixed="right" unresize="true" width="150" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
				
				<smart:gridRow>
					<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<smart:gridRow>
					<smart:form id="editForm">
					<smart:textInput type="hidden" value="${ids}" name="ids"></smart:textInput>
						<smart:textInput type="hidden" value="${total}" name="total"></smart:textInput>
						<smart:textInput type="hidden" value="${total310}" name="total310"></smart:textInput>
						<smart:textInput type="hidden" value="${total_gwy}" name="totalGwy"></smart:textInput>
						<smart:textInput type="hidden" value="${total_gwy310}" name="totalGwy310"></smart:textInput>
						<smart:textInput type="hidden" value="${total_cg}" name="totalCg"></smart:textInput>
						<smart:textInput type="hidden" value="${total_cg310}" name="totalCg310"></smart:textInput>
						<smart:textInput type="hidden" value="${cgExpName}" name="cgExpName"></smart:textInput>
						<smart:textInput type="hidden" value="${gwyExpName}" name="gwyExpName"></smart:textInput>
						<smart:textInput type="hidden" name="type"></smart:textInput>
<%-- 						<smart:gridColumn colPart="5">
							<smart:textInput labelName="请示文件标题" name="requestFileName" ></smart:textInput>
						</smart:gridColumn> --%>
						<smart:gridColumn colPart="5" colOffset="1">
							<smart:textInput labelName="单位名称" isNotNull="true" verify="required" name="unitName" ></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="5">
							<smart:textInput labelName="编号" isNotNull="true" verify="required" name="serialNumber" ></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="5" colOffset="1">
							<smart:textInput labelName="签发人" isNotNull="true" verify="required" name="signer" ></smart:textInput>
						</smart:gridColumn>
						<%-- <smart:gridColumn colPart="12">
							<smart:fileUpload accept="file" exts="xls|xlsx|doc|docx" auto="false" buttonName="请示或函" dataName="askForFilePath" name="askForFile" size="20000"/>
							<smart:fileUpload accept="file" exts="xls|xlsx|doc|docx" auto="false" buttonName="审核报告" dataName="repostFilePath" name="repostFile" size="20000"/>
						</smart:gridColumn>--%>
										<smart:gridRow>
					<smart:line color="blue" />
					<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
						<smart:buttonGroup container="true">
<%-- 							<smart:button size="sm" method="temporary" title="暂存">
								<smart:icon icon="plus">&nbsp;暂存</smart:icon>
							</smart:button>
 --%>							<smart:button id="save" other="lay-submit" size="sm" method="add" title="提交">
								<smart:icon icon="plus">&nbsp;提交</smart:icon>
							</smart:button>
							<smart:button theme="warm" size="sm" method="back" title="返回">
								<smart:icon icon="pencil">&nbsp;返回</smart:icon>
							</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
					</smart:form>
				</smart:gridRow> 

			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,upload">
		<smart:utils/>
		<smart:fileUploadUtils/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			view : function(data) {
				smart.show({
					title : '信息表',
					size : 'full',
					url : "ofcflow/draftServantSummary/draftServantReportView?id="+data.data.id,
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			temporary : function() {
				$("#editForm input[name='type']").val("0");//暂存
				smart.confirm({
					title:'暂存上报信息',
					message:'确认暂存上报信息吗？',
					url:'ofcflow/draftServantSummary/saveReport',
					params : smart.json("#editForm"),
					callback : function(){
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
 		form.on('submit(save)', function (data) {//表单保存
		
				$("#editForm input[name='type']").val("1");//提交
					smart.confirm({
						title:'提交上报信息',
						message:'确认提交上报信息吗？',
						url:'ofcflow/draftServantSummary/saveReport',
						params : smart.json("#editForm"),
						callback : function(){
							parent.layui.table.reload('navigationList');
							var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						}
				});
		});
	</smart:scriptHead>
</smart:body>
</html>