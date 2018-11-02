<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="事项申请-年度考核" />
</head>
<smart:body>
	<smart:gridRow>
		<smart:title title="考核记录" style="margin-top: 5px;" color="blue" />
	</smart:gridRow>
	<smart:gridRow>
	  	<smart:form>
			<smart:gridColumn colPart="3">
				2015年度的考核优秀比例为:0%
			</smart:gridColumn>
		</smart:form>
	</smart:gridRow>
	<smart:gridRow colSpace="5">
		<smart:gridColumn colPart="12" deviceType="md">
			<smart:accordionPanel id="dsaf" isAccordion="true">
					<smart:table id="checkList"  url="ofc/yearcheck/checkdatalist" height="full" text="未找到该年度考核数据！">
						<tr>
							<smart:tableItem isCheckbox="true"></smart:tableItem>
							<smart:tableItem field="name" width=".2" sort="true">姓名</smart:tableItem>
							<smart:tableItem field="cardno" width=".2" sort="true">身份证号</smart:tableItem>
							<smart:tableItem field="unitname" width=".2" sort="true" >单位</smart:tableItem>
							<smart:tableItem field="status" width=".2" sort="false">考核结果</smart:tableItem>
							<smart:tableItem align="center" width=".2" fixed="right" unresize="true" toolbar="checkListToolBar">操作</smart:tableItem>
						</tr>
						<smart:tableToolBar id="checkListToolBar">
							<smart:tableToolBtn theme="warm" event="checkEdit" title="编辑">
								<smart:icon icon="edit"></smart:icon>
							</smart:tableToolBtn>
							<smart:tableToolBtn theme="normal" event="view" title="查看">
								<smart:icon icon="eye"></smart:icon>
							</smart:tableToolBtn>
						</smart:tableToolBar>
					</smart:table>
			</smart:accordionPanel>
		</smart:gridColumn>
	</smart:gridRow>
	<smart:gridRow>
			<smart:gridColumn colPart="12" deviceType="md">
				<smart:fileUpload accept="file" auto="false" buttonName="考核工作总结" dataName="khzzzj"/>
			</smart:gridColumn>
		</smart:gridRow>
	<smart:scriptHead models="table,form,layer,element,upload">
		<smart:utils/>
		<smart:tableScriptAction tableId="checkList">
			checkEdit : function(data) {
				layer.open({
			        type: 2 
			        ,title: '考核记录'
			        ,area: [$(window).width() / 1.1 + 'px', $(window).height() / 1.1 + 'px']
			        ,shade: [0.8, '#393D49']
			        ,maxmin: true 
			        ,skin: 'layui-layer-molv'
			        ,anim: 5 
			        ,isOutAnim: false
			        ,offset: 'auto'
			        ,scrollbar:true
			        ,content: 'ofc/yearcheck/checkDetail?id='+data.data.id
			        ,btn2: function(){
			          	layer.closeAll();
			        }
			        ,zIndex: layer.zIndex
			        ,success: function(layero){
			        	
			        }
				});
			},
			view : function(data) {
				console.log(data);
			}
		</smart:tableScriptAction>
	</smart:scriptHead>
</smart:body>
</html>