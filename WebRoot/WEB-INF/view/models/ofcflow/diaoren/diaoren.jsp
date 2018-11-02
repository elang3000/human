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
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="调任" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:accordionPanel id="test" isAccordion="true">
								<smart:accordionPanelItem title="公务员基本信息" isShow="true">
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridRow>
												<smart:gridColumn colPart="6">
													<smart:textInput isNotNull="true" verify="required"
														labelName="姓名：" name="growPlaceName" value="${d.name}"></smart:textInput>
												</smart:gridColumn>
												<smart:gridColumn colPart="6">
													<smart:textInput isNotNull="true" verify="required"
														labelName="身份证：" name="cardNo" value="${d.cardNo}"></smart:textInput>
												</smart:gridColumn>
											</smart:gridRow>
											<smart:gridRow>
												<smart:gridColumn colPart="6">
													<smart:singleSelect isNotNull="true" verify="required"
														name="sex.id" labelName="性别：" display="block"
														initSelectedKey="${d.sex.id}"
														url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
												</smart:gridColumn>
												<smart:gridColumn colPart="6">
													<smart:date style="aa" isNotNull="true" verify="required"
														labelName="出生日期：" name="birthDate" id="birthDate"
														value="${d.birthDate}" display="block"></smart:date>
												</smart:gridColumn>
											</smart:gridRow>
											<smart:gridRow>
												<smart:gridColumn colPart="6">
													<smart:textInput isNotNull="true" verify="required"
														labelName="籍贯：" name="growPlaceName"
														value="${d.growPlaceName}"></smart:textInput>
												</smart:gridColumn>
												<smart:gridColumn colPart="6">
													<smart:singleSelect isSearch="true" isNotNull="true"
														verify="required" name="birthPlace.id" labelName="出生地："
														initSelectedKey="${d.birthPlace.id}" display="block"
														url="dictquery/sub/code/GBT_2260_2007"></smart:singleSelect>
												</smart:gridColumn>
											</smart:gridRow>
											<smart:gridRow>
												<smart:gridColumn colPart="6">
													<smart:singleSelect isNotNull="true" verify="required"
														name="nation.id" labelName="民族：" display="block"
														initSelectedKey="${d.nation.id}"
														url="dictquery/sub/code/GBT_3304_1991"></smart:singleSelect>
												</smart:gridColumn>
											</smart:gridRow>
											<smart:gridRow>
												<smart:gridColumn colPart="6">
													<smart:singleSelect labelName="政治面貌：" name="politics.id"
														initSelectedKey="${d.politics.id}" display="block"
														url="dictquery/sub/code/GBT_4762_1984"></smart:singleSelect>
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
											<smart:singleSelect labelName="健康状况："
												name="health.id"
												initSelectedKey="${d.health.id}" display="block"
												url="dictquery/sub/code/GBT_2261_3_2003"></smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:accordionPanelItem>
							</smart:accordionPanel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:accordionPanel  id="test" isAccordion="true">
								<smart:accordionPanelItem title="拟任职务信息">
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="拟任职务代码：" name="health.id"
													display="block" url="dictquery/sub/id/GBT_12403_1990/null"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="拟任职务名称：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:singleSelect labelName="拟任职务层次：" name="health.id"
												display="block" url="dictquery/sub/id/0060/null"></smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="拟任职务属性：" name="health.id"
													display="block" url="dictquery/sub/id/0008/null"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="任职形式：" name="health.id"
													display="block" url="dictquery/sub/id/0199/null"></smart:singleSelect>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:singleSelect labelName="是否破格提拔：" name="health.id"
												display="block" url="dictquery/sub/id/0132/null"></smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="是否班子成员：" name="health.id"
													display="block" url="dictquery/sub/id/0132/null"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="分管（从事）工作：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:textInput isNotNull="true" verify="required"
												labelName="集体内排序号：" name="growPlaceName"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="任职机构代码：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="任职机构名称：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:singleSelect labelName="任职原因：" name="health.id"
												display="block" url="dictquery/sub/0104/GBT_2261_3_2003/null"></smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:textarea display="block" labelName="拟任职务"></smart:textarea>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:buttonGroup container="true">
												<smart:button size="sm" method="search" title="添加"
													theme="primary">
													<smart:icon icon="plus"></smart:icon>&nbsp;添加
						  				 		</smart:button>
												<smart:button size="sm" method="history" title="删除"
													theme="primary" type="reset">
													<smart:icon icon="trash"></smart:icon>&nbsp;删除
						   						</smart:button>
											</smart:buttonGroup>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:accordionPanelItem>
							</smart:accordionPanel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:accordionPanel id="test" isAccordion="true">
								<smart:accordionPanelItem title="学历学位信息">
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="学历代码：" name="health.id"
													display="block" url="dictquery/sub/id/0119/null"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="学历名称：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:date style="aa" isNotNull="true" verify="required"
												labelName="参加工作时间：" name="workdate" id="workdate3"
												display="block"></smart:date>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="学历证书号：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:date style="aa" isNotNull="true" verify="required"
													labelName="毕（肄）业日期：" name="workdate" id="workdate4"
													display="block"></smart:date>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:textInput isNotNull="true" verify="required"
												labelName="学制年限：" name="growPlaceName"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="学校（单位）名称：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="教育类别    ：" name="health.id"
													display="block" url="dictquery/sub/id/0015/null"></smart:singleSelect>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:textInput isNotNull="true" verify="required"
												labelName="所学专业名称：" name="growPlaceName"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:singleSelect labelName="学位代码：" name="health.id"
													display="block" url="dictquery/sub/id/0007/null"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="学位名称：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:date style="aa" isNotNull="true" verify="required"
												labelName="学位授予日期：" name="workdate" id="workdate5"
												display="block"></smart:date>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:textarea display="block" labelName="学历学位信息"></smart:textarea>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:buttonGroup container="true">
												<smart:button size="sm" method="search" title="添加"
													theme="primary">
													<smart:icon icon="plus"></smart:icon>&nbsp;添加
						  				 		</smart:button>
												<smart:button size="sm" method="history" title="删除"
													theme="primary" type="reset">
													<smart:icon icon="trash"></smart:icon>&nbsp;删除
						   						</smart:button>
											</smart:buttonGroup>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:accordionPanelItem>
							</smart:accordionPanel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:accordionPanel id="test" isAccordion="true">
								<smart:accordionPanelItem title="简历信息">
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:date style="aa" isNotNull="true" verify="required"
													labelName="起始时间：" name="workdate" id="workdate6"
													display="block"></smart:date>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:date style="aa" isNotNull="true" verify="required"
													labelName="终止时间：" name="workdate" id="workdate7"
													display="block"></smart:date>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:textInput isNotNull="true" verify="required"
												labelName="在何单位：" name="growPlaceName"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="在何部门：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput isNotNull="true" verify="required"
													labelName="担任何职：" name="growPlaceName"></smart:textInput>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:textInput isNotNull="true" verify="required"
												labelName="证明人：" name="growPlaceName"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="12">
											<smart:checkbox isSwitch="true" labelName="期间标识"
												filter="checkstate" name="state" other="lay-text='ON|OFF'" />
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:textarea display="block" labelName="简历信息"></smart:textarea>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:buttonGroup container="true">
												<smart:button size="sm" method="search" title="添加"
													theme="primary">
													<smart:icon icon="plus"></smart:icon>&nbsp;添加
						  				 		</smart:button>
												<smart:button size="sm" method="history" title="删除"
													theme="primary" type="reset">
													<smart:icon icon="trash"></smart:icon>&nbsp;删除
						   						</smart:button>
											</smart:buttonGroup>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:accordionPanelItem>
							</smart:accordionPanel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:accordionPanel id="test" isAccordion="true">
								<smart:accordionPanelItem title="调入信息">
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:date style="aa" verify="required" value="${d.enterLocalRegionDate }"
													labelName="进入本地区日期：" name="enterLocalRegionDate" id="workdate6"
													display="block"></smart:date>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:date style="aa" verify="required" value="${d.enterTheSystemDate }"
													labelName="进入本行业、本系统日期：" name="enterTheSystemDate" id="workdate7"
													display="block"></smart:date>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:date style="aa" verify="required" value="${d.enterTheUnitDate }"
												labelName="进入本单位日期：" name="enterTheUnitDate" id="workdate8"
												display="block"></smart:date>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:singleSelect verify="required"
														name="enterTheUnitChangeType.id" labelName="进入本单位变动类别：" display="block" initSelectedKey="${d.enterTheUnitChangeType.id}"
														url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:singleSelect verify="required"
														name="formerPersonalIdentity.id" labelName="进入本单位前个人身份：" display="block" initSelectedKey="${d.formerPersonalIdentity.id}"
														url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:singleSelect verify="required"
													name="enterReason.id" labelName="进入本单位原因：" display="block" initSelectedKey="${d.enterReason.id}"
													url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:textInput verify="required" value="${d.formerUnitName}"
													labelName="进入本单位前工作单位名称：" name="formerUnitName"></smart:textInput>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput verify="required" value="${d.formerUnitCode}"
													labelName="进入本单位前工作单位代码：" name="formerUnitCode"></smart:textInput>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:singleSelect verify="required"
													name="formerUnitAreaCode.id" labelName="进入本单位前工作单位所在行政区划：" display="block" initSelectedKey="${d.formerUnitAreaCode.id}"
													url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:singleSelect verify="required"
														name="formerUnitBelongRelationship.id" labelName="进入本单位前工作单位隶属关系：" display="block" initSelectedKey="${d.formerUnitBelongRelationship.id}"
														url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:singleSelect verify="required"
														name="formerUnitLevel.id" labelName="进入本单位前工作单位级别：" display="block" initSelectedKey="${d.formerUnitLevel.id}"
														url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:singleSelect verify="required"
													name="formerUnitNature.id" labelName="进入本单位前工作单位性质类别：" display="block" initSelectedKey="${d.formerUnitNature.id}"
													url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
										</smart:gridColumn>
									</smart:gridRow>
									<smart:gridRow>
										<smart:gridColumn colPart="8">
											<smart:gridColumn colPart="6">
												<smart:singleSelect verify="required"
														name="formerUnitIndustry.id" labelName="进入本单位前工作单位所属行业：" display="block" initSelectedKey="${d.formerUnitIndustry.id}"
														url="dictquery/sub/code/GBT_2261_1_2003"></smart:singleSelect>
											</smart:gridColumn>
											<smart:gridColumn colPart="6">
												<smart:textInput verify="required"
													labelName="在原单位职务名称：" name="formerUnitJobName" value="${d.formerUnitJobName}"></smart:textInput>
											</smart:gridColumn>
										</smart:gridColumn>
										<smart:gridColumn colPart="4">
											<smart:textInput verify="required"
													labelName="在原单位职务级别：" name="formerUnitRank" value="${d.formerUnitRank}"></smart:textInput>
										</smart:gridColumn>
									</smart:gridRow>
								</smart:accordionPanelItem>
							</smart:accordionPanel>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm" title="保存"
									theme="normal">
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
		<smart:utils />
		<smart:buttonScriptAction>
				save : function(data) {
				},
				reSave : function(data) {
				},
				goBack : function(data) {
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
		</smart:buttonScriptAction>
		<smart:dateRender id="birthDate" />
		<smart:dateRender id="workdate" />
		<smart:dateRender id="workdate1" />
		<smart:dateRender id="workdate2" />
		<smart:dateRender id="workdate3" />
		<smart:dateRender id="workdate4" />
		<smart:dateRender id="workdate5" />
		<smart:dateRender id="workdate6" />
		<smart:dateRender id="workdate7" />
	</smart:scriptHead>
</smart:body>
</html>