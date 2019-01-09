<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="调入（进入）信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="ofc/intoMgr/save">
					<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="姓名" infovalue="${servant.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${intoMgr.id }"></smart:textInput>
										<smart:textInput type="hidden" name="servant.id" value="${servant.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="enterTheUnitChangeType" labelName="进入本单位变动类别：" shortName="变动类别" inputName="enterTheUnitChangeType.id" codeTypeCode="GBT_12405_2008" inputVal="${intoMgr.enterTheUnitChangeType.id}" valType="ID" widthPercent="0.5"/>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<smart:continuousSelect id="enterReason" labelName="进入本单位原因：" inputName="enterReason.id" codeTypeCode="DM015" inputVal="${intoMgr.enterReason.id}" valType="ID" widthPercent="0.5"/>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
								<smart:gridColumn colPart="4">
										<smart:date labelName="进入本单位日期：" display="block" name="enterTheUnitDate" id="enterTheUnitDate" value="${intoMgr.enterTheUnitDate}"></smart:date>
									</smart:gridColumn>
									
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="进入本单位前工作单位名称：" shortName="原单位名称" name="formerUnitName" value="${intoMgr.formerUnitName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="原单位职务名称：" shortName="原单位职务" name="formerUnitJobName" value="${intoMgr.formerUnitJobName}" ></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="原单位职务级别：" shortName="原单位职务级别" name="formerUnitRank" value="${intoMgr.formerUnitRank}" ></smart:textInput>
									</smart:gridColumn>
									
								</smart:gridRow>
								<smart:gridRow>
								<smart:gridColumn colPart="4">
										<smart:numberInput min="0" labelName="进入本单位时基层工作经历时间（月）:" shortName="基层工作时间" name="intoBasicWorkTime" value="${intoMgr.intoBasicWorkTime}"  display="block" type="text"></smart:numberInput>
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
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:continuousSelectAction/>
		<smart:dateRender id="enterTheUnitDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('intoMgrNavigationList');
	                	var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
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