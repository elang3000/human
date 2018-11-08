<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区公务员信息管理系统--专项统计" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="专项统计"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="公务员处分统计" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
						<smart:gridColumn>
							<smart:grid>
								<smart:gridRow>
									<smart:textInput type="hidden" id="departId" value="${departId}"></smart:textInput>
									<smart:textInput type="hidden" id="year" value="${year}"></smart:textInput>
									<smart:textInput type="hidden" id="departName" value="${departName}"></smart:textInput>
									
									<smart:gridColumn>
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
        }).use(['index','specialCount/punishCount/punishCount']);
	</script>
</smart:body>
</html>