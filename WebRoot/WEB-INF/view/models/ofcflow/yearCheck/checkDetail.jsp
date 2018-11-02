<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="事项申请-年度考核" />
</head>
<smart:body>
	<smart:grid>
		<smart:gridRow>
			<smart:accordionPanelItem title="公务员基本信息" isShow="false"></smart:accordionPanelItem>
			<smart:infoshowercontainer>
				<smart:infoshowerrow>
					<smart:infoshower infoname="单位" infovalue="黄浦区公务员局"></smart:infoshower>
					<smart:infoshower infoname="部门" infovalue="黄浦区民政局财务科"></smart:infoshower>
					<smart:infoshowerimg rowspan="7" colspan="2" src="static/image/20170705135600.jpg"></smart:infoshowerimg>
				</smart:infoshowerrow>
				<smart:infoshowerrow>
					<smart:infoshower infoname="姓名" infovalue="流转"></smart:infoshower>
					<smart:infoshower infoname="性别" infovalue="男"></smart:infoshower>
				</smart:infoshowerrow>
				<smart:infoshowerrow>
					<smart:infoshower infoname="出生年月" infovalue="1995-7-31"></smart:infoshower>
					<smart:infoshower infoname="民族" infovalue="汉族"></smart:infoshower>
				</smart:infoshowerrow>
				<smart:infoshowerrow>
					<smart:infoshower infoname="政治面貌" infovalue="中共党员"></smart:infoshower>
					<smart:infoshower infoname="身份证号"  infovalue="310108199507310000"></smart:infoshower>
				</smart:infoshowerrow>
				<smart:infoshowerrow>
					<smart:infoshower infoname="籍贯" infovalue="成都市"></smart:infoshower>
					<smart:infoshower infoname="健康状况"  infovalue="健康或良好"></smart:infoshower>
				</smart:infoshowerrow>
				<smart:infoshowerrow>
					<smart:infoshower infoname="家庭住址" infovalue=""></smart:infoshower>
					<smart:infoshower infoname="户口所在地" ></smart:infoshower>
				</smart:infoshowerrow>
				<smart:infoshowerrow>
					<smart:infoshower infoname="现任职务" infovalue="科长"></smart:infoshower>
					<smart:infoshower infoname="级别" ></smart:infoshower>
				</smart:infoshowerrow>
			</smart:infoshowercontainer>
	
		</smart:gridRow>
		<smart:gridRow>
			<smart:form method="post" id="myform">
				<smart:accordionPanelItem title="考核结果录入" isShow="false"></smart:accordionPanelItem>
				<smart:infoshowercontainer>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:singleSelect labelName="考核结果" data="[{'key':'0','value':'优秀'},{'key':'1','value':'称职'}]"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:singleSelect labelName="是否嘉奖" data="[{'key':'1','value':'是'},{'key':'0','value':'否'}]"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:textarea labelName="备注" display="block"></smart:textarea>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:infoshowercontainer>
			</smart:form>
		</smart:gridRow>
		<smart:gridRow>
			<smart:gridColumn colOffset="5">
				<smart:buttonGroup container="true">
					<smart:button theme="default" size="lg" title="提交" radius="true">
						<smart:icon icon="check"></smart:icon>
					</smart:button>
					<smart:button theme="danger" size="lg" title="返回">
						<smart:icon icon="reply"></smart:icon>
					</smart:button>
				</smart:buttonGroup>
			</smart:gridColumn>
		</smart:gridRow>
	</smart:grid>
	
	<smart:scriptHead models="table,form,layer,elementl"></smart:scriptHead>
</smart:body>
</html>