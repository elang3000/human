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
							<smart:tabPanelItem show="true" eId="" itemName="公务员综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="" show="false" eId="" itemName="事业人员综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="" show="false" eId="" itemName="国企职工综合查询"></smart:tabPanelItem>
							<smart:tabPanelItem turnurl="" show="false" eId="" itemName="社工综合查询"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:form id="searchForm">
				<div id = "query" class="layui-fluid">
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
									<smart:singleSelect labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
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
									<div class="layui-form-item">
									     <label class="layui-form-label">
									      	年龄：   
									     </label>
									    <div class="layui-input-block">
									      <input type="text"  onblur="numberValidate(this);" style="width:47%;float:left" class="layui-input" name="ageMin" id="ageMin" autocomplete="off" placeholder="年龄下限">
										  <span  style="float:left">--</span>
									      <input type="text"  onblur="numberValidate(this);" style="width:47%;float:left" class="layui-input" name="ageMax" id="ageMax" autocomplete="off" placeholder="年龄上限">
										</div>
									</div>
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
				   				<smart:button size="sm" method="chaxun" title="高级查询"
											theme="normal">
											<smart:icon icon="search"></smart:icon>&nbsp;高级查询
				   					</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
								
							</smart:gridRow>
						
					</smart:fieldSet>
				</smart:gridRow>
				</div>
				
				<div class="layui-layer layui-layer-iframe" id="advancedQuery" style="display:none;z-index: 19891015; width: 100%;height:100%;top: 0px;">
				<div id = "" class="layui-fluid">
					<fieldset class="layui-elem-field" style="border-color:rgb(30, 159, 255);margin-top:1em;padding-left:2em;padding-right:2em;">
					<legend>高级查询条件</legend>
					<div>
					<div style="" class="layui-row">
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">查询条件：</label>	
									<div class="layui-input-block">
									<select name="code1" mysign url="analysis/general/queryList/G" lay-filter="business" lay-search initselectedkey="3">
									<option value="">请选择</option> 
 									</select>
	 								</div>
 							</div>
						</div>
							
						<div class="layui-col-md2">
							<div class="layui-form-item">
								<label class="layui-form-label" style="width: 60px;text-align:left">操作符：</label>	
									<div class="layui-input-block" style="margin-left: 90px;">
									<select>
									<option value="3">请选择</option> 
									<option value="1"><</option> 
									<option value="2"><=</option> 
									<option value="3">=</option> 
									<option value="4">>=</option> 
									<option value="5">></option> 
									<option value="6">like</option> 
 									</select>
	 								</div>
 							</div>
						</div>
									
						<div class="layui-col-md4">
							<div class="layui-form-item">
   								<div class="layui-input-inline" style="width: 100%;">
									<input type="text" autocomplete="off" class="layui-input"  lay-verify="required" placeholder="输入查询内容">
								</div>
			 				</div>
						</div>
						
						
						
						<div class="layui-col-md2">
							<div class="layui-btn-container" style="padding-top: 1px;">
								<div class="layui-inline" style="margin-left:2rem">
									<button class="layui-btn   layui-btn-normal layui-btn-sm btn-success" type="button"  title="增加" onclick="addController(this);">
										<i class="fa fa-plus   " style=""></i>
				  				 </button>
								</div>
							
								<div class="layui-inline">
									<button style="display:none" class="layui-btn   layui-btn-danger layui-btn-sm btn-danger" type="button"  title="删除" onclick="deleteController(this);">
										<i class="fa fa-minus   " style=""></i>
				   					</button>
								</div>
							</div>
						</div>
						</div>
						</div>
						
						<div style="" class="layui-row">
						<div class="layui-col-md2 layui-col-md-offset10">
							<div class="layui-btn-container" style="padding-top: 6px;">
								<div class="layui-inline">
									<button class="layui-btn   layui-btn-primary layui-btn-sm" type="button" data-method="search" title="查询" >
											<i class="fa fa-search   " style=""></i>&nbsp;查询
				  				 	</button>
								</div>
								<div class="layui-inline">
									<button class="layui-btn   layui-btn-primary layui-btn-sm" type="button" data-method="back" title="返回" >
											<i class="fa fa-search   " style=""></i>&nbsp;返回
				  				 	</button>
								</div>
							</div>
						</div>
						</div>
					</fieldset>		
				</div>
				</div>
				</smart:form>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList" url="analysis/general/query"
							height="full-240" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="name" width=".08" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".08" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".16" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".11" sort="false">单位部门</smart:tableItem>
								<smart:tableItem field="postName" width=".12" sort="false">职务名称</smart:tableItem>
								<smart:tableItem field="postAttributeName" width=".12" sort="false">职务属性</smart:tableItem>
								<smart:tableItem field="jobLevel" width=".12" sort="false">职级名称</smart:tableItem>
								<smart:tableItem field="isOnHold" width=".11" sort="false">人员状态</smart:tableItem>
								<smart:tableItem align="center" fixed="right" width=".1" unresize="true"
									toolbar="navListToolBar">操作</smart:tableItem>
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
		<smart:continuousSelectAction/>
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
			chaxun : function() {
				$('#advancedQuery').show();
			},
			back :function() {
				$('#advancedQuery').hide();
			},
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
		
		form.on('select(business)', function(data){
			var object = data.elem; //得到select原始DOM对象
			var index=object.selectedIndex ;
			var value=object.options[index].value;//获取被选中的select值
			var address = 'analysis/general/info/'+value;
			var info;
			//查询该id对应条件对象
			$.ajax({ 
			        type: "post", 
			        url: address, 
			        async:false, 
			        success: function(result){ 
			        	info = result;
					}
			});
			
			var divNode = object.parentNode.parentNode.parentNode.nextElementSibling.firstElementChild;
			var divNode2 = object.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling.firstElementChild;
			var divNodeHtml = divNode.outerHTML;
			var frameNode = divNode.parentNode;
			var frameNode2 = divNode2.parentNode;
			$(divNode).remove();//删除内容input框
			$(divNode2).remove();//删除内容input框
			var i ='';//拼串值
			var j ='';//拼串值
			var select;
			
			if(info!=null){
				var name = info.name;
				var type = info.type;
				var code = info.code;
				var url = info.url;
				var codeId = info.codeId;
				
				//对应4种类型不同类型对应不同input框拼串+layui渲染
				if(type=='String'){
					i +='<div class="layui-form-item"><div class="layui-input-inline" style="width: 100%;">'+
							'<input type="text" class="layui-input" autocomplete="off" placeholder="输入查询内容" name="code2">'+
						'</div></div>';
					
					j +='<div class="layui-form-item">'+
								'<label class="layui-form-label" style="width: 60px;text-align:left">操作符：</label>'+	
									'<div class="layui-input-block" style="margin-left: 90px;">'+
									'<select name="code3">'+
									'<option value="3">请选择</option>'+ 
									'<option value="3">=</option>'+
									'<option value="6">like</option>'+
 									'</select>'+
	 								'</div>'+
						'</div>';
				}else if(type=='Number'){
					i +='<div class="layui-form-item"><div class="layui-input-inline" style="width: 100%;">'+
							'<input onblur="numberValidate(this);"  type="text" autocomplete="off" class="layui-input"  placeholder="请输入查询内容" name="code2">'+
						'</div></div>';
						
					j +='<div class="layui-form-item">'+
								'<label class="layui-form-label" style="width: 60px;text-align:left">操作符：</label>'+	
									'<div class="layui-input-block" style="margin-left: 90px;">'+
									'<select name="code3">'+
									'<option value="3">请选择</option>'+ 
									'<option value="1"><</option>'+ 
									'<option value="2"><=</option>'+ 
									'<option value="3">=</option>'+
									'<option value="4">>=</option>'+ 
									'<option value="5">></option>'+ 
 									'</select>'+
	 								'</div>'+
						'</div>';
						
				}else if(type=='Dict'){
					i +='<div class="layui-form-item"><div class="layui-input-inline" style="width: 100%;"><select  name="code2">'+
							'<option value="">请选择</option>'+ 
					$.ajax({ 
				        type: "post", 
				        url: url, 
				        async:false, 
				        success: function(result2){ 
				        	select = result2;
						}
					});
					select.forEach(function(value,l){
						i += '<option value="'+select[l].id+'">'+select[l].name+'</option>';
					});
				 	i += '</select></div></div>';
				 	
				 	j +='<div class="layui-form-item">'+
								'<label class="layui-form-label" style="width: 60px;text-align:left">操作符：</label>'+	
									'<div class="layui-input-block" style="margin-left: 90px;">'+
									'<select name="code3">'+
									'<option value="3">=</option>'+
 									'</select>'+
	 								'</div>'+
						'</div>';
				}else if(type=='Date'){
					i +='<div class="layui-form-item">'+
							'<div class="layui-input-inline" style="width: 100%;"><input type="text" class="layui-input" name="code2" id="'+codeId+'"></div>'+
						'</div>';
						
					j +='<div class="layui-form-item">'+
								'<label class="layui-form-label" style="width: 60px;text-align:left">操作符：</label>'+	
									'<div class="layui-input-block" style="margin-left: 90px;">'+
									'<select name="code3">'+
									'<option value="3">请选择</option>'+ 
									'<option value="1"><</option>'+ 
									'<option value="2"><=</option>'+ 
									'<option value="3">=</option>'+
									'<option value="4">>=</option>'+ 
									'<option value="5">></option>'+ 
 									'</select>'+
	 								'</div>'+
						'</div>';
				}else if(type=='Dict1'){
					i +='<div class="layui-form-item continuousSelect" val_type="ID" code_type_code="'+url+'" widthpercent="0.3" issaveshowname="true" allorlast="last"> '+
						'<input type="hidden" name="code2" value=""> '+	
						'<div class="layui-input-inline" style="width:165px"> '+	
						'<select name="interest" lay-filter="continuous">  '+	
						'<option value="">请选择</option>'+ 
					$.ajax({ 
				        type: "post", 
				        url: 'dictquery/sub/code/'+url, 
				        async:false, 
				        success: function(result2){ 
				        	select = result2;
						}
					});
					select.forEach(function(value,l){
						i += '<option value="'+select[l].id+'">'+select[l].name+'</option>';
					});
				 	i += '</select></div></div>';
				 	
				 	j +='<div class="layui-form-item">'+
								'<label class="layui-form-label" style="width: 60px;text-align:left">操作符：</label>'+	
									'<div class="layui-input-block" style="margin-left: 90px;">'+
									'<select name="code3">'+
									'<option value="3">=</option>'+
 									'</select>'+
	 								'</div>'+
						'</div>';
				}else if(type=='Dict2'){
					i +='<div class="layui-form-item continuousSelect" val_type="ID" code_type_code="'+url+'" widthpercent="0.24" issaveshowname="true" allorlast="last"> '+
						'<input type="hidden" name="code2" value=""> '+	
						'<div class="layui-input-inline" style="width:107px"> '+	
						'<select lay-filter="continuous">  '+	
						'<option value="">请选择</option>'+ 
					$.ajax({ 
				        type: "post", 
				        url: 'dictquery/sub/code/'+url, 
				        async:false, 
				        success: function(result2){ 
				        	select = result2;
						}
					});
					select.forEach(function(value,l){
						i += '<option value="'+select[l].id+'">'+select[l].name+'</option>';
					});
				 	i += '</select></div></div>';
				 	
				 	j +='<div class="layui-form-item">'+
								'<label class="layui-form-label" style="width: 60px;text-align:left">操作符：</label>'+	
									'<div class="layui-input-block" style="margin-left: 90px;">'+
									'<select name="code3">'+
									'<option value="3">=</option>'+
 									'</select>'+
	 								'</div>'+
						'</div>';
				}
					
			}else{
				//选择请选择时，拼串成input text类型不带name
				i +='<div class="layui-form-item"><div class="layui-input-inline" style="width: 100%;">'+
						'<input type="text" class="layui-input" autocomplete="off" placeholder="输入查询内容">'+
					'</div></div>';
					
				j +='<div class="layui-form-item">'+
								'<label class="layui-form-label" style="width: 60px;text-align:left">操作符：</label>'+	
									'<div class="layui-input-block" style="margin-left: 90px;">'+
									'<select>'+
									'<option value="">请选择</option>'+
									'<option value="1"><</option>'+
									'<option value="2"><=</option>'+
									'<option value="3">=</option>'+
									'<option value="4">>=</option>'+ 
									'<option value="5">></option>'+ 
									'<option value="6">like</option>'+ 
 									'</select>'+
	 								'</div>'+
						'</div>';
			}
			$(frameNode).append(j);//拼串加载父类下面
			$(frameNode2).append(i);//拼串加载父类下面
			form.render();//渲染layui
			//为时间类型的时候渲染laydate
			if(type=='Date'){
				var time = '#'+codeId+'';
				laydate.render({
					elem: time,
					format: 'yyyy-MM-dd'
				});
			}
		});

	</smart:scriptHead>
	<script type="text/javascript" src="ztree/js/jquery-1.4.4.min.js" charset="UTF-8"></script>
	<script type="text/javascript">
		function addController(object) {
			var divNode = object.parentNode.parentNode.parentNode.parentNode;
			var divNodeHtml = divNode.outerHTML;
			var frameNode = divNode.parentNode;
			$(frameNode).append(divNodeHtml);
			$(divNode).find('button.btn-success').css('display','none');
			$(divNode).find('button.btn-danger').css('display','inline-block');
			$(frameNode.lastChild).find('button.btn-success').css('display','inline-block');
			$(frameNode.lastChild).find('button.btn-danger').css('display','inline-block');
			layui.form.render();
		}
		
		function deleteController(object) {
			var divNode = object.parentNode.parentNode.parentNode.parentNode;
			var frameNode = divNode.parentNode;
			frameNode.removeChild(divNode);
			if (frameNode.children.length > 1) {
				$(frameNode.lastChild).find('button.btn-success').css('display','inline-block');
				$(frameNode.lastChild).find('button.btn-danger').css('display','inline-block');
			} else {
				$(frameNode).find('button.btn-success').css('display','inline-block');
				$(frameNode).find('button.btn-danger').css('display','none');
			}
			layui.form.render();
		}
	</script>
</smart:body>
</html>