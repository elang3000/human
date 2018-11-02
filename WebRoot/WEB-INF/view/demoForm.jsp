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
	<smart:gridRow>
		<smart:gridColumn colPart="3">
			<smart:card>
				<smart:cardHead>
								组织树
						</smart:cardHead>
				<smart:cardBody>
					<script>
						function test(event, treeId, treeNode) {
							alert(treeNode.tId + ", " + treeNode.name);
						};
					</script>
					<smart:customDynamicTree id="treeArea" url="org/orgRelations"
						style="border:1px solid #e6e6e6;" cutSize="155" customEvent="true"
						funcType="onClick" funcName="test" />
				</smart:cardBody>
			</smart:card>
		</smart:gridColumn>
		<smart:gridColumn colPart="9">
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 5px;">
				<legend>表单组件</legend>
			</fieldset>
			<smart:form>
				<smart:continuousSelect labelName="学习专业(ID)" inputName="abc"
					codeTypeCode="GBT_16835_1997" inputVal="" valType="ID"
					widthPercent="70%" />
				<smart:continuousSelect labelName="学习专业(CODE)" inputName="abc"
					codeTypeCode="GBT_16835_1997" inputVal="" valType="CODE"
					verify="required" widthPercent="20%" />

				<smart:continuousSelect labelName="学习专业(ID值)" inputName="abc"
					codeTypeCode="GBT_16835_1997"
					inputVal="1cc73aef-b9ee-4ba2-8d74-15c02a3345c4" valType="ID"
					widthPercent="40%" />
				<smart:continuousSelect labelName="学习专业(CODE值)" inputName="abc"
					codeTypeCode="GBT_16835_1997" inputVal="100303" valType="CODE"
					verify="required" />
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">单行选择框</label>
						<div class="layui-input-block">
							<select name="interest" lay-filter="aihao">
								<option value=""></option>
								<option value="0">写作</option>
								<option value="1" selected="">阅读</option>
								<option value="2">游戏</option>
								<option value="3">音乐</option>
								<option value="4">旅行</option>
							</select>
						</div>
					</div>
					<smart:singleSelect id="myid" name="interest" other="myattr='1'"
						labelName="测试url加载字典" url="dictquery/sub/id/GBT_2260_2007/5107"
						data="[{'key':'0','value':'写作'},{'key':'1','value':'阅读'},{'key':'2','value':'游戏'},{'key':'3','value':'音乐'},{'key':'4','value':'旅行'}]"
						isAddDefaltOption="true"></smart:singleSelect>
				</div>
				<div class="layui-form-item">
					<smart:singleSelect data="${selectData }" display="block"
						labelName="测试el表达式加载"></smart:singleSelect>
					<smart:singleSelect labelName="搜索选择框2" clas="c1" id="myid2"
						name="interest" other="myattr='2'" isSearch="true"
						data="[{'key':'0','value':'写作'},{'key':'1','value':'阅读'},{'key':'2','value':'游戏'},{'key':'3','value':'音乐'},{'key':'4','value':'旅行'}]"
						isAddDefaltOption="true" initSelectedKey="3"></smart:singleSelect>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">分组选择框</label>
						<div class="layui-input-inline">
							<select name="quiz" lay-search>
								<option value="">请选择问题</option>
								<optgroup label="城市记忆">
									<option value="你工作的第一个城市">你工作的第一个城市</option>
								</optgroup>
								<optgroup label="学生时代">
									<option value="你的工号">你的工号</option>
									<option value="你最喜欢的老师">你最喜欢的老师</option>
								</optgroup>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">复选框</label>
						<div class="layui-input-block">
							<input type="checkbox" name="like[write]" title="写作"> <input
								type="checkbox" name="like[read]" title="阅读" checked="">
							<input type="checkbox" name="like[game]" title="游戏">
						</div>
					</div>
					<smart:checkbox clas="c" other="my=\"1\"" labelName="复选框2"
						name="aaa" url="dictquery/sub/id/GBT_2260_2007/5107"
						data="[{'key':'0','value':'写作'},{'key':'1','value':'阅读'},{'key':'2','value':'游戏'},{'key':'3','value':'音乐'},{'key':'4','value':'旅行'}]"
						checkedKey="2803" />
				</div>
				<div class="layui-form-item layui-form-text">
					<div class="layui-form-item">
						<label class="layui-form-label">文本域</label>
						<div class="layui-input-block">
							<textarea name="desc" placeholder="请输入内容" class="layui-textarea"
								name='a'>12阿斯顿发送到</textarea>
						</div>
					</div>
					<smart:textarea labelName="文本域组件" placeholder="请输入"
						other="my=\"1\"">测试</smart:textarea>
				</div>
				<div class="layui-form-item layui-form-text">
					图片回显测试：<img
						src="previewImg?downloadUrl=ftp://10.10.10.42/document/017b8b7a-55a3-488f-8c3a-7d12f1fc321e.png">
				</div>
				<div class="layui-form-item">
					<smart:fileUpload accept="file" dataName="dasf2"
						buttonName="自动上传单选" name="ceshi" size="1000" auto="false" />
					<smart:fileUpload accept="file" dataName="dasf" buttonName="自动上传多选"
						name="ceshi" size="1000" auto="true" multiple="true" />
					<smart:fileUpload accept="file" dataName="dasf3"
						data="${uploadData}" buttonName="不自动上传多选" name="ceshi" size="1000"
						auto="false" multiple="true" />
				</div>
				<smart:formTree dynamic="true" ruleKey="TreeA" labelName="A树"
					popupName="A树弹出框" showName="asd" hiddenName="asd12" />
				<smart:formTree dynamic="false" ruleKey="TreeB" labelName="B树"
					popupName="B树弹出框" showName="asd" hiddenName="asd12" />
				<smart:formTree dynamic="true" url="org/orgRelations"
					labelName="动态自定义树" popupName="动态自定义树" showName="asd"
					hiddenName="asd12" />

				<smart:gridRow>
					<smart:gridColumn>
						<smart:buttonGroup container="true">
							<smart:button id="save" other="lay-submit" size="sm" title="保存"
								theme="normal">
								<smart:icon icon="check"></smart:icon>&nbsp;保存
						</smart:button>
							<smart:button size="sm" type="reset" title="重新填写">
								<smart:icon icon="refresh"></smart:icon>&nbsp;重新填写
					    </smart:button>
							<smart:button size="sm" method="goBack" title="返回" theme="warm">
								<smart:icon icon="reply"></smart:icon>&nbsp;返回
						</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:form>
		</smart:gridColumn>
	</smart:gridRow>


	<smart:scriptHead models="table,form,layer,laydate,upload">
		<smart:utils />
		<smart:fileUploadUtils />
		<smart:continuousSelectAction />
		
		form.on('submit(save)', function (data) {
			alert(123);
		});
		
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