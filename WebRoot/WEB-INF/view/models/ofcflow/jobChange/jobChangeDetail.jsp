<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<smart:initHead title="事项申请--公务员职务变动详情" />
</head>
<smart:body>
	<smart:card>
		<smart:cardHead>
			<smart:gridRow>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="职务变动" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:gridRow>
		</smart:cardHead>
		<smart:cardBody>
			<smart:gridRow colSpace="5">
				<smart:gridColumn>
					<smart:title title="人员基本信息" style="margin-top: 5px;" color="blue" />
					<smart:grid>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="单位部门"
											infovalue="${servant.departName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="姓名"
											infovalue="${servant.name}"></smart:infoShowerLabel>
										<smart:textInput type="hidden" name="id" value="${servant.id}"></smart:textInput>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="姓名拼音"
											infovalue="${servant.pinYinName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="姓名拼音缩写"
											infovalue="${servant.shortName}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="出生日期"
											infovalue="${servant.birthDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="性别"
											infovalue="${servant.sex.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="民族"
											infovalue="${servant.nation.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="政治面貌"
											infovalue="${servant.politics.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="出生地"
											infovalue="${servant.birthPlace.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="籍贯"
											infovalue="${servant.nativePlace.name}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="成长地"
											infovalue="${servant.growPlaceName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="6">
										<smart:infoShowerLabel infoname="户籍所在地"
											infovalue="${servant.residencePlace}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:gridColumn>
							<smart:gridColumn colPart="3" colOffset="1">
								<smart:gridRow>
									<smart:gridColumn colPart="12">
										<img alt="照片" src="static/image/20170705135600.jpg">
									</smart:gridColumn>
								</smart:gridRow>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="婚姻状况"
										infovalue="${servant.marriage.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="健康状况"
										infovalue="${servant.health.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="健康状况描述"
									infovalue="${servant.healthDescribe}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="个人身份有效证件名称"
										infovalue="${servant.cardType.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="个人身份有效证件号码"
										infovalue="${servant.cardTypeNo}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="公民身份证号"
									infovalue="${servant.cardNo}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="专业技术职务"
										infovalue="${servant.expertisePost}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="专长"
										infovalue="${servant.expertise}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="专业技术类公务员任职资格"
									infovalue="${servant.qualifications.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="8">
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="入党时间"
										infovalue="${servant.joinParty}"></smart:infoShowerLabel>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:infoShowerLabel infoname="第二党派"
										infovalue="${servant.secondParty.name}"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="第三党派"
									infovalue="${servant.thirdParty.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
					</smart:grid>
				</smart:gridColumn>
			</smart:gridRow>

			<smart:gridRow>
				<smart:title title="职务信息" style="margin-top: 5px;" color="blue" />
				<smart:table id="postNavigationList"
					url="ofcflow/jobchange/activeAndNoChangeJob/${servantId}" height="full"
					page="false" limit="10000" text="未找到职务数据！">
					<tr>
						<smart:tableItem field="postName" width=".2" sort="false">职务名称</smart:tableItem>
						<smart:tableItem field="tenureName" width=".2" sort="false">任职机构名称</smart:tableItem>
						<smart:tableItem field="tenureReason" width=".2" sort="false">任职原因</smart:tableItem>
						<smart:tableItem field="tenureStatus" width=".2" sort="false">任职状态</smart:tableItem>
						<smart:tableItem align="center" width=".2" fixed="right"
							unresize="true" toolbar="postNavListToolBar">操作</smart:tableItem>
					</tr>
					<smart:tableToolBar id="postNavListToolBar">
						<smart:tableToolBtn theme="danger" event="promote" title="晋升">
							<smart:icon icon="arrow-up"></smart:icon>
						</smart:tableToolBtn>
						<smart:tableToolBtn theme="normal" event="depose" title="免职">
							<smart:icon icon="close (alias)"></smart:icon>
						</smart:tableToolBtn>
						<smart:tableToolBtn theme="default" event="demote" title="降职">
							<smart:icon icon="arrow-down"></smart:icon>
						</smart:tableToolBtn>
						<smart:tableToolBtn theme="warm" event="shift" title="轮岗">
							<smart:icon icon="refresh"></smart:icon>
						</smart:tableToolBtn>
					</smart:tableToolBar>
				</smart:table>
			</smart:gridRow>
			<smart:grid>
				<smart:form id="saveForm" action="#">

					<smart:gridRow>
						<smart:gridColumn>
							<smart:buttonGroup container="true">
								<smart:button theme="warm" size="sm" title="返回" method="goBack">
									<smart:icon icon="reply"></smart:icon>&nbsp;返回
								</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:grid>
		</smart:cardBody>

	</smart:card>
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils />
		<smart:buttonScriptAction>
		goBack : function(data) {
			parent.location.reload(); // 父页面刷新
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			parent.layer.close(index);
		},			addPost : function(data) {
				smart.show({
					title:'职务子集新增',
					size:'full',
					url:'ofc/post/edit',
					params:{
						servantId:'${servantId}'
					},
					scrollbar:false
				});
			}	
		</smart:buttonScriptAction>
		<smart:dateRender id="jobchangeDate" />
		form.on('submit(save)', function (data) {//表单保存
			var params=data.field;
			var url=data.form.action;
			$.post(url,params,function(result){
				if(result.success){//保存成功
	                layer.alert(
	                result.message, 
	                {icon: 1,closeBtn: 1 },
	                function () {
	                	parent.layui.table.reload('navigationList');
	                	var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
	                });
				}else{
					smart.message({
						message : result.message,
						type : 'E' //S保存  I问号  W感叹号 E错误
					});
				}
			});
			return false;
		});
		
		
		
				<smart:tableScriptAction tableId="postNavigationList"
					checkbox="true" sort="true" rowEdit="true">
				promote : function(data) {
					smart.show({
						title : '职务晋升',
						size : 'full',
						url : "ofcflow/jobchange/promote/"+"${servant.id }"+"/post/"+data.data.id,
						scrollbar : false
					});
				},
				depose : function(data) {
					smart.show({
						title : '免职',
						size : 'full',
						url : "ofcflow/jobchange/depose/"+"${servant.id }"+"/post/"+data.data.id,
						scrollbar : false
					});
				},
				demote : function(data) {
					smart.show({
						title : '降职',
						size : 'full',
						url : "ofcflow/jobchange/demote/"+"${servant.id }"+"/post/"+data.data.id,
						scrollbar : false
					});
				},
				shift : function(data) {
					smart.show({
						title : '轮岗',
						size : 'full',
						url : "ofcflow/jobchange/shift/"+"${servant.id }"+"/post/"+data.data.id,
						scrollbar : false
					});
				},
				reload : function(){
					table.reload('postNavigationList');
				}
		</smart:tableScriptAction>
	</smart:scriptHead>
</smart:body>
</html>