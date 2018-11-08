<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--预警预告" />
<style type="text/css">
	.inLineBlock{
		display: inline-block;	
	}
	.show_add_span{
		border-bottom:solid 1px #e7e7e7;
		height:40px;
		width: 90%;
		line-height: 40px;
	}
	.del_btn{
	    padding: 0px 5px;
	    margin: 8px 0px 0px 3px;
	    color: red;
	    font-size: 16px;
	    float: right;
		display: none; 
	}
</style>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="预警预告"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="新增方案" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem turnurl="programinfo/index" show="false"
								eId="" itemName="方案管理"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="新增方案"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:form id="editForm">
					<smart:fromTokenTag />
					<smart:textInput name="programTime" id="programTime" type="hidden"></smart:textInput>
					<smart:gridRow>
						<smart:title title="基本信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="方案名称:" name="name" isNotNull="true"
								verify="required" placeholder="方案名称"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect isNotNull="true" verify="required"
								data="[{'key':'1','value':'预警方案'},{'key':'2','value':'预告方案'}]"
								name="programType" labelName="方案类型：" display="block"
								isAddDefaltOption="true"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="12">
							<smart:textarea labelName="方案描述：" name="describe" display="block"></smart:textarea>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="12">
							<smart:textInput labelName="通知内容：" display="block" name="newsContent" isNotNull="true" verify="required"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="10">
							<smart:textInput labelName="方案执行时间：" name="programTimeDescribe" id="programTimeDescribe"
								isNotNull="true" verify="required" otherAttr="readonly"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="1">
							<smart:buttonGroup container="true">
								<smart:button size="sm" method="setTime" title="设置时间"
									theme="normal">
									<smart:icon icon="clock-o"></smart:icon>&nbsp;设置时间
		  				 		</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:title title="方案详情" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:checkbox isRadio="true" display="block2" filter="programCode" name="programCode" checkedKey="1" data="[{'key':'1','value':'公务员基本信息'},{'key':'2','value':'单位基本信息'}]"/>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="3">
							<script type="text/javascript">
								function treeClick(event, treeId, treeNode) {
									if(treeNode.level=='2'){
										var h = "<div class='show_add_span'> "+treeNode.parentName+"-"+treeNode.name+"：";
										if(treeNode.colType=='1'){//字符串
											h += "<select name='operation' style='display:inline-block;margin-left: 5px;height: 30px;margin-top: 3px;'><option value ='=' >=</option><option value ='gt' >></option><option value='lt' ><</option><option value='like' >like</option></select>";
											h += "<input name='condition' type='text' class='cgDataType' style='margin-left:5px;height: 26px;' lay-verify='required'/>";
										}else if(treeNode.colType=='2'){//日期
											h += "<select style='display:inline-block;margin-left: 5px;height: 30px;margin-top: 3px;'><option value ='=' >=</option><option value ='gt' >></option><option value='lt' ><</option></select>";
										}else if(treeNode.colType=='3'){//数据字典
											h += "<select style='display:inline-block;margin-left: 5px;height: 30px;margin-top: 3px;'><option value ='=' >=</option></select>";
											
										}
										h += " <button class='del_btn'>x</button><input name='colName' value='"+treeNode.tableName+"."+treeNode.code+
										"' type='hidden'><input name='tableName' value='"+treeNode.tableName+"' type='hidden'><input name='colType' value='"+treeNode.colType+"' type='hidden'><input name='colNameb' value='"+treeNode.name+"' type='hidden'></div>";
										$("#sqlDiv").append(h);
									}
								}
							</script>
							<div id="programTreeDiv1">
								<smart:customDynamicTree id="programTree1" url="programinfo/tree?type=1" style="border:1px solid #e6e6e6;" customEvent="true" funcType="onClick" funcName="treeClick"/>
							</div>
							<div id="programTreeDiv2" style="display: none;">
								<smart:customDynamicTree id="programTree2" url="programinfo/tree?type=2" style="border:1px solid #e6e6e6;" customEvent="true" funcType="onClick" funcName="treeClick"/>
							</div>
						</smart:gridColumn>
						<smart:gridColumn colPart="8" colOffset="1">
							  <div id="sqlDiv" style="min-height:60px;height:auto;"></div>
		                      <div style="border-top:solid 1px #c6d3de;width: 90%;" >
			                       <span style="float: left;margin-top: 7px;">符号：</span>
			                       <smart:checkbox isRadio="true" externalClass="layui-form-item inLineBlock" display="block2" filter="logic" name="logic" data="[{'key':'OR','value':'或者'},{'key':'AND','value':'并且'},{'key':'(','value':'左括号'},{'key':')','value':'右括号'}]"/>
		                      </div>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<smart:button id="submit" other="lay-submit" size="sm"
									title="添加" theme="default">
									<smart:icon icon="plus">&nbsp;添加</smart:icon>
								</smart:button>
								<smart:button size="sm" method="goBack" title="返回"
									theme="warm">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:buttonScriptAction>
			setTime : function() {
				smart.show({
					title : '设置时间',
					size : 'lg',
					url : "programinfo/setTime",
					scrollbar : false
				});
			}
			,goBack : function() {
				window.location.href="programinfo/index";
			}
		 </smart:buttonScriptAction>
		 form.on('radio(programCode)', function (data) {//切换人员信息和单位信息
		 	$("#programCode").val(data.value);
		 	$("#sqlDiv").html("");//清空设置好的sql
		 	if(data.value=="1"){//人员基本信息
		 		$("#programTreeDiv1").show();
		 		$("#programTreeDiv2").hide();
		 	}else if(data.value=="2"){//单位基本信息
		 		$("#programTreeDiv2").show();
		 		$("#programTreeDiv1").hide();
		 	}
		 })
		 form.on('radio(logic)', function (data) {//添加逻辑运算
		 	$("#sqlDiv").append("<div class='show_add_span'> "+data.value+" <button class='del_btn'>x</button>"+
		 	"<input name='operation' type='hidden'><input name='condition' value='"+data.value+"' type='hidden'>"+
		 	"<input name='colName' type='hidden'><input name='tableName' type='hidden'>"+
		 	"<input name='colType' value='logic' type='hidden'><input name='colNameb' type='hidden'>"+
		 	"</div>");
		 })
		 //点击删除
	   $(document).on('click', '.del_btn', function(){
		   $(this).parent(".show_add_span").remove();
		});
	   
	   //鼠标附上去 显示 删除按钮
	   $(document).on('mouseover', '.show_add_span', function(){
		   $(this).children(".del_btn").css("display","block");
		});
	   $(document).on('mouseout', '.show_add_span', function(){
		   $(this).children(".del_btn").css("display","none");
		});
		form.on('submit(submit)', function (data) {//表单提交
 			smart.confirm({
				title:'添加方案',
				message:'确认添加该方案吗？',
				url:'programinfo/save',
				params : $("#editForm").serializeArray(),
				callback : function(){
					window.location.href="programinfo/index";
				}
			});
		});
	</smart:scriptHead>
	<script type="text/javascript">
		function getTimeValues(name,time){
			layui.use('layer',function(){
				var $ = layui.jquery,layer=layui.layer;
				$("#programTimeDescribe").val(name);
				$("#programTime").val(time);
				layer.closeAll();
			});
		}
	</script>
</smart:body>
</html>