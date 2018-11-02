<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--辞职管理" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="辞职管理" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem turnurl="ofcflow/resign/index" eId="" show="false" itemName="人员列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/resign/flow" show="false" eId="" itemName="流程备案"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="辞职列表"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:gridRow>
					<smart:fieldSet title="查询条件" style="margin-top: 5px;" color="blue">
						<smart:form id="searchForm">
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
									<smart:singleSelect name="reason.id" labelName="辞职原因：" 
												display="block" url="dictquery/sub/code/DM009/5" isAddDefaltOption="true">
									</smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>

							<smart:gridRow>

								<smart:gridColumn colPart="2" colOffset="10">
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
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/resign/pageList" height="full-250" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".2" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".1">单位名称</smart:tableItem>
								<smart:tableItem field="reason" width=".1">辞职原因</smart:tableItem>
								<smart:tableItem field="resignWhereabouts" width=".15">辞职去向</smart:tableItem>
								<smart:tableItem field="status" width=".15">状态</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true"
									toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
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
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />

		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				view : function(data) {
					smart.show({
					title : '公务员辞职申请',
					size : 'full',
					url : 'ofcflow/resign/resignDetail?id='+data.data.id,
					scrollbar : false,
					});
				}
			</smart:tableScriptAction>
		var buttonInvokeMethod = {
			search : function() {
				var params = utils.json($('#searchForm'));
				table.reload('navigationList', {
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