<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="上海市公务员信息管理系统--信息维护" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事业单位管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="人员信息维护" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="12">
						<smart:gridRow>
							<div class="layui-tab-content">
								<div class="layui-tab-item layui-show">
								<smart:gridRow>
									<smart:fieldSet title="查询条件" color="blue">
										<smart:form id="searchForm_ofcList" clazz="searchForm">
											<smart:gridRow>
												<smart:gridColumn colPart="4">
													<smart:textInput labelName="姓名：" autocomplete="off" placeholder="输入姓名" name="name"></smart:textInput>
													<smart:textInput type="hidden" name="departId"></smart:textInput>
												</smart:gridColumn>
												<smart:gridColumn colPart="4">
													<smart:textInput labelName="身份证号：" autocomplete="off" placeholder="输入身份证号" name="cardNo"></smart:textInput>
												    <smart:textInput type="hidden" name="conditionquery" value="1"></smart:textInput>
												</smart:gridColumn>
												<%-- <smart:gridColumn colPart="4">
													<smart:singleSelect labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
													</smart:singleSelect>
												</smart:gridColumn> --%>
												<smart:gridColumn colPart="4">
													<smart:buttonGroup container="true" align="right">
													<smart:button size="sm" method="search_ofcList" title="查询"
															theme="primary" other="search_btn">
															<smart:icon icon="search"></smart:icon>&nbsp;查询
							  				 		</smart:button>
													<smart:button size="sm" method="history" title="重置"
															theme="primary" type="reset">
															<smart:icon icon="history"></smart:icon>&nbsp;重置
							   						</smart:button>
													</smart:buttonGroup>
												</smart:gridColumn>
												<%-- <smart:gridColumn colPart="4">
													<smart:singleSelect labelName="任职状态：" display="block" name="isOnHold.id" url="dictquery/sub/code/DM200" isAddDefaltOption="true">
													</smart:singleSelect>
												</smart:gridColumn> --%>
											</smart:gridRow>
										</smart:form>
									</smart:fieldSet>
								</smart:gridRow>
								<smart:gridRow colSpace="5" >
									<smart:gridColumn >
										<smart:table id="navigationList_ofcList" url="publicInstitution/pageAllList" height="300" text="未找到用户数据！" page="true">
											<tr>
												<%-- <smart:tableItem isCheckbox="true">全选</smart:tableItem> --%>
												<smart:tableItem field="name" width="100" sort="true">姓名</smart:tableItem>
												<smart:tableItem field="sex" width="80" sort="true">性别</smart:tableItem>
												<smart:tableItem field="cardNo" width="240" sort="true">身份证号</smart:tableItem>
												<smart:tableItem field="departName" width="190" sort="false">单位部门</smart:tableItem>
												<smart:tableItem field="postname" width="120" sort="false">职务名称</smart:tableItem>
												<smart:tableItem field="isOnHold" width="120" sort="false">状态</smart:tableItem>
												<!--<smart:tableItem field="isOnHold" width="120" sort="false">状态</smart:tableItem>
												<smart:tableItem field="postname" width="120" sort="false">职务名称</smart:tableItem>
												<smart:tableItem field="nowPostName" width="120" sort="false">职务层次</smart:tableItem>
												<smart:tableItem field="postatt" width="120" sort="false">职务属性</smart:tableItem>-->
												<smart:tableItem align="center" width="150" fixed="right"
													unresize="true" toolbar="navListToolBar_ofcList">操作</smart:tableItem>
											</tr>
											<smart:tableToolBar id="navListToolBar_ofcList">
												<smart:tableToolBtn theme="warm" event="add" title="交流编辑">
													<smart:icon icon="edit">交流编辑</smart:icon>
												</smart:tableToolBtn>
											</smart:tableToolBar>
										</smart:table>
									</smart:gridColumn>
								</smart:gridRow>
								</div>
							</div>
						</smart:gridRow>

					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList_ofcList" checkbox="true"
			sort="false" rowEdit="true">
			edit_ofcList : function(data) {
				window.location.href='publicInstitution/main?id='+data.data.id;
			},
			add : function(data) {
			    smart.show({
					title : '人员交流轮岗信息申请',
					size : 'full',
					url : "instflow/alternatingrotation/returnApply?id=" + data.data.id,
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search_ofcList : function() {
				var params = utils.json($('#searchForm_ofcList'));
				table.reload('navigationList_ofcList', {
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