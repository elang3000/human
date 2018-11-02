<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="学位信息"/>
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
										<smart:singleSelect labelName="学位代码：" id="code" verify="required" isNotNull="true" name="code.id" initSelectedKey="${e.code.id}" display="block" url="dictquery/sub/code/GBT_6864_2003" isAddDefaltOption="true"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="学位名称：" id="name" otherAttr="readonly" name="name" value="${e.name}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="学位授予日期：" verify="required" isNotNull="true" display="block" name="grantDate" id="grantDate" value="${e.grantDate}"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="学位授予单位：" verify="required" isNotNull="true" name="grantUnit" value="${e.grantUnit }" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="grantUnitAreaCode.id" labelName="授予单位所在行政区划：" display="block" url="dictquery/sub/code/GBT_2260_2007" initSelectedKey="${e.grantUnitAreaCode.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="topFlag.id" labelName="最高学位标识 ：" display="block" url="dictquery/sub/code/DM215" initSelectedKey="${e.topFlag.id}"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput verify="required" isNotNull="true" labelName="学位证书号：" name="degreeNo" value="${e.degreeNo}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="grantCountryAreaCode.id" labelName="学位授予国家 ：" display="block" url="dictquery/sub/code/GBT_2260_2007" initSelectedKey="${e.grantCountryAreaCode.id}"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect isAddDefaltOption="true"  name="secondCode.id" labelName="第二学位：" display="block" url="dictquery/sub/code/GBT_6864_2003" initSelectedKey="${e.secondCode.id}"></smart:singleSelect>
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
		<smart:dateRender id="grantDate"/>
		<smart:dateRender id="approvalDismissalDate"/>
		form.on('submit(save)', function(obj) {
			smart.confirm({
				title:'保存学位信息',
				message:'确认提交保存吗？',
				url:'ofcflow/eventDegree/save',
				params : smart.json("#editForm"),
				callback : function(){
					parent.layui.table.reload('degreeNavigationList');
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