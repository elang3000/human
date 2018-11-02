<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid >
	 <smart:form>
		<smart:gridRow>
				<smart:gridColumn colPart="4" >
				   <smart:textInput labelName="选调单位："  otherAttr="readonly"  id="xxx" placeholder="" display="inline"></smart:textInput>
				</smart:gridColumn>
		</smart:gridRow>
		<smart:gridRow>
				<smart:gridColumn colPart="4"   colOffset="8">
				   <smart:singleSelect labelName="用人单位：" initSelectedKey="1"  id="myid" name="interest" data="[{'key':'0','value':'写作'},{'key':'1','value':'阅读'},{'key':'2','value':'游戏'},{'key':'3','value':'音乐'},{'key':'4','value':'旅行'}]" ></smart:singleSelect>
				</smart:gridColumn>
		</smart:gridRow>
		<smart:gridRow>
				<smart:gridColumn colPart="4"   colOffset="8">
				   <smart:textInput labelName="创建单位："  display="inline"  placeholder="创建单位"></smart:textInput>
				</smart:gridColumn>
		</smart:gridRow>
	  </smart:form>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
	<smart:utils/>
	  form.on('select(myid)', function(data){
	    $("#xxx").val(data.elem[data.elem.selectedIndex].text);
	  	form.render('select');
	  });
	  
 	  $("#xxx").val($('#myid option:selected').text());
	 	
	 
	  
	</smart:scriptHead>
</smart:body>
</html>