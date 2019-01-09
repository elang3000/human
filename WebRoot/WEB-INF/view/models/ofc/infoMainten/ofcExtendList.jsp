<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
					<smart:breadcrumbNavMenuItem iname="信息维护"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员登记表" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:tabPanelParent filter="tab"
						style="margin-left:10px;margin-right:10px;">
						<smart:tabPanel>
							<smart:tabPanelItem show="false" eId="" itemName="公务员登记表"
								turnurl="ofc/main?id=${id}"></smart:tabPanelItem>
							<shiro:hasPermission name="H001005003">
								<smart:tabPanelItem show="false" eId="" itemName="基本信息集"
								turnurl="ofc/servant_edit?id=${id}"></smart:tabPanelItem>
							</shiro:hasPermission>
							<smart:tabPanelItem show="true" eId="" itemName="其他子集信息"></smart:tabPanelItem>
						</smart:tabPanel>
					</smart:tabPanelParent>
				</smart:gridRow>

				<smart:accordionPanel id="panel" isAccordion="true">
					<shiro:hasPermission name="H001005005001">
						<smart:accordionPanelItem title="职务子集" isShow="true">
							<smart:table id="postNavigationList" url="ofc/post/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！" >
								<tr>
									<smart:tableItem field="postName" width=".2" sort="false">职务名称</smart:tableItem>
									<smart:tableItem field="attribute" width=".2" sort="false">职务属性</smart:tableItem>
									<smart:tableItem field="workContentRange" width=".2" sort="false">主管（从事）工作</smart:tableItem>
									<smart:tableItem field="tenureStatus" width=".2" sort="false">任职状态</smart:tableItem>
									<smart:tableItem align="center" width=".2" fixed="right" unresize="true" toolbar="postNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="postNavListToolBar">
									<shiro:hasPermission name="H001005005001002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005001003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005001001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addPost" title="新增职务信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增职务信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005002">
						<smart:accordionPanelItem title="录聘子集">
							<smart:table id="employNavigationList" url="ofc/employ/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="approveEmployDate" width=".2" sort="false">批准录用日期 </smart:tableItem>
									<smart:tableItem field="employDept" width=".2" sort="false">录用部门</smart:tableItem>
									<smart:tableItem field="employJob" width=".2" sort="false">录用职位</smart:tableItem>
									<smart:tableItem field="shSource" width=".2" sort="false">来源</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="employNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="employNavListToolBar">
									<shiro:hasPermission name="H001005005002002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005002003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005002001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addEmploy" title="新增录聘信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增录聘信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005003">
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
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="probationNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="probationNavListToolBar">
									<shiro:hasPermission name="H001005005003002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005003003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005003001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addProbation" title="新增试用信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增试用信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005004">
						<smart:accordionPanelItem title="职级子集">
							<smart:table id="jobLevelNavigationList" url="ofc/jobLevel/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="name" width=".15" sort="false">职级名称 </smart:tableItem>
									<smart:tableItem field="isLeaderName" width=".15" sort="false">职级属性</smart:tableItem>
									<smart:tableItem field="approvalDate" width=".15" sort="false">批准日期</smart:tableItem>
									<smart:tableItem field="endDate" width=".15" sort="false">终止日期</smart:tableItem>
									<smart:tableItem field="approvalNo" width=".2" sort="false">批准文号</smart:tableItem>
									<smart:tableItem field="status" width=".15" sort="false">现行职级</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="jobLevelNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="jobLevelNavListToolBar">
									<%-- <smart:tableToolBtn theme="warm" event="edit" title="编辑">
										<smart:icon icon="edit"></smart:icon>
									</smart:tableToolBtn> --%>
									<shiro:hasPermission name="H001005005004003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005004001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addJobLevel" title="新增职级信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增职级信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005005">
						<smart:accordionPanelItem title="专业技术任职资格子集">
							<smart:table id="competenceNavigationList" url="ofc/competence/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！" >
								<tr>
									<smart:tableItem field="name" width=".2" sort="false">专业技术任职资格名称</smart:tableItem>
									<smart:tableItem field="lvl" width=".2" sort="false">资格级别</smart:tableItem>
									<smart:tableItem field="gainDate" width=".2" sort="false">获得日期</smart:tableItem>
									<smart:tableItem field="jury" width=".2" sort="false">资格评委会或考试名称</smart:tableItem>
									<smart:tableItem field="approvalUnit" width=".2" sort="false">资格审批单位</smart:tableItem>
									<smart:tableItem align="center" width=".2" fixed="right" unresize="true" toolbar="competenceNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="competenceNavListToolBar">
									<shiro:hasPermission name="H001005005005002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005005003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							<shiro:hasPermission name="H001005005005001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addCompetence" title="新增资格信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增资格信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005006">
						<smart:accordionPanelItem title="学历子集">
							<smart:table id="educationNavigationList" url="ofc/education/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="name" width=".2" sort="false">学历名称 </smart:tableItem>
									<smart:tableItem field="shoolName" width=".2" sort="false">学校（单位）名称 </smart:tableItem>
									<smart:tableItem field="enterDate" width=".2" sort="false">入学日期</smart:tableItem>
									<smart:tableItem field="graduateDate" width=".2" sort="false">毕（肄）业日期</smart:tableItem>
									<smart:tableItem field="educationNo" width=".2" sort="false">学历证书号</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="educationNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="educationNavListToolBar">
									<shiro:hasPermission name="H001005005006002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005006003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005006001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addEducation" title="新增学历信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增学历信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005007">
						<smart:accordionPanelItem title="学位子集">
							<smart:table id="degreeNavigationList" url="ofc/degree/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="name" width=".2" sort="false">学位名称 </smart:tableItem>
									<smart:tableItem field="grantUnit" width=".2" sort="false">学校授予单位 </smart:tableItem>
									<smart:tableItem field="grantDate" width=".2" sort="false">学校授予日期</smart:tableItem>
									<smart:tableItem field="degreeNo" width=".2" sort="false">学位证书号</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="degreeNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="degreeNavListToolBar">
									<shiro:hasPermission name="H001005005007002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005007003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005007001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addDegree" title="新增学位信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增学位信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005008">
						<smart:accordionPanelItem title="学习（培训、进修）子集">
							<smart:table id="studyNavigationList" url="ofc/study/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
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
									<shiro:hasPermission name="H001005005008002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005008003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005008001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addStudy" title="新增学习信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增学习信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005009">
						<smart:accordionPanelItem title="奖励子集">
							<smart:table id="rewardNavigationList" url="ofc/reward/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="rewardName" width=".2" sort="false">受奖励名称</smart:tableItem>
									<smart:tableItem field="honoraryName" width=".2" sort="false">荣誉称号名称</smart:tableItem>
									<smart:tableItem field="rewardApprovalUnitName" width=".2" sort="false">奖励批准单位名称</smart:tableItem>
									<smart:tableItem field="rewardApprovalDate" width=".2" sort="false">奖励批准日期</smart:tableItem>
									<smart:tableItem field="rewardNo" width=".2" sort="false">受奖励决定原始文件</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="rewardNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="rewardNavListToolBar">
									<shiro:hasPermission name="H001005005009002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005009003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005009001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addReward" title="新增奖励信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增奖励信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005010">
						<smart:accordionPanelItem title="处分子集">
							<smart:table id="punishNavigationList" url="ofc/punish/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="punishName" width=".2" sort="false">受惩戒名称</smart:tableItem>
									<smart:tableItem field="punishApprovalUnitName" width=".2" sort="false">惩戒批准单位名称</smart:tableItem>
									<smart:tableItem field="punishApprovalDate" width=".2" sort="false">惩戒批准日期</smart:tableItem>
									<smart:tableItem field="punishNo" width=".2" sort="false">受处分决定原始文件</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="punishNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="punishNavListToolBar">
									<shiro:hasPermission name="H001005005010002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005010003">									
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005010001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addPunish" title="新增处分信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增处分信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005011">
						<smart:accordionPanelItem title="考核子集">
							<smart:table id="assessmentNavigationList" url="ofc/assessment/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="assessmentYear" width=".2" sort="false">考核年度</smart:tableItem>
									<smart:tableItem field="category" width=".2" sort="false">考核类别 </smart:tableItem>
									<smart:tableItem field="organizationName" width=".2" sort="false">考核组织名称</smart:tableItem>
									<smart:tableItem field="conclusionCategory" width=".2" sort="false">考核结论类别</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="assessmentNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="assessmentNavListToolBar">
									<shiro:hasPermission name="H001005005011002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									
									<shiro:hasPermission name="H001005005011003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005011001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addAssessment" title="新增考核信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增考核信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005012">
						<smart:accordionPanelItem title="简历子集">
							<smart:table id="experienceNavigationList" url="ofc/experience/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="formerUnit" width=".2" sort="false">曾在单位 </smart:tableItem>
									<smart:tableItem field="startDate" width=".2" sort="false">起始日期 </smart:tableItem>
									<smart:tableItem field="endDate" width=".2" sort="false">终止日期</smart:tableItem>
									<smart:tableItem field="formerJob" width=".2" sort="false">曾经从事工作或担任职务</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="experienceNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="experienceNavListToolBar">
									<shiro:hasPermission name="H001005005012002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005012003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005012001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addExperience" title="新增简历信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增简历信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005013">
						<smart:accordionPanelItem title="家庭子集">
							<smart:table id="familyNavigationList" url="ofc/family/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="relationName" width=".2" sort="false">关系名称 </smart:tableItem>
									<smart:tableItem field="name" width=".2" sort="false">姓名 </smart:tableItem>
									<smart:tableItem field="sex" width=".2" sort="false">性别</smart:tableItem>
									<smart:tableItem field="birthDate" width=".2" sort="false">出生日期</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="familyNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="familyNavListToolBar">
									<shiro:hasPermission name="H001005005013002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005013003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005013001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addFamily" title="新增家庭信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增家庭信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005014">
						<smart:accordionPanelItem title="职务变动情况子集">
							<smart:table id="jobChangeNavigationList" url="ofc/jobChange/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="formerUnitName" width=".2" sort="false">原单位名称 </smart:tableItem>
									<smart:tableItem field="newUnitName" width=".2" sort="false">新单位名称 </smart:tableItem>
									<smart:tableItem field="postChangeDate" width=".2" sort="false">职务变动日期</smart:tableItem>
									<smart:tableItem field="postTenureChange" width=".2" sort="false">职务变动类别</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="jobChangeNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="jobChangeNavListToolBar">
									<shiro:hasPermission name="H001005005014002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005014003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005014001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addJobChange" title="新增职务变动信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增职务变动信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005015">
						<smart:accordionPanelItem title="登记子集">
							<smart:table id="registrationNavigationList" url="ofc/registration/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="registrationCode" width=".3" sort="false">公务员登记标识 </smart:tableItem>
									<smart:tableItem field="manageCode" width=".2" sort="false">列入公务员法实施范围管理类别标识 </smart:tableItem>
									<smart:tableItem field="registrationNO" width=".2" sort="false">公务员登记号</smart:tableItem>
									<smart:tableItem field="registrationDate" width=".2" sort="false">公务员登记日期</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="registrationNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="registrationNavListToolBar">
									<shiro:hasPermission name="H001005005015002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005015003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005015001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addRegistration" title="新增登记信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增登记信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005016">
						<smart:accordionPanelItem title="退出（调出）子集">
							<smart:table id="outMgrNavigationList" url="ofc/outMgr/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="category" width=".2" sort="false">调出本单位类别 </smart:tableItem>
									<smart:tableItem field="outDate" width=".2" sort="false">调出本单位日期</smart:tableItem>
									<smart:tableItem field="gotoUnitName" width=".2" sort="false">调往单位名称</smart:tableItem>
									<smart:tableItem field="reasonType" width=".2" sort="false">调动原因</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="outMgrNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="outMgrNavListToolBar">
									<shiro:hasPermission name="H001005005016002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									<shiro:hasPermission name="H001005005016003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005016001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addOutMgr" title="新增退出信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增退出信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="H001005005017">
						<smart:accordionPanelItem title="进入（调入）子集">
							<smart:table id="intoMgrNavigationList" url="ofc/intoMgr/pageList?servantId=${id}" height="full" doneCallBack="fixedCol" page="false" limit="10000" text="未找到用户数据！">
								<tr>
									<smart:tableItem field="enterTheUnitChangeType" width=".2" sort="false">进入本单位类别 </smart:tableItem>
									<smart:tableItem field="enterTheUnitDate" width=".2" sort="false">进入本单位日期</smart:tableItem>
									<smart:tableItem field="formerUnitName" width=".2" sort="false">进入本单位前工作单位名称</smart:tableItem>
									<smart:tableItem field="enterReason" width=".2" sort="false">进入原因</smart:tableItem>
									<smart:tableItem align="center" fixed="right" unresize="true" width=".2"
										toolbar="intoMgrNavListToolBar">操作</smart:tableItem>
								</tr>
								<smart:tableToolBar id="intoMgrNavListToolBar">
									<shiro:hasPermission name="H001005005017002">
										<smart:tableToolBtn theme="warm" event="edit" title="编辑">
											<smart:icon icon="edit"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
									
									<shiro:hasPermission name="H001005005017003">
										<smart:tableToolBtn theme="danger" event="delete" title="删除">
											<smart:icon icon="trash"></smart:icon>
										</smart:tableToolBtn>
									</shiro:hasPermission>
								</smart:tableToolBar>
							</smart:table>
							
							<shiro:hasPermission name="H001005005017001">
								<smart:line color="blue" />
								<smart:buttonGroup container="true">
									<smart:button size="xs" method="addIntoMgr" title="新增进入信息">
										<smart:icon icon="plus"></smart:icon>&nbsp;&nbsp;新增进入信息
						  		    </smart:button>
								</smart:buttonGroup>
							</shiro:hasPermission>
						</smart:accordionPanelItem>
					</shiro:hasPermission>
					
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
						url:'ofc/post/edit',
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
						url:'ofc/post/delete',
						params:{id:data.data.id},
						callback:postNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('postNavigationList');
				}
		</smart:tableScriptAction>
		<smart:tableScriptAction tableId="employNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'录聘子集修改',
						size:'full',
						url:'ofc/employ/edit',
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
						url:'ofc/employ/delete',
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
						url:'ofc/probation/edit',
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
						url:'ofc/probation/delete',
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
						url:'ofc/jobLevel/edit',
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
						url:'ofc/jobLevel/delete',
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
						url:'ofc/education/edit',
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
						url:'ofc/education/delete',
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
						url:'ofc/degree/edit',
						params:{
							degreeId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'ofc/degree/delete',
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
						url:'ofc/family/edit',
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
						url:'ofc/family/delete',
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
						url:'ofc/competence/edit',
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
						url:'ofc/competence/delete',
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
						title:'学习（培训、进修）子集修改',
						size:'full',
						url:'ofc/study/edit',
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
						url:'ofc/study/delete',
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
						title:'奖励子集修改',
						size:'full',
						url:'ofc/reward/edit',
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
						url:'ofc/reward/delete',
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
						title:'处分子集修改',
						size:'full',
						url:'ofc/punish/edit',
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
						url:'ofc/punish/delete',
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
						title:'考核子集修改',
						size:'full',
						url:'ofc/assessment/edit',
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
						url:'ofc/assessment/delete',
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
						title:'简历子集修改',
						size:'full',
						url:'ofc/experience/edit',
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
						url:'ofc/experience/delete',
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
						title:'登记子集修改',
						size:'full',
						url:'ofc/registration/edit',
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
						url:'ofc/registration/delete',
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
						title:'退出子集修改',
						size:'full',
						url:'ofc/outMgr/edit',
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
						url:'ofc/outMgr/delete',
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
						title:'进入子集修改',
						size:'full',
						url:'ofc/intoMgr/edit',
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
						url:'ofc/intoMgr/delete',
						params:{id:data.data.id},
						callback:intoMgrNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('intoMgrNavigationList');
				}
		</smart:tableScriptAction>
		
		<smart:tableScriptAction tableId="jobChangeNavigationList" checkbox="true" sort="true" rowEdit="true">
				edit : function(data) {
					smart.show({
						title:'职务变动子集修改',
						size:'full',
						url:'ofc/jobChange/edit',
						params:{
							jobChangeId:data.data.id
						},
						scrollbar:false
					});
				},
				delete : function(data) {
					smart.confirm({
						title:'提示',
						message:'确认删除此项？',
						url:'ofc/jobChange/delete',
						params:{id:data.data.id},
						callback:jobChangeNavigationList_TableInvokeMethod.reload
					});
				},
				reload : function(){
					table.reload('jobChangeNavigationList');
				}
		</smart:tableScriptAction>
		
		
		<smart:buttonScriptAction>
			addPost : function(data) {
				smart.show({
					title:'职务子集新增',
					size:'full',
					url:'ofc/post/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addEmploy : function(data) {
				smart.show({
					title:'录聘子集新增',
					size:'full',
					url:'ofc/employ/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},
			addProbation : function(data) {
				smart.show({
					title:'试用子集新增',
					size:'full',
					url:'ofc/probation/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},
			addJobLevel : function(data) {
				smart.show({
					title:'职级子集新增',
					size:'full',
					url:'ofc/jobLevel/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},
			addEducation : function(data) {
				smart.show({
					title:'学历子集新增',
					size:'full',
					url:'ofc/education/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},
			addDegree : function(data) {
				smart.show({
					title:'学位子集新增',
					size:'full',
					url:'ofc/degree/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addFamily : function(data) {
				smart.show({
					title:'家庭子集新增',
					size:'full',
					url:'ofc/family/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addCompetence : function(data) {
				smart.show({
					title:'资格子集新增',
					size:'full',
					url:'ofc/competence/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addStudy : function(data) {
				smart.show({
					title:'学习（培训、进修）子集新增',
					size:'full',
					url:'ofc/study/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addReward : function(data) {
				smart.show({
					title:'奖励子集新增',
					size:'full',
					url:'ofc/reward/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addPunish : function(data) {
				smart.show({
					title:'处分子集新增',
					size:'full',
					url:'ofc/punish/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addAssessment : function(data) {
				smart.show({
					title:'考核子集新增',
					size:'full',
					url:'ofc/assessment/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addExperience : function(data) {
				smart.show({
					title:'简历子集新增',
					size:'full',
					url:'ofc/experience/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addRegistration : function(data) {
				smart.show({
					title:'登记子集新增',
					size:'full',
					url:'ofc/registration/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addOutMgr : function(data) {
				smart.show({
					title:'退出子集新增',
					size:'full',
					url:'ofc/outMgr/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addIntoMgr : function(data) {
				smart.show({
					title:'进入子集新增',
					size:'full',
					url:'ofc/intoMgr/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			},			
			addJobChange : function(data) {
				smart.show({
					title:'进入职务变动新增',
					size:'full',
					url:'ofc/jobChange/edit',
					params:{
						servantId:'${id}'
					},
					scrollbar:false
				});
			}
			
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>