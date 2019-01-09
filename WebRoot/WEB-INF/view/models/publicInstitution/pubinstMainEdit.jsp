
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
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="信息维护"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="基本信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
							<smart:tabPanel>
								<smart:tabPanelItem show="false"  eId="" itemName="登记信息" turnurl="publicInstitution/main?id=${id}"></smart:tabPanelItem>
								<smart:tabPanelItem show="true"   eId="" itemName="基本信息" ></smart:tabPanelItem>
								<smart:tabPanelItem show="false"  eId="" itemName="其他信息" turnurl="publicInstitution/extendlist?id=${id}"></smart:tabPanelItem>
							</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
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
												<smart:infoShowerLabel infoname="姓名拼音" infovalue="${pubinst.pinYinName}"></smart:infoShowerLabel>
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
												<smart:infoShowerLabel infoname="民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族" infovalue="${pubinst.nation.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="政治面貌" infovalue="${pubinst.politics.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										
										<smart:gridRow >
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="出生地" infovalue="${pubinst.birthPlaceName}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="成长地" infovalue="${pubinst.growPlace.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										
										<smart:gridRow>
											<smart:gridColumn colPart="6">
												<smart:infoShowerLabel infoname="成长地" infovalue="${pubinst.nativePlace.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="婚姻状况" infovalue="${pubinst.marriage.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
										</smart:gridRow>
										<smart:gridRow>
									
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="健康状况" infovalue="${pubinst.health.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="健康描述" infovalue="${pubinst.healthDescribe}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>	
									</smart:gridColumn>
									<smart:gridColumn colPart="3" colOffset="1">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<img style="width:150px;height:200px;min-width:150px;min-height:200px;" alt="照片" src="ftp/getImg?imgName=${pubinst.photoPath}">
										    </smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
							
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="有效证件" infovalue="${pubinst.cardType.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="证件号码" infovalue="${pubinst.cardTypeNo}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="身份证号" infovalue="${pubinst.cardNo}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="入党时间" infovalue="${pubinst.joinParty}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="第二党派" infovalue="${pubinst.secondParty.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="第三党派" infovalue="${pubinst.thirdParty.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="办公电话" name="officePhone" value="${pubinst.officePhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="住宅电话" name="homePhone" value="${pubinst.homePhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="移动电话" name="mobilePhone" value="${pubinst.mobilePhone}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="秘书电话" name="secretaryPhone" value="${pubinst.secretaryPhone}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="电子邮箱" name="email" value="${pubinst.email}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="家庭住址" name="homeAddress" value="${pubinst.homeAddress}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="户籍所在地" name="residencePlace" value="${pubinst.residencePlace}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="住址邮政编码" name="addressPost" value="${pubinst.addressPost}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="参加工作日期" name="attendDate" id="attendDate" value="${pubinst.attendDate}" display="block" ></smart:date>
									</smart:gridColumn>
									
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="连续工龄" name="workYear" value="${pubinst.workYear}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="专长" name="expertise" value="${pubinst.expertise}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="爱好" name="interested" value="${pubinst.interested}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="基层工作单位" name="grassRootUnit" value="${pubinst.grassRootUnit}"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput labelName="基层工作时间" min="0" type="type" placeholder="(单位/年)" id="grassRootYear" name="grassRootYear" value="${pubinst.grassRootYear }" display="block"></smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="10">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:infoShowerLabel infoname="从事专业" infovalue="${pubinst.doingSpecialty.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:title title="" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="最高学历" infovalue="${pubinst.topEducation}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="最高学位" infovalue="${pubinst.topDegree}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="毕业时间" infovalue="${pubinst.graduateDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="职务名称" infovalue="${pubinst.nowPostCode.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="职务属性" infovalue="${pubinst.nowPostAttribute.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="职务级别" infovalue="${pubinst.nowJobLevel.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="管理类别" infovalue="${pubinst.levelManage.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="8">
										<smart:gridRow>
											<smart:gridColumn colPart="12">
												<smart:infoShowerLabel infoname="人员类别" infovalue="${pubinst.personType.name}"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:infoShowerLabel infoname="个人情况备注" infovalue="${pubinst.personRemark}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
						    <smart:button size="sm" type="reset" title="编辑" method="edit">
								<smart:icon icon="edit"></smart:icon>&nbsp;编辑
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
				<!-- window.location.href='publicInstitution/list'; -->
				window.history.back(-1);
			},
			edit:function(data) {
				smart.show({
					title : '修改基本信息',
					size : 'full',
					url : 'publicInstitution/editPersonal?id=${id}',
					scrollbar : true
				});
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
						window.location.href='publicInstitution/list';
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