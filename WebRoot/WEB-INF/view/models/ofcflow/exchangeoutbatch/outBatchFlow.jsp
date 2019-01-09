<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--参公交流" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="参公交流管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<smart:textInput type="hidden" name="result" id="result" ></smart:textInput>
	
					<smart:title title="人员参公交流信息列表" style="margin-top: 5px;" color="blue" />
	
					<smart:gridRow colSpace="5">
						<smart:gridColumn colPart="12" deviceType="md">
							<smart:table id="navigationList" 
								url="ofcflow/exchangeOutB/outServantList?id=${d.id}" text="未获取到数据！">
								<tr>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="cardNo" width=".1" sort="false">身份证号</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="birthDate" width=".1" sort="true">出生日期</smart:tableItem>
								<smart:tableItem field="nation" width=".1" sort="true">民族</smart:tableItem>
								<smart:tableItem field="sourceOrgan" width=".1" sort="false">转出单位</smart:tableItem>
								<smart:tableItem field="category" width=".1" sort="false">调出本单位类别</smart:tableItem>
								<smart:tableItem field="outDate" width=".1" sort="false">调出本单位日期</smart:tableItem>
								<smart:tableItem field="gotoUnitName" width=".1" sort="false">调往单位名称</smart:tableItem>
									<smart:tableItem align="center" fixed="right" width=".1"
										unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
								</tr>
								<script type="text/html" id="navListToolBar">
								{{#  if(d.status=="1"){ }}
									<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
										<i class="fa fa-edit"></i>
									</a>
								{{#  } }}
								<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="view"  title="查看">
									<i class="fa fa-eye"></i>
								</a>
								</script>
							</smart:table>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="1">
							<smart:fileUpload accept="file" isView="true" buttonName="附件" dataName="filePath" auto="false" name="filePathName" size="20000" multiple="true" number="7" data="${d.filePath}"/>
						</smart:gridColumn>
					</smart:gridRow>
					<c:if test="${isFlow}">
						<c:if test="${d.status>1&&d.status<6 }">
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:textarea name="opinion" id="opinion" labelName="审批意见" display="block"></smart:textarea>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
					</c:if>
					<smart:gridRow>
						<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<c:if test="${isFlow}">
									<c:if test="${d.status>1&&d.status<6 }">
										<smart:button id="pass" other="lay-submit" size="sm" title="审批通过"
												theme="normal">
											<smart:icon icon="check">&nbsp;审批通过</smart:icon>
										</smart:button>
										<smart:button id="noPass" other="lay-submit" size="sm" title="审批驳回"
											theme="warm">
											<smart:icon icon="refresh">&nbsp;审批驳回</smart:icon>
										</smart:button>
										<smart:button id="stopPass" other="lay-submit" size="sm" title="审批不通过"
											theme="danger">
											<smart:icon icon="minus-circle">&nbsp;审批不通过</smart:icon>
										</smart:button>
									</c:if>
									<c:if test="${d.status==1||d.status==6 }">
										<smart:button id="confirm" other="lay-submit" size="sm" title="确认"
												theme="normal">
											<smart:icon icon="check">&nbsp;确认</smart:icon>
										</smart:button>
									</c:if>
									<c:if test="${d.status==6}">
										<smart:button method="download" size="sm" title="下载介绍信"
 												theme="danger"> 
 											<smart:icon icon="download">&nbsp;下载介绍信</smart:icon> 
 										</smart:button> 
 									</c:if> 
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
	<smart:scriptHead models="table,form,layer,element,upload,laydate">
		<smart:fileUploadUtils/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			
			edit : function(data) {
				smart.show({
					title : '编辑转出信息',
					size : 'full',
					url : "ofcflow/exchangeOutB/servantEdit?id="+data.data.id,
					scrollbar : false
				});
			},
			view : function(data) {
				smart.show({
					title : '查看转出信息',
					size : 'full',
					url : "ofcflow/exchangeOutB/servantView?status=${d.status}&id="+data.data.id,
					scrollbar : false
				});
			}
			,reload : function(){
				table.reload('navigationList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			download : function(data) {
				//通过form下载
	            var $eleForm = $("<form method='post'></form>");
	 
	            $eleForm.attr("action","ofcflow/material/downLoad?id=${ids}&name=${introductionName}");
	 
	            $(document.body).append($eleForm);
	 
	            //提交表单，实现下载
	            $eleForm.submit();
			},
			search : function() {
				var params = utils.json($('.layui-form'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
			,goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 </smart:buttonScriptAction>
		 form.on('submit(pass)', function (data) {//表单保存
			$("#result").val("1");//审批通过
			smart.confirm({
				title:'确认审批通过',
				message:'确认审批通过吗？',
				url:'ofcflow/exchangeOutB/operationFlow',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		  form.on('submit(noPass)', function (data) {//表单保存
			$("#result").val("0");//审批驳回
			if(!$("#opinion").val()){
				smart.message({
					message : "请输入审批驳回意见！",
					type : 'W' //S保存  I问号  W感叹号 E错误
				});
				return;
			}
			smart.confirm({
				title:'确认审批驳回',
				message:'确认审批驳回至上一办理人员？',
				url:'ofcflow/zrtlbOutB/operationFlow',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		 form.on('submit(stopPass)', function (data) {//表单保存
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
				url:'ofcflow/zrtlbOutB/operationFlow',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		form.on('submit(save)', function (data) {//表单保存
			$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/exchangeOutB/operationFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
		});
		form.on('submit(confirm)', function (data) {//表单保存
			$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/exchangeOutB/operationFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
		});
	</smart:scriptHead>
</smart:body>
</html>