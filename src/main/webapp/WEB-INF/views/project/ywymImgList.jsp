<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>图片表列表</title>
    <%@ include file="/WEB-INF/views/include/style.jsp"%>
</head>
<body>
<ul class="nav nav-tabs sys-font">
    <li class="active"><a href="/sys/ywymImgWebController/list">图片表列表</a></li>
    <li><a href="/sys/ywymImgWebController/form">图片表添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="ywymImg" action="/sys/ywymImgWebController/list" method="post" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>对象id：</label>
            <form:input path="objectId" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>类型    1礼品图片：</label>
            <form:input path="type" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>图片：</label>
            <form:input path="img" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>排序：</label>
            <form:input path="rank" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>创建时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymImg.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymImg.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li><label>更新时间：</label>
            <input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymImg.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymImg.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>
</form:form>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
        <tr>
            <th style="text-align: center;">对象id</th>
            <th style="text-align: center;">类型    1礼品图片</th>
            <th style="text-align: center;">图片</th>
            <th style="text-align: center;">排序</th>
            <th style="text-align: center;">创建时间</th>
            <th style="text-align: center;">更新时间</th>
            <th style="text-align: center;">操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <td style="text-align: center;">${entity.objectId }</td>
            <td style="text-align: center;">${entity.type }</td>
            <td style="text-align: center;">${entity.img }</td>
            <td style="text-align: center;">${entity.rank }</td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;">
                <a href="/sys/ywymImgWebController/form?id=${entity.id }">修改</a>
                <a href="/sys/ywymImgWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sys:page page="${page }"/>
</body>
</html>
