<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--年度考核奖励" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="年度考核奖励"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="考核进度查看" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
			
			
			
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="jobChange_searchForm">
							<smart:gridColumn colPart="5">
								<smart:textInput labelName="单位名称："  display="inline"
									placeholder="请输入单位名称" name="unitName"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="3" >
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 			</smart:button>
									<smart:button size="sm" method="history" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   						   </smart:button>
		   							<smart:button size="sm" method="finishAssess" title="结束考核"
										theme="normal" >
										<smart:icon icon="hourglass-end"></smart:icon>&nbsp;结束考核
		   							</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table   id="navigationList" url="ofcflow/assess/assessProgressViewList?assessCollectId=${assessCollectId }"
							height="full-215" 
							text="未找到有效数据！"> 
							<tr>
								<smart:tableItem field="departName" width=".2" sort="true">单位名称</smart:tableItem>
								<smart:tableItem field="userCount" width=".2" sort="true">单位总人数</smart:tableItem>
								<smart:tableItem field="assessCount" width=".2" sort="true">已经考核人数</smart:tableItem>
								<smart:tableItem field="isConfirmStr" width=".2" sort="false">是否考核完成</smart:tableItem>
								<smart:tableItem align="center" width=".2" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar  id="navListToolBar">
									<smart:tableToolBtn theme="warm" event="view" title="查看详情">
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
					title : '考核详情',
					size : 'full',
					url : 'ofcflow/assess/unitCheckIndexPage/${assessCollectId }/'+data.data.id,
					scrollbar : false
					});
				}
			</smart:tableScriptAction>
		var buttonInvokeMethod = {
			search : function() {
				var params = utils.json($('#jobChange_searchForm'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
				finishAssess : function() {
				//完成考核
					smart.confirm({
						title:'提示',
						message:'结束考核需要所有单位完成考核,确认结束考核？',
						url:'ofcflow/assess/finishCollect',
						params:{collectId:'${assessCollectId }'}
					});
			    }
		};

		$('#jobChange_searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
	</smart:scriptHead>
</smart:body>
</html>