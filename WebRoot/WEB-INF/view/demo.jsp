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
	<smart:panel>
		<smart:row>
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 5px;">
				<legend>视图管理</legend>
			</fieldset>
		</smart:row>
		<smart:row>
			<form class="layui-form">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">视图代码：</label>
						<div class="layui-input-inline">
							<input name="code" autocomplete="off" class="layui-input"
								type="text" placeholder="输入查询条件" />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">视图名称：</label>
						<div class="layui-input-inline">
							<input name="name" autocomplete="off" class="layui-input"
								type="text" placeholder="输入查询条件" />
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layui-btn-normal layui-btn-sm"
							type="button" data-method="search">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</div>
			</form>
		</smart:row>
		<smart:row>
			<smart:column col="2" offset="10">
				<div class="layui-btn-group">
					<button class="layui-btn layui-btn-sm" type="button"
						data-method="add">
						<i class="fa fa-plus"></i>
					</button>
					<button class="layui-btn layui-btn-sm" type="button"
						data-method="add">
						<i class="fa fa-upload"></i>
					</button>
					<button class="layui-btn layui-btn-sm" type="button"
						data-method="add">
						<i class="fa fa-download"></i>
					</button>
				</div>
			</smart:column>
		</smart:row>
		<smart:row>
			<smart:table id="navigationList" url="../navigration/query">
				<smart:tableItem field="code" width=".25" sort="true">视图代码</smart:tableItem>
				<smart:tableItem field="name" width=".25" sort="true">视图名称</smart:tableItem>
				<smart:tableItem field="path" width=".25" sort="false">路径</smart:tableItem>
				<smart:tableItem width=".25" align="center"
					toolBarId="navListToolBar">操作</smart:tableItem>
			</smart:table>
		</smart:row>
	</smart:panel>
	<smart:tableToolBar id="navListToolBar">
		<smart:tableToolBtn theme="normal" event="view">
			<i class="fa fa-eye"></i>
		</smart:tableToolBtn>
		<smart:tableToolBtn theme="warm" event="edit">
			<i class="fa fa-edit"></i>
		</smart:tableToolBtn>
		<smart:tableToolBtn theme="danger" event="delete">
			<i class="fa fa-trash-o"></i>
		</smart:tableToolBtn>
	</smart:tableToolBar>
	<smart:scriptHead models="table,form,layer">
		var utils = {
			json : function(form) {
				var ojbect = {};
				var array = $(form).serializeArray();
				$.each(array, function() {
					if (ojbect[this.name] !== undefined) {
						if (!ojbect[this.name].push) {
							ojbect[this.name] = [ojbect[this.name]];
						}
						ojbect[this.name].push(this.value || '');
					} else {
						ojbect[this.name] = this.value || '';
					}
				});
				return ojbect;
			} 
		}
		var tableInvokeMethod = {
			view : function(data) {
				console.log(data);
			},
			delete : function(data) {
				console.log(data);
			},
			edit : function(data) {
				console.log(data);
			}
		};
		table.on('tool(navigationList)', function(obj) {
			tableInvokeMethod[obj.event] ? tableInvokeMethod[obj.event].call(this, obj.data) : '';
		});

		var buttonInvokeMethod = {
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
		};

		$('.layui-btn').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
	</smart:scriptHead>
</smart:body>
</html>