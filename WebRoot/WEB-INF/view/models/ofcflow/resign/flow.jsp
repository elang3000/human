<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="流程备案" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem turnurl="ofcflow/resign/index" eId="" show="false" itemName="人员列表"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="流程备案"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/resign/list" eId="" show="false" itemName="辞职列表"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="来文单位：" name="sourceOrganNode" placeholder="请输入来文单位"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="2" >
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 			</smart:button>
									<smart:button size="sm" title="重置"
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
						<smart:table id="navigationList" url="workflow/doing/page?busType=${busType}"
							height="full-200" text="未找到有效数据！">
							<tr>
								<smart:tableItem field="sourceOrganNode" width=".1" sort="true">来文单位</smart:tableItem>
								<smart:tableItem field="createTime" width=".1" sort="true">来文时间</smart:tableItem>
								<smart:tableItem field="targetOrganNode" width=".1" templet="topTpl">办理单位</smart:tableItem>
								<smart:tableItem field="targetSecurityUser" width=".1" templet="downTpl">办理人员</smart:tableItem>
								<smart:tableItem field="busName" width=".2">业务类型</smart:tableItem>
								<smart:tableItem field="ofcFlowDescription" width=".2">业务描述</smart:tableItem>
								<smart:tableItem align="center" width=".2" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar" >
								<smart:tableToolBtn theme="danger" event="shenpi" title="办理">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="normal" event="viewFlowRecord" title="查看流程记录">
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
		<smart:utils/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			shenpi : function(data) {
				var url = "ofcflow/resign/resignFlow";
				smart.show({
					title : '辞职备案',
					size : 'full',
					url : url,
					params:{id:data.data.id},
					scrollbar : false
				});
			}
			,viewFlowRecord : function(record) {
				smart.show({
					title : '查看流程记录',
					url : 'workflow/detail/index',
					params : {
						id : record.data.id
					},
					size:'full',
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
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>