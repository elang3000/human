<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="查询统计与分析--综合查询" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="查询统计与分析"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="快捷查询" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>

				<smart:form id="searchForm">
					<div id="query" class="layui-fluid">
						<smart:gridRow>
							<smart:fieldSet title="查询条件" color="blue">
								<smart:accordionPanel id="panel" isAccordion="true">
									<smart:accordionPanelItem title="快捷查询" isShow="false">
										<div class="layui-block">
											<label class="layui-form-label">1.学历</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikA"> <input
													type="checkbox" name="qucikA" title="本科" value="1"
													lay-skin="primary"> <input type="checkbox"
													name="qucikA" title="专科" value="2" lay-skin="primary">
												<input type="checkbox" name="qucikA" title="硕士" value="3"
													lay-skin="primary"> <input type="checkbox"
													name="qucikA" title="博士" value="4" lay-skin="primary">
												<input type="checkbox" name="qucikA" title="其他" value="5"
													lay-skin="primary">
											</div>
										</div>

										<div class="layui-block">
											<label class="layui-form-label">2.年龄</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikB"> <input
													type="checkbox" name="qucikB" title="35" value="1"
													lay-skin="primary"> <input type="checkbox"
													name="qucikB" title="35~45" value="2" lay-skin="primary">
												<input type="checkbox" name="qucikB" title="45~55" value="3"
													lay-skin="primary"> <input type="checkbox"
													name="qucikB" title="55以上" value="4" lay-skin="primary">
											</div>
										</div>

										<div class="layui-block">
											<label class="layui-form-label">3.性别</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikC"> <input
													type="checkbox" name="qucikC" title="男" value="1"
													lay-skin="primary"> <input type="checkbox"
													name="qucikC" title="女" value="2" lay-skin="primary">
											</div>
										</div>

										<div class="layui-block">
											<label class="layui-form-label">4.党派</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikD"> <input
													type="checkbox" name="qucikD" title="中共" value="1"
													lay-skin="primary"> <input type="checkbox"
													name="qucikD" title="共青团" value="2" lay-skin="primary">
												<input type="checkbox" name="qucikD" title="民族党派" value="3"
													lay-skin="primary"> <input type="checkbox"
													name="qucikD" title="群众" value="4" lay-skin="primary">
											</div>
										</div>

										<div class="layui-block">
											<label class="layui-form-label">5.民族</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikE"> <input
													type="checkbox" name="qucikE" title="汉" value="1"
													lay-skin="primary"> <input type="checkbox"
													name="qucikE" title="少数民族" value="2" lay-skin="primary">
											</div>
										</div>

										<div class="layui-block">
											<label class="layui-form-label">6.专业</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikF"> <input
													type="checkbox" name="qucikF" title="文史类" value="1"
													lay-skin="primary"> <input type="checkbox"
													name="qucikF" title="理工" value="2" lay-skin="primary">
												<input type="checkbox" name="qucikF" title="法学" value="3"
													lay-skin="primary"> <input type="checkbox"
													name="qucikF" title="管理学" value="4" lay-skin="primary">
												<input type="checkbox" name="qucikF" title="经济学" value="5"
													lay-skin="primary"> <input type="checkbox"
													name="qucikF" title="其它" value="6" lay-skin="primary">
											</div>
										</div>

										<div class="layui-block">
											<label class="layui-form-label">7.学校种类</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikG"> <input
													type="checkbox" name="qucikG" title="双一流" value="1"
													lay-skin="primary"> <input type="checkbox"
													name="qucikG" title="985" value="2" lay-skin="primary">
												<input type="checkbox" name="qucikG" title="211" value="3"
													lay-skin="primary"> <input type="checkbox"
													name="qucikG" title="其它" value="4" lay-skin="primary">
											</div>
										</div>

										<div class="layui-block">
											<label class="layui-form-label">8.职务层次</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikH"> <input
													type="checkbox" name="qucikH" title="正科" value="1"
													lay-skin="primary"> <input type="checkbox"
													name="qucikH" title="主任科员" value="2" lay-skin="primary">
												<input type="checkbox" name="qucikH" title="副科" value="3"
													lay-skin="primary"> <input type="checkbox"
													name="qucikH" title="副主任科员" value="4" lay-skin="primary">
											</div>
										</div>
										
										<div class="layui-block">
											<label class="layui-form-label">9.人员状态</label>
											<div class="layui-input-block">
												<input type="hidden" name="qucikI"> 
												<input type="checkbox" name="qucikI" title="现职" value="1" lay-skin="primary"> 
												<input type="checkbox" name="qucikI" title="调出" value="2" lay-skin="primary">
												<input type="checkbox" name="qucikI" title="试用期" value="3" lay-skin="primary"> 
												<input type="checkbox" name="qucikI" title="其他" value="4" lay-skin="primary">
											</div>
										</div>
									</smart:accordionPanelItem>
								</smart:accordionPanel>

								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:textInput labelName="姓名：" autocomplete="off"
											placeholder="输入姓名" name="name">
										</smart:textInput>
									</smart:gridColumn>

									<smart:gridColumn colPart="4">
										<smart:textInput labelName="单位部门：" autocomplete="off"
											placeholder="输入单位部门" name="departName">
										</smart:textInput>
									</smart:gridColumn>

									<smart:gridColumn colPart="4">
										<smart:buttonGroup container="true">
											<smart:button size="sm" method="search" title="查询"
												theme="primary">
												<smart:icon icon="search"></smart:icon>&nbsp;查询
				  				 </smart:button>
											<smart:button size="sm" method="history" title="重置"
												theme="primary" type="reset">
												<smart:icon icon="history"></smart:icon>&nbsp;重置
				   				</smart:button>
										</smart:buttonGroup>
									</smart:gridColumn>
								</smart:gridRow>

							</smart:fieldSet>
						</smart:gridRow>
					</div>

				</smart:form>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList"
							url="analysis/qucikQuery/queryList" height="full-190"
							text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="name" width=".08" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".08" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".16" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".11" sort="false">单位部门</smart:tableItem>
								<smart:tableItem field="postName" width=".12" sort="false">职务名称</smart:tableItem>
								<smart:tableItem field="postAttributeName" width=".12"
									sort="false">职务属性</smart:tableItem>
								<smart:tableItem field="jobLevel" width=".12" sort="false">职级名称</smart:tableItem>
								<smart:tableItem field="isOnHold" width=".11" sort="false">人员状态</smart:tableItem>
								<smart:tableItem align="center" fixed="right" width=".1"
									unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
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
		<smart:utils />
		<smart:continuousSelectAction />
		<smart:tableScriptAction tableId="navigationList" checkbox="true"
			sort="true" rowEdit="true">
				view : function(data) {
					smart.show({
					title : '公务员信息',
					size : 'full',
					url : 'ofc/main/view?id='+data.data.id,
					scrollbar : false,
					});
				}
			</smart:tableScriptAction>
		var buttonInvokeMethod = {
			back :function() {
				$('#advancedQuery').hide();
			},
			search : function(){
				var params = utils.json($('.layui-form'));
				$.each(params, function(j) {
					if(params[j] instanceof Array){
						params[j].splice(0,1);//数组时，去除第一个空字符串
						params[j] = params[j].join(',');//数组转成为字符串
					}
				});
				
				table.reload('navigationList', {
					where : params,
					page : {
						curr : 1
					}
				});
			},
		}

		$('#searchForm button').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
		
	</smart:scriptHead>
</smart:body>
</html>