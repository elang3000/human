<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请--汇总表单查看" />
<script type="text/javascript">
	var tabNum=3;
</script>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员录用" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<%@include file="modual/draftHeadTab.jsp" %>
				</smart:gridRow>
				<smart:gridRow>
					<smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
						<smart:form>
							<smart:gridRow>
							<% 
							String path = request.getContextPath();  
							String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
							String remoteAddress=request.getRemoteAddr();  
							String servletPath=request.getServletPath();  
							String realPath=request.getRealPath("/");  
							String remoteUser=request.getRemoteUser();  
							String requestURI=request.getRequestURI();  
/* 							out.println("path:"+path+"<br>");   */
							%>
<%-- 								<smart:gridColumn colPart="3">
									<smart:date labelName="年份" display="block" name="yearTip" id="year" placeholder="年份"></smart:date>
								</smart:gridColumn> --%>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="序列号" display="block" name="serialNumber"
										placeholder="请填写序列号"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="签发人" display="block" name="signer"
										placeholder="请填写签发人"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="2" colOffset="1">
									<smart:button theme="normal" size="sm" method="search"
										title="查询">
										<smart:icon icon="search"></smart:icon>
									</smart:button>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>

					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn colPart="12" deviceType="md">
						<smart:table id="navigationList" url="ofcflow/draftServantReportResult/list"
							height="full-215" text="未获取到数据！">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="creater" width="150" sort="true">创建者</smart:tableItem>
								<smart:tableItem field="unitName" width="150" sort="false">单位名称</smart:tableItem>
								<smart:tableItem field="serialNumber" width="150" sort="false">序列号</smart:tableItem>
								<smart:tableItem field="signer" width="150" sort="false">签发人</smart:tableItem>
								<smart:tableItem field="isAgree" width="150" sort="false">是否市局同意</smart:tableItem>
								<smart:tableItem field="total" width="150" sort="true">总共汇总条数</smart:tableItem>
								<smart:tableItem field="total310" width="150" sort="true">总共310人数</smart:tableItem>
								<smart:tableItem field="totalGwy" width="150" sort="false">公务员人数</smart:tableItem>
								<smart:tableItem field="totalGwy310" width="150" sort="false">公务员310人数</smart:tableItem>
								<smart:tableItem field="totalCg" width="150" sort="false">参公人数</smart:tableItem>
								<smart:tableItem field="totalCg310" width="150" sort="false">参公310人数</smart:tableItem>

								<smart:tableItem align="center" fixed="right" width="150" unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="view" title="查看">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
								

							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils/>
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
			view : function(data) {
				smart.show({
					title : '信息表',
					size : 'full',
					url : "ofcflow/draftServantReportResult/detail?id="+data.data.id,
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = utils.json($('.layui-form'));
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		 </smart:buttonScriptAction>
		<smart:dateRender type="year" id="year" />
		$(".layui-tab-title li").removeClass("layui-this");
		$(".layui-tab-title li:eq("+tabNum+")").addClass("layui-this");
	</smart:scriptHead>
	
</smart:body>
</html>