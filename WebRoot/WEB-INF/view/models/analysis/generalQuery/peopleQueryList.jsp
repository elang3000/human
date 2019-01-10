<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="查询统计与分析--综合查询" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="查询统计与分析"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="综合查询" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>

				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="true" eId="" itemName="人员综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="analysis/general/generalQueryList"
								show="false" eId="" itemName="公务员综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="" show="false" eId=""
								itemName="事业人员综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="" show="false" eId=""
								itemName="国企职工综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="" show="false" eId=""
								itemName="社工综合查询"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>

				<smart:form id="searchForm">
					<div id="query" class="layui-fluid">
						<smart:gridRow>
							<smart:fieldSet title="查询条件" color="blue">
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
										<smart:textInput labelName="单位部门：" autocomplete="off"
											placeholder="输入单位部门" name="departName">
										</smart:textInput>
									</smart:gridColumn>

									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="人员类型：" display="block"
											name="itype" data="[{'key':'1','value':'公务员'},{'key':'2','value':'事业人员'},{'key':'3','value':'国企职工'},{'key':'4','value':'社工'}]"
											isAddDefaltOption="true">
										</smart:singleSelect>
									</smart:gridColumn>

									<smart:gridColumn colPart="4" colOffset="0">
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
								</smart:gridRow>
							</smart:fieldSet>
						</smart:gridRow>
					</div>
				</smart:form>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList"
							url="analysis/general/peoplequery" height="full-240"
							text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="name" width=".08" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".08" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".16" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".11" sort="false">单位部门</smart:tableItem>
								<smart:tableItem field="postName" width=".12" sort="false">职务名称</smart:tableItem>
								<smart:tableItem field="postAttributeName" width=".12"
									sort="false">职务属性</smart:tableItem>
								<smart:tableItem field="jobLevel" width=".12" sort="false">职级名称</smart:tableItem>
								<smart:tableItem field="isOnHold" width=".11" sort="false">人员状态</smart:tableItem>
								<smart:tableItem field="peopleTypeName" width=".1" sort="false">人员类型</smart:tableItem>
<%-- 								<smart:tableItem align="center" fixed="right" width=".1" --%>
<%-- 									unresize="true" toolbar="navListToolBar">操作</smart:tableItem> --%>
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
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:continuousSelectAction />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				view : function(data) {
					smart.show({
					title : '公务员信息',
					size : 'full',
					url : 'ofc/main/view?id='+data.data.id,
					scrollbar : false,
				});
				}
			</smart:tableScriptAction>
		var buttonInvokeMethod = {
			search : function(){
				var params = utils.json($('.layui-form'));
				var ageMin = $('#ageMin').val();
				var ageMax = $('#ageMax').val();
				if(ageMin!=''&&ageMax!=''){
					if(Number(ageMin)>Number(ageMax)){
						layer.alert(
		                '年龄上限不能大于年龄下限', 
		                {icon: 0,closeBtn:0 });
						return false;
					}
				}
				$.each(params, function(i) {
					if(params[i] instanceof Array){
						$.each(params[i],function(j,value){
						     if(params[i][j] ==''){
						     	params[i][j] = ' ';//如果为空，则赋值一个空格
						     }
						});
						params[i] = params[i].join(',');//数组转成为字符串
					}
				});
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
				
				$('#advancedQuery').hide();
			},
		}

		$('#searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
		

	</smart:scriptHead>
	<script type="text/javascript" src="ztree/js/jquery-1.4.4.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript">

	</script>
</smart:body>
</html>