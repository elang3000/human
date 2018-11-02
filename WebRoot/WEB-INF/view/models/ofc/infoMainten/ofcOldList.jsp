<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--信息维护" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="信息维护" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="2">
						<smart:card>
							<smart:cardHead>
								组织树
							</smart:cardHead>
							<smart:cardBody>
								<script>
									function loadListById(event, treeId, treeNode) {
										$('#searchForm button[type="reset"]').click();//先重置表单
										$("#searchForm input[name='departId']").val(treeNode.id);//赋值当前选中单位id
										$('#searchForm button[data-method="search"]').click();//查询
							        };
								</script>
								<smart:customDynamicTree id="treeArea" url="org/orgRelations" style="border:1px solid #e6e6e6;" cutSize="155" customEvent="true" funcType="onClick" funcName="loadListById"/>
							</smart:cardBody>
						</smart:card>
					</smart:gridColumn>
					<smart:gridColumn colPart="10">
						<smart:gridRow>
							<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
								<smart:tabPanel>
									<smart:tabPanelItem show="false"  eId="" itemName="在职人员" turnurl="ofc/list"></smart:tabPanelItem>
									<smart:tabPanelItem show="true" eId="" itemName="历史人员"></smart:tabPanelItem>
									<smart:tabPanelItem show="false" eId="" itemName="进出管理"  turnurl="ofc/intoList"></smart:tabPanelItem>
								</smart:tabPanel>
							</smart:tabPanelParent>
						</smart:gridRow>
						<smart:gridRow>
							<smart:fieldSet title="查询条件" color="blue">
								<smart:form id="searchForm">
									<smart:gridRow>
										<smart:gridColumn colPart="4">
											<smart:textInput labelName="姓名：" autocomplete="off" placeholder="输入姓名" name="name"></smart:textInput>
											<smart:textInput type="hidden" name="departId"></smart:textInput>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:textInput labelName="身份证号：" autocomplete="off" placeholder="输入身份证号" name="cardNo"></smart:textInput>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:singleSelect labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
											</smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="4">
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
								<smart:table id="navigationList" url="ofc/oldPageList" height="full-315" sortField="cardnum" sortType="asc" text="未找到用户数据！" page="true">
									<tr>
										<smart:tableItem isCheckbox="true">全选</smart:tableItem>
										<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
										<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
										<smart:tableItem field="cardNo" width=".2" sort="true">身份证号</smart:tableItem>
										<smart:tableItem field="departName" width=".1" sort="false">单位部门</smart:tableItem>
										<smart:tableItem field="isOnHold" width=".1" sort="false">状态</smart:tableItem>
										<smart:tableItem field="postname" width=".2" sort="false">职务名称</smart:tableItem>
										<smart:tableItem field="nowPostRank" width=".1" sort="false">职务层次</smart:tableItem>
										<smart:tableItem field="postatt" width=".1" sort="false">职务属性</smart:tableItem>
										<smart:tableItem align="center" width=".1" fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
									</tr>
									<smart:tableToolBar id="navListToolBar">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</smart:tableToolBar>
								</smart:table>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridColumn>
				</smart:gridRow>
		 	</smart:cardBody>
	 	</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			edit : function(data) {
				window.location.href='ofc/main?id='+data.data.id;
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