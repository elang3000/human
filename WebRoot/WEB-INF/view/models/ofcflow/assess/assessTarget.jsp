<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--年度考核奖励--编辑年度考核优秀比例" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="年度考核奖励"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="编辑年度考核优秀比例" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
			
			
			
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="jobChange_searchForm">
							<smart:gridColumn colPart="5">
								<smart:textInput labelName="单位名称 ："  display="inline"
									placeholder="请输入姓名" name="name"></smart:textInput>
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
<%-- 									<smart:button size="sm" method="resetPercent" title="修改默认比例"
										theme="normal" >
										<smart:icon icon="history"></smart:icon>&nbsp;修改默认优秀比例
		   							</smart:button> --%>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table   id="navigationList" url="ofcflow/assess/assessTargetData/${id }"
							height="full-215" 
							text="未找到有效数据！">
							<tr>
								<smart:tableItem field="orgName" width=".2" sort="true">单位名称</smart:tableItem>
								<smart:tableItem field="draftOutstandingPercent" width=".2" sort="true">拟优秀比例</smart:tableItem>
								<smart:tableItem field="draftOutstandingNumb" width=".2" sort="true">拟优秀人数</smart:tableItem>
								<smart:tableItem field="outstandingNumb" width=".2" sort="true">指定拟优秀人数</smart:tableItem>

								<smart:tableItem align="center" width=".2" 
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar  id="navListToolBar">
									<smart:tableToolBtn theme="warm" event="edit" title="编辑">
										<smart:icon icon="edit"></smart:icon>
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
				edit : function(data) {
					smart.show({
					title : '修改拟优秀人数--'+data.data.orgName,
					size : 'xs',
					url : 'ofcflow/assess/assessTargetEdit/'+data.data.id,
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
			}
		};

		$('#jobChange_searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
	</smart:scriptHead>
</smart:body>
</html>