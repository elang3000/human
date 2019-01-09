<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="信息维护--公务员登记" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:gridRow>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员调任管理" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:gridRow>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form action="ofcflow/diaorenOut/edit">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="servantId" id="servantId"></smart:textInput>
					<smart:textInput type="hidden" value="1" id="status"></smart:textInput>
					<smart:textInput type="hidden" name="id" id="zhuanRenId"></smart:textInput>
					<smart:accordionPanel id="test" isAccordion="true">
						<smart:accordionPanelItem title="人员检索" isShow="true">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput isNotNull="true" verify="required"
										labelName="姓名：" name="name" id="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput isNotNull="true" verify="required"
										labelName="身份证：" name="cardNo" id="cardNo"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect isNotNull="true" labelName="调出区域：" display="block" id="areaType" data="${areaTypeList}"></smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:textInput labelName="现工作单位：" name="departName" id="departName"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:accordionPanelItem>
					</smart:accordionPanel>
					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<smart:button method="checkServant" size="sm" title="检索">
									<smart:icon icon="check"></smart:icon>&nbsp;检索
								</smart:button>
								<smart:button method="save" size="sm" title="提交"
									theme="normal">
									<smart:icon icon="plus"></smart:icon>&nbsp;提交
								</smart:button>
								<smart:button size="sm" method="back" title="返回" theme="warm">
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
		<smart:buttonScriptAction>
			checkServant : function(data) {
				var name = $("#name").val();
				var cardNo = $("#cardNo").val();
				if(name&&name!=""&&cardNo&&cardNo!=""){
					var requestConfig = {};
					requestConfig.url = "ofcflow/diaorenOut/checkServant";
					requestConfig.data = {name:name,cardNo:cardNo};
					requestConfig.success = function(data){
						$("#status").val("2");//修改为已检索
						if (data.success) {
							$("#status").val("3");//修改为可提交
							var code = data.data.code;
							if(code=="1"){//人员库中检索出的数据，点击提交后不可编辑
								$("#departName").val(data.data.departname);
								$("#servantId").val(data.data.id);
							}else if(code=="2"){//调任信息中检索出的数据，点击后可编辑
								$("#zhuanRenId").val(data.data.id);
								if(data.data.departname){
									$("#departName").val(data.data.departname);
								}
							}else if(code=="3"){//正在调任流程
								$("#status").val("4");
							}
						}
						layer.alert(data.message);
					}
					smart.request(requestConfig);
				}else{
					smart.message({
						message : "请录入姓名和身份证号！"
						,type : 'W' //S保存  I问号  W感叹号 E错误
					});
				}
			}
			,back : function() {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
			,save : function() {
				if($("#status").val()=="1"){
					smart.message({
						message : "请先进行人员检索！"
						,type : 'W' //S保存  I问号  W感叹号 E错误
					});
				}else if($("#status").val()=="2"){
					smart.message({
						message : "系统中不存在该公务员，无法发起调出申请！"
						,type : 'W' //S保存  I问号  W感叹号 E错误
					});
				}else if($("#status").val()=="4"){
					smart.message({
						message : "该人员正在调出流程中，无法再次发起调出申请！"
						,type : 'W' //S保存  I问号  W感叹号 E错误
					});
				}else{
					var id = $("#servantId").val();
					var name = $("#name").val();
					var cardNo = $("#cardNo").val();
					var zhuanRenId = $("#zhuanRenId").val();
					var areaType = $("#areaType").val();
					window.location.href="ofcflow/diaorenOut/edit?servantId="+id+"&id="+zhuanRenId+"&areaType="+areaType;
				}
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>