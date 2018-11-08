<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="smart"
           uri="http://smart.wondersgroup.com/page/component" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
    <smart:initHead title="长宁区公务员信息管理系统--通用统计"/>
</head>
<smart:body>
    <smart:grid>
        <smart:card>
            <smart:cardHead>
                <smart:breadcrumbNavMenu separator=">">
                    <smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
                    <smart:breadcrumbNavMenuItem iname="通用统计" cite="true"></smart:breadcrumbNavMenuItem>
                </smart:breadcrumbNavMenu>
            </smart:cardHead>
            <smart:cardBody>

                <smart:gridRow>
                    <smart:fieldSet title="条件查询" style="margin-top: 5px;" color="blue">
                        <smart:form id="jobChange_searchForm"
                                    action="ofcflow/assess/batchAssess">
                            <smart:gridColumn colPart="4">
                                <smart:textInput labelName="单位名称：" display="block"
                                                 placeholder="请输入单位名称" name="orgName"></smart:textInput>
                            </smart:gridColumn>
                            <smart:gridColumn colPart="3" colOffset="1">
                                <smart:buttonGroup container="true" align="left">
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

                        </smart:form>
                    </smart:fieldSet>
                </smart:gridRow>
                <smart:gridRow colSpace="5">
                    <smart:gridColumn colPart="12" deviceType="md">
                        <smart:table id="navigationList"
                                     url="analysis/generalCount/indexData" height="full-215"
                                     limits="10,50,100" text="未找到有效数据！">
                            <tr>
                                <smart:tableItem field="unitBasicSimpleName" width="270">单位</smart:tableItem>
                                <smart:tableItem field="actualNumber" width="170">实有人数</smart:tableItem>
                                <smart:tableItem field="unitPlanningTotal" width="170">核定批准编制总数</smart:tableItem>
                                <smart:tableItem field="divisionChiefLevelNumber" width="170">处级人数</smart:tableItem>
                                <smart:tableItem field="sectionChiefLevelNumber" width="170">科级人数</smart:tableItem>
                                <smart:tableItem field="nonLeaderSectionChiefLevelNumber"
                                                 width="170">副科人数</smart:tableItem>
                                <smart:tableItem field="staffMembersNumber" width="170">科员、办事员及其他人员数</smart:tableItem>
                                <smart:tableItem field="notIntoNum" width="170">试用期人数</smart:tableItem>
                                <smart:tableItem align="center" width="170" fixed="right"
                                                 unresize="true" toolbar="navListToolBar">操作</smart:tableItem>
                            </tr>
                            <smart:tableToolBar id="navListToolBar">
                                <smart:tableToolBtn theme="default" event="view" title="查看">
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
        var ids =new Array();//存放选中复选框的id
        var table_data=new Array();
        <smart:tableScriptAction tableId="navigationList"
                                 checkbox="true" sort="true" rowEdit="true">
            view : function(data) {
            smart.show({
            title : '单位详情查看',
            size : 'full',
            url : 'analysis/generalCount/unitPage/'+data.data.id,
            scrollbar : false
            });
            }
        </smart:tableScriptAction>
        var buttonInvokeMethod = {
        search : function() {
        var params = utils.json($('#jobChange_searchForm'));
        table.reload('navigationList', {
        where : params,
        page : {
        curr : 1
        }
        });
        }
        };

        $('#jobChange_searchForm button').on('click', function() {
        var othis = $(this), method = othis.data('method');
        buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
        });


    </smart:scriptHead>
</smart:body>
</html>