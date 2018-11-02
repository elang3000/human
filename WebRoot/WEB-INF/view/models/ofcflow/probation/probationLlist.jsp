<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="试用期管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="试用期名单列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/probation/flow" show="false" eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="姓名" display="block" name="name" placeholder="姓名"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect name="isTimeOK" display="block" labelName="状态" 
									data="[{'key':'1','value':'试用期满'},{'key':'0','value':'试用期未满'}]"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询"
											theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
			  				 			</smart:button>
										<smart:button size="sm" title="重置"
											theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
			   							</smart:button>
			   							<shiro:hasPermission name="MANAGE_PROBATION_BTN">
				   							<smart:button size="sm" method="timeLength" title="管理试用期时长">
												<smart:icon icon="gears">&nbsp;管理试用期时长</smart:icon>
											</smart:button>
										</shiro:hasPermission>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/probation/probationServantList"
							height="full-210" text="未获取到数据！">
							<tr>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".1" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="draftDeptName" width=".1" sort="false">单位</smart:tableItem>
								<smart:tableItem field="probationStartDate" width=".1" sort="false">试用期开始时间</smart:tableItem>
								<smart:tableItem field="probationEndDate" width=".1" sort="false">试用期结束时间</smart:tableItem>
								<smart:tableItem field="probationStatus" width=".1" sort="false">考核状态</smart:tableItem>
								<smart:tableItem field="status" width=".1" sort="false">状态</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
									{{#  if(d.statusFlag=="0"){ }}
										<shiro:hasPermission name="EDIT_PROBATION_BTN">
											<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
												<i class="fa fa-edit"></i>
											</a>
										</shiro:hasPermission>
 									{{#  } }}
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
			edit : function(data) {
				smart.show({
					title : '公务员试用结果',
					size : 'full',
					url : "ofcflow/probation/edit?id="+data.data.id,
					scrollbar : false
				});
			}
			,view : function(data) {
				smart.show({
					title : '公务员试用结果',
					size : 'full',
					url : "ofcflow/probation/view?id="+data.data.id,
					scrollbar : false
				});
			}
			,reload : function(){
				table.reload('navigationList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('.layui-form'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
			,timeLength : function(data) {
				smart.show({
					title : '管理试用期时长',
					size : 'full',
					url : "ofcflow/probation/timeLength",
					size:'xs',
					scrollbar : false
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>