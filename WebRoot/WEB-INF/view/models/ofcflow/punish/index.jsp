<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--培训学时考核" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="处分备案" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="处分备案列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/punish/flow" show="false" eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="姓名：" placeholder="请输入姓名" name="name"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="身份证：" placeholder="请输入身份证" name="cardNo"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="处分原始文件号：" placeholder="请输入处分原始文件号" name="punishFileName"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="处分原因：" name="punishReason.id" display="block" url="dictquery/sub/code/DM021_PUNISH" isAddDefaltOption="true"></smart:singleSelect>
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
		   							<shiro:hasPermission name="ADD_PUNISH_SERVANT_BTN">
		   							<smart:button size="sm" method="add" title="新增处分">
										<smart:icon icon="plus">&nbsp;新增处分</smart:icon>
									</smart:button>
									</shiro:hasPermission>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/punish/pageList" 
							height="full-240" text="未找到用户数据！" page="true" doneCallBack="fixedCol">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="cardNo" width=".15" sort="false">身份证</smart:tableItem>
								<smart:tableItem field="punishCode" width=".2" sort="false">处分名称</smart:tableItem>
								<smart:tableItem field="punishReason" width=".15" sort="false">处分原因</smart:tableItem>
								<smart:tableItem field="punishFileName" width=".15" sort="false">处分文件号</smart:tableItem>
								<smart:tableItem field="punishApprovalDate" width=".15" sort="false">处分批准时间</smart:tableItem>
								<smart:tableItem field="punishYear" width=".15" sort="false">处分期限（年）</smart:tableItem>
								<smart:tableItem field="status" width=".15" sort="false">状态</smart:tableItem>
								<smart:tableItem align="center" width=".15" fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
									{{#  if(d.statusSign==1){ }}
										<shiro:hasPermission name="EDIT_PUNISH_SERVANT_BTN">
										<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="edit"  title="编辑">
											<i class="fa fa-edit"></i>
										</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="REMOVE_PUNISH_SERVANT_BTN">
										<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"  title="删除">
											<i class="fa fa-trash"></i>
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
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:dateRender id="startDate" />
		<smart:dateRender id="endDate" />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
					title : '编辑培训信息',
					size : 'full',
					url : 'ofcflow/punish/addPunish?id='+data.data.id,
					scrollbar : false,
					});
				},
				remove : function(data) {
					smart.confirm({
						title:'删除处分信息',
						message:'确认删除处分信息？',
						type:'POST',
						url:'ofcflow/punish/remove',
						params : {id : data.data.id},
						callback : function() {
							var params = smart.json($('#searchForm'));
							table.reload('navigationList', {
								where : params
							});
						}
				});
				},
				view : function(data) {
				smart.show({
					title : '查看处分信息',
					size : 'full',
					url : "ofcflow/punish/view",
					params:{id:data.data.id},
					scrollbar : false
				});
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
			},
			add : function() {
				smart.show({
					title : '新增处分备案',
					size : 'full',
					url : "ofcflow/punish/addPunish",
					size:'full',
					scrollbar : false
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>