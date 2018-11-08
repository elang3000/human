<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请--录用查看" />
<!-- 录用_录用查看 -->
<script type="text/javascript">
	var tabNum=4;
</script>
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
						<%@include file="modual/draftHeadTab.jsp" %>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="3">
									<smart:date labelName="年份" display="block" name="yearTip" id="year" placeholder="年份"></smart:date>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="姓名" display="block" name="name"
										placeholder="姓名"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="身份证" display="block" name="cardNo"
										placeholder="身份证"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="2" colOffset="1">
									<smart:button theme="normal" size="sm" method="search"
										title="查询">
										<smart:icon icon="search"></smart:icon>
									</smart:button>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>

					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/draftServant/draftServantPage"
							height="full-215" text="未获取到数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="yearTip" width="107" sort="true">年度</smart:tableItem>
								<smart:tableItem field="draftUnitName" width="200" sort="true">录用单位</smart:tableItem>
								<smart:tableItem field="draftDeptName" width="150" sort="true">录用部门</smart:tableItem>
								<smart:tableItem field="name" width="130" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="sex" width="100" sort="false">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width="190" sort="false">身份证</smart:tableItem>
								<smart:tableItem field="servantType" width="150" sort="false">人员类型</smart:tableItem>
								<smart:tableItem field="employComment" width="150" sort="false">录用鉴定评语</smart:tableItem>
								<smart:tableItem field="employSituation" width="100" sort="false">录用情况</smart:tableItem>
								<smart:tableItem field="employResult" width="100" sort="false">录用标识</smart:tableItem>
								<smart:tableItem field="state" width="100" sort="false">录用状态</smart:tableItem>
								<smart:tableItem align="center" width="100"  fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="default" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
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
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			view : function(data) {
				smart.show({
					title : '信息表',
					size : 'full',
					url : "ofcflow/draftServant/draftServantView?id="+data.data.id,
					scrollbar : false
				});
			},
			edit : function(data) {
			   smart.show({
					title : '编辑录用信息',
					size : 'full',
					url : "ofcflow/draftServant/employInfoEditPage?id="+data.data.id,
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
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
		 </smart:buttonScriptAction>
		<smart:dateRender type="year" id="year" />
		$(".layui-tab-title li").removeClass("layui-this");
		$(".layui-tab-title li:eq("+tabNum+")").addClass("layui-this");
	</smart:scriptHead>
</smart:body>
</html>