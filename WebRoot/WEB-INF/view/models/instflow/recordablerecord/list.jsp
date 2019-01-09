<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%> 

<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--离退备案" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="离退备案" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<%-- 
							<c:if test="${busType eq 'MemberInfoRegister'}">
								<smart:tabPanelItem show="false" turnurl="instflow/inforegister/index"
									eId="" itemName="登记人员"></smart:tabPanelItem>
							</c:if> 
							--%>
							<%-- 
							<smart:tabPanelItem show="true" eId="" itemName="流程审批"></smart:tabPanelItem>
							 --%>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="查询条件" style="margin-top: 5px;" color="blue">
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
								
							</smart:gridRow>

							<smart:gridRow>

								<smart:gridColumn colPart="4" colOffset="8">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询"
											theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
				  				 </smart:button>
										<smart:button size="sm" method="history" title="重置"
											theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
				   				</smart:button>
				   						 <shiro:hasPermission name="H002005004"> 
			   						    <smart:button size="sm" method="add" title="新增离退人员备案">
										    <smart:icon icon="plus">&nbsp;新增离退人员备案</smart:icon>
									    </smart:button>
								     </shiro:hasPermission> 
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<%-- <smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="来文单位：" name="sourceOrganNode" placeholder="请输入来文单位"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="5" >
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 			</smart:button>
									<smart:button size="sm" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   						    </smart:button>
		   						     <shiro:hasPermission name="H002005004"> 
			   						    <smart:button size="sm" method="add" title="新增离退人员备案">
										    <smart:icon icon="plus">&nbsp;新增离退人员备案</smart:icon>
									    </smart:button>
								     </shiro:hasPermission> 
 								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
						
						<smart:gridColumn colPart="6" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:fieldSet>
					
				</smart:gridRow> --%>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList" url="instflow/recordablerecord/pageList" height="full-235" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="name" width=".2" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".2" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".2">单位名称</smart:tableItem>
								<smart:tableItem field="recodeWay" width=".2" sort="true">备案类型</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true"
									toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="view" title="查看">
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
			view : function(data) {
					smart.show({
					title : '人员离退详情',
					size : 'full',
					url : 'instflow/recordablerecord/recordDetail?id='+data.data.id,
					scrollbar : false,
					});
				}
			<%-- shenpi : function(data) {
				<c:if test="${busType eq 'RecordableRecord'}">
					var url = "instflow/recordablerecord/queryRegister";
				</c:if>
				smart.show({
					title : '审批离退备案',
					size : 'full',
					url : url,
					params:{id:data.data.id},
					scrollbar : false
				});
			} --%>
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
			},
			add : function() {
			    smart.show({
					title : '事业单位离退人员',
					size : 'full',
					url : "instflow/recordablerecord/personList",
					scrollbar : false
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>