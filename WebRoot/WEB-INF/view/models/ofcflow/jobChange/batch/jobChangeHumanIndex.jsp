<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--职务变动" />
	<style type="text/css">
		.layui-form-label {
			width: 90px;
		}
	</style>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请" ></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="职务变动" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
			
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="false" eId="" itemName="职务变动列表"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId=""  itemName="职务变动人员列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/jobchange/flow" show="false" eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
			
			
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="jobChange_searchForm" action="ofcflow/jobchangeB/addJobShiftCollect">
								<smart:gridColumn colPart="5">
									<smart:singleSelect name="jobChangeType" id="servantType" display="inline" labelName="职务变动类型" shortName="变动类型"
											isNotNull="true" verify="required"	data="[{'key':'','value':'请选择..'},{'key':'JOBSHIFT_PROMOTEB','value':'晋升'},{'key':'JOBSHIFT_DEPOSE','value':'免职'},{'key':'JOBSHIFT_DEMOTE','value':'降职'},{'key':'JOBSHIFT_SHIFT','value':'轮岗'}]"></smart:singleSelect>
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
									<shiro:hasPermission name="ADD_JOBSHIFT_BTN">
										<smart:button size="sm"  title="新增职务变动"  id="save" other="lay-submit"
											theme="normal" >
											<smart:icon icon="plus"></smart:icon>&nbsp;新增
										</smart:button>
									</shiro:hasPermission>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table  id="navigationList" url="ofcflow/jobchangeB/humanIndexData"
							height="full-205"
							text="未找到有效数据！">
							<tr>
								<smart:tableItem field="collectName" width=".2" sort="true">批次名</smart:tableItem>
								<smart:tableItem field="jobChangeTypeStr" width=".2" sort="true">职务变动类型</smart:tableItem>
								<smart:tableItem field="createTimeStr" width=".2" sort="false">变动发起时间</smart:tableItem>
								<smart:tableItem field="remark" width=".3" sort="false">备注</smart:tableItem>
								<smart:tableItem align="CENTER" width=".1" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar  id="navListToolBar">
									<smart:tableToolBtn theme="warm" event="view" title="查看">
										<smart:icon icon="eye"></smart:icon>
									</smart:tableToolBtn>
								<%--	<smart:tableToolBtn theme="normal" event="edit" title="编辑">
										<smart:icon icon="pencil"></smart:icon>
									</smart:tableToolBtn>--%>
									<%--<smart:tableToolBtn theme="danger" event="del" title="删除">
										<smart:icon icon="trash"></smart:icon>
									</smart:tableToolBtn>--%>
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
						title : '职务变动详情',
						size : 'full',
						url : 'ofcflow/jobchangeB/jobchangePromoteView?id='+data.data.id,
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
			addJobShift:function(){
				
			}
		};

		$('#jobChange_searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
		
		
		form.on('submit(save)', function (data) {
			var params=smart.json("#jobChange_searchForm");
			var url=data.form.action;
			smart.show({
				title : '新增职务变动',
				size : 'full',
				url : url,
				scrollbar : false,
				params : params
			});
		});
		
		
	</smart:scriptHead>
</smart:body>
</html>