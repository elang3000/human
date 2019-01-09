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
			<smart:cardBody>
				<smart:form id="fileForm">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridColumn colPart="3" >
							<smart:date labelName="开始日期 ："  display="block" name="start" id="startDate" verify="required" isNotNull="true"></smart:date>
						</smart:gridColumn>
						<smart:gridColumn colPart="3">
							<smart:date labelName="结束日期 ："  display="block" name="end" id="endDate" verify="required" isNotNull="true"></smart:date>
						</smart:gridColumn>
						<smart:gridColumn colPart="3">
							<smart:numberInput name="hour1" max="1000" min="0" type="text" labelName="学时（时）：" isNotNull="true" verify="required" placeholder="学时（时）" display="block" isInt="true">
									</smart:numberInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="3">
							<smart:numberInput name="hour2" max="1000" min="0" type="text" labelName="最低学时（时）：" isNotNull="true" verify="required" placeholder="最低学时（时）" display="block" isInt="true">
									</smart:numberInput>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow style="padding-top:40px" >
						<smart:gridColumn >
							<smart:buttonGroup container="true">
								<smart:button id="export" other="lay-submit" size="sm"
									title="导出" theme="normal">
									<smart:icon icon="check"></smart:icon>&nbsp;导出
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
		var startDate=laydate.render({
	    	elem:'#startDate',
	    	btns: ['clear', 'confirm'],
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
    	
    	form.on('submit(export)', function (data) {//表单保存
			   $("#fileForm").attr("action","ofcflow/trainServant/exportByUnit");
			   $("#fileForm").submit();
		});
	</smart:scriptHead>
</smart:body>
</html>