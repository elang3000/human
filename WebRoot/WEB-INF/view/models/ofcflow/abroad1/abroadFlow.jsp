<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="因公出国政审"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:grid>
							<smart:gridRow>
							<smart:title title="因公出国计划" style="margin-top: 5px;" color="blue" />
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="出境团组名称" infovalue="${abroadYearPlan.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="出境地区" infovalue="${abroadYearPlan.country}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="停留天数" infovalue="${abroadYearPlan.day}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							
							<smart:gridRow>
									<smart:title title="因公出国人员列表" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>
								
								<smart:gridRow colSpace="5">
									<smart:gridColumn>
										<smart:table id="navigationList"
											url="ofcflow/abroadB/abroadPeopleList?abroadId=${abroadServant.id}&abroadYearId=${abroadYearPlan.id}"
											height="" text="未找到用户数据！" page="true" doneCallBack="fixedCol">
											<tr>
												<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
												<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
												<smart:tableItem field="cardNo" width=".15" sort="true">身份证号</smart:tableItem>
												<smart:tableItem field="isTime" width=".15" sort="false">是否其他批件时间内</smart:tableItem>
												<smart:tableItem field="departName" width=".12" sort="false">单位部门</smart:tableItem>
												<smart:tableItem field="postName" width=".12" sort="false">职务名称</smart:tableItem>
												<smart:tableItem field="postAttributeName" width=".12" sort="false">职务属性</smart:tableItem>
												<smart:tableItem field="jobLevel" width=".12" sort="false">职级名称</smart:tableItem>
												<smart:tableItem align="center" width=".1" fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
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
									<smart:gridColumn colPart="1">
										<smart:fileUpload accept="file" isView="true" buttonName="附件" dataName="abroadFtp" auto="false" name="filePathName" size="20000" multiple="true" number="7" data="${abroadServant.abroadFtp}"/>
									</smart:gridColumn>
								</smart:gridRow>
						</smart:grid>
					</smart:gridColumn>
				</smart:gridRow>
				
				<smart:form id="editForm">
				<smart:fromTokenTag/>
				
					<smart:gridRow>
						<smart:textInput type="hidden" name="id"  value="${abroadServant.id}" ></smart:textInput>
						<smart:textInput type="hidden" id="result" name="result" ></smart:textInput>
						<c:if test="${abroadServant.status<=7}">
							<smart:gridColumn colPart="12">
								<smart:textarea labelName="审批意见:" id="opinion" name="opinion" display="block"></smart:textarea>
							</smart:gridColumn>
						</c:if>
					</smart:gridRow>
						
					<smart:gridRow>
					<smart:line color="blue" />
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
						   <c:if test="${abroadServant.status<=7}">
						   		<smart:button method="pass" size="sm" title="审批通过" theme="normal">
									<smart:icon icon="check">&nbsp;审批通过</smart:icon>
								</smart:button>
								<smart:button method="noPass" size="sm" title="审批驳回"
									theme="warm">
									<smart:icon icon="refresh">&nbsp;审批驳回</smart:icon>
								</smart:button>
								<smart:button method="stopPass" size="sm" title="审批不通过"
									theme="danger">
									<smart:icon icon="minus-circle">&nbsp;审批不通过</smart:icon>
								</smart:button>
								</c:if>
								<c:if test="${abroadServant.status==8}">
									<smart:button method="pass" size="sm" title="确认"
										theme="normal">
										<smart:icon icon="check">&nbsp;确认</smart:icon>
									</smart:button>
							</c:if>
						    <smart:button theme="primary" size="sm" method="goBack" title="返回">
								<smart:icon icon="reply">&nbsp;返回</smart:icon>
							</smart:button>
						 </smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,upload">
		<smart:utils/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				view : function(data) {
					smart.show({
						title : '公务员信息',
						size : 'full',
						url : 'ofc/main/view?id='+data.data.id,
						scrollbar : false,
					});
				}
		</smart:tableScriptAction>
		<smart:fileUploadUtils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/abroadB/operationFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			noPass : function() {
				$("#result").val("0");//审批驳回
				if(!$("#opinion").val()){
					smart.message({
						message : "请输入审批驳回意见！",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
				}
				smart.confirm({
					title:'确认审批驳回',
					message:'确认审批驳回吗？',
					url:'ofcflow/abroadB/operationFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			stopPass : function() {
				$("#result").val("-1");//审批不通过
				if(!$("#opinion").val()){
					smart.message({
						message : "请输入审批不通过意见！",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
				}
				smart.confirm({
					title:'确认审批不通过',
					message:'确认审批不通过吗？',
					url:'ofcflow/abroadB/operationFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		
	</smart:scriptHead>
</smart:body>
</html>