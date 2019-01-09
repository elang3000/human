<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--公务员登记" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="false" eId="" itemName="公务员登记表"
								turnurl="ofc/main/view?id=${id}"></smart:tabPanelItem>
							<smart:tabPanelItem show="false" eId="" itemName="基本信息集"
								turnurl="ofc/servant_edit/view?id=${id}"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="其他子集信息"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>

				<smart:accordionPanel id="panel" isAccordion="true">
					<smart:accordionPanelItem title="职务子集" isShow="true">
						<smart:table id="postNavigationList" url="ofc/post/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！" >
							<tr>
								<smart:tableItem field="postName" width=".25" sort="false">职务名称</smart:tableItem>
								<smart:tableItem field="tenureName" width=".25" sort="false">任职机构名称</smart:tableItem>
								<smart:tableItem field="tenureReason" width=".25" sort="false">任职原因</smart:tableItem>
								<smart:tableItem field="tenureStatus" width=".25" sort="false">任职状态</smart:tableItem>
							</tr>
						</smart:table>

					</smart:accordionPanelItem>

					<smart:accordionPanelItem title="录聘子集">
						<smart:table id="employNavigationList" url="ofc/employ/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="approveEmployDate" width=".25" sort="false">批准录用日期 </smart:tableItem>
								<smart:tableItem field="employDept" width=".25" sort="false">录用部门</smart:tableItem>
								<smart:tableItem field="employJob" width=".25" sort="false">录用职位</smart:tableItem>
								<smart:tableItem field="shSource" width=".25" sort="false">来源</smart:tableItem>
							</tr>
						</smart:table>

					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="试用子集">
						<smart:table id="probationNavigationList" url="ofc/probation/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="type" width=".2" sort="false">试用类别 </smart:tableItem>
								<smart:tableItem field="unitName" width=".2" sort="false">试用单位</smart:tableItem>
								<smart:tableItem field="startDate" width=".2" sort="false">试用起始日期</smart:tableItem>
								<smart:tableItem field="endDate" width=".2" sort="false">试用终止日期</smart:tableItem>
								<smart:tableItem field="conclusion" width=".2" sort="false">试用期满考核结论</smart:tableItem>
								<smart:tableItem field="becomeDate" width=".2" sort="false">入职转正日期</smart:tableItem>
								<smart:tableItem field="becomeNo" width=".2" sort="false">入职转正批准文号</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="职级子集">
						<smart:table id="jobLevelNavigationList" url="ofc/jobLevel/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="name" width=".25" sort="false">职级名称 </smart:tableItem>
								<smart:tableItem field="approvalDate" width=".25" sort="false">批准日期</smart:tableItem>
								<smart:tableItem field="endDate" width=".25" sort="false">终止日期</smart:tableItem>
								<smart:tableItem field="approvalNo" width=".25" sort="false">批准文号</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="专业技术任职资格子集">
						<smart:table id="competenceNavigationList" url="ofc/competence/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！" >
							<tr>
								<smart:tableItem field="name" width=".2" sort="false">专业技术任职资格名称</smart:tableItem>
								<smart:tableItem field="lvl" width=".2" sort="false">资格级别</smart:tableItem>
								<smart:tableItem field="gainDate" width=".2" sort="false">获得日期</smart:tableItem>
								<smart:tableItem field="jury" width=".2" sort="false">资格评委会或考试名称</smart:tableItem>
								<smart:tableItem field="approvalUnit" width=".2" sort="false">资格审批单位</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="学历子集">
						<smart:table id="educationNavigationList" url="ofc/education/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="name" width=".2" sort="false">学历名称 </smart:tableItem>
								<smart:tableItem field="shoolName" width=".2" sort="false">学校（单位）名称 </smart:tableItem>
								<smart:tableItem field="enterDate" width=".2" sort="false">入学日期</smart:tableItem>
								<smart:tableItem field="graduateDate" width=".2" sort="false">毕（肄）业日期</smart:tableItem>
								<smart:tableItem field="educationNo" width=".2" sort="false">学历证书号</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="学位子集">
						<smart:table id="degreeNavigationList" url="ofc/degree/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="name" width=".25" sort="false">学位名称 </smart:tableItem>
								<smart:tableItem field="grantUnit" width=".25" sort="false">学校授予单位 </smart:tableItem>
								<smart:tableItem field="grantDate" width=".25" sort="false">学校授予日期</smart:tableItem>
								<smart:tableItem field="degreeNo" width=".25" sort="false">学位证书号</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="学习（培训、进修）子集">
						<smart:table id="studyNavigationList" url="ofc/study/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="category" width=".2" sort="false">学习类别 </smart:tableItem>
								<smart:tableItem field="hostUnitName" width=".2" sort="false">主办单位 </smart:tableItem>
								<smart:tableItem field="studyUnitName" width=".2" sort="false">在学单位</smart:tableItem>
								<smart:tableItem field="startDate" width=".2" sort="false">起始日期</smart:tableItem>
								<smart:tableItem field="endDate" width=".2" sort="false">结束日期</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="奖励子集">
						<smart:table id="rewardNavigationList" url="ofc/reward/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="rewardName" width=".2" sort="false">受奖励名称</smart:tableItem>
								<smart:tableItem field="honoraryName" width=".2" sort="false">荣誉称号名称</smart:tableItem>
								<smart:tableItem field="rewardApprovalUnitName" width=".2" sort="false">奖励批准单位名称</smart:tableItem>
								<smart:tableItem field="rewardApprovalDate" width=".2" sort="false">奖励批准日期</smart:tableItem>
								<smart:tableItem field="rewardNo" width=".2" sort="false">受奖励决定原始文件</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="处分子集">
						<smart:table id="punishNavigationList" url="ofc/punish/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="punishName" width=".2" sort="false">受惩戒名称</smart:tableItem>
								<smart:tableItem field="punishApprovalUnitName" width=".2" sort="false">惩戒批准单位名称</smart:tableItem>
								<smart:tableItem field="punishApprovalDate" width=".2" sort="false">惩戒批准日期</smart:tableItem>
								<smart:tableItem field="punishNo" width=".2" sort="false">受处分决定原始文件</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="考核子集">
						<smart:table id="assessmentNavigationList" url="ofc/assessment/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="assessmentYear" width=".2" sort="false">考核年度</smart:tableItem>
								<smart:tableItem field="category" width=".2" sort="false">考核类别 </smart:tableItem>
								<smart:tableItem field="organizationName" width=".2" sort="false">考核组织名称</smart:tableItem>
								<smart:tableItem field="conclusionCategory" width=".2" sort="false">考核结论类别</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="简历子集">
						<smart:table id="experienceNavigationList" url="ofc/experience/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="formerUnit" width=".25" sort="false">曾在单位 </smart:tableItem>
								<smart:tableItem field="startDate" width=".25" sort="false">起始日期 </smart:tableItem>
								<smart:tableItem field="endDate" width=".25" sort="false">终止日期</smart:tableItem>
								<smart:tableItem field="formerJob" width=".25" sort="false">曾经从事工作或担任职务</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="家庭子集">
						<smart:table id="familyNavigationList" url="ofc/family/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="relationName" width=".25" sort="false">关系名称 </smart:tableItem>
								<smart:tableItem field="name" width=".25" sort="false">姓名 </smart:tableItem>
								<smart:tableItem field="sex" width=".25" sort="false">性别</smart:tableItem>
								<smart:tableItem field="birthDate" width=".25" sort="false">出生日期</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="职务变动情况子集">
						<smart:table id="jobChangeNavigationList" url="ofc/jobChange/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="formerUnitName" width=".25" sort="false">原单位名称 </smart:tableItem>
								<smart:tableItem field="newUnitName" width=".25" sort="false">新单位名称 </smart:tableItem>
								<smart:tableItem field="postChangeDate" width=".25" sort="false">职务变动日期</smart:tableItem>
								<smart:tableItem field="postTenureChange" width=".25 " sort="false">职务变动类别</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="登记子集">
						<smart:table id="registrationNavigationList" url="ofc/registration/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="registrationCode" width=".3" sort="false">公务员登记标识 </smart:tableItem>
								<smart:tableItem field="manageCode" width=".2" sort="false">列入公务员法实施范围管理类别标识 </smart:tableItem>
								<smart:tableItem field="registrationNO" width=".2" sort="false">公务员登记号</smart:tableItem>
								<smart:tableItem field="registrationDate" width=".2" sort="false">公务员登记日期</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="退出（调出）子集">
						<smart:table id="outMgrNavigationList" url="ofc/outMgr/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="category" width=".25" sort="false">调出本单位类别 </smart:tableItem>
								<smart:tableItem field="outDate" width=".25" sort="false">调出本单位日期</smart:tableItem>
								<smart:tableItem field="gotoUnitName" width=".25" sort="false">调往单位名称</smart:tableItem>
								<smart:tableItem field="reasonType" width=".25" sort="false">调动原因</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="进入（调入）子集">
						<smart:table id="intoMgrNavigationList" url="ofc/intoMgr/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="enterTheUnitChangeType" width=".25" sort="false">进入本单位类别 </smart:tableItem>
								<smart:tableItem field="enterTheUnitDate" width=".25" sort="false">进入本单位日期</smart:tableItem>
								<smart:tableItem field="formerUnitName" width=".25" sort="false">进入本单位前工作单位名称</smart:tableItem>
								<smart:tableItem field="enterReason" width=".25" sort="false">进入原因</smart:tableItem>
							</tr>
						</smart:table>
					</smart:accordionPanelItem>
					
				</smart:accordionPanel>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
	</smart:scriptHead>
</smart:body>
</html>