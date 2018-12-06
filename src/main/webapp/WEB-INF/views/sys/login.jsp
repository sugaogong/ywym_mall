<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>管理后台登陆</title>
	<%@ include file="/WEB-INF/views/include/style.jsp"%>
	<style>
		.bg{background: url("../../../static/sys/img/login.jpg") no-repeat;background-size: 100% 100%; position: absolute; width: 100%; height: 100%}
		.form-wrap{width:300px;margin:300px auto 0;border:2px solid #f5f5f5;border-radius:10px;background-color: #fff;}
		.div-error{width:480px;margin:0 auto;margin-top:30px;display:none;}
		.form-horizontal {padding-left: 0;}
		.from_bottom {
			position: absolute;
			width: 400px;
			bottom: 20px;
			left: 50%;
			margin-left: -200px;
			height: 50px;
			line-height: 50px;
			text-align: center;
			color: #fff;
		}
		.icon_img {
			position: absolute;
			left: 44.7%;
			width: 24px;
			height: 40px;
			background: no-repeat center center;
			background-size: contain;
		}
		.icon_img.icon_mima {
			background-image: url("${ctx }/static/sys/img/icon-ys.png")
		}

		.icon_img.icon_zh {
			background-image: url("${ctx }/static/sys/img/icon-ren.png")
		}
	</style>
</head>
<body>
<div class="bg">
	<div class="alert alert-error div-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<span id="error-message">${message }</span>
	</div>
	<h2 style="text-align: center; position: fixed; z-index: 20; top: 22%; left: 0px; width: 100%; color: #fff;">积分商城管理后台</h2>
	<div class="form-wrap" style="background: rgba(0,0,0,0.1)">
		<form id="inputForm" method="post" action="/sys/indexWebController/login" class="form-horizontal">
			<div class="control-group"></div>
			<div class="control-group">
				<div class="controls">
					<span class="icon_img icon_zh"></span>
					<input id="username" style="margin-left: -140px; height: 30px; text-align: center;" placeholder="用户名" type="text" name="username"/>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="icon_img icon_mima"></span>
					<input id="password" style="margin-left: -140px; height: 30px; text-align: center;" placeholder="密码" type="password" name="password"/>
				</div>
			</div>
			<div class="form-group">
				<input type="submit" style="margin-left: 40px;width: 223px;" value="登录" class="btn btn-large btn-info"/>
			</div>
		</form>
	</div>
</div>
<div class="from_bottom">
	<span> Copyright @ 2018 一物一码数据(广州)实业有限公司</span>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        // 让登陆页面始终在顶级窗口打开
        if (self != top) {
            window.top.location.href="/sys/loginWebController/login";
        }
        // 错误消息不为空时显示div
        var msg = $("#error-message").text();
        if(msg != null && msg != '' && msg != 'null'){
            $(".div-error").css("display","block");
        }
        // 表单提交
        $("#inputForm").submit(function(){
            var username = $("#username").val();
            var password = $("#password").val();
            // 去除空格
            var reg=new RegExp(' ',"gm");
            username = username.replace(reg,'');
            password = password.replace(reg,'');
            // 非空校验
            if(typeof(username) != 'undefined' && username != null && username != '' && typeof(password) != 'undefined' && password != null && password != ''){
                // 密码sha1加密
                var shaObj = new jsSHA("SHA-1", "TEXT");
                shaObj.update(password);
                password = shaObj.getHash("HEX");
                $("#username").val(username);
                $("#password").val(password);
                return true;
            }
            return false;
        });
    });
</script>
</html>
