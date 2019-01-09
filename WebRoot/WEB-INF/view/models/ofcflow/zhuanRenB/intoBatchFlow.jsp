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
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="同类别转任管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:title title="转入单位编制信息" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<%@include file="../zhuanRenB/formation.jsp" %>
				<smart:gridRow>
					<smart:title title="校验编制信息" style="margin-top: 5px;" color="blue" />
				</smart:gridRow>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${d.id}"></smart:textInput>
					<smart:textInput type="hidden" name="result" id="result" ></smart:textInput>
					<smart:gridRow>
						<c:if test="${d.areaType=='1'}">
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="人员来源单位"
								infovalue="${d.sourceOrgan.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</c:if>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="转任总人数 "
								infovalue="${d.sumNumber}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<c:if test="${d.status>2 }">
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="报道日期" infovalue="${d.enterTheUnitDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</c:if>
						<c:if test="${isFlow&&d.status==2}">
							<smart:gridColumn colPart="4">
								<smart:date labelName="报道日期：" display="block" isNotNull="true"
									name="enterTheUnitDate" id="enterTheUnitDate" verify="required"
									value="${d.enterTheUnitDate}"></smart:date>
							</smart:gridColumn>
						</c:if>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="乡科级正职（领导）转任人数"
								infovalue="${d.plusKeLeaderNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="乡科级正职（非领导）转任人数"
								infovalue="${d.plusKeNoLeaderNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="乡科级副职（领导）转任人数"
								infovalue="${d.minusKeLeaderNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="乡科级副职（非领导）转任人数"
								infovalue="${d.minusKeNoLeaderNum}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="科员级转任人数 "
								infovalue="${d.clerkNumber}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="办事员级转任人数"
								infovalue="${d.cClerkNumber}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
	
					<smart:title title="人员转任信息列表" style="margin-top: 5px;" color="blue" />
	
					<smart:gridRow colSpace="5">
						<smart:gridColumn colPart="12" deviceType="md">
							<smart:table id="navigationList" url="ofcflow/zrtlbIntoB/intoServantList?id=${d.id}" text="未获取到数据！">
								<tr>
									<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
									<smart:tableItem field="cardNo" width=".1" sort="false">身份证号</smart:tableItem>
									<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
									<smart:tableItem field="birthDate" width=".1" sort="true">出生日期</smart:tableItem>
									<smart:tableItem field="formerUnitName" width=".1" sort="false">原工作单位</smart:tableItem>
									<smart:tableItem field="jobLevelName" width=".1" sort="true">职级名称</smart:tableItem>
									<smart:tableItem field="isLeaderView" width=".1" sort="true">职级属性</smart:tableItem>
									<smart:tableItem field="postName" width=".1" sort="true">拟任职务</smart:tableItem>
									<smart:tableItem field="causeType" width=".1" sort="true">转任事由</smart:tableItem>
									<smart:tableItem align="center" fixed="right" width=".1"
										unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
								</tr>
								<script type="text/html" id="navListToolBar">
								<c:if test="${d.status==6}">
									<a class="layui-btn layui-btn-xs layui-btn-default" lay-event="edit"  title="编辑">
										<i class="fa fa-edit"></i>
									</a>
								</c:if>
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
						<c:if test="${d.status<6 }">
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
									<c:if test="${d.status<6 }">
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
									<c:if test="${d.status==6 }">
										<smart:button id="confirm" other="lay-submit" size="sm" title="同意"
												theme="normal">
											<smart:icon icon="check">&nbsp;同意</smart:icon>
										</smart:button>
									</c:if>
									<c:if test="${d.status==7 }">
										<smart:button id="save" other="lay-submit" size="sm" title="确认"
												theme="normal">
											<smart:icon icon="check">&nbsp;确认</smart:icon>
										</smart:button>
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
		<smart:dateRender id="enterTheUnitDate" />
		<smart:fileUploadUtils/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			edit : function(data) {
				smart.show({
					title : '编辑转入信息',
					size : 'full',
					url : "ofcflow/zrtlbIntoB/servantView?isFlow=${isFlow}&status=${d.status}&areaType=${d.areaType}&id="+data.data.id,
					scrollbar : false
				});
			}
			,view : function(data) {
				smart.show({
					title : '查看转入信息',
					size : 'full',
					url : "ofcflow/zrtlbIntoB/servantView?status=${d.status}&areaType=${d.areaType}&id="+data.data.id,
					scrollbar : false
				});
			}
			,reload : function(){
				table.reload('navigationList');
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			download : function() {
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
				<c:if test="${d.areaType eq '1' }">
					url:'ofcflow/zrtlbIntoB/operationFlow',
				</c:if>
				<c:if test="${d.areaType eq '2' }">
					url:'ofcflow/zrtlbIntoB/operationFlowOuter',
				</c:if>
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
				<c:if test="${d.areaType eq '1' }">
					url:'ofcflow/zrtlbIntoB/operationFlow',
				</c:if>
				<c:if test="${d.areaType eq '2' }">
					url:'ofcflow/zrtlbIntoB/operationFlowOuter',
				</c:if>
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
				<c:if test="${d.areaType eq '1' }">
					url:'ofcflow/zrtlbIntoB/operationFlow',
				</c:if>
				<c:if test="${d.areaType eq '2' }">
					url:'ofcflow/zrtlbIntoB/operationFlowOuter',
				</c:if>
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
					<c:if test="${d.areaType eq '1' }">
						url:'ofcflow/zrtlbIntoB/operationFlow',
					</c:if>
					<c:if test="${d.areaType eq '2' }">
						url:'ofcflow/zrtlbIntoB/operationFlowOuter',
					</c:if>
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
					<c:if test="${d.areaType eq '1' }">
						url:'ofcflow/zrtlbIntoB/operationFlow',
					</c:if>
					<c:if test="${d.areaType eq '2' }">
						url:'ofcflow/zrtlbIntoB/operationFlowOuter',
					</c:if>
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