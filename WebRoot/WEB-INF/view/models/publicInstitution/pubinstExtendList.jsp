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
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="信息维护" ></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="其它信息" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab" style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="false"  eId="" itemName="登记信息" turnurl="publicInstitution/main?id=${id}"></smart:tabPanelItem>
							<smart:tabPanelItem show="false" eId="" itemName="基本信息" turnurl="publicInstitution/edit?id=${id}"></smart:tabPanelItem>
							<smart:tabPanelItem show="true" eId="" itemName="其他信息"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>
				
				<smart:accordionPanel id="panel" isAccordion="true">
					<smart:accordionPanelItem title="职务信息" isShow="true">
						<smart:table id="postNavigationList" url="pubinst/post/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！" >
							<tr>
								<smart:tableItem field="postName" width=".2" sort="false">职务名称</smart:tableItem>
								<smart:tableItem field="tenureName" width=".2" sort="false">任职机构名称</smart:tableItem>
								<smart:tableItem field="tenureReason" width=".2" sort="false">任职原因</smart:tableItem>
								<smart:tableItem field="tenureStatus" width=".2" sort="false">任职状态</smart:tableItem>
								<smart:tableItem align="center" width=".2" fixed="right" unresize="true" toolbar="postNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="postNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addPost" title="新增职务信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增职务信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>

					<smart:accordionPanelItem title="录聘信息">
						<smart:table id="employNavigationList" url="pubinst/employ/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="approveEmployDate" width=".2" sort="false">批准录用日期 </smart:tableItem>
								<smart:tableItem field="employDept" width=".2" sort="false">录用部门</smart:tableItem>
								<smart:tableItem field="employJob" width=".2" sort="false">录用职位</smart:tableItem>
								<smart:tableItem field="shSource" width=".2" sort="false">来源</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="employNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="employNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addEmploy" title="新增录聘信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增录聘信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="试用信息">
						<smart:table id="probationNavigationList" url="pubinst/probation/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="unitName" width=".2" sort="false">试用单位</smart:tableItem>
								<smart:tableItem field="startDate" width=".2" sort="false">试用起始日期</smart:tableItem>
								<smart:tableItem field="endDate" width=".2" sort="false">试用终止日期</smart:tableItem>
								<smart:tableItem field="conclusion" width=".2" sort="false">试用期满考核结论</smart:tableItem>
								<%-- <smart:tableItem field="becomeDate" width=".2" sort="false">入职转正日期</smart:tableItem>
								<smart:tableItem field="becomeNo" width=".2" sort="false">入入职转正批准文号</smart:tableItem> 
								<smart:tableItem field="type" width=".2" sort="false">试用类别 </smart:tableItem>
								--%>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="probationNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="probationNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addProbation" title="新增试用信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增试用信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="职级信息">
						<smart:table id="jobLevelNavigationList" url="pubinst/jobLevel/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="name" width=".2" sort="false">职级名称 </smart:tableItem>
								<smart:tableItem field="approvalDate" width=".2" sort="false">批准日期</smart:tableItem>
								<smart:tableItem field="endDate" width=".2" sort="false">终止日期</smart:tableItem>
								<smart:tableItem field="approvalNo" width=".2" sort="false">批准文号</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="jobLevelNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="jobLevelNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addJobLevel" title="新增职级信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增职级信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="专业技术任职资格信息">
						<smart:table id="competenceNavigationList" url="pubinst/competence/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！" >
							<tr>
								<smart:tableItem field="name" width=".2" sort="false">专业技术任职资格名称</smart:tableItem>
								<smart:tableItem field="lvl" width=".2" sort="false">资格级别</smart:tableItem>
								<smart:tableItem field="gainDate" width=".2" sort="false">获得日期</smart:tableItem>
								<smart:tableItem field="jury" width=".2" sort="false">资格评委会或考试名称</smart:tableItem>
								<smart:tableItem field="approvalUnit" width=".2" sort="false">资格审批单位</smart:tableItem>
								<smart:tableItem align="center" width=".2" fixed="right" unresize="true" toolbar="competenceNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="competenceNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addCompetence" title="新增资格信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增资格信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="学历信息">
						<smart:table id="educationNavigationList" url="pubinst/education/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="name" width=".2" sort="false">学历名称 </smart:tableItem>
<%-- 								<smart:tableItem field="shoolName" width=".2" sort="false">学校（单位）名称 </smart:tableItem>
 --%>								<smart:tableItem field="enterDate" width=".2" sort="false">入学日期</smart:tableItem>
								<smart:tableItem field="graduateDate" width=".2" sort="false">毕（肄）业日期</smart:tableItem>
								<smart:tableItem field="educationNo" width=".2" sort="false">学历证书号</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="educationNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="educationNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addEducation" title="新增学历信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增学历信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="学位信息">
						<smart:table id="degreeNavigationList" url="pubinst/degree/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="name" width=".2" sort="false">学位名称 </smart:tableItem>
								<smart:tableItem field="grantUnit" width=".2" sort="false">学校授予单位 </smart:tableItem>
								<smart:tableItem field="grantDate" width=".2" sort="false">学校授予日期</smart:tableItem>
								<smart:tableItem field="degreeNo" width=".2" sort="false">学位证书号</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="degreeNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="degreeNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addDegree" title="新增学位信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增学位信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="学习（培训、进修）信息">
						<smart:table id="studyNavigationList" url="pubinst/study/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="category" width=".2" sort="false">学习类别 </smart:tableItem>
								<smart:tableItem field="hostUnitName" width=".2" sort="false">主办单位 </smart:tableItem>
								<smart:tableItem field="studyUnitName" width=".2" sort="false">在学单位</smart:tableItem>
								<smart:tableItem field="startDate" width=".2" sort="false">起始日期</smart:tableItem>
								<smart:tableItem field="endDate" width=".2" sort="false">结束日期</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="studyNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="studyNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addStudy" title="新增学习信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增学习信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="奖励信息">
						<smart:table id="rewardNavigationList" url="pubinst/reward/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="category" width=".2" sort="false">人员受奖惩类别 </smart:tableItem>
								<smart:tableItem field="rewardNo" width=".2" sort="false">受奖励决定原始文件</smart:tableItem>
								<smart:tableItem field="rewardName" width=".2" sort="false">受奖励名称</smart:tableItem>
								<smart:tableItem field="rewardApprovalUnitName" width=".2" sort="false">奖励批准单位名称</smart:tableItem>
								<smart:tableItem field="rewardApprovalDate" width=".2" sort="false">奖励批准日期</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="rewardNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="rewardNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addReward" title="新增奖励信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增奖励信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="处分信息">
						<smart:table id="punishNavigationList" url="pubinst/punish/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="category" width=".2" sort="false">人员受奖惩类别 </smart:tableItem>
								<smart:tableItem field="punishNo" width=".2" sort="false">受处分决定原始文件</smart:tableItem>
								<smart:tableItem field="punishName" width=".2" sort="false">受惩戒名称</smart:tableItem>
								<smart:tableItem field="punishApprovalUnitName" width=".2" sort="false">惩戒批准单位名称</smart:tableItem>
								<smart:tableItem field="punishApprovalDate" width=".2" sort="false">惩戒批准日期</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="punishNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="punishNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addPunish" title="新增处分信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增处分信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="考核信息">
						<smart:table id="assessmentNavigationList" url="pubinst/assessment/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="assessmentYear" width=".2" sort="false">考核年度</smart:tableItem>
								<smart:tableItem field="category" width=".2" sort="false">考核类别 </smart:tableItem>
								<smart:tableItem field="organizationName" width=".2" sort="false">考核组织名称</smart:tableItem>
								<smart:tableItem field="conclusionCategory" width=".2" sort="false">考核结论类别</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="assessmentNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="assessmentNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addAssessment" title="新增考核信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增考核信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="简历信息">
						<smart:table id="experienceNavigationList" url="pubinst/experience/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="formerUnit" width=".2" sort="false">所在单位 </smart:tableItem>
								<smart:tableItem field="startDate" width=".2" sort="false">起始日期 </smart:tableItem>
								<smart:tableItem field="endDate" width=".2" sort="false">终止日期</smart:tableItem>
								<smart:tableItem field="formerJob" width=".2" sort="false">从事工作或担任职务</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="experienceNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="experienceNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addExperience" title="新增简历信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增简历信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="家庭信息">
						<smart:table id="familyNavigationList" url="pubinst/family/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="relationName" width=".2" sort="false">关系名称 </smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="false">姓名 </smart:tableItem>
								<smart:tableItem field="sex" width=".2" sort="false">性别</smart:tableItem>
								<smart:tableItem field="birthDate" width=".2" sort="false">出生日期</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="familyNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="familyNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addFamily" title="新增家庭信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增家庭信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<smart:accordionPanelItem title="登记信息">
						<smart:table id="registrationNavigationList" url="pubinst/registration/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="registrationCode" width=".3" sort="false">公务员登记标识 </smart:tableItem>
								<smart:tableItem field="manageCode" width=".3" sort="false">列入公务员法实施范围管理类别标识 </smart:tableItem>
								<smart:tableItem field="registrationDate" width=".2" sort="false">公务员登记日期</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="registrationNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="registrationNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>

						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addRegistration" title="新增登记信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增登记信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem>
					
					<%-- <smart:accordionPanelItem title="退出（调出）信息">
						<smart:table id="outMgrNavigationList" url="pubinst/outMgr/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="category" width=".2" sort="false">调出本单位类别 </smart:tableItem>
								<smart:tableItem field="outDate" width=".2" sort="false">调出本单位日期</smart:tableItem>
								<smart:tableItem field="gotoUnitName" width=".2" sort="false">调往单位名称</smart:tableItem>
								<smart:tableItem field="reasonType" width=".2" sort="false">调动原因</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="outMgrNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="outMgrNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addOutMgr" title="新增退出信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增退出信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem> --%>
					
					<%-- <smart:accordionPanelItem title="进入（调入）信息">
						<smart:table id="intoMgrNavigationList" url="pubinst/intoMgr/pageList?Id=${id}" height="full" page="false" limit="10000" text="未找到用户数据！">
							<tr>
								<smart:tableItem field="enterTheUnitChangeType" width=".2" sort="false">进入本单位类别 </smart:tableItem>
								<smart:tableItem field="enterTheUnitDate" width=".2" sort="false">进入本单位日期</smart:tableItem>
								<smart:tableItem field="formerUnitName" width=".2" sort="false">进入本单位前工作单位名称</smart:tableItem>
								<smart:tableItem field="enterReason" width=".2" sort="false">进入原因</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
									toolbar="intoMgrNavListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="intoMgrNavListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="delete" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
						<smart:line color="blue" />
						<smart:buttonGroup container="true">
							<smart:button size="xs" method="addIntoMgr" title="新增进入信息">
								<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增进入信息
				  		    </smart:button>
						</smart:buttonGroup>
					</smart:accordionPanelItem> --%>
					
				</smart:accordionPanel>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:tableScriptAction tableId="postNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'职务子集修改',
						size:'full',
						url:'pubinst/post/edit',
						params:{
							postId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/post/delete',
						params:{id:data.data.id},
						callback:postNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('postNavigationList');
					table.reload('experienceNavigationList');
				}
		</smart:tableScriptAction>
		<smart:tableScriptAction tableId="employNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'录聘子集修改',
						size:'full',
						url:'pubinst/employ/edit',
						params:{
							employId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/employ/delete',
						params:{id:data.data.id},
						callback:employNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('employNavigationList');
				}
		</smart:tableScriptAction>
		<smart:tableScriptAction tableId="probationNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'试用子集修改',
						size:'full',
						url:'pubinst/probation/edit',
						params:{
							probationId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/probation/delete',
						params:{id:data.data.id},
						callback:probationNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('probationNavigationList');
				}
		</smart:tableScriptAction>
		<smart:tableScriptAction tableId="jobLevelNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'职级子集修改',
						size:'full',
						url:'pubinst/jobLevel/edit',
						params:{
							jobLevelId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/jobLevel/delete',
						params:{id:data.data.id},
						callback:jobLevelNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('jobLevelNavigationList');
				}
		</smart:tableScriptAction>
		<smart:tableScriptAction tableId="educationNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'学历子集修改',
						size:'full',
						url:'pubinst/education/edit',
						params:{
							educationId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/education/delete',
						params:{id:data.data.id},
						callback:educationNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('educationNavigationList');
				}
		</smart:tableScriptAction>
		<smart:tableScriptAction tableId="degreeNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'学位子集修改',
						size:'full',
						url:'pubinst/degree/edit',
						params:{
							degreeId:data.data.id,
							Id:'${id}'
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/degree/delete',
						params:{id:data.data.id},
						callback:degreeNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('degreeNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="familyNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'家庭子集修改',
						size:'full',
						url:'pubinst/family/edit',
						params:{
							familyId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/family/delete',
						params:{id:data.data.id},
						callback:familyNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('familyNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="competenceNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'资格子集修改',
						size:'full',
						url:'pubinst/competence/edit',
						params:{
							competenceId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/competence/delete',
						params:{id:data.data.id},
						callback:competenceNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('competenceNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="studyNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'编辑培训信息',
						size:'full',
						url:'pubinst/study/edit',
						params:{
							studyId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/study/delete',
						params:{id:data.data.id},
						callback:studyNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('studyNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="rewardNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'编辑奖励信息',
						size:'full',
						url:'pubinst/reward/edit',
						params:{
							rewardId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/reward/delete',
						params:{id:data.data.id},
						callback:rewardNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('rewardNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="punishNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'编辑处分信息',
						size:'full',
						url:'pubinst/punish/edit',
						params:{
							punishId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/punish/delete',
						params:{id:data.data.id},
						callback:punishNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('punishNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="assessmentNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'编辑考核信息',
						size:'full',
						url:'pubinst/assessment/edit',
						params:{
							assessmentId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/assessment/delete',
						params:{id:data.data.id},
						callback:assessmentNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('assessmentNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="experienceNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'编辑简历信息',
						size:'full',
						url:'pubinst/experience/edit',
						params:{
							experienceId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/experience/delete',
						params:{id:data.data.id},
						callback:experienceNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('experienceNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="registrationNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'编辑登记信息',
						size:'full',
						url:'pubinst/registration/edit',
						params:{
							registrationId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/registration/delete',
						params:{id:data.data.id},
						callback:registrationNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('registrationNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="outMgrNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'编辑调(转)出信息',
						size:'full',
						url:'pubinst/outMgr/edit',
						params:{
							outMgrId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/outMgr/delete',
						params:{id:data.data.id},
						callback:outMgrNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('outMgrNavigationList');
				}
		</smart:tableScriptAction>
		<smart:tableScriptAction tableId="intoMgrNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'编辑调(转)入信息',
						size:'full',
						url:'pubinst/intoMgr/edit',
						params:{
							intoMgrId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'pubinst/intoMgr/delete',
						params:{id:data.data.id},
						callback:intoMgrNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('intoMgrNavigationList');
				}
		</smart:tableScriptAction>
		
		
		<smart:buttonScriptAction>
			addPost : function(data) {
				smart.show({
					title:'新增职务信息',
					size:'full',
					url:'pubinst/post/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addEmploy : function(data) {
				smart.show({
					title:'新增录聘信息',
					size:'full',
					url:'pubinst/employ/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},
			addProbation : function(data) {
				smart.show({
					title:'新增试用信息',
					size:'full',
					url:'pubinst/probation/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},
			addJobLevel : function(data) {
				smart.show({
					title:'新增职级信息',
					size:'full',
					url:'pubinst/jobLevel/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},
			addEducation : function(data) {
				smart.show({
					title:'新增学历信息',
					size:'full',
					url:'pubinst/education/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},
			addDegree : function(data) {
				smart.show({
					title:'新增学位信息',
					size:'full',
					url:'pubinst/degree/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addFamily : function(data) {
				smart.show({
					title:'新增家庭信息',
					size:'full',
					url:'pubinst/family/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addCompetence : function(data) {
				smart.show({
					title:'新增资格信息',
					size:'full',
					url:'pubinst/competence/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addStudy : function(data) {
				smart.show({
					title:'学习（培训、进修）子集新增',
					size:'full',
					url:'pubinst/study/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addReward : function(data) {
				smart.show({
					title:'奖励子集新增',
					size:'full',
					url:'pubinst/reward/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addPunish : function(data) {
				smart.show({
					title:'处分子集新增',
					size:'full',
					url:'pubinst/punish/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addAssessment : function(data) {
				smart.show({
					title:'考核子集新增',
					size:'full',
					url:'pubinst/assessment/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addExperience : function(data) {
				smart.show({
					title:'简历子集新增',
					size:'full',
					url:'pubinst/experience/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addRegistration : function(data) {
				smart.show({
					title:'登记子集新增',
					size:'full',
					url:'pubinst/registration/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addOutMgr : function(data) {
				smart.show({
					title:'退出子集新增',
					size:'full',
					url:'pubinst/outMgr/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			},			
			addIntoMgr : function(data) {
				smart.show({
					title:'进入子集新增',
					size:'full',
					url:'pubinst/intoMgr/edit',
					params:{
						Id:'${id}'
					},
					scrollbar:false
				});
			}
			
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>