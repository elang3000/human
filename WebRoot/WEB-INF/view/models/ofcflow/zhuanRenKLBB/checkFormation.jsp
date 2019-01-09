<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--跨类别转任管理" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag />
					<smart:textInput type="hidden" name="areaType" value="${d.areaType}"></smart:textInput>
					<smart:gridRow>
						<smart:title title="转入单位编制信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<%@include file="../zhuanRenB/formation.jsp" %>
					<smart:gridRow>
						<smart:title title="校验编制" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<c:if test="${d.areaType=='1'}">
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="人员来源单位：" name="sourceOrgan.id"
									isSearch="true" display="block"
									url="system/organ/node/queryAll?organTreeId=394e21fa-1eb6-42ee-ba32-50655fa16517"
									isAddDefaltOption="true" initSelectedKey="${d.sourceOrgan.id }"
									isNotNull="true" verify="required" initDidableKey="${organCode}"></smart:singleSelect>
							</smart:gridColumn>
						</c:if>
						<smart:gridColumn colPart="4">
							<smart:textInput name="sumNumber" id="sumNumber" labelName="转任总人数" placeholder="转任总人数，自动计算"
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
							<smart:numberInput name="plusKeLeaderNum" min="0" max="1000" type="text"
								id="plusKeLeaderNum" onBlur="sumnumber()" labelName="乡科级正职（领导）转任人数"
								value="${d.plusKeLeaderNum}" display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="plusKeNoLeaderNum" min="0" type="text"
								max="1000" id="plusKeNoLeaderNum" onBlur="sumnumber()"
								labelName="乡科级正职（非领导）转任人数" value="${d.plusKeNoLeaderNum}"
								display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="minusKeLeaderNum" min="0" max="1000" type="text"
								id="minusKeLeaderNum" onBlur="sumnumber()" labelName="乡科级副职（领导）转任人数"
								value="${d.minusKeLeaderNum}" display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="minusKeNoLeaderNum" min="0" type="text"
								max="1000" id="minusKeNoLeaderNum" onBlur="sumnumber()"
								labelName="乡科级副职（非领导）转任人数" value="${d.minusKeNoLeaderNum}"
								display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="clerkNumber" min="0" max="1000" type="text"
								id="clerkNumber" onBlur="sumnumber()" labelName="科员级转任人数"
								value="${d.clerkNumber}" display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:numberInput name="cClerkNumber" min="0" max="1000" type="text"
								id="cClerkNumber" onBlur="sumnumber()" labelName="办事员级转任人数"
								value="${d.cClerkNumber}" display="block" isInt="true"></smart:numberInput>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm" title="确认"
									theme="default">
									<smart:icon icon="plus">&nbsp;确认</smart:icon>
								</smart:button>
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
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils/>
		<smart:buttonScriptAction>
			goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存
			var sumNumber = $("#sumNumber").val();
			if(!sumNumber||sumNumber==""||parseInt(sumNumber)==0){
				smart.message({
					message : "请输入转任人数！",
					type : 'W' //S保存  I问号  W感叹号 E错误
				});
				return;
			}
			smart.confirm({
				title:'校验编制',
				message:'确认提交保存吗？',
				url:'ofcflow/zrklbIntoB/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('navigationList');
					window.location.href="ofcflow/zrklbIntoB/servantList?id="+this.data;
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>