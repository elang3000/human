<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="培训信息" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="ofcflow/trainServant/saveTrain">
					<smart:fromTokenTag />
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>

								<smart:gridRow>
									<smart:textInput type="hidden" name="id" value="${train.id}"></smart:textInput>
									<smart:textInput type="hidden" name="type" value="${type}"></smart:textInput>
									<smart:textInput type="hidden" id="servantIds" name="servantIds"></smart:textInput>
									<smart:textInput type="hidden" id="createOrganNodeId" name="createOrganNodeId" value="${x.id}"></smart:textInput>
									<smart:title title="培训信息" style="margin-top: 5px;" color="blue" />
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="培训班名称 ："
											value="${train.trainClassName}" display="block"
											verify="required" isNotNull="true" name="trainClassName"></smart:textInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="培训类别：" name="studyType.id"
											display="block" url="dictquery/sub/code/DM040"
											isAddDefaltOption="true" verify="required" isNotNull="true"
											initSelectedKey="${train.studyType.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="培训机构名 ："
											value="${train.trainOrganName}" display="block"
											name="trainOrganName" verify="required" isNotNull="true"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="培训机构类别："
											name="trainOrganType.id" display="block"
											url="dictquery/sub/code/DM022" isAddDefaltOption="true"
											verify="required" isNotNull="true"
											initSelectedKey="${train.trainOrganType.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="主办单位：" name="hostOrgan.id" isSearch="true" display="block" 
										url="system/organ/node/queryAll?organTreeId=394e21fa-1eb6-42ee-ba32-50655fa16517"
										isAddDefaltOption="true" isNotNull="true" verify="required" initSelectedKey="${train.hostOrgan.id }">
										</smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:numberInput name="days" min="0" type="text"
											value="${train.days}" labelName="培训时长（天）：" isNotNull="true"
											verify="required" placeholder="培训时长（天）" display="block"  isInt="true">
										</smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput name="hours" min="0" type="text"
											value="${train.hours}" labelName="培训学时（时）：" isNotNull="true"
											verify="required" placeholder="培训学时（时）" display="block" isInt="true">
										</smart:numberInput>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="开始日期 ：" value="${start}"
											display="block" name="startDate" id="startDate"
											verify="required" isNotNull="true"></smart:date>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:date labelName="结束日期 ：" value="${end}"
											display="block" name="endDate" id="endDate" verify="required"
											isNotNull="true"></smart:date>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:singleSelect labelName="培训性质：" name="nature.id"
											display="block" url="dictquery/sub/code/0114"
											isAddDefaltOption="true" verify="required" isNotNull="true"
											initSelectedKey="${train.nature.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect shortName="组织单位级别" labelName="培训组织单位级别："
											name="level.id" display="block"
											url="dictquery/sub/code/TRAIN_LEVEL" isAddDefaltOption="true"
											verify="required" isNotNull="true"
											initSelectedKey="${train.level.id }"></smart:singleSelect>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:singleSelect shortName="出境、市标识" labelName="出境、出市学习标识："
											name="isAbroad.id" display="block"
											url="dictquery/sub/code/DM215" isAddDefaltOption="true"
											verify="required" isNotNull="true"
											initSelectedKey="${train.isAbroad.id }"></smart:singleSelect>
									</smart:gridColumn>
								</smart:gridRow>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:numberInput name="funds" min="0" 
											value="${train.funds}" type="text" labelName="资金（万元）："
											isNotNull="true" verify="required" placeholder="资金（万元）"
											display="block">
										</smart:numberInput>
									</smart:gridColumn>
								</smart:gridRow>

							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:title title="培训人员信息列表" style="margin-top: 5px;" color="blue" />

				<smart:gridRow  colSpace="5">
					<smart:gridColumn  colPart="12" deviceType="md">
						<smart:buttonGroup container="true" align="right">
								<button class="layui-btn layui-btn-sm" type="button"
									data-method="select" title="选择人员">
									<i class="fa fa-search" style=""></i>&nbsp;选择人员
								</button>
								<smart:button method="del" size="sm" title="删除选中人员"
									theme="danger">
									<smart:icon icon="trash">&nbsp;删除选中人员</smart:icon>
								</smart:button>
							</smart:buttonGroup>
						<smart:table id="navigationList"
							url="ofcflow/trainServant/pageTrainServant?id=${train.id}"
							text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".2" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".13" sort="false">单位部门</smart:tableItem>
								<smart:tableItem field="postName" width=".13" sort="false">职务名称</smart:tableItem>
								<smart:tableItem field="postAttributeName" width=".11"
									sort="false">职务属性</smart:tableItem>
								<smart:tableItem field="jobLevel" width=".11" sort="false">职级名称</smart:tableItem>
								<smart:tableItem align="center" width=".08" fixed="right"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="danger" event="remove" title="删除">
									<smart:icon icon="trash"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
				
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<smart:button id="submit" other="lay-submit" size="sm"
									title="提交" theme="normal">
									<smart:icon icon="check">&nbsp;提交</smart:icon>
								</smart:button>
								<smart:button id="save" other="lay-submit" size="sm" title="暂存"
									theme="default">
									<smart:icon icon="plus">&nbsp;暂存</smart:icon>
								</smart:button>
								<smart:button theme="primary" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
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
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			},
			select : function() {
			    smart.show({
					title : '选择人员',
					size : 'full',
					url : 'orgInfo/selectServants',
					params : {createOrganNodeId:$('#createOrganNodeId').val(),ids:$('#servantIds').val(),busClass:'com.wondersgroup.human.bo.ofcflow.TrainPeople',busCol:'train.id',busId:'${train.id}',type:'1'},
					scrollbar : false,
				});
			},
			del : function() {
					if(ids.length == 0){
			    		smart.message({
							message : "请选择人员！"
							,type : 'W' //S保存  I问号  W感叹号 E错误
						});
			    	}else{ 
			    		smart.confirm({
							title:'删除人员信息',
							message:'确认删除人员信息？',
							type:'POST',
							url:'ofcflow/resignB/deletePeople',
							params : {ids : ids.join(",")},
							callback : function() {
								ids.splice(0,ids.length);
								layui.table.reload('navigationList');
							}
						});
					}
				}, 
		</smart:buttonScriptAction>
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
    	
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			smart.confirm({
				title:'确认保存培训信息吗',
				message:'确认保存培训信息吗？',
				url:url,
				params : params,
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		
		form.on('submit(submit)', function (data) {//表单提交
 			var params=data.field;
			var url=data.form.action;
			params.result="1";
			smart.confirm({
				title:'确认提交培训信息吗',
				message:'确认提交培训信息吗？',
				url:'ofcflow/trainServant/operationPlan',
				params : params,
				callback : function(){
					parent.layui.table.reload('navigationList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		
		setInterval(function(){
		    if($('#servantIds').val()!=""){
		    	if($('#servantIds').val()==""){
			 		smart.message({
						message : "请选择需要转任的人员！",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
			 	}
			 	var params=smart.json("#editForm");
			 	var requestConfig = {};
			 	requestConfig.url = "ofcflow/trainServant/savePeople";
				requestConfig.data = params;
	   			 $("#servantIds").val("");
				$("#servantNames").val("");
				requestConfig.success = function(result){
					parent.layui.table.reload('navigationList');
					window.location.href="ofcflow/trainServant/editTrain?id="+result.data;//新增成功刷新此页面
				}
				smart.request(requestConfig);
		    }
		    
		},100);
		
	</smart:scriptHead>
</smart:body>
</html>