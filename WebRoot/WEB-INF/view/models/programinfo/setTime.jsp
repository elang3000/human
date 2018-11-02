<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>

<script type="text/javascript">
function HashMap() {
	/** Map��С* */
	var size = 0;
	/** ����* */
	var entry = new Object();
	/** Map�Ĵ�put����* */
	this.put = function(key, value) {
		if (!this.containsKey(key)) {
			size++;
			entry[key] = value;
		}
	}
	/** Mapȡget����* */
	this.get = function(key) {
		return this.containsKey(key) ? entry[key] : null;
	}
	/** Mapɾ��remove����* */
	this.remove = function(key) {
		if (this.containsKey(key) && (delete entry[key])) {
			size--;
		}
	}
	/** �Ƿ����Key* */
	this.containsKey = function(key) {
		return (key in entry);
	}
	/** �Ƿ����Value* */
	this.containsValue = function(value) {
		for ( var prop in entry) {
			if (entry[prop] == value) {
				return true;
			}
		}
		return false;
	}
	/** ���е�Value* */
	this.values = function() {
		var values = new Array();
		for ( var prop in entry) {
			values.push(entry[prop]);
		}
		return values;
	}
	/** ���е� Key* */
	this.keys = function() {
		var keys = new Array();
		for ( var prop in entry) {
			keys.push(prop);
		}
		return keys;
	}
	/** Map size* */
	this.size = function() {
		return size;
	}
	/** ���Map* */
	this.clear = function() {
		size = 0;
		entry = new Object();
	}
}

</script>
<script type="text/javascript">
//嵌入页获取cron 表达式
function getcron(){
   return getvalues();
}
var radio_type=new HashMap(); 
radio_type.put("1","2"); 
radio_type.put("2","1");
radio_type.put("3","1");
radio_type.put("4","1");
radio_type.put("5","1");
radio_type.put("6","1");
radio_type.put("7","1");

//修改radio_type值
function editradio(key,value){
  if(radio_type.containsKey(key)){
    radio_type.remove(key);
	radio_type.put(key,value);
  }else{
    radio_type.put(key,value);
  }
}


//分钟tab事件
function fz_radio_click(obj){ 
var str=obj.value;

if(str=="fz1"){
 editradio("1","1");
 document.getElementById("fks").disabled=true; 
 document.getElementById("fzx").disabled=true; 
 document.getElementById("mks").disabled=false; 
 document.getElementById("mzx").disabled=false; 
 chk_disabled(1);
}
if(str=="fz2"){
 editradio("1","2");
 document.getElementById("fks").disabled=false; 
 document.getElementById("fzx").disabled=false; 
 document.getElementById("mks").disabled=true; 
 document.getElementById("mzx").disabled=true; 
 chk_disabled(1);
}
if(str=="fz3"){
 editradio("1","3");
 document.getElementById("fks").disabled=true; 
 document.getElementById("fzx").disabled=true; 
 document.getElementById("mks").disabled=true; 
 document.getElementById("mzx").disabled=true; 
 chk_disabled(2);
}

}
//0-59 分钟disabled控制
function chk_disabled(str){
   if(str=='1'){
     for(var i=0;i<60;i++){
	   document.getElementById("chk"+i).disabled=true; 
	   document.getElementById("chk"+i).checked=false;
	 }
   }
   if(str=='2'){
     for(var i=0;i<60;i++){
	   document.getElementById("chk"+i).disabled=false; 
	   //document.getElementById("chk"+i).checked=true;
	 }
   }

}
//小时tab事件

function xs_radio_click(obj){
  var str=obj.value;
  if(str=="xs1"){
   editradio("2","1");
   for(var i=0;i<24;i++){
       document.getElementById("sj"+i).disabled=true; 
	   document.getElementById("sj"+i).checked=false;
   }
  }
  if(str=="xs2"){
     editradio("2","2");
    for(var i=0;i<24;i++){
       document.getElementById("sj"+i).disabled=false; 
	   //document.getElementById("sj"+i).checked=false;
   } 
  }
}
//天tab事件

function mt_radio_click(obj){
  var str=obj.value;
  if(str=="mt1"){
    editradio("3","1");
   for(var i=1;i<32;i++){
       document.getElementById("t"+i).disabled=true; 
	   document.getElementById("t"+i).checked=false;
   }
  }
  if(str=="mt2"){
     editradio("3","2");
    for(var i=1;i<32;i++){
       document.getElementById("t"+i).disabled=false; 
	   //document.getElementById("sj"+i).checked=false;
   } 
  }
}

//月tab事件

function my_radio_click(obj){
  var str=obj.value;
  if(str=="my1"){
   editradio("4","1");
   for(var i=1;i<13;i++){
       document.getElementById("y"+i).disabled=true; 
	   document.getElementById("y"+i).checked=false;
   }
  }
  if(str=="my2"){
    editradio("4","2");
    for(var i=1;i<13;i++){
       document.getElementById("y"+i).disabled=false; 
	   //document.getElementById("sj"+i).checked=false;
   } 
  }
}

//周tab事件
function zhou_checkbox_click(){
 if(document.getElementById("mzhou").checked){ 
   editradio("5","2");
   document.getElementsByName("mzh")[0].disabled = false 
   document.getElementsByName("mzh")[1].disabled = false 
 }else{
   editradio("5","1");
   editradio("6","0");
   document.getElementsByName("mzh")[0].disabled = true 
   document.getElementsByName("mzh")[0].checked = true 
   document.getElementsByName("mzh")[1].disabled = true 
    for(var i=1;i<8;i++){
         document.getElementById("zhou"+i).disabled=true;
	     document.getElementById("zhou"+i).checked=false;
     }
 }
 }

function  zhou_radio_click(obj){
    var str=obj.value;
	 
	if(str=="mzh1"){
	  editradio("6","1");
	   for(var i=1;i<8;i++){
         document.getElementById("zhou"+i).disabled=true;
	     document.getElementById("zhou"+i).checked=false;
     }
	}
	if(str=="mzh2"){
	  editradio("6","2");
	  for(var i=1;i<8;i++){
        document.getElementById("zhou"+i).disabled=false;
	    //document.getElementById("y"+i).checked=false;
     }
	}
 }
</script>
<smart:initHead title="长宁区人事管理信息系统--预警预告" />
<style type="text/css">
	div.minute div{
		width:6%;
		display: inline-block;
	}
	div.hour div{
		width:8%;
		display: inline-block;
	}
	div.day div{
		width:5.6%;
		display: inline-block;
	}
	div.month div{
		width:5.6%;
		display: inline-block;
	}
	.dataSpan{
		margin-left: 14px;
	}
</style>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<div class="layui-tab">
						<ul class="layui-tab-title">
							<li class="layui-this" style="width:15%;">分</li>
							<li style="width:15%;">时</li>
							<li style="width:15%;">天</li>
							<li style="width:15%;">月</li>
							<li style="width:15%;">周</li>
						</ul>
						<div class="layui-tab-content">
							<!-- 分 -->
							<div class="layui-tab-item layui-show">
								<table style="width:100%;">
							        <tr>
							          <td width="3%" height="27" align="left" valign="middle">
							              <div align="left">
							                <input name="fz" id="fz" type="radio" value="fz1" onclick="fz_radio_click(this)">
							              </div></td>
							          <td width="22%" align="left" valign="middle"> <div align="left">秒周期循环：从</div></td>
							          <td width="15%" align="left" valign="middle"><div align="left">
							            <input name="mks" id="mks" type="text" class="DG-spinner" id="testElement" value="0" maxlength="3" properties="maxValue:59,minValue:0,shiftIncrement:20">
							          </div></td>
							          <td width="25%" align="left" valign="middle"> <div align="left">秒钟开始时间，间隔</div></td>
							          <td width="15%" align="left" valign="middle"><div align="left">
							            <input name="mzx" id="mzx" type="text" class="DG-spinner" id="text" value="0" maxlength="3" properties="maxValue:59,minValue:0,shiftIncrement:20" >
							          </div></td> 
							          <td width="20%" align="left" valign="middle"><div align="left">秒钟执行一次</div></td>
							        </tr>
							        <tr>
							          <td height="25" valign="middle">
									    <div align="left">
									      <input name="fz" id="fz" type="radio" onclick="fz_radio_click(this)" value="fz2" checked>
								        </div></td>
							          <td height="25"><div align="left">分周期循环：从 </div></td>
							          <td height="25"><div align="left">
							            <input name="fks" id="fks" type="text" class="DG-spinner"  value="0" maxlength="3" properties="maxValue:59,minValue:0,shiftIncrement:20">
							          </div></td>
							          <td height="25"><div align="left">分钟开始时间，间隔</div></td>
							          <td height="25"><div align="left">
							            <input name="fzx" id="fzx" type="text" class="DG-spinner"  value="1" maxlength="3" properties="maxValue:59,minValue:0,shiftIncrement:20">  
							          </div></td>
							          <td height="25"><div align="left">分钟执行一次</div></td>
							        </tr>
							        <tr>
							          <td height="25" valign="middle">
							              <div align="left">
							                <input name="fz" id="fz" type="radio" value="fz3" onclick="fz_radio_click(this)">
							              </div></td>
							          <td height="25"><div align="left">手动指定</div></td>
							          <td height="25">&nbsp;</td>
							          <td height="25">&nbsp;</td>
							          <td height="25">&nbsp;</td>
							          <td height="25">&nbsp;</td>
							        </tr>
						        </table>
						        <div style="width:100%;" class="minute">
						        	<c:forEach items="${minute}" var="m">
							        	<div><input type="checkbox" name="chk${m}" id="chk${m}" value="${m}" disabled="disabled"><span class="dataSpan">${m}</span></div>
						        	</c:forEach>
						        </div>
							</div>
							<!-- 时 -->
							<div class="layui-tab-item">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
							        <tr>
							          <td width="10" height="26"><div align="left">
							            <input name="xs" id="xs" type="radio" value="xs1" checked  onClick="xs_radio_click(this)">
							          </div></td>
							          <td width="97%" align="left"><div align="left">每一小时</div></td>
							        </tr>
							        <tr>
							          <td height="20" width="10"><div align="left">
							            <input name="xs" id="xs" type="radio" value="xs2" onClick="xs_radio_click(this)">
							          </div></td>
							          <td height="20" align="left"><div align="left">手动指定</div></td>
							        </tr>
						        </table>
						        <div style="width:100%;" class="hour">
						        	<c:forEach items="${hour}" var="m">
							        	<div><input type="checkbox" name="sj${m}" id="sj${m}" value="${m}" disabled="disabled"><span class="dataSpan">${m}</span></div>
						        	</c:forEach>
						        </div>
							</div>
							<!-- 天 -->
							<div class="layui-tab-item">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
							        <tr>
							          <td width="10" height="24"><div align="left">
							            <input name="mt" id="mt" type="radio" value="mt1" checked onClick="mt_radio_click(this)" >
							          </div></td>
							          <td width="97%" align="left"><div align="left">每一天（<span class="STYLE1">温馨提示：如果使用了周循环天循环不起效！</span>）</div></td>
							        </tr>
							        <tr>
							          <td height="20" width="10"><div align="left">
							            <input name="mt" id="mt" type="radio" value="mt2"  onClick="mt_radio_click(this)">
							          </div></td>
							          <td height="20" align="left"><div align="left">手动指定</div></td>
							        </tr>
						        </table>
						        <div style="width:100%;" class="day">
						        	<c:forEach items="${day}" var="m">
							        	<div><input type="checkbox" name="t${m}" id="t${m}" value="${m}" disabled="disabled"><span class="dataSpan">${m}</span></div>
						        	</c:forEach>
						        </div>
							</div>
							<!-- 月 -->
							<div class="layui-tab-item">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
							        <tr>
							          <td height="20" width="20"><div align="left">
							            <input name="my"   id="my" type="radio" value="my1" checked onClick="my_radio_click(this)">
							          </div></td>
							          <td width="570"><div align="left">每一月</div></td>
							        </tr>
							        <tr>
							          <td height="20" width="20"><div align="left">
							            <input name="my"  id="my" type="radio" value="my2"  onClick="my_radio_click(this)">
							          </div></td>
							          <td height="20"><div align="left">手动指定</div></td>
							        </tr>
						        </table>
						        <div style="width:100%;" class="month">
						        	<c:forEach items="${month}" var="m">
							        	<div><input type="checkbox" name="y${m}" id="y${m}" value="${m}" disabled="disabled"><span class="dataSpan">${m}</span></div>
						        	</c:forEach>
						        </div>
							</div>
							<!-- 周 -->
							<div class="layui-tab-item">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
							        <tr>
							          <td height="20" width="10"><input name="mzhou" id="mzhou" type="checkbox" value="checkbox" onclick="zhou_checkbox_click()"></td>
							          <td width="100%"><div align="left">每一周(<span class="STYLE1">温馨提示：选择周循环则天循环失效！</span>)</div></td>
							        </tr>
							        <tr>
							          <td height="50" colspan="2" align="center">
							          	<table width="100%" border="0" cellpadding="0" cellspacing="0 ">
							            <tr>
							              <td width="51"><div align="left">
							                <input name="mzh"  id="mzh" type="radio"   disabled="disabled" value="mzh1" checked onclick="zhou_radio_click(this)">
							              </div></td>
							              <td colspan="14"><div align="left">每一周</div></td>
							              </tr>
							            <tr>
							              <td><div align="left">
							                <input name="mzh" id="mzh"  type="radio" value="mzh2"  disabled="disabled" onclick="zhou_radio_click(this)">
							              </div></td>
							              <td colspan="14"><div align="left">手动指定</div></td>
						              	</tr>
							            <tr>
							              <td>&nbsp;</td>
							              <td width="10"><input type="checkbox" name="zhou1" id="zhou1"  value="1" disabled="disabled"></td>
							              <td width="35">周一</td>
							              <td width="10"><input type="checkbox" name="zhou2" id="zhou2"  value="2" disabled="disabled"></td>
							              <td width="35">周二</td>
							              <td width="10"><input type="checkbox" name="zhou3" id="zhou3"  value="3" disabled="disabled"></td>
							              <td width="35">周三</td>
							              <td width="10"><input type="checkbox" name="zhou4" id="zhou4"  value="4" disabled="disabled"></td>
							              <td width="35">周四</td>
							              <td width="10"><input type="checkbox" name="zhou5" id="zhou5"  value="5" disabled="disabled"></td>
							              <td width="35">周五</td>
							              <td width="10"><input type="checkbox" name="zhou6" id="zhou6"  value="6" disabled="disabled"></td>
							              <td width="35">周六</td>
										  <td width="10"><input type="checkbox" name="zhou7"  id="zhou7" value="7" disabled="disabled"></td>
							              <td width="35">周日</td>
							            </tr>
							          </table></td>
							        </tr>
						      </table>
							</div>
						</div>
					</div>
				</smart:gridRow>
				<smart:gridRow>
					<smart:gridColumn>
						<smart:buttonGroup container="true">
							<smart:button method="confirm" size="sm" title="确认"
								theme="default">
								<smart:icon icon="plus">&nbsp;确认</smart:icon>
							</smart:button>
							<smart:button size="sm" method="goBack" title="取消" theme="warm">
								<smart:icon icon="reply">&nbsp;取消</smart:icon>
							</smart:button>
						</smart:buttonGroup>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:buttonScriptAction>
			goBack : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
			,confirm : function(){
			  //var mks,mzx,fks,fzx,chk1,sj0,t1,y1,zhou1,d4311,d4312="";
			  var values=new HashMap(); 
			  //循环周期 分钟处理
			   if(radio_type.get("1")=="1"){
			      var mks=document.getElementById("mks").value;
				  var mzx=document.getElementById("mzx").value;
				  var str=mks+"/"+mzx;
				  values.put("1",str);
				  values.put("m","从"+mks+"秒开始,每隔"+mzx);
			   }
			   if(radio_type.get("1")=="2"){
			      var fks=document.getElementById("fks").value;
				  var fzx=document.getElementById("fzx").value;
				  var str=fks+"/"+fzx;
				  values.put("2",str);
				  values.put("f","从"+fks+"分开始,每隔"+fzx);
			   }
			   if(radio_type.get("1")=="3"){
			    var fen="";
			    for(var i=0;i<60;i++){ 
				   if(document.getElementById("chk"+i).checked)
				   fen+=document.getElementById("chk"+i).value+",";
				}
			     values.put("2",fen.substring(0,fen.length-1));
			   }
			//小时处理
			    if(radio_type.get("2")=="1"){
			        values.put("5","*");
				}
			    if(radio_type.get("2")=="2"){
					var xiaoshi="";
			        for(var i=0;i<24;i++){
				      if(document.getElementById("sj"+i).checked)
			             xiaoshi+=document.getElementById("sj"+i).value+",";   
			        }
			         values.put("5",xiaoshi.substring(0,xiaoshi.length-1));
				}
			//天处理
			  if(radio_type.get("3")=="1"){
			     values.put("6","*");
			  }
			  if(radio_type.get("3")=="2"){
			    var tian="";
			    for(var i=1;i<32;i++){
				     if(document.getElementById("t"+i).checked)
			             tian+=document.getElementById("t"+i).value+",";   
			    }
			         values.put("6",tian.substring(0,tian.length-1));
			  }
	
			//月处理
			  if(radio_type.get("4")=="1"){
			     values.put("7","*");
			  }
			  if(radio_type.get("4")=="2"){
			    var yue="";
			    for(var i=1;i<13;i++){
				     if(document.getElementById("y"+i).checked)
			             yue+=document.getElementById("y"+i).value+",";   
			    }
			         values.put("7",yue.substring(0,yue.length-1));
			  }
	
			//周处理
	
			  if(radio_type.get("5")=="1"){
			     values.put("8","?");
			  }
			  if(radio_type.get("5")=="2"){
			     if(radio_type.get("6")=="1"){
				   values.put("8","*");
				 }
				 if(radio_type.get("6")=="2"){
				    var zhou="";
			        for(var i=1;i<8;i++){
				        if(document.getElementById("zhou"+i).checked)
			             zhou+=document.getElementById("zhou"+i).value+",";   
			         }
			         values.put("8",zhou.substring(0,zhou.length-1));
			      }
			  }
			   
				//勾选周循环天循环失效
				if(radio_type.get("5")=="2"&&values.containsKey("6")){
				    values.remove("6");
					values.put("6","?");
				}
				//秒循环设0
				if(values.get("1")==null||values.get("1")=="0/0"){
				   if(values.containsKey("1")){
				      values.remove("1");
					  values.put("1","0");
				   }else{
				      values.put("1","0");
				   } 
				}
				//分循环设0
				if(values.get("2")==null||values.get("2")=="0/0"){
				   if(values.containsKey("2")){
				      values.remove("2");
					  values.put("2","*");
				   }else{
				      values.put("2","*");
				   } 
				}
				/* var cronstr="秒:"+values.get("1")+"  分:"+values.get("2")+"  小时:"+values.get("5")+
				       "  天:"+values.get("6")+" 月"+values.get("7")+"  周:"+values.get("8"); */
				     
				var cronstr="每年"+(values.get("7")!="*"?values.get("7"):"每")+"月"+(values.get("6")!="*"?values.get("6"):"每")+"日"+(values.get("5")!="*"?values.get("5"):"每")+"时"
				 +(values.get("2").indexOf("/") > 0?values.get("f"):(values.get("2")!="*"?values.get("2"):"每")) +"分"+(values.get("1").indexOf("/") > 0?values.get("m"):(values.get("1")!="*"?values.get("1"):"每"))+"秒执行."
				
				var cron=values.get("1")+" "+values.get("2")+" "+values.get("5")+" "+values.get("6")+" "+values.get("7")+"  "+values.get("8")
				
				window.parent.getTimeValues(cronstr,cron);
			}
		 </smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>