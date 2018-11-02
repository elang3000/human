<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--信息维护" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="信息维护" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="3">
						<smart:card>
							<smart:cardHead>
								<blockquote class="layui-elem-quote" style="padding:5px;">
									<div>&nbsp;&nbsp;&nbsp;组织结构树</div>
								</blockquote>
							</smart:cardHead>
							<smart:cardBody>
								<script>
									function loadListById(event, treeId, treeNode) {
										$('.searchForm button[type="reset"]').click();//先重置表单
										$(".searchForm input[name='departId']").val(treeNode.id);//赋值当前选中单位id
										$('.searchForm button[search_btn]').click();//查询
							        };
								</script>
								<smart:customDynamicTree id="treeArea" url="org/orgRelations" style="border:1px solid #e6e6e6;" cutSize="155" customEvent="true" funcType="onClick" funcName="loadListById"/>
							</smart:cardBody>
						</smart:card>
					</smart:gridColumn>
					<smart:gridColumn colPart="9">
						<smart:gridRow>
							<div class="layui-card">
								<div class="layui-card-body">
									<div class="layui-tab">
										<ul class="layui-tab-title">
											<li class="layui-this">在职人员</li>
											<li>历史人员</li>
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
																<smart:singleSelect labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
																</smart:singleSelect>
															</smart:gridColumn>
														</smart:gridRow>
														<smart:gridRow>
															<smart:gridColumn colPart="12">
																<smart:buttonGroup container="true" align="right">
																	<smart:button size="sm" method="search_ofcList" title="查询"
																		theme="primary" other="search_btn">
																		<smart:icon icon="search"></smart:icon>&nbsp;查询
										  				 		</smart:button>
																	<smart:button size="sm" method="history" title="重置"
																		theme="primary" type="reset">
																		<smart:icon icon="history"></smart:icon>&nbsp;重置
										   						</smart:button>
										   						
										   						<%-- <smart:button size="sm" method="check" title="常用查询方案"
																		theme="normal">
																		<smart:icon icon="plus"></smart:icon>&nbsp;新增方案
										   						</smart:button>
																	<smart:button size="sm" method="check" title="数据校验">
																		<smart:icon icon="check"></smart:icon>&nbsp;数据校验
									   							</smart:button>
																	<smart:button size="sm" method="check" title="变更部门"
																		theme="warm">
																		<smart:icon icon="info"></smart:icon>&nbsp;变更部门
										   						</smart:button> --%>
										   						
																</smart:buttonGroup>
															</smart:gridColumn>
															
														</smart:gridRow>
													</smart:form>
												</smart:fieldSet>
											</smart:gridRow>
											<smart:gridRow colSpace="5">
												<smart:gridColumn>
													<smart:table id="navigationList_ofcList" url="ofc/pageList" text="未找到用户数据！" height="full-310" page="true" doneCallBack="fixedCol">
														<tr>
															<smart:tableItem isCheckbox="true">全选</smart:tableItem>
															<smart:tableItem field="name" width="120" sort="true">姓名</smart:tableItem>
															<smart:tableItem field="sex" width="120" sort="true">性别</smart:tableItem>
															<smart:tableItem field="cardNo" width="240" sort="true">身份证号</smart:tableItem>
															<smart:tableItem field="departName" width="120" sort="false">单位部门</smart:tableItem>
															<smart:tableItem field="postName" width="120" sort="false">现任最高职务名称</smart:tableItem>
															<smart:tableItem field="postAttributeName" width="120" sort="false">职务属性</smart:tableItem>
															<smart:tableItem field="jobLevel" width="120" sort="false">职级名称</smart:tableItem>
															<smart:tableItem field="isOnHold" width="120" sort="false">状态</smart:tableItem>
															<smart:tableItem align="center" width="120" fixed="right"
																unresize="true" toolbar="navListToolBar_ofcList">操作</smart:tableItem>
														</tr>
														<smart:tableToolBar id="navListToolBar_ofcList">
															<smart:tableToolBtn theme="warm" event="edit_ofcList" title="编辑">
																<smart:icon icon="edit"></smart:icon>
															</smart:tableToolBtn>
														</smart:tableToolBar>
													</smart:table>
												</smart:gridColumn>
											</smart:gridRow>
											</div>
											<div class="layui-tab-item">
											<smart:gridRow>
												<smart:fieldSet title="查询条件" color="blue">
													<smart:form id="searchForm_ofcOldList" clazz="searchForm" >
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
															<smart:gridColumn colPart="12">
																<smart:buttonGroup container="true" align="right">
																	<smart:button size="sm" method="search_ofcOldList" title="查询"
																		theme="primary" other="search_btn">
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
													<smart:table id="navigationList_ofcOldList" url="ofc/oldPageList" height="full-310" text="未找到用户数据！" page="true" doneCallBack="fixedCol">
														<tr>
															<smart:tableItem isCheckbox="true">全选</smart:tableItem>
															<smart:tableItem field="name" width="120" sort="false">姓名</smart:tableItem>
															<smart:tableItem field="sex" width="120" sort="false">性别</smart:tableItem>
															<smart:tableItem field="cardNo" width="240" sort="false">身份证号</smart:tableItem>
															<smart:tableItem field="departName" width="120" sort="false">单位部门</smart:tableItem>
															<smart:tableItem field="postName" width="120" sort="false">职务名称</smart:tableItem>
															<smart:tableItem field="postAttributeName" width="120" sort="false">职务属性</smart:tableItem>
															<smart:tableItem field="jobLevel" width="120" sort="false">职级名称</smart:tableItem>
															<smart:tableItem field="isOnHold" width="120" sort="false">状态</smart:tableItem>
															<smart:tableItem align="center" width="120" fixed="right" unresize="true" toolbar="navListToolBar_ofcOldList">操作</smart:tableItem>
														</tr>
														<smart:tableToolBar id="navListToolBar_ofcOldList">
															<smart:tableToolBtn theme="warm" event="edit_ofcOldList" title="编辑">
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
				window.location.href='ofc/main?id='+data.data.id;
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
			},
			search_ofcOldList : function() {
				var params = utils.json($('#searchForm_ofcOldList'));
				table.reload('navigationList_ofcOldList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>
		<smart:tableScriptAction tableId="navigationList_ofcOldList" checkbox="true"
			sort="false" rowEdit="true">
			edit_ofcOldList : function(data) {
				window.location.href='ofc/main?id='+data.data.id;
			}
		</smart:tableScriptAction>
	</smart:scriptHead>
</smart:body>
</html>