<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--通用统计" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="专项统计"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员奖励统计" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:textInput type="hidden" id="departId" value="${departId}"></smart:textInput>
									
									<smart:gridColumn colPart="8">
										<smart:gridRow>
											<smart:gridColumn>
												<div class="layui-card">
													<div class="layui-card-body">
														<div id="chart1" style="width: 100%;height: 420px">
														</div>
													</div>
												</div>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
									
									<smart:gridColumn colPart="4">
										<smart:gridRow>
											<smart:gridColumn>
													<smart:infoShowerLabel infoname="单位名称" infovalue="1"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										
										<smart:gridRow>
											<smart:gridColumn>
													<smart:infoShowerLabel infoname="单位名称" infovalue="1"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
										
										<smart:gridRow>
											<smart:gridColumn>
													<smart:infoShowerLabel infoname="单位名称" infovalue="1"></smart:infoShowerLabel>
											</smart:gridColumn>
										</smart:gridRow>
									</smart:gridColumn>
									
									
									
								</smart:gridRow>
							</smart:grid>
						</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
    </smart:scriptHead>
	<script>
        layui.config({
            base: 'layadmin/'
        }).extend({
            index: 'lib/index'
        }).use(['index','specialCount/rewardCount/rewardCount']);
	</script>
</smart:body>
</html>