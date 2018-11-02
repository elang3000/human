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
					<smart:breadcrumbNavMenuItem iname="选调交流"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="新增选调交流" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:gridRow>
						<smart:textInput name="id" value="${t.id}" type="hidden"></smart:textInput>
						<smart:textInput name="recruitOrgan.id" value="${t.recruitOrgan.id}" type="hidden"></smart:textInput>
						<smart:textInput name="employOrgan.id" value="${t.employOrgan.id}" type="hidden"></smart:textInput>
						<smart:textInput name="allowWeaveNum" value="${t.allowWeaveNum}" type="hidden"></smart:textInput>
						<smart:textInput name="realNum" value="${t.realNum}" type="hidden"></smart:textInput>
						<smart:textInput name="thisYearLackWeaveNum" value="${t.thisYearLackWeaveNum}" type="hidden"></smart:textInput>
						<smart:gridRow>
							<smart:title title="新增选调交流" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="选调机构" infovalue="${t.recruitOrgan.allName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="用人机构" infovalue="${t.employOrgan.allName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构核定人数" infovalue="${t.allowWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构实有人数" infovalue="${t.realNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构年度缺编人数" infovalue="${t.thisYearLackWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block" other="readonly" labelName="编制类型" initSelectedKey="${t.recuritType.id}" name="recuritType.id"
									data="${recuritType}"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:numberInput labelName="计划选调人数" isNotNull="true" display="block" name="planEmployNum" value="${t.planEmployNum}" min="1" verify="required" autocomplete="on"></smart:numberInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:numberInput labelName="计划减员人数" isNotNull="true" display="block" name="planCutNum" value="${t.planCutNum}" min="0" verify="required" autocomplete="on"></smart:numberInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="联系人" placeholder="联系人" isNotNull="true" verify="required" name="contacter" value="${t.contacter}"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="联系电话" placeholder="联系电话" isNotNull="true" verify="required" name="contactPhone" value="${t.contactPhone}"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="咨询电话" placeholder="咨询电话" name="consultPhone" value="${t.consultPhone}"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="通讯地址" placeholder="通讯地址" name="contactAddress" value="${t.contactAddress}"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea name="remark" display="block" labelName="备注">${t.remark}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
							<smart:buttonGroup container="true">
								<smart:button size="sm" id="save" other="lay-submit" title="提交">
									<smart:icon icon="plus">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="back" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils/>
		<smart:buttonScriptAction>
			back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
		 form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'保存选调计划信息',
				message:'确认提交保存吗？',
				url:'ofcflow/transferring/save',
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