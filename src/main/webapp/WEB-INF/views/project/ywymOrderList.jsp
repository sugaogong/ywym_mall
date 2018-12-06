<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>订单列表</title>
    <%@ include file="/WEB-INF/views/include/style.jsp" %>
    <!-- 快递鸟 -->
    <link rel="stylesheet" type="text/css" href="${ctxStatic }/kdniao/KDNWidget.css"/>
    <script type="text/javascript" src="${ctxStatic }/kdniao/KDNWidget.js"></script>
</head>
<body>
<ul class="nav nav-tabs sys-font">
    <li class="active"><a href="/sys/ywymOrderWebController/list">订单列表</a></li>
    <%-- <li><a href="/sys/ywymOrderWebController/form">订单添加</a></li>--%>
</ul>
<form:form id="searchForm" modelAttribute="ywymOrder" action="/sys/ywymOrderWebController/list" method="post"
           class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>订单编号：</label>
            <form:input path="orderNo" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>收货人：</label>
            <form:input path="receName" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>联系方式：</label>
            <form:input path="recePhone" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>礼品类型：</label>
            <form:select path="goodsType" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fnc:getDictList('goods_type')}" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>订单状态</label>
            <form:select path="status" class="input-medium">
                <form:option value="" label="全部订单"/>
                <form:options items="${fnc:getDictList('order_status')}" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>下单时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${ywymOrder.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${ywymOrder.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <%--<li><label>付款时间：</label>
            <input name="payTimeStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${ywymOrder.payTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="payTimeEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${ywymOrder.payTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>--%>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>
</form:form>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
    <tr>
        <th style="text-align: center;">用户账号</th>
        <th style="text-align: center;">订单编号</th>
        <th style="text-align: center;">礼品类型</th>
        <th style="text-align: center;">礼品名称</th>
        <th style="text-align: center;">订单（积分）</th>
        <th style="text-align: center;">收货人</th>
        <th style="text-align: center;">联系方式</th>
        <th style="text-align: center;">收货地址</th>
        <th style="text-align: center;">下单时间</th>
        <th style="text-align: center;">快递公司</th>
        <th style="text-align: center;">快递单号</th>
        <th style="text-align: center;">订单状态</th>
        <th style="text-align: center;">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <td style="text-align: center;">${entity.username }</td>
            <td style="text-align: center;"><a class="showDetail" data-id="${entity.id }" style="cursor:pointer;">${entity.orderNo }</a></td>
            <td style="text-align: center;">${fnc:getDictLabel(entity.goodsType, 'goods_type' ,' ') }</td>
            <td style="text-align: left;"><img class="myImg" src="${entity.goodsIcon }" width="60px" height="60px"/>&nbsp;&nbsp;${entity.goodsName }</td>
            <td style="text-align: center;">${entity.score }</td>
            <td style="text-align: center;">${entity.receName }</td>
            <td style="text-align: center;">${entity.recePhone }</td>
            <td style="text-align: center;">${entity.receAddress }</td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;">${fnc:getDictLabel(entity.expCode, 'exp_code' ,' ') }</td>
            <td style="text-align: center;"><a onclick="showKD(event, '${entity.expCode}','${entity.expNo}')" style="cursor:pointer;">${entity.expNo }</a></td>
            <td style="text-align: center;">${fnc:getDictLabel(entity.status, 'order_status' ,' ') }</td>
            <td style="text-align: center;">
                <c:choose>
                    <c:when test="${entity.status eq '1' && entity.goodsType eq '1' }">
                        <a><input type="button" class="send" data-id="${entity.id }" value="发 货"></a>
                    </c:when>
                    <c:when test="${entity.status eq '2' && entity.goodsType eq '1' }">
                        <a href="/sys/ywymOrderWebController/confirm?id=${entity.id }&status=3"><input type="button" value="确认收货"></input></a>
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sys:page page="${page }"/>

<!-- Modal -->
<div class="modal fade" id="myModal" style="width:400px;display:none;" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center;">请输入快递单号</h4>
            </div>
            <div class="modal-body">
                <div class="control-group">
                    <div class="controls" style="margin:12px 0">
                        快递公司：<select id="expCode" class="input-medium" style="width:165px">
                        <option value="ZTO">中通快递</option>
                        <option value="YTO">圆通速递</option>
                        <option value="YD">韵达速递</option>
                        <option value="YZPY">邮政快递</option>
                        <option value="EMS">EMS</option>
                        <option value="JD">京东物流</option>
                        <option value="QFKD">全峰快递</option>
                        <option value="GTO">国通快递</option>
                        <option value="UC">优速快递</option>
                        <option value="DBL">德邦物流</option>
                        <option value="FAST">快捷快递</option>
                        <option value="ZJS">宅急送</option>
                    </select>
                    </div>
                    <div class="controls">
                        快递单号：<input type="text" id="expNo" class="input-medium edit-top"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-info" id="save">发货</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal2 -->
<div class="modal fade" id="myModal2" style="width:900px;margin-left:-470px;height:550px;display:none;" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <h4 class="modal-title" style="text-align: center;">查询结果</h4>
        <div class="modal-content">
            <div id="expID"></div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var id;
    $(".showDetail").on('click', function () {
        var id = $(this).data('id')
        layer.open({
            type: 2,
            title:'订单详情',
            area: ['60%','650px'],
            shadeClose:true,
            closeBtn:true,
            content: ['${ctx }/sys/ywymOrderWebController/getOrderInfo?id='+ id], //iframe的url，no代表不显示滚动条
        });
    })
    $('.send').click(function () {
        $('#myModal').modal('show')
        id = $(this).attr('data-id');
    });
    $('.conform').click(function () {
        $('#myModal').modal('show')
        id = $(this).attr('data-id');
    });
    $(document).ready(function () {
        $("#save").click(function (e) {
            var expCode = $('#expCode').val();
            var expNo = $('#expNo').val();
            if (!expNo) {
                alert('亲，快递单号不能为空！');
                return;
            }
            $.ajax({
                url: "/sys/ywymOrderWebController/send",
                data: {
                    id: id,
                    expCode: expCode,
                    expNo: expNo
                },
                type: "post",
                dataType: "json",
                success: function (data) {
                    window.location.reload();
                }, error: function () {
                   /* console.log(data.resultMessage);*/
                }
            });
        });
    });

    function showKD (event, expCode,expNo) {
        event.stopPropagation();
        $('#myModal2').modal('show');
        KDNWidget.run({
            serviceType: "B",
            expCode: expCode,
            expNo: expNo,
            showType:"normal",
            container: "expID"
        })
    }
</script>
</body>
</html>
