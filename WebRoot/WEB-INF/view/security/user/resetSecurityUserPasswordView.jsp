<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
</head>
<smart:body>
	<smart:grid>
		<div class="layui-card">
			<div class="layui-card-body">

				<smart:fieldSet title="修改用户密码" color="blue">
					<smart:form>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="原用户密码：" autocomplete="off" placeholder="请输入原始用户密码" verify="required" name="organal">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="新密码：" autocomplete="off" placeholder="请输入新的用户密码" verify="required" name="name">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:textInput labelName="确认信息密码：" autocomplete="off" placeholder="请再次输入新的用户密码" verify="required" name="name">
								</smart:textInput>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:buttonGroup container="true">
									<smart:button size="sm" method="confirm" title="确认">
										<smart:icon icon="check"></smart:icon>&nbsp;确认
				   				</smart:button>
									<smart:button size="sm" theme="warm" method="reset" title="重置">
										<smart:icon icon="rotate-left"></smart:icon>&nbsp;重置
				   				</smart:button>
								</smart:buttonGroup>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:form>
				</smart:fieldSet>
			</div>
		</div>
	</smart:grid>
	<smart:scriptHead models="form,layer,element">
		<smart:utils />
	</smart:scriptHead>
</smart:body>
</html>