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
			<smart:cardBody>
				<smart:form id="editForm" action="ofcflow/resignB/operationFlow">
					<smart:fromTokenTag />
					<smart:gridRow>
						<smart:textInput type="hidden" id="id" name="id"
							value="${resignPlan.id}"></smart:textInput>
						<smart:title title="辞职信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>

					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批次名"
								infovalue="${resignPlan.resignName}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="批次人数"
								infovalue="${resignPlan.resignNumber}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="辞职日期"
								infovalue="${resignPlan.resignDate}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:title title="辞职人员列表" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:table id="navigationList"
								url="ofcflow/resignB/resignPeopleList?planId=${resignPlan.id}"
								height="" text="未找到用户数据！" page="true" doneCallBack="fixedCol">
								<tr>
									<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
									<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
									<smart:tableItem field="cardNo" width=".2" sort="true">身份证号</smart:tableItem>
									<smart:tableItem field="departName" width=".17" sort="false">单位部门</smart:tableItem>
									<smart:tableItem field="reason" width=".17" sort="false">辞职原因</smart:tableItem>
									<smart:tableItem field="resignWhereabouts" width=".16"
										sort="false">辞职去向</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true"
										toolbar="navListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="navListToolBar">
									<smart:tableToolBtn theme="warm" event="view" title="详情">
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
							<smart:fileUpload accept="file" buttonName="附件"
								dataName="resignFtp" auto="false" name="filePathName"
								size="20000" multiple="true" number="7"
								data="${resignPlan.resignFtp}" isView="true" />
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
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
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				view : function(data) {
					smart.show({
						title : '公务员信息',
						size : 'full',
						url : 'ofcflow/resignB/resignDetail?id='+data.data.id,
						scrollbar : false,
					});
				}
		</smart:tableScriptAction>
		<smart:fileUploadUtils />
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="resignDate" />
	</smart:scriptHead>
</smart:body>
</html>