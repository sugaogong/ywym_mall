<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
	    <title>积分商城管理后台</title>
	    <%@ include file="/WEB-INF/views/include/style.jsp"%>
	    <style>
	    .clearfix:after{content:"";display:block;height:0;clear:both;visibility:hidden;}
		.clearfix{zoom:1;}
	    div{margin:0;padding:0;position:relative;font-family:微软雅黑;}
	    .div-wrap{width:100%;height:100%;}
	    .div-nav{margin-bottom:10px;}
	    .div-body{width:100%;height:100%;}
	    .body-left{width:150px;position:absolute;left:0;top:0;}
	    .body-right{position:absolute;left:160px;top:0;}
	    .menu-a,.menu-a:hover{text-decoration:none !important;text-align:center;font-weight:bold;}
	    .menu-three-div{margin-top:5px;}
	    .menu-three-a{display:block;wdith:100%;text-align:center;text-decoration:none !important;}
	    .menu-three-a-active{background-color:#2FA4E7;color:#FFFFFF !important;border-radius:2px;}
	    .iframe-body{width:100%;height:100%;overflow:scroll;}
	    .hide{display:none;}
		.navbar-inner {color: white;min-height: 45px;background: #09102C;}
		.navbar .brand {
			display: block;
			float: left;
			padding: 12px 20px 10px;
			margin-left: -20px;
			font-size: 20px;
			font-weight: 200;
			color: #f2f2f2;
			text-shadow: 0 0px 0 #fff;
		}
		.nav>.active>a:focus {
			color: white;
			text-decoration: none;
			background-color: black;
			-webkit-box-shadow: inset 0 3px 8px rgba(0,0,0,0.125);
			-moz-box-shadow: inset 0 3px 8px rgba(0,0,0,0.125);
			box-shadow: inset 0 3px 8px rgba(0,0,0,0.125);
		}
		.navbar .nav>li>a {
			float: none;
			padding: 15px 15px 10px;
			color: white;
			text-decoration: none;
			text-shadow: 0 0px 0 #fff;
		}
	    .a-logout{color:white;float:right;margin-top:15px;}
	    .a-welcome{color:white;float:right;margin-top:15px;}
	    </style>
  	</head>
	<body>
    	<div class=" div-wrap">
			<div class="navbar div-nav">
			  	<div class="navbar-inner">
					<a class="brand" href="/sys/indexWebController/index;">&nbsp;&nbsp;&nbsp;积分商城管理后台&nbsp;&nbsp;&nbsp;</a>
				    <ul class="nav menu-one-ul">
				    	<%--<li class="active menu-one-li"><a href="javascript:;">系统设置</a></li>
				      	<li class="menu-one-li"><a href="javascript:;">首页</a></li>--%>
				    </ul>
				    <a class="a-logout" href="/sys/indexWebController/logout">退出</a>
					<a class="a-logout editPassword" href="javascript:void(0)" style="margin-right: 20px;">修改密码</a>
					<b class="a-welcome" href="javascript:void(0)" style="margin-right: 30px;">欢迎您：${username }</b>
			  	</div>
			</div>
    		<!-- 主体 -->
	    	<div class="div-body clearfix">
	   			<div class="body-left">
					<div class="accordion" id="menu-left">
					</div>
				</div>
	   			<div class="body-right">
					<iframe class="iframe-body" name="main" frameborder="no" border="0" marginwidth="0" marginheight="0" ></iframe>
				</div>
	    	</div>
    	</div>
	</body>
	<script type="text/javascript">
	$(document).ready(function(){
		// 用一个全局数组变量缓存二级菜单
		var menuTwos = [];
		// 设定主体div的宽高自适应
		var rw = $(window).width() - $(".body-left").width();
		var rh = $(window).height() - 70;
		$(".body-right").width(rw-20);
		$(".body-right").height(rh);
		// 一级菜单点击
		$(".menu-one-li").live("click",function(){
			$(".menu-one-li").removeClass("active");
			$(this).addClass("active");
			var index = $(this).index();
			$("#menu-left").html(menuTwos[index]);
		});
		// 二级菜单点击
		var ma;
		$(".menu-a").live("click",function(){
		    var index = $(this).parent().parent().index();
		    if(ma === index) {
                ma = null;
                $(this).find(".menu-two-i").css({'transform': 'rotate(0deg)'});
			} else {
                ma = index;
                $(".menu-two-i").css({'transform': 'rotate(0deg)'});
                $(this).find(".menu-two-i").css({'transform': 'rotate(90deg)'});
			}
		});
		// 三级菜单点击
		$(".menu-three-a").live("click",function(){
			$(".menu-three-a").removeClass("menu-three-a-active");
			$(this).addClass("menu-three-a-active");
		});
		// 异步读取一级菜单		
		$.ajax( {
			url : "/ajax/sysMenuAjaxController/findAllList",
			type : "post",
			dataType : "json",
			success : function(data) {
				var menuOneStr = "";
				for(var i=0;i<data.resultData.length;i++){
					var menuTwo = "";
                	<shiro:hasAnyRoles name="admin">
					if(i == 0){
						menuOneStr += "<li title='"+data.resultData[i].id+"' class='active menu-one-li'><a href='javascript:;'>"+data.resultData[i].name+"</a></li>";
					}else{
						menuOneStr += "<li title='"+data.resultData[i].id+"' class='menu-one-li'><a href='javascript:;'>"+data.resultData[i].name+"</a></li>";
					}
                	</shiro:hasAnyRoles>
					if(data.resultData[i].childList != null && typeof(data.resultData[i].childList) != "undefined" && data.resultData[i].childList.length>0){
						var menuTwoList = data.resultData[i].childList;
						for(var k=0;k<menuTwoList.length;k++){
							/* ------------------- 二级菜单 start ------------------- */
							menuTwo += "<div class='accordion-group menu-two-group "+data.resultData[i].id+"'>";
							menuTwo += "<div class='accordion-heading'>";
							menuTwo += "<a class='accordion-toggle menu-a collapsed' data-toggle='collapse' data-parent='#menu-left' href='#sys-menu-two"+menuTwoList[k].id+"'>";
							if (menuTwoList[k].icon != null){
								menuTwo += "<img class='myImg' src='"+menuTwoList[k].icon+"' width='18px' height='18px'/> &nbsp;&nbsp;&nbsp;" ;
							}
							menuTwo += menuTwoList[k].name;
							menuTwo += "&nbsp;<i class='menu-two-i icon-chevron-right' style='background-position: -456px -70px'></i></a>";
							menuTwo += "</div>";
							menuTwo += "<div id='sys-menu-two"+menuTwoList[k].id+"' class='accordion-body collapse'>";
							menuTwo += "<div class='accordion-inner'>";
							if(menuTwoList[k].childList != null && typeof(menuTwoList[k].childList) != "undefined" && menuTwoList[k].childList.length>0){
								var menuThreeStr = "";
								for(var c=0;c<menuTwoList[k].childList.length;c++){
									var menuThree = menuTwoList[k].childList[c];
									menuThreeStr += "<div class='menu-three-div'><a class='menu-three-a' href='${ctx}"+menuThree.href+"' target='main'>"+menuThree.name+"</a></div>";
								}
								menuTwo += menuThreeStr;
							}
							menuTwo += "</div>";
							menuTwo += "</div>";
							menuTwo += "</div>";
							/* -------------------- 二级菜单 end ------------------- */
						}
					}
					menuTwos.push(menuTwo);	// 把二级菜单放进全部数组变量menuTwo中
				}

				$(".menu-one-ul").html(menuOneStr);	// 显示一级菜单
				$("#menu-left").html(menuTwos[0]);	// 显示二级菜单数组menuTwo的第0个
				// 首页默认打开第1个二级菜单的第1个三级菜单
				var href = $("#menu-left .menu-three-a").eq(0).attr("href");
				window.main.location.href=href;
				$("#menu-left .menu-three-a").eq(0).addClass("menu-three-a-active");
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("error : /ajax/sysMenuAjaxController/findAllList");
            }
		});

        $(".editPassword").on('click', function () {
            layer.open({
                type: 2,
                title:'修改密码',
                area: ['28%','280px'],
                shadeClose:true,
                closeBtn:true,
                content: ['${ctx }/sys/userWebController/goEditPassword'], //iframe的url，no代表不显示滚动条
            });
        })
	});
	</script>
</html>
