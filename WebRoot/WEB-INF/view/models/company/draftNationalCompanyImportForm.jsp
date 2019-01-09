<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="上海市公务员信息管理系统--录用记录导入" />
<script type="text/javascript" src="fontAwesome/src/3.2.1/assets/js/jquery-1.7.1.min.js" ></script>
</head>
<smart:body>
	<smart:form action="#"
				id="editform" name="editform" enctype="multipart/form-data">
		<smart:grid>
			<smart:card>
				<smart:cardHead>
				</smart:cardHead>
				<smart:cardBody>
					<smart:gridRow>
						<smart:gridColumn colPart="3">
							<smart:date labelName="导入年份" name="recordYear" id="recordYear" isNotNull="true" display="block" verify="required" placeholder="年份"></smart:date>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="8" colOffset="2">
							<smart:fileUpload accept="file" dataName="importInfo" folder="function(){return document.getElementById('recordYear').value}" number="1"
								buttonName="选择国企人员文件" name="attPath" size="1000" auto="false" />
						</smart:gridColumn>
					</smart:gridRow>
				</smart:cardBody>
			</smart:card>
		</smart:grid>
	</smart:form>
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:fileUploadUtils uploadURL="company/importdraftNationalCompanyInfo" />
		<smart:dateRender id="recordYear" type="year" />
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
		    $.post(url,params,function(result){
			
			});
			return false;	
		});
			
		//手动验证是否填入年份
		$("#myUploadAction").click(function(){
			$(this).attr("disabled","disabled");
			$(this).addClass("layui-btn-disabled"); 
		});
	</smart:scriptHead>
</smart:body>
</html>