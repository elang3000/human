<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="垂直导航条测试"/>
</head>
<body>
	<div class="layui-upload">
		<button type="button" class="layui-btn" id="test1">上传图片</button>
		<div class="layui-upload-list">
			<img class="layui-upload-img" id="demo1">
			<p id="demoText"></p>
		</div>
	</div>
	
	<smart:fileUpload imgClass="layui-upload-img" />
	<smart:scriptHead models="table,form,layer,element,upload">
	</smart:scriptHead>
	
	<script type="text/javascript">
	layui.use('upload', function(){
		  var $ = layui.jquery
		  ,upload = layui.upload;
		  
		  //普通图片上传
		  var uploadInst = upload.render({
		    elem: '#demo1'
		    ,url: '/upload/'
		    ,before: function(obj){
		      //预读本地文件示例，不支持ie8
		      obj.preview(function(index, file, result){
		        $('#demo1').attr('src', result); //图片链接（base64）
		      });
		    }
		    ,done: function(res){
		      //如果上传失败
		      if(res.code > 0){
		        return layer.msg('上传失败');
		      }
		      //上传成功
		    }
		    ,error: function(){
		      //演示失败状态，并实现重传
		      var demoText = $('#demoText');
		      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
		      demoText.find('.demo-reload').on('click', function(){
		        uploadInst.upload();
		      });
		    }
		  });
	});
	</script>

</body>
</html>