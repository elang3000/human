<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区人事管理信息系统--机构编控列表" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="编制管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="机构编控" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:gridColumn colPart="3">
						<smart:card>
							<smart:cardHead>
								<blockquote class="layui-elem-quote" style="padding:5px;">
									<div>&nbsp;&nbsp;&nbsp;组织结构树</div>
								</blockquote>
							</smart:cardHead>
							<smart:cardBody>
								<script>
									function loadListById(event, treeId,treeNode) {
										layui.use('table',function(){
											var table = layui.table;
											table.reload('formationControlList', {
												where : {organId:treeNode.id},
												page : {
													curr : 1
												}
											});
										});
									};
									
									function getZTreeObj(){
										return $.fn.zTree.getZTreeObj("treeArea");
									}
								</script>
								<smart:customDynamicTree id="treeArea" url="org/orgRelations"
									style="border:1px solid #e6e6e6;" cutSize="155"
									customEvent="true" funcType="onClick" funcName="loadListById" />
							</smart:cardBody>
						</smart:card>
					</smart:gridColumn>
					<smart:gridColumn colPart="9">
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:buttonGroup container="true" align="right">
									<smart:button size="sm" method="editBtn" title="编控设置">
										<smart:icon icon="pencil-square-o">&nbsp;编控设置</smart:icon>
									</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow colSpace="5">
							<smart:gridColumn>
								<smart:table id="formationControlList" url="formationControl/pageList" text="未找到编控数据！" page="true" height="full-155">
									<tr>
										<smart:tableItem field="orgInfo" width=".2">编控单位</smart:tableItem>
										<smart:tableItem field="isOpenControl" width=".2">是否进行编控</smart:tableItem>
										<smart:tableItem field="isLowToHigh" width=".2">是否高职低配</smart:tableItem>
										<smart:tableItem field="overflowNum" width=".2">编制溢出人数</smart:tableItem>
										<smart:tableItem field="overflowRule" width=".2">编制溢出规则</smart:tableItem>
									</tr>
								</smart:table>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>

	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="formationControlList" checkbox="true"
			sort="true" rowEdit="true">    
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			editBtn : function() {
				var zTreeOjb = getZTreeObj();
				var selectedNodes = zTreeOjb.getSelectedNodes();
				if(selectedNodes.length==0){
					smart.message({
						message : "请在左侧选择机构节点",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
				}
				var organId = selectedNodes[0].id;
				smart.show({
					title:'编控设置',
					size:'full',
					url:'formationControl/edit',
					params:{
						organId:organId
					},
					scrollbar:false
				});
			}
		</smart:buttonScriptAction>	
	</smart:scriptHead>
</smart:body>
</html>