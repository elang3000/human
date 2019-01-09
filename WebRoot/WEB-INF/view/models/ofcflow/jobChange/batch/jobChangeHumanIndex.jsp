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
							<smart:tabPanelItem show="false" eId="" turnurl="ofcflow/jobchangeB/index" itemName="职务变动列表"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId=""  itemName="变动人员列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/jobchangeB/flow" show="false" eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
			
			
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="jobChange_searchForm" action="ofcflow/jobchangeB/addJobShiftCollect">
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="姓名：" autocomplete="off"
												 placeholder="输入姓名" name="name">
								</smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="身份证号：" autocomplete="off"
												 placeholder="输入身份证号" name="cardNo">
								</smart:textInput>
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
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="cardNo" width=".1" sort="false">身份证号</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="birthDate" width=".1" sort="true">出生日期</smart:tableItem>
								<smart:tableItem field="formerPostName" width=".1" sort="true">原职务</smart:tableItem>
								<smart:tableItem field="newPostName" width=".1" sort="true">变动后职务</smart:tableItem>
								<smart:tableItem field="formerJobLevelName" width=".1" sort="true">原职级</smart:tableItem>
								<smart:tableItem field="newJobLevelName" width=".1" sort="true">变动后职级</smart:tableItem>
								<smart:tableItem align="center" fixed="right" width=".15"
												 unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
								<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="view"  title="查看">
									<i class="fa fa-eye"></i>
								</a>
							</script>
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
						title : '查看职务变动信息',
						size : 'full',
						url : "ofcflow/jobchangeB/promoteView/"+data.data.id,
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