<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区人事管理信息系统--机构信息列表" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="机构编制管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="机构信息查看" cite="true"></smart:breadcrumbNavMenuItem>
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
									function loadListById(event, treeId,treeNode) {
 										$('.searchForm button[type="reset"]').click();//先重置表单
										$(".searchForm input[name='parentOrganId']").val(treeNode.id);//赋值当前选中单位id
										$('.searchForm button[search_btn]').click();//查询
									};
								</script>
								<smart:customDynamicTree id="treeArea" url="org/orgRelations"
									style="border:1px solid #e6e6e6;" cutSize="155"
									customEvent="true" funcType="onClick" funcName="loadListById" />
							</smart:cardBody>
						</smart:card>
					</smart:gridColumn>
					<smart:gridColumn colPart="9">
						<smart:gridRow>
							<smart:fieldSet title="查询条件" color="blue">
								<smart:form id="searchForm_list" clazz="searchForm">
									<smart:gridRow>
										<smart:gridColumn colPart="6">
											<smart:textInput labelName="单位名称：" autocomplete="off" placeholder="输入关键字名称" name="unitBasicName"></smart:textInput>
											<smart:textInput type="hidden" name="parentOrganId"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="12">
											<smart:buttonGroup container="true" align="right">
												<smart:button size="sm" method="search_list" title="查询"
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
								<smart:table id="navigationList_orgInfoMgrList" url="orgInfo/pageList" text="未找到单位数据！" page="true" height="full-245">
									<tr>
										<smart:tableItem field="unitBasicName" width=".2">单位名称</smart:tableItem>
										<smart:tableItem field="unitBasicSimpleName" width=".15">单位简称</smart:tableItem>
										<smart:tableItem field="xydm" width=".2">统一社会信用代码</smart:tableItem>
										<smart:tableItem field="corporation" width=".15">单位负责人</smart:tableItem>
										<smart:tableItem field="buildDate" width=".15">成立日期</smart:tableItem>
										<smart:tableItem align="center" width=".15" fixed="right"
											unresize="true" toolbar="navListToolBar_list">操作</smart:tableItem>
									</tr>
									<smart:tableToolBar id="navListToolBar_list">
										<smart:tableToolBtn theme="warm" event="view" title="查看">
											<smart:icon icon="eye"></smart:icon>
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
		<smart:tableScriptAction tableId="navigationList_orgInfoMgrList" checkbox="true"
			sort="true" rowEdit="true">    
			view : function(data) {
				smart.show({
					title : '机构信息查看',
					size : 'full',
					url : 'orgInfo/viewPage?id='+data.data.id,
					scrollbar : false,
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search_list : function() {
				var params = utils.json($('#searchForm_list'));
				table.reload('navigationList_orgInfoMgrList', {
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