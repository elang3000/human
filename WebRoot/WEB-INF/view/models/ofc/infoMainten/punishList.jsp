<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--处分备案" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="处分备案" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="false" eId="" itemName="人员列表" 
										turnurl="ofc/punish/servantList"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="处分备案"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="查询条件" color="blue">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="文件号：" autocomplete="off"
										placeholder="输入文件号" name="punishNo">
									</smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="惩戒名称：" autocomplete="off"
										placeholder="输入惩戒名称" name="punishName">
									</smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="惩戒原因：" display="block" name="punishReason" url="dictquery/sub/id/0198/null" isAddDefaltOption="true">
									</smart:singleSelect>
								</smart:gridColumn>

							</smart:gridRow>

							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="姓名：" autocomplete="off"
										placeholder="输入姓名" name="name">
									</smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="身份证号：" autocomplete="off"
										placeholder="输入身份证号" name="cardNo">
									</smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询"
											theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
				  				 </smart:button>
										<smart:button size="sm" method="history" title="重置"
											theme="primary"  type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
				   				</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="punishNavigationList" url="ofc/punish/getPage"
							height="full-295" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="punishNo" width=".1" sort="false">处分文件号</smart:tableItem>
								<smart:tableItem field="punishName" width=".2" sort="false">惩戒名称</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".2" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="punishApprovalDate" width=".1" sort="false">惩戒批准日期</smart:tableItem>
								<smart:tableItem field="punishApprovalUnitName" width=".1" sort="false">惩戒批准单位</smart:tableItem>
								<smart:tableItem field="punishReason" width=".2" sort="false">惩戒原因</smart:tableItem>
								<smart:tableItem field="punishDescription" width=".2" sort="false">惩戒说明</smart:tableItem>
								<smart:tableItem field="punishRevokeDate" width=".1" sort="false">惩戒解除日期</smart:tableItem>
								<smart:tableItem field="punishIdentification" width=".1" sort="false">是否监察机关给予惩戒</smart:tableItem>
								<smart:tableItem field="punishmentIdentification" width=".1" sort="false">是否受处分</smart:tableItem>
								<smart:tableItem align="center" width=".1" fixed="right"
									toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="del" title="删除">
									<smart:icon icon="trash"></smart:icon>
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

		<smart:tableScriptAction tableId="punishNavigationList" checkbox="true"
			sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
					title : '编辑处分',
					size : 'full',
					url : 'ofc/punish/edit?punishId='+data.data.id,
					scrollbar : false,
					});
				},
				del : function(data) {
					smart.confirm({
							title:'删除处分记录',
							message:'确认删除这条处分记录？',
							url:'ofc/punish/delete',
							params : {id : data.data.id},
							callback : punishNavigationList_TableInvokeMethod.reload
						});
				},
				reload : function(){
					table.reload('punishNavigationList');
				}
			</smart:tableScriptAction>
		var buttonInvokeMethod = {
			search : function() {
				var params = utils.json($('#searchForm'));
				table.reload('punishNavigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		};

		$('#searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
	</smart:scriptHead>
</smart:body>
</html>