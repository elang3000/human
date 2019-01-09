<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
					<smart:breadcrumbNavMenuItem iname="职位简章" cite="true"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="招录职位" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:form id="editForm">
						<smart:fromTokenTag/>
						<smart:gridRow>
							<smart:title title="用人机构编制信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<%@include file="../zhuanRenB/formation.jsp" %>
						<smart:gridRow>
							<smart:title title="招录职位" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="年度计划" infovalue="${recruityearplan.name}"></smart:infoShowerLabel>
								<smart:textInput type="hidden" id="id" name="id"  value="${d.id}" ></smart:textInput>
								<smart:textInput type="hidden" name="result" id="result"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="起止时间" infovalue="${recruityearplan.startDate}至${recruityearplan.endDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="招录机构" infovalue="${d.recruitOrgan.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="用人机构" infovalue="${d.employOrgan.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机关类别" infovalue="${d.recuritType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="计划招录人数" infovalue="${d.planEmployNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel red="true" infoname="终审招录人数" infovalue="${d.endEmployNum }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel shortName="计划减员人数" infoname="机构计划减员人数" infovalue="${d.planCutNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="人员类别" infovalue="${d.personType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="联系人" infovalue="${d.contacter}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="联系电话" infovalue="${d.contactPhone}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="通讯地址" infovalue="${d.contactAddress}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="咨询电话" infovalue="${d.consultPhone}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:infoShowerLabel infoname="备注" infovalue="${d.remark}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<c:if test="${d.planState>8&&isFlow}">
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:textarea name="opinion" id="opinion" labelName="审批意见" display="block"></smart:textarea>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
					</smart:form>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList"
							url="ofcflow/recruit/employPlan/postlist?planId=${d.id}" 
							 text="未找到有效数据！">
							<tr>
								<smart:tableItem field="name" width=".15" sort="true">职位名称</smart:tableItem>
								<smart:tableItem field="planEmployNum" width=".1" sort="false">招录人数</smart:tableItem>
								<smart:tableItem field="politicalStatus" width=".1" sort="false">政治面貌</smart:tableItem>
								<smart:tableItem field="workYear" width=".1" sort="false">最低工作年限</smart:tableItem>
								<smart:tableItem field="education" width=".1" sort="false">学历要求</smart:tableItem>
								<smart:tableItem field="degree" width=".1" sort="false">学位要求</smart:tableItem>
								<smart:tableItem field="majorSubject" width=".2" sort="false">专业考试科目</smart:tableItem>
								<smart:tableItem align="" width=".15" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<c:if test="${d.planState==8}">
									<smart:tableToolBtn theme="warm" event="edit" title="编辑">
										<smart:icon icon="edit"></smart:icon>
									</smart:tableToolBtn>
									<smart:tableToolBtn theme="danger" event="delete" title="删除">
										<smart:icon icon="trash"></smart:icon>
									</smart:tableToolBtn>
								</c:if>
								<smart:tableToolBtn theme="warm" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>

				<smart:gridRow>
					<smart:line color="blue" />
					<smart:gridColumn colPart="5" deviceType="md" colOffset="3">
						<smart:buttonGroup container="true">
							<c:if test="${d.planState==8}">
								<smart:button size="sm" method="add" title="新增职位">
									<smart:icon icon="plus">&nbsp;新增职位</smart:icon>
								</smart:button>
								<smart:button size="sm" method="confirmEmployPlan" theme="normal" 
									title="招录计划确认 ">
									<smart:icon icon="check">&nbsp;招录计划确认 </smart:icon>
								</smart:button>
							</c:if>
							<c:if test="${d.planState>8&&isFlow}">
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
							<smart:button theme="primary" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/recruit/employPlan/deletepost',
					params:{postId:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			},
			edit : function(data) {
				smart.show({
					title : '编辑职位信息',
					size : 'full',
					url : "ofcflow/recruit/addPost",
					params:{postId:data.data.id,id:'${d.id}'},
					scrollbar : false
				});
			}
			,view : function(data) {
				smart.show({
					title : '查看职位信息',
					size : 'full',
					url : "ofcflow/recruit/viewPost",
					params:{postId:data.data.id,id:'${d.id}'},
					scrollbar : false
				});
			}
			,reload : function(){
				table.reload('navigationList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
		pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/recruit/operationPost',
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
					message:'确认审批驳回至上一办理人员？',
					url:'ofcflow/recruit/operationPost',
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
					message:'确认审批不通过，结束业务办理？',
					url:'ofcflow/recruit/operationPost',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			add : function() {
				smart.show({
					title : '新增职位信息',
					size : 'full',
					url : "ofcflow/recruit/addPost",
					params:{id:'${d.id}'},
					scrollbar : false
				});
			},
			confirmEmployPlan : function() {
				smart.confirm({
					title:'提示',
					message:'您确认上报至区人事主管部门？',
					url:'ofcflow/recruit/employPlan/confirm',
					params:{id:'${d.id}'},
					callback:function () {
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