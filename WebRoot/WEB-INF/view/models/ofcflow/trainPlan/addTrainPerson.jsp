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
				<smart:form id="editForm"
				action="ofcflow/train/saveTrainPerson">
				<smart:fromTokenTag/>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:title title="培训信息" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训名称" infovalue="${trainPlan.trainName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="开始日期" infovalue="${trainPlan.startDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="结束日期" infovalue="${trainPlan.endDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训学时" infovalue="${trainPlan.hours}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="描述" infovalue="${trainPlan.description}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:title title="个人培训信息" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:selectResource labelName="姓名：" id="selectNameTag" display="block"/>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="身份证号：" name="cardNo" display="block" otherAttr="readonly"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="所在单位：" name="departName" display="block" otherAttr="readonly"></smart:textInput>
										<smart:textInput type="hidden" name="dept.id"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput type="hidden" name="plan.id" value="${trainPlan.id}"></smart:textInput>
										<smart:numberInput name="hours" min="0" type="text" labelName="学时：" isNotNull="true" verify="required" placeholder="学时" display="block">
									</smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput name="startDate" labelName="开始日期：" isNotNull="true" verify="required" otherAttr="readonly" 
									  	id="startDate" placeholder="开始日期"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput name="endDate" labelName="结束日期：" isNotNull="true" verify="required"  otherAttr="readonly" 
									  	id="endDate" placeholder="结束日期"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput name="day" min="0" type="text" labelName="培训天数：" isNotNull="true" verify="required" placeholder="培训天数" display="block">
										</smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="培训性质：" display="block" isNotNull="true" verify="required" 
										name="nature.id" url="dictquery/sub/code/0114"
										isAddDefaltOption="true">
										</smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="培训组织单位级别：" display="block" isNotNull="true" verify="required" 
										name="level.id" url="dictquery/sub/code/TRAIN_LEVEL"
										isAddDefaltOption="true">
										</smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>
								
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="是否境外培训：" display="block" isNotNull="true" verify="required" 
										name="isAbroad.id" url="dictquery/sub/code/DM215"
										isAddDefaltOption="true">
										</smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput name="funds" min="0" type="text" labelName="培训专项费用（万元）：" isNotNull="true" verify="required" placeholder="培训专项费用（万元）" display="block">
										</smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>
								
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
					   <smart:gridColumn  colPart="2" colOffset="10">
					     <smart:buttonGroup container="true">
						    <smart:button id="save" other="lay-submit" size="sm" title="添加" theme="normal">
								<smart:icon icon="plus"></smart:icon>&nbsp;添加
							</smart:button>
						     <smart:button size="sm" method="goBack" title="返回" theme="warm">
								<smart:icon icon="reply"></smart:icon>&nbsp;返回
							</smart:button>
						 </smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
				</smart:form>
				
				<smart:gridRow>
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/train/trainPersonList?planId=${trainPlan.id}"
							height="full-500" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="name" width=".2" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="hours" width=".1" sort="false">培训学时</smart:tableItem>
								<smart:tableItem field="startDate" width=".1" sort="false">开始时间</smart:tableItem>
								<smart:tableItem field="endDate" width=".1" sort="false">结束时间</smart:tableItem>
								<smart:tableItem field="nature" width=".1" sort="false">培训性质</smart:tableItem>
								<smart:tableItem field="level" width=".1" sort="false">培训组织单位级别</smart:tableItem>
								<smart:tableItem field="funds" width=".1" sort="false">培训经费（万元）</smart:tableItem>
								<smart:tableItem field="isAbroad" width=".1" sort="false">是否境外培训</smart:tableItem>
								<smart:tableItem align="center" fixed="right" unresize="true"
									toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="danger" event="remove" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate,selectResource">
		<smart:utils/>
		<smart:initSelectResource id = "selectNameTag" value="" name="servant.name" hiddenName="servant.id" winSize="lg" winTitle="选择人员信息" winUrl="orgInfo/selectServant?createOrganNodeId=${trainPlan.trainOrgan.id}" linkElement="{fieldName :'cardNo',fieldValue : 'cardNo'},{fieldName :'departName',fieldValue : 'departName'},{fieldName :'dept.id',fieldValue : 'departId'}"/>
		<smart:tableScriptAction tableId="navigationList" checkbox="false"
			sort="true" rowEdit="true">
			remove : function(data) {
					smart.confirm({
						title:'删除个人培训信息',
						message:'确认删除个人培训信息？',
						type:'POST',
						url:'ofcflow/train/delTrainPerson',
						params : {id : data.data.id},
						callback : function() {
							layui.table.reload('navigationList');
						}
				});
			}
		</smart:tableScriptAction>
		
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		var startDate=laydate.render({
	    	elem:'#startDate',
	    	min:'${trainPlan.startDate}',
	    	max:'${trainPlan.endDate}',
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
	    	min:'${trainPlan.startDate}',
	    	max:'${trainPlan.endDate}',
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
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
                	layui.table.reload('navigationList');
                	layer.alert(
	                result.message, 
	                {icon: 1,closeBtn:1 });
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