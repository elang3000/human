<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--年度考核奖励" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="年度考核奖励" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>

				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form id="jobChange_searchForm"
							action="ofcflow/assess/batchAssess">
							<smart:gridColumn colPart="3">
								<smart:textInput labelName="姓名：" display="block"
									placeholder="请输入姓名" name="name"></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="3">
										<smart:singleSelect isAddDefaltOption="true" id="result"
											isNotNull="true" verify="required" display="block"
											labelName="考核结论:" initSelectedKey="" name="id"
											url="dictquery/sub/code/DM018"></smart:singleSelect>
									</smart:gridColumn>
							<smart:gridColumn colPart="4" colOffset="1">
								<smart:buttonGroup container="true" align="left">
									<smart:button size="sm" method="search" title="查询"
										theme="primary">
										<smart:icon icon="search"></smart:icon>&nbsp;查询
		  				 			</smart:button>
									<smart:button size="sm" method="history" title="重置"
										theme="primary" type="reset">
										<smart:icon icon="history"></smart:icon>&nbsp;重置
		   						</smart:button>
		   																	<smart:button size="sm" method="batchAssess"
												title="请先选择考核结论,再批量考核!" id="save" other="lay-submit"
												theme="default">
												<smart:icon icon="rocket"></smart:icon>&nbsp;批量考核
				   							</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>

									
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table  id="navigationList"
							url="ofcflow/assess/unitCheckPageList?assessCollectId=${assessCollectId }&orgId=${orgId }"
							height="full-215" limits="10,100,300,1000" text="未找到有效数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width="200" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="orgName" width="300" sort="true">单位</smart:tableItem>
								<smart:tableItem field="cardNo" width="200" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="result" width="200" sort="false">审核结果</smart:tableItem>
								<smart:tableItem field="remarks" width="500" sort="false">备注</smart:tableItem>
								<smart:tableItem align="center" width="200"  fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="default" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
									<smart:tableToolBtn theme="warm" event="assess" title="审核">
										<smart:icon icon="edit"></smart:icon>
									</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		var ids =new Array();//存放选中复选框的id
		var table_data=new Array();
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				assess : function(data) {
					smart.show({
					title : '人员审核',
					size : 'full',
					url : 'ofcflow/assess/unitCheckPage?id='+data.data.id,
					scrollbar : false
					});
				},
				view : function(data) {
					smart.show({
					title : '人员审核查看',
					size : 'full',
					url : 'ofcflow/assess/unitCheckPage?view=true&id='+data.data.id,
					scrollbar : false
					});
				}
			</smart:tableScriptAction>
		var buttonInvokeMethod = {
			search : function() {
				var params = utils.json($('#jobChange_searchForm'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		};

		$('#jobChange_searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
		
		
		$('#jobChange_searchForm #complete').on('click', function() {
			smart.confirm({
				title:'确认考核结果',
				message:'确认考核需要完成所有人的考核,${seasonAlertStr }继续吗?',
				type:'POST',
				url:'ofcflow/assess/completeAssess/${assessCollectId }',
				callback : function() {
		                	parent.layui.table.reload('navigationList');
		                	var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
				}
			});
		});
		
		
		form.on('submit(save)', function (data) {
			var params=smart.json("#jobChange_searchForm");
			var url=data.form.action;
			if(ids.length==0){
				smart.message({
					message : "请选择需要批量考核的记录!",
					type : 'W' //S保存  I问号  W感叹号 E错误
				});
				return false;
			}
			params.ids=ids;
			smart.confirm({
				title:'批量考核',
				message:'批量考核将考核所有选中的记录,继续吗?',
				type:'POST',
				url:url,
				params : params,
				callback : function() {
					ids=new Array();
					layui.table.reload('navigationList');
					parent.layui.table.reload('navigationList');
				}
			});
			
		});
		
		 //复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
        table.on('checkbox(navigationList)', function (obj) {
           var checkStatus = table.checkStatus('navigationList').data;
              for(var i=0;i<checkStatus.length
			;i++){
                   var da=checkStatus[i];
			table_data.push(da.id);
               }
           if(obj.checked==true){
			if(obj.type==
			'one'){
                   ids.push(obj.data.id);
                }else{
	               ids.splice(0,ids.length);//清空数组
			for(var i=0;i
			<table_data.length;i++){
                       ids.push(table_data[i]);
                   }
               }
           }else{
               if(obj.type=='one'){
                   for(var i=0;i<ids.length;i++){
                      if(ids[i]==obj.data.id){
                           ids.remove(i);
                       }
                  }
               }else{
                  ids.splice(0,ids.length);//清空数组 
               }
           }
           table_data.splice(0,table_data.length);//清空数组 
        });
        
         //删除数组自定义函数
	    Array.prototype.remove=function(dx)
	    {
	        if(isNaN(dx)||dx>this.length){return false;}
	        for(var i=0,n=0;i<this.length;i++)
	        {
	            if(this[i]!=this[dx])
	            {
	                this[n++]=this[i]
	            }
	        }
	        this.length-=1
	    }
	</smart:scriptHead>
</smart:body>
</html>