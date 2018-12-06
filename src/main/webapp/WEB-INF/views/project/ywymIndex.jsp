<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        body {
            padding: 20px;
            background: #f5f5f5;
        }
        .header-count {
            display: flex;
        }

        .header-count .list {
            display: flex;
            align-items: center;
            flex: 1;
            flex-flow: wrap;
        }

        .header-count .list:not(:first-child) {
            margin-left: 20px;
            background: none;
        }

        .header-count .list:not(:first-child) a {
            display: flex;
            align-items: center;
            width: 100%;
            height: 100%;
            text-decoration: none;
            justify-content: flex-end;
        }

        .header-count .list:not(:first-child) .item {
            height: 120px;
            justify-content: flex-end;
        }

        .header-count .list:not(:first-child) .item .text {
            width: 120px;
            text-align: center;
        }

        .header-count .list:not(:first-child) .item .text h2 {
            font-size: 28px;
        }

        .header-count .list:nth-child(1) .item {
            padding-left: 10%;
            padding-top: 40px;
            height: 220px;
            align-items: flex-start;
            background-image: url(${ctx }/static/index/1.png);
        }

        .header-count .list:nth-child(2) .item:nth-child(1) {
            background-image: url(${ctx }/static/index/2.png);
        }

        .header-count .list:nth-child(2) .item:nth-child(2) {
            background-image: url(${ctx }/static/index/3.png);
        }

        .header-count .list:nth-child(3) .item:nth-child(1) {
            background-image: url(${ctx }/static/index/4.png);
        }

        .header-count .list:nth-child(3) .item:nth-child(2) {
            background-image: url(${ctx }/static/index/5.png);
        }

        .header-count .list:nth-child(4) .item:nth-child(1) {
            background-image: url(${ctx }/static/index/6.png);
        }

        .header-count .list:nth-child(4) .item:nth-child(2) {
            background-image: url(${ctx }/static/index/7.png);
        }

        .header-count .list h2 {
            width: 100%;
            font-size: 36px;
            color: #333333;
        }

        .header-count .list p {
            font-size: 14px;
            color: #7F8389;
        }

        .header-count .list .item {
            width: 100%;
            display: flex;
            align-items: center;
            background-size: 100% 100%;
            padding-right: 20px;
            box-shadow: 0 2px 4px 0 rgba(227, 227, 227, 0.5);
        }

        .header-count .list .item+.item {
            margin-top: 20px;
        }

        .body-count {
            display: flex;
            margin-top: 20px;
        }

        .body-count .title {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 20px;
            height: 60px;
            line-height: 60px;
            border-bottom: 1px solid #E7E7E7;
        }

        .body-count .title h2 {
            font-size: 16px;
            color: #333333;
            font-weight: normal;
        }

        .body-count .title img {
            width: 18px;
            margin-right: 10px;
            vertical-align: middle;
        }

        .body-count .platform-info {
            flex: 1;
            background: #FFF;
        }

        .body-count .platform-info .login-log {
            padding: 26px 30px 28px;
            border-bottom: 1px solid #F1F0F5;
        }

        .body-count .platform-info .login-log p {
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 14px;
            color: #333333;
        }

        .body-count .platform-info .login-log p+p {
            margin-top: 10px;
        }

        .body-count .platform-info .login-log p i {
            font-style: normal;
            font-size: 14px;
            color: #a0a0a0;
        }

        .body-count .platform-info .operation-log h2 {
            margin: 0 30px;
            padding: 16px 0 17px;
            display: flex;
            justify-content: space-between;
            font-size: 14px;
            color: #333333;
            border-bottom: 1px solid #F1F0F5;
        }

        .body-count .platform-info .operation-log h2 a {
            font-size: 12px;
            color: #016FFD;
            text-decoration: none;
        }

        .body-count .platform-info .operation-log p {
            display: flex;
            margin-top: 28px;
            height: 20px;
            line-height: 20px;
            padding: 0 30px;
            justify-content: space-between;
            font-size: 14px;
            color: #333333;
        }

        .body-count .product-rank {
            flex: 1;
            margin-left: 22px;
            background: #FFF;
        }

        .body-count .product-rank .content {
            padding: 0 20px;
        }

        .body-count .product-rank .list {
            margin-top: 17.5px;
            display: flex;
            justify-content: space-between;
            line-height: 50px;
            background: #F1F0F5;
            font-size: 14px;
            color: #333333;
        }

        .body-count .product-rank .list .index {
            width: 50px;
            text-align: center;
            font-size: 36px;
            color: #FFFFFF;
        }

        .body-count .product-rank .list .name {
            flex: 2;
            padding-left: 20px;
        }

        .body-count .product-rank .list .nums {
            padding-right: 20px;
        }

        .body-count .product-rank .list:nth-child(1) .index {
            background-color: #FC372A;
        }

        .body-count .product-rank .list:nth-child(2) .index {
            background: #EF392D;
        }

        .body-count .product-rank .list:nth-child(3) .index {
            background: #F35147;
        }

        .body-count .product-rank .list:nth-child(4) .index {
            background: #F5665D;
        }

        .body-count .product-rank .list:nth-child(5) .index {
            background: #F37971;
        }

        .body-count .product-rank .list:nth-child(6) .index {
            background: #FF938B;
        }

        .body-count .product-rank .list:nth-child(7) .index {
            background: #FCA29C;
        }

        .body-count .product-rank .list:nth-child(8) .index {
            background: #FFBDB9;
        }
    </style>
</head>

<body>
<div class="header-count">
    <div class="list">
        <div class="item">
            <div class="text">
                <h2>${map.totScore}</h2>
                <p>成交总积分</p>
            </div>
        </div>
    </div>
    <div class="list">
        <div class="item">
            <div class="text">
                <h2>${map.visUser}</h2>
                <p>今日访客</p>
            </div>
        </div>
        <div class="item">
            <a href="/sys/ywymOrderWebController/list?status=1">
                <div class="text">
                    <h2>${map.penOrder}</h2>
                    <p>待发货订单数</p>
                </div>
            </a>
        </div>
    </div>
    <div class="list">
        <div class="item">
            <a href="/sys/ywymOrderWebController/list?status=3">
            <div class="text">
                <h2>${map.comOrder}</h2>
                <p>已完成订单</p>
            </div>
            </a>
        </div>
        <div class="item">
            <a href="/sys/ywymGoodsWebController/list?isShelve=1">
            <div class="text">
                <h2>${map.uppGoods}</h2>
                <p>出售礼品数</p>
            </div>
            </a>
        </div>
    </div>
    <div class="list">
        <div class="item">
            <a href="/sys/ywymOrderWebController/list?status=2">
            <div class="text">
                <h2>${map.recOrder}</h2>
                <p>待收货订单</p>
            </div>
            </a>
        </div>
        <div class="item">
            <a href="/sys/ywymGoodsWebController/list?isShelve=0">
            <div class="text">
                <h2>${map.lowGoods}</h2>
                <p>下架礼品数</p>
            </div>
            </a>
        </div>
    </div>
</div>
<div class="body-count">
    <div class="platform-info">
        <div class="title">
            <h2><img src="${ctx }/static/index/ptxx.png" />操作日志</h2>
            <a href="/sys/ywymLogHandleWebController/list">更多 ></a>
        </div>
        <div class="operation-log">
            <c:forEach items="${map.logHandleList}" var="item">
                <p><span style="width: 138px;"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                   <span style="width: 100px;">${item.username}</span>
                   <span style="width: 200px;">${item.content}</span>
                   <span style="width: 40px;">${item.type}</span>
                   <span style="width: 100px;">${item.score}</span>
                   <span>${item.remark}</span></p>
            </c:forEach>
        </div>
    </div>
    <div class="product-rank">
        <div class="title">
            <h2><img src="${ctx }/static/index/pm.png" />单品销售排名</h2>
        </div>
        <div class="content">
            <c:forEach items="${map.goodsList}" var="item" varStatus="index">
                <div class="list">
                    <div class="index">${index.index+1}</div>
                    <div class="name">${item.name}</div>
                    <div class="nums">销量${item.saleNum}</div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>

</html>
