<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.showNone{
		display:none;
	}
</style>
<smart:initHead title="长宁区公务员信息管理系统--考核人员" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>

					<smart:form id="editForm" action="ofcflow/assess/saveUnitCheckDetail">
						<smart:fromTokenTag />

						<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:title title="人员基本信息" style="margin-top: 5px;" color="blue" />
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="单位部门" infovalue="${servant.departName}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
												<smart:textInput type="hidden" name="id" value="${detail.id}"></smart:textInput>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="姓名拼音" infovalue="${servant.pinYinName}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="姓名拼音缩写" infovalue="${servant.shortName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="出生日期" infovalue="${servant.birthDate}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="性别" infovalue="${servant.sex.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="民族" infovalue="${servant.nation.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="政治面貌" infovalue="${servant.politics.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:infoShowerLabel infoname="出生地" infovalue="${servant.birthPlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:infoShowerLabel infoname="籍贯" infovalue="${servant.nativePlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:infoShowerLabel infoname="成长地" infovalue="${servant.growPlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
									<smart:gridColumn colPart="3" colOffset="1">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<img style="width:150px;height:200px;min-width:150px;min-height:200px;" alt="照片" src="ftp/getImg?imgName=${servant.photoPath}">
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="婚姻状况" infovalue="${servant.marriage.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="健康状况" infovalue="${servant.health.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="健康状况描述" infovalue="${servant.healthDescribe}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="有效证件类型" infovalue="${servant.cardType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="有效证件号码" infovalue="${servant.cardTypeNo}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">

										<smart:infoShowerLabel infoname="公民身份证号" infovalue="${servant.cardNoView}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="入党时间" infovalue="${servant.joinParty}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="第二党派" infovalue="${servant.secondParty.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="第三党派" infovalue="${servant.thirdParty.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>


							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>

						<smart:title title="人员考核" style="margin-top: 5px;" color="blue" />


								<smart:gridRow>

									<smart:gridColumn colPart="12">
									<c:if test="${ assessmentType=='1'  }">
									<script type="text/javascript">
										function conclusionChange(data){
											layui.use('form',function(){
												var $ = layui.jquery;
												if(data.elem[data.elem.selectedIndex].text=="优秀"){
													$("#iscommend").parent().parent().parent().parent().show();
													$("#iscredit").parent().parent().parent().parent().show();
												}else{
													$("#iscommend").parent().parent().parent().parent().hide();
													$("#iscredit").parent().parent().parent().parent().hide();
												}

												console.log(data);
											});
										}
									</script>
									</c:if>
										<smart:continuousSelect id="conclusion" labelName="考核结论：" isNotNull="true" verify="required"   inputName="result.id" codeTypeCode="DM018" inputVal="${detail.result.id }" valType="ID"  changeCallBack="conclusionChange" widthPercent="0.3333"/>
									</smart:gridColumn>

								</smart:gridRow>

<%-- 								<smart:gridRow>
									<div <c:if test="${!result }"> style="display:none" </c:if>  >
										<smart:gridColumn colPart="4"  >
												<smart:singleSelect
														display="block" id="iscommend" initSelectedKey="${detail.iscommend }"
														labelName="是否嘉奖"  name="iscommend"  isAddDefaltOption="true"
														url="dictquery/sub/code/DM215"></smart:singleSelect>
										</smart:gridColumn>
									</div>
								</smart:gridRow> --%>

								<smart:gridRow>
									<div <c:if test="${(!result) || assessmentType=='2'  }">style="display:none"</c:if> >
										<smart:gridColumn colPart="4">
											<smart:singleSelect
											    display="block" id="iscredit" initSelectedKey="${detail.iscredit }"
												labelName="是否记三等功"  name="iscredit" isAddDefaltOption="true"
												url="dictquery/sub/code/DM215"></smart:singleSelect>
										</smart:gridColumn>
									</div>
								</smart:gridRow>
							<c:if test="${!isSeasonDown}">
									<smart:gridRow>
										<smart:gridColumn colPart="4">
											<smart:numberInput labelName="投票数：" display="block"  isNotNull="true" verify="required"
															 placeholder="请输入投票数量" name="voteNumb" value="${detail.voteNumb}"></smart:numberInput>
										</smart:gridColumn>
									</smart:gridRow>
							</c:if>
							<smart:gridRow>
								<smart:gridColumn colPart="8">
									<smart:textarea labelName="备注:" display="block" name="remarks"
										>${detail.remarks }</smart:textarea>
								</smart:gridColumn>
							</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn>
								<smart:buttonGroup container="true">
									<c:if test="${!view }">
										<smart:button size="sm" id="save" title="确定考核" other="lay-submit"
											theme="default">
											<smart:icon icon="check"></smart:icon>&nbsp;确定
			  				 			</smart:button>
		  				 			</c:if>
							     <smart:button size="sm" method="goBack" title="返回" theme="warm">
									<smart:icon icon="reply"></smart:icon>&nbsp;返回
								</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>

				</smart:gridRow>

			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:dateRender id="assessYear" type="year" />
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		form.on('submit(save)', function (data) {//表单保存

			var params=smart.json("#editForm");
			var url="ofcflow/assess/saveUnitCheckDetail";
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
		                result.message,
		                {icon: 1,closeBtn: 1 },
		                function () {
		                	parent.layui.table.reload('navigationList');
		                	var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
	                });
				}else{
					smart.message({
						message : result.message,
						type : 'E' //S保存  I问号  W感叹号 E错误
					});
				}
			});
			return false;

		});

	</smart:scriptHead>
</smart:body>
</html>