<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
<!-- 录用 _拟录用名单列表 -->
<script type="text/javascript">
	var tabNum=1;
</script>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员录用" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<%@include file="modual/draftHeadTab.jsp" %>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
								<smart:gridColumn colPart="3">
									<smart:date labelName="年份" name="yearTip" id="year" placeholder="年份"></smart:date>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="姓名" display="inline" name="name"
										placeholder="姓名"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="身份证" display="inline" name="cardNo"
										placeholder="身份证"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:button theme="normal" size="sm" method="search"
										title="查询">
										<smart:icon icon="search"></smart:icon>
									</smart:button>
									<smart:button size="sm" method="batchSummary" title="批量汇总">
										<smart:icon icon="plus">&nbsp;批量汇总</smart:icon>
									</smart:button>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/draftServant/draftServantList"
							height="full-215" text="未获取到数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="yearTip" width="150" sort="true">年度</smart:tableItem>
								<smart:tableItem field="draftUnitName" width="150" sort="true">录用单位</smart:tableItem>
								<smart:tableItem field="draftDeptName" width="150" sort="true">录用部门</smart:tableItem>
								<smart:tableItem field="name" width="150" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="sex" width="150" sort="false">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width="170" sort="false">身份证</smart:tableItem>
								<smart:tableItem field="servantType" width="150" sort="false">人员类型</smart:tableItem>
								<smart:tableItem field="employComment" width="150" sort="false">录用鉴定评语</smart:tableItem>
								<smart:tableItem field="employSituation" width="100" sort="false">录用情况</smart:tableItem>
								<smart:tableItem field="employResult" width="100" sort="false">录用标识</smart:tableItem>
								<smart:tableItem align="center" width="150"  fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
<%-- 							<smart:tableToolBar id="navListToolBar"> --%>
<%-- 								<smart:tableToolBtn theme="warm" event="edit" title="编辑"> --%>
<%-- 									<smart:icon icon="edit"></smart:icon> --%>
<%-- 								</smart:tableToolBtn> --%>
<%-- 							</smart:tableToolBar> --%>
								<script type="text/html" id="navListToolBar">
										<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"  title="编辑">
											<i class="fa fa-edit"></i>
										</a>
										{{#  if(d.employSituation!=="拟录用"){ }}
										<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="summary"  title="汇总">
											<i class="fa fa-play-circle-o"></i>
										</a>
 										{{#  } }}
									</script>
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
		<smart:dateRender id="yearTip" type="year"/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			edit : function(data) {
				smart.show({
					title : '编辑录用信息',
					size : 'full',
					url : "ofcflow/draftServant/employInfoEditPage?id="+data.data.id,
					scrollbar : false
				});
			}
			,summary : function(data) {
				smart.confirm({
					title:'报送汇总',
					message:'确定报送汇总？',
					url:'ofcflow/draftServant/collectById',
					params : {"id":data.data.id},
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
			batchSummary : function() {
		    	if(ids.length == 0){
		    		smart.message({
						message : "请选择汇总数据！"
						,type : 'W' //S保存  I问号  W感叹号 E错误
					});
		    	}else{
		    		smart.confirm({
						title:'报送汇总',
						message:'确定批量报送汇总？',
						url:'ofcflow/draftServant/collectById',
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
		<smart:dateRender type="year" id="year" />
		//复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
	        table.on('checkbox(navigationList)', function (obj) {
	           var checkStatus = table.checkStatus('navigationList').data;
               for(var i=0;i<checkStatus.length;i++){
                    var da=checkStatus[i];
						table_data.push(da.id);
                }
	           if(obj.checked==true){
			   		if(obj.type=='one'){
	                   ids.push(obj.data.id);
	                }else{
		               ids.splice(0,ids.length);//清空数组 
	                   for(var i=0;i<table_data.length;i++){
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
		$(".layui-tab-title li").removeClass("layui-this");
		$(".layui-tab-title li:eq("+tabNum+")").addClass("layui-this");
	</smart:scriptHead>
</smart:body>
</html>