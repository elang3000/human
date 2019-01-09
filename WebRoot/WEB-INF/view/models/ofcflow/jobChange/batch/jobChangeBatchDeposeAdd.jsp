<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
		   uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<smart:initHead title="事项办理--职务变动" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="职务变动"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="流程审批" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag />
					<smart:textInput type="hidden" name="collectType" value="${d.collectType}"></smart:textInput>
					<smart:gridRow>
						<smart:title title="单位编制信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<%@include file="../../../zhuanRenB/formation.jsp" %>
					<smart:gridRow>
						<smart:title title="职务变动批次信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:textInput name="result" id="result" type="hidden"></smart:textInput>
						<smart:textInput name="isSubmit" id="isSubmit" type="hidden"></smart:textInput>
						<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="批次名：" name="collectName"  isNotNull="true" otherAttr="disabled"
											 verify="required"  value="${d.collectName}"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:textarea display="block" labelName="备注:" other="disabled"
											name="remark">${d.remark }</smart:textarea>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:title title="校验编制" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:textInput name="sumNumber" id="sumNumber" labelName="职务变动总人数" placeholder="总人数，自动计算"
											 value="${d.sumNumber}" display="block" otherAttr="readonly"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					<script type="text/javascript">
                        function sumnumber(){
                            layui.use('form',function(){
                                var $ = layui.jquery;
                                var plusKeLeaderNum = parseInt($("#plusKeLeaderNum").val());
                                var plusKeNoLeaderNum = parseInt($("#plusKeNoLeaderNum").val());
                                var minusKeLeaderNum = parseInt($("#minusKeLeaderNum").val());
                                var minusKeNoLeaderNum = parseInt($("#minusKeNoLeaderNum").val());
                                var clerkNumber = parseInt($("#clerkNumber").val());
                                var cClerkNumber = parseInt($("#cClerkNumber").val());

                                plusKeLeaderNum = isNaN(plusKeLeaderNum)?0:plusKeLeaderNum;
                                plusKeNoLeaderNum = isNaN(plusKeNoLeaderNum)?0:plusKeNoLeaderNum;
                                minusKeLeaderNum = isNaN(minusKeLeaderNum)?0:minusKeLeaderNum;
                                minusKeNoLeaderNum = isNaN(minusKeNoLeaderNum)?0:minusKeNoLeaderNum;
                                clerkNumber = isNaN(clerkNumber)?0:clerkNumber;
                                cClerkNumber = isNaN(cClerkNumber)?0:cClerkNumber;

                                var sum = plusKeLeaderNum + plusKeNoLeaderNum + minusKeLeaderNum + minusKeNoLeaderNum + clerkNumber + cClerkNumber;

                                $("#sumNumber").val(sum);
                            });
                        }
					</script>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="plusKeLeaderNum" min="0" max="1000" type="text" otherAttr="disabled"
											   id="plusKeLeaderNum" onBlur="sumnumber()" labelName="乡科级正职（领导）转任人数"
											   value="${d.plusKeLeaderNum}" display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="plusKeNoLeaderNum" min="0" type="text" otherAttr="disabled"
											   max="1000" id="plusKeNoLeaderNum" onBlur="sumnumber()"
											   labelName="乡科级正职（非领导）转任人数" value="${d.plusKeNoLeaderNum}"
											   display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="minusKeLeaderNum" min="0" max="1000" type="text" otherAttr="disabled"
											   id="minusKeLeaderNum" onBlur="sumnumber()" labelName="乡科级副职（领导）转任人数"
											   value="${d.minusKeLeaderNum}" display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="minusKeNoLeaderNum" min="0" type="text" otherAttr="disabled"
											   max="1000" id="minusKeNoLeaderNum" onBlur="sumnumber()"
											   labelName="乡科级副职（非领导）转任人数" value="${d.minusKeNoLeaderNum}"
											   display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="clerkNumber" min="0" max="1000" type="text" otherAttr="disabled"
											   id="clerkNumber" onBlur="sumnumber()" labelName="科员级转任人数"
											   value="${d.clerkNumber}" display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="cClerkNumber" min="0" max="1000" type="text" otherAttr="disabled"
											   id="cClerkNumber" onBlur="sumnumber()" labelName="办事员级转任人数"
											   value="${d.cClerkNumber}" display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
					</smart:gridRow>

					<smart:textInput type="hidden" name="servantIds"
									 id="servantIds"></smart:textInput>
					<c:if test="${isShowTable}">
						<smart:title title="人员转任信息列表" style="margin-top: 5px;" color="blue" />

						<smart:gridRow colSpace="5">
							<smart:gridColumn colPart="12" deviceType="md">
								<smart:buttonGroup container="true" align="right">
									<c:if test="${isStep7}">

										<button class="layui-btn layui-btn-sm" type="button"
												data-method="selectPerson" title="选择转任人员">
											<i class="fa fa-search" style=""></i>&nbsp;选择
										</button>
										<smart:button method="deleteBatch" size="sm"
													  title="删除选中人员" theme="danger">
											<smart:icon icon="trash">&nbsp;删除选中人员</smart:icon>
										</smart:button>
									</c:if>
									<c:if test="${isStep13}">
										<button class="layui-btn layui-btn-sm" type="button"
												data-method="downloadLetter" title="下载任职通知">
											<i class="fa fa-download" style=""></i>&nbsp;下载任职通知
										</button>
									</c:if>
								</smart:buttonGroup>
								<smart:table id="navigationList" doneCallBack="fixedCol"
											 url="ofcflow/jobchangeB/jobShiftList/${d.id}" text="未获取到数据！">
									<tr>
										<smart:tableItem isCheckbox="true">全选</smart:tableItem>
										<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
										<smart:tableItem field="cardNo" width=".1" sort="false">身份证号</smart:tableItem>
										<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
										<smart:tableItem field="birthDate" width=".1" sort="true">出生日期</smart:tableItem>
										<smart:tableItem field="formerPostName" width=".1" sort="true">原职务</smart:tableItem>
										<smart:tableItem field="newPostName" width=".1" sort="true">变动后职务</smart:tableItem>
										<smart:tableItem field="formerJobLevelName" width=".1" sort="true">原职级</smart:tableItem>
										<smart:tableItem field="newJobLevelName" width=".1" sort="true">变动后职级</smart:tableItem>
										<smart:tableItem align="center" fixed="right" width=".15"
														 unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
									</tr>
									<script type="text/html" id="navListToolBar">


											<c:if test="${isStep13}">
												<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="editTime"  title="编辑任职时间">
													<i class="fa fa-edit"></i>
												</a>
											</c:if>

											<c:if test="${isStep7}">
												<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
													<i class="fa fa-edit"></i>
												</a>
												<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete"  title="删除">
													<i class="fa fa-trash"></i>
												</a>
											</c:if>
										<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="view"  title="查看">
											<i class="fa fa-eye"></i>
										</a>
									</script>
								</smart:table>
							</smart:gridColumn>
						</smart:gridRow>
					</c:if>
					<smart:gridRow>
						<smart:title title="流程审批" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>






					<c:if test="${isShowTable}">
						<smart:gridRow>
							<smart:gridColumn colPart="1">

								<smart:fileUpload accept="file" buttonName="考核、测评材料文件" multiple="true" isView="${!isStep7 }" dataName="examineFilePath" auto="false" name="examineFilePath1" size="20000" data="${d.examineFilePath}"/>

								<smart:fileUpload accept="file" buttonName="任职文件" multiple="true" isView="${!isStep12 }" dataName="personInfoFilePath" auto="false" name="personInfoFilePath1" size="20000" data="${d.personInfoFilePath}"/>
							</smart:gridColumn>
						</smart:gridRow>
					</c:if>
					<smart:gridRow>
						<c:if test="${!(isStep7||isStep12) }">
							<smart:gridColumn colPart="8">
								<smart:textarea labelName="审批意见:" display="block" id="opinion"
												name="opinion" placeholder="审批意见"></smart:textarea>
							</smart:gridColumn>
						</c:if>
						<c:if test="${isStep7||isStep12 }">
							<smart:gridColumn colPart="8">
								<smart:textarea labelName="提交备注:" display="block" id="opinion"
												name="opinion" placeholder="提交备注"></smart:textarea>
							</smart:gridColumn>
						</c:if>
					</smart:gridRow>

					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<c:if test="${!(isStep7||isStep12)}">
									<smart:button method="pass" size="sm" title="审批通过" theme="normal">
										<smart:icon icon="check">&nbsp;审批通过</smart:icon>
									</smart:button>
									<smart:button method="noPass" size="sm" title="审批驳回"
												  theme="warm">
										<smart:icon icon="refresh">&nbsp;审批驳回</smart:icon>
									</smart:button>
									<smart:button method="stopPass" size="sm" title="审批不通过"
												  theme="danger">
										<smart:icon icon="minus-circle">&nbsp;审批不通过</smart:icon>
									</smart:button>
								</c:if>

								<c:if test="${ (isStep7||isStep12)}">
									<smart:button id="save" other="lay-submit" size="sm" method="add" title="提交">
										<smart:icon icon="check">&nbsp;提交</smart:icon>
									</smart:button>
								</c:if>
								<smart:button theme="primary" size="sm" method="goBack" title="返回">
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
		<smart:fileUploadUtils/>
		<smart:utils/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
								 sort="true" rowEdit="true">
			edit : function(data) {
			smart.show({
			title : '编辑职务变动信息',
			size : 'full',
			url : "ofcflow/jobchangeB/promote/"+data.data.id+"<c:if test="${isStep13}">/1</c:if>",
			scrollbar : false
			});
			},
			editTime : function(data) {
			smart.show({
			title : '编辑职务变动信息',
			size : 'full',
			url : "ofcflow/jobchangeB/promote/"+data.data.id+"<c:if test="${isStep13}">/1</c:if>",
			scrollbar : false
			});
			},
			delete : function(data) {
			smart.confirm({
			title:'提示',
			message:'您确定要删除这条记录？',
			url:'ofcflow/jobchangeB/deleteJobShiftBatch',
			params:{ids:data.data.id},
			callback:navigationList_TableInvokeMethod.reload
			});
			},
			view : function(data) {
			smart.show({
			title : '查看职务变动信息',
			size : 'full',
			url : "ofcflow/jobchangeB/promoteView/"+data.data.id,
			scrollbar : false
			});
			},
			reload : function(){
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
			url:'ofcflow/jobchangeB/deleteJobShiftBatch',
			params:{ids:ids.join(',')},
			callback:navigationList_TableInvokeMethod.reload
			});
			}
			},
			downloadLetter:function(){

			$.ajax({
			type: "post",
			url: "ofcflow/jobchangeB/validDownload?id=${d.id}",
			async:false,
			success: function(result){
			if(result.success){
			//通过form下载
			var $eleForm = $("<form method='post'></form>");

			$eleForm.attr("action","ofcflow/jobchangeB/downLoadLetter?id=${d.id}");

			$(document.body).append($eleForm);

			//提交表单，实现下载
			$eleForm.submit();
			}else{
			smart.message({
			message : result.message,
			type : 'W' //S保存  I问号  W感叹号 E错误
			});
			}
			}
			});



			},
			pass : function() {
			$("#result").val("1");//审批通过
			smart.confirm({
			title:'确认审批通过',
			message:'确认审批通过吗？',
			url:'ofcflow/jobchangeB/operatePromoteFlow',
			params : smart.json("#editForm"),
			callback : function(){
			parent.layui.table.reload('navigationList');
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			}
			});
			},
			stopPass : function() {
			$("#result").val("-1");//审批不通过
			if(!$("#opinion").val()){
			smart.message({
			message : "请输入审批不通过意见！",
			type : 'W' //S保存  I问号  W感叹号 E错误
			});
			return;
			}
			smart.confirm({
			title:'确认审批不通过',
			message:'确认审批不通过，结束业务办理？',
			url:'ofcflow/jobchangeB/operatePromoteFlow',
			params : smart.json("#editForm"),
			callback : function(){
			parent.layui.table.reload('navigationList');
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			}
			});


			},
			noPass : function() {
			$("#result").val("0");//审批不通过
			if(!$("#opinion").val()){
			smart.message({
			message : "请输入审批不通过意见！",
			type : 'W' //S保存  I问号  W感叹号 E错误
			});
			return;
			}
			smart.confirm({
			title:'确认审批不通过',
			message:'确认审批不通过吗？',
			url:'ofcflow/jobchangeB/operatePromoteFlow',
			params : smart.json("#editForm"),
			callback : function(){
			parent.layui.table.reload('navigationList');
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			}
			});
			},
			selectPerson : function() {
			smart.show({
			title : '选择人员',
			size : 'full',
			url : "orgInfo/selectServants?busClass=com.wondersgroup.human.bo.ofcflow.JobShiftB&createOrganNodeId=${d.sourceOrganNode.id}&ids="
			+$("#servantIds").val()+"&names="+$("#servantNames").val(),
			scrollbar : false,
			});
			},
			goBack : function(data) {
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
		$("#result").val("1");//审批通过
		smart.confirm({
		title:'提示',
		message:'确认提交保存吗？',
		url:'ofcflow/jobchangeB/operatePromoteFlow',
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

		setInterval(function(){
		if($('#servantIds').val()!=""){
		if($('#servantIds').val()==""){
		smart.message({
		message : "请选择需要转任的人员！",
		type : 'W' //S保存  I问号  W感叹号 E错误
		});
		return;
		}
		var requestConfig = {};
		requestConfig.url = "ofcflow/jobchangeB/addJobShiftBatch";
		requestConfig.data = {"id":'${d.id}',"ids":$('#servantIds').val()};
		$("#servantIds").val("");
		$("#servantNames").val("");
		requestConfig.success = function(data){
		layui.table.reload('navigationList');
		}
		smart.request(requestConfig);
		}

		},100);
	</smart:scriptHead>
</smart:body>
</html>