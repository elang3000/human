<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
</head>
<smart:body>
	<smart:grid>
		<div class="layui-card">
			<div class="layui-card-body">
				<div class="layui-tab">
	  				<ul class="layui-tab-title">
	    				<li class="layui-this">组织详情</li>
	    				<c:if test="${organNode.people == 1}">
	    					<li>组织人员</li>
	    				</c:if>
	    				<c:if test="${organNode.relation == 1}">
	    				<li>组织关系</li>
	    				</c:if>
	  				</ul>
  					<div class="layui-tab-content">
					    <div class="layui-tab-item layui-show">
							<smart:fieldSet title="维护组织节点信息" color="blue" style="padding-top:1em;">
							<smart:form id="saveForm">
								<smart:textInput type="hidden" name="treeTypeId" value="${organTreeId}"></smart:textInput>
								<smart:textInput type="hidden" name="id" value="${organNode.id}"></smart:textInput>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="组织名称：" value="${organNode.name}" autocomplete="off" placeholder="请输入组织名称" verify="required" name="name" isNotNull="true">
										</smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="组织全名：" value="${organNode.allName}" autocomplete="off" placeholder="请输入组织全名" verify="required" name="allName" isNotNull="true">
										</smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="组织编码：" value="${organNode.code}" autocomplete="off" placeholder="请输入组织编码" verify="required" name="code" isNotNull="true">
										</smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:linkSelect labelName="组织类型：" id="organNodeTypeTag" display="block"/>
									</smart:gridColumn>
									<c:if test="${organNode.people == 1}">
										<smart:gridColumn colPart="6">
											<smart:linkSelect labelName="业务类型：" id="organNodeBizTypeTag" display="block"/>
										</smart:gridColumn>
									</c:if>
									<smart:gridColumn colPart="6">
										<smart:linkSelect labelName="管理人员：" id="organNodeAdminUserTag" display="block"/>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="地址：" value="${organNode.deptAddress}" autocomplete="off" placeholder="请输入组织地址" name="deptAddress">
										</smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="传真：" value="${organNode.fax}" autocomplete="off" placeholder="请输入组织传真" name="fax">
										</smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="组织负责人：" value="${organNode.principal}" autocomplete="off" placeholder="请输入组织负责人" name="principal">
										</smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="联系电话：" value="${organNode.principalPhone}" autocomplete="off" placeholder="请输入联系电话" name="principalPhone">
										</smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:textInput labelName="显示序列：" value="${organNode.orders}" autocomplete="off" placeholder="请输入组织节点显示序列"  verify="number" name="orders">
										</smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow style="margin-top:0px;">
									<smart:gridColumn colPart="12">
										<smart:textarea name="description" labelName="描述信息" display="block">${organNode.description}</smart:textarea>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:buttonGroup container="true">
											<button class="layui-btn layui-btn-sm" type="button" lay-submit lay-filter="smart-submit" title="确认">
												<i class="fa fa-check"></i>&nbsp;确认
											</button>
											<smart:button size="sm" theme="warm" type="reset" title="重置">
												<smart:icon icon="rotate-left"></smart:icon>&nbsp;重置
						   					</smart:button>
						   					<smart:button size="sm" theme="normal" type="button" method="newRootOrganNode" title="添加根节点">
												<smart:icon icon="plus-square"></smart:icon>&nbsp;添加根节点
						   					</smart:button>
						   					<smart:button size="sm" theme="normal" type="button" method="newSubOrganNode" title="添加子节点">
												<smart:icon icon="plus-square-o"></smart:icon>&nbsp;添加子节点
						   					</smart:button>
						   					<smart:button size="sm" theme="danger" type="button" method="removeOrganNode" title="删除组织节点">
												<smart:icon icon="remove"></smart:icon>&nbsp;删除
						   					</smart:button>
										</smart:buttonGroup>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:form>
						</smart:fieldSet>
					    </div>
					    <c:if test="${organNode.people == 1}">
	    					<div class="layui-tab-item">
		    					<smart:grid>
									<smart:gridRow>
										<smart:gridColumn colPart="12">
											<smart:fieldSet title="组织系统人员查询" color="blue" style="padding-top:1em;">
												<smart:form id="securityUserSearchForm">
													<smart:gridRow>
														<smart:gridColumn colPart="4">
															<smart:textInput labelName="系统登录账号：" autocomplete="off" placeholder="请输入系统登录账号" name="loginName"></smart:textInput>
														</smart:gridColumn>
														<smart:gridColumn colPart="4">
															<smart:textInput labelName="系统用户名称：" autocomplete="off" placeholder="组输入系统用户名称" name="name"></smart:textInput>
														</smart:gridColumn>
														<smart:gridColumn colPart="4">
															<smart:buttonGroup container="true">
																<smart:button size="sm" method="searchUser" title="查询" theme="primary">
																	<smart:icon icon="searchUser"></smart:icon>&nbsp;查询
											  				 	</smart:button>
																<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
																	<smart:icon icon="history"></smart:icon>&nbsp;重置
											   					</smart:button>
															</smart:buttonGroup>
														</smart:gridColumn>
													</smart:gridRow>
												</smart:form>
											</smart:fieldSet>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="12">
											<smart:table id="securityUserList" url="system/organ/user/quey?organTreeId=${organTreeId}&organNodeId=${organNode.id}" sortField="loginName" sortType="asc" text="未找到数据！" page="true">
												<tr>
													<smart:tableItem field="loginName" width="150" sort="true">登录名</smart:tableItem>
													<smart:tableItem field="name" width="200" sort="true">姓名</smart:tableItem>
													<smart:tableItem field="sexStr" width="200" sort="true">性别</smart:tableItem>
													<smart:tableItem field="statusName" width="200" sort="true">状态</smart:tableItem>
													<smart:tableItem field="accountTypeName" width="100" sort="true">类型</smart:tableItem>
													<smart:tableItem align="center" width="100" toolbar="securityUserBar">操作</smart:tableItem>
												</tr>
												<smart:tableToolBar id="securityUserBar">
													<smart:tableToolBtn theme="warn" event="moveUser" title="移动用户">
														<smart:icon icon="car"></smart:icon>
													</smart:tableToolBtn>
												</smart:tableToolBar>
											</smart:table>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:grid>
	    					</div>
    					</c:if>
    					<c:if test="${organNode.relation == 1}">
		   					<div class="layui-tab-item">
	    						<smart:grid>
									<smart:gridRow>
										<smart:gridColumn colPart="12">
											<smart:fieldSet title="组织关系上级查询" color="blue" style="padding-top:1em;">
												<smart:form id="supOrganRelationSearchForm">
													<smart:gridRow>
														<smart:gridColumn colPart="4">
															<smart:textInput labelName="组织名称：" autocomplete="off" placeholder="请输入组织名称" name="organNodeName"></smart:textInput>
														</smart:gridColumn>
														<smart:gridColumn colPart="4">
															<smart:linkSelect labelName="组织关系类型：" id="organRelationTypeTag1" display="block"/>
														</smart:gridColumn>
														<smart:gridColumn colPart="4">
															<smart:buttonGroup container="true">
																<smart:button size="sm" method="searchSupRelation" title="查询" theme="primary">
																	<smart:icon icon="search"></smart:icon>&nbsp;查询
												  				</smart:button>
																	<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
																		<smart:icon icon="history"></smart:icon>&nbsp;重置
												   				</smart:button>
												   				<smart:button size="sm" method="newRelation" title="添加组织上级关系" theme="normal">
																	<smart:icon icon="plus"></smart:icon>&nbsp;添加
															   	</smart:button>
															</smart:buttonGroup>
														</smart:gridColumn>
													</smart:gridRow>
												</smart:form>
												<smart:table id="supOrganRelationList" url="system/organ/relation/target/query?organNodeId=${organNode.id}" sortField="organNodeAllName" sortType="asc" text="未找到数据！" page="true">
												<tr>
													<!--<smart:tableItem field="organRelationTypeCode" width="200" sort="true">组织关系类型编码</smart:tableItem>-->
													<smart:tableItem field="organRelationTypeName" width="200" sort="true">组织关系类型</smart:tableItem>
													<smart:tableItem field="organNodeAllName" width="200" sort="true">组织节点名称</smart:tableItem>
													<smart:tableItem field="description" width="100">描述信息</smart:tableItem>
													<smart:tableItem align="center" width="100" toolbar="supOrganRelationBar">操作</smart:tableItem>
												</tr>
												<smart:tableToolBar id="supOrganRelationBar">
													<smart:tableToolBtn theme="warn" event="editRelation" title="编辑组织关系">
														<smart:icon icon="pencil"></smart:icon>
													</smart:tableToolBtn>
													<smart:tableToolBtn theme="danger" event="removeRelation" title="删除组织关系">
														<smart:icon icon="remove"></smart:icon>
													</smart:tableToolBtn>
												</smart:tableToolBar>
												</smart:table>
											</smart:fieldSet>
											
										</smart:gridColumn>		
									</smart:gridRow>
									<smart:gridRow>					
										<smart:gridColumn  colPart="12">
											<smart:fieldSet title="组织关系下级查询" color="blue" style="padding-top:1em;">
												<smart:form id="subOrganRelationSearchForm">
													<smart:gridRow>
														<smart:gridColumn colPart="4">
															<smart:textInput labelName="组织名称：" autocomplete="off" placeholder="请输入组织名称" name="organNodeName"></smart:textInput>
														</smart:gridColumn>
														<smart:gridColumn colPart="4">
															<smart:linkSelect labelName="组织关系类型：" id="organRelationTypeTag2" display="block"/>
														</smart:gridColumn>
														<smart:gridColumn colPart="4">
															<smart:buttonGroup container="true">
																<smart:button size="sm" method="searchSubRelation" title="查询" theme="primary">
																	<smart:icon icon="search"></smart:icon>&nbsp;查询
											  				 </smart:button>
																<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
																	<smart:icon icon="history"></smart:icon>&nbsp;重置
											   				</smart:button>
															</smart:buttonGroup>
														</smart:gridColumn>
													</smart:gridRow>
												</smart:form>
												<smart:table id="subOrganRelationList" url="system/organ/relation/source/query?organNodeId=${organNode.id}" sortField="organNodeAllName" sortType="asc" text="未找到数据！" page="true">
													<tr>
														<!-- <smart:tableItem field="organRelationTypeCode" width="200" sort="true">组织关系类型编码</smart:tableItem> --> 
														<smart:tableItem field="organRelationTypeName" width="200" sort="true">组织关系类型</smart:tableItem>
														<smart:tableItem field="organNodeAllName" width="200" sort="true">组织节点名称</smart:tableItem>
														<smart:tableItem field="description" width="200">描述信息</smart:tableItem>
													</tr>
												</smart:table>
											</smart:fieldSet>
											
										</smart:gridColumn>
									</smart:gridRow>
								</smart:grid>
	    					</div>
    					</c:if>
  					</div>
				</div>			
			</div>
		</div>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,linkSelect,table">
		<smart:initLinkSelect id="organNodeTypeTag" name="organNodeTypeId" tips="请选择组织类型" verify="required" selected="${organNode.organNodeTypeId}" url="system/organ/node/type/query/accredit?organTreeId=${organTreeId}&organNodeId=${organNode.parentId}"/>
 		<c:if test="${organNode.people == 1}">
 			<smart:initLinkSelect id="organNodeBizTypeTag" name="bizType" tips="请选择组织业务类型" verify="required" selected="${organNode.bizType}" url="dictquery/sub/code/orgBizType" keyName="code" titleName="name"/>
	    </c:if>
	    <smart:initLinkSelect id="organNodeAdminUserTag" name="adminUserId" tips="请选择组织管理人员" selected="${organNode.adminUserId}" url="security/user/query/list" />
		<c:if test="${organNode.relation == 1}">
			<smart:initLinkSelect id="organRelationTypeTag1" name="organRelationTypeCode" tips="请选择组织关系类型" url="system/organ/relation/type/query" keyName="code" titleName="name"/>
			<smart:initLinkSelect id="organRelationTypeTag2" name="organRelationTypeCode" tips="请选择组织关系类型" url="system/organ/relation/type/query" keyName="code" titleName="name"/>
		</c:if>
		<c:if test="${organNode.people == 1}">
			<smart:tableScriptAction tableId="securityUserList">
				moveUser:function(result) {
					smart.show({
						title : '确认移动组织人员至其他组织？',
						size : 'full',
						url:'system/organ/node/user/move?organTreeId=${organTreeId}&organNodeId=${organNode.id}&userId=' + result.data.id,
						scrollbar : false
					});
				}
			</smart:tableScriptAction>
		</c:if>
		<c:if test="${organNode.relation == 1}">
	 		<smart:tableScriptAction tableId="supOrganRelationList">
				removeRelation:function(result) {
					smart.confirm({
						title:'删除组织关系',
						message:'确认删除组织关系？',
						type:'POST',
						url:'system/organ/relation/remove/save',
						params : {id : result.data.id},
						callback : function() {
							var params = smart.json($('#supOrganRelationSearchForm'));
							table.reload('supOrganRelationList', {
								where : params
							});
						}
					});
				}
				,editRelation:function(result) {
					smart.show({
						title : '修改组织关系',
						url : 'system/organ/relation/edit?organTreeId=${organTreeId}&organRelationId=' + result.data.id,
						scrollbar : false
					});				
				}
			</smart:tableScriptAction>	
			<smart:tableScriptAction tableId="subOrganRelationList">
			
			</smart:tableScriptAction>
		</c:if>
		<smart:buttonScriptAction>
			removeOrganNode:function() {
				smart.confirm({
					title:'删除组织',
					message:'确认删除组织以及组织下属相关组织节点？',
					type:'POST',
					url:'system/organ/tree/node/remove/save?id=${organNode.id}&treeTypeId=${organTreeId}',
					callback : function() {
						parent.refreshOrganNode('${organNode.parentId}');
					}
				});
			},
			newRootOrganNode : function () {
				smart.show({
					title : '新增组织节点',
					size : 'full',
					url : 'system/organ/tree/node/create?organTreeId=${organTreeId}',
					scrollbar : false
				});
			},
			newSubOrganNode : function() {
				smart.show({
					title : '新增组织节点',
					size : 'full',
					url : 'system/organ/tree/node/create?organNodeId=${organNode.id}&organTreeId=${organTreeId}',
					scrollbar : false
				});
			},
			newRelation:function () {
				smart.show({
					title : '新增组织关系',
					size:'full',
					url : 'system/organ/relation/create?organNodeId=${organNode.id}&organTreeId=${organTreeId}',
					scrollbar : false
				});
			},
			searchUser:function() {
				var params = smart.json($('#securityUserSearchForm'));
				table.reload('securityUserList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			searchSupRelation:function() {
				var params = smart.json($('#supOrganRelationSearchForm'));
				table.reload('supOrganRelationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			searchSubRelation:function() {
				var params = smart.json($('#subOrganRelationSearchForm'));
				table.reload('subOrganRelationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>
		var index = parent.layer.getFrameIndex(window.name);
		form.on('submit(smart-submit)', function(obj) {
			var mask = layer.load(1, {
			  	shade: [0.5,'#fff']
			});
			$.post('system/organ/tree/node/edit/save',
				obj.field,
				function(result) {	
					layer.close(mask);
					if (result.success) {
						layer.msg(result.message, {
							time: 1000
							,icon:1
							,anim:5
						});
						parent.refreshOrganNode('${organNode.parentId}');
						parent.layer.close(index);
					} else {							
						layer.msg(result.message, {
							time:1000
							,icon:2
							,anim:6
						});
					}
				},'json');
		});
	</smart:scriptHead>
	<script type="text/javascript" src="ztree/js/jquery-1.4.4.min.js" charset="UTF-8"></script>
	<script type="text/javascript">
		function refreshUser() {
			$('#securityUserSearchForm button[data-method="searchUser"]').click();
		}
		function refreshSupRelation() {
			$('#supOrganRelationSearchForm button[data-method="searchSupRelation"]').click();
		}
	</script>
</smart:body>
</html>