
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--基本信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
				<smart:form id="editForm" action="publicInstitution/savePublicInst">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:title title="人员基本信息" style="margin-top: 5px;" color="blue" />
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="8">
									<smart:gridRow>
										<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名" infovalue="${pubinst.name}"></smart:infoShowerLabel>
												<smart:textInput type="hidden" name="id" value="${pubinst.id}"></smart:textInput>
										</smart:gridColumn>
										<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别" infovalue="${pubinst.sex.name}"></smart:infoShowerLabel>
										</smart:gridColumn>
									</smart:gridRow>
									
									<smart:gridRow>
												<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="出生日期" infovalue="${pubinst.birthDate}"></smart:infoShowerLabel>
												
<%-- 												<smart:date isNotNull="true" verify="required" labelName="出生日期：" value="${pubinst.birthDate}" name="birthDate" id="birthDate" display="block"></smart:date>
 --%>											</smart:gridColumn>
												<smart:gridColumn colPart="6">
												<smart:textInput labelName="姓名拼音：" name="pinYinName" value="${pubinst.pinYinName}"></smart:textInput>
										</smart:gridColumn>	
									</smart:gridRow>
										<%-- <smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="单位部门" infovalue="${pubinst.departName}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:continuousSelect verify="required" id="nativePlace" labelName="籍贯：" inputName="nativePlace.id" codeTypeCode="GBT_2260_2007" inputVal="${pubinst.nativePlace.id}" valType="ID" widthPercent="0.4" />
											</smart:gridColumn>
										</smart:gridRow> --%>
										
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:singleSelect  name="nation.id" labelName="民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：" initSelectedKey="${pubinst.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="政治面貌：" name="politics.id" initSelectedKey="${pubinst.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
											</smart:gridColumn>
										</smart:gridRow>
										
										<smart:gridRow >
											<smart:gridColumn colPart="12">
												<smart:continuousSelect isNotNull="true" verify="required" id="birthPlace" labelName="出生地：" inputName="birthPlace.id" codeTypeCode="GBT_2260_2007" inputVal="${pubinst.birthPlace.id}" valType="ID" widthPercent="0.4" isSaveShowName="true" inputShowName="birthPlaceName"/>
											</smart:gridColumn>
										</smart:gridRow>
										
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect isNotNull="true" verify="required" id="nativePlace" labelName="籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯：" inputName="nativePlace.id" codeTypeCode="GBT_2260_2007" inputVal="${pubinst.nativePlace.id}" valType="ID" widthPercent="0.4" isSaveShowName="true" inputShowName="nativePlaceName"/>
											</smart:gridColumn>
										</smart:gridRow>
										
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect isNotNull="true" verify="required" id="growPlace" labelName="成长地：" inputName="growPlace.id" codeTypeCode="GBT_2260_2007" inputVal="${pubinst.growPlace.id}" valType="ID" widthPercent="0.4" isSaveShowName="true" inputShowName="growPlaceName"/>
											</smart:gridColumn>
										</smart:gridRow>
										
									</smart:gridColumn>
									<smart:gridColumn colPart="3" colOffset="1">
										<smart:gridRow>
											<smart:gridColumn colPart="3" colOffset="1">
												<smart:gridRow>
													<smart:headPic imgId="headImg"
														photostrInputId="photostrInput" headBtnId="headBtn"
														initPhotoPath="${pubinst.photoPath}"
														photostrInputName="photoPath" />
												</smart:gridRow>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="婚姻状况：" name="marriage.id" initSelectedKey="${pubinst.marriage.id}" display="block" url="dictquery/sub/code/GBT_2261_2_2003" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="健康状况：" name="health.id" initSelectedKey="${pubinst.health.id}" display="block" url="dictquery/sub/code/GBT_2261_3_2003" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="健康描述：" name="healthDescribe" value="${pubinst.healthDescribe}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isNotNull="true" verify="required" labelName="有效证件：" initSelectedKey="${pubinst.cardType.id}" name="cardType.id" display="block" url="dictquery/sub/code/DM075"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput isNotNull="true" verify="required" labelName="证件号码：" name="cardTypeNo" value="${pubinst.cardTypeNo}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput isNotNull="true" verify="required" labelName="身份证号：" name="cardNo" value="${pubinst.cardNo}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date id="joinParty" name="joinParty" display="block" labelName="入党时间：" value="${pubinst.joinParty}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="第二党派：" name="secondParty.id" display="block" url="dictquery/sub/code/GBT_4762_1984" initSelectedKey="${pubinst.secondParty.id}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="第三党派：" name="thirdParty.id" display="block" url="dictquery/sub/code/GBT_4762_1984" initSelectedKey="${pubinst.thirdParty.id}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="办公电话：" name="officePhone" value="${pubinst.officePhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="住宅电话：" name="homePhone" value="${pubinst.homePhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="移动电话：" name="mobilePhone" value="${pubinst.mobilePhone}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="秘书电话：" name="secretaryPhone" value="${pubinst.secretaryPhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="电子邮箱：" name="email" value="${pubinst.email}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="家庭住址：" name="homeAddress" value="${pubinst.homeAddress}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="户籍所在地：" name="residencePlace" value="${pubinst.residencePlace}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="住址邮政编码：" name="addressPost" value="${pubinst.addressPost}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="参加工作日期：" name="attendDate" id="attendDate" value="${pubinst.attendDate}" display="block" ></smart:date>
									</smart:gridColumn>
									
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="连续工龄：" name="workYear" value="${pubinst.workYear}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="专长：" name="expertise" value="${pubinst.expertise}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="爱好：" name="interested" value="${pubinst.interested}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="基层工作单位：" name="grassRootUnit" value="${pubinst.grassRootUnit}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="基层工作时间：" min="0" type="type" placeholder="(单位/年)" id="grassRootYear" name="grassRootYear" value="${pubinst.grassRootYear }" display="block"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="10">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect id="doingSpecialty" labelName="从事专业：" inputName="doingSpecialty.id" codeTypeCode="GBT_16835_1997" inputVal="${pubinst.doingSpecialty.id}" valType="ID" widthPercent="0.4"/>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="最高学历：" name="topEducation" display="block" url="dictquery/sub/code/GBT_4658_2006"  initSelectedKey="${pubinst.topEducation}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="最高学位：" name="topDegree" display="block" url="dictquery/sub/code/GBT_6864_2003" initSelectedKey="${pubinst.topDegree}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="毕业时间：" name="graduateDate" id="graduateDate" value="${pubinst.graduateDate}" display="block" ></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="职务名称：" name="nowPostCode.id" display="block" url="dictquery/sub/code/GBT_12403_1990" initSelectedKey="${pubinst.nowPostCode.id}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="职务属性：" name="nowPostAttribute.id" display="block" url="dictquery/sub/code/DM049" initSelectedKey="${pubinst.nowPostAttribute.id}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="管理类别：" name="levelManage.id" display="block" initSelectedKey="${pubinst.levelManage.id}" url="dictquery/sub/code/DM203" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="10">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect id="personType" labelName="人员类别：" inputName="personType.id" codeTypeCode="DM199" inputVal="${pubinst.personType.id}" valType="ID" widthPercent="0.5"/>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow >
									<smart:gridColumn colPart="12">
										<smart:continuousSelect isNotNull="true" verify="required" id="nowJobLevel" labelName="职务级别：" inputName="nowJobLevel.id" codeTypeCode="GBT_12407_2008" inputVal="${pubinst.nowJobLevel.id}" valType="ID" widthPercent="0.5"/>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:textarea name="personRemark" display="block" labelName="个人基本情况备注:">${pubinst.personRemark}</smart:textarea>
									</smart:gridColumn>
								</smart:gridRow>
								
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
						    <smart:button id="save" other="lay-submit" size="sm" title="保存" theme="normal">
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
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:headPicAction imgId="headImg" headBtnId="headBtn" photostrInputId="photostrInput"/>
		<smart:buttonScriptAction>
			goBack : function(data) {
			    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			    parent.layer.close(index);
				window.location.href='publicInstitution/list';
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="birthDate"/>
		<smart:dateRender id="joinParty"/>
		<smart:dateRender id="attendDate"/>
		<smart:dateRender id="graduateDate"/>
		<smart:dateRender id="indentityBegin"/>
		<smart:dateRender id="entryDate"/>
		<smart:dateRender id="startHighestLevel"/>
		<smart:dateRender id="dieDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
			            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			            parent.layer.close(index);
			            window.location.href='publicInstitution/edit?id=${id}';
	                });
				}else{
					layer.alert(
	                result.message, 
	                {icon: 0,closeBtn:0 });
				}
			});
			return false;
		});
	</smart:scriptHead>
</smart:body>
</html>