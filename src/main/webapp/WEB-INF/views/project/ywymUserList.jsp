<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>用户表列表</title>
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
    <li class="active"><a href="/sys/ywymUserWebController/list">用户表列表</a></li>
    <%--<li><a href="/sys/ywymUserWebController/form">用户表添加</a></li>--%>
</ul>
<form:form id="searchForm" modelAttribute="ywymUser" action="/sys/ywymUserWebController/list" method="post" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>昵称：</label>
            <form:input path="nickname" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>账号：</label>
            <form:input path="username" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>性别：</label>
            <%--<form:input path="gender" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
            <form:select path="gender" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fnc:getDictList('gender')}"   htmlEscape="false"/>
            </form:select>
        </li>
       <%-- <li><label>密码：</label>
            <form:input path="password" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>手机号码：</label>
            <form:input path="phone" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>

        <li><label>头像：</label>
            <form:input path="headImg" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>支付密码：</label>
            <form:input path="payPassword" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>--%>
        <%--<li><label>当前积分：</label>
            <form:input path="score" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>获得总积分：</label>
            <form:input path="totalGetScore" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>消耗总积分：</label>
            <form:input path="totalUseScore" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>--%>
      <%--  <li><label>微信openid：</label>
            <form:input path="openId" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>--%>
        <li><label>用户状态：</label>
            <%--<form:input path="locked" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
            <form:select path="locked" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fnc:getDictList('locked')}"   htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>创建时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymUser.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymUser.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li><label>更新时间：</label>
            <input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymUser.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymUser.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>
</form:form>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
        <tr>
            <th style="text-align: center;">头像</th>
            <th style="text-align: center;">昵称</th>
            <th style="text-align: center;">账号</th>
            <th style="text-align: center;">性别</th>
            <%--<th style="text-align: center;">密码</th>--%>
            <%--<th style="text-align: center;">手机号码</th>--%>
           <%-- <th style="text-align: center;">支付密码</th>--%>
            <th style="text-align: center;">当前积分</th>
            <th style="text-align: center;">获得总积分</th>
            <th style="text-align: center;">消耗总积分</th>
            <%--<th style="text-align: center;">微信openid</th>--%>
            <th style="text-align: center;">用户状态</th>
            <th style="text-align: center;">创建时间</th>
            <th style="text-align: center;">更新时间</th>
            <th style="text-align: center;">操作</th>
        </tr>
    </thead>
<%--<c:choose>
    <c:when test="${not empty page.list}">--%>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <%--<td style="text-align: center;">${entity.headImg }</td>--%>
            <td style="text-align: center;"><img class="myImg" src="${entity.headImg }" width="60px" height="60px" style="border-radius:50%;"/></td>
            <td style="text-align: center;">${entity.nickname }</td>
            <td style="text-align: center;">${entity.username }</td>
            <%--<td style="text-align: center;">${entity.gender }</td>--%>
            <td style="text-align: center;">${fnc:getDictLabel(entity.gender, 'gender' ,' ') }</td>
            <%--<td style="text-align: center;">${entity.password }</td>--%>
            <%--<td style="text-align: center;">${entity.phone }</td>--%>

           <%-- <td style="text-align: center;">${entity.payPassword }</td>--%>
            <td style="text-align: center;">${entity.score }</td>
            <td style="text-align: center;">${entity.totalGetScore }</td>
            <td style="text-align: center;">${entity.totalUseScore }</td>
            <%--<td style="text-align: center;">${entity.openId }</td>--%>
            <td style="text-align: center;">${fnc:getDictLabel(entity.locked, 'locked' ,' ') }</td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;">
                <a href="javascript:seeScore('${entity.id }','${entity.username}')" id="seeScore">积分明细</a> &nbsp
                <%--<a href="/sys/ywymUserWebController/form?id=${entity.id }">积分明细</a>--%>
                <%--<c:choose>
                    <c:when test="${entity.locked ne '1' }">
                        <a href="/sys/ywymUserWebController/lockOrNot?id=${entity.id }&locked=1" ><input type="button" value="锁定"/></a>
                    </c:when>
                    <c:otherwise >
                        <a href="/sys/ywymUserWebController/lockOrNot?id=${entity.id }&locked=0" ><input type="button" value="正常"/></a>
                    </c:otherwise>
                </c:choose>--%>
                <c:if test="${entity.locked ne '1'}">
                    <a href="/sys/ywymUserWebController/lockOrNot?id=${entity.id }&locked=1">锁定</a>
                </c:if>
                <c:if test="${entity.locked eq '1'}">
                    <a href="/sys/ywymUserWebController/lockOrNot?id=${entity.id }&locked=0">解锁</a>
                </c:if>
                <%--<a href="/sys/ywymUserWebController/form?id=${entity.id }">修改</a>
                <a href="/sys/ywymUserWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>--%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <%--</c:when>
    <c:otherwise >
        <div>暂无数据</div>
    </c:otherwise>
</c:choose>--%>
</table>
<sys:page page="${page }"/>
<script type="text/javascript">
    function seeScore(userId,username){
        layer.open({
            type: 2,
            title: username + ' 积分明细',
            area: ['50%','600px'],
            shadeClose:true,
            closeBtn:true,
            content: ['${ctx }/sys/ywymLogUserWebController/gotUserScoreList?userId='+ userId], //iframe的url，no代表不显示滚动条
        });
    }
</script>
</body>


</html>
