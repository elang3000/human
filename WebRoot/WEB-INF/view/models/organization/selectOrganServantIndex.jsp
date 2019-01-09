<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="长宁区人事管理信息系统--机构人员查看" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="查询条件" color="blue">
						<smart:form id="searchForm_ofcList" clazz="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="姓名：" autocomplete="off" placeholder="输入姓名" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="身份证号：" autocomplete="off" placeholder="输入身份证号" name="cardNo"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="性别：" display="block" name="sex.id" url="dictquery/sub/code/GBT_2261_1_2003" isAddDefaltOption="true">
									</smart:singleSelect>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="12">
									<smart:buttonGroup container="true" align="right">
										<smart:button size="sm" method="search_ofcList" title="查询"
											theme="primary" other="search_btn">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
			  				 		</smart:button>
										<smart:button size="sm" method="history" title="重置"
											theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
			   						</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
				</smart:gridRow>
				<smart:gridRow colSpace="5">
					<smart:gridColumn>
						<smart:table id="navigationList_ofcList" url="ofc/pageList?departId=${createOrganNodeId}&type=1" text="未找到用户数据！" page="true" height="full-180">
							<tr>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sex" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="cardNo" width=".15" sort="true">身份证号</smart:tableItem>
								<smart:tableItem field="departName" width=".15" sort="false">单位部门</smart:tableItem>
								<smart:tableItem field="postName" width=".1" sort="false">职务名称</smart:tableItem>
								<smart:tableItem field="postAttributeName" width="120" sort="false">职务属性</smart:tableItem>
								<smart:tableItem field="jobLevel" width=".1" sort="false">职级名称</smart:tableItem>
								<smart:tableItem field="isOnHold" width=".1" sort="false">状态</smart:tableItem>
								<smart:tableItem align="center" width=".07" fixed="right"
									unresize="true" toolbar="navListToolBar_ofcList">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar_ofcList">
								<smart:tableToolBtn theme="warm" event="select" title="选择人员">
									<smart:icon icon="check-circle"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils />
		<smart:tableScriptAction tableId="navigationList_ofcList" checkbox="false"
			sort="true" rowEdit="false">    
			select : function(result) {
				var index = parent.layer.getFrameIndex(window.name);
				parent.setSeletResourceData(result.data);
				parent.layer.close(index);
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search_ofcList : function() {
				var params = utils.json($('#searchForm_ofcList'));
				table.reload('navigationList_ofcList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>	
	</smart:scriptHead>
</smart:body>
</html>