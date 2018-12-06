<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>用户流水记录列表</title>
    <%@ include file="/WEB-INF/views/include/style.jsp"%>
</head>
<body>
<ul class="nav nav-tabs sys-font">
    <li class="active"><a href="/sys/ywymLogUserWebController/list">用户流水记录列表</a></li>
    <%--<li><a href="/sys/ywymLogUserWebController/form">用户流水记录添加</a></li>--%>
</ul>
<form:form id="searchForm" modelAttribute="ywymLogUser" action="/sys/ywymLogUserWebController/list" method="post" class="breadcrumb form-search">
   <%-- <ul class="ul-form">
        <li><label>序号：</label>
            <form:input path="rank" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>积分变动  -1支出 1入账：</label>
            <form:input path="type" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>用户id：</label>
            <form:input path="userId" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>对象id：</label>
            <form:input path="objectId" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>积分：</label>
            <form:input path="score" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>内容：</label>
            <form:input path="remark" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>创建时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymLogUser.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymLogUser.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li><label>更新时间：</label>
            <input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymLogUser.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymLogUser.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>--%>
</form:form>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
        <tr>
            <th style="text-align: center;">序号</th>
            <th style="text-align: center;">账户</th>
            <th style="text-align: center;">内容</th>
           <%-- <th style="text-align: center;">积分变动</th>--%>
            <%--<th style="text-align: center;">对象id</th>--%>
            <th style="text-align: center;">积分</th>
            <th style="text-align: center;">积分变动日期</th>
          <%--  <th style="text-align: center;">更新时间</th>--%>
            <th style="text-align: center;">操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <td style="text-align: center;">${entity.rank }</td>
            <td style="text-align: center;">${entity.username }</td>
            <td style="text-align: center;">${entity.remark }</td>
           <%-- <td style="text-align: center;">${entity.type }</td>--%>
           <%-- <td style="text-align: center;">${entity.objectId }</td>--%>
            <td style="text-align: center;">${entity.type eq '-1' ? '-' : '+' } ${entity.score }</td>


            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <%--<td style="text-align: center;"><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
            <td style="text-align: center;">
                <a href="/sys/ywymLogUserWebController/form?id=${entity.id }">修改</a>
                <a href="/sys/ywymLogUserWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sys:page page="${page }"/>
</body>
</html>
