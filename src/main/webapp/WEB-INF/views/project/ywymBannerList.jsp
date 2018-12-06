<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>轮播图列表</title>
    <%@ include file="/WEB-INF/views/include/style.jsp"%>
</head>
<style>
    .horizontal-content {
        display: flex;
        align-items: center;
        justify-content: center;
    }
</style>
<body>
<ul class="nav nav-tabs sys-font">
    <li class="active"><a href="/sys/ywymBannerWebController/list">轮播图列表</a></li>
    <li><a href="/sys/ywymBannerWebController/form">轮播图添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="ywymBanner" action="/sys/ywymBannerWebController/list" method="post" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>轮播图名称：</label>
            <form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>上架状态：</label>
            <form:select path="status" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fnc:getDictList('banner_status')}"   htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>添加时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymBanner.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymBanner.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li><label>更新时间：</label>
            <input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymBanner.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymBanner.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>
</form:form>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
        <tr>
            <th style="text-align: center;">序号</th>
            <th style="text-align: center;">类型</th>
            <th style="text-align: center;">轮播图名称</th>
            <th style="text-align: center;">轮播图素材</th>
            <th style="text-align: center;">链接</th>
            <th style="text-align: center;">对象</th>
            <th style="text-align: center;">状态</th>
            <th style="text-align: center;">添加时间</th>
            <th style="text-align: center;">更新时间</th>
            <th style="text-align: center;">操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <td style="text-align: center;">${entity.rank }</td>
            <td style="text-align: center;">${fnc:getDictLabel(entity.type, 'banner_type' ,' ') }</td>
            <td style="text-align: center;">${entity.title }</td>
            <td style="text-align: center;" class="horizontal-content"><div style="width:60px; height:60px;border:1px solid #ddd" class="horizontal-content" ><img class="myImg" src="${entity.img}" style="vertical-align: center;" width="60px" height="60px"/></div></td>
            <td style="text-align: center;">${entity.link }</td>
            <td style="text-align: center;">${entity.objectId }</td>
            <td style="text-align: center;">${fnc:getDictLabel(entity.status, 'banner_status' ,' ') }</td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;">
                <a href="/sys/ywymBannerWebController/form?id=${entity.id }">修改</a>
                <a href="/sys/ywymBannerWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sys:page page="${page }"/>
</body>
</html>
