<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<smart:title title="转入单位编制信息" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<%@include file="../zhuanRenB/formation.jsp"%>
				<smart:gridRow>
					<smart:title title="校验编制信息" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<smart:gridRow>
					<c:if test="${d.areaType=='1'}">
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="人员来源单位"
								infovalue="${d.sourceOrgan.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</c:if>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="参公交流总人数 "
							infovalue="${d.sumNumber}"></smart:infoShowerLabel>
					</smart:gridColumn>
				</smart:gridRow>
				<smart:gridRow>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="乡科级正职（领导）参公交流人数"
							infovalue="${d.plusKeLeaderNum}"></smart:infoShowerLabel>
					</smart:gridColumn>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="乡科级正职（非领导）参公交流人数"
							infovalue="${d.plusKeNoLeaderNum}"></smart:infoShowerLabel>
					</smart:gridColumn>
				</smart:gridRow>
				<smart:gridRow>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="乡科级副职（领导）参公交流人数"
							infovalue="${d.minusKeLeaderNum}"></smart:infoShowerLabel>
					</smart:gridColumn>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="乡科级副职（非领导）参公交流人数"
							infovalue="${d.minusKeNoLeaderNum}"></smart:infoShowerLabel>
					</smart:gridColumn>
				</smart:gridRow>
				<smart:gridRow>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="科员级参公交流人数 "
							infovalue="${d.clerkNumber}"></smart:infoShowerLabel>
					</smart:gridColumn>
					<smart:gridColumn colPart="4">
						<smart:infoShowerLabel infoname="办事员级参公交流人数"
							infovalue="${d.cClerkNumber}"></smart:infoShowerLabel>
					</smart:gridColumn>
				</smart:gridRow>

				<%-- 				<c:if test="${d.areaType=='1'}"> --%>
				<%-- 					<smart:title title="添加参公交流人员" style="margin-top: 5px;" color="blue" /> --%>
				<%-- 					<smart:form action="ofcflow/exchangeB/savePeople"> --%>
				<%-- 						<smart:fromTokenTag/> --%>
				<%-- 						<smart:grid> --%>
				<%-- 							<smart:gridRow> --%>
				<%-- 								<smart:gridColumn colPart="12" deviceType="md"> --%>
				<!-- 									<div class="layui-form-item"> -->
				<!-- 										<div class="layui-input-block" style="margin-left: 0px;"> -->
				<%-- 											<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput> --%>
				<%-- 											<smart:textInput type="hidden" name="servantIds" --%>
				<%-- 												id="servantIds"></smart:textInput> --%>
				<!-- 											<input type="text" class="layui-input" -->
				<!-- 												autocomplete="off" id="servantNames" readonly="readonly" -->
				<!-- 												style="width: 80%; display: inline-block;"> -->
				<!-- 											<button class="layui-btn layui-btn-sm" type="button" -->
				<!-- 												data-method="selectPerson" title="选择参公交流人员"> -->
				<!-- 												<i class="fa fa-search" style=""></i>&nbsp;选择 -->
				<!-- 											</button> -->
				<!-- 											<button lay-filter="add" id="add" lay-submit="" -->
				<!-- 												class="layui-btn layui-btn-normal layui-btn-sm" -->
				<!-- 												type="button" data-method="" title="确认添加参公交流人员"> -->
				<!-- 												<i class="fa fa-plus" style=""></i>&nbsp;确认添加 -->
				<!-- 											</button> -->
				<!-- 										</div> -->
				<!-- 									</div> -->
				<%-- 								</smart:gridColumn> --%>
				<%-- 							</smart:gridRow> --%>
				<%-- 						</smart:grid> --%>
				<%-- 					</smart:form> --%>
				<%-- 				</c:if> --%>

				<smart:title title="人员参公交流信息列表" style="margin-top: 5px;"
					color="blue" />
				<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput> 
				<smart:textInput type="hidden" name="servantIds"  id="servantIds"></smart:textInput>  
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:buttonGroup container="true" align="right">
							<c:if test="${d.areaType=='1'}">
								<smart:button method="selectPerson" size="sm" title="选择参公交流人员"
									theme="normal">
									<smart:icon icon="plus">&nbsp;选择参公交流人员</smart:icon>
								</smart:button>
							</c:if>
							<c:if test="${d.areaType=='2'}">
								<smart:button method="addPerson" size="sm" title="新增参公交流人员"
									theme="normal">
									<smart:icon icon="plus">&nbsp;新增参公交流人员</smart:icon>
								</smart:button>
							</c:if>
							<smart:button method="deleteBatch" size="sm" title="删除选中人员"
								theme="danger">
								<smart:icon icon="trash">&nbsp;删除选中人员</smart:icon>
							</smart:button>
						</smart:buttonGroup>
						<smart:table id="navigationList"
							url="ofcflow/exchangeB/intoServantList?id=${d.id}" text="未获取到数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="cardNo" width=".15" sort="false">身份证号</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="birthDate" width=".1" sort="true">出生日期</smart:tableItem>
								<smart:tableItem field="nation" width=".1" sort="true">民族</smart:tableItem>
								<smart:tableItem field="personType" width=".1" sort="false">人员类别</smart:tableItem>
								<smart:tableItem field="sourceOrganName" width=".15"
									sort="false">原工作单位</smart:tableItem>
								<smart:tableItem align="center" fixed="right" width=".15"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<script type="text/html" id="navListToolBar">
								<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
									<i class="fa fa-edit"></i>
								</a>
								<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete"  title="删除">
									<i class="fa fa-trash"></i>
								</a>
								<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="view"  title="查看">
									<i class="fa fa-eye"></i>
								</a>
							</script>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
				<smart:form id="editForm">
					<smart:fromTokenTag />
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<smart:gridRow>
						<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="1">
							<smart:fileUpload accept="file" buttonName="附件"
								dataName="filePath" auto="false" name="filePathName"
								size="20000" multiple="true" number="7" data="${d.filePath}" />
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<smart:button id="submit" other="lay-submit" size="sm"
									title="提交" theme="normal">
									<smart:icon icon="check">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button id="save" other="lay-submit" size="sm" title="暂存"
									theme="default">
									<smart:icon icon="plus">&nbsp;暂存</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>

			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,upload">
		var ids =new Array();//存放选中复选框的id
		<smart:fileUploadUtils />
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			edit : function(data) {
				smart.show({
					title : '编辑参公交流信息',
					size : 'full',
					url : "ofcflow/exchangeB/servantEdit?id="+data.data.id,
					scrollbar : false
				});
			}
			,delete : function(data) {
				smart.confirm({
					title:'提示',
					message:'您确定要删除这条记录？',
					url:'ofcflow/exchangeB/servantDelete',
					params:{id:data.data.id},
					callback:navigationList_TableInvokeMethod.reload
				});
			}
			,view : function(data) {
				smart.show({
					title : '查看参公交流信息',
					size : 'full',
					url : "ofcflow/exchangeB/servantView?id="+data.data.id,
					scrollbar : false
				});
			}
			,reload : function(){
				table.reload('navigationList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
		deleteBatch : function() {
				if(ids.length == 0){
		    		smart.message({
						message : "请选择需要删除的人员！"
						,type : 'W' //S保存  I问号  W感叹号 E错误
					});
		    	}else{
					smart.confirm({
						title:'提示',
						message:'您确定要删除选中的记录？',
						url:'ofcflow/exchangeB/servantDelete',
						params:{id:ids.join(',')},
						callback:navigationList_TableInvokeMethod.reload
					});
				}
			},
			search : function() {
				var params = utils.json($('.layui-form'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
			selectPerson : function() {
			    smart.show({
					title : '选择人员',
					size : 'full',
					url : "orgInfo/selectServants?busClass=com.wondersgroup.human.bo.ofcflow.ReferenceExchange&createOrganNodeId=${d.sourceOrgan.id}&ids="
					+$("#servantIds").val()+"&names="+$("#servantNames").val(),
					scrollbar : false,
					closefunction:saveselectedservant
				});
			}
			,addPerson : function(data) {
				smart.show({
					title : '新增参公交流人员',
					size : 'full',
					url : "ofcflow/exchangeB/servantEdit?batchId=${d.id}",
					scrollbar : false
				});
			}
			,goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
		 form.on('submit(add)', function (data) {//添加人员
		 	if(data.field.servantIds==""){
		 		smart.message({
					message : "请选择需要参公交流的人员！",
					type : 'W' //S保存  I问号  W感叹号 E错误
				});
				return;
		 	}
		 	var requestConfig = {};
			requestConfig.url = data.form.action;
			requestConfig.data = data.field;
			requestConfig.success = function(data){
				$("#servantIds").val("");
				$("#servantNames").val("");
				layui.table.reload('navigationList');
			};
			requestConfig.false = function(data){
				smart.message({
					message : data.message,
					type : 'E' //S保存  I问号  W感叹号 E错误
				});
				return;
			};
			smart.request(requestConfig);
		});
		form.on('submit(save)', function (data) {//表单保存
			smart.confirm({
				title:'保存参公交流信息',
				message:'确认提交保存吗？',
				url:'ofcflow/exchangeB/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
 		form.on('submit(submit)', function (data) {//表单提交
 			$("#result").val("1");
 			smart.confirm({
				title:'保存参公交流信息',
				message:'确认提交保存吗？',
				<c:if test="${d.areaType eq '1' }">
					url:'ofcflow/exchangeB/operationFlow',
				</c:if>
		<c:if test="${d.areaType eq '2' }">
					url:'ofcflow/exchangeB/operationFlowOuter',
				</c:if>
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		//复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
		Array.prototype.remove = function(val) { 
			var index = this.indexOf(val); 
				if (index > -1) { 
					this.splice(index, 1); 
				}
		};
        table.on('checkbox(navigationList)', function (obj) {
        	var cArr = table.cache.navigationList;//获取当前列表所有数据
        	cArr.forEach(function(value,i){
        		var c = cArr[i];
        		if(c.LAY_CHECKED){//选中状态
        			if(ids.indexOf(c.id)==-1){//如果不存在数组中，则添加
				     	ids.push(c.id);//id
                 	}
        		}else{//未选中状态，从数组中删除
		       		ids.remove(c.id);
        		}
	     	});
        });
        
        function saveselectedservant(){
         if($('#servantIds').val()!=""){
		    	if($('#servantIds').val()==""){
			 		smart.message({
						message : "请选择需要参公交流的人员！",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
			 	}
			 	var requestConfig = {};
	        	requestConfig.url = "ofcflow/exchangeB/savePeople";
				requestConfig.data = {"id":'${d.id}',"servantIds":$('#servantIds').val()};
		   		$("#servantIds").val("");
				$("#servantNames").val("");
				requestConfig.success = function(data){
					if(data.success)
					{
						layui.table.reload('navigationList');
					}
					else{
						smart.message({
						message : data.message,
						type : 'E' //S保存  I问号  W感叹号 E错误
						});
					return;
					}

				
				}
				smart.request(requestConfig);
        	}
        }
	</smart:scriptHead>
</smart:body>
</html>