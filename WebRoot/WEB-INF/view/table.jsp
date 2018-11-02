<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
</head>
<smart:body>
	<smart:grid>
		<smart:gridRow>
			<smart:title title="用户管理" style="margin-top: 5px;" color="red" />
		</smart:gridRow>
		<smart:gridRow>
			<form class="layui-form">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">用户名称：</label>
						<div class="layui-input-block">
							<input name="name" autocomplete="off" class="layui-input"
								type="text" placeholder="输入查询条件" />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">登录名：</label>
						<div class="layui-input-block">
							<input name="loginName" autocomplete="off" class="layui-input"
								type="text" placeholder="输入查询条件" />
						</div>
					</div>
					<%-- 					<smart:singleSelect id="sex" labelName="性别"
						url="dictquery/sub/id/GBT_2260_2007/5107" isAddDefaltOption="true"></smart:singleSelect> --%>
					<smart:button theme="normal" size="sm" method="search" title="查询">
						<smart:icon icon="search"></smart:icon>
					</smart:button>

				</div>
			</form>
		</smart:gridRow>
		<smart:gridRow>
			<smart:gridColumn colPart="12" deviceType="md" colOffset="10">
				<smart:buttonGroup container="true">
					<smart:button size="sm" method="add" title="新增">
						<smart:icon icon="plus">&nbsp;新增</smart:icon>
					</smart:button>
					<smart:button theme="warm" size="sm" method="add" title="修改">
						<smart:icon icon="pencil">&nbsp;修改</smart:icon>
					</smart:button>
					<smart:button theme="danger" size="sm" method="add" title="删除">
						<smart:icon icon="trash">&nbsp;刪除</smart:icon>
					</smart:button>
				</smart:buttonGroup>
			</smart:gridColumn>
		</smart:gridRow>
		<smart:gridRow colSpace="5">
			<smart:gridColumn colPart="12" deviceType="md">
				<smart:accordionPanel id="test" isAccordion="true">
					<smart:accordionPanelItem title="ayui 更适合哪些开发者？" isShow="true">
						<smart:table id="navigationList" url="user/query" height="full"
							sortField="loginName" sortType="asc" text="未找到用户数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="loginName" width=".15" sort="true">登录名</smart:tableItem>
								<smart:tableItem field="name" width=".15" sort="true">用户名称</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true"
									templet="sexTpl">性别</smart:tableItem>
								<smart:tableItem field="email" width=".15" sort="false">邮箱地址</smart:tableItem>
								<smart:tableItem field="honePhone" width=".1" sort="false">联系电话</smart:tableItem>
								<smart:tableItem field="officePhone" width=".1" sort="false">办公电话</smart:tableItem>
								<smart:tableItem field="accountType" width=".1" sort="false"
									templet="accountTypeTpl">用户类型</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true"
									toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:accordionPanelItem>
					<smart:accordionPanelItem title="为什么JS社区大量采用未发布或者未广泛支持的语言特性？">
						<smart:fieldSet title="red" color="red">
							<div>
								<p>有不少其他答案说是因为JS太差。我下面的答案已经说了，这不是根本性的原因。但除此之外，我还要纠正一些对JS具体问题的误解。JS当初是被作为脚本语言设计的，所以某些问题并不是JS设计得差或者是JS设计者的失误。比如var的作用域问题，并不是“错误”，而是当时绝大部分脚本语言都是这样的，如perl/php/sh等。模块的问题也是，脚本语言几乎都没有模块/命名空间功能。弱类型、for-in之类的问题也是，只不过现在用那些老的脚本语言的人比较少，所以很多人都误以为是JS才有的坑。另外有人说JS是半残语言，满足不了开发需求，1999年就该死。半残这个嘛，就夸张了。JS虽然有很多问题，但是设计总体还是优秀的。——来自知乎@贺师俊</p>
							</div>
						</smart:fieldSet>
					</smart:accordionPanelItem>
				</smart:accordionPanel>
			</smart:gridColumn>
		</smart:gridRow>
	</smart:grid>
	<script type="text/html" id="sexTpl">
  			{{#  if (d.sex == 0){ }}
				男
 			{{#  } else if (d.sex == 1) { }}
    			女
  			{{#  } else { }}

			{{#  } }}
	</script>
	<script type="text/html" id="accountTypeTpl">
  			{{#  if (d.accountType == 0){ }}
				临时帐户
 			{{#  } else if (d.accountType == 1) { }}
    			普通帐户
  			{{#  } else { }}

			{{#  } }}
	</script>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true" sort="true" rowEdit="true">
			view : function(data) {
				console.log(data);
			},
			delete : function(data) {
				console.log(data);
			},
			edit : function(data) {
				console.log(data);
			},
			checkbox : function(data) {
				console.log(this);
				console.log(data);
			},
			sort : function(data) {
				console.log(this);
				console.log(data);
			},
			rowEdit : function(data) {
				console.log(data);
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
				var that = this; 
				layer.open({
			        type: 2 
			        ,title: '新增导航视图'
			        ,area: [$(window).width() / 2 + 'px', $(window).height() / 2 + 'px']
			        ,shade: [0.8, '#393D49']
			        ,maxmin: true
			        ,skin: 'layui-layer-molv'
			        ,anim: 5
			        ,isOutAnim: false
			        ,offset: 'auto'
			        ,content: '../view/nav/add.jsp'
			        ,btn: ['确认', '关闭']
			        ,yes: function(){
			          
			        }
			        ,btn2: function(){
			          	layer.closeAll();
			        }
			        ,zIndex: layer.zIndex
			        ,success: function(layero){
			        	
			        }
				});
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>