<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="人事管理记录" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="addForm" action="ofcflow/retire/save">
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:textInput type="hidden" name="ids" id="ids" value="${ids}"></smart:textInput>
							<smart:gridColumn colPart="6" colOffset="5">
								<smart:singleSelect display="block" labelName="退休类别："
										name="retireType.id" url="dictquery/sub/id/0197/null"
										isAddDefaltOption="true" isNotNull="true" verify="required"></smart:singleSelect>
							</smart:gridColumn>
							
							<smart:gridColumn colPart="6" colOffset="5">
								<smart:numberInput labelName="退休年龄：" isNotNull="true" placeholder="请输入年龄" type="text" 
									name="retireAge" verify="required" display="block"></smart:numberInput>
							</smart:gridColumn>
							
							<smart:gridColumn colPart="6" colOffset="5">
								<smart:date labelName="退休时间：" name="retireDate" id="retireDate" display="block" 
											isNotNull="true" verify="required">
										</smart:date>
							</smart:gridColumn>
						
							<smart:gridColumn colPart="6" colOffset="5">
								<smart:textarea labelName="退休备注：" isNotNull="true" placeholder="" 
									name="retireRemark" verify="required" display="block"></smart:textarea>
							</smart:gridColumn>
						
						</smart:gridColumn>
					</smart:gridRow>

					<smart:gridRow>
						<smart:gridColumn colPart="4" colOffset="4">
							<smart:buttonGroup container="true">
								<smart:button id="save" other="lay-submit" size="sm" title="保存">
									<smart:icon icon="plus">&nbsp;保存</smart:icon>
								</smart:button>
								<smart:button size="sm" type="reset" title="重新填写">
									<smart:icon icon="refresh"></smart:icon>&nbsp;重新填写
					  			</smart:button>
								<smart:button theme="warm" size="sm" title="返回" method="goBack">
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
		goBack : function(data) {
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			parent.layer.close(index);
		}
		</smart:buttonScriptAction>
		<smart:dateRender id="retireDate"/>
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
					parent.layui.table.reload('navigationList');
                	var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
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