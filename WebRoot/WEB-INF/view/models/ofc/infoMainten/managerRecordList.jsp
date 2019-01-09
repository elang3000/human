<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="人事管理记录" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="人事管理记录" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="查询条件" color="blue">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="姓名：" autocomplete="off"
										placeholder="姓名" name="name">
									</smart:textInput>
								</smart:gridColumn>

								<smart:gridColumn colPart="4">
									<smart:textInput labelName="身份证号：" autocomplete="off"
										placeholder="身份证号" name="cardNo">
									</smart:textInput>
								</smart:gridColumn>

								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="性别：" display="block" name="sex"
										url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
									</smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="人事管理类型：" display="block"
										name="recordType" url="dictquery/sub/code/HumanChange"
										isAddDefaltOption="true">
									</smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="进出管理类型：" display="block"
										name="itemType" 
										data="[{'key':'0','value':'进'},{'key':'1','value':'出'},{'key':'2','value':'管理'}]"
										isAddDefaltOption="true">
									</smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="2" colOffset="2">
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
						<smart:table id="navigationList" url="ofc/managerRecord/getPage?departId=${departId}&year=${year}"
							height="full-190" sortField="" sortType="" text="未找到用户数据！"
							page="true">
							<tr>
								<smart:tableItem field="name" width=".1" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="false">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".2" sort="false">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".2" sort="false">事项发生时单位</smart:tableItem>
								<smart:tableItem field="itemType" width=".1" sort="false">进出管理类型</smart:tableItem>
								<smart:tableItem field="recordType" width=".1" sort="false">人事管理类型</smart:tableItem>
								<smart:tableItem field="recordTime" width=".1" sort="false">记录时间</smart:tableItem>
								<smart:tableItem align="center" width=".1" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="search1" title="查看">
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
				search1 : function(data) {
					smart.show({
					title : '进出管理详情',
					size : 'full',
					url : 'ofc/managerRecord/detail?id='+data.data.id,
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