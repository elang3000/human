<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="学历信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${e.personName}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${e.id}"></smart:textInput>
										<smart:textInput type="hidden" name="personName" value="${e.personName}"></smart:textInput>
										<smart:textInput type="hidden" name="cardNo" value="${e.cardNo}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="学历代码：" id="code" verify="required" isNotNull="true" name="code.id" initSelectedKey="${e.code.id}" display="block" url="dictquery/sub/code/GBT_4658_2006" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="学历名称：" id="name" otherAttr="readonly" name="name" value="${e.name}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="入学日期：" verify="required" isNotNull="true" display="block" name="enterDate" id="enterDate" value="${e.enterDate}"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="学历证书号：" verify="required" isNotNull="true" name="educationNo" value="${e.educationNo }" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="毕（肄、结）业日期：" verify="required" isNotNull="true" display="block" name="graduateDate" id="graduateDate" value="${e.graduateDate}"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="学制：" verify="required" isNotNull="true" name="eductionalSystem" value="${e.eductionalSystem }" ></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput verify="required" isNotNull="true" labelName="学校名称：" name="shoolName" value="${e.shoolName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="shoolAreaCode.id" labelName="学校所在行政区划：" display="block" url="dictquery/sub/code/GBT_2260_2007" initSelectedKey="${e.shoolAreaCode.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="eductionalType.id" labelName="教育类别：" display="block" url="dictquery/sub/code/DM024" initSelectedKey="${e.eductionalType.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="unitType.id" labelName="学历从学单位类别：" display="block" url="dictquery/sub/code/DM022" initSelectedKey="${e.unitType.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput verify="required" isNotNull="true" labelName="所学专业名称：" name="professionName" value="${e.professionName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="professionCode.id" labelName="所学专业类别：" display="block" url="dictquery/sub/code/GBT_16835_1997" initSelectedKey="${e.professionCode.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="complete.id" labelName="学历学习完成情况：" display="block" url="dictquery/sub/code/DM025" initSelectedKey="${e.complete.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="topFlag.id" labelName="最高学历标识：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${e.topFlag.id}"></smart:singleSelect>
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
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="enterDate"/>
		<smart:dateRender id="graduateDate"/>
		form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'保存学历信息',
				message:'确认提交保存吗？',
				url:'ofcflow/eventEducation/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('educationNavigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		form.on('select(code)', function(data){
		    $('#name').val(data.elem[data.elem.selectedIndex].text)
		});
	</smart:scriptHead>
</smart:body>
</html>