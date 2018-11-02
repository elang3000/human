<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区公务员信息管理系统--上报考核嘉奖名单" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>

					<smart:form id="editForm"
						action="#">
						<smart:fromTokenTag />
						<smart:fieldSet title="发起考核" style="margin-top: 5px;" color="blue">

							<smart:gridRow>
								<smart:textInput type="hidden" name="id" value="${percent.id}">
								</smart:textInput>
								<smart:textInput type="hidden" name="result" id="result"></smart:textInput>
								<smart:textInput type="hidden" name="isSubmit" id="isSubmit"></smart:textInput>

								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="考核年份"
										infovalue="${collect.year}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="拟定优秀比例"
										infovalue="${collect.draftOutstandingPercent }%"></smart:infoShowerLabel>
								</smart:gridColumn>

							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="人社局备注"
										infovalue="${collect.remark }"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="申请优秀人数"
										infovalue="${percent.outstandingNumb }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="备注"
										infovalue="${percent.remark }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
							<c:if test="${step eq 'STATUS_ASSESS_STEP7' }">
							<smart:gridRow>
										<div class="layui-col-md6">
									<div style="width: 100%;">
										<label class="layui-form-label formLabel"> 上报嘉奖名单 ： </label>
										<div class="layui-input-block">
										<smart:button method="pick" size="sm" title="跳转到选择考核页面,对单位人员进行考核"
											theme="normal">
											<smart:icon icon="user-o">&nbsp;选择</smart:icon>
										</smart:button>
										</div>
									</div>

								</div>
							</smart:gridRow>
							</c:if>
											<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table  id="navigationList"
							url="ofcflow/assess/unitCheckPageList?assessCollectId=${collectId }&orgId=${percent.orgNode.id }&id=${fineCodeInfoId }"
							height="full-415" limits="10,100,300,1000" text="未找到有效数据！">
							<tr>
								<smart:tableItem field="name" width="200" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="orgName" width="300" sort="true">单位</smart:tableItem>
								<smart:tableItem field="cardNo" width="200" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="result" width="200" sort="false">审核结果</smart:tableItem>
								<smart:tableItem field="remarks" width="500" sort="false">备注</smart:tableItem>
								<smart:tableItem align="center" width="200"  fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="default" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
					
				</smart:gridRow>
				<smart:gridRow>
				<c:if test="${step != 'STATUS_ASSESS_STEP7' }">
								<smart:gridColumn colPart="6">
									<smart:textarea labelName="审批意见:" display="block" name="opinion" placeholder="审批意见"></smart:textarea>
								</smart:gridColumn>
								</c:if>
							</smart:gridRow>
						</smart:fieldSet>
						<smart:gridRow>
							<smart:gridColumn>
								<smart:buttonGroup container="true">
									<c:if test="${step != 'STATUS_ASSESS_STEP7' }">
										<smart:button method="pass" size="sm" title="审批通过"
											theme="normal">
											<smart:icon icon="check">&nbsp;审批通过</smart:icon>
										</smart:button>
										<smart:button method="noPass" size="sm" title="审批不通过"
											theme="danger">
											<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
										</smart:button>
									</c:if>
									<c:if test="${step == 'STATUS_ASSESS_STEP7' }">
										<smart:button method="submi" size="sm" title="发起年度考核的流程"
											 theme="default">
											<smart:icon icon="plus"></smart:icon>&nbsp;提交
		  				 			</smart:button>
									</c:if>
									<smart:button theme="warm" size="sm" method="goBack" title="返回">
										<smart:icon icon="reply">&nbsp;返回</smart:icon>
									</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
						
						
					</smart:form>

				</smart:gridRow>

			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:dateRender id="assessYear" type="year" />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				view : function(data) {
					smart.show({
					title : '人员审核查看',
					size : 'full',
					url : 'ofcflow/assess/unitCheckPage?view=true&id='+data.data.id,
					scrollbar : false
					});
				}
		</smart:tableScriptAction>
		
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/assess/operateAssessProgress',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			submi : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认提交',
					message:'确认提交吗？',
					url:'ofcflow/assess/operateAssessProgress',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			pick : function() {
					smart.show({
						title : '人员考核,考核优秀的人为嘉奖人员',
						size : 'full',
						url : 'ofcflow/assess/unitCheckIndexPage/${collect.id }',
						scrollbar : false
					});
			},
			noPass : function() {
				$("#result").val("0");//审批不通过
				smart.confirm({
					title:'确认审批不通过',
					message:'确认审批不通过吗？',
					url:'ofcflow/assess/operateAssessProgress',
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