<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="职位简章"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="启动年度招录计划" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form action="ofcflow/recruit/saveYearPlan">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="启动年度招录计划" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:textInput name="name" labelName="年度招录计划名称"
									value="${recruityearplan.name}" placeholder="年度招录计划名称"></smart:textInput>
									<smart:textInput type="hidden" id="id" name="id" value="${recruityearplan.id }"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:textInput name="startDate" labelName="开始日期"
									value="${recruityearplan.startDate}"  id="startDate" placeholder="开始日期"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:textInput name="endDate" labelName="结束日期" id="endDate"
									value="${recruityearplan.endDate}"  placeholder="结束日期"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:textInput name="description" labelName="描述"
									value="${recruityearplan.description}" placeholder="描述"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:checkbox isSwitch="true" labelName="状态"
									filter="checkstate" checkedKey="ON"  name="states" other="lay-text='ON|OFF'" />
								<smart:textInput type="hidden" id="state" name="state"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="2" deviceType="md" colOffset="5">
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm" title="提交"
									theme="normal">
									<smart:icon icon="plus">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button theme="warm" size="sm" method="goBack" title="返回">
									<smart:icon icon="pencil">&nbsp;返回</smart:icon>
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
			goBack : function(data) {
				window.location.href='ofcflow/recruit/year/list';
			}
		</smart:buttonScriptAction>
		<smart:dateRender id="startDate" />
		<smart:dateRender id="endDate" />
		 $("#state").val("0");
		 
		  form.on('switch(checkstate)', function(data){
		        if("ON"==data.othis[0].textContent){
		            $("#state").val("1");
		        }else if("OFF"==data.othis[0].textContent){
		        	$("#state").val("0");
		        }
		    
		  });
		  
 	   
 	    
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
						window.location.href='ofcflow/recruit/year/list';
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