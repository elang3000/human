<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--消息中心" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="消息中心" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="searchForm">
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="消息状态：" name="state" data="[{'key':'0','value':'未读'},{'key':'1','value':'已读'}]" isAddDefaltOption="true"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="2" colOffset="5">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 		</smart:button>
									<smart:button size="sm" method="history" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   						</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="announcement/announcementList"
							height="full-200" 
							text="未找到有效数据！">
							<tr>
								<smart:tableItem field="title" width=".5" sort="true">消息标题</smart:tableItem>
								<smart:tableItem field="state" width=".2" sort="true">消息状态</smart:tableItem>
								<smart:tableItem field="createTime" width=".2" sort="true">消息日期</smart:tableItem>
								<smart:tableItem align="center"  fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" >
			reload : function(){
				table.reload('navigationList');
			}
			,view : function(data) {
				smart.show({
					title : '消息中心',
					size : 'full',
					url : "announcement/detail?id="+data.data.id,
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('#searchForm'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>