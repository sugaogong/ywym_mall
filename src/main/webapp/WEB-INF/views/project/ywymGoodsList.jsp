<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>礼品列表</title>
    <%@ include file="/WEB-INF/views/include/style.jsp"%>
    <style>
        /*隐藏radio按钮*/
        input[type="radio"] {
            opacity: 0;
        }
        .myradio {
            display: inline-block;
            vertical-align: middle;
            margin: 0;
            padding: 0;
            width: 80px;
            height: 30px;
            border-radius: 20px;
            position: relative;
            overflow: hidden;
        }
        .mrclose {
            background-color: #4ab0ce;
        }
        .mropen {
            background-color: #fab41d;
        }

        .myradio .open, .myradio .close {
            width: 28px;
            height: 28px;
            font-size: 14px;
            font-weight:normal;
            border-radius: 50%;
            background: #fff;
            color: #fff;
            position: absolute;
            top: 0;
            left: 0;
            border: 1px solid #e8e8e8;
        }

        .myradio .open {
            color: #fff;
            background-color: #fff;
        }

        .disabled {
            pointer-events: none;
            cursor: default;
        }

        .myradio .close {
            left: auto;
            right: 0;
            opacity: 1;
        }

        .myradio .open:after {
            content: '否';
            position: absolute;
            top: 0;
            left: 38px;
            width: 30px;
            height: 30px;
            line-height: 28px;
        }

        .myradio .close:before {
            content: '是';
            position: absolute;
            top: 0;
            left: -38px;
            width: 30px;
            height: 30px;
            line-height: 28px;
        }
    </style>
</head>
<body>
<ul class="nav nav-tabs sys-font">
    <li class="active"><a href="/sys/ywymGoodsWebController/list">礼品列表</a></li>
    <li><a href="/sys/ywymGoodsWebController/form">礼品添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="ywymGoods" action="/sys/ywymGoodsWebController/list" method="post" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>礼品分类：</label>
            <form:select path="catlogId" class="input-medium">
            <form:option value="" label="全部" />
            <c:forEach items="${catlogList}" var="item">
                <form:option value="${item.id}" label="${item.name}"/>
            </c:forEach>title
            </form:select>
        </li>
        <li><label>礼品类型：</label>
            <form:select path="type" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fnc:getDictList('goods_type')}"   htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>上下架：</label>
            <form:select path="isShelve" class="input-medium">
                <form:option value="" label="全部"/>
                <form:option value="1" label="已上架"/>
                <form:option value="0" label="已下架"/>
            </form:select>
        </li>
        <li><label>首页展示：</label>
            <form:select path="isIndex" class="input-medium">
                <form:option value="" label="全部"/>
                <form:option value="1" label="是"/>
                <form:option value="0" label="否"/>
            </form:select>
        </li>
        <br>
        <br>
        <li><label>礼品名称：</label>
            <form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>库存：</label>
            <form:input path="stock" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>添加时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymGoods.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymGoods.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>
</form:form>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
        <tr>
            <th>序号</th>
            <th style="text-align: center;">礼品分类</th>
            <th style="text-align: center;">礼品名称</th>
            <th style="text-align: center;">规格</th>
            <th style="text-align: center;">积分</th>
            <th style="text-align: center;">库存</th>
            <th style="text-align: center;">预警库存</th>
            <th style="text-align: center;">销售数量</th>
            <th style="text-align: center;">礼品类型</th>
           <%-- <th style="text-align: center;">原价（积分）</th>
            <th style="text-align: center;">价格</th>
            <th style="text-align: center;">参考价格</th>
            <th style="text-align: center;">轮播图</th>
            <th style="text-align: center;">礼品详情</th>--%>
            <th style="text-align: center;">礼品状态</th>
            <th style="text-align: center;">首页展示状态</th>
            <th style="text-align: center;">创建时间</th>
            <th style="text-align: center;">更新时间</th>
            <th style="text-align: center;">操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <td style="text-align: center;">${entity.rank }</td>
            <td style="text-align: center;">${entity.catlogName }</td>
            <td style="text-align: left;"><img class="myImg" src="${entity.icon }" width="60px" height="60px"/>&nbsp;&nbsp;${entity.name }</td>
            <td style="text-align: center;">${entity.spec }</td>
            <td style="text-align: center;">${entity.nowScore }</td>
            <td style="text-align: center;">${entity.stock }</td>
            <td style="text-align: center;">${entity.warnStock }</td>
            <td style="text-align: center;">${entity.saleNum }</td>
            <td style="text-align: center;">${fnc:getDictLabel(entity.type, 'goods_type' ,' ') }</td>
           <%-- <td style="text-align: center;">${entity.oldScore }</td>
            <td style="text-align: center;">${entity.price }</td>
            <td style="text-align: center;">${entity.refPrice }</td>
            <td style="text-align: center;">${entity.img }</td>
            <td style="text-align: center;">${entity.details }</td>--%>
            <td style="text-align: center;">
                <c:choose>
                    <c:when test="${entity.status eq '0'}">
                        已下架
                    </c:when>
                    <c:when test="${entity.status eq '1'}">
                        在售中
                    </c:when>
                    <c:when test="${entity.status eq '2'}">
                        已售罄
                    </c:when>
                </c:choose>
            </td>
            <td style="text-align: center;">
                <c:if test="${entity.isIndex eq 1}">
                    <p class="myradio mrclose">
                        <label class="open" style="display: none">
                            <input type="radio" value="open" data-id="${entity.id}"/>
                        </label>
                        <label class="close">
                            <input type="radio" value="close" data-id="${entity.id}"/>
                        </label>
                    </p>
                </c:if>
                <c:if test="${entity.isIndex eq 0}">
                    <p class="myradio mropen">
                        <label class="open">
                            <input type="radio" value="open" data-id="${entity.id}"/>
                        </label>
                        <label class="close" style="display: none">
                            <input type="radio" value="close" data-id="${entity.id}"/>
                        </label>
                    </p>
                </c:if>
            </td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;">
                <c:if test="${entity.isShelve ne '1'}">
                    <a href="/sys/ywymGoodsWebController/upperOrLower?id=${entity.id }&isShelve=1">上架</a>
                </c:if>
                <c:if test="${entity.isShelve eq '1'}">
                    <a href="/sys/ywymGoodsWebController/upperOrLower?id=${entity.id }&isShelve=0&isIndex=0">下架</a>
                </c:if>
                <a href="/sys/ywymGoodsWebController/form?id=${entity.id }">编辑</a>
                <a href="/sys/ywymGoodsWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sys:page page="${page }"/>
<script>
    $(".myradio input").click(function(e){
        var id = $(this).attr('data-id');
        var state =  $(this).val();
        var myradio = $(".myradio");
        var iclose = $(this).parents(".myradio").find('.close');
        var iopen = $(this).parents(".myradio").find('.open');
        $(this).parents(".myradio").find(':radio').removeAttr('checked');
        $(this).parent('label').addClass('disabled');
        $(this).parent('label').siblings('label').find(':radio').attr('checked',true);
        if (state == 'open') {
            $(this).parents(".myradio").removeClass('mropen').addClass('mrclose');
            open();
        } else {
            $(this).parents(".myradio").removeClass('mrclose').addClass('mropen');
            close();
        }

        function open(){
            iopen.animate({left:"50px"},100);
            setTimeout(function(){
                iopen.hide();
                iclose.show();
                iopen.css('left',0);
                $(".myradio label").removeClass('disabled');
            },300);
            $.ajax( {
                url : "/sys/ywymGoodsWebController/authIndex",
                data: {
                    id: id,
                    isIndex: 1
                },
                type : "GET",
                dataType : "json",
                success : function(data) {
                    console.log(data)
                },error: function() {
                    console.log(data.resultMessage);
                }
            });
        }

        function close(){
            iclose.animate({left:"0px"},100);
            setTimeout(function(){
                iclose.hide();
                iopen.show();
                iclose.css('left','50px');
                $(".myradio label").removeClass('disabled');
            },300);
            $.ajax( {
                url : "/sys/ywymGoodsWebController/authIndex",
                data: {
                    id: id,
                    isIndex: 0
                },
                type : "GET",
                dataType : "json",
                success : function(data) {
                    console.log(data)
                },error: function(data) {
                    console.log(data);
                }
            });
        }
    })
</script>
</body>
</html>
