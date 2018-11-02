<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
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
					<smart:breadcrumbNavMenuItem iname="选调交流" cite="true"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="修改选调交流职位" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form>
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="修改选调职位" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="用人机构" infovalue="${t.employOrgan.allName }"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构核定人数" infovalue="${t.allowWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构实有人数" infovalue="${t.realNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构年度缺编人数" infovalue="${t.thisYearLackWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="计划选调人数" infovalue="${t.planEmployNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="计划减员人数" infovalue="${t.planCutNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow colSpace="5">
						<smart:gridColumn colPart="12" deviceType="md">
							<smart:table id="postNavigationList"
								url="ofcflow/transferring/postList?planId=${t.id}" height="full" text="未找到数据！">
								<tr>
									<smart:tableItem field="name" width=".1" sort="true">职位名称</smart:tableItem>
									<smart:tableItem field="postType" width=".1" sort="true">职位类别</smart:tableItem>
									<smart:tableItem field="planEmployNum" width=".1" sort="true">选调人数</smart:tableItem>
									<smart:tableItem field="politicalStatus" width=".1" sort="false">政治面貌</smart:tableItem>
									<smart:tableItem field="professional" width=".1" sort="false">专业类别</smart:tableItem>
									<smart:tableItem field="workYear" width=".1" sort="false">最低工作年限</smart:tableItem>
									<smart:tableItem field="degree" width=".1" sort="false">最低学位要求</smart:tableItem>
									<smart:tableItem field="education" width=".1" sort="false">最低学历要求</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true"
										toolbar="navListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="navListToolBar">
									<smart:tableToolBtn theme="warm" event="edit" title="编辑">
										<smart:icon icon="edit"></smart:icon>
									</smart:tableToolBtn>
									<smart:tableToolBtn theme="danger" event="delete" title="删除">
										<smart:icon icon="trash"></smart:icon>
									</smart:tableToolBtn>
								</smart:tableToolBar>
							</smart:table>
						</smart:gridColumn>
					</smart:gridRow>
	
					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
							<smart:buttonGroup container="true">
								<smart:button size="sm" method="add" title="新增职位">
									<smart:icon icon="plus">&nbsp;新增职位</smart:icon>
								</smart:button>
								<smart:button theme="normal" size="sm" method="confirmEmployPlan" title="提交汇总 ">
									<smart:icon icon="pencil">&nbsp;提交汇总 </smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="back" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:tableScriptAction tableId="postNavigationList">
			delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/transferring/postDelete',
					params:{id:data.data.id},
					callback:postNavigationList_TableInvokeMethod.reload
				});
			},
			edit : function(data) {
				smart.show({
					title : '修改职位信息',
					size : 'full',
					url : "ofcflow/transferring/addPost",
					params:{id:data.data.id},
					scrollbar : false
				});
			}
			,reload : function(){
				table.reload('postNavigationList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
		add : function() {
			smart.show({
				title : '新增职位',
				size : 'full',
				url : "ofcflow/transferring/addPost",
				params:{tId:'${t.id}'},
				scrollbar : false
			});
		},
		confirmEmployPlan : function() {
			if(layui.table.cache.postNavigationList.length==0){
				layer.msg("请添加一个职位！");
				return;
			}
			smart.confirm({
				title:'提示',
				message:'您确定提交汇总吗？',
				url:'ofcflow/transferring/saveSt',
				params:{id:'${t.id}'},
				callback:function (){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		}
		,back : function() {
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>