<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--公务员辞职详情" />
</head>
<smart:body>
	<smart:card>
		<smart:cardBody>
			<smart:gridRow colSpace="5">
				 <smart:gridColumn>
					<smart:title title="人员基本信息" style="margin-top: 5px;" color="blue" />
					<smart:grid>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="单位部门"
											infovalue="${servant.departName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="姓名"
											infovalue="${servant.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${servant.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="姓名拼音"
											infovalue="${servant.pinYinName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="姓名拼音缩写"
											infovalue="${servant.shortName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="出生日期"
											infovalue="${servant.birthDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="性别"
											infovalue="${servant.sex.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="民族"
											infovalue="${servant.nation.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="政治面貌"
											infovalue="${servant.politics.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:infoShowerLabel infoname="出生地"
											infovalue="${servant.birthPlaceName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:infoShowerLabel infoname="籍贯"
											infovalue="${servant.nativePlaceName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:infoShowerLabel infoname="成长地"
											infovalue="${servant.growPlaceName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:gridColumn>
							<smart:gridColumn colPart="3" colOffset="1">
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<img alt="照片" src="static/image/20170705135600.jpg">
									</smart:gridColumn>
								</smart:gridRow>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="婚姻状况"
									infovalue="${servant.marriage.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="健康状况"
									infovalue="${servant.health.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="健康状况描述"
									infovalue="${servant.healthDescribe}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="个人身份有效证件名称"
									infovalue="${servant.cardType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="个人身份有效证件号码"
									infovalue="${servant.cardTypeNo}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">

								<smart:infoShowerLabel infoname="公民身份证号"
									infovalue="${servant.cardNo}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="入党时间"
									infovalue="${servant.joinParty}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="第二党派"
									infovalue="${servant.secondParty.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="第三党派"
									infovalue="${servant.thirdParty.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:title title="" style="margin-top: 5px;" color="blue" />
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="办公电话"
									infovalue="${servant.officePhone}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="住宅电话"
									infovalue="${servant.homePhone}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="移动电话"
									infovalue="${servant.mobilePhone}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="秘书电话"
									infovalue="${servant.secretaryPhone}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="电子邮箱"
									infovalue="${servant.email}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="家庭住址"
									infovalue="${servant.homeAddress}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="户籍所在地"
									infovalue="${servant.residencePlace}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="住址邮政编码"
									infovalue="${servant.addressPost}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="参加工作日期"
									infovalue="${servant.attendDate}"></smart:infoShowerLabel>
							</smart:gridColumn>

						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="连续工龄"
									infovalue="${servant.workYear}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="专长"
									infovalue="${servant.expertise}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="爱好"
									infovalue="${servant.interested}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="基层工作单位"
									infovalue="${servant.grassRootUnit}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="基层工作经历时间（年）"
									infovalue="${servant.grassRootYear}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="从事专业"
									infovalue="${servant.doingSpecialty.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>

						<smart:title title="" style="margin-top: 5px;" color="blue" />
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="最高学历"
									infovalue="${servant.topEducation}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="最高学位"
									infovalue="${servant.topDegree}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="毕业时间"
									infovalue="${servant.graduateDateStr}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="职务名称"
									infovalue="${servant.nowPostCode.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="职务属性"
									infovalue="${servant.nowPostAttribute.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="职级名称"
									infovalue="${servant.nowJobLevel.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>

						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="管理类别"
									infovalue="${servant.levelManage.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="人员类别"
									infovalue="${servant.personType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="参照公务员法管理标识"
									infovalue="${servant.servantLaw.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>

						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="离岗退养标识"
									infovalue="${servant.isLeavePost.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="公务员登记号"
									infovalue="${servant.registerNo}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>

						<div class="layui-col-md12">
							<div class="layui-form-item">
								<label class="layui-form-label formLabel">个人基本情况备注：</label>
								<div class="layui-inline">
									<div class="layui-form-mid">${servant.personRemark}</div>
								</div>
							</div>
						</div>
					</smart:grid>
				</smart:gridColumn>

				<smart:gridColumn>
					<smart:title title="辞职信息" style="margin-top: 5px;" color="blue" />
					<smart:grid>
						<smart:form id="saveForm"
							action="ofcflow/resign/operationFlow">
							<smart:fromTokenTag/>
							<smart:gridRow>
								<smart:gridColumn>
									<smart:gridRow>
										<smart:textInput type="hidden" id="status" name="status" value="1"></smart:textInput>
										<smart:textInput type="hidden" name="servant.id" value="${servant.id}"></smart:textInput>
										<smart:textInput type="hidden" name="organ.id" value="${servant.departId}"></smart:textInput>
										<smart:textInput type="hidden" id="unitName" name="unitName" value="${servant.departName}"></smart:textInput>
										<smart:gridColumn colPart="4">
											<smart:singleSelect isNotNull="true" verify="required" name="reason.id" labelName="辞职原因：" 
												display="block" url="dictquery/sub/code/DM009/5" isAddDefaltOption="true">
											</smart:singleSelect>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:singleSelect isNotNull="true" verify="required" name="resignWhereabouts.id" labelName="辞职去向："
											 display="block" url="dictquery/sub/code/DM142" isAddDefaltOption="true"></smart:singleSelect>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:textInput labelName="辞职时间：" isNotNull="true"
												verify="required" name="resignDate" id="resignDate"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn>
											<smart:textarea labelName="备注：" name="remark" display="block"></smart:textarea>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:gridColumn>
							</smart:gridRow>

							<smart:gridRow>
								<smart:line color="blue" />
								<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
									<smart:buttonGroup container="true">
										<smart:button id="save" other="lay-submit" size="sm"
											title="提交" theme="normal">
											<smart:icon icon="check">&nbsp;提交</smart:icon>
										</smart:button>
										<smart:button size="sm" type="reset" title="重新填写">
											<smart:icon icon="refresh"></smart:icon>&nbsp;重新填写
						  			</smart:button>
										<smart:button theme="warm" size="sm" method="goBack"
											title="返回">
											<smart:icon icon="reply">&nbsp;返回</smart:icon>
										</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:grid>
				</smart:gridColumn>
			</smart:gridRow>
		</smart:cardBody>
	</smart:card>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:buttonScriptAction>
		goBack : function(data) {
			parent.location.reload(); // 父页面刷新
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			parent.layer.close(index);
		}
		</smart:buttonScriptAction>
		<smart:dateRender id="resignDate" />
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			params.result="1";
			smart.confirm({
					title:'确认提交辞职备案吗',
					message:'确认提交辞职备案吗？',
					url:url,
					params : params,
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