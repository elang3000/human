<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="事项申请-年度考核" />
</head>
<smart:body>
	<smart:gridRow>
		<smart:breadcrumbNavMenu separator=">">
			<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
			<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
			<smart:breadcrumbNavMenuItem iname="年度考核" cite="true"></smart:breadcrumbNavMenuItem>
		</smart:breadcrumbNavMenu>
	</smart:gridRow>
	<smart:gridRow>
		<smart:title title="历史考核记录" style="margin-top: 5px;" color="blue" />
	</smart:gridRow>
	<smart:gridRow>
	  	<smart:form>
			<smart:gridColumn colPart="3">
				<smart:singleSelect labelName="考核年份" id="year" name="year" data="${yearList}"></smart:singleSelect>
			</smart:gridColumn>
		</smart:form>
	</smart:gridRow>
	<smart:gridRow colSpace="5">
		<smart:gridColumn colPart="12" deviceType="md">
			<smart:accordionPanel id="yearList" isAccordion="true">
					<smart:table id="navigationList" url="ofc/yearcheck/ajaxofclist" height="full" sortField="year" sortType="desc" text="未找到年度考核数据！">
						<tr>
							<smart:tableItem field="year" width=".1" sort="true">考核年度</smart:tableItem>
							<smart:tableItem field="danwei" width=".3" sort="true">考核单位</smart:tableItem>
							<smart:tableItem field="create" width=".2" sort="true" >创建人</smart:tableItem>
							<smart:tableItem field="status" width=".2" sort="false">状态</smart:tableItem>
							<smart:tableItem align="center" width=".2" fixed="right" unresize="true"  toolbar="navListToolBar">操作</smart:tableItem>
						</tr>
						<smart:tableToolBar id="navListToolBar">
							<smart:tableToolBtn theme="warm" event="yearEdit" title="编辑">
								<smart:icon icon="edit"></smart:icon>
							</smart:tableToolBtn>
						</smart:tableToolBar>
					</smart:table>
			</smart:accordionPanel>
		</smart:gridColumn>
	</smart:gridRow>
	<smart:gridRow>
			<smart:gridColumn colPart="12" deviceType="md">
				<smart:buttonGroup container="true">
					<smart:button size="sm" method="huizong" title="汇总">
						<smart:icon icon="pencil">汇总年度考核</smart:icon>
					</smart:button>
					<smart:button theme="warm" size="sm" method="add" title="添加">
						<smart:icon icon="plus">&nbsp;添加年度考核</smart:icon>
					</smart:button>
					<smart:button theme="danger" size="sm" method="back" title="返回">
						<smart:icon icon="trash">&nbsp;返回</smart:icon>
					</smart:button>
				</smart:buttonGroup>
			</smart:gridColumn>
		</smart:gridRow>
	<smart:scriptHead models="table,form,layer,element">
		<smart:tableScriptAction tableId="navigationList">
			yearEdit : function(data) {
				window.location.href='ofc/yearcheck/checkList?id='+data.data.id;
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			huizong : function(data) {
				window.location.href='ofc/yearcheck/checkList?id='+data.data.id;
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>