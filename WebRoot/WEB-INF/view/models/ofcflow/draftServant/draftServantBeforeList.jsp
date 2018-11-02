<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请--导入记录查看" />
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
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="3">
									<smart:date labelName="年份" display="block" id="year" name="yearTip" placeholder="年份"></smart:date>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="姓名" name="name" display="block"
										placeholder="姓名"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="身份证" name="cardNo" display="block"
										placeholder="身份证"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:buttonGroup container="true">
										<smart:button theme="normal" size="sm" method="search"
											title="查询">
											<smart:icon icon="search"></smart:icon>
										</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>

					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList1" url="ofcflow/draftServant/draftServantBeforePage?id=${importId}"
							height="full-215" text="未获取到数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="yearTip" width="100" sort="true">年度</smart:tableItem>
								<smart:tableItem field="draftUnitName" width="200" sort="true">录用单位</smart:tableItem>
								<smart:tableItem field="draftDeptName" width="200" sort="true">录用部门</smart:tableItem>
								<smart:tableItem field="name" width="100" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="sex" width="100" sort="false">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width="200" sort="false">身份证</smart:tableItem>
								<smart:tableItem field="aptitudeTestScore" width="150" sort="false">专业能力测试</smart:tableItem>
								<smart:tableItem field="publicSubjectTestScore" width="150" sort="false">公共科目成绩</smart:tableItem>
								<smart:tableItem field="professionalSubjectScore" width="150" sort="false">专业科目成绩</smart:tableItem>
								<smart:tableItem field="otherSubjectScore" width="150" sort="false">其他科目成绩</smart:tableItem>
								<smart:tableItem field="interviewScore" width="100" sort="false">面试</smart:tableItem>
								<smart:tableItem field="politicalExam" width="100" sort="false">政审考核</smart:tableItem>
								<smart:tableItem align="center" width="100" fixed="right" unresize="true" toolbar="navListToolBar1">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar1">
								<smart:tableToolBtn theme="warm" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils/>
		<smart:tableScriptAction tableId="navigationList1" checkbox="true"
			sort="true" rowEdit="true">
			view : function(data) {
				smart.show({
					title : '信息表',
					size : 'full',
					url : "ofcflow/draftServant/draftServantView?id="+data.data.id,
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('.layui-form'));
				table.reload('navigationList1', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		 </smart:buttonScriptAction>
		<smart:dateRender type="year" id="year" />
	</smart:scriptHead>
</smart:body>
</html>