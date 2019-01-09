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
		<smart:fromTokenTag/>
		<smart:grid>
			<smart:card>
				<smart:cardHead>
				</smart:cardHead>
				<smart:cardBody>
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:date labelName="记录年份" name="recordYear" id="recordYear" isNotNull="true" display="block" verify="required" placeholder="年份"></smart:date>
						</smart:gridColumn>
						<smart:gridColumn colPart="6">
							<smart:singleSelect name="servantType" id="servantType" display="inline" labelName="文件类型"  isNotNull="true" verify="required"
							data="[{'key':'-1','value':'请选择文件类型'},{'key':'1','value':'公务员类型'},{'key':'2','value':'参公类型'}]"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="10" colOffset="1">
							<script type="text/javascript">
								function importCallBack(res){
									layui.use(['layer'],function(){
										if(res.code==1){
											layer.alert(
									                res.msg, 
									                {icon: 1,closeBtn: 1 },
									                function () {
                                                       		parent.layui.table.reload('navigationList');
															var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
															parent.layer.close(index);  // 关闭layer
						               		 });
										}else{
											var index=
												layer.alert(
									                res.msg, 
									                {icon: 1,closeBtn: 0 },
									                function () {
									                	$("#myUploadAction").removeClass("layui-btn-disabled");
									                	layer.close(index);
								                });
										}

									
									});

								}
							</script>
							<smart:fileUpload accept="file" dataName="importInfo" folder="function(){return document.getElementById('recordYear').value+'_'+document.getElementById('servantType').value;}" number="1"
								buttonName="选择录用结果文件" name="attPath" size="1000" auto="false" callBack="importCallBack"/>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:cardBody>
			</smart:card>
		</smart:grid>
	</smart:form>
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:fileUploadUtils uploadURL="ofcflow/draftServant/importdraftservantInfo" />
		<smart:dateRender id="recordYear" type="year" />
			
		//将上传按钮disabled
		$("#myUploadAction").click(function(){
			$(this).addClass("layui-btn-disabled");
			$(this).attr("disabled","true");
		});
	</smart:scriptHead>
</smart:body>
</html>