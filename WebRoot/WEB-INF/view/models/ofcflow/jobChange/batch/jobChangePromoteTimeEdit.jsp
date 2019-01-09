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
                            <smart:title title="原职务" style="margin-top: 5px;" color="blue"/>
                            <smart:gridRow>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="原职务" infovalue="${jobShift.formerPostName }"></smart:infoShowerLabel>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="晋升职务" infovalue="${jobShift.newPostName }"></smart:infoShowerLabel>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="晋升职务属性" infovalue="${jobShift.newPostAttribute.name }"></smart:infoShowerLabel>
                                </smart:gridColumn>




                            </smart:gridRow>
                            <smart:gridRow>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="晋升类别" infovalue="${jobShift.postTenureChange.name }"></smart:infoShowerLabel>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="晋升原因" infovalue="${jobShift.promotionType.name }"></smart:infoShowerLabel>
                                </smart:gridColumn>
                                    <smart:gridColumn colPart="4">
                                        <smart:date labelName="任职日期：" isNotNull="true" verify="required"
                                                    display="block" name="postChangeDate"
                                                    value="${jobShift.postChangeDate}"
                                                    id="jobChangeDate"></smart:date>
                                    </smart:gridColumn>
                            </smart:gridRow>
                            <smart:gridRow>



                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="职级名称" infovalue="${jobShift.newJobLevel.name }"></smart:infoShowerLabel>
                                </smart:gridColumn>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="职级属性" infovalue="${jobShift.isLeaderStr }"></smart:infoShowerLabel>
                                </smart:gridColumn>

                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="最高职务标识" infovalue="${jobShift.highestPostSign.name }"></smart:infoShowerLabel>
                                </smart:gridColumn>


                            </smart:gridRow>
                            <smart:gridRow>
                                <smart:gridColumn colPart="4">
                                    <smart:infoShowerLabel infoname="是否兼任下级领导职务" infovalue="${jobShift.isLowerLeader.name }"></smart:infoShowerLabel>
                                </smart:gridColumn>
                            </smart:gridRow>

                            <smart:gridRow>
                                <smart:gridColumn colPart="12">
                                    <smart:textarea isNotNull="true" verify="required"  other="disabled"
                                                    display="block" labelName="备注:"
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