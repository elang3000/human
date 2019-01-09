<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--信息维护" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="效验基本信息" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="查询条件" color="blue">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="部门单位：" id="organNodeIdTag" display="block" />
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 		</smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   						</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow>
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofc/query/basic/info" height="full-180" sortField="name" sortType="asc" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width="100" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sexName" width="100"  sort="true">性别</smart:tableItem>
								<smart:tableItem field="idNumberStr" width="160"  sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width="160" sort="false">单位部门</smart:tableItem>
								<smart:tableItem field="nationName" width="100" sort="false">民族</smart:tableItem>
								<smart:tableItem field="years" width="100" sort="false">年龄</smart:tableItem>
								<smart:tableItem field="postLevelName" width="120" sort="false" templet="postLevelNameTpl">最高职务级别</smart:tableItem>
								<smart:tableItem field="postName" width="120" sort="false" templet="postNameTpl">最高职务</smart:tableItem>
								<smart:tableItem field="isLeaderPostName" width="100" sort="false" templet="isLeaderPostNameTpl">是否为领导职务</smart:tableItem>
								<smart:tableItem field="postYears" width="100" sort="false" templet="postYearsTpl">现任职务年限</smart:tableItem>
								<smart:tableItem field="educationName" width="160" sort="false" templet="educationNameTpl">学历</smart:tableItem>
								<smart:tableItem field="degreeName" width="160" sort="false" templet="degreeNameTpl">学位</smart:tableItem>
								<smart:tableItem field="isHoldName" width="120" sort="false">任职状态</smart:tableItem>
								<smart:tableItem align="center" width="80" fixed="right" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="ann" title="错误通知">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<script type="text/html" id="postLevelNameTpl">
  	{{#  if(d.postLevelName === 'N/A'){ }}
   		<i class="layui-icon layui-icon-close" style="font-size:30px; color:red;"></i>  
  	{{#  } else { }}
    	{{ d.postLevelName }}
  	{{#  } }}
	</script>
	<script type="text/html" id="postNameTpl">
  	{{#  if(d.postName === 'N/A'){ }}
   		<i class="layui-icon layui-icon-close" style="font-size:30px; color:red;"></i>  
  	{{#  } else { }}
    	{{ d.postName }}
  	{{#  } }}
	</script>
	<script type="text/html" id="isLeaderPostNameTpl">
  	{{#  if(d.isLeaderPostName === 'N/A'){ }}
   		<i class="layui-icon layui-icon-close" style="font-size:30px; color:red;"></i>  
  	{{#  } else { }}
    	{{ d.isLeaderPostName }}
  	{{#  } }}
	</script>
	<script type="text/html" id="postYearsTpl">
  	{{#  if(d.postYears === 'N/A'){ }}
   		<i class="layui-icon layui-icon-close" style="font-size:30px; color:red;"></i>  
  	{{#  } else { }}
    	{{ d.postYears }}
  	{{#  } }}
	</script>
	<script type="text/html" id="educationNameTpl">
  	{{#  if(d.educationName === 'N/A'){ }}
   		<i class="layui-icon layui-icon-close" style="font-size:30px; color:red;"></i>  
  	{{#  } else { }}
    	{{ d.educationName }}ssaw
  	{{#  } }}
	</script>
	<script type="text/html" id="degreeNameTpl">
  	{{#  if(d.degreeName === 'N/A'){ }}
   		<i class="layui-icon layui-icon-close" style="font-size:30px; color:red;"></i>  
  	{{#  } else { }}
    	{{ d.degreeName }}
  	{{#  } }}
	</script>	
	<smart:scriptHead models="table,form,layer,element,linkSelect">
		<smart:utils />
		<smart:initLinkSelect id="organNodeIdTag" name="organNodeId" tips="请选择所属单位部门"  url="system/organ/node/query" params="{organTreeId:'${organTreeId}'}" /> 
		<smart:tableScriptAction tableId="navigationList" checkbox="true" sort="true" rowEdit="true">
			ann : function(data) {
				smart.show({
					title : '信息维护',
					url : 'ofc/main?id=' + data.data.id,
					size : 'full',
					scrollbar : false
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
			}
		};

		$('#searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
	</smart:scriptHead>
</smart:body>
</html>