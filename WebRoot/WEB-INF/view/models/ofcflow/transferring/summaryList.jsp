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
					<smart:breadcrumbNavMenuItem iname="选调交流" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="false"
								turnurl="ofcflow/transferring/index" eId="" itemName="选调交流"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="选调交流汇总"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList"
							url="ofcflow/transferring/summaryPageList" height="full-315" text="未找到数据！">
							<tr>
								<smart:tableItem field="recruitOrgan" width=".1" sort="false">选调机构</smart:tableItem>
								<smart:tableItem field="allowWeaveNum" width=".1" sort="true">核定编制数</smart:tableItem>
								<smart:tableItem field="realNum" width=".1" sort="true">实有人数</smart:tableItem>
								<smart:tableItem field="thisYearLackWeaveNum" width=".1" sort="true">年度缺编人数</smart:tableItem>
								<smart:tableItem field="planCutNum" width=".1" sort="true">计划减员人数</smart:tableItem>
								<smart:tableItem field="planEmployNum" width=".1" sort="true">计划选调人数</smart:tableItem>
								<smart:tableItem field="recuritType" width=".1" sort="false">编制类型</smart:tableItem>
								<smart:tableItem field="status" width=".1" sort="true">状态</smart:tableItem>
								<smart:tableItem align="center" width=".2" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="view" title="查看汇总详情">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="warm" event="edit" title="上传选调人员">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			edit : function(data) {
				smart.show({
					title : '添加选调人员',
					size : 'full',
					url : "ofcflow/transferring/plan",
					scrollbar : false
				});
			}
			,view : function(data) {
				smart.show({
					title : '选调交流汇总',
					size : 'full',
					url : "ofcflow/transferring/summaryPage",
					params : {id:data.data.id},
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
	</smart:scriptHead>
</smart:body>
</html>