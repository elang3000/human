<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
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
					<smart:breadcrumbNavMenuItem iname="选调交流"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="管理选调交流职位" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:gridRow>
						<smart:textInput type="hidden" name="plan.id" value="${t.id}"></smart:textInput>
						<smart:textInput type="hidden" name="plan.planEmployNum" value="${t.planEmployNum}"></smart:textInput>
						<smart:textInput type="hidden" name="id" value="${p.id}"></smart:textInput>
						<smart:gridRow>
							<smart:title title="管理选调职位" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="用人机构" infovalue="${t.employOrgan.allName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构核定人数" infovalue="${t.allowWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构实有人数" infovalue="${t.realNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构年度缺编人数" infovalue="${t.thisYearLackWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="计划选调人数" infovalue="${t.planEmployNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="计划减员人数" infovalue="${t.planCutNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput name="name" value="${p.name}" labelName="职位名称" placeholder="职位名称" isNotNull="true" verify="required" ></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block" labelName="职位类别" isAddDefaltOption="true" initSelectedKey="${p.postType.id}" name="postType.id" url="dictquery/sub/code/0137"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:numberInput labelName="选调人数" isNotNull="true" display="block" name="planEmployNum" value="${p.planEmployNum}" min="1" verify="required" autocomplete="on"></smart:numberInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textInput name="description" value="${p.description}" display="block" labelName="职位简介" placeholder="职位简介"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block" labelName="政治面貌" isAddDefaltOption="true" initSelectedKey="${p.politicalStatus.id}" name="politicalStatus.id" url="dictquery/sub/code/GBT_4762_1984"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block" labelName="专业类别" isAddDefaltOption="true" initSelectedKey="${p.professional.id}" name="professional.id" url="dictquery/sub/code/EMPLOY_PRO_TYPE"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block" labelName="基层工作最低年限" isAddDefaltOption="true" initSelectedKey="${p.workYear.id}" name="workYear.id" url="dictquery/sub/code/EMPLOY_WORK_YEARS"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:continuousSelect labelName="本科阶段专业" inputName="undergraduate.id" codeTypeCode="GBT_16835_1997" inputVal="${p.undergraduate.id}" valType="ID" widthPercent="0.333"/>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:continuousSelect labelName="研究生阶段专业" inputName="graduate.id" codeTypeCode="GBT_16835_1997" inputVal="${p.graduate.id}" valType="ID" widthPercent="0.333"/>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block" labelName="最低学历要求" isAddDefaltOption="true" initSelectedKey="${p.education.id}" name="education.id" url="dictquery/sub/code/GBT_4658_2006"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block" labelName="最低学位要求" isAddDefaltOption="true" initSelectedKey="${p.degree.id}" name="degree.id" url="dictquery/sub/code/GBT_6864_2003"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea labelName="其他要求" name="other" display="block" placeholder="其他要求">${p.other}</smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea labelName="备注" name="remark" display="block" placeholder="备注">${p.remark}</smart:textarea>
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
	<smart:scriptHead models="table,form,layer,element,linkSelect">
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('.layui-form'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
			,back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
		 form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'保存职位信息',
				message:'确认提交保存吗？',
				url:'ofcflow/transferring/postSave',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('postNavigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>