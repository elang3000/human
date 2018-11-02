<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
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
					<smart:breadcrumbNavMenuItem iname="选调交流" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="选调交流"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="ofcflow/transferring/summaryList"
								show="false" eId="" itemName="选调交流汇总"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridColumn colPart="4">
								<smart:singleSelect name="status" isAddDefaltOption="true" labelName="状态"  data="${statusList}"></smart:singleSelect>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:button theme="normal" size="sm" method="search"
									title="查询">
									<smart:icon icon="search"></smart:icon>
								</smart:button>
							</smart:gridColumn>
						</smart:form>
						<smart:gridColumn colPart="12" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<smart:button size="sm" method="add" title="新增">
									<smart:icon icon="plus">&nbsp;新增选调交流</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="summary"
									title="选调交流汇总">
									<smart:icon icon="pencil">&nbsp;选调交流汇总</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList"
							url="ofcflow/transferring/pageList" height="full-315" text="未找到数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="recruitOrgan" width=".1" sort="false">选调机构</smart:tableItem>
								<smart:tableItem field="employOrgan" width=".1" sort="false">用人机构</smart:tableItem>
								<smart:tableItem field="allowWeaveNum" width=".1" sort="true">核定编制数</smart:tableItem>
								<smart:tableItem field="realNum" width=".1" sort="true">实有人数</smart:tableItem>
								<smart:tableItem field="thisYearLackWeaveNum" width=".1" sort="true">年度缺编人数</smart:tableItem>
								<smart:tableItem field="planCutNum" width=".1" sort="true">计划减员人数</smart:tableItem>
								<smart:tableItem field="planEmployNum" width=".1" sort="true">计划选调人数</smart:tableItem>
								<smart:tableItem field="recuritType" width=".1" sort="false">编制类型</smart:tableItem>
								<smart:tableItem field="status" width=".1" sort="true">状态</smart:tableItem>
								<smart:tableItem field="remark" width=".2" sort="false">备注</smart:tableItem>
								<smart:tableItem field="reback" width=".1" sort="false">退回备注</smart:tableItem>
								<smart:tableItem align="center" width=".2" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="report" title="职位上报">
									<smart:icon icon="cloud-upload"></smart:icon>
								</smart:tableToolBtn>
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
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils/>
		var ids =new Array();//存放选中复选框的id
		var table_data=new Array();
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			report : function(data) {
				smart.show({
					title : '修改选调职位',
					size : 'full',
					url : "ofcflow/transferring/post",
					params:{id:data.data.id},
					scrollbar : false
				});
			},
			delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/transferring/delete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			},
			edit : function(data) {
				smart.show({
					title : '修改选调计划',
					size : 'full',
					url : "ofcflow/transferring/plan",
					params:{id:data.data.id},
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
			,add : function() {
				smart.show({
					title : '新增选调交流',
					size : 'full',
					url : "ofcflow/transferring/plan",
					scrollbar : false
				});
			}
			,summary : function(data) {
				if(ids.length == 0){
		    		smart.message({
						message : "请选择要汇总的选调计划！"
						,type : 'W' //S保存  I问号  W感叹号 E错误
					});
		    	}else{
		    		smart.confirm({
						title:'汇总',
						message:'确定汇总选中选调计划？',
						url:'ofcflow/transferring/summary',
						params : {"id":ids.join(",")},
						callback : function(data){
							var params = utils.json($('.layui-form'));
							table.reload('navigationList', {
								where : params,
								page : {
									curr : 1
								}
							});
						}
					});
		    	}
			}
		 </smart:buttonScriptAction>
		//复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
        table.on('checkbox(navigationList)', function (obj) {
           table_data.splice(0,table_data.length);//清空数组 
           var checkStatus = table.checkStatus('navigationList').data;
           for(var i=0;i<checkStatus.length;i++){
                 var da=checkStatus[i];
			     table_data.push(da.id);
           }
           ids=table_data;
        });
	</smart:scriptHead>
</smart:body>
</html>