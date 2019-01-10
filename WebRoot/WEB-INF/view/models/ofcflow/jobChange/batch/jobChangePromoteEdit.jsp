<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="smart"
           uri="http://smart.wondersgroup.com/page/component" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <smart:initHead title="事项申请--公务员职务变动--职务晋升"/>
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
            <smart:form id="editForm"
                        action="ofcflow/jobchangeB/editJobShiftPromote">
                <smart:fromTokenTag/>
                <smart:gridRow colSpace="5">
                    <smart:gridColumn>

                        <smart:grid>
                            <smart:title title="现职务信息" style="margin-top: 5px;" color="blue"/>
                            <smart:gridRow>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="姓名"
                                                           infovalue="${servant.name}"></smart:infoShowerLabel>
                                    <smart:textInput type="hidden" name="servant.id"
                                                     value="${servant.id}"></smart:textInput>
                                    <c:if test="${!empty jobShift }">
                                        <smart:textInput type="hidden" name="id"
                                                         value="${jobShift.id}"></smart:textInput>
                                    </c:if>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="原职级"
                                                           infovalue="${jobLevel.name}"></smart:infoShowerLabel>
                                    <smart:textInput type="hidden" name="formerJobLevelName"
                                                     value="${jobLevel.name}"></smart:textInput>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="原职级属性"
                                                           infovalue="${jobLevel.isLeaderStr}"></smart:infoShowerLabel>
                                </smart:gridColumn>
                            </smart:gridRow>
                            <smart:title title="职务变动信息" style="margin-top: 5px;" color="blue"/>
                            <smart:gridRow>
                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect labelName="原职务："   id="formerPostCode"
                                                        name="prePost.id" display="block"
                                                        url="ofcflow/jobchangeB/activeAndNoChangeJob/${servant.id}" isAddDefaltOption="true"
                                                        initSelectedKey="${jobShift.prePost.id }" isSaveShowName="true"
                                                        inputShowName="formerPostName" verify="required" isNotNull="true"></smart:singleSelect>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect id="postCode" isAddDefaltOption="true"
                                                        name="newPostCode.id" labelName="晋升职务：" display="block"
                                                        url="dictquery/sub/code/GBT_12403_1990" verify="required"
                                                        initSelectedKey="${jobShift.newPostCode.id}"
                                                        isNotNull="true" isSaveShowName="true"
                                                        inputShowName="newPostName"></smart:singleSelect>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect id="attribute" isAddDefaltOption="true"
                                                        name="newPostAttribute.id" labelName="晋升职务属性："
                                                        display="block" url="dictquery/sub/code/DM049"
                                                        initSelectedKey="${jobShift.newPostAttribute.id }"
                                                        verify="required" isNotNull="true"></smart:singleSelect>
                                </smart:gridColumn>




                            </smart:gridRow>
                            <smart:gridRow>
                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect isAddDefaltOption="true"
                                                        name="postTenureChange.id" labelName="晋升类别：" display="block"
                                                        url="dictquery/sub/code/DM006/2" verify="required"
                                                        initSelectedKey="${jobShift.postTenureChange.id}"
                                                        isNotNull="true"></smart:singleSelect>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect isAddDefaltOption="true"
                                                        name="promotionType.id" labelName="晋升原因：" display="block"
                                                        url="dictquery/sub/code/DM113" verify="required"
                                                        initSelectedKey="${jobShift.promotionType.id}"
                                                        isNotNull="true"></smart:singleSelect>
                                </smart:gridColumn>
                                <c:if test="${isStep13}">
                                    <smart:gridColumn colPart="4">
                                        <smart:date labelName="任职日期：" isNotNull="true" verify="required"
                                                    display="block" name="postChangeDate"
                                                    value="${jobShift.postChangeDate}"
                                                    id="jobChangeDate"></smart:date>
                                    </smart:gridColumn>
                                </c:if>
                            </smart:gridRow>
                            <smart:gridRow>



                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect id="jobLevel" labelName="职级名称：" name="newJobLevel.id"
                                                        display="block"  verify="required" isNotNull="true"
                                                        url="dictquery/sub/code/GBT_12407_2008/1"
                                                        isAddDefaltOption="true" changeCallBack="singleSelectChange"
                                                        initSelectedKey="${jobShift.newJobLevel.id}"
                                                        initIncludeKey="${jobLevelArray}" ></smart:singleSelect>

                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect labelName="职级属性：" data="[{'key':'1','value':'领导'},{'key':'0','value':'非领导'}]" id="isLeader" name="isLeader"
                                                        verify="required" isNotNull="true"     display="block" isAddDefaltOption="true" initSelectedKey="${jobShift.isLeader }" ></smart:singleSelect>
                                </smart:gridColumn>

                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect id="highestPostSign" labelName="最高职务标识："
                                                        name="highestPostSign.id" display="block"
                                                        url="dictquery/sub/code/DM215" isAddDefaltOption="true"
                                                        initSelectedKey="${jobShift.highestPostSign.id }"
                                                        verify="required" isNotNull="true"
                                                        ></smart:singleSelect>
                                </smart:gridColumn>


                            </smart:gridRow>
                            <smart:gridRow>
                                <smart:gridColumn colPart="4">
                                    <smart:singleSelect labelName="是否兼任下级领导职务：" shortName="兼下级领导职务"  id="isLowerLeader"
                                                        name="isLowerLeader.id" display="block"
                                                        url="dictquery/sub/code/DM215" isAddDefaltOption="true"
                                                        initSelectedKey="${jobShift.isLowerLeader.id }"
                                                        verify="required" isNotNull="true"></smart:singleSelect>
                                </smart:gridColumn>
                            </smart:gridRow>

                        <%--    <smart:gridRow>
                                <smart:gridColumn colPart="10" colOffset="2">
                                    <smart:fileUpload accept="file" buttonName="人员信息任免表"
                                                      dataName="appointSheetFilePath" auto="false"
                                                      name="cancelLetter" size="20000"
                                                      data="${jobShift.appointSheetFilePath}"/>
                                </smart:gridColumn>
                            </smart:gridRow>--%>
                            <smart:gridRow>
                                <smart:gridColumn colPart="12">
                                    <smart:textarea display="block" labelName="备注:"
                                                    name="remark">${jobShift.remark }</smart:textarea>
                                </smart:gridColumn>
                            </smart:gridRow>
                        </smart:grid>
                    </smart:gridColumn>
                </smart:gridRow>


                <smart:gridRow>
                    <smart:gridColumn>
                        <smart:buttonGroup container="true">
                            <c:if test="${!view}">
                                <smart:button id="save" other="lay-submit" size="sm" method="save"
                                              title="提交当前表单">
                                    <smart:icon icon="check">提交</smart:icon>
                                </smart:button>
                            </c:if>
                            <smart:button theme="warm" size="sm" title="返回" method="goBack">
                                <smart:icon icon="reply"></smart:icon>&nbsp;返回
                            </smart:button>
                        </smart:buttonGroup>
                    </smart:gridColumn>
                </smart:gridRow>
            </smart:form>
        </smart:cardBody>

    </smart:card>
    <smart:scriptHead models="table,form,layer,element,laydate,upload">
        <smart:utils/>
        <smart:dateRender id="jobChangeDate"/>
        <smart:continuousSelectAction/>
        <smart:fileUploadUtils/>
        <smart:buttonScriptAction>
            goBack : function(data) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);
            }
        </smart:buttonScriptAction>
        <smart:dateRender id="jobchangeDate"/>
        form.on('submit(save)', function (data) {//表单保存
            var oldJobLevel='${jobLevel.code.id}';
            var oldIsLeader='${jobLevel.isLeader}';
            var newJobLevel=data.field["newJobLevel.id"];
            var newIsLeader=data.field.isLeader;
            if(oldJobLevel==newJobLevel&&oldIsLeader==newIsLeader){
                smart.message({
                    message : "新职级与旧职级相同!请重新填写!",
                    type : 'E' //S保存 I问号 W感叹号 E错误
                });
                return;
            }
            $("#result").val("1");
            $("#isSubmit").val("1");
            var params=smart.json("#editForm");
            var url=data.form.action;
            $.post(url,params,function(result){
                if(result.success){//保存成功
                    layer.alert(
                    result.message,
                    {icon: 1,closeBtn: 1 },
                    function () {
                        try{
                            parent.layui.table.reload('navigationList');
                            parent.layer.closeAll();
                            parent.parent.parent.layui.table.reload('navigationList');
                            parent.parent.parent.layer.closeAll();
                        }catch(err){
                            parent.layui.table.reload('navigationList');
                            parent.layer.closeAll();
                        }

                    });
                }else{
                    smart.message({
                        message : result.message,
                        type : 'E' //S保存 I问号 W感叹号 E错误
                    });
               }
        });
        return false;
        });


        <smart:tableScriptAction tableId="postNavigationList"
                                 checkbox="true" sort="true" rowEdit="true">
            reload : function(){
            table.reload('postNavigationList');
            }
        </smart:tableScriptAction>
    </smart:scriptHead>
</smart:body>
</html>