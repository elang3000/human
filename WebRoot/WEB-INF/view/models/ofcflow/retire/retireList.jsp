<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--退休管理" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="退休管理" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="查询条件" color="blue">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="姓名：" autocomplete="off"
										placeholder="输入姓名" name="name">
									</smart:textInput>
								</smart:gridColumn>

								<smart:gridColumn colPart="4">
									<smart:textInput labelName="身份证号：" autocomplete="off"
										placeholder="输入身份证号" name="cardNo">
									</smart:textInput>
								</smart:gridColumn>

								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="性别：" display="block"
										name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003"
										isAddDefaltOption="true">
									</smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>

							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:numberInput labelName="年龄：" min="0" 
										type="text" id="age" name="age" placeholder="输入年龄" display="block"></smart:numberInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="2" colOffset="2">
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
								<smart:gridColumn colPart="2" colOffset="1">
									<smart:button size="sm" method="add" title="退休申请">
										<smart:icon icon="plus">&nbsp;退休申请</smart:icon>
									</smart:button>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>

				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/retire/query"
							height="full-295" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".2" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".1" sort="false">单位部门</smart:tableItem>
								<smart:tableItem field="postName" width="120" sort="false">职务名称</smart:tableItem>
								<smart:tableItem field="postAttributeName" width="120" sort="false">职务属性</smart:tableItem>
								<smart:tableItem field="jobLevel" width="120" sort="false">职级名称</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true"
									toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="search1" title="详情">
									<smart:icon icon="search"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
var ids =new Array();
var table_data=new Array();
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				search1 : function(data) {
					smart.show({
					title : '公务员详情',
					size : 'full',
					url : 'ofcflow/retire/retireDetail?servantId='+data.data.id,
					scrollbar : false,
					});
				}
			</smart:tableScriptAction>
		var buttonInvokeMethod = {
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
				if(ids.length<1){
					layer.alert(
	                "请先选择人员", 
	                {icon: 0,closeBtn:0 });
	                return false;
				}
				b = ids.join(',')
				smart.show({
					title : '退休申请',
					size : 'lg',
					url : 'ofcflow/retire/applyRetire?ids='+b,
					scrollbar : false,
				});
			}
		};

		$('#searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
		
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