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
					<smart:breadcrumbNavMenuItem iname="国企管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="信息维护" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="12">
						<smart:gridRow>
							<div class="layui-card">
								<div class="layui-card-body">
									<div class="layui-tab">
										<ul class="layui-tab-title">
											<li class="layui-this">在职人员</li>
										</ul>
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
															</smart:gridColumn>
															<smart:gridColumn colPart="4">
																<smart:singleSelect labelName="性别：" display="block" name="sexId" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
																</smart:singleSelect>
															</smart:gridColumn>
														</smart:gridRow>
														<smart:gridRow>
															<%-- <smart:gridColumn colPart="4">
																<smart:singleSelect labelName="查询方案："
																	display="block">
																</smart:singleSelect>
															</smart:gridColumn> --%>
															<smart:gridColumn colPart="2" colOffset="10">
																<smart:buttonGroup container="true">
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
															<%-- <smart:gridColumn colPart="4" colOffset="1">
																<smart:buttonGroup container="true">
																	<smart:button size="sm" method="check" title="常用查询方案"
																		theme="normal">
																		<smart:icon icon="plus"></smart:icon>&nbsp;新增方案
										   						</smart:button>
																	<smart:button size="sm" method="check" title="数据校验">
																		<smart:icon icon="check"></smart:icon>&nbsp;数据校验
									   							</smart:button>
																	<smart:button size="sm" method="check" title="变更部门"
																		theme="warm">
																		<smart:icon icon="info"></smart:icon>&nbsp;变更部门
										   						</smart:button>
																</smart:buttonGroup>
															</smart:gridColumn> --%>
														</smart:gridRow>
													</smart:form>
												</smart:fieldSet>
											</smart:gridRow>
											<smart:gridRow colSpace="5">
												<smart:gridColumn>
													<smart:table id="navigationList_ofcList" url="company/outMgrPageList" text="未找到用户数据！" page="true">
														<tr>
														    <%-- 
														    <smart:tableItem type="hidden" field="swId"></smart:tableItem>
															<smart:tableItem isCheckbox="true">全选</smart:tableItem> --%>
															<smart:tableItem field="category" width="120" sort="true">调出本单位类别</smart:tableItem>
															<smart:tableItem field="outDate" width="120" sort="true">调出时间</smart:tableItem>
															<smart:tableItem field="gotoUnitName" width="240" sort="true">调往单位名称</smart:tableItem>
															<smart:tableItem field="reasonType" width="120" sort="false">调动原因</smart:tableItem>
															<smart:tableItem field="proposeType" width="120" sort="false">调动类型</smart:tableItem>
															<smart:tableItem field="name" width="120" sort="false">姓名</smart:tableItem>
															<smart:tableItem field="departName" width="120" sort="false">单位名称</smart:tableItem>
															<smart:tableItem field="cardNo" width="120" sort="false">身份证</smart:tableItem>
															<smart:tableItem align="center" width="120" fixed="right"
																unresize="true" toolbar="navListToolBar_ofcList">操作</smart:tableItem>
														</tr>
														<smart:tableToolBar id="navListToolBar_ofcList">
															<smart:tableToolBtn theme="warm" event="edit_ofcList" title="查看信息">
																<smart:icon icon="edit"></smart:icon>
															</smart:tableToolBtn>
														</smart:tableToolBar>
													</smart:table>
												</smart:gridColumn>
											</smart:gridRow>
											</div>
										</div>
									</div>
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
				window.location.href='company/main?id='+data.data.swId + '&flag=outmgr';
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