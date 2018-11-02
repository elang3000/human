<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="培训信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form action="ofcflow/train/saveTrainPlan">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="培训信息" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:textInput name="trainName" labelName="培训名称" isNotNull="true" verify="required" 
									value="${trainPlan.trainName}" placeholder="培训名称"></smart:textInput>
									<smart:textInput type="hidden" id="id" name="id" value="${trainPlan.id }"></smart:textInput>
									<smart:textInput type="hidden" id="status" name="status" value="1"></smart:textInput>
									<smart:textInput type="hidden" name="yearPlan.id"  value="${yearPlan.id}" ></smart:textInput>
									<smart:textInput type="hidden" name="trainOrgan.id"  value="${trainPlan.trainOrgan.id}" ></smart:textInput>
									<smart:textInput type="hidden" name="inputOrgan.id"  value="${trainPlan.inputOrgan.id}" ></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:numberInput name="hours" labelName="培训学时" min="0" 
									type="text" isNotNull="true" verify="required" 
									value="${trainPlan.hours}" placeholder="培训学时" display="block"></smart:numberInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:textInput name="startDate" labelName="开始日期" isNotNull="true" verify="required" otherAttr="readonly" 
									value="${trainPlan.startDate}"  id="startDate" placeholder="开始日期"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:textInput name="endDate" labelName="结束日期" id="endDate" isNotNull="true" verify="required" otherAttr="readonly" 
									value="${trainPlan.endDate}"  placeholder="结束日期"></smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4" colOffset="3">
								<smart:textInput name="description" labelName="描述"
									value="${trainPlan.description}" placeholder="描述"></smart:textInput>
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
		<smart:utils/>
		<smart:continuousSelectAction/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		var startDate=laydate.render({
	    	elem:'#startDate',
	    	btns: ['clear', 'confirm'],
	    	min:'${yearPlan.startDate}',
	    	max:'${yearPlan.endDate}',
	    	done:function(value,date){
		    	    endDate.config.min={    	    		
		    	    	year:date.year,
		    	    	month:date.month-1,//关键
		                date:date.date,
		                hours:date.hours,
		                minutes:date.minutes,
		                seconds:date.seconds
		    	    };
	    	}
    	});
    	var endDate=laydate.render({
	    	elem:'#endDate',
	    	btns: ['clear', 'confirm'],
	    	min:'${yearPlan.startDate}',
	    	max:'${yearPlan.endDate}',
	    	done:function(value,date){
		    	    startDate.config.max={
		    	    		year:date.year,
		        	    	month:date.month-1,//关键
		                    date:date.date,
		                    hours:date.hours,
		                    minutes:date.minutes,
		                    seconds:date.seconds
		    	    }
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
	                	parent.layui.table.reload('navigationList');
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