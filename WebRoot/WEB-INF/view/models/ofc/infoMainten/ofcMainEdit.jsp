
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--公务员登记"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="信息维护"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员登记表" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="false"  eId="" itemName="公务员登记表" turnurl="ofc/main?id=${id}"></smart:tabPanelItem>
							<smart:tabPanelItem show="true"   eId="" itemName="基本信息集" ></smart:tabPanelItem>
							<smart:tabPanelItem show="false"  eId="" itemName="其他子集信息" turnurl="ofc/extend_list?id=${id}"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				<smart:form id="editForm" action="ofc/saveServant">
					<smart:fromTokenTag/>
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
												<smart:textInput type="hidden" name="id" value="${servant.id}"></smart:textInput>
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
												<smart:date isNotNull="true" verify="required" labelName="出生日期：" value="${servant.birthDate}" name="birthDate" id="birthDate" display="block"></smart:date>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:singleSelect isNotNull="true" verify="required" name="sex.id" labelName="性别：" initSelectedKey="${servant.sex.id}" display="block" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true"></smart:singleSelect>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:singleSelect isNotNull="true" verify="required" name="nation.id" labelName="民族：" initSelectedKey="${servant.nation.id}" display="block" url="dictquery/sub/code/GBT_3304_1991" isAddDefaltOption="true"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="政治面貌：" name="politics.id" initSelectedKey="${servant.politics.id}" display="block" url="dictquery/sub/code/GBT_4762_1984" isAddDefaltOption="true"></smart:singleSelect>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect verify="required" id="birthPlace" labelName="出生地：" inputName="birthPlace.id" codeTypeCode="GBT_2260_2007" inputVal="${servant.birthPlace.id}" valType="ID" widthPercent="0.4" isSaveShowName="true" inputShowName="birthPlaceName"/>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect verify="required" id="nativePlace" labelName="籍贯：" inputName="nativePlace.id" codeTypeCode="GBT_2260_2007" inputVal="${servant.nativePlace.id}" valType="ID" widthPercent="0.4" isSaveShowName="true" inputShowName="nativePlaceName"/>
											</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect verify="required" id="growPlace" labelName="成长地：" inputName="growPlace.id" codeTypeCode="GBT_2260_2007" inputVal="${servant.growPlace.id}" valType="ID" widthPercent="0.4" isSaveShowName="true" inputShowName="growPlaceName"/>
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
										<smart:singleSelect labelName="婚姻状况：" name="marriage.id" initSelectedKey="${servant.marriage.id}" display="block" url="dictquery/sub/code/GBT_2261_2_2003" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="健康状况：" name="health.id" initSelectedKey="${servant.health.id}" display="block" url="dictquery/sub/code/GBT_2261_3_2003" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="健康状况描述：" name="healthDescribe" value="${servant.healthDescribe}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isNotNull="true" verify="required" labelName="个人身份有效证件名称：" initSelectedKey="${servant.cardType.id}" name="cardType.id" display="block" url="dictquery/sub/code/DM075" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput isNotNull="true" verify="required" labelName="个人身份有效证件号码：" name="cardTypeNo" value="${servant.cardTypeNo}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput isNotNull="true" verify="required" labelName="公民身份证号：" name="cardNo" value="${servant.cardNo}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:date id="joinParty" name="joinParty" display="block" labelName="入党时间：" value="${servant.joinParty}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="第二党派：" name="secondParty.id" display="block" url="dictquery/sub/code/GBT_4762_1984" initSelectedKey="${servant.secondParty.id}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="第三党派：" name="thirdParty.id" display="block" url="dictquery/sub/code/GBT_4762_1984" initSelectedKey="${servant.thirdParty.id}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="办公电话：" name="officePhone" value="${servant.officePhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="住宅电话：" name="homePhone" value="${servant.homePhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="移动电话：" name="mobilePhone" value="${servant.mobilePhone}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="秘书电话：" name="secretaryPhone" value="${servant.secretaryPhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="电子邮箱：" name="email" value="${servant.email}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="家庭住址：" name="homeAddress" value="${servant.homeAddress}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="户籍所在地：" name="residencePlace" value="${servant.residencePlace}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="住址邮政编码：" name="addressPost" value="${servant.addressPost}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date name="attendDate" id="attendDate" value="${servant.attendDate}" display="block" labelName="参加工作日期："></smart:date>
									</smart:gridColumn>
									
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="连续工龄：" name="workYear" value="${servant.workYear}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="专长：" name="expertise" value="${servant.expertise}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="爱好：" name="interested" value="${servant.interested}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="基层工作单位：" value="${servant.grassRootUnit}" name="grassRootUnit"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="基层工作经历时间（年）：" min="0" type="type" id="grassRootYear" name="grassRootYear" value="${servant.grassRootYear }" display="block"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="8">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect id="doingSpecialty" labelName="从事专业：" inputName="doingSpecialty.id" codeTypeCode="GBT_16835_1997" inputVal="${servant.doingSpecialty.id}" valType="ID" widthPercent="0.4"/>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="最高学历" infovalue="${servant.topEducation}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="最高学位" infovalue="${servant.topDegree}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="毕业时间" infovalue="${servant.graduateDateStr}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="现任最高职务名称" infovalue="${servant.nowPostCode.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="职务属性" infovalue="${servant.nowPostAttribute.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="职级名称" infovalue="${servant.nowJobLevel.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="管理类别：" name="levelManage.id" display="block" initSelectedKey="${servant.levelManage.id}" url="dictquery/sub/code/DM203" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="8">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:continuousSelect id="personType" labelName="人员类别：" inputName="personType.id" codeTypeCode="DM199" inputVal="${servant.personType.id}" valType="ID" widthPercent="0.5" isNotNull="true" verify="required"/>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="参照公务员法管理标识：" name="servantLaw.id" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${servant.servantLaw.id}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="离岗退养标识：" name="isLeavePost.id" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${servant.isLeavePost.id}" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="公务员登记号" infovalue="${servant.registerNo}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:textarea name="personRemark" display="block" labelName="个人基本情况备注:">${servant.personRemark}</smart:textarea>
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
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				window.location.href='ofc/list';
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="birthDate"/>
		<smart:dateRender id="joinParty"/>
		<smart:dateRender id="attendDate"/>
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
						window.location.href='ofc/list';
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