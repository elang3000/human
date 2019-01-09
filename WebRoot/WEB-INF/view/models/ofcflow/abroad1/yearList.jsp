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
					<smart:breadcrumbNavMenuItem iname="因公出国"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="因公出国计划" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="searchForm">
							<smart:gridColumn colPart="4">
								<smart:textInput name="startDate" labelName="开始日期："
									  id="startDate" placeholder="开始日期"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:textInput name="endDate" labelName="结束日期："
									 id="endDate" placeholder="开始日期"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="2" colOffset="1">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 		</smart:button>
									<smart:button size="sm" method="history" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   						</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:form>
						<smart:form id="fileForm">
							<smart:textInput name="id" id="planId" type="hidden"></smart:textInput>
						</smart:form>
						<smart:gridColumn colPart="12" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<shiro:hasPermission name="ADD_ABROAD_YEAR_PLAN_BTN">
								<smart:button size="sm" method="add" title="新增因公出国计划">
									<smart:icon icon="plus">&nbsp;新增因公出国计划</smart:icon>
								</smart:button>
								</shiro:hasPermission>
								<smart:button theme="warm" size="sm" method="back" title="返回单位因公出国事项">
									<smart:icon icon="pencil">&nbsp;返回单位因公出国事项</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/abroadB/year/yearplanlist"
							height="full-175" 
							text="未找到有效数据！">
							<tr>
								<smart:tableItem field="name" width=".14" sort="false">出境团组名称</smart:tableItem>
								<smart:tableItem field="country" width=".2" sort="false">出境地区</smart:tableItem>
								<smart:tableItem field="day" width=".1" sort="false">停留天数</smart:tableItem>
								<smart:tableItem field="startDate" width=".15" sort="false">开始时间</smart:tableItem>
								<smart:tableItem field="endDate" width=".15" sort="false">结束时间</smart:tableItem>
								<smart:tableItem field="state" width=".08" sort="false">状态</smart:tableItem>
								<smart:tableItem align="center"  width=".18" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<shiro:hasPermission name="EDIT_ABROAD_YEAR_PLAN_BTN">
								<smart:tableToolBtn theme="default" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								</shiro:hasPermission>
								<shiro:hasPermission name="NOTICE_ABROAD_YEAR_PLAN_BTN">
								<smart:tableToolBtn theme="normal" event="notice" title="通知单位">
									<smart:icon icon="envelope-o"></smart:icon>
								</smart:tableToolBtn>
								</shiro:hasPermission>
								<shiro:hasPermission name="REMOVE_ABROAD_YEAR_PLAN_BTN">
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
								</shiro:hasPermission>
								<shiro:hasPermission name="EXPORT_ABROAD_YEAR_PLAN_APPROVE">
								<smart:tableToolBtn theme="warm" event="createPlan" title="生成批件">
									<smart:icon icon="book"></smart:icon>
								</smart:tableToolBtn>
								</shiro:hasPermission>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
	<smart:utils />
		var startDate=laydate.render({
	    	elem:'#startDate',
	    	btns: ['clear', 'confirm'],
	    	done:function(value,date){
		    	    endDate.config.min={    	    		
		    	    	year:date.year,
		    	    	month:date.month-1,//关键
		                date:date.date,
		                hours:date.hours,
		                minutes:date.minutes,
		                seconds:date.seconds
		    	    };
	    	}
    	});
    	var endDate=laydate.render({
	    	elem:'#endDate',
	    	btns: ['clear', 'confirm'],
	    	done:function(value,date){
		    	    startDate.config.max={
		    	    		year:date.year,
		        	    	month:date.month-1,//关键
		                    date:date.date,
		                    hours:date.hours,
		                    minutes:date.minutes,
		                    seconds:date.seconds
		    	    }
	    	}
    	});
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'确认删除因公出国计划？',
					url:'ofcflow/abroadB/year/delete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			},
			createPlan : function(data) {
				smart.show({
					title : '导出因公出国计划',
					size : 'lg',
					url : 'ofcflow/abroadB/year/export?id='+data.data.id,
					scrollbar : false
				});
			},
			edit : function(data) {
				smart.show({
					title : '编辑因公出国计划',
					size : 'full',
					url : 'ofcflow/abroadB/year/edit?id='+data.data.id,
					size:'full',
					scrollbar : false
				});
			},
			notice : function(data) {
				var requestConfig = {};
				requestConfig.url = "ofcflow/abroadB/plan/validate";
				requestConfig.data = {yearPlanId:data.data.id};
				requestConfig.success = function(result){
					if (result.success) {
						smart.show({
							title : '通知单位选择',
							size : 'full',
							url : 'orgInfo/selectOrgans',
							params : {id:data.data.id,url:'ofcflow/abroadB/year/noticeOrgan'},
							size:'full',
							scrollbar : false
						});
					}
					layer.msg(result.message);
				}
				smart.request(requestConfig);
			},
			reload : function(){
				table.reload('navigationList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('#searchForm'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			add : function() {
				smart.show({
					title : '新增因公出国计划',
					size : 'full',
					url : "ofcflow/abroadB/year/edit",
					size:'full',
					scrollbar : false
				});
			},
			back : function(data) {
				window.location.href='ofcflow/abroadB/index';
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>