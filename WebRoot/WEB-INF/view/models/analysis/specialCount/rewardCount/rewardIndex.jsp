<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--专项统计" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="专项统计"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员奖励统计" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>

				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="searchForm">
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="单位名称：" display="block"
									placeholder="请输入单位名称" name="departName"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:date labelName="年度：" display="block"
									placeholder="请输入年度" name="year" id="year"></smart:date>
							</smart:gridColumn>
							<smart:gridColumn colPart="3" colOffset="1">
								<smart:buttonGroup container="true" align="left">
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
						<smart:table id="navigationList"
							url="analysis/specialCount/reward/list" height="full-155" 
							text="未找到有效数据！">
							<tr>
								<smart:tableItem field="departName" width=".2">单位</smart:tableItem>
								<smart:tableItem field="houor" width=".14">授予荣誉称号</smart:tableItem>
								<smart:tableItem field="commendation" width=".14">嘉奖</smart:tableItem>
								<smart:tableItem field="firReward" width=".14">记一等功</smart:tableItem>
								<smart:tableItem field="secReward" width=".14">记二等功</smart:tableItem>
								<smart:tableItem field="thiReward" width=".14">记三等功</smart:tableItem>
								<smart:tableItem align="center" width=".1" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="default" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:dateRender id="year" type="year"/>
        <smart:tableScriptAction tableId="navigationList"
			checkbox="true" sort="true" rowEdit="true">
            view : function(data) {
            	var year = $('#year').val();
	            smart.show({
		            title : '单位详情查看',
		            size : 'full',
		            url : 'analysis/specialCount/reward/view?departId='+data.data.departId+'&year='+year+'&departName='+data.data.departName,
		            scrollbar : false
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