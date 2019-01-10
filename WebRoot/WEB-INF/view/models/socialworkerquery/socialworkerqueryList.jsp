<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%> 
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
					<smart:breadcrumbNavMenuItem iname="查询统计与分析"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="综合查询" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
			<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem turnurl="analysis/general/peopleQueryList" show="false" eId="" itemName="人员综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="analysis/general/generalQueryList"
								show="false" eId="" itemName="公务员综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="business/personel/businessQueryList"
								show="false" eId="" itemName="事业人员综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem
								turnurl="business/nation/nationalCompanyQueryList" show="false"
								eId="" itemName="国企职工综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="社工综合查询"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
			
			
				<smart:gridRow>
					<smart:gridColumn colPart="2">
						<smart:card>
							
						</smart:card>
					</smart:gridColumn>
					<smart:gridColumn colPart="14">
						<smart:gridRow>
							<!-- <div class="layui-card">
								<div class="layui-card-body">
									<div class="layui-tab">
										<ul class="layui-tab-title">
											
											
										</ul> -->
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
															<%-- 
															<smart:gridColumn colPart="4">
																<smart:textInput labelName="身份证号：" autocomplete="off" placeholder="输入身份证号" name="cardNo"></smart:textInput>
															</smart:gridColumn> 
															--%>
															<smart:gridColumn colPart="4">
																<smart:singleSelect labelName="任职状态：" display="block" name="isOnHold.id" url="dictquery/sub/code/DM200" isAddDefaltOption="true">
																</smart:singleSelect>
															</smart:gridColumn>
															<smart:gridColumn colPart="4">
																<smart:singleSelect labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
																</smart:singleSelect>
															</smart:gridColumn>
															<smart:gridColumn colPart="4">
																<smart:textInput labelName="身份证号：" autocomplete="off" placeholder="输入身份证号" name="cardNo"></smart:textInput>
															</smart:gridColumn> 
															<smart:gridColumn colPart="4">
																<smart:textInput labelName="单位部门：" autocomplete="off" placeholder="输入部门" name="departName"></smart:textInput>
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
										   						
															 </smart:buttonGroup>
															</smart:gridColumn>
															<smart:gridColumn colPart="4" colOffset="1">
																<smart:buttonGroup container="true">
																	
																</smart:buttonGroup>
															</smart:gridColumn>
														</smart:gridRow>
													</smart:form>
												</smart:fieldSet>
											</smart:gridRow>
											<smart:gridRow colSpace="5">
												<smart:gridColumn>
													<smart:table id="navigationList_ofcList" url="socialworker/pageList" text="未找到用户数据！" height="full-299" page="true">
														<tr>
															<%-- <smart:tableItem isCheckbox="true">全选</smart:tableItem> --%>
															<smart:tableItem field="name" width="120" sort="true">姓名</smart:tableItem>
															<smart:tableItem field="sex" width="120" sort="true">性别</smart:tableItem>
															<smart:tableItem field="departName" width="120" sort="false">单位部门</smart:tableItem>
															<smart:tableItem field="isOnHold" width="120" sort="false">状态</smart:tableItem>
															<smart:tableItem field="nowPostName" width="120" sort="false">职务名称</smart:tableItem>
															<smart:tableItem field="topEducation" width="120" sort="false">学历</smart:tableItem>
															<smart:tableItem field="topDegree" width="120" sort="false">学位</smart:tableItem>
															<smart:tableItem field="personType" width="120" sort="false">人员类型</smart:tableItem>
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
				window.location.href='socialworker/main?id='+data.data.id;
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
			socialWorkerImport : function() {
				smart.show({
					title:'社工人员导入',
					url:'socialworker/draftsocialworkerImport',
					scrollbar:false
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
			},
			search_ofcIntoList : function() {
				var params = utils.json($('#searchForm_ofcIntoList'));
				table.reload('navigationList_ofcIntoList', {
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
		<smart:tableScriptAction tableId="navigationList_ofcIntoList" checkbox="true"
			sort="false" rowEdit="true">
			edit_ofcIntoList : function(data) {
				window.location.href='ofc/main?id='+data.data.id;
			}
		</smart:tableScriptAction>
	</smart:scriptHead>
</smart:body>
</html>