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
					<smart:breadcrumbNavMenuItem iname="职位简章"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="管理招录职位" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="addForm">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="管理招录职位" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="招录机构" infovalue="${ep.recruitOrgan.name}"></smart:infoShowerLabel>
								<smart:textInput type="hidden" name="plan.id" value="${ep.id}"></smart:textInput>
								<smart:textInput type="hidden" id="id" name="id" value="${post.id}"></smart:textInput>
								<smart:textInput type="hidden" name="plan.endEmployNum" value="${ep.endEmployNum}"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="用人机构" infovalue="${ep.employOrgan.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="终审招录人数" infovalue="${ep.endEmployNum }"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="职位名称" isNotNull="true" verify="required" name="name" value="${post.name }" placeholder="职位名称"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="招录人数" isNotNull="true" verify="required" name="planEmployNum" value="${post.planEmployNum }" placeholder="招录人数 "></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="专业考试科目" isNotNull="true" verify="required" name="majorSubject" value="${post.majorSubject }" placeholder="专业考试科目"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block"
									labelName="学历要求" initSelectedKey="${post.education.id }" 
									name="education.id"
									url="dictquery/sub/code/GBT_4658_2006"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block"
									labelName="学位要求" initSelectedKey="${post.degree.id }" 
									name="degree.id"
									url="dictquery/sub/code/GBT_6864_2003"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block"
									labelName="政治面貌" initSelectedKey="${post.politicalStatus.id }"  name="politicalStatus.id"
									 url="dictquery/sub/code/GBT_4762_1984"></smart:singleSelect>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:singleSelect display="block"
									labelName="最低工作年限" initSelectedKey="${post.workYear.id }" 
									name="workYear.id"
									url="dictquery/sub/code/EMPLOY_WORK_YEARS"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="职位简介" isNotNull="true" verify="required" name="description" value="${post.description }" placeholder="职位简介"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="其他条件" value="${post.other }" name="other" placeholder="其他要求"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:continuousSelect labelName="本科阶段专业" inputName="undergraduateCourse.id" widthPercent="24.2%"
									codeTypeCode="GBT_16835_1997" inputVal="${post.undergraduateCourse.id }" valType="ID"
									verify="required" />
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:continuousSelect labelName="研究生阶段专业" widthPercent="24.2%" inputName="graduateCourse.id"
									codeTypeCode="GBT_16835_1997" inputVal="${post.graduateCourse.id }" valType="ID"
									 />
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm" title="提交"
									theme="normal">
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
	<smart:utils />
	<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
		 form.on('submit(save)', function(data) {
			smart.confirm({
				title:'保存职位信息',
				message:'确认提交保存吗？',
				url:'ofcflow/recruit/employPlan/savePost',
				params : smart.json("#addForm"),
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