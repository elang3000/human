<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--批量参公交流" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="参公交流管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="调入情况列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/exchangeOutB/outindex" show="false" eId="" itemName="调出情况列表"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/exchangeB/flow" show="false" eId="" itemName="流程审批"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="交流区域" isAddDefaltOption="true"
										name="areaType" display="block"
										data="[{'key':'1','value':'本区'},{'key':'2','value':'外区'}]"
										isSearch="true"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="5">
									<div class="layui-form-item">
										<div class="layui-inline">
											<label class="layui-form-label" title="交流人数">交流人数：</label>
											<div class="layui-input-inline" style="width: 100px;">
												<input onblur="numberValidate(this,true);" type="number"
													autocomplete="off" class="layui-input" max="1000" min="0"
													step="1" name="sumNumberS" title="交流人数起"
													placeholder="交流人数起">
											</div>
											<div class="layui-form-mid">-</div>
											<div class="layui-input-inline" style="width: 100px;">
												<input onblur="numberValidate(this,true);" type="number"
													autocomplete="off" class="layui-input" max="1000" min="0"
													step="1" name="sumNumberE" title="交流人数止"
													placeholder="交流人数止">
											</div>
										</div>
									</div>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询"
											theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
			  				 			</smart:button>
										<smart:button size="sm" title="重置" theme="primary"
											type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
			   							</smart:button>
										<shiro:hasPermission name="ADD_EXCHANGE_BTN">
											<smart:button size="sm" method="add" title="新增">
												<smart:icon icon="plus">&nbsp;新增</smart:icon>
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
						<smart:table id="navigationList" url="ofcflow/exchangeB/pagelist"
							height="full-210" text="未获取到数据！">
							<tr>
								<smart:tableItem field="sourceOrgan" width=".1" sort="true">来源单位</smart:tableItem>
								<smart:tableItem field="areaType" width=".1" sort="true">交流区域</smart:tableItem>
								<smart:tableItem field="allowWeaveNum" width=".15" sort="false">核定编制数</smart:tableItem>
								<smart:tableItem field="realNum" width=".1" sort="true">实有人数</smart:tableItem>
								<smart:tableItem field="thisYearLackWeaveNum" width=".1" sort="true">机构缺编数</smart:tableItem>
								<smart:tableItem field="sumNumber" width=".1" sort="true">交流总人数</smart:tableItem>
								<smart:tableItem field="requestDate" width=".1" sort="false">申请日期</smart:tableItem>
								<smart:tableItem field="statusName" width=".1" sort="false">状态</smart:tableItem>
								<smart:tableItem align="center" fixed="right" width=".15" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
										{{#  if(d.status=="0"){ }}
										<shiro:hasPermission name="EDIT_EXCHANGE_BTN">
											<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
												<i class="fa fa-edit"></i>
											</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="REMOVE_EXCHANGE_BTN">
											<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete"  title="删除">
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
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			edit : function(data) {
				smart.show({
					title : '参公交流管理',
					size : 'full',
					url : "ofcflow/exchangeB/servantList?id="+data.data.id,
					scrollbar : false
				});
			}
			,delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/exchangeB/delete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			}
			,view : function(data) {
				smart.show({
					title : '参公交流管理',
					size : 'full',
					url : "ofcflow/exchangeB/view?id="+data.data.id,
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
			},
			add : function() {
				//选择本区还是外区调入
				layer.confirm('请选择交流人员来源!', {
				  btn: ['本区','外区'] //按钮
				}, function(){
					layer.closeAll('dialog'); //关闭信息框
				    smart.show({
						title : '参公交流单位校验编制',
						size : 'full',
						url : "ofcflow/exchangeB/check?areaType=1",
						scrollbar : false,
					});
				}, function(){
					layer.closeAll('dialog'); //关闭信息框
					smart.show({
						title : '新增参公交流信息',
						size : 'full',
						url : "ofcflow/exchangeB/check?areaType=2",
						scrollbar : false
					});
				});
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>